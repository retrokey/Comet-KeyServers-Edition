/*package com.cometproject.server.game.guides;

import com.cometproject.api.utilities.Initialisable;
import com.cometproject.server.game.guides.types.HelpRequest;
import com.cometproject.server.game.guides.types.HelperSession;
import com.cometproject.server.network.messages.outgoing.help.guides.*;
import com.cometproject.server.network.messages.outgoing.notification.AlertMessageComposer;
import com.cometproject.server.tasks.CometThreadManager;
import com.cometproject.server.utilities.collections.ConcurrentHashSet;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GuideManager implements Initialisable {
    private static GuideManager guideManagerInstance;

    private final Map<Integer, HelperSession> sessions = new ConcurrentHashMap<>();

    private final Map<Integer, Boolean> activeGuides = new ConcurrentHashMap<>();
    private final Set<Integer> activeGuardians = new ConcurrentHashSet<>();

    private final Map<Integer, HelpRequest> activeHelpRequests = new ConcurrentHashMap<>();

    public static GuideManager getInstance() {
        if (guideManagerInstance == null) {
            guideManagerInstance = new GuideManager();
        }

        return guideManagerInstance;
    }

    @Override
    public void initialize() {
        CometThreadManager.getInstance().executePeriodic(this::processRequests, 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    private void processRequests() {
        // Loop through every request and make sure it has an attached guide, if it doesn't.. find a guide that hasn't
        // declined it yet.

        for (HelpRequest helpRequest : this.activeHelpRequests.values()) {
            if (!helpRequest.hasGuide()) {
                if (helpRequest.getProcessTicks() >= 60) {
                    // Find a guide!
                    for (Map.Entry<Integer, Boolean> activeGuide : activeGuides.entrySet()) {
                        if (!activeGuide.getValue()) { // Guide is available!
                            if (!helpRequest.declined(activeGuide.getKey())) {
                                helpRequest.setGuide(activeGuide.getKey());
                                helpRequest.getGuideSession().getPlayer().setHelpRequest(helpRequest);
                                helpRequest.getPlayerSession().getPlayer().setHelpRequest(helpRequest);

                                helpRequest.getPlayerSession().send(new GuideSessionAttachedMessageComposer(helpRequest, false));
                                helpRequest.getGuideSession().send(new GuideSessionAttachedMessageComposer(helpRequest, true));
                                helpRequest.resetProcessTicks();
                                break;
                            }
                        }
                    }

                    if (helpRequest.hasGuide()) {
                        this.activeGuides.put(helpRequest.guideId, true);
                    }

                    // None found? Search again!
                    helpRequest.resetProcessTicks();
                } else {
                    helpRequest.incrementProcessTicks();
                }
            }

            if(helpRequest.hasGuide() && !helpRequest.getValidation()){
                if (helpRequest.getProcessTicks() >= 60) {

                    helpRequest.getGuideSession().send(new GuideSessionDettachedMessageComposer());
                    GuideManager.getInstance().finishPlayerDuty(helpRequest.getGuideSession().getPlayer().getHelperSession());
                    helpRequest.getGuideSession().send(new GuideToolsMessageComposer(false));

                    helpRequest.decline(helpRequest.guideId);
                }

                helpRequest.incrementProcessTicks();
            }
        }
    }

    public void startPlayerDuty(final HelperSession helperSession) {
        this.sessions.put(helperSession.getPlayerId(), helperSession);

        if (helperSession.handlesHelpRequests()) {
            this.activeGuides.put(helperSession.getPlayerId(), false);
        }

        if (helperSession.handlesBullyReports()) {
            this.activeGuardians.add(helperSession.getPlayerId());
        }
    }

    public void finishPlayerDuty(final HelperSession helperSession) {
        //check if they have any on-going stuff?

        this.sessions.remove(helperSession.getPlayerId());

        if (helperSession.handlesHelpRequests()) {
            this.activeGuides.remove(helperSession.getPlayerId());
        }

        if (helperSession.handlesBullyReports()) {
            this.activeGuardians.remove(helperSession.getPlayerId());
        }
    }

    public void requestHelp(final HelpRequest helpRequest) {
        this.activeHelpRequests.put(helpRequest.getPlayerId(), helpRequest);
    }

    public void removeRequest(final int playerId, final HelpRequest helpRequest){
        this.activeHelpRequests.remove(playerId, helpRequest);
    }

    public void flushGuide(final int guideId){
        this.activeGuides.remove(guideId);
        this.activeGuides.put(guideId, false);

    }

    public HelpRequest getHelpRequestByCreator(final int playerId) {
        return this.activeHelpRequests.get(playerId);
    }

    public int getActiveGuideCount() {
        return this.activeGuides.size();
    }

    public int getActiveGuardianCount() {
        return this.activeGuardians.size();
    }
}

*/

        package com.cometproject.server.game.guides;

        import com.cometproject.server.game.guides.types.HelpRequest;
        import com.cometproject.server.game.guides.types.HelperSession;
        import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionAttachedMessageComposer;
        import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionDetachedMessageComposer;
        import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionEndedMessageComposer;
        import com.cometproject.server.network.messages.outgoing.help.guides.GuideSessionErrorMessageComposer;
        import com.cometproject.server.tasks.CometThreadManager;
        import com.cometproject.api.utilities.Initialisable;
        import com.cometproject.server.utilities.collections.ConcurrentHashSet;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

        import java.util.Map;
        import java.util.Set;
        import java.util.concurrent.ConcurrentHashMap;
        import java.util.concurrent.TimeUnit;

public class GuideManager implements Initialisable {
    private static GuideManager guideManagerInstance;

    private final Map<Integer, HelperSession> sessions = new ConcurrentHashMap<>();

    private final Map<Integer, Boolean> activeGuides = new ConcurrentHashMap<>();
    private final Set<Integer> activeGuardians = new ConcurrentHashSet<>();

    private final Map<Integer, HelpRequest> activeHelpRequests = new ConcurrentHashMap<>();

    @Override
    public void initialize() {
        CometThreadManager.getInstance().executePeriodic(this::processRequests, 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    public Map<Integer, HelpRequest> getactiveHelpRequests()
    {
        return activeHelpRequests;
    }

    private void processRequests()
    {
        if(this.getActiveGuideCount() == 0)
        {
            if(this.activeHelpRequests.size() > 0)
            {
                for(Map.Entry<Integer, HelpRequest> help : this.activeHelpRequests.entrySet())
                {
                    HelpRequest helpRequest = help.getValue();
                    if(helpRequest.getPlayerSession() != null && !helpRequest.IsBEGIN())
                        helpRequest.getPlayerSession().send(new GuideSessionErrorMessageComposer(GuideSessionErrorMessageComposer.NO_HELPERS_AVAILABLE));
                    else if(helpRequest.getPlayerSession() != null && helpRequest.IsBEGIN())
                        helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(1));
                }

                this.activeHelpRequests.clear();
            }

            return;
        }

        Map<Integer, HelpRequest> req = new ConcurrentHashMap<>(this.activeHelpRequests);

        List<Map.Entry<Integer, HelpRequest>> entries = new ArrayList<Map.Entry<Integer, HelpRequest>>(req.entrySet());
        Collections.shuffle(entries);
        for(Map.Entry<Integer, HelpRequest> help : entries)
        {
            HelpRequest helpRequest = help.getValue();
            if(!helpRequest.IsBEGIN())
            {
                if(helpRequest.getProcessTicks() >= 60)
                {
                    if(helpRequest.hasGuide())
                    {
                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, false);
                        else
                            this.activeGuides.put(helpRequest.guideId, false);

                        if(helpRequest.getGuideSession() != null)
                            helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                        helpRequest.decline(helpRequest.guideId);
                        helpRequest.setGuide(0);
                    }

                    boolean Active = false;
                    for(Map.Entry<Integer, Boolean> activeGuide : activeGuides.entrySet())
                    {
                        if(!activeGuide.getValue())
                        {
                            if(!helpRequest.declined(activeGuide.getKey()))
                            {
                                Active = true;
                                helpRequest.setGuide(activeGuide.getKey());
                                helpRequest.getGuideSession().getPlayer().setHelpRequest(helpRequest);
                                helpRequest.getPlayerSession().getPlayer().setHelpRequest(helpRequest);
                                helpRequest.getPlayerSession().send(new GuideSessionAttachedMessageComposer(helpRequest, false));
                                helpRequest.getGuideSession().send(new GuideSessionAttachedMessageComposer(helpRequest, true));
                                break;
                            }
                        }
                    }

                    if(!Active)
                    {
                        helpRequest.getPlayerSession().send(new GuideSessionErrorMessageComposer(GuideSessionErrorMessageComposer.NO_HELPERS_AVAILABLE));
                        this.activeHelpRequests.remove(help.getKey());
                        continue;
                    }

                    if(helpRequest.hasGuide())
                    {
                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, true);
                        else
                            this.activeGuides.put(helpRequest.guideId, true);
                    }

                    helpRequest.resetProcessTicks();
                }
                else
                {
                    if(helpRequest.IsStop())
                    {
                        if(helpRequest.getPlayerSession() != null)
                        {
                            //helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(0));
                            helpRequest.getPlayerSession().send(new GuideSessionDetachedMessageComposer());
                        }

                        if(helpRequest.getGuideSession()!= null)
                        {
                            //helpRequest.getGuideSession().send(new GuideSessionEndedMessageComposer(0));
                            helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                            if(this.activeGuides.containsKey(helpRequest.guideId))
                                this.activeGuides.replace(helpRequest.guideId, false);
                            else
                                this.activeGuides.put(helpRequest.guideId, false);
                        }

                        this.activeHelpRequests.remove(help.getKey());
                        continue;
                    }

                    helpRequest.incrementProcessTicks();
                }

            }
            else
            {
                if(helpRequest.getPlayerSession() == null || helpRequest.getGuideSession() == null)
                {
                    if(helpRequest.getPlayerSession() != null)
                    {
                        //helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getPlayerSession().send(new GuideSessionDetachedMessageComposer());
                    }

                    if(helpRequest.getGuideSession()!= null)
                    {
                        //helpRequest.getGuideSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, false);
                        else
                            this.activeGuides.put(helpRequest.guideId, false);
                    }

                    this.activeHelpRequests.remove(help.getKey());
                    continue;
                }

                if(helpRequest.getPlayerSession().getPlayer() == null || helpRequest.getGuideSession().getPlayer() == null)
                {
                    if(helpRequest.getPlayerSession() != null)
                    {
                        //helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getPlayerSession().send(new GuideSessionDetachedMessageComposer());
                    }

                    if(helpRequest.getGuideSession()!= null)
                    {
                        //helpRequest.getGuideSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, false);
                        else
                            this.activeGuides.put(helpRequest.guideId, false);
                    }

                    this.activeHelpRequests.remove(help.getKey());
                    continue;
                }

                if(helpRequest.getPlayerSession().getPlayer().getHelpRequest() == null && helpRequest.getGuideSession().getPlayer().getHelpRequest() == null)
                {
                    if(helpRequest.getPlayerSession() != null)
                    {
                        //helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getPlayerSession().send(new GuideSessionDetachedMessageComposer());
                    }

                    if(helpRequest.getGuideSession()!= null)
                    {
                        //helpRequest.getGuideSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, false);
                        else
                            this.activeGuides.put(helpRequest.guideId, false);
                    }

                    this.activeHelpRequests.remove(help.getKey());
                    continue;
                }

                if(helpRequest.getPlayerSession().getPlayer().getHelpRequest() == null || helpRequest.getGuideSession().getPlayer().getHelpRequest() == null)
                {
                    if(helpRequest.getPlayerSession() != null)
                    {
                        //helpRequest.getPlayerSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getPlayerSession().send(new GuideSessionDetachedMessageComposer());
                    }

                    if(helpRequest.getGuideSession()!= null)
                    {
                        //helpRequest.getGuideSession().send(new GuideSessionEndedMessageComposer(0));
                        helpRequest.getGuideSession().send(new GuideSessionDetachedMessageComposer());

                        if(this.activeGuides.containsKey(helpRequest.guideId))
                            this.activeGuides.replace(helpRequest.guideId, false);
                        else
                            this.activeGuides.put(helpRequest.guideId, false);
                    }

                    this.activeHelpRequests.remove(help.getKey());
                    continue;
                }
            }
        }
    }

    public void startPlayerDuty(final HelperSession helperSession) {
        this.sessions.put(helperSession.getPlayerId(), helperSession);

        if(helperSession.handlesHelpRequests()) {
            this.activeGuides.put(helperSession.getPlayerId(), false);
        }

        if(helperSession.handlesBullyReports()) {
            this.activeGuardians.add(helperSession.getPlayerId());
        }
    }

    public void finishPlayerDuty(final HelperSession helperSession) {
        //check if they have any on-going stuff?

        if(this.sessions.containsKey(helperSession.getPlayerId())) {
            this.sessions.remove(helperSession.getPlayerId());
        }

        if(helperSession.handlesHelpRequests()) {
            this.activeGuides.remove(helperSession.getPlayerId());
        }

        if(helperSession.handlesBullyReports()) {
            this.activeGuardians.remove(helperSession.getPlayerId());
        }
    }

    public void requestHelp(final HelpRequest helpRequest) {
        this.activeHelpRequests.put(helpRequest.getPlayerId(), helpRequest);
    }

    public HelpRequest getHelpRequestByCreator(final int playerId) {
        return this.activeHelpRequests.get(playerId);
    }

    public int getActiveGuideCount() {
        return this.activeGuides.size();
    }

    public int getActiveGuardianCount() {
        return this.activeGuardians.size();
    }

    public static GuideManager getInstance() {
        if(guideManagerInstance == null) {
            guideManagerInstance = new GuideManager();
        }

        return guideManagerInstance;
    }
}
