package com.cometproject.games.snowwar;
import com.cometproject.games.snowwar.gameobjects.GameItemObject;
import com.cometproject.games.snowwar.gameobjects.PileGameObject;
import com.cometproject.games.snowwar.items.BaseItem;
import com.cometproject.games.snowwar.items.MapStuffData;
import java.util.Map;

public class SnowWarArena9 extends SnowWarArenaBase {
    public SnowWarArena9() {

        ArenaType = 9;
        ArenaHeight = 50;
        ArenaWidth = 50;
        HeightMap = "" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
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
                "xxxxx0000000000000000000000000000000000000xxxxxxxx\r" +
                "xxxxx00000000000000000000000000000000000000xxxxxxx\r" +
                "xxxxx000000000000000000000000000000000000000xxxxxx\r" +
                "xxxxx0000000000000000000000000000000000000000xxxxx\r" +
                "0xxxx00000000000000000000000000000000000000000xxxx\r" +
                "xxxxx00000000000000000000000000000000000000000xxxx\r" +
                "xxxxx00000000000000000000000000000000000000000xxxx\r" +
                "xxxxx000000000000000000000000000000000000000000xxx\r" +
                "xxxxx000000000000000000000000000000000000000000xxx\r" +
                "xxxxx000000000000000000000000000000000000000000xxx\r" +
                "xxxxxx00000000000000000000000000000000000000000xxx\r" +
                "xxxxxxx0000000000000000000000000000000000000000xxx\r" +
                "xxxxxxxx0000000000000000000000000000000000000xxxxx\r" +
                "xxxxxxxxx00000000000000000000000000000000000xxxxxx\r" +
                "xxxxxxxxxx000000000000000000000000000000000xxxxxxx\r" +
                "xxxxxxxxxxx00000000000000000000000000000000xxxx0xx\r" +
                "xxxxxxxxxxxx0000000000000000000000000000000xxxxxxx\r" +
                "xxxxxxxxxxxxx00000000000000000000000000000xxxxxxxx\r" +
                "xxxxxxxxxxxxxx0000000000000000000000000000xxxxxxxx\r" +
                "xxxxxxxxxxxxxxx00000000000000000000000000xxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxx0000000000000000000000xxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxx00000000000000000000xxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\r";


        GamefuseObject Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_iceblock;
        Item.baseItem.allowWalk = false;
        Item.baseItem.Height = 1.0F;
        Item.itemId = 0;
        Item.X = 9;
        Item.Y = 14;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 1;
        Item.X = 22;
        Item.Y = 27;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 2;
        Item.X = 8;
        Item.Y = 20;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        /* 107 */     Item = new GamefuseObject();
        /* 108 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 109 */     Item.baseItem.allowWalk = true;
        /* 110 */     Item.baseItem.Height = 0.0F;
        /* 111 */     Item.itemId = 3;
        /* 112 */     Item.X = 8;
        /* 113 */     Item.Y = 26;
        /* 114 */     Item.Rot = 0;
        /* 115 */     Item.Z = 0;
        /* 116 */     Item.extraData.setExtraData("0");
        /* 117 */     fuseObjects.add(Item);
        /*     */
        /* 119 */     Item = new GamefuseObject();
        /* 120 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 121 */     Item.baseItem.allowWalk = false;
        /* 122 */     Item.baseItem.Height = 1.0F;
        /* 123 */     Item.itemId = 4;
        /* 124 */     Item.X = 35;
        /* 125 */     Item.Y = 30;
        /* 126 */     Item.Rot = 2;
        /* 127 */     Item.Z = 0;
        /* 128 */     Item.extraData.setExtraData("");
        /* 129 */     fuseObjects.add(Item);
        /*     */
        /* 131 */     Item = new GamefuseObject();
        /* 132 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 133 */     Item.baseItem.allowWalk = false;
        /* 134 */     Item.baseItem.Height = 1.0F;
        /* 135 */     Item.itemId = 5;
        /* 136 */     Item.X = 22;
        /* 137 */     Item.Y = 17;
        /* 138 */     Item.Rot = 2;
        /* 139 */     Item.Z = 0;
        /* 140 */     Item.extraData.setExtraData("");
        /* 141 */     fuseObjects.add(Item);
        /*     */
        /* 143 */     Item = new GamefuseObject();
        /* 144 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 145 */     Item.baseItem.allowWalk = false;
        /* 146 */     Item.baseItem.Height = 1.0F;
        /* 147 */     Item.itemId = 6;
        /* 148 */     Item.X = 9;
        /* 149 */     Item.Y = 17;
        /* 150 */     Item.Rot = 2;
        /* 151 */     Item.Z = 0;
        /* 152 */     Item.extraData.setExtraData("");
        /* 153 */     fuseObjects.add(Item);
        /*     */
        /* 155 */     Item = new GamefuseObject();
        /* 156 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 157 */     Item.baseItem.allowWalk = false;
        /* 158 */     Item.baseItem.Height = 1.0F;
        /* 159 */     Item.itemId = 7;
        /* 160 */     Item.X = 35;
        /* 161 */     Item.Y = 24;
        /* 162 */     Item.Rot = 4;
        /* 163 */     Item.Z = 0;
        /* 164 */     Item.extraData.setExtraData("");
        /* 165 */     fuseObjects.add(Item);
        /*     */
        /* 167 */     Item = new GamefuseObject();
        /* 168 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 169 */     Item.baseItem.allowWalk = true;
        /* 170 */     Item.baseItem.Height = 0.0F;
        /* 171 */     Item.itemId = 8;
        /* 172 */     Item.X = 36;
        /* 173 */     Item.Y = 18;
        /* 174 */     Item.Rot = 0;
        /* 175 */     Item.Z = 0;
        /* 176 */     Item.extraData.setExtraData("0");
        /* 177 */     fuseObjects.add(Item);
        /*     */
        /* 179 */     Item = new GamefuseObject();
        /* 180 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 181 */     Item.baseItem.allowWalk = true;
        /* 182 */     Item.baseItem.Height = 0.0F;
        /* 183 */     Item.itemId = 9;
        /* 184 */     Item.X = 24;
        /* 185 */     Item.Y = 21;
        /* 186 */     Item.Rot = 0;
        /* 187 */     Item.Z = 0;
        /* 188 */     Item.extraData.setExtraData("0");
        /* 189 */     fuseObjects.add(Item);
        /*     */
        /* 191 */     Item = new GamefuseObject();
        /* 192 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 193 */     Item.baseItem.allowWalk = false;
        /* 194 */     Item.baseItem.Height = 1.0F;
        /* 195 */     Item.itemId = 10;
        /* 196 */     Item.X = 22;
        /* 197 */     Item.Y = 25;
        /* 198 */     Item.Rot = 4;
        /* 199 */     Item.Z = 0;
        /* 200 */     Item.extraData.setExtraData("");
        /* 201 */     fuseObjects.add(Item);
        /*     */
        /* 203 */     Item = new GamefuseObject();
        /* 204 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 205 */     Item.baseItem.allowWalk = false;
        /* 206 */     Item.baseItem.Height = 1.0F;
        /* 207 */     Item.itemId = 11;
        /* 208 */     Item.X = 18;
        /* 209 */     Item.Y = 21;
        /* 210 */     Item.Rot = 2;
        /* 211 */     Item.Z = 0;
        /* 212 */     Item.extraData.setExtraData("");
        /* 213 */     fuseObjects.add(Item);
        /*     */
        /* 215 */     Item = new GamefuseObject();
        /* 216 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 217 */     Item.baseItem.allowWalk = true;
        /* 218 */     Item.baseItem.Height = 0.0F;
        /* 219 */     Item.itemId = 12;
        /* 220 */     Item.X = 8;
        /* 221 */     Item.Y = 23;
        /* 222 */     Item.Rot = 0;
        /* 223 */     Item.Z = 0;
        /* 224 */     Item.extraData.setExtraData("0");
        /* 225 */     fuseObjects.add(Item);
        /*     */
        /* 227 */     Item = new GamefuseObject();
        /* 228 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 229 */     Item.baseItem.allowWalk = true;
        /* 230 */     Item.baseItem.Height = 0.0F;
        /* 231 */     Item.itemId = 13;
        /* 232 */     Item.X = 36;
        /* 233 */     Item.Y = 27;
        /* 234 */     Item.Rot = 0;
        /* 235 */     Item.Z = 0;
        /* 236 */     Item.extraData.setExtraData("0");
        /* 237 */     fuseObjects.add(Item);
        /*     */
        /* 239 */     Item = new GamefuseObject();
        /* 240 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 241 */     Item.baseItem.allowWalk = false;
        /* 242 */     Item.baseItem.Height = 1.0F;
        /* 243 */     Item.itemId = 14;
        /* 244 */     Item.X = 9;
        /* 245 */     Item.Y = 20;
        /* 246 */     Item.Rot = 6;
        /* 247 */     Item.Z = 0;
        /* 248 */     Item.extraData.setExtraData("");
        /* 249 */     fuseObjects.add(Item);
        /*     */
        /* 251 */     Item = new GamefuseObject();
        /* 252 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 253 */     Item.baseItem.allowWalk = true;
        /* 254 */     Item.baseItem.Height = 0.0F;
        /* 255 */     Item.itemId = 15;
        /* 256 */     Item.X = 36;
        /* 257 */     Item.Y = 21;
        /* 258 */     Item.Rot = 0;
        /* 259 */     Item.Z = 0;
        /* 260 */     Item.extraData.setExtraData("0");
        /* 261 */     fuseObjects.add(Item);
        /*     */
        /* 263 */     Item = new GamefuseObject();
        /* 264 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 265 */     Item.baseItem.allowWalk = true;
        /* 266 */     Item.baseItem.Height = 0.0F;
        /* 267 */     Item.itemId = 16;
        /* 268 */     Item.X = 28;
        /* 269 */     Item.Y = 21;
        /* 270 */     Item.Rot = 0;
        /* 271 */     Item.Z = 0;
        /* 272 */     Item.extraData.setExtraData("0");
        /* 273 */     fuseObjects.add(Item);
        /*     */
        /* 275 */     Item = new GamefuseObject();
        /* 276 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 277 */     Item.baseItem.allowWalk = false;
        /* 278 */     Item.baseItem.Height = 1.0F;
        /* 279 */     Item.itemId = 17;
        /* 280 */     Item.X = 35;
        /* 281 */     Item.Y = 15;
        /* 282 */     Item.Rot = 2;
        /* 283 */     Item.Z = 0;
        /* 284 */     Item.extraData.setExtraData("");
        /* 285 */     fuseObjects.add(Item);
        /*     */
        /* 287 */     Item = new GamefuseObject();
        /* 288 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 289 */     Item.baseItem.allowWalk = false;
        /* 290 */     Item.baseItem.Height = 1.0F;
        /* 291 */     Item.itemId = 18;
        /* 292 */     Item.X = 9;
        /* 293 */     Item.Y = 29;
        /* 294 */     Item.Rot = 2;
        /* 295 */     Item.Z = 0;
        /* 296 */     Item.extraData.setExtraData("");
        /* 297 */     fuseObjects.add(Item);
        /*     */
        /* 299 */     Item = new GamefuseObject();
        /* 300 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 301 */     Item.baseItem.allowWalk = false;
        /* 302 */     Item.baseItem.Height = 1.0F;
        /* 303 */     Item.itemId = 19;
        /* 304 */     Item.X = 35;
        /* 305 */     Item.Y = 18;
        /* 306 */     Item.Rot = 6;
        /* 307 */     Item.Z = 0;
        /* 308 */     Item.extraData.setExtraData("");
        /* 309 */     fuseObjects.add(Item);
        /*     */
        /* 311 */     Item = new GamefuseObject();
        /* 312 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 313 */     Item.baseItem.allowWalk = false;
        /* 314 */     Item.baseItem.Height = 1.0F;
        /* 315 */     Item.itemId = 20;
        /* 316 */     Item.X = 22;
        /* 317 */     Item.Y = 21;
        /* 318 */     Item.Rot = 0;
        /* 319 */     Item.Z = 0;
        /* 320 */     Item.extraData.setExtraData("");
        /* 321 */     fuseObjects.add(Item);
        /*     */
        /* 323 */     Item = new GamefuseObject();
        /* 324 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 325 */     Item.baseItem.allowWalk = false;
        /* 326 */     Item.baseItem.Height = 1.0F;
        /* 327 */     Item.itemId = 21;
        /* 328 */     Item.X = 9;
        /* 329 */     Item.Y = 23;
        /* 330 */     Item.Rot = 4;
        /* 331 */     Item.Z = 0;
        /* 332 */     Item.extraData.setExtraData("");
        /* 333 */     fuseObjects.add(Item);
        /*     */
        /* 335 */     Item = new GamefuseObject();
        /* 336 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 337 */     Item.baseItem.allowWalk = true;
        /* 338 */     Item.baseItem.Height = 0.0F;
        /* 339 */     Item.itemId = 22;
        /* 340 */     Item.X = 36;
        /* 341 */     Item.Y = 30;
        /* 342 */     Item.Rot = 0;
        /* 343 */     Item.Z = 0;
        /* 344 */     Item.extraData.setExtraData("0");
        /* 345 */     fuseObjects.add(Item);
        /*     */
        /* 347 */     Item = new GamefuseObject();
        /* 348 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 349 */     Item.baseItem.allowWalk = true;
        /* 350 */     Item.baseItem.Height = 0.0F;
        /* 351 */     Item.itemId = 23;
        /* 352 */     Item.X = 22;
        /* 353 */     Item.Y = 23;
        /* 354 */     Item.Rot = 0;
        /* 355 */     Item.Z = 0;
        /* 356 */     Item.extraData.setExtraData("0");
        /* 357 */     fuseObjects.add(Item);
        /*     */
        /* 359 */     Item = new GamefuseObject();
        /* 360 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 361 */     Item.baseItem.allowWalk = true;
        /* 362 */     Item.baseItem.Height = 0.0F;
        /* 363 */     Item.itemId = 24;
        /* 364 */     Item.X = 22;
        /* 365 */     Item.Y = 19;
        /* 366 */     Item.Rot = 0;
        /* 367 */     Item.Z = 0;
        /* 368 */     Item.extraData.setExtraData("0");
        /* 369 */     fuseObjects.add(Item);
        /*     */
        /* 371 */     Item = new GamefuseObject();
        /* 372 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 373 */     Item.baseItem.allowWalk = true;
        /* 374 */     Item.baseItem.Height = 0.0F;
        /* 375 */     Item.itemId = 25;
        /* 376 */     Item.X = 8;
        /* 377 */     Item.Y = 14;
        /* 378 */     Item.Rot = 0;
        /* 379 */     Item.Z = 0;
        /* 380 */     Item.extraData.setExtraData("0");
        /* 381 */     fuseObjects.add(Item);
        /*     */
        /* 383 */     Item = new GamefuseObject();
        /* 384 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 385 */     Item.baseItem.allowWalk = true;
        /* 386 */     Item.baseItem.Height = 0.0F;
        /* 387 */     Item.itemId = 26;
        /* 388 */     Item.X = 36;
        /* 389 */     Item.Y = 15;
        /* 390 */     Item.Rot = 0;
        /* 391 */     Item.Z = 0;
        /* 392 */     Item.extraData.setExtraData("0");
        /* 393 */     fuseObjects.add(Item);
        /*     */
        /* 395 */     Item = new GamefuseObject();
        /* 396 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 397 */     Item.baseItem.allowWalk = false;
        /* 398 */     Item.baseItem.Height = 1.0F;
        /* 399 */     Item.itemId = 27;
        /* 400 */     Item.X = 35;
        /* 401 */     Item.Y = 27;
        /* 402 */     Item.Rot = 0;
        /* 403 */     Item.Z = 0;
        /* 404 */     Item.extraData.setExtraData("");
        /* 405 */     fuseObjects.add(Item);
        /*     */
        /* 407 */     Item = new GamefuseObject();
        /* 408 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 409 */     Item.baseItem.allowWalk = false;
        /* 410 */     Item.baseItem.Height = 1.0F;
        /* 411 */     Item.itemId = 28;
        /* 412 */     Item.X = 35;
        /* 413 */     Item.Y = 21;
        /* 414 */     Item.Rot = 0;
        /* 415 */     Item.Z = 0;
        /* 416 */     Item.extraData.setExtraData("");
        /* 417 */     fuseObjects.add(Item);
        /*     */
        /* 419 */     Item = new GamefuseObject();
        /* 420 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 421 */     Item.baseItem.allowWalk = false;
        /* 422 */     Item.baseItem.Height = 1.0F;
        /* 423 */     Item.itemId = 29;
        /* 424 */     Item.X = 26;
        /* 425 */     Item.Y = 21;
        /* 426 */     Item.Rot = 6;
        /* 427 */     Item.Z = 0;
        /* 428 */     Item.extraData.setExtraData("");
        /* 429 */     fuseObjects.add(Item);
        /*     */
        /* 431 */     Item = new GamefuseObject();
        /* 432 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 433 */     Item.baseItem.allowWalk = true;
        /* 434 */     Item.baseItem.Height = 0.0F;
        /* 435 */     Item.itemId = 30;
        /* 436 */     Item.X = 8;
        /* 437 */     Item.Y = 17;
        /* 438 */     Item.Rot = 0;
        /* 439 */     Item.Z = 0;
        /* 440 */     Item.extraData.setExtraData("0");
        /* 441 */     fuseObjects.add(Item);
        /*     */
        /* 443 */     Item = new GamefuseObject();
        /* 444 */     Item.baseItem = BaseItem.snst_ballpile;
        /* 445 */     Item.baseItem.allowWalk = true;
        /* 446 */     Item.baseItem.Height = 0.0F;
        /* 447 */     Item.itemId = 31;
        /* 448 */     Item.X = 22;
        /* 449 */     Item.Y = 15;
        /* 450 */     Item.Rot = 0;
        /* 451 */     Item.Z = 0;
        /* 452 */     Item.extraData.setExtraData("0");
        /* 453 */     fuseObjects.add(Item);
        /*     */
        /* 455 */     Item = new GamefuseObject();
        /* 456 */     Item.baseItem = BaseItem.snst_iceblock;
        /* 457 */     Item.baseItem.allowWalk = false;
        Item.baseItem.Height = 1.0F;
        Item.itemId = 32;
        Item.X = 9;
        Item.Y = 26;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 33;
        Item.X = 16;
        Item.Y = 21;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 34;
        Item.X = 20;
        Item.Y = 21;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 35;
        Item.X = 36;
        Item.Y = 24;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.ads_background;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.baseItem.itemExtraType = 1;
        Item.itemId = 36;
        Item.X = 0;
        Item.Y = 22;
        Item.Rot = 1;
        Item.Z = 0;
        Item.extraData = new MapStuffData("state=0\toffsetX=-1070\toffsetZ=9920\toffsetY=1520\timageUrl=https://comet.habbia.in/swf/c_images/DEV_tests/snst_bg_2_big.png");
        fuseObjects.add(Item);

        Item = new GamefuseObject();
        Item.baseItem = BaseItem.snst_ballpile;
        Item.baseItem.allowWalk = true;
        Item.baseItem.Height = 0.0F;
        Item.itemId = 37;
        Item.X = 8;
        Item.Y = 29;
        Item.Rot = 0;
        Item.Z = 0;
        Item.extraData.setExtraData("0");
        fuseObjects.add(Item);

        spawnsBLUE.add(new SpawnPoint(10, 10));
        spawnsRED.add(new SpawnPoint(11, 11));
    }

    public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room) {
        gameObjects.put(0, new PileGameObject(22, 27, 12, 12, 1, room.map, room));
        gameObjects.put(1, new PileGameObject(8, 20, 12, 12, 2, room.map, room));
        gameObjects.put(2, new PileGameObject(8, 26, 12, 12, 3, room.map, room));
        gameObjects.put(3, new PileGameObject(36, 18, 12, 12, 8, room.map, room));
        gameObjects.put(4, new PileGameObject(24, 21, 12, 12, 9, room.map, room));
        gameObjects.put(5, new PileGameObject(8, 23, 12, 12, 12, room.map, room));
        gameObjects.put(6, new PileGameObject(36, 27, 12, 12, 13, room.map, room));
        gameObjects.put(7, new PileGameObject(36, 21, 12, 12, 15, room.map, room));
        gameObjects.put(8, new PileGameObject(28, 21, 12, 12, 16, room.map, room));
        gameObjects.put(9, new PileGameObject(36, 30, 12, 12, 22, room.map, room));
        gameObjects.put(10, new PileGameObject(22, 23, 12, 12, 23, room.map, room));
        gameObjects.put(11, new PileGameObject(22, 19, 12, 12, 24, room.map, room));
        gameObjects.put(12, new PileGameObject(8, 14, 12, 12, 25, room.map, room));
        gameObjects.put(13, new PileGameObject(36, 15, 12, 12, 26, room.map, room));
        gameObjects.put(14, new PileGameObject(22, 15, 12, 12, 31, room.map, room));
        gameObjects.put(15, new PileGameObject(16, 21, 12, 12, 33, room.map, room));
        gameObjects.put(16, new PileGameObject(20, 21, 12, 12, 34, room.map, room));
        gameObjects.put(17, new PileGameObject(36, 24, 12, 12, 35, room.map, room));
        gameObjects.put(18, new PileGameObject(8, 29, 12, 12, 37, room.map, room));
    }
}