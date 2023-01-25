package com.cometproject.website.boot;

import com.cometproject.website.storage.SqlStorageManager;
import com.cometproject.website.website.WebsiteManager;

public class CometWebsite {
    private final SqlStorageManager sqlStorageManager;
    private final WebsiteManager websiteManager;

    public CometWebsite() {

        this.sqlStorageManager = new SqlStorageManager();
        this.websiteManager = new WebsiteManager();
    }

    private static String[] arguments;
    private static CometWebsite cometManager;

    public static void main(String[] args) {
        arguments = args;
        cometManager = new CometWebsite();
    }

    public static String[] getArguments() {
        return arguments;
    }

    public static CometWebsite getInstance() {
        return cometManager;
    }
}
