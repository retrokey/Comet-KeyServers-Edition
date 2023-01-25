package com.cometproject.server.config;

import com.cometproject.server.storage.queries.config.LocaleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class Locale {
    /**
     * Logging for locale object
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Locale.class);

    /**
     * Store locale in memory
     */
    private static Map<String, String> locale;

    /**
     * Initialize the locale
     */
    public static void initialize() {
        reload();
    }

    /**
     * Load locale from the database
     */
    public static void reload() {
        if (locale != null)
            locale.clear();

        locale = LocaleDao.getAll();
        LOGGER.info("Loaded " + locale.size() + " locale strings");
    }

    /**
     * Get a locale string by the key
     *
     * @param key Retrieve from the locale by the key
     * @return String from the locale
     */
    public static String get(String key) {
        if (locale.containsKey(key))
            return locale.get(key);
        else
            return key;
    }

    public static String getOrDefault(String key, String defaultValue) {
        if (!locale.containsKey(key)) {
            return defaultValue;
        }

        return locale.get(key);
    }

    public static Map<String, String> getAll() {
        return locale;
    }
}
