package ru.billing.diffhelpers;


import org.jgrapht.graph.DefaultEdge;
import ru.billing.hextypes.*;

import java.util.HashMap;
import java.util.Set;

public class GeneralHelpers {
    // adapted from String.hashCode()
    public static long hash(String string) {
        long h = 1125899906842597L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 31*h + string.charAt(i);
        }
        return h;
    }
    ///////////////////////////

    private static String padString (Integer symbols) {
        String s = "|_";
        for (int i=0; i<symbols; i++) {
            s = " "+s;
        }
        return s;
    }

    ///////////////////////////


    public static String getRequestGUIDByHarc (
            HashMap<String, HexArcRequestRecord> vHexArcRequest,
            HashMap<String, HexRequestRecord>  vHexRequests,
            String vHARC_ID
    ) {
        try {

            return vHexArcRequest.get(vHARC_ID).getREQUEST_GUID();
        }catch (Exception e) {return "";}

    }

    ///////////////////////////
    public static String getRequestNameByHarc (
            HashMap<String, HexArcRequestRecord> vHexArcRequest,
            HashMap<String, HexRequestRecord>  vHexRequests,
            String vHARC_ID
    ) {
        try {
            String reqGUID = vHexArcRequest.get(vHARC_ID).getREQUEST_GUID();
            return vHexRequests.get(reqGUID).getSDCT_SDCT_ID();
        }catch (Exception e) {return "";}

    }

    ///////////////////////////

    public static String getResultPredicateGUIDByHarc (
            HashMap<String, HexArcRequestRecord> vHexArcRequest,
            HashMap<String, HexPredicateRecord>  vHexPredicates,
            String vHARC_ID
    ) {
        try {

            return vHexArcRequest.get(vHARC_ID).getPREDICATE_GUID();
        }catch (Exception e) {return "";}

    }

    ///////////////////////////
    public static String getResultPredicateNameByHarc (
            HashMap<String, HexArcRequestRecord> vHexArcRequest,
            HashMap<String, HexPredicateRecord>  vHexPredicates,
            String vHARC_ID
    ) {
        try {
            String predGUID = vHexArcRequest.get(vHARC_ID).getPREDICATE_GUID();
            return vHexPredicates.get(predGUID).getSDCT_SDCT_ID();
        }catch (Exception e) {return "";}

    }


    ////////////////////////


    public static String getConditionPredicateGUIDByHarc (
            HashMap<String, HexArcRelationRecord> vHexArcRelation,
            HashMap<String, HexPredicateRecord>  vHexPredicates,
            String vHARC_ID
    ) {
        try {

            return vHexArcRelation.get(vHARC_ID).getPREDICATE_GUID();
        }catch (Exception e) {return "";}

    }

    ///////////////////////////
    public static String getConditionPredicateNameByHarc (
            HashMap<String, HexArcRelationRecord> vHexArcRelation,
            HashMap<String, HexPredicateRecord>  vHexPredicates,
            String vHARC_ID
    ) {
        try {
            String predGUID = vHexArcRelation.get(vHARC_ID).getPREDICATE_GUID();
            return vHexPredicates.get(predGUID).getSDCT_SDCT_ID();
        }catch (Exception e) {return "";}

    }

    ///////////////////////////
    public static void getCmdAlg (HexCommandGraphRecord iht,
                                  HashMap<String, HexArcRelationRecord> relMap,
                                  HashMap<String, HexArcRequestRecord> harMap,
                                  HashMap<String, HexRequestRecord> reqMap,
                                  HashMap<String, HexPredicateRecord> prdMap,
                                  String startEdge,
                                  int Level) {

        Integer vLevel = Level;
        Set<DefaultEdge> edges = iht.getcmdAlgorithm().outgoingEdgesOf(startEdge);
        if (!(edges.isEmpty())) {
            for (DefaultEdge edge : edges) {
                String v1 = iht.getcmdAlgorithm().getEdgeSource(edge);
                String v2 = iht.getcmdAlgorithm().getEdgeTarget(edge);


                System.out.print(
                        padString(vLevel)     +
                                " "        +
                                v1         +
                                " "

                );
                System.out.print(" Cond GUID: "+getConditionPredicateGUIDByHarc(relMap,prdMap,v2));
                System.out.print(" Cond NAME: "+getConditionPredicateNameByHarc(relMap,prdMap,v2));

                System.out.print(" Request GUID: "+getRequestGUIDByHarc(harMap,reqMap,v2));
                System.out.print(" Request NAME: "+getRequestNameByHarc(harMap,reqMap,v2));

                System.out.print(" RES GUID: "+getResultPredicateGUIDByHarc(harMap,prdMap,v2));
                System.out.print(" RES NAME: "+getResultPredicateNameByHarc(harMap,prdMap,v2));

                System.out.println();

                getCmdAlg(iht, relMap, harMap,reqMap,prdMap, v2, vLevel + 1);
            }

        }

    }

    ///////////////////////////
    public static void getCmdAlg1 (HexCommandGraphRecord iht,
                                  HashMap<String, HexArcRelationRecord> relMap,
                                  HashMap<String, HexArcRequestRecord> harMap,
                                  HashMap<String, HexRequestRecord> reqMap,
                                  HashMap<String, HexPredicateRecord> prdMap,
                                  String startEdge,
                                  int Level) {

        Integer vLevel = Level;
        Set<DefaultEdge> edges = iht.getcmdAlgorithm().outgoingEdgesOf(startEdge);
        if (!(edges.isEmpty())) {
            for (DefaultEdge edge : edges) {
                String v1 = iht.getcmdAlgorithm().getEdgeSource(edge);
                String v2 = iht.getcmdAlgorithm().getEdgeTarget(edge);


                System.out.print(
                        padString(vLevel)     +
                                " "        +
                                v1         +
                                " "

                );
                System.out.print(" Cond GUID: "+getConditionPredicateGUIDByHarc(relMap,prdMap,v2));
                System.out.print(" Cond NAME: "+getConditionPredicateNameByHarc(relMap,prdMap,v2));

                System.out.print(" Request GUID: "+getRequestGUIDByHarc(harMap,reqMap,v2));
                System.out.print(" Request NAME: "+getRequestNameByHarc(harMap,reqMap,v2));

                System.out.print(" RES GUID: "+getResultPredicateGUIDByHarc(harMap,prdMap,v2));
                System.out.print(" RES NAME: "+getResultPredicateNameByHarc(harMap,prdMap,v2));

                System.out.println();

                getCmdAlg(iht, relMap, harMap,reqMap,prdMap, v2, vLevel + 1);
            }

        }

    }
}
