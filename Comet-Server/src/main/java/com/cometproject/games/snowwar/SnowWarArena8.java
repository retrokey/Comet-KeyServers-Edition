package com.cometproject.games.snowwar;

import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.MachineGameObject;
import com.cometproject.games.snowwar.gameobjects.TreeGameObject;
import com.cometproject.games.snowwar.items.BaseItem;
import com.cometproject.games.snowwar.items.MapStuffData;

import java.util.ArrayList;
import java.util.Map;

public class SnowWarArena8 extends SnowWarArenaBase {
    public SnowWarArena8() {
        ArenaHeight = 50;
        ArenaWidth = 50;
        HeightMap = "" +
                "xxxxxxxxxxxxxxxxxxx00000xxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxx00000000000000000xxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxx0000000000000000000xxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxx000000000000000000000xxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxx00000000000000000000000xxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxx0000000000000000000000000xxxxxxxxxxxxxxxx\r" +
                "xxxxxxxx000000000000000000000000000xxxxxxxxxxxxxxx\r" +
                "xxxxxxx00000000000000000000000000000xxxxxxxxxxxxxx\r" +
                "xxxxxx0000000000000000000000000000000xxxxxxxxxxxxx\r" +
                "xxxxx000000000000000000000000000000000xxxxxxxxxxxx\r" +
                "xxxx00000000000000000000000000000000000xxxxxxxxxxx\r" +
                "xxx0000000000000000000000000000000000000xxxxxxxxxx\r" +
                "xx000000000000000000000000000000000000000xxxxxxxxx\r" +
                "x00000000000000000000000000000000000000000xxxxxxxx\r" +
                "00000000000000000000xxxxx0xxxxxxx0000000000xxxxxxx\r" +
                "00000000000000000000xxxxx0xxxxxxx00000000000xxxxxx\r" +
                "00000000000000000000xxxxx0xxxxxxx000000000000xxxxx\r" +
                "00000000000000000000xxx0000000xxx0000000000000xxxx\r" +
                "x0000000000000000000xxx0000000xxx00000000000000xxx\r" +
                "xx000000000000000000xxx0000000000000000000000000xx\r" +
                "xxx00000000000000000xxx0000000xxx0000000000000000x\r" +
                "xxxx00000000000000000000000000xxx00000000000000000\r" +
                "xxxxx000000000000000xxx0000000xxx00000000000000000\r" +
                "xxxxxx00000000000000xxxxxxx0xxxxx00000000000000000\r" +
                "xxxxxxx0000000000000xxxxxxx0xxxxx00000000000000000\r" +
                "xxxxxxxx000000000000xxxxxxx0xxxxx00000000000000000\r" +
                "xxxxxxxxx00000000000000000000000000000000000000000\r" +
                "xxxxxxxxxx000000000000000000000000000000000000000x\r" +
                "xxxxxxxxxxx0000000000000000000000000000000000000xx\r" +
                "xxxxxxxxxxxx00000000000000000000000000000000000xxx\r" +
                "xxxxxxxxxxxxx000000000000000000000000000000000xxxx\r" +
                "xxxxxxxxxxxxxx0000000000000000000000000000000xxxxx\r" +
                "xxxxxxxxxxxxxxx00000000000000000000000000000xxxxxx\r" +
                "xxxxxxxxxxxxxxxx000000000000000000000000000xxxxxxx\r" +
                "xxxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxx000000000000000000000xxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxx0000000000000000000xxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxx00000000000000000xxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxx00000xxxxxxxxxxxxxxxxxx\r";


        this.fuseObjects = new ArrayList<>(200);

        GamefuseObject Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_block1;
        Item.baseItem.allowWalk = false;
        Item.baseItem.Height = 1.0F;
        Item.itemId = 0;
        Item.X = 41;
        Item.Y = 37;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_block1;
        Item.baseItem.allowWalk = false;
        Item.baseItem.Height = 1.0F;
        Item.itemId = 1;
        Item.X = 2;
        Item.Y = 20;
        Item.Rot = 0;
        Item.Z = 1440;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);
        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_block1;
        Item.baseItem.allowWalk = false;
        Item.baseItem.Height = 1.0F;
        Item.itemId = 2;
        Item.X = 10;
        Item.Y = 18;
        /*  103 */     Item.Rot = 6;
        /*  104 */     Item.Z = 1440;
        /*  105 */     Item.extraData.setExtraData("0");
        /*  106 */     fuseObjects.add(Item);
        /*      */
        /*  108 */     Item = new GamefuseObject();
        /*  109 */     Item.baseItem = BaseItem.snst_tree1;
        /*  110 */     Item.baseItem.allowWalk = false;
        /*  111 */     Item.baseItem.Height = 1.0F;
        /*  112 */     Item.itemId = 3;
        /*  113 */     Item.X = 19;
        /*  114 */     Item.Y = 41;
        /*  115 */     Item.Rot = 0;
        /*  116 */     Item.Z = 0;
        /*  117 */     Item.extraData.setExtraData("0");
        /*  118 */     fuseObjects.add(Item);
        /*      */
        /*  120 */     Item = new GamefuseObject();
        /*  121 */     Item.baseItem = BaseItem.snst_block1;
        /*  122 */     Item.baseItem.allowWalk = false;
        /*  123 */     Item.baseItem.Height = 1.0F;
        /*  124 */     Item.itemId = 4;
        /*  125 */     Item.X = 41;
        /*  126 */     Item.Y = 20;
        /*  127 */     Item.Rot = 0;
        /*  128 */     Item.Z = 0;
        /*  129 */     Item.extraData.setExtraData("0");
        /*  130 */     fuseObjects.add(Item);
        /*      */
        /*  132 */     Item = new GamefuseObject();
        /*  133 */     Item.baseItem = BaseItem.snst_tree1;
        /*  134 */     Item.baseItem.allowWalk = false;
        /*  135 */     Item.baseItem.Height = 1.0F;
        /*  136 */     Item.itemId = 5;
        /*  137 */     Item.X = 9;
        /*  138 */     Item.Y = 31;
        /*  139 */     Item.Rot = 0;
        /*  140 */     Item.Z = 0;
        /*  141 */     Item.extraData.setExtraData("0");
        /*  142 */     fuseObjects.add(Item);
        /*      */
        /*  144 */     Item = new GamefuseObject();
        /*  145 */     Item.baseItem = BaseItem.snst_block1;
        /*  146 */     Item.baseItem.allowWalk = false;
        /*  147 */     Item.baseItem.Height = 1.0F;
        /*  148 */     Item.itemId = 6;
        /*  149 */     Item.X = 23;
        /*  150 */     Item.Y = 44;
        /*  151 */     Item.Rot = 0;
        /*  152 */     Item.Z = 1440;
        /*  153 */     Item.extraData.setExtraData("0");
        /*  154 */     fuseObjects.add(Item);
        /*      */
        /*  156 */     Item = new GamefuseObject();
        /*  157 */     Item.baseItem = BaseItem.snst_block1;
        /*  158 */     Item.baseItem.allowWalk = false;
        /*  159 */     Item.baseItem.Height = 1.0F;
        /*  160 */     Item.itemId = 7;
        /*  161 */     Item.X = 4;
        /*  162 */     Item.Y = 18;
        /*  163 */     Item.Rot = 0;
        /*  164 */     Item.Z = 0;
        /*  165 */     Item.extraData.setExtraData("0");
        /*  166 */     fuseObjects.add(Item);
        /*      */
        /*  168 */     Item = new GamefuseObject();
        /*  169 */     Item.baseItem = BaseItem.snst_block1;
        /*  170 */     Item.baseItem.allowWalk = false;
        /*  171 */     Item.baseItem.Height = 1.0F;
        /*  172 */     Item.itemId = 8;
        /*  173 */     Item.X = 2;
        /*  174 */     Item.Y = 20;
        /*  175 */     Item.Rot = 0;
        /*  176 */     Item.Z = 0;
        /*  177 */     Item.extraData.setExtraData("0");
        /*  178 */     fuseObjects.add(Item);
        /*      */
        /*  180 */     Item = new GamefuseObject();
        /*  181 */     Item.baseItem = BaseItem.snst_tree1;
        /*  182 */     Item.baseItem.allowWalk = false;
        /*  183 */     Item.baseItem.Height = 1.0F;
        /*  184 */     Item.itemId = 9;
        /*  185 */     Item.X = 25;
        /*  186 */     Item.Y = 38;
        /*  187 */     Item.Rot = 0;
        /*  188 */     Item.Z = 0;
        /*  189 */     Item.extraData.setExtraData("0");
        /*  190 */     fuseObjects.add(Item);
        /*      */
        /*  192 */     Item = new GamefuseObject();
        /*  193 */     Item.baseItem = BaseItem.snst_block1;
        /*  194 */     Item.baseItem.allowWalk = false;
        /*  195 */     Item.baseItem.Height = 1.0F;
        /*  196 */     Item.itemId = 10;
        /*  197 */     Item.X = 2;
        /*  198 */     Item.Y = 19;
        /*  199 */     Item.Rot = 0;
        /*  200 */     Item.Z = 0;
        /*  201 */     Item.extraData.setExtraData("0");
        /*  202 */     fuseObjects.add(Item);
        /*      */
        /*  204 */     Item = new GamefuseObject();
        /*  205 */     Item.baseItem = BaseItem.snst_block1;
        /*  206 */     Item.baseItem.allowWalk = false;
        /*  207 */     Item.baseItem.Height = 1.0F;
        /*  208 */     Item.itemId = 11;
        /*  209 */     Item.X = 9;
        /*  210 */     Item.Y = 18;
        /*  211 */     Item.Rot = 0;
        /*  212 */     Item.Z = 0;
        /*  213 */     Item.extraData.setExtraData("0");
        /*  214 */     fuseObjects.add(Item);
        /*      */
        /*  216 */     Item = new GamefuseObject();
        /*  217 */     Item.baseItem = BaseItem.snst_block1;
        /*  218 */     Item.baseItem.allowWalk = false;
        /*  219 */     Item.baseItem.Height = 1.0F;
        /*  220 */     Item.itemId = 12;
        /*  221 */     Item.X = 7;
        /*  222 */     Item.Y = 18;
        /*  223 */     Item.Rot = 0;
        /*  224 */     Item.Z = 0;
        /*  225 */     Item.extraData.setExtraData("0");
        /*  226 */     fuseObjects.add(Item);
        /*      */
        /*  228 */     Item = new GamefuseObject();
        /*  229 */     Item.baseItem = BaseItem.snst_fence;
        /*  230 */     Item.baseItem.allowWalk = false;
        /*  231 */     Item.baseItem.Height = 0.0F;
        /*  232 */     Item.itemId = 13;
        /*  233 */     Item.X = 17;
        /*  234 */     Item.Y = 14;
        /*  235 */     Item.Rot = 2;
        /*  236 */     Item.Z = 0;
        /*  237 */     Item.extraData.setExtraData("2");
        /*  238 */     fuseObjects.add(Item);
        /*      */
        /*  240 */     Item = new GamefuseObject();
        /*  241 */     Item.baseItem = BaseItem.snst_block1;
        /*  242 */     Item.baseItem.allowWalk = false;
        /*  243 */     Item.baseItem.Height = 1.0F;
        /*  244 */     Item.itemId = 14;
        /*  245 */     Item.X = 2;
        /*  246 */     Item.Y = 18;
        /*  247 */     Item.Rot = 0;
        /*  248 */     Item.Z = 0;
        /*  249 */     Item.extraData.setExtraData("0");
        /*  250 */     fuseObjects.add(Item);
        /*      */
        /*  252 */     Item = new GamefuseObject();
        /*  253 */     Item.baseItem = BaseItem.snst_tree1;
        /*  254 */     Item.baseItem.allowWalk = false;
        /*  255 */     Item.baseItem.Height = 1.0F;
        /*  256 */     Item.itemId = 15;
        /*  257 */     Item.X = 49;
        /*  258 */     Item.Y = 28;
        /*  259 */     Item.Rot = 0;
        /*  260 */     Item.Z = 0;
        /*  261 */     Item.extraData.setExtraData("0");
        /*  262 */     fuseObjects.add(Item);
        /*      */
        /*  264 */     Item = new GamefuseObject();
        /*  265 */     Item.baseItem = BaseItem.snst_block1;
        /*  266 */     Item.baseItem.allowWalk = false;
        /*  267 */     Item.baseItem.Height = 1.0F;
        /*  268 */     Item.itemId = 16;
        /*  269 */     Item.X = 2;
        /*  270 */     Item.Y = 22;
        /*  271 */     Item.Rot = 6;
        /*  272 */     Item.Z = 0;
        /*  273 */     Item.extraData.setExtraData("0");
        /*  274 */     fuseObjects.add(Item);
        /*      */
        /*  276 */     Item = new GamefuseObject();
        /*  277 */     Item.baseItem = BaseItem.snst_block1;
        /*  278 */     Item.baseItem.allowWalk = false;
        /*  279 */     Item.baseItem.Height = 1.0F;
        /*  280 */     Item.itemId = 17;
        /*  281 */     Item.X = 5;
        /*  282 */     Item.Y = 18;
        /*  283 */     Item.Rot = 0;
        /*  284 */     Item.Z = 0;
        /*  285 */     Item.extraData.setExtraData("0");
        /*  286 */     fuseObjects.add(Item);
        /*      */
        /*  288 */     Item = new GamefuseObject();
        /*  289 */     Item.baseItem = BaseItem.snst_block1;
        /*  290 */     Item.baseItem.allowWalk = false;
        /*  291 */     Item.baseItem.Height = 1.0F;
        /*  292 */     Item.itemId = 18;
        /*  293 */     Item.X = 4;
        /*  294 */     Item.Y = 18;
        /*  295 */     Item.Rot = 0;
        /*  296 */     Item.Z = 1440;
        /*  297 */     Item.extraData.setExtraData("0");
        /*  298 */     fuseObjects.add(Item);
        /*      */
        /*  300 */     Item = new GamefuseObject();
        /*  301 */     Item.baseItem = BaseItem.snst_block1;
        /*  302 */     Item.baseItem.allowWalk = false;
        /*  303 */     Item.baseItem.Height = 1.0F;
        /*  304 */     Item.itemId = 19;
        /*  305 */     Item.X = 39;
        /*  306 */     Item.Y = 22;
        /*  307 */     Item.Rot = 2;
        /*  308 */     Item.Z = 1440;
        /*  309 */     Item.extraData.setExtraData("0");
        /*  310 */     fuseObjects.add(Item);
        /*      */
        /*  312 */     Item = new GamefuseObject();
        /*  313 */     Item.baseItem = BaseItem.snst_block1;
        /*  314 */     Item.baseItem.allowWalk = false;
        /*  315 */     Item.baseItem.Height = 1.0F;
        /*  316 */     Item.itemId = 20;
        /*  317 */     Item.X = 39;
        /*  318 */     Item.Y = 23;
        /*  319 */     Item.Rot = 0;
        /*  320 */     Item.Z = 0;
        /*  321 */     Item.extraData.setExtraData("0");
        /*  322 */     fuseObjects.add(Item);
        /*      */
        /*  324 */     Item = new GamefuseObject();
        /*  325 */     Item.baseItem = BaseItem.snst_fence;
        /*  326 */     Item.baseItem.allowWalk = false;
        /*  327 */     Item.baseItem.Height = 0.0F;
        /*  328 */     Item.itemId = 21;
        /*  329 */     Item.X = 24;
        /*  330 */     Item.Y = 44;
        /*  331 */     Item.Rot = 0;
        /*  332 */     Item.Z = 0;
        /*  333 */     Item.extraData.setExtraData("0");
        /*  334 */     fuseObjects.add(Item);
        /*      */
        /*  336 */     Item = new GamefuseObject();
        /*  337 */     Item.baseItem = BaseItem.snst_tree1;
        /*  338 */     Item.baseItem.allowWalk = false;
        /*  339 */     Item.baseItem.Height = 1.0F;
        /*  340 */     Item.itemId = 22;
        /*  341 */     Item.X = 36;
        /*  342 */     Item.Y = 15;
        /*  343 */     Item.Rot = 0;
        /*  344 */     Item.Z = 0;
        /*  345 */     Item.extraData.setExtraData("0");
        /*  346 */     fuseObjects.add(Item);
        /*      */
        /*  348 */     Item = new GamefuseObject();
        /*  349 */     Item.baseItem = BaseItem.snst_block1;
        /*  350 */     Item.baseItem.allowWalk = false;
        /*  351 */     Item.baseItem.Height = 1.0F;
        /*  352 */     Item.itemId = 23;
        /*  353 */     Item.X = 23;
        /*  354 */     Item.Y = 44;
        /*  355 */     Item.Rot = 0;
        /*  356 */     Item.Z = 0;
        /*  357 */     Item.extraData.setExtraData("0");
        /*  358 */     fuseObjects.add(Item);
        /*      */
        /*  360 */     Item = new GamefuseObject();
        /*  361 */     Item.baseItem = BaseItem.snst_tree1;
        /*  362 */     Item.baseItem.allowWalk = false;
        /*  363 */     Item.baseItem.Height = 1.0F;
        /*  364 */     Item.itemId = 24;
        /*  365 */     Item.X = 47;
        /*  366 */     Item.Y = 32;
        /*  367 */     Item.Rot = 0;
        /*  368 */     Item.Z = 0;
        /*  369 */     Item.extraData.setExtraData("0");
        /*  370 */     fuseObjects.add(Item);
        /*      */
        /*  372 */     Item = new GamefuseObject();
        /*  373 */     Item.baseItem = BaseItem.snst_block1;
        /*  374 */     Item.baseItem.allowWalk = false;
        /*  375 */     Item.baseItem.Height = 1.0F;
        /*  376 */     Item.itemId = 25;
        /*  377 */     Item.X = 39;
        /*  378 */     Item.Y = 37;
        /*  379 */     Item.Rot = 0;
        /*  380 */     Item.Z = 0;
        /*  381 */     Item.extraData.setExtraData("0");
        /*  382 */     fuseObjects.add(Item);
        /*      */
        /*  384 */     Item = new GamefuseObject();
        /*  385 */     Item.baseItem = BaseItem.snst_block1;
        /*  386 */     Item.baseItem.allowWalk = false;
        /*  387 */     Item.baseItem.Height = 1.0F;
        /*  388 */     Item.itemId = 26;
        /*  389 */     Item.X = 8;
        /*  390 */     Item.Y = 18;
        /*  391 */     Item.Rot = 0;
        /*  392 */     Item.Z = 0;
        /*  393 */     Item.extraData.setExtraData("0");
        /*  394 */     fuseObjects.add(Item);
        /*      */
        /*  396 */     Item = new GamefuseObject();
        /*  397 */     Item.baseItem = BaseItem.snst_block1;
        /*  398 */     Item.baseItem.allowWalk = false;
        /*  399 */     Item.baseItem.Height = 1.0F;
        /*  400 */     Item.itemId = 27;
        /*  401 */     Item.X = 2;
        /*  402 */     Item.Y = 19;
        /*  403 */     Item.Rot = 0;
        /*  404 */     Item.Z = 1440;
        /*  405 */     Item.extraData.setExtraData("0");
        /*  406 */     fuseObjects.add(Item);
        /*      */
        /*  408 */     Item = new GamefuseObject();
        /*  409 */     Item.baseItem = BaseItem.snst_tree1;
        /*  410 */     Item.baseItem.allowWalk = false;
        /*  411 */     Item.baseItem.Height = 1.0F;
        /*  412 */     Item.itemId = 28;
        /*  413 */     Item.X = 6;
        /*  414 */     Item.Y = 20;
        /*  415 */     Item.Rot = 0;
        /*  416 */     Item.Z = 0;
        /*  417 */     Item.extraData.setExtraData("0");
        /*  418 */     fuseObjects.add(Item);
        /*      */
        /*  420 */     Item = new GamefuseObject();
        /*  421 */     Item.baseItem = BaseItem.snst_block1;
        /*  422 */     Item.baseItem.allowWalk = false;
        /*  423 */     Item.baseItem.Height = 1.0F;
        /*  424 */     Item.itemId = 29;
        /*  425 */     Item.X = 2;
        /*  426 */     Item.Y = 18;
        /*  427 */     Item.Rot = 0;
        /*  428 */     Item.Z = 1440;
        /*  429 */     Item.extraData.setExtraData("0");
        /*  430 */     fuseObjects.add(Item);
        /*      */
        /*  432 */     Item = new GamefuseObject();
        /*  433 */     Item.baseItem = BaseItem.snst_fence;
        /*  434 */     Item.baseItem.allowWalk = false;
        /*  435 */     Item.baseItem.Height = 0.0F;
        /*  436 */     Item.itemId = 30;
        /*  437 */     Item.X = 15;
        /*  438 */     Item.Y = 14;
        /*  439 */     Item.Rot = 2;
        /*  440 */     Item.Z = 0;
        /*  441 */     Item.extraData.setExtraData("0");
        /*  442 */     fuseObjects.add(Item);
        /*      */
        /*  444 */     Item = new GamefuseObject();
        /*  445 */     Item.baseItem = BaseItem.snst_tree1;
        /*  446 */     Item.baseItem.allowWalk = false;
        /*  447 */     Item.baseItem.Height = 1.0F;
        /*  448 */     Item.itemId = 31;
        /*  449 */     Item.X = 26;
        /*  450 */     Item.Y = 6;
        /*  451 */     Item.Rot = 0;
        /*  452 */     Item.Z = 0;
        /*  453 */     Item.extraData.setExtraData("0");
        /*  454 */     fuseObjects.add(Item);
        /*      */
        /*  456 */     Item = new GamefuseObject();
        /*  457 */     Item.baseItem = BaseItem.snst_block1;
        /*  458 */     Item.baseItem.allowWalk = false;
        /*  459 */     Item.baseItem.Height = 1.0F;
        /*  460 */     Item.itemId = 32;
        /*  461 */     Item.X = 39;
        /*  462 */     Item.Y = 23;
        /*  463 */     Item.Rot = 4;
        /*  464 */     Item.Z = 1440;
        /*  465 */     Item.extraData.setExtraData("0");
        /*  466 */     fuseObjects.add(Item);
        /*      */
        /*  468 */     Item = new GamefuseObject();
        /*  469 */     Item.baseItem = BaseItem.snst_block1;
        /*  470 */     Item.baseItem.allowWalk = false;
        /*  471 */     Item.baseItem.Height = 1.0F;
        /*  472 */     Item.itemId = 33;
        /*  473 */     Item.X = 23;
        /*  474 */     Item.Y = 38;
        /*  475 */     Item.Rot = 2;
        /*  476 */     Item.Z = 1440;
        /*  477 */     Item.extraData.setExtraData("0");
        /*  478 */     fuseObjects.add(Item);
        /*      */
        /*  480 */     Item = new GamefuseObject();
        /*  481 */     Item.baseItem = BaseItem.snst_tree1;
        /*  482 */     Item.baseItem.allowWalk = false;
        /*  483 */     Item.baseItem.Height = 1.0F;
        /*  484 */     Item.itemId = 34;
        /*  485 */     Item.X = 10;
        /*  486 */     Item.Y = 26;
        /*  487 */     Item.Rot = 0;
        /*  488 */     Item.Z = 0;
        /*  489 */     Item.extraData.setExtraData("0");
        /*  490 */     fuseObjects.add(Item);
        /*      */
        /*  492 */     Item = new GamefuseObject();
        /*  493 */     Item.baseItem = BaseItem.snst_block1;
        /*  494 */     Item.baseItem.allowWalk = false;
        /*  495 */     Item.baseItem.Height = 1.0F;
        /*  496 */     Item.itemId = 35;
        /*  497 */     Item.X = 23;
        /*  498 */     Item.Y = 45;
        /*  499 */     Item.Rot = 4;
        /*  500 */     Item.Z = 0;
        /*  501 */     Item.extraData.setExtraData("0");
        /*  502 */     fuseObjects.add(Item);
        /*      */
        /*  504 */     Item = new GamefuseObject();
        /*  505 */     Item.baseItem = BaseItem.snst_block1;
        /*  506 */     Item.baseItem.allowWalk = false;
        /*  507 */     Item.baseItem.Height = 1.0F;
        /*  508 */     Item.itemId = 36;
        /*  509 */     Item.X = 39;
        /*  510 */     Item.Y = 21;
        /*  511 */     Item.Rot = 0;
        /*  512 */     Item.Z = 1440;
        /*  513 */     Item.extraData.setExtraData("0");
        /*  514 */     fuseObjects.add(Item);
        /*      */
        /*  516 */     Item = new GamefuseObject();
        /*  517 */     Item.baseItem = BaseItem.snst_block1;
        /*  518 */     Item.baseItem.allowWalk = false;
        /*  519 */     Item.baseItem.Height = 1.0F;
        /*  520 */     Item.itemId = 37;
        /*  521 */     Item.X = 5;
        /*  522 */     Item.Y = 18;
        /*  523 */     Item.Rot = 0;
        /*  524 */     Item.Z = 1440;
        /*  525 */     Item.extraData.setExtraData("0");
        /*  526 */     fuseObjects.add(Item);
        /*      */
        /*  528 */     Item = new GamefuseObject();
        /*  529 */     Item.baseItem = BaseItem.snst_block1;
        /*  530 */     Item.baseItem.allowWalk = false;
        /*  531 */     Item.baseItem.Height = 1.0F;
        /*  532 */     Item.itemId = 38;
        /*  533 */     Item.X = 39;
        /*  534 */     Item.Y = 24;
        /*  535 */     Item.Rot = 6;
        /*  536 */     Item.Z = 1440;
        /*  537 */     Item.extraData.setExtraData("0");
        /*  538 */     fuseObjects.add(Item);
        /*      */
        /*  540 */     Item = new GamefuseObject();
        /*  541 */     Item.baseItem = BaseItem.snst_tree1;
        /*  542 */     Item.baseItem.allowWalk = false;
        /*  543 */     Item.baseItem.Height = 1.0F;
        /*  544 */     Item.itemId = 39;
        /*  545 */     Item.X = 13;
        /*  546 */     Item.Y = 15;
        /*  547 */     Item.Rot = 0;
        /*  548 */     Item.Z = 0;
        /*  549 */     Item.extraData.setExtraData("0");
        /*  550 */     fuseObjects.add(Item);
        /*      */
        /*  552 */     Item = new GamefuseObject();
        /*  553 */     Item.baseItem = BaseItem.snst_block1;
        /*  554 */     Item.baseItem.allowWalk = false;
        /*  555 */     Item.baseItem.Height = 1.0F;
        /*  556 */     Item.itemId = 40;
        /*  557 */     Item.X = 3;
        /*  558 */     Item.Y = 18;
        /*  559 */     Item.Rot = 0;
        /*  560 */     Item.Z = 1440;
        /*  561 */     Item.extraData.setExtraData("0");
        /*  562 */     fuseObjects.add(Item);
        /*      */
        /*  564 */     Item = new GamefuseObject();
        /*  565 */     Item.baseItem = BaseItem.snst_fence;
        /*  566 */     Item.baseItem.allowWalk = false;
        /*  567 */     Item.baseItem.Height = 0.0F;
        /*  568 */     Item.itemId = 41;
        /*  569 */     Item.X = 13;
        /*  570 */     Item.Y = 14;
        /*  571 */     Item.Rot = 2;
        /*  572 */     Item.Z = 0;
        /*  573 */     Item.extraData.setExtraData("1");
        /*  574 */     fuseObjects.add(Item);
        /*      */
        /*  576 */     Item = new GamefuseObject();
        /*  577 */     Item.baseItem = BaseItem.snst_tree1;
        /*  578 */     Item.baseItem.allowWalk = false;
        /*  579 */     Item.baseItem.Height = 1.0F;
        /*  580 */     Item.itemId = 42;
        /*  581 */     Item.X = 30;
        /*  582 */     Item.Y = 7;
        /*  583 */     Item.Rot = 0;
        /*  584 */     Item.Z = 0;
        /*  585 */     Item.extraData.setExtraData("0");
        /*  586 */     fuseObjects.add(Item);
        /*      */
        /*  588 */     Item = new GamefuseObject();
        /*  589 */     Item.baseItem = BaseItem.snst_block1;
        /*  590 */     Item.baseItem.allowWalk = false;
        /*  591 */     Item.baseItem.Height = 1.0F;
        /*  592 */     Item.itemId = 43;
        /*  593 */     Item.X = 40;
        /*  594 */     Item.Y = 20;
        /*  595 */     Item.Rot = 0;
        /*  596 */     Item.Z = 0;
        /*  597 */     Item.extraData.setExtraData("0");
        /*  598 */     fuseObjects.add(Item);
        /*      */
        /*  600 */     Item = new GamefuseObject();
        /*  601 */     Item.baseItem = BaseItem.snst_fence;
        /*  602 */     Item.baseItem.allowWalk = false;
        /*  603 */     Item.baseItem.Height = 0.0F;
        /*  604 */     Item.itemId = 44;
        /*  605 */     Item.X = 29;
        /*  606 */     Item.Y = 7;
        /*  607 */     Item.Rot = 0;
        /*  608 */     Item.Z = 0;
        /*  609 */     Item.extraData.setExtraData("1");
        /*  610 */     fuseObjects.add(Item);
        /*      */
        /*  612 */     Item = new GamefuseObject();
        /*  613 */     Item.baseItem = BaseItem.snst_fence;
        /*  614 */     Item.baseItem.allowWalk = false;
        /*  615 */     Item.baseItem.Height = 0.0F;
        /*  616 */     Item.itemId = 45;
        /*  617 */     Item.X = 21;
        /*  618 */     Item.Y = 14;
        /*  619 */     Item.Rot = 2;
        /*  620 */     Item.Z = 0;
        /*  621 */     Item.extraData.setExtraData("2");
        /*  622 */     fuseObjects.add(Item);
        /*      */
        /*  624 */     Item = new GamefuseObject();
        /*  625 */     Item.baseItem = BaseItem.snst_fence;
        /*  626 */     Item.baseItem.allowWalk = false;
        /*  627 */     Item.baseItem.Height = 0.0F;
        /*  628 */     Item.itemId = 46;
        /*  629 */     Item.X = 24;
        /*  630 */     Item.Y = 40;
        /*  631 */     Item.Rot = 0;
        /*  632 */     Item.Z = 0;
        /*  633 */     Item.extraData.setExtraData("0");
        /*  634 */     fuseObjects.add(Item);
        /*      */
        /*  636 */     Item = new GamefuseObject();
        /*  637 */     Item.baseItem = BaseItem.snst_block1;
        /*  638 */     Item.baseItem.allowWalk = false;
        /*  639 */     Item.baseItem.Height = 1.0F;
        /*  640 */     Item.itemId = 47;
        /*  641 */     Item.X = 23;
        /*  642 */     Item.Y = 40;
        /*  643 */     Item.Rot = 0;
        /*  644 */     Item.Z = 1440;
        /*  645 */     Item.extraData.setExtraData("0");
        /*  646 */     fuseObjects.add(Item);
        /*      */
        /*  648 */     Item = new GamefuseObject();
        /*  649 */     Item.baseItem = BaseItem.snst_tree1;
        /*  650 */     Item.baseItem.allowWalk = false;
        /*  651 */     Item.baseItem.Height = 1.0F;
        /*  652 */     Item.itemId = 48;
        /*  653 */     Item.X = 15;
        /*  654 */     Item.Y = 10;
        /*  655 */     Item.Rot = 0;
        /*  656 */     Item.Z = 0;
        /*  657 */     Item.extraData.setExtraData("0");
        /*  658 */     fuseObjects.add(Item);
        /*      */
        /*  660 */     Item = new GamefuseObject();
        /*  661 */     Item.baseItem = BaseItem.snst_block1;
        /*  662 */     Item.baseItem.allowWalk = false;
        /*  663 */     Item.baseItem.Height = 1.0F;
        /*  664 */     Item.itemId = 49;
        /*  665 */     Item.X = 37;
        /*  666 */     Item.Y = 37;
        /*  667 */     Item.Rot = 0;
        /*  668 */     Item.Z = 0;
        /*  669 */     Item.extraData.setExtraData("0");
        /*  670 */     fuseObjects.add(Item);
        /*      */
        /*  672 */     Item = new GamefuseObject();
        /*  673 */     Item.baseItem = BaseItem.snst_tree1;
        /*  674 */     Item.baseItem.allowWalk = false;
        /*  675 */     Item.baseItem.Height = 1.0F;
        /*  676 */     Item.itemId = 50;
        /*  677 */     Item.X = 20;
        /*  678 */     Item.Y = 4;
        /*  679 */     Item.Rot = 0;
        /*  680 */     Item.Z = 0;
        /*  681 */     Item.extraData.setExtraData("0");
        /*  682 */     fuseObjects.add(Item);
        /*      */
        /*  684 */     Item = new GamefuseObject();
        /*  685 */     Item.baseItem = BaseItem.snst_block1;
        /*  686 */     Item.baseItem.allowWalk = false;
        /*  687 */     Item.baseItem.Height = 1.0F;
        /*  688 */     Item.itemId = 51;
        /*  689 */     Item.X = 3;
        /*  690 */     Item.Y = 18;
        /*  691 */     Item.Rot = 0;
        /*  692 */     Item.Z = 0;
        /*  693 */     Item.extraData.setExtraData("0");
        /*  694 */     fuseObjects.add(Item);
        /*      */
        /*  696 */     Item = new GamefuseObject();
        /*  697 */     Item.baseItem = BaseItem.snst_block1;
        /*  698 */     Item.baseItem.allowWalk = false;
        /*  699 */     Item.baseItem.Height = 1.0F;
        /*  700 */     Item.itemId = 52;
        /*  701 */     Item.X = 23;
        /*  702 */     Item.Y = 40;
        /*  703 */     Item.Rot = 0;
        /*  704 */     Item.Z = 0;
        /*  705 */     Item.extraData.setExtraData("0");
        /*  706 */     fuseObjects.add(Item);
        /*      */
        /*  708 */     Item = new GamefuseObject();
        /*  709 */     Item.baseItem = BaseItem.snst_block1;
        /*  710 */     Item.baseItem.allowWalk = false;
        /*  711 */     Item.baseItem.Height = 1.0F;
        /*  712 */     Item.itemId = 53;
        /*  713 */     Item.X = 2;
        /*  714 */     Item.Y = 22;
        /*  715 */     Item.Rot = 2;
        /*  716 */     Item.Z = 1440;
        /*  717 */     Item.extraData.setExtraData("0");
        /*  718 */     fuseObjects.add(Item);
        /*      */
        /*  720 */     Item = new GamefuseObject();
        /*  721 */     Item.baseItem = BaseItem.snst_block1;
        /*  722 */     Item.baseItem.allowWalk = false;
        /*  723 */     Item.baseItem.Height = 1.0F;
        /*  724 */     Item.itemId = 54;
        /*  725 */     Item.X = 43;
        /*  726 */     Item.Y = 20;
        /*  727 */     Item.Rot = 6;
        /*  728 */     Item.Z = 1440;
        /*  729 */     Item.extraData.setExtraData("0");
        /*  730 */     fuseObjects.add(Item);
        /*      */
        /*  732 */     Item = new GamefuseObject();
        /*  733 */     Item.baseItem = BaseItem.snst_block1;
        /*  734 */     Item.baseItem.allowWalk = false;
        /*  735 */     Item.baseItem.Height = 1.0F;
        /*  736 */     Item.itemId = 55;
        /*  737 */     Item.X = 23;
        /*  738 */     Item.Y = 39;
        /*  739 */     Item.Rot = 0;
        /*  740 */     Item.Z = 0;
        /*  741 */     Item.extraData.setExtraData("0");
        /*  742 */     fuseObjects.add(Item);
        /*      */
        /*  744 */     Item = new GamefuseObject();
        /*  745 */     Item.baseItem = BaseItem.snst_tree1;
        /*  746 */     Item.baseItem.allowWalk = false;
        /*  747 */     Item.baseItem.Height = 1.0F;
        /*  748 */     Item.itemId = 56;
        /*  749 */     Item.X = 45;
        /*  750 */     Item.Y = 25;
        /*  751 */     Item.Rot = 0;
        /*  752 */     Item.Z = 0;
        /*  753 */     Item.extraData.setExtraData("0");
        /*  754 */     fuseObjects.add(Item);
        /*      */
        /*  756 */     Item = new GamefuseObject();
        /*  757 */     Item.baseItem = BaseItem.snst_block1;
        /*  758 */     Item.baseItem.allowWalk = false;
        /*  759 */     Item.baseItem.Height = 1.0F;
        /*  760 */     Item.itemId = 57;
        /*  761 */     Item.X = 23;
        /*  762 */     Item.Y = 42;
        /*  763 */     Item.Rot = 0;
        /*  764 */     Item.Z = 0;
        /*  765 */     Item.extraData.setExtraData("0");
        /*  766 */     fuseObjects.add(Item);
        /*      */
        /*  768 */     Item = new GamefuseObject();
        /*  769 */     Item.baseItem = BaseItem.snst_block1;
        /*  770 */     Item.baseItem.allowWalk = false;
        /*  771 */     Item.baseItem.Height = 1.0F;
        /*  772 */     Item.itemId = 58;
        /*  773 */     Item.X = 42;
        /*  774 */     Item.Y = 20;
        /*  775 */     Item.Rot = 0;
        /*  776 */     Item.Z = 0;
        /*  777 */     Item.extraData.setExtraData("0");
        /*  778 */     fuseObjects.add(Item);
        /*      */
        /*  780 */     Item = new GamefuseObject();
        /*  781 */     Item.baseItem = BaseItem.snst_block1;
        /*  782 */     Item.baseItem.allowWalk = false;
        /*  783 */     Item.baseItem.Height = 1.0F;
        /*  784 */     Item.itemId = 59;
        /*  785 */     Item.X = 9;
        /*  786 */     Item.Y = 18;
        /*  787 */     Item.Rot = 0;
        /*  788 */     Item.Z = 1440;
        /*  789 */     Item.extraData.setExtraData("0");
        /*  790 */     fuseObjects.add(Item);
        /*      */
        /*  792 */     Item = new GamefuseObject();
        /*  793 */     Item.baseItem = BaseItem.snst_block1;
        /*  794 */     Item.baseItem.allowWalk = false;
        /*  795 */     Item.baseItem.Height = 1.0F;
        /*  796 */     Item.itemId = 60;
        /*  797 */     Item.X = 10;
        /*  798 */     Item.Y = 18;
        /*  799 */     Item.Rot = 0;
        /*  800 */     Item.Z = 0;
        /*  801 */     Item.extraData.setExtraData("0");
        /*  802 */     fuseObjects.add(Item);
        /*      */
        /*  804 */     Item = new GamefuseObject();
        /*  805 */     Item.baseItem = BaseItem.snst_fence;
        /*  806 */     Item.baseItem.allowWalk = false;
        /*  807 */     Item.baseItem.Height = 0.0F;
        /*  808 */     Item.itemId = 61;
        /*  809 */     Item.X = 24;
        /*  810 */     Item.Y = 38;
        /*  811 */     Item.Rot = 0;
        /*  812 */     Item.Z = 0;
        /*  813 */     Item.extraData.setExtraData("1");
        /*  814 */     fuseObjects.add(Item);
        /*      */
        /*  816 */     Item = new GamefuseObject();
        /*  817 */     Item.baseItem = BaseItem.snst_block1;
        /*  818 */     Item.baseItem.allowWalk = false;
        /*  819 */     Item.baseItem.Height = 1.0F;
        /*  820 */     Item.itemId = 62;
        /*  821 */     Item.X = 11;
        /*  822 */     Item.Y = 18;
        /*  823 */     Item.Rot = 0;
        /*  824 */     Item.Z = 0;
        /*  825 */     Item.extraData.setExtraData("0");
        /*  826 */     fuseObjects.add(Item);
        /*      */
        /*  828 */     Item = new GamefuseObject();
        /*  829 */     Item.baseItem = BaseItem.snst_block1;
        /*  830 */     Item.baseItem.allowWalk = false;
        /*  831 */     Item.baseItem.Height = 1.0F;
        /*  832 */     Item.itemId = 63;
        /*  833 */     Item.X = 39;
        /*  834 */     Item.Y = 37;
        /*  835 */     Item.Rot = 0;
        /*  836 */     Item.Z = 1440;
        /*  837 */     Item.extraData.setExtraData("0");
        /*  838 */     fuseObjects.add(Item);
        /*      */
        /*  840 */     Item = new GamefuseObject();
        /*  841 */     Item.baseItem = BaseItem.snst_block1;
        /*  842 */     Item.baseItem.allowWalk = false;
        /*  843 */     Item.baseItem.Height = 1.0F;
        /*  844 */     Item.itemId = 64;
        /*  845 */     Item.X = 8;
        /*  846 */     Item.Y = 18;
        /*  847 */     Item.Rot = 0;
        /*  848 */     Item.Z = 1440;
        /*  849 */     Item.extraData.setExtraData("0");
        /*  850 */     fuseObjects.add(Item);
        /*      */
        /*  852 */     Item = new GamefuseObject();
        /*  853 */     Item.baseItem = BaseItem.snst_block1;
        /*  854 */     Item.baseItem.allowWalk = false;
        /*  855 */     Item.baseItem.Height = 1.0F;
        /*  856 */     Item.itemId = 65;
        /*  857 */     Item.X = 39;
        /*  858 */     Item.Y = 20;
        /*  859 */     Item.Rot = 0;
        /*  860 */     Item.Z = 0;
        /*  861 */     Item.extraData.setExtraData("0");
        /*  862 */     fuseObjects.add(Item);
        /*      */
        /*  864 */     Item = new GamefuseObject();
        /*  865 */     Item.baseItem = BaseItem.snst_block1;
        /*  866 */     Item.baseItem.allowWalk = false;
        /*  867 */     Item.baseItem.Height = 1.0F;
        /*  868 */     Item.itemId = 66;
        /*  869 */     Item.X = 38;
        /*  870 */     Item.Y = 37;
        /*  871 */     Item.Rot = 0;
        /*  872 */     Item.Z = 0;
        /*  873 */     Item.extraData.setExtraData("0");
        /*  874 */     fuseObjects.add(Item);
        /*      */
        /*  876 */     Item = new GamefuseObject();
        /*  877 */     Item.baseItem = BaseItem.snst_block1;
        /*  878 */     Item.baseItem.allowWalk = false;
        /*  879 */     Item.baseItem.Height = 1.0F;
        /*  880 */     Item.itemId = 67;
        /*  881 */     Item.X = 40;
        /*  882 */     Item.Y = 37;
        /*  883 */     Item.Rot = 0;
        /*  884 */     Item.Z = 1440;
        /*  885 */     Item.extraData.setExtraData("0");
        /*  886 */     fuseObjects.add(Item);
        /*      */
        /*  888 */     Item = new GamefuseObject();
        /*  889 */     Item.baseItem = BaseItem.snst_block1;
        /*  890 */     Item.baseItem.allowWalk = false;
        /*  891 */     Item.baseItem.Height = 1.0F;
        /*  892 */     Item.itemId = 68;
        /*  893 */     Item.X = 2;
        /*  894 */     Item.Y = 21;
        /*  895 */     Item.Rot = 0;
        /*  896 */     Item.Z = 0;
        /*  897 */     Item.extraData.setExtraData("0");
        /*  898 */     fuseObjects.add(Item);
        /*      */
        /*  900 */     Item = new GamefuseObject();
        /*  901 */     Item.baseItem = BaseItem.snst_fence;
        /*  902 */     Item.baseItem.allowWalk = false;
        /*  903 */     Item.baseItem.Height = 0.0F;
        /*  904 */     Item.itemId = 69;
        /*  905 */     Item.X = 24;
        /*  906 */     Item.Y = 42;
        /*  907 */     Item.Rot = 0;
        /*  908 */     Item.Z = 0;
        /*  909 */     Item.extraData.setExtraData("2");
        /*  910 */     fuseObjects.add(Item);
        /*      */
        /*  912 */     Item = new GamefuseObject();
        /*  913 */     Item.baseItem = BaseItem.snst_block1;
        /*  914 */     Item.baseItem.allowWalk = false;
        /*  915 */     Item.baseItem.Height = 1.0F;
        /*  916 */     Item.itemId = 70;
        /*  917 */     Item.X = 39;
        /*  918 */     Item.Y = 22;
        /*  919 */     Item.Rot = 0;
        /*  920 */     Item.Z = 0;
        /*  921 */     Item.extraData.setExtraData("0");
        /*  922 */     fuseObjects.add(Item);
        /*      */
        /*  924 */     Item = new GamefuseObject();
        /*  925 */     Item.baseItem = BaseItem.snst_block1;
        /*  926 */     Item.baseItem.allowWalk = false;
        /*  927 */     Item.baseItem.Height = 1.0F;
        /*  928 */     Item.itemId = 71;
        /*  929 */     Item.X = 38;
        /*  930 */     Item.Y = 37;
        /*  931 */     Item.Rot = 0;
        /*  932 */     Item.Z = 1440;
        /*  933 */     Item.extraData.setExtraData("0");
        /*  934 */     fuseObjects.add(Item);
        /*      */
        /*  936 */     Item = new GamefuseObject();
        /*  937 */     Item.baseItem = BaseItem.snst_block1;
        /*  938 */     Item.baseItem.allowWalk = false;
        /*  939 */     Item.baseItem.Height = 1.0F;
        /*  940 */     Item.itemId = 72;
        /*  941 */     Item.X = 40;
        /*  942 */     Item.Y = 20;
        /*  943 */     Item.Rot = 0;
        /*  944 */     Item.Z = 1440;
        /*  945 */     Item.extraData.setExtraData("0");
        /*  946 */     fuseObjects.add(Item);
        /*      */
        /*  948 */     Item = new GamefuseObject();
        /*  949 */     Item.baseItem = BaseItem.snst_tree1;
        /*  950 */     Item.baseItem.allowWalk = false;
        /*  951 */     Item.baseItem.Height = 1.0F;
        /*  952 */     Item.itemId = 73;
        /*  953 */     Item.X = 28;
        /*  954 */     Item.Y = 47;
        /*  955 */     Item.Rot = 0;
        /*  956 */     Item.Z = 0;
        /*  957 */     Item.extraData.setExtraData("0");
        /*  958 */     fuseObjects.add(Item);
        /*      */
        /*  960 */     Item = new GamefuseObject();
        /*  961 */     Item.baseItem = BaseItem.snst_block1;
        /*  962 */     Item.baseItem.allowWalk = false;
        /*  963 */     Item.baseItem.Height = 1.0F;
        /*  964 */     Item.itemId = 74;
        /*  965 */     Item.X = 6;
        /*  966 */     Item.Y = 18;
        /*  967 */     Item.Rot = 0;
        /*  968 */     Item.Z = 0;
        /*  969 */     Item.extraData.setExtraData("0");
        /*  970 */     fuseObjects.add(Item);
        /*      */
        /*  972 */     Item = new GamefuseObject();
        /*  973 */     Item.baseItem = BaseItem.s_snowball_machine;
        /*  974 */     Item.baseItem.allowWalk = false;
        /*  975 */     Item.baseItem.Height = 0.0F;
        /*  976 */     Item.itemId = 75;
        /*  977 */     Item.X = 26;
        /*  978 */     Item.Y = 24;
        /*  979 */     Item.Rot = 0;
        /*  980 */     Item.Z = 0;
        /*  981 */     Item.extraData.setExtraData("0");
        /*  982 */     fuseObjects.add(Item);
        /*      */
        /*  984 */     Item = new GamefuseObject();
        /*  985 */     Item.baseItem = BaseItem.snst_tree1;
        /*  986 */     Item.baseItem.allowWalk = false;
        /*  987 */     Item.baseItem.Height = 1.0F;
        /*  988 */     Item.itemId = 76;
        /*  989 */     Item.X = 5;
        /*  990 */     Item.Y = 24;
        /*  991 */     Item.Rot = 0;
        /*  992 */     Item.Z = 0;
        /*  993 */     Item.extraData.setExtraData("0");
        /*  994 */     fuseObjects.add(Item);
        /*      */
        /*  996 */     Item = new GamefuseObject();
        /*  997 */     Item.baseItem = BaseItem.snst_block1;
        /*  998 */     Item.baseItem.allowWalk = false;
        /*  999 */     Item.baseItem.Height = 1.0F;
        /* 1000 */     Item.itemId = 77;
        /* 1001 */     Item.X = 41;
        /* 1002 */     Item.Y = 20;
        /* 1003 */     Item.Rot = 4;
        /* 1004 */     Item.Z = 1440;
        /* 1005 */     Item.extraData.setExtraData("0");
        /* 1006 */     fuseObjects.add(Item);
        /*      */
        /* 1008 */     Item = new GamefuseObject();
        /* 1009 */     Item.baseItem = BaseItem.snst_fence;
        /* 1010 */     Item.baseItem.allowWalk = false;
        /* 1011 */     Item.baseItem.Height = 0.0F;
        /* 1012 */     Item.itemId = 78;
        /* 1013 */     Item.X = 19;
        /* 1014 */     Item.Y = 14;
        /* 1015 */     Item.Rot = 2;
        /* 1016 */     Item.Z = 0;
        /* 1017 */     Item.extraData.setExtraData("0");
        /* 1018 */     fuseObjects.add(Item);
        /*      */
        /* 1020 */     Item = new GamefuseObject();
        /* 1021 */     Item.baseItem = BaseItem.ads_background;
        /* 1022 */     Item.baseItem.allowWalk = true;
        /* 1023 */     Item.baseItem.Height = 0.0F;
        /* 1024 */     Item.baseItem.itemExtraType = 1;
        /* 1025 */     Item.itemId = 79;
        /* 1026 */     Item.X = 0;
        /* 1027 */     Item.Y = 19;
        /* 1028 */     Item.Rot = 1;
        /* 1029 */     Item.Z = 0;
        /* 1030 */     Item.extraData = new MapStuffData("state=0\toffsetX=-1166\toffsetZ=10000\toffsetY=1542\timageUrl=https://comet.habbia.in/swf/c_images/DEV_tests/snst_bg_1_a_big.png");
        /* 1031 */     fuseObjects.add(Item);
        /*      */
        /* 1033 */     Item = new GamefuseObject();
        /* 1034 */     Item.baseItem = BaseItem.snst_tree1;
        /* 1035 */     Item.baseItem.allowWalk = false;
        /* 1036 */     Item.baseItem.Height = 1.0F;
        /* 1037 */     Item.itemId = 80;
        /* 1038 */     Item.X = 20;
        /* 1039 */     Item.Y = 8;
        /* 1040 */     Item.Rot = 0;
        /* 1041 */     Item.Z = 0;
        /* 1042 */     Item.extraData.setExtraData("0");
        /* 1043 */     fuseObjects.add(Item);
        /*      */
        /* 1045 */     Item = new GamefuseObject();
        /* 1046 */     Item.baseItem = BaseItem.snst_block1;
        /* 1047 */     Item.baseItem.allowWalk = false;
        /* 1048 */     Item.baseItem.Height = 1.0F;
        /* 1049 */     Item.itemId = 81;
        /* 1050 */     Item.X = 2;
        /* 1051 */     Item.Y = 21;
        /* 1052 */     Item.Rot = 0;
        /* 1053 */     Item.Z = 1440;
        /* 1054 */     Item.extraData.setExtraData("0");
        /* 1055 */     fuseObjects.add(Item);
        /*      */
        /* 1057 */     Item = new GamefuseObject();
        /* 1058 */     Item.baseItem = BaseItem.snst_block1;
        /* 1059 */     Item.baseItem.allowWalk = false;
        /* 1060 */     Item.baseItem.Height = 1.0F;
        /* 1061 */     Item.itemId = 82;
        /* 1062 */     Item.X = 23;
        /* 1063 */     Item.Y = 38;
        /* 1064 */     Item.Rot = 0;
        /* 1065 */     Item.Z = 0;
        /* 1066 */     Item.extraData.setExtraData("0");
        /* 1067 */     fuseObjects.add(Item);
        /*      */
        /* 1069 */     Item = new GamefuseObject();
        /* 1070 */     Item.baseItem = BaseItem.snst_block1;
        /* 1071 */     Item.baseItem.allowWalk = false;
        /* 1072 */     Item.baseItem.Height = 1.0F;
        /* 1073 */     Item.itemId = 83;
        /* 1074 */     Item.X = 23;
        /* 1075 */     Item.Y = 42;
        /* 1076 */     Item.Rot = 0;
        /* 1077 */     Item.Z = 1440;
        /* 1078 */     Item.extraData.setExtraData("0");
        /* 1079 */     fuseObjects.add(Item);
        /*      */
        /* 1081 */     Item = new GamefuseObject();
        /* 1082 */     Item.baseItem = BaseItem.snst_block1;
        /* 1083 */     Item.baseItem.allowWalk = false;
        /* 1084 */     Item.baseItem.Height = 1.0F;
        /* 1085 */     Item.itemId = 84;
        /* 1086 */     Item.X = 42;
        /* 1087 */     Item.Y = 20;
        /* 1088 */     Item.Rot = 0;
        /* 1089 */     Item.Z = 1440;
        /* 1090 */     Item.extraData.setExtraData("0");
        /* 1091 */     fuseObjects.add(Item);
        /*      */
        /* 1093 */     Item = new GamefuseObject();
        /* 1094 */     Item.baseItem = BaseItem.snst_block1;
        /* 1095 */     Item.baseItem.allowWalk = false;
        /* 1096 */     Item.baseItem.Height = 1.0F;
        /* 1097 */     Item.itemId = 85;
        /* 1098 */     Item.X = 39;
        /* 1099 */     Item.Y = 21;
        /* 1100 */     Item.Rot = 0;
        /* 1101 */     Item.Z = 0;
        /* 1102 */     Item.extraData.setExtraData("0");
        /* 1103 */     fuseObjects.add(Item);
        /*      */
        /* 1105 */     Item = new GamefuseObject();
        /* 1106 */     Item.baseItem = BaseItem.snst_block1;
        /* 1107 */     Item.baseItem.allowWalk = false;
        /* 1108 */     Item.baseItem.Height = 1.0F;
        /* 1109 */     Item.itemId = 86;
        /* 1110 */     Item.X = 12;
        /* 1111 */     Item.Y = 18;
        /* 1112 */     Item.Rot = 4;
        /* 1113 */     Item.Z = 0;
        /* 1114 */     Item.extraData.setExtraData("0");
        /* 1115 */     fuseObjects.add(Item);
        /*      */
        /* 1117 */     Item = new GamefuseObject();
        /* 1118 */     Item.baseItem = BaseItem.snst_block1;
        /* 1119 */     Item.baseItem.allowWalk = false;
        /* 1120 */     Item.baseItem.Height = 1.0F;
        /* 1121 */     Item.itemId = 87;
        /* 1122 */     Item.X = 11;
        /* 1123 */     Item.Y = 18;
        /* 1124 */     Item.Rot = 0;
        /* 1125 */     Item.Z = 1440;
        /* 1126 */     Item.extraData.setExtraData("0");
        /* 1127 */     fuseObjects.add(Item);
        /*      */
        /* 1129 */     Item = new GamefuseObject();
        /* 1130 */     Item.baseItem = BaseItem.snst_block1;
        /* 1131 */     Item.baseItem.allowWalk = false;
        /* 1132 */     Item.baseItem.Height = 1.0F;
        /* 1133 */     Item.itemId = 88;
        /* 1134 */     Item.X = 40;
        /* 1135 */     Item.Y = 37;
        /* 1136 */     Item.Rot = 0;
        /* 1137 */     Item.Z = 0;
        /* 1138 */     Item.extraData.setExtraData("0");
        /* 1139 */     fuseObjects.add(Item);
        /*      */
        /* 1141 */     Item = new GamefuseObject();
        /* 1142 */     Item.baseItem = BaseItem.snst_block1;
        /* 1143 */     Item.baseItem.allowWalk = false;
        /* 1144 */     Item.baseItem.Height = 1.0F;
        /* 1145 */     Item.itemId = 89;
        /* 1146 */     Item.X = 23;
        /* 1147 */     Item.Y = 41;
        /* 1148 */     Item.Rot = 0;
        /* 1149 */     Item.Z = 0;
        /* 1150 */     Item.extraData.setExtraData("0");
        /* 1151 */     fuseObjects.add(Item);
        /*      */
        /* 1153 */     Item = new GamefuseObject();
        /* 1154 */     Item.baseItem = BaseItem.snst_block1;
        /* 1155 */     Item.baseItem.allowWalk = false;
        /* 1156 */     Item.baseItem.Height = 1.0F;
        /* 1157 */     Item.itemId = 90;
        /* 1158 */     Item.X = 43;
        /* 1159 */     Item.Y = 20;
        /* 1160 */     Item.Rot = 2;
        /* 1161 */     Item.Z = 0;
        /* 1162 */     Item.extraData.setExtraData("0");
        /* 1163 */     fuseObjects.add(Item);
        /*      */
        /* 1165 */     Item = new GamefuseObject();
        /* 1166 */     Item.baseItem = BaseItem.snst_block1;
        /* 1167 */     Item.baseItem.allowWalk = false;
        /* 1168 */     Item.baseItem.Height = 1.0F;
        /* 1169 */     Item.itemId = 91;
        /* 1170 */     Item.X = 39;
        /* 1171 */     Item.Y = 20;
        /* 1172 */     Item.Rot = 0;
        /* 1173 */     Item.Z = 1440;
        /* 1174 */     Item.extraData.setExtraData("0");
        /* 1175 */     fuseObjects.add(Item);
        /*      */
        /* 1177 */     Item = new GamefuseObject();
        /* 1178 */     Item.baseItem = BaseItem.snst_tree1;
        /* 1179 */     Item.baseItem.allowWalk = false;
        /* 1180 */     Item.baseItem.Height = 1.0F;
        /* 1181 */     Item.itemId = 92;
        /* 1182 */     Item.X = 15;
        /* 1183 */     Item.Y = 34;
        /* 1184 */     Item.Rot = 0;
        /* 1185 */     Item.Z = 0;
        /* 1186 */     Item.extraData.setExtraData("0");
        /* 1187 */     fuseObjects.add(Item);
        /*      */
        /* 1189 */     Item = new GamefuseObject();
        /* 1190 */     Item.baseItem = BaseItem.snst_block1;
        /* 1191 */     Item.baseItem.allowWalk = false;
        /* 1192 */     Item.baseItem.Height = 1.0F;
        /* 1193 */     Item.itemId = 93;
        /* 1194 */     Item.X = 6;
        /* 1195 */     Item.Y = 18;
        /* 1196 */     Item.Rot = 0;
        /* 1197 */     Item.Z = 1440;
        /* 1198 */     Item.extraData.setExtraData("0");
        /* 1199 */     fuseObjects.add(Item);
        /*      */
        /* 1201 */     Item = new GamefuseObject();
        /* 1202 */     Item.baseItem = BaseItem.snst_block1;
        /* 1203 */     Item.baseItem.allowWalk = false;
        /* 1204 */     Item.baseItem.Height = 1.0F;
        /* 1205 */     Item.itemId = 94;
        /* 1206 */     Item.X = 7;
        /* 1207 */     Item.Y = 18;
        /* 1208 */     Item.Rot = 0;
        /* 1209 */     Item.Z = 1440;
        /* 1210 */     Item.extraData.setExtraData("0");
        /* 1211 */     fuseObjects.add(Item);
        /*      */
        /* 1213 */     Item = new GamefuseObject();
        /* 1214 */     Item.baseItem = BaseItem.snst_fence;
        /* 1215 */     Item.baseItem.allowWalk = false;
        /* 1216 */     Item.baseItem.Height = 0.0F;
        /* 1217 */     Item.itemId = 95;
        /* 1218 */     Item.X = 29;
        /* 1219 */     Item.Y = 9;
        /* 1220 */     Item.Rot = 0;
        /* 1221 */     Item.Z = 0;
        /* 1222 */     Item.extraData.setExtraData("2");
        /* 1223 */     fuseObjects.add(Item);
        /*      */
        /* 1225 */     Item = new GamefuseObject();
        /* 1226 */     Item.baseItem = BaseItem.snst_block1;
        /* 1227 */     Item.baseItem.allowWalk = false;
        /* 1228 */     Item.baseItem.Height = 1.0F;
        /* 1229 */     Item.itemId = 96;
        /* 1230 */     Item.X = 39;
        /* 1231 */     Item.Y = 24;
        /* 1232 */     Item.Rot = 2;
        /* 1233 */     Item.Z = 0;
        /* 1234 */     Item.extraData.setExtraData("0");
        /* 1235 */     fuseObjects.add(Item);
        /*      */
        /* 1237 */     Item = new GamefuseObject();
        /* 1238 */     Item.baseItem = BaseItem.snst_block1;
        /* 1239 */     Item.baseItem.allowWalk = false;
        /* 1240 */     Item.baseItem.Height = 1.0F;
        /* 1241 */     Item.itemId = 97;
        /* 1242 */     Item.X = 23;
        /* 1243 */     Item.Y = 43;
        /* 1244 */     Item.Rot = 0;
        /* 1245 */     Item.Z = 0;
        /* 1246 */     Item.extraData.setExtraData("0");
        /* 1247 */     fuseObjects.add(Item);
        /*      */
        /*      */
        /* 1250 */     Item = new GamefuseObject();
        /* 1251 */     Item.baseItem = BaseItem.ads_igorraygun;
        /* 1252 */     Item.baseItem.allowWalk = false;
        /* 1253 */     Item.baseItem.Height = 0.1F;
        /* 1254 */     Item.itemId = 98;
        /* 1255 */     Item.X = 28;
        /* 1256 */     Item.Y = 12;
        /* 1257 */     Item.Rot = 4;
        /* 1258 */     Item.Z = 0;
        /* 1259 */     Item.extraData.setExtraData("0");
        /* 1260 */     fuseObjects.add(Item);
        /*      */
        /* 1262 */     Item = new GamefuseObject();
        /* 1263 */     Item.baseItem = BaseItem.ads_igorraygun;
        /* 1264 */     Item.baseItem.allowWalk = false;
        /* 1265 */     Item.baseItem.Height = 0.1F;
        /* 1266 */     Item.itemId = 99;
        /* 1267 */     Item.X = 41;
        /* 1268 */     Item.Y = 33;
        /* 1269 */     Item.Rot = 6;
        /* 1270 */     Item.Z = 0;
        /* 1271 */     Item.extraData.setExtraData("0");
        /* 1272 */     fuseObjects.add(Item);
        /*      */
        /* 1274 */     Item = new GamefuseObject();
        /* 1275 */     Item.baseItem = BaseItem.ads_igorraygun;
        /* 1276 */     Item.baseItem.allowWalk = false;
        /* 1277 */     Item.baseItem.Height = 0.1F;
        /* 1278 */     Item.itemId = 100;
        /* 1279 */     Item.X = 31;
        /* 1280 */     Item.Y = 41;
        /* 1281 */     Item.Rot = 0;
        /* 1282 */     Item.Z = 0;
        /* 1283 */     Item.extraData.setExtraData("0");
        /* 1284 */     fuseObjects.add(Item);
        /*      */
        /* 1286 */     Item = new GamefuseObject();
        /* 1287 */     Item.baseItem = BaseItem.ads_igorraygun;
        /* 1288 */     Item.baseItem.allowWalk = false;
        /* 1289 */     Item.baseItem.Height = 0.1F;
        /* 1290 */     Item.itemId = 101;
        /* 1291 */     Item.X = 17;
        /* 1292 */     Item.Y = 37;
        /* 1293 */     Item.Rot = 2;
        /* 1294 */     Item.Z = 0;
        /* 1295 */     Item.extraData.setExtraData("0");
        /* 1296 */     fuseObjects.add(Item);
        /*      */
        /* 1298 */     Item = new GamefuseObject();
        /* 1299 */     Item.baseItem = BaseItem.ads_igorraygun;
        /* 1300 */     Item.baseItem.allowWalk = false;
        /* 1301 */     Item.baseItem.Height = 0.1F;
        /* 1302 */     Item.itemId = 102;
        /* 1303 */     Item.X = 11;
        /* 1304 */     Item.Y = 21;
        /* 1305 */     Item.Rot = 2;
        /* 1306 */     Item.Z = 0;
        /* 1307 */     Item.extraData.setExtraData("0");
        /* 1308 */     fuseObjects.add(Item);
        /*      */
        /*      */
        /* 1311 */     spawnsBLUE.add(new SpawnPoint(22, 9));
        /* 1312 */     spawnsBLUE.add(new SpawnPoint(25, 12));
        /* 1313 */     spawnsBLUE.add(new SpawnPoint(26, 8));
        /* 1314 */     spawnsBLUE.add(new SpawnPoint(31, 14));
        /* 1315 */     spawnsBLUE.add(new SpawnPoint(23, 13));
        /*      */
        /* 1317 */     spawnsRED.add(new SpawnPoint(30, 43));
        /* 1318 */     spawnsRED.add(new SpawnPoint(33, 42));
        /* 1319 */     spawnsRED.add(new SpawnPoint(38, 41));
        /* 1320 */     spawnsRED.add(new SpawnPoint(26, 42));
        /* 1321 */     spawnsRED.add(new SpawnPoint(33, 46));
    }

    public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room) {
        gameObjects.put(0, new TreeGameObject(19, 41, 0, 1, 3, 3, 0, room.map, room));
        gameObjects.put(1, new TreeGameObject(9, 31, 0, 1, 5, 3, 0, room.map, room));
        gameObjects.put(2, new TreeGameObject(25, 38, 0, 1, 9, 3, 0, room.map, room));
        gameObjects.put(3, new TreeGameObject(49, 28, 0, 1, 15, 3, 0, room.map, room));
        gameObjects.put(4, new TreeGameObject(36, 15, 0, 1, 22, 3, 0, room.map, room));
        gameObjects.put(5, new TreeGameObject(47, 32, 0, 1, 24, 3, 0, room.map, room));
        gameObjects.put(6, new TreeGameObject(6, 20, 0, 1, 28, 3, 0, room.map, room));
        gameObjects.put(7, new TreeGameObject(26, 6, 0, 1, 31, 3, 0, room.map, room));
        gameObjects.put(8, new TreeGameObject(10, 26, 0, 1, 34, 3, 0, room.map, room));
        gameObjects.put(9, new TreeGameObject(13, 15, 0, 1, 39, 3, 0, room.map, room));
        gameObjects.put(10, new TreeGameObject(30, 7, 0, 1, 42, 3, 0, room.map, room));
        gameObjects.put(11, new TreeGameObject(15, 10, 0, 1, 48, 3, 0, room.map, room));
        gameObjects.put(12, new TreeGameObject(20, 4, 0, 1, 50, 3, 0, room.map, room));
        gameObjects.put(13, new TreeGameObject(45, 25, 0, 1, 56, 3, 0, room.map, room));
        gameObjects.put(14, new TreeGameObject(28, 47, 0, 1, 73, 3, 0, room.map, room));
        gameObjects.put(15, new MachineGameObject(26, 24, 0, 5, 0, 75, room.map, room));
        gameObjects.put(16, new TreeGameObject(5, 24, 0, 1, 76, 3, 0, room.map, room));
        gameObjects.put(17, new TreeGameObject(20, 8, 0, 1, 80, 3, 0, room.map, room));
        gameObjects.put(18, new TreeGameObject(15, 34, 0, 1, 92, 3, 0, room.map, room));
    }
}