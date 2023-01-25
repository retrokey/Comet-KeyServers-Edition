package com.cometproject.website.utilities;

import com.cometproject.website.config.Configuration;
import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtil.class);

    public static String hash(String password) {
        String hash = "";

        try {
            switch(Configuration.getInstance().getPasswordHashMode()) {
                case "sha1":
                    hash += sha1(password + Configuration.getInstance().getPasswordSalt());
                    break;

                case "bcrypt":
                    hash += BCrypt.hashpw(password, Configuration.getInstance().getPasswordSalt());
                    break;
            }

        } catch(Exception e) {
            LOGGER.error("Error while hashing password", e);
        }

        return hash;
    }

    private static String sha1(String string) {
        return DigestUtils.sha1Hex(string);
    }
}
