package com.cometproject.catalogtool;

import com.cometproject.server.boot.Comet;
import com.cometproject.api.config.Configuration;
import com.cometproject.server.game.catalog.CatalogManager;
import com.cometproject.server.game.items.ItemManager;
import com.cometproject.server.storage.StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

public class CatalogTool {
    private static Logger LOGGER = LoggerFactory.getLogger(CatalogTool.class.getName());

    public static void main(String[] args) {
        if(args.length != 2) {
            LOGGER.warn("Invalid arguments, expecting pageId and furniline");
        }

        final int pageId = Integer.parseInt(args[0]);
        final String furniline = args[1];

        Configuration.setConfiguration(new Configuration("./config/comet.properties"));

        StorageManager.getInstance().initialize();
        ItemManager.getInstance().initialize();
        CatalogManager.getInstance().initialize();

        //parse xml
        // loop through every item with furniline = furniline and create definitions where needed
        // and create catalog items
    }
}
