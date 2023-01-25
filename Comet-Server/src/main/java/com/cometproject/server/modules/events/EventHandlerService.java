package com.cometproject.server.modules.events;

import com.cometproject.api.commands.CommandInfo;
import com.cometproject.api.events.Event;
import com.cometproject.api.events.EventArgs;
import com.cometproject.api.events.EventHandler;
import com.cometproject.api.networking.sessions.ISession;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class EventHandlerService implements EventHandler {
    private final ExecutorService asyncEventExecutor;
    private final Logger LOGGER = LoggerFactory.getLogger(EventHandlerService.class.getName());

    private final Map<Class<?>, List<Event>> listeners;

    private final Map<String, BiConsumer<ISession, String[]>> chatCommands;
    private final Map<String, CommandInfo> commandInfo;

    public EventHandlerService() {
        this.asyncEventExecutor = Executors.newCachedThreadPool();

        this.listeners = Maps.newConcurrentMap();
        this.chatCommands = Maps.newConcurrentMap();
        this.commandInfo = Maps.newConcurrentMap();
    }

    public void initialize() {
        if (this.listeners != null) {
            this.listeners.clear();
        }

        if (this.chatCommands != null) {
            this.chatCommands.clear();
        }

        if (this.commandInfo != null) {
            this.commandInfo.clear();
        }
    }

    @Override
    public void registerCommandInfo(String commandName, CommandInfo info) {
        this.commandInfo.put(commandName, info);
    }

    @Override
    public void registerChatCommand(String commandExecutor, BiConsumer<ISession, String[]> consumer) {
        this.chatCommands.put(commandExecutor, consumer);
    }

    @Override
    public void registerEvent(Event consumer) {
        if (this.listeners.containsKey(consumer.getClass())) {
            this.listeners.get(consumer.getClass()).add(consumer);
        } else {
            this.listeners.put(consumer.getClass(), Lists.newArrayList(consumer));
        }

        LOGGER.debug(String.format("Registered event listener for %s", consumer.getClass().getSimpleName()));
    }

    @Override
    public <T extends EventArgs> boolean handleEvent(Class<? extends Event> eventClass, T args) {
        if (this.listeners.containsKey(eventClass)) {
            this.invoke(eventClass, args);
            LOGGER.debug(String.format("Event handled: %s\n", eventClass.getSimpleName()));
        } else {
            LOGGER.debug(String.format("Unhandled event: %s\n", eventClass.getSimpleName()));
        }

        return args.isCancelled();
    }

    @Override
    public Map<String, CommandInfo> getCommands() {
        return this.commandInfo;
    }

    @Override
    public boolean handleCommand(ISession session, String commandExectutor, String[] arguments) {
        if (!this.chatCommands.containsKey(commandExectutor) || !this.commandInfo.containsKey(commandExectutor)) {
            return false;
        }

        CommandInfo commandInfo = this.commandInfo.get(commandExectutor);

        if (!session.getPlayer().getPermissions().hasCommand(commandInfo.getPermission()) && !commandInfo.getPermission().isEmpty()) {
            return false;
        }

        BiConsumer<ISession, String[]> chatCommand = this.chatCommands.get(commandExectutor);

        try {
            chatCommand.accept(session, arguments);
        } catch (Exception e) {
            LOGGER.warn("Failed to execute module command: " + commandExectutor);
        }

        return true;
    }

    private <T extends EventArgs> void invoke(Class<? extends Event> eventClass, T args) {
        for (Event event : this.listeners.get(eventClass)) {
            try {
                if (event.isAsync()) {
                    this.asyncEventExecutor.submit(() -> {
                        event.consume(args);
                    });
                } else {
                    event.consume(args);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}