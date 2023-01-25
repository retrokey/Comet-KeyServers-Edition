package com.cometproject.website.storage.dao;

import com.cometproject.website.storage.SqlStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoHelper {
    private static SqlStorageManager storageManager;
    private static Logger LOGGER = LoggerFactory.getLogger(DaoHelper.class);

    public static void init(SqlStorageManager sqlStorageManager) {
        storageManager = sqlStorageManager;
    }

    public static Connection getConnection() {
        try {
            return storageManager.getConnections().getConnection();
        } catch(Exception e) {
            handleException(e);
        }

        return null;
    }

    public static void close(Object closableObject) {
        try {
            if (closableObject instanceof Connection) {
                ((Connection) closableObject).close();
            } else if (closableObject instanceof ResultSet) {
                ((ResultSet) closableObject).close();
            } else if(closableObject instanceof PreparedStatement) {
                ((PreparedStatement) closableObject).close();
            }
        } catch(Exception e) {
            handleException(e);
        }
    }


    public static void handleException(Exception e) {
        LOGGER.error("Exception thrown from DAO", e);
    }
}
