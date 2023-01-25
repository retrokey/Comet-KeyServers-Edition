package com.cometproject.server.game.utilities.validator;

import com.cometproject.api.config.CometSettings;
import com.cometproject.server.boot.Comet;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Steve Winfield (IDK Project)
 */
public class PlayerFigureValidator {

    private static Map<Integer, Map<Integer, PlayerFigureColor>> palettes;
    private static Map<String, PlayerFigureSetType> setTypes;
    private static Map<String, Map<Integer, List<String>>> mandatorySetTypes;
    private static List<Integer> listexeptionsetmap;
    private static List<Integer> listexeptioncolors;

    public static boolean CheckFilterChar(String data) {
        char[] letters = data.toLowerCase().toCharArray();
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyz1234567890-.";

        for (char chr : letters) {
            if (!allowedCharacters.contains(chr + "")) {
                return true;
            }
        }
        return false;
    }

    public static void loadFigureData() {
        try {
            final File figureDataFile = new File("config/figuredata.xml");
            final Document furnidataDocument;

            if (!figureDataFile.exists()) {
                furnidataDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Comet.class.getResourceAsStream("/config/figuredata.xml"));
                FileUtils.copyURLToFile(Comet.class.getResource("/config/figuredata.xml"), figureDataFile);
            } else {
                furnidataDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(figureDataFile);
            }

            final Element furnidata = (Element) furnidataDocument.getElementsByTagName("figuredata").item(0);

            PlayerFigureValidator.palettes = new ConcurrentHashMap<>();
            PlayerFigureValidator.setTypes = new ConcurrentHashMap<>();

            /**
             * Gender and club mandatories
             */
            PlayerFigureValidator.mandatorySetTypes = new ConcurrentHashMap<>();
            PlayerFigureValidator.mandatorySetTypes.put("m", new ConcurrentHashMap<>());
            PlayerFigureValidator.mandatorySetTypes.get("m").put(0, new ArrayList<>());
            PlayerFigureValidator.mandatorySetTypes.get("m").put(1, new ArrayList<>());
            PlayerFigureValidator.mandatorySetTypes.get("m").put(2, new ArrayList<>());
            PlayerFigureValidator.mandatorySetTypes.put("f", new ConcurrentHashMap<>());
            PlayerFigureValidator.mandatorySetTypes.get("f").put(0, new ArrayList<>());
            PlayerFigureValidator.mandatorySetTypes.get("f").put(1, new ArrayList<>());
            PlayerFigureValidator.mandatorySetTypes.get("f").put(2, new ArrayList<>());

            final NodeList palettes = furnidata.getElementsByTagName("palette");

            for (int paletteIndex = 0; paletteIndex < palettes.getLength(); ++paletteIndex) {
                final Node paletteNode = palettes.item(paletteIndex);

                if (paletteNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element paletteElement = (Element) paletteNode;

                    final int paletteId = Integer.valueOf(paletteElement.getAttribute("id"));
                    PlayerFigureValidator.palettes.put(paletteId, new ConcurrentHashMap<>());

                    final NodeList colors = paletteElement.getElementsByTagName("color");

                    for (int colorIndex = 0; colorIndex < colors.getLength(); ++colorIndex) {
                        final Node colorNode = colors.item(colorIndex);

                        if (colorNode.getNodeType() == Node.ELEMENT_NODE) {
                            final Element colorElement = (Element) colorNode;
                            final int colorId = Integer.valueOf(colorElement.getAttribute("id"));

                            PlayerFigureValidator.palettes.get(paletteId).put(colorId, new PlayerFigureColor(Integer.valueOf(colorElement.getAttribute("club")), Integer.valueOf(colorElement.getAttribute("selectable")) == 1));
                        }
                    }
                }
            }

            final NodeList setTypes = furnidata.getElementsByTagName("settype");

            for (int setTypeIndex = 0; setTypeIndex < setTypes.getLength(); ++setTypeIndex) {
                final Node setTypeNode = setTypes.item(setTypeIndex);

                if (setTypeNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element setTypeElement = (Element) setTypeNode;
                    final String typeName = setTypeElement.getAttribute("type").toLowerCase();

                    if (Integer.valueOf(setTypeElement.getAttribute("mand_m_0")) > 0) {
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(0).add(typeName);
                    }

                    if (Integer.valueOf(setTypeElement.getAttribute("mand_f_0")) > 0) {
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(0).add(typeName);
                    }

                    if (Integer.valueOf(setTypeElement.getAttribute("mand_m_1")) > 0) {
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(1).add(typeName);
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(2).add(typeName);
                    }

                    if (Integer.valueOf(setTypeElement.getAttribute("mand_f_1")) > 0) {
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(1).add(typeName);
                        PlayerFigureValidator.mandatorySetTypes.get("m").get(2).add(typeName);
                    }

                    final Map<Integer, PlayerFigureSet> setMap = new ConcurrentHashMap<>();
                    final NodeList sets = setTypeElement.getElementsByTagName("set");

                    for (int setIndex = 0; setIndex < sets.getLength(); ++setIndex) {
                        final Node setNode = sets.item(setIndex);

                        if (setNode.getNodeType() == Node.ELEMENT_NODE) {
                            final Element setElement = (Element) setNode;
                            final int setId = Integer.valueOf(setElement.getAttribute("id"));

                            int colorCount = 0;
                            final NodeList parts = setElement.getElementsByTagName("part");

                            for (int partIndex = 0; partIndex < parts.getLength(); ++partIndex) {
                                final Node partNode = parts.item(partIndex);

                                if (partNode.getNodeType() == Node.ELEMENT_NODE) {
                                    final Element partElement = (Element) partNode;
                                    final int colorIndex = Integer.valueOf(partElement.getAttribute("colorindex"));

                                    if (Integer.valueOf(partElement.getAttribute("colorable")) > 0 && colorIndex > colorCount) {
                                        colorCount = colorIndex;
                                    }
                                }
                            }

                            setMap.put(setId, new PlayerFigureSet(setElement.getAttribute("gender").toLowerCase(), Integer.valueOf(setElement.getAttribute("club")), Integer.valueOf(setElement.getAttribute("colorable")) > 0, Integer.valueOf(setElement.getAttribute("selectable")) > 0, colorCount));
                        }
                    }

                    PlayerFigureValidator.setTypes.put(typeName, new PlayerFigureSetType(typeName, Integer.valueOf(setTypeElement.getAttribute("paletteid")), setMap));
                }
            }
        } catch (Exception e) {
            Comet.getServer().getLogger().warn("Error while initializing the PlayerFigureValidator", e);
        }
    }

    public static List<Integer> setmapexeption() {
        List<Integer> data = new ArrayList<Integer>();
        data.add(99999);
        data.add(11090314);
        data.add(0);
        data.add(12090314);
        data.add(13090314);
        data.add(78322);
        data.add(5964);
        data.add(15863);
        data.add(1536);
        data.add(742327);
        data.add(7823);
        data.add(32);
        data.add(450);
        data.add(9767);
        data.add(9563333);
        data.add(2738000);
        data.add(3379);
        data.add(3375);
        data.add(3122);
        data.add(6661);
        data.add(3382);
        data.add(30);
        data.add(6662);
        data.add(71788);
        data.add(9002);
        data.add(3);
        data.add(2);
        data.add(318);
        data.add(140);
        data.add(874);
        data.add(305);
        data.add(33);
        data.add(316);
        data.add(8);
        data.add(200);
        data.add(3387);
        data.add(33811);
        data.add(30166666);
        data.add(91289496);
        data.add(91932046);
        data.add(3024);
        data.add(3388);
        data.add(33821);
        data.add(9921);
        data.add(595902031);
        data.add(3384);
        data.add(3376);
        data.add(2729);
        data.add(333555);
        data.add(999);
        data.add(20);
        data.add(329);
        data.add(160);
        data.add(12);
        data.add(616);
        data.add(956);
        data.add(334);
        data.add(332);
        data.add(7557);
        data.add(21015);
        data.add(322);
        data.add(9888);
        data.add(88188);
        data.add(757013);
        data.add(1);
        data.add(2911);
        data.add(317);
        data.add(31);
        data.add(307);
        data.add(100);
        data.add(757014);
        data.add(18);
        data.add(757012);
        data.add(201);
        data.add(326);
        data.add(321);
        data.add(9000);
        data.add(130903);
        data.add(9);
        data.add(306);
        data.add(181);
        data.add(337);
        data.add(335);
        data.add(301);
        data.add(9992);
        data.add(336);
        data.add(338);
        data.add(102);
        data.add(3373);
        data.add(33444121);
        data.add(88133305);
        data.add(3601111);
        data.add(30060);
        data.add(3380);
        data.add(30061);
        data.add(3381);
        data.add(3386);
        data.add(33810);
        data.add(33771);
        data.add(3385);
        data.add(595);
        data.add(3378);
        data.add(3374);
        data.add(3383);
        data.add(30010);
        data.add(583200);
        data.add(33850);
        data.add(33820);
        data.add(3377);
        data.add(1204);
        data.add(310);
        data.add(9999);
        data.add(6690);
        data.add(2799);
        data.add(3010);
        data.add(2459);
        data.add(7775);
        data.add(319);
        data.add(1000);
        data.add(308);
        data.add(4998);
        data.add(3494);
        data.add(7570);
        data.add(538);
        data.add(75700);
        data.add(90);
        data.add(921);
        data.add(320);
        data.add(3543);
        data.add(6);
        data.add(92115);
        data.add(5);
        data.add(20705);
        data.add(921152);
        data.add(161);
        data.add(55);
        data.add(99975);
        data.add(907);
        data.add(7105);
        data.add(14);
        data.add(314);
        data.add(999248);
        data.add(92);
        data.add(981199);
        data.add(2016);
        data.add(5994);
        data.add(327);
        data.add(25101);
        data.add(7);
        data.add(99);
        data.add(451);
        return data;
    }

    public static boolean isValidFigureCode(final String figureCode, final String genderCode) {
        if (!CometSettings.playerFigureValidation) {
            return true;
        }

        if (figureCode == null) {
            return false;
        }

        try {
            final String gender = genderCode.toLowerCase();

            if (!gender.equals("m") && !gender.equals("f")) {
                return false;
            }

            final String[] sets = figureCode.split("\\.");
            final List<String> mandatorySets = PlayerFigureValidator.mandatorySetTypes.get(gender).get(2);

            if (sets.length < mandatorySets.size()) {
                return false;
            }

            final List<String> containedSets = new ArrayList<>();

            for (final String set : sets) {
                final String[] setData = set.split("-");

                if (setData.length < 2) { //3 de base on passe a 2
                    return false;
                }

                final String setType = setData[0].toLowerCase();

                if (!PlayerFigureValidator.setTypes.containsKey(setType)) {
                    return false;
                }

                final PlayerFigureSetType setTypeInstance = PlayerFigureValidator.setTypes.get(setType);
                final Map<Integer, PlayerFigureSet> setMap = setTypeInstance.getSets();
                final int setId = Integer.valueOf(setData[1]);

                if (!setMap.containsKey(setId) && !listexeptionsetmap.contains(setId)) { //exception sur certains look
                    return false;
                }

                if (setData.length != 2 && !listexeptionsetmap.contains(setId)) // si look a 2 on passe pas le filtre couleurs
                {
                    int colorIdbug = 0;

                    if (setData.length > 3)
                        colorIdbug = Integer.valueOf(setData[3]);

                    if ((!listexeptioncolors.contains(colorIdbug)) && setData.length > 3) // exception couleurs
                    {
                        final PlayerFigureSet setInstance = setMap.get(setId);

                        if (!setInstance.isSelectable() || (setData.length - 2) < setInstance.getColorCount()) {
                            return false;
                        }

                        for (int i = 0; i < setInstance.getColorCount(); ++i) {
                            final int colorId = Integer.valueOf(setData[i + 2]);

                            if (!PlayerFigureValidator.palettes.get(setTypeInstance.getPaletteId()).containsKey(colorId)) {
                                return false;
                            }

                            final PlayerFigureColor colorInstance = PlayerFigureValidator.palettes.get(setTypeInstance.getPaletteId()).get(colorId);

                            if (!colorInstance.isSelectable()) {
                                return false;
                            }
                        }
                    }
                }

                containedSets.add(setType);
            }

            for (final String mandatorySet : mandatorySets) {
                if (!containedSets.contains(mandatorySet) && !mandatorySet.equals("lg")) {
                    return false;
                }
            }

            return true;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}