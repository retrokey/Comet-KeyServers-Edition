package com.cometproject.games.snowwar;
import com.cometproject.api.config.CometSettings;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.PileGameObject;
import com.cometproject.games.snowwar.gameobjects.TreeGameObject;
import com.cometproject.games.snowwar.items.BaseItem;
import com.cometproject.games.snowwar.items.MapStuffData;
import java.util.Map;

public class SnowWarArena11 extends SnowWarArenaBase {
    public SnowWarArena11() {
        ArenaType = 11;
        ArenaWidth = 50;
        ArenaHeight = 50;
        HeightMap = "" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxx00000000xxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxxxxxxxxx\r" +
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
                "xxxxx0000000000000000000000000000000000xxxxxxxxxxx\r" +
                "xxxxx00000000000000000000000000000000000xxxxxxxxxx\r" +
                "xxxxx000000000000000000000000000000000000xxxxxxxxx\r" +
                "xxxx00000000000000000000000000000000000000xxxxxxxx\r" +
                "xxxx000000000000000000000000000000000000000xxxxxxx\r" +
                "xxxx0000000000000000000000000000000000000000xxxxxx\r" +
                "xxxx00000000000000000000000000000000000000000xxxxx\r" +
                "0xxx000000000000000000000000000000000000000000xxxx\r" +
                "xxxx000000000000000000000000000000000000000000xxxx\r" +
                "xxxx0000000000000000000000000000000000000000000xxx\r" +
                "xxxx0000000000000000000000000000000000000000000xxx\r" +
                "xxxx0000000000000000000000000000000000000000000xxx\r" +
                "xxxxx000000000000000000000000000000000000000000xxx\r" +
                "xxxxxx00000000000000000000000000000000000000000xxx\r" +
                "xxxxxxx0000000000000000000000000000000000000000xxx\r" +
                "xxxxxxxx000000000000000000000000000000000000000xxx\r" +
                "xxxxxxxxx0000000000000000000000000000000000000xxxx\r" +
                "xxxxxxxxxx000000000000000000000000000000000000xxxx\r" +
                "xxxxxxxxxxx0000000000000000000000000000000000xxxxx\r" +
                "xxxxxxxxxxxx00000000000000000000000000000000xxxxxx\r" +
                "xxxxxxxxxxxxx000000000000000000000000000000xxxxxxx\r" +
                "xxxxxxxxxxxxxx0000000000000000000000000000xxxxxxxx\r" +
                "xxxxxxxxxxxxxxx00000000000000000000000000xxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxx0000000000000000000000xxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxx00000000000000000000xxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxx000000000000000000xxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxx0000000000000000xxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxx000000xxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r";


        /*  72 */     GamefuseObject Item = new GamefuseObject();
        /*  73 */     Item.baseItem = BaseItem.snst_tree1_d;
        /*  74 */     Item.baseItem.allowWalk = false;
        /*  75 */     Item.baseItem.Height = 1.0F;
        /*  76 */     Item.itemId = 0;
        /*  77 */     Item.X = 29;
        /*  78 */     Item.Y = 23;
        /*  79 */     Item.Rot = 0;
        /*  80 */     Item.Z = 0;
        /*  81 */     Item.extraData.setExtraData("0");
        /*  82 */     fuseObjects.add(Item);
        /*     */
        /*  84 */     Item = new GamefuseObject();
        /*  85 */     Item.baseItem = BaseItem.snst_tree1_d;
        /*  86 */     Item.baseItem.allowWalk = false;
        /*  87 */     Item.baseItem.Height = 1.0F;
        /*  88 */     Item.itemId = 1;
        /*  89 */     Item.X = 11;
        /*  90 */     Item.Y = 16;
        /*  91 */     Item.Rot = 2;
        /*  92 */     Item.Z = 0;
        /*  93 */     Item.extraData.setExtraData("0");
        /*  94 */     fuseObjects.add(Item);
        /*     */
        /*  96 */     Item = new GamefuseObject();
        /*  97 */     Item.baseItem = BaseItem.snst_tree1_d;
        /*  98 */     Item.baseItem.allowWalk = false;
        /*  99 */     Item.baseItem.Height = 1.0F;
        /* 100 */     Item.itemId = 2;
        /* 101 */     Item.X = 26;
        /* 102 */     Item.Y = 28;
        /* 103 */     Item.Rot = 2;
        /* 104 */     Item.Z = 0;
        /* 105 */     Item.extraData.setExtraData("0");
        /* 106 */     fuseObjects.add(Item);
        /*     */
        /* 108 */     Item = new GamefuseObject();
        /* 109 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 110 */     Item.baseItem.allowWalk = false;
        /* 111 */     Item.baseItem.Height = 1.0F;
        /* 112 */     Item.itemId = 3;
        /* 113 */     Item.X = 31;
        /* 114 */     Item.Y = 42;
        /* 115 */     Item.Rot = 2;
        /* 116 */     Item.Z = 0;
        /* 117 */     Item.extraData.setExtraData("0");
        /* 118 */     fuseObjects.add(Item);
        /*     */
        /* 120 */     Item = new GamefuseObject();
        /* 121 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 122 */     Item.baseItem.allowWalk = true;
        /* 123 */     Item.baseItem.Height = 0.0F;
        /* 124 */     Item.itemId = 4;
        /* 125 */     Item.X = 27;
        /* 126 */     Item.Y = 16;
        /* 127 */     Item.Rot = 0;
        /* 128 */     Item.Z = 0;
        /* 129 */     Item.extraData.setExtraData("0");
        /* 130 */     fuseObjects.add(Item);
        /*     */
        /* 132 */     Item = new GamefuseObject();
        /* 133 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 134 */     Item.baseItem.allowWalk = false;
        /* 135 */     Item.baseItem.Height = 1.0F;
        /* 136 */     Item.itemId = 5;
        /* 137 */     Item.X = 32;
        /* 138 */     Item.Y = 10;
        /* 139 */     Item.Rot = 0;
        /* 140 */     Item.Z = 0;
        /* 141 */     Item.extraData.setExtraData("1");
        /* 142 */     fuseObjects.add(Item);
        /*     */
        /* 144 */     Item = new GamefuseObject();
        /* 145 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 146 */     Item.baseItem.allowWalk = false;
        /* 147 */     Item.baseItem.Height = 1.0F;
        /* 148 */     Item.itemId = 6;
        /* 149 */     Item.X = 13;
        /* 150 */     Item.Y = 21;
        /* 151 */     Item.Rot = 2;
        /* 152 */     Item.Z = 0;
        /* 153 */     Item.extraData.setExtraData("0");
        /* 154 */     fuseObjects.add(Item);
        /*     */
        /* 156 */     Item = new GamefuseObject();
        /* 157 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 158 */     Item.baseItem.allowWalk = false;
        /* 159 */     Item.baseItem.Height = 1.0F;
        /* 160 */     Item.itemId = 7;
        /* 161 */     Item.X = 33;
        /* 162 */     Item.Y = 14;
        /* 163 */     Item.Rot = 0;
        /* 164 */     Item.Z = 0;
        /* 165 */     Item.extraData.setExtraData("0");
        /* 166 */     fuseObjects.add(Item);
        /*     */
        /* 168 */     Item = new GamefuseObject();
        /* 169 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 170 */     Item.baseItem.allowWalk = true;
        /* 171 */     Item.baseItem.Height = 0.0F;
        /* 172 */     Item.itemId = 8;
        /* 173 */     Item.X = 18;
        /* 174 */     Item.Y = 14;
        /* 175 */     Item.Rot = 0;
        /* 176 */     Item.Z = 0;
        /* 177 */     Item.extraData.setExtraData("0");
        /* 178 */     fuseObjects.add(Item);
        /*     */
        /* 180 */     Item = new GamefuseObject();
        /* 181 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 182 */     Item.baseItem.allowWalk = false;
        /* 183 */     Item.baseItem.Height = 1.0F;
        /* 184 */     Item.itemId = 9;
        /* 185 */     Item.X = 15;
        /* 186 */     Item.Y = 36;
        /* 187 */     Item.Rot = 2;
        /* 188 */     Item.Z = 0;
        /* 189 */     Item.extraData.setExtraData("1");
        /* 190 */     fuseObjects.add(Item);
        /*     */
        /* 192 */     Item = new GamefuseObject();
        /* 193 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 194 */     Item.baseItem.allowWalk = false;
        /* 195 */     Item.baseItem.Height = 1.0F;
        /* 196 */     Item.itemId = 10;
        /* 197 */     Item.X = 34;
        /* 198 */     Item.Y = 17;
        /* 199 */     Item.Rot = 0;
        /* 200 */     Item.Z = 0;
        /* 201 */     Item.extraData.setExtraData("2");
        /* 202 */     fuseObjects.add(Item);
        /*     */
        /* 204 */     Item = new GamefuseObject();
        /* 205 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 206 */     Item.baseItem.allowWalk = false;
        /* 207 */     Item.baseItem.Height = 1.0F;
        /* 208 */     Item.itemId = 11;
        /* 209 */     Item.X = 17;
        /* 210 */     Item.Y = 33;
        /* 211 */     Item.Rot = 2;
        /* 212 */     Item.Z = 0;
        /* 213 */     Item.extraData.setExtraData("2");
        /* 214 */     fuseObjects.add(Item);
        /*     */
        /* 216 */     Item = new GamefuseObject();
        /* 217 */     Item.baseItem = BaseItem.snst_block1;
        /* 218 */     Item.baseItem.allowWalk = false;
        /* 219 */     Item.baseItem.Height = 1.0F;
        /* 220 */     Item.itemId = 12;
        /* 221 */     Item.X = 27;
        /* 222 */     Item.Y = 17;
        /* 223 */     Item.Rot = 4;
        /* 224 */     Item.Z = 0;
        /* 225 */     Item.extraData.setExtraData("");
        /* 226 */     fuseObjects.add(Item);
        /*     */
        /* 228 */     Item = new GamefuseObject();
        /* 229 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 230 */     Item.baseItem.allowWalk = true;
        /* 231 */     Item.baseItem.Height = 0.0F;
        /* 232 */     Item.itemId = 13;
        /* 233 */     Item.X = 31;
        /* 234 */     Item.Y = 12;
        /* 235 */     Item.Rot = 0;
        /* 236 */     Item.Z = 0;
        /* 237 */     Item.extraData.setExtraData("0");
        /* 238 */     fuseObjects.add(Item);
        /*     */
        /* 240 */     Item = new GamefuseObject();
        /* 241 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 242 */     Item.baseItem.allowWalk = false;
        /* 243 */     Item.baseItem.Height = 1.0F;
        /* 244 */     Item.itemId = 14;
        /* 245 */     Item.X = 34;
        /* 246 */     Item.Y = 42;
        /* 247 */     Item.Rot = 2;
        /* 248 */     Item.Z = 0;
        /* 249 */     Item.extraData.setExtraData("2");
        /* 250 */     fuseObjects.add(Item);
        /*     */
        /* 252 */     Item = new GamefuseObject();
        /* 253 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 254 */     Item.baseItem.allowWalk = false;
        /* 255 */     Item.baseItem.Height = 1.0F;
        /* 256 */     Item.itemId = 15;
        /* 257 */     Item.X = 41;
        /* 258 */     Item.Y = 31;
        /* 259 */     Item.Rot = 0;
        /* 260 */     Item.Z = 0;
        /* 261 */     Item.extraData.setExtraData("0");
        /* 262 */     fuseObjects.add(Item);
        /*     */
        /* 264 */     Item = new GamefuseObject();
        /* 265 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 266 */     Item.baseItem.allowWalk = false;
        /* 267 */     Item.baseItem.Height = 1.0F;
        /* 268 */     Item.itemId = 16;
        /* 269 */     Item.X = 9;
        /* 270 */     Item.Y = 20;
        /* 271 */     Item.Rot = 2;
        /* 272 */     Item.Z = 0;
        /* 273 */     Item.extraData.setExtraData("0");
        /* 274 */     fuseObjects.add(Item);
        /*     */
        /* 276 */     Item = new GamefuseObject();
        /* 277 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 278 */     Item.baseItem.allowWalk = false;
        /* 279 */     Item.baseItem.Height = 1.0F;
        /* 280 */     Item.itemId = 17;
        /* 281 */     Item.X = 30;
        /* 282 */     Item.Y = 39;
        /* 283 */     Item.Rot = 0;
        /* 284 */     Item.Z = 0;
        /* 285 */     Item.extraData.setExtraData("1");
        /* 286 */     fuseObjects.add(Item);
        /*     */
        /* 288 */     Item = new GamefuseObject();
        /* 289 */     Item.baseItem = BaseItem.snst_block1;
        /* 290 */     Item.baseItem.allowWalk = false;
        /* 291 */     Item.baseItem.Height = 1.0F;
        /* 292 */     Item.itemId = 18;
        /* 293 */     Item.X = 21;
        /* 294 */     Item.Y = 27;
        /* 295 */     Item.Rot = 6;
        /* 296 */     Item.Z = 0;
        /* 297 */     Item.extraData.setExtraData("");
        /* 298 */     fuseObjects.add(Item);
        /*     */
        /* 300 */     Item = new GamefuseObject();
        /* 301 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 302 */     Item.baseItem.allowWalk = true;
        /* 303 */     Item.baseItem.Height = 0.0F;
        /* 304 */     Item.itemId = 19;
        /* 305 */     Item.X = 27;
        /* 306 */     Item.Y = 12;
        /* 307 */     Item.Rot = 0;
        /* 308 */     Item.Z = 0;
        /* 309 */     Item.extraData.setExtraData("0");
        /* 310 */     fuseObjects.add(Item);
        /*     */
        /* 312 */     Item = new GamefuseObject();
        /* 313 */     Item.baseItem = BaseItem.snst_block1;
        /* 314 */     Item.baseItem.allowWalk = false;
        /* 315 */     Item.baseItem.Height = 1.0F;
        /* 316 */     Item.itemId = 20;
        /* 317 */     Item.X = 22;
        /* 318 */     Item.Y = 28;
        /* 319 */     Item.Rot = 4;
        /* 320 */     Item.Z = 0;
        /* 321 */     Item.extraData.setExtraData("");
        /* 322 */     fuseObjects.add(Item);
        /*     */
        /* 324 */     Item = new GamefuseObject();
        /* 325 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 326 */     Item.baseItem.allowWalk = false;
        /* 327 */     Item.baseItem.Height = 1.0F;
        /* 328 */     Item.itemId = 21;
        /* 329 */     Item.X = 13;
        /* 330 */     Item.Y = 31;
        /* 331 */     Item.Rot = 2;
        /* 332 */     Item.Z = 0;
        /* 333 */     Item.extraData.setExtraData("0");
        /* 334 */     fuseObjects.add(Item);
        /*     */
        /* 336 */     Item = new GamefuseObject();
        /* 337 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 338 */     Item.baseItem.allowWalk = false;
        /* 339 */     Item.baseItem.Height = 1.0F;
        /* 340 */     Item.itemId = 22;
        /* 341 */     Item.X = 10;
        /* 342 */     Item.Y = 30;
        /* 343 */     Item.Rot = 2;
        /* 344 */     Item.Z = 0;
        /* 345 */     Item.extraData.setExtraData("0");
        /* 346 */     fuseObjects.add(Item);
        /*     */
        /* 348 */     Item = new GamefuseObject();
        /* 349 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 350 */     Item.baseItem.allowWalk = true;
        /* 351 */     Item.baseItem.Height = 0.0F;
        /* 352 */     Item.itemId = 23;
        /* 353 */     Item.X = 22;
        /* 354 */     Item.Y = 35;
        /* 355 */     Item.Rot = 0;
        /* 356 */     Item.Z = 0;
        /* 357 */     Item.extraData.setExtraData("0");
        /* 358 */     fuseObjects.add(Item);
        /*     */
        /* 360 */     Item = new GamefuseObject();
        /* 361 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 362 */     Item.baseItem.allowWalk = true;
        /* 363 */     Item.baseItem.Height = 0.0F;
        /* 364 */     Item.itemId = 24;
        /* 365 */     Item.X = 21;
        /* 366 */     Item.Y = 28;
        /* 367 */     Item.Rot = 0;
        /* 368 */     Item.Z = 0;
        /* 369 */     Item.extraData.setExtraData("0");
        /* 370 */     fuseObjects.add(Item);
        /*     */
        /* 372 */     Item = new GamefuseObject();
        /* 373 */     Item.baseItem = BaseItem.xm09_man_a;
        /* 374 */     Item.baseItem.allowWalk = false;
        /* 375 */     Item.baseItem.Height = 0.0F;
        /* 376 */     Item.itemId = 25;
        /* 377 */     Item.X = 14;
        /* 378 */     Item.Y = 9;
        /* 379 */     Item.Rot = 2;
        /* 380 */     Item.Z = 0;
        /* 381 */     Item.extraData.setExtraData("0");
        /* 382 */     fuseObjects.add(Item);
        /*     */
        /* 384 */     Item = new GamefuseObject();
        /* 385 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 386 */     Item.baseItem.allowWalk = false;
        /* 387 */     Item.baseItem.Height = 1.0F;
        /* 388 */     Item.itemId = 26;
        /* 389 */     Item.X = 40;
        /* 390 */     Item.Y = 34;
        /* 391 */     Item.Rot = 0;
        /* 392 */     Item.Z = 0;
        /* 393 */     Item.extraData.setExtraData("1");
        /* 394 */     fuseObjects.add(Item);
        /*     */
        /* 396 */     Item = new GamefuseObject();
        /* 397 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 398 */     Item.baseItem.allowWalk = false;
        /* 399 */     Item.baseItem.Height = 1.0F;
        /* 400 */     Item.itemId = 27;
        /* 401 */     Item.X = 42;
        /* 402 */     Item.Y = 21;
        /* 403 */     Item.Rot = 0;
        /* 404 */     Item.Z = 0;
        /* 405 */     Item.extraData.setExtraData("0");
        /* 406 */     fuseObjects.add(Item);
        /*     */
        /* 408 */     Item = new GamefuseObject();
        /* 409 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 410 */     Item.baseItem.allowWalk = true;
        /* 411 */     Item.baseItem.Height = 0.0F;
        /* 412 */     Item.itemId = 28;
        /* 413 */     Item.X = 27;
        /* 414 */     Item.Y = 32;
        /* 415 */     Item.Rot = 0;
        /* 416 */     Item.Z = 0;
        /* 417 */     Item.extraData.setExtraData("0");
        /* 418 */     fuseObjects.add(Item);
        /*     */
        /* 420 */     Item = new GamefuseObject();
        /* 421 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 422 */     Item.baseItem.allowWalk = false;
        /* 423 */     Item.baseItem.Height = 1.0F;
        /* 424 */     Item.itemId = 29;
        /* 425 */     Item.X = 25;
        /* 426 */     Item.Y = 20;
        /* 427 */     Item.Rot = 0;
        /* 428 */     Item.Z = 0;
        /* 429 */     Item.extraData.setExtraData("2");
        /* 430 */     fuseObjects.add(Item);
        /*     */
        /* 432 */     Item = new GamefuseObject();
        /* 433 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 434 */     Item.baseItem.allowWalk = false;
        /* 435 */     Item.baseItem.Height = 1.0F;
        /* 436 */     Item.itemId = 30;
        /* 437 */     Item.X = 36;
        /* 438 */     Item.Y = 14;
        /* 439 */     Item.Rot = 0;
        /* 440 */     Item.Z = 0;
        /* 441 */     Item.extraData.setExtraData("0");
        /* 442 */     fuseObjects.add(Item);
        /*     */
        /* 444 */     Item = new GamefuseObject();
        /* 445 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 446 */     Item.baseItem.allowWalk = false;
        /* 447 */     Item.baseItem.Height = 1.0F;
        /* 448 */     Item.itemId = 31;
        /* 449 */     Item.X = 11;
        /* 450 */     Item.Y = 26;
        /* 451 */     Item.Rot = 2;
        /* 452 */     Item.Z = 0;
        /* 453 */     Item.extraData.setExtraData("0");
        /* 454 */     fuseObjects.add(Item);
        /*     */
        /* 456 */     Item = new GamefuseObject();
        /* 457 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 458 */     Item.baseItem.allowWalk = false;
        /* 459 */     Item.baseItem.Height = 1.0F;
        /* 460 */     Item.itemId = 32;
        /* 461 */     Item.X = 39;
        /* 462 */     Item.Y = 36;
        /* 463 */     Item.Rot = 0;
        /* 464 */     Item.Z = 0;
        /* 465 */     Item.extraData.setExtraData("1");
        /* 466 */     fuseObjects.add(Item);
        /*     */
        /* 468 */     Item = new GamefuseObject();
        /* 469 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 470 */     Item.baseItem.allowWalk = false;
        /* 471 */     Item.baseItem.Height = 1.0F;
        /* 472 */     Item.itemId = 33;
        /* 473 */     Item.X = 25;
        /* 474 */     Item.Y = 24;
        /* 475 */     Item.Rot = 0;
        /* 476 */     Item.Z = 0;
        /* 477 */     Item.extraData.setExtraData("0");
        /* 478 */     fuseObjects.add(Item);
        /*     */
        /* 480 */     Item = new GamefuseObject();
        /* 481 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 482 */     Item.baseItem.allowWalk = true;
        /* 483 */     Item.baseItem.Height = 0.0F;
        /* 484 */     Item.itemId = 34;
        /* 485 */     Item.X = 22;
        /* 486 */     Item.Y = 13;
        /* 487 */     Item.Rot = 0;
        /* 488 */     Item.Z = 0;
        /* 489 */     Item.extraData.setExtraData("0");
        /* 490 */     fuseObjects.add(Item);
        /*     */
        /* 492 */     Item = new GamefuseObject();
        /* 493 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 494 */     Item.baseItem.allowWalk = false;
        /* 495 */     Item.baseItem.Height = 1.0F;
        /* 496 */     Item.itemId = 35;
        /* 497 */     Item.X = 13;
        /* 498 */     Item.Y = 13;
        /* 499 */     Item.Rot = 0;
        /* 500 */     Item.Z = 0;
        /* 501 */     Item.extraData.setExtraData("0");
        /* 502 */     fuseObjects.add(Item);
        /*     */
        /* 504 */     Item = new GamefuseObject();
        /* 505 */     Item.baseItem = BaseItem.snst_fence;
        /* 506 */     Item.baseItem.allowWalk = false;
        /* 507 */     Item.baseItem.Height = 0.0F;
        /* 508 */     Item.itemId = 36;
        /* 509 */     Item.X = 19;
        /* 510 */     Item.Y = 22;
        /* 511 */     Item.Rot = 2;
        /* 512 */     Item.Z = 0;
        /* 513 */     Item.extraData.setExtraData("0");
        /* 514 */     fuseObjects.add(Item);
        /*     */
        /* 516 */     Item = new GamefuseObject();
        /* 517 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 518 */     Item.baseItem.allowWalk = false;
        /* 519 */     Item.baseItem.Height = 1.0F;
        /* 520 */     Item.itemId = 37;
        /* 521 */     Item.X = 28;
        /* 522 */     Item.Y = 9;
        /* 523 */     Item.Rot = 0;
        /* 524 */     Item.Z = 0;
        /* 525 */     Item.extraData.setExtraData("1");
        /* 526 */     fuseObjects.add(Item);
        /*     */
        /* 528 */     Item = new GamefuseObject();
        /* 529 */     Item.baseItem = BaseItem.snst_fence;
        /* 530 */     Item.baseItem.allowWalk = false;
        /* 531 */     Item.baseItem.Height = 0.0F;
        /* 532 */     Item.itemId = 38;
        /* 533 */     Item.X = 34;
        /* 534 */     Item.Y = 26;
        /* 535 */     Item.Rot = 2;
        /* 536 */     Item.Z = 0;
        /* 537 */     Item.extraData.setExtraData("1");
        /* 538 */     fuseObjects.add(Item);
        /*     */
        /* 540 */     Item = new GamefuseObject();
        /* 541 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 542 */     Item.baseItem.allowWalk = false;
        /* 543 */     Item.baseItem.Height = 1.0F;
        /* 544 */     Item.itemId = 39;
        /* 545 */     Item.X = 19;
        /* 546 */     Item.Y = 11;
        /* 547 */     Item.Rot = 0;
        /* 548 */     Item.Z = 0;
        /* 549 */     Item.extraData.setExtraData("1");
        /* 550 */     fuseObjects.add(Item);
        /*     */
        /* 552 */     Item = new GamefuseObject();
        /* 553 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 554 */     Item.baseItem.allowWalk = true;
        /* 555 */     Item.baseItem.Height = 0.0F;
        /* 556 */     Item.itemId = 40;
        /* 557 */     Item.X = 32;
        /* 558 */     Item.Y = 20;
        /* 559 */     Item.Rot = 0;
        /* 560 */     Item.Z = 0;
        /* 561 */     Item.extraData.setExtraData("0");
        /* 562 */     fuseObjects.add(Item);
        /*     */
        /* 564 */     Item = new GamefuseObject();
        /* 565 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 566 */     Item.baseItem.allowWalk = false;
        /* 567 */     Item.baseItem.Height = 1.0F;
        /* 568 */     Item.itemId = 41;
        /* 569 */     Item.X = 29;
        /* 570 */     Item.Y = 44;
        /* 571 */     Item.Rot = 2;
        /* 572 */     Item.Z = 0;
        /* 573 */     Item.extraData.setExtraData("0");
        /* 574 */     fuseObjects.add(Item);
        /*     */
        /* 576 */     Item = new GamefuseObject();
        /* 577 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 578 */     Item.baseItem.allowWalk = false;
        /* 579 */     Item.baseItem.Height = 1.0F;
        /* 580 */     Item.itemId = 42;
        /* 581 */     Item.X = 43;
        /* 582 */     Item.Y = 27;
        /* 583 */     Item.Rot = 0;
        /* 584 */     Item.Z = 0;
        /* 585 */     Item.extraData.setExtraData("0");
        /* 586 */     fuseObjects.add(Item);
        /*     */
        /* 588 */     Item = new GamefuseObject();
        /* 589 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 590 */     Item.baseItem.allowWalk = false;
        /* 591 */     Item.baseItem.Height = 1.0F;
        /* 592 */     Item.itemId = 43;
        /* 593 */     Item.X = 19;
        /* 594 */     Item.Y = 36;
        /* 595 */     Item.Rot = 2;
        /* 596 */     Item.Z = 0;
        /* 597 */     Item.extraData.setExtraData("0");
        /* 598 */     fuseObjects.add(Item);
        /*     */
        /* 600 */     Item = new GamefuseObject();
        /* 601 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 602 */     Item.baseItem.allowWalk = false;
        /* 603 */     Item.baseItem.Height = 1.0F;
        /* 604 */     Item.itemId = 44;
        /* 605 */     Item.X = 29;
        /* 606 */     Item.Y = 26;
        /* 607 */     Item.Rot = 0;
        /* 608 */     Item.Z = 0;
        /* 609 */     Item.extraData.setExtraData("1");
        /* 610 */     fuseObjects.add(Item);
        /*     */
        /* 612 */     Item = new GamefuseObject();
        /* 613 */     Item.baseItem = BaseItem.xm09_man_c;
        /* 614 */     Item.baseItem.allowWalk = false;
        /* 615 */     Item.baseItem.Height = 0.0F;
        /* 616 */     Item.itemId = 45;
        /* 617 */     Item.X = 14;
        /* 618 */     Item.Y = 9;
        /* 619 */     Item.Rot = 4;
        /* 620 */     Item.Z = 2480;
        /* 621 */     Item.extraData.setExtraData("6");
        /* 622 */     fuseObjects.add(Item);
        /*     */
        /* 624 */     Item = new GamefuseObject();
        /* 625 */     Item.baseItem = BaseItem.ads_background;
        /* 626 */     Item.baseItem.allowWalk = true;
        /* 627 */     Item.baseItem.Height = 0.0F;
        /* 628 */     Item.baseItem.itemExtraType = 1;
        /* 629 */     Item.itemId = 46;
        /* 630 */     Item.X = 0;
        /* 631 */     Item.Y = 22;
        /* 632 */     Item.Rot = 1;
        /* 633 */     Item.Z = 0;
        /* 634 */     Item.extraData = new MapStuffData("state=0\toffsetX=-1119\toffsetZ=9950\toffsetY=390\timageUrl=https://comet.habbia.in/swf/c_images/DEV_tests/snst_bg_3_noscale.png");
        /* 635 */     fuseObjects.add(Item);
        /*     */
        /* 637 */     Item = new GamefuseObject();
        /* 638 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 639 */     Item.baseItem.allowWalk = false;
        /* 640 */     Item.baseItem.Height = 1.0F;
        /* 641 */     Item.itemId = 47;
        /* 642 */     Item.X = 14;
        /* 643 */     Item.Y = 17;
        /* 644 */     Item.Rot = 2;
        /* 645 */     Item.Z = 0;
        /* 646 */     Item.extraData.setExtraData("0");
        /* 647 */     fuseObjects.add(Item);
        /*     */
        /* 649 */     Item = new GamefuseObject();
        /* 650 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 651 */     Item.baseItem.allowWalk = false;
        /* 652 */     Item.baseItem.Height = 1.0F;
        /* 653 */     Item.itemId = 48;
        /* 654 */     Item.X = 24;
        /* 655 */     Item.Y = 9;
        /* 656 */     Item.Rot = 0;
        /* 657 */     Item.Z = 0;
        /* 658 */     Item.extraData.setExtraData("0");
        /* 659 */     fuseObjects.add(Item);
        /*     */
        /* 661 */     Item = new GamefuseObject();
        /* 662 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 663 */     Item.baseItem.allowWalk = false;
        /* 664 */     Item.baseItem.Height = 1.0F;
        /* 665 */     Item.itemId = 49;
        /* 666 */     Item.X = 22;
        /* 667 */     Item.Y = 20;
        /* 668 */     Item.Rot = 0;
        /* 669 */     Item.Z = 0;
        /* 670 */     Item.extraData.setExtraData("0");
        /* 671 */     fuseObjects.add(Item);
        /*     */
        /* 673 */     Item = new GamefuseObject();
        /* 674 */     Item.baseItem = BaseItem.snst_fence;
        /* 675 */     Item.baseItem.allowWalk = false;
        /* 676 */     Item.baseItem.Height = 0.0F;
        /* 677 */     Item.itemId = 50;
        /* 678 */     Item.X = 17;
        /* 679 */     Item.Y = 22;
        /* 680 */     Item.Rot = 2;
        /* 681 */     Item.Z = 0;
        /* 682 */     Item.extraData.setExtraData("1");
        /* 683 */     fuseObjects.add(Item);
        /*     */
        /* 685 */     Item = new GamefuseObject();
        /* 686 */     Item.baseItem = BaseItem.snst_block1;
        /* 687 */     Item.baseItem.allowWalk = false;
        /* 688 */     Item.baseItem.Height = 1.0F;
        /* 689 */     Item.itemId = 51;
        /* 690 */     Item.X = 26;
        /* 691 */     Item.Y = 16;
        /* 692 */     Item.Rot = 6;
        /* 693 */     Item.Z = 0;
        /* 694 */     Item.extraData.setExtraData("");
        /* 695 */     fuseObjects.add(Item);
        /*     */
        /* 697 */     Item = new GamefuseObject();
        /* 698 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 699 */     Item.baseItem.allowWalk = true;
        /* 700 */     Item.baseItem.Height = 0.0F;
        /* 701 */     Item.itemId = 52;
        /* 702 */     Item.X = 17;
        /* 703 */     Item.Y = 29;
        /* 704 */     Item.Rot = 0;
        /* 705 */     Item.Z = 0;
        /* 706 */     Item.extraData.setExtraData("0");
        /* 707 */     fuseObjects.add(Item);
        /*     */
        /* 709 */     Item = new GamefuseObject();
        /* 710 */     Item.baseItem = BaseItem.snst_block1;
        /* 711 */     Item.baseItem.allowWalk = false;
        /* 712 */     Item.baseItem.Height = 1.0F;
        /* 713 */     Item.itemId = 53;
        /* 714 */     Item.X = 22;
        /* 715 */     Item.Y = 27;
        /* 716 */     Item.Rot = 6;
        /* 717 */     Item.Z = 0;
        /* 718 */     Item.extraData.setExtraData("");
        /* 719 */     fuseObjects.add(Item);
        /*     */
        /* 721 */     Item = new GamefuseObject();
        /* 722 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 723 */     Item.baseItem.allowWalk = false;
        /* 724 */     Item.baseItem.Height = 1.0F;
        /* 725 */     Item.itemId = 54;
        /* 726 */     Item.X = 37;
        /* 727 */     Item.Y = 40;
        /* 728 */     Item.Rot = 2;
        /* 729 */     Item.Z = 0;
        /* 730 */     Item.extraData.setExtraData("0");
        /* 731 */     fuseObjects.add(Item);
        /*     */
        /* 733 */     Item = new GamefuseObject();
        /* 734 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 735 */     Item.baseItem.allowWalk = false;
        /* 736 */     Item.baseItem.Height = 1.0F;
        /* 737 */     Item.itemId = 55;
        /* 738 */     Item.X = 24;
        /* 739 */     Item.Y = 42;
        /* 740 */     Item.Rot = 2;
        /* 741 */     Item.Z = 0;
        /* 742 */     Item.extraData.setExtraData("0");
        /* 743 */     fuseObjects.add(Item);
        /*     */
        /* 745 */     Item = new GamefuseObject();
        /* 746 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 747 */     Item.baseItem.allowWalk = false;
        /* 748 */     Item.baseItem.Height = 1.0F;
        /* 749 */     Item.itemId = 56;
        /* 750 */     Item.X = 17;
        /* 751 */     Item.Y = 10;
        /* 752 */     Item.Rot = 0;
        /* 753 */     Item.Z = 0;
        /* 754 */     Item.extraData.setExtraData("0");
        /* 755 */     fuseObjects.add(Item);
        /*     */
        /* 757 */     Item = new GamefuseObject();
        /* 758 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 759 */     Item.baseItem.allowWalk = true;
        /* 760 */     Item.baseItem.Height = 0.0F;
        /* 761 */     Item.itemId = 57;
        /* 762 */     Item.X = 14;
        /* 763 */     Item.Y = 24;
        /* 764 */     Item.Rot = 0;
        /* 765 */     Item.Z = 0;
        /* 766 */     Item.extraData.setExtraData("0");
        /* 767 */     fuseObjects.add(Item);
        /*     */
        /* 769 */     Item = new GamefuseObject();
        /* 770 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 771 */     Item.baseItem.allowWalk = false;
        /* 772 */     Item.baseItem.Height = 1.0F;
        /* 773 */     Item.itemId = 58;
        /* 774 */     Item.X = 20;
        /* 775 */     Item.Y = 40;
        /* 776 */     Item.Rot = 2;
        /* 777 */     Item.Z = 0;
        /* 778 */     Item.extraData.setExtraData("0");
        /* 779 */     fuseObjects.add(Item);
        /*     */
        /* 781 */     Item = new GamefuseObject();
        /* 782 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 783 */     Item.baseItem.allowWalk = false;
        /* 784 */     Item.baseItem.Height = 1.0F;
        /* 785 */     Item.itemId = 59;
        /* 786 */     Item.X = 20;
        /* 787 */     Item.Y = 8;
        /* 788 */     Item.Rot = 0;
        /* 789 */     Item.Z = 0;
        /* 790 */     Item.extraData.setExtraData("0");
        /* 791 */     fuseObjects.add(Item);
        /*     */
        /* 793 */     Item = new GamefuseObject();
        /* 794 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 795 */     Item.baseItem.allowWalk = true;
        /* 796 */     Item.baseItem.Height = 0.0F;
        /* 797 */     Item.itemId = 60;
        /* 798 */     Item.X = 17;
        /* 799 */     Item.Y = 35;
        /* 800 */     Item.Rot = 0;
        /* 801 */     Item.Z = 0;
        /* 802 */     Item.extraData.setExtraData("0");
        /* 803 */     fuseObjects.add(Item);
        /*     */
        /* 805 */     Item = new GamefuseObject();
        /* 806 */     Item.baseItem = BaseItem.snst_block1;
        /* 807 */     Item.baseItem.allowWalk = false;
        /* 808 */     Item.baseItem.Height = 1.0F;
        /* 809 */     Item.itemId = 61;
        /* 810 */     Item.X = 26;
        /* 811 */     Item.Y = 17;
        /* 812 */     Item.Rot = 4;
        /* 813 */     Item.Z = 0;
        /* 814 */     Item.extraData.setExtraData("");
        /* 815 */     fuseObjects.add(Item);
        /*     */
        /* 817 */     Item = new GamefuseObject();
        /* 818 */     Item.baseItem = BaseItem.snst_tree1_d;
        /* 819 */     Item.baseItem.allowWalk = false;
        /* 820 */     Item.baseItem.Height = 1.0F;
        /* 821 */     Item.itemId = 62;
        /* 822 */     Item.X = 22;
        /* 823 */     Item.Y = 24;
        /* 824 */     Item.Rot = 2;
        /* 825 */     Item.Z = 0;
        /* 826 */     Item.extraData.setExtraData("0");
        /* 827 */     fuseObjects.add(Item);
        /*     */
        /* 829 */     Item = new GamefuseObject();
        /* 830 */     Item.baseItem = BaseItem.snst_fence;
        /* 831 */     Item.baseItem.allowWalk = false;
        /* 832 */     Item.baseItem.Height = 0.0F;
        /* 833 */     Item.itemId = 63;
        /* 834 */     Item.X = 30;
        /* 835 */     Item.Y = 26;
        /* 836 */     Item.Rot = 2;
        /* 837 */     Item.Z = 0;
        /* 838 */     Item.extraData.setExtraData("1");
        /* 839 */     fuseObjects.add(Item);
        /*     */
        /* 841 */     Item = new GamefuseObject();
        /* 842 */     Item.baseItem = BaseItem.xm09_man_b;
        /* 843 */     Item.baseItem.allowWalk = false;
        /* 844 */     Item.baseItem.Height = 0.0F;
        /* 845 */     Item.itemId = 64;
        /* 846 */     Item.X = 14;
        /* 847 */     Item.Y = 9;
        /* 848 */     Item.Rot = 4;
        /* 849 */     Item.Z = 1280;
        /* 850 */     Item.extraData.setExtraData("5");
        /* 851 */     fuseObjects.add(Item);
        /*     */
        /* 853 */     spawnsBLUE.add(new SpawnPoint(22, 9));
        /* 854 */     spawnsBLUE.add(new SpawnPoint(25, 12));
        /* 855 */     spawnsBLUE.add(new SpawnPoint(26, 8));
        /* 856 */     spawnsBLUE.add(new SpawnPoint(31, 14));
        /* 857 */     spawnsBLUE.add(new SpawnPoint(23, 13));
        /*     */
        /* 859 */     spawnsRED.add(new SpawnPoint(30, 43));
        /* 860 */     spawnsRED.add(new SpawnPoint(33, 42));
        /* 861 */     spawnsRED.add(new SpawnPoint(38, 41));
        /* 862 */     spawnsRED.add(new SpawnPoint(26, 42));
        /* 863 */     spawnsRED.add(new SpawnPoint(33, 46));
        /*     */   }
    /*     */
    /*     */
    /*     */   public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room) {
        /* 868 */     gameObjects.put(0, new TreeGameObject(29, 23, 0, 1, 0, 3, 0, room.map, room));
        /* 869 */     gameObjects.put(1, new TreeGameObject(11, 16, 2, 1, 1, 3, 0, room.map, room));
        /* 870 */     gameObjects.put(2, new TreeGameObject(26, 28, 2, 1, 2, 3, 0, room.map, room));
        /* 871 */     gameObjects.put(3, new TreeGameObject(31, 42, 2, 1, 3, 3, 0, room.map, room));
        /* 872 */     gameObjects.put(4, new PileGameObject(27, 16, 12, 12, 4, room.map, room));
        /* 873 */     gameObjects.put(5, new TreeGameObject(32, 10, 0, 1, 5, 3, 1, room.map, room));
        /* 874 */     gameObjects.put(6, new TreeGameObject(13, 21, 2, 1, 6, 3, 0, room.map, room));
        /* 875 */     gameObjects.put(7, new TreeGameObject(33, 14, 0, 1, 7, 3, 0, room.map, room));
        /* 876 */     gameObjects.put(8, new PileGameObject(18, 14, 12, 12, 8, room.map, room));
        /* 877 */     gameObjects.put(9, new TreeGameObject(15, 36, 2, 1, 9, 3, 1, room.map, room));
        /* 878 */     gameObjects.put(10, new TreeGameObject(34, 17, 0, 1, 10, 3, 2, room.map, room));
        /* 879 */     gameObjects.put(11, new TreeGameObject(17, 33, 2, 1, 11, 3, 2, room.map, room));
        /* 880 */     gameObjects.put(12, new PileGameObject(31, 12, 12, 12, 13, room.map, room));
        /* 881 */     gameObjects.put(13, new TreeGameObject(34, 42, 2, 1, 14, 3, 2, room.map, room));
        /* 882 */     gameObjects.put(14, new TreeGameObject(41, 31, 0, 1, 15, 3, 0, room.map, room));
        /* 883 */     gameObjects.put(15, new TreeGameObject(9, 20, 2, 1, 16, 3, 0, room.map, room));
        /* 884 */     gameObjects.put(16, new TreeGameObject(30, 39, 0, 1, 17, 3, 1, room.map, room));
        /* 885 */     gameObjects.put(17, new PileGameObject(27, 12, 12, 12, 19, room.map, room));
        /* 886 */     gameObjects.put(18, new TreeGameObject(13, 31, 2, 1, 21, 3, 0, room.map, room));
        /* 887 */     gameObjects.put(19, new TreeGameObject(10, 30, 2, 1, 22, 3, 0, room.map, room));
        /* 888 */     gameObjects.put(20, new PileGameObject(22, 35, 12, 12, 23, room.map, room));
        /* 889 */     gameObjects.put(21, new PileGameObject(21, 28, 12, 12, 24, room.map, room));
        /* 890 */     gameObjects.put(22, new TreeGameObject(40, 34, 0, 1, 26, 3, 1, room.map, room));
        /* 891 */     gameObjects.put(23, new TreeGameObject(42, 21, 0, 1, 27, 3, 0, room.map, room));
        /* 892 */     gameObjects.put(24, new PileGameObject(27, 32, 12, 12, 28, room.map, room));
        /* 893 */     gameObjects.put(25, new TreeGameObject(25, 20, 0, 1, 29, 3, 2, room.map, room));
        /* 894 */     gameObjects.put(26, new TreeGameObject(36, 15, 0, 1, 30, 3, 0, room.map, room));
        /* 895 */     gameObjects.put(27, new TreeGameObject(11, 26, 2, 1, 31, 3, 0, room.map, room));
        /* 896 */     gameObjects.put(28, new TreeGameObject(39, 36, 0, 1, 32, 3, 1, room.map, room));
        /* 897 */     gameObjects.put(29, new TreeGameObject(25, 24, 0, 1, 33, 3, 0, room.map, room));
        /* 898 */     gameObjects.put(30, new PileGameObject(22, 13, 12, 12, 34, room.map, room));
        /* 899 */     gameObjects.put(31, new TreeGameObject(13, 13, 0, 1, 35, 3, 0, room.map, room));
        /* 900 */     gameObjects.put(32, new TreeGameObject(28, 9, 0, 1, 37, 3, 1, room.map, room));
        /* 901 */     gameObjects.put(33, new TreeGameObject(19, 11, 0, 1, 39, 3, 1, room.map, room));
        /* 902 */     gameObjects.put(34, new PileGameObject(32, 20, 12, 12, 40, room.map, room));
        /* 903 */     gameObjects.put(35, new TreeGameObject(29, 44, 2, 1, 41, 3, 0, room.map, room));
        /* 904 */     gameObjects.put(36, new TreeGameObject(43, 27, 0, 1, 42, 3, 0, room.map, room));
        /* 905 */     gameObjects.put(37, new TreeGameObject(19, 36, 2, 1, 43, 3, 0, room.map, room));
        /* 906 */     gameObjects.put(38, new TreeGameObject(29, 26, 0, 1, 44, 3, 1, room.map, room));
        /* 907 */     gameObjects.put(39, new TreeGameObject(14, 17, 2, 1, 47, 3, 0, room.map, room));
        /* 908 */     gameObjects.put(40, new TreeGameObject(24, 9, 0, 1, 48, 3, 0, room.map, room));
        /* 909 */     gameObjects.put(41, new TreeGameObject(23, 20, 0, 1, 49, 3, 0, room.map, room));
        /* 910 */     gameObjects.put(42, new PileGameObject(17, 29, 12, 12, 52, room.map, room));
        /* 911 */     gameObjects.put(43, new TreeGameObject(37, 40, 2, 1, 54, 3, 0, room.map, room));
        /* 912 */     gameObjects.put(44, new TreeGameObject(24, 42, 2, 1, 55, 3, 0, room.map, room));
        /* 913 */     gameObjects.put(45, new TreeGameObject(17, 10, 0, 1, 56, 3, 0, room.map, room));
        /* 914 */     gameObjects.put(46, new PileGameObject(14, 24, 12, 12, 57, room.map, room));
        /* 915 */     gameObjects.put(47, new TreeGameObject(20, 40, 2, 1, 58, 3, 0, room.map, room));
        /* 916 */     gameObjects.put(48, new TreeGameObject(20, 8, 0, 1, 59, 3, 0, room.map, room));
        /* 917 */     gameObjects.put(49, new PileGameObject(17, 35, 12, 12, 60, room.map, room));
        /* 918 */     gameObjects.put(50, new TreeGameObject(22, 24, 2, 1, 62, 3, 0, room.map, room));
        /*     */   }
    /*     */ }