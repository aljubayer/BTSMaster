package com.OMCB.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapInputDataToDataStructure {

    public Map<String, Map<String, Map<String, String>>> InsertIntoDataStructure(List<InputData> aInputList) throws NoSuchFieldException {

       
        Map<String, Map<String, Map<String, String>>> aInputDictionary1 = new HashMap<String, Map<String, Map<String, String>>>();

        for (InputData aInput : aInputList) {

            if (!aInputDictionary1.containsKey(aInput.BSC)) {
                List<PcmMapping> aPcmMap = new ArrayList<PcmMapping>();
                Map<String, String> aPcmMap1 = new HashMap<String, String>();

                if (!aInput.PCM1.equals("-1") && !aInput.TargetPCM1.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM1)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM1);
                        aMap.setTargetPCM(aInput.TargetPCM1);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM1, aInput.TargetPCM1);
                    }
                }

                if (!aInput.PCM2.equals("-1") && !aInput.TargetPCM2.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM2)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM2);
                        aMap.setTargetPCM(aInput.TargetPCM2);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM2, aInput.TargetPCM2);
                    }
                }

                if (!aInput.PCM3.equals("-1") && !aInput.TargetPCM3.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM3)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM3);
                        aMap.setTargetPCM(aInput.TargetPCM3);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM3, aInput.TargetPCM3);
                    }
                }
                if (!aInput.PCM4.equals("-1") && !aInput.TargetPCM4.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM4)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM4);
                        aMap.setTargetPCM(aInput.TargetPCM4);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM4, aInput.TargetPCM4);
                    }
                }

                if (!aInput.PCM5.equals("-1") && !aInput.TargetPCM5.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM5)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM5);
                        aMap.setTargetPCM(aInput.TargetPCM5);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM5, aInput.TargetPCM5);
                    }
                }

                if (!aInput.PCM6.equals("-1") && !aInput.TargetPCM6.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM6)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM6);
                        aMap.setTargetPCM(aInput.TargetPCM6);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM6, aInput.TargetPCM6);
                    }
                }

                if (!aInput.PCM7.equals("-1") && !aInput.TargetPCM7.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM7)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM7);
                        aMap.setTargetPCM(aInput.TargetPCM7);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM7, aInput.TargetPCM7);
                    }
                }

                if (!aInput.PCM8.equals("-1") && !aInput.TargetPCM8.equals("-1")) {
                    if (!aPcmMap1.containsKey(aInput.PCM8)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM8);
                        aMap.setTargetPCM(aInput.TargetPCM8);
                        aPcmMap.add(aMap);
                        aPcmMap1.put(aInput.PCM8, aInput.TargetPCM8);
                    }
                }

                Map<String, List<PcmMapping>> aBcfPcmMap = new HashMap<String, List<PcmMapping>>();
                Map<String, Map<String, String>> aBcfPcmMap1 = new HashMap<String, Map<String, String>>();
                aBcfPcmMap.put(aInput.BCF, aPcmMap);
                aBcfPcmMap1.put(aInput.BCF, aPcmMap1);
                aInputDictionary1.put(aInput.BSC, aBcfPcmMap1);

            } else if (!aInputDictionary1.get(aInput.BSC).containsKey(aInput.BCF)) {

                List<PcmMapping> aPcmMap = new ArrayList<PcmMapping>();
                Map<String, String> aPcmMap1 = new HashMap<String, String>();
                aInputDictionary1.get(aInput.BSC).put(aInput.BCF, aPcmMap1);
                if (!aInput.PCM1.equals("-1") && !aInput.TargetPCM1.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM1)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM1);
                        aMap.setTargetPCM(aInput.TargetPCM1);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM1, aInput.TargetPCM1);
                    }
                }

                if (!aInput.PCM2.equals("-1") && !aInput.TargetPCM2.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM2)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM2);
                        aMap.setTargetPCM(aInput.TargetPCM2);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM2, aInput.TargetPCM2);
                    }
                }

                if (!aInput.PCM3.equals("-1") && !aInput.TargetPCM3.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM3)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM3);
                        aMap.setTargetPCM(aInput.TargetPCM3);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM3, aInput.TargetPCM3);
                    }
                }

                if (!aInput.PCM4.equals("-1") && !aInput.TargetPCM4.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM4)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM4);
                        aMap.setTargetPCM(aInput.TargetPCM4);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM4, aInput.TargetPCM4);
                    }
                }

                if (!aInput.PCM5.equals("-1") && !aInput.TargetPCM5.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM5)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM5);
                        aMap.setTargetPCM(aInput.TargetPCM5);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM5, aInput.TargetPCM5);
                    }
                }

                if (!aInput.PCM6.equals("-1") && !aInput.TargetPCM6.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM6)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM6);
                        aMap.setTargetPCM(aInput.TargetPCM6);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM6, aInput.TargetPCM6);
                    }
                }

                if (!aInput.PCM7.equals("-1") && !aInput.TargetPCM7.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM7)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM7);
                        aMap.setTargetPCM(aInput.TargetPCM7);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM7, aInput.TargetPCM7);
                    }
                }

                if (!aInput.PCM8.equals("-1") && !aInput.TargetPCM8.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM8)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM8);
                        aMap.setTargetPCM(aInput.TargetPCM8);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM8, aInput.TargetPCM8);
                    }
                }

                Map<String, List<PcmMapping>> aBcfPcmMap = new HashMap<String, List<PcmMapping>>();
                aBcfPcmMap.put(aInput.BCF, aPcmMap);


            } else {
                Map<String, String> aPcmMap1 = new HashMap<String, String>();
                aPcmMap1 = aInputDictionary1.get(aInput.BSC).get(aInput.BCF);
                List<PcmMapping> aPcmMap = new ArrayList<PcmMapping>();

                if (!aInput.PCM1.equals("-1") && !aInput.TargetPCM1.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM1)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM1);
                        aMap.setTargetPCM(aInput.TargetPCM1);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM1, aInput.TargetPCM1);
                    }
                }

                if (!aInput.PCM2.equals("-1") && !aInput.TargetPCM2.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM2)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM2);
                        aMap.setTargetPCM(aInput.TargetPCM2);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM2, aInput.TargetPCM2);
                    }
                }

                if (!aInput.PCM3.equals("-1") && !aInput.TargetPCM3.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM3)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM3);
                        aMap.setTargetPCM(aInput.TargetPCM3);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM3, aInput.TargetPCM3);
                    }
                }

                if (!aInput.PCM4.equals("-1") && !aInput.TargetPCM4.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM4)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM4);
                        aMap.setTargetPCM(aInput.TargetPCM4);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM4, aInput.TargetPCM4);
                    }
                }

                if (!aInput.PCM5.equals("-1") && !aInput.TargetPCM5.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM5)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM5);
                        aMap.setTargetPCM(aInput.TargetPCM5);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM5, aInput.TargetPCM5);
                    }
                }

                if (!aInput.PCM6.equals("-1") && !aInput.TargetPCM6.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM6)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM6);
                        aMap.setTargetPCM(aInput.TargetPCM6);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM6, aInput.TargetPCM6);
                    }
                }

                if (!aInput.PCM7.equals("-1") && !aInput.TargetPCM7.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM7)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM7);
                        aMap.setTargetPCM(aInput.TargetPCM7);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM7, aInput.TargetPCM7);
                    }
                }

                if (!aInput.PCM8.equals("-1") && !aInput.TargetPCM8.equals("-1")) {
                    if (!aInputDictionary1.get(aInput.BSC).get(aInput.BCF).containsKey(aInput.PCM8)) {
                        PcmMapping aMap = new PcmMapping();
                        aMap.setOldPCM(aInput.PCM8);
                        aMap.setTargetPCM(aInput.TargetPCM8);
                        aPcmMap.add(aMap);
                        aInputDictionary1.get(aInput.BSC).get(aInput.BCF).put(aInput.PCM8, aInput.TargetPCM8);
                    }
                }

            }

            System.out.println(aInput.BCF + " " + aInput.BSC + " " + aInput.PCM1);

        }
        
        for (Map.Entry<String, Map<String, Map<String, String>>> amp : aInputDictionary1.entrySet()) {

            System.out.println(amp.getKey() + "/" + amp.getValue());
        }
        return aInputDictionary1;
    }

}
