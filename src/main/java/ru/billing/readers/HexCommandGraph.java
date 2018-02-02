package ru.billing.readers;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import ru.billing.hextypes.*;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HexCommandGraph {
    private static String cmdPa;
    private static String cmdHARC;
    private static String cmdPaGUID;
    private static HexRequests     vHexRequests;
    private static HexPredicates   vHexPredicates;
    private static HexArcRequest   vHexArcRequest;

    private static HashMap<String, HexCommanHistoryRecord> vCmdHist;
    private static HashMap<String, HexArcRelationRecord> vArcRel;



    private HexCommandGraphRecord fullHexCommandGraph;

    public HexCommandGraph(
            HashMap<String, HexCommanHistoryRecord> iCmdHist,
            HashMap<String, HexArcRelationRecord> iArcRel,
            HexRequests iHexRequests,
            HexPredicates iHexPredicates,
            HexArcRequest iHexArcRequest


    )  {
        this.vHexRequests   = iHexRequests;
        this.vHexPredicates = iHexPredicates;
        this.vHexArcRequest = iHexArcRequest;

        this.vCmdHist = iCmdHist;
        this.vArcRel  = iArcRel;

        fullHexCommandGraph = buildCommandsGraphs ();
       fillCmdAlgs();

    }


    // internal
    private HexCommandGraphRecord buildCommandsGraphs () {
        HexCommandGraphRecord cmdGraph = new HexCommandGraphRecord();
        Set set = this.vArcRel.entrySet();
        Iterator iterator = set.iterator();
        String guid;
        HexArcRelationRecord  hrel;
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            guid = mentry.getKey().toString();
            hrel = (HexArcRelationRecord) mentry.getValue();
            //пихаем в граф следующий шаг - начинаем с конца - так как направленный, следующие шаги уникальны
            cmdGraph.addVertex(hrel.getNEXT_HARC_ID());
            //пихаем в графтекущий шаг - в методе проверка - если его нет, то не вставится
            cmdGraph.addVertex(hrel.getHARC_HARC_ID());
            //теперь вставим ребро - от текущего до следующего
            cmdGraph.addEdge(hrel.getHARC_HARC_ID(),hrel.getNEXT_HARC_ID());
            //теперь проверим - если текущий шаг не замечен где-нибудь как следующий, значит это и есть первый шаг команды
            //воткнем ребро от ROOT до него
            if (!(this.vArcRel.containsKey(hrel.getHARC_HARC_ID()))) {
                cmdGraph.addEdge("ROOT",hrel.getHARC_HARC_ID());

            }
        }
     //   System.out.println(cmdGraph.getcmdAlgorithm().toString());
        return cmdGraph;
    }

    private   void getCmdAlg (
                                   String startEdge,
                                   int Level,
                                   DefaultDirectedGraph<String, DefaultEdge> cmdGraph
                                  ) {
        cmdGraph.addVertex(startEdge);
        Integer vLevel = Level;
        try {
            Set<DefaultEdge> edges = getHexCommandGraph().getcmdAlgorithm().outgoingEdgesOf(startEdge);
            if (!(edges.isEmpty())) {
                for (DefaultEdge edge : edges) {
                    String v1 = getHexCommandGraph().getcmdAlgorithm().getEdgeSource(edge);
                    String v2 = getHexCommandGraph().getcmdAlgorithm().getEdgeTarget(edge);

                    cmdGraph.addVertex(v2);
                    cmdGraph.addEdge(v1, v2);

                    getCmdAlg(v2, vLevel + 1, cmdGraph);
                }

            }
        }catch (Exception e) {
                                if (        // пустая команда без аглоритма - когда понял это, потратил половину словарного запаса
                                        e.getMessage().equals("no such vertex in graph") && Level == 1
                                    ) {
                                            System.out.println("Command without alg, START HARC_HARC_ID : " + startEdge);
                                      }
        }

    }

    private void fillCmdAlgs () {
        Set set = vCmdHist.entrySet();
        Iterator iterator = set.iterator();
        String guid;
        HexCommanHistoryRecord  cmdHistRec;

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            cmdHistRec = (HexCommanHistoryRecord) mentry.getValue();
            DefaultDirectedGraph<String, DefaultEdge> setCmdAlgorithm = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
            getCmdAlg(cmdHistRec.getHARC_HARC_ID(),1,setCmdAlgorithm);
            cmdHistRec.setCmdAlgorithm(setCmdAlgorithm);
            getCmdPaths(cmdHistRec);
           // System.out.println("===============");
           // System.out.println("number of Roads: "+cmdHistRec.getCmdRoads().size());
        }
    }

    //
    public static String getConditionRpedicateGUID (String HARC_HARC_ID)
    {
        try{
            return vArcRel.get(HARC_HARC_ID).getPREDICATE_GUID();
        }catch (Exception e) {return "";}
    }

    public static String getResultRpedicateGUID (String HARC_HARC_ID)
    {
        try{
            return vHexArcRequest.getArcRequestRecordByHARC_HARC_ID(HARC_HARC_ID).getPREDICATE_GUID();
        }catch (Exception e) {return "";}
    }

    public static String getRequestGUID (String HARC_HARC_ID)
    {
        try{
            return vHexArcRequest.getArcRequestRecordByHARC_HARC_ID(HARC_HARC_ID).getREQUEST_GUID();
           }catch (Exception e) {return "";}
    }

    public static String getString4HARC_HARC_ID (String HARC_HARC_ID) {
        return  "When: "   + getConditionRpedicateGUID(HARC_HARC_ID) +
                "Run: "    + getRequestGUID(HARC_HARC_ID) +
                "Check: "  + getResultRpedicateGUID(HARC_HARC_ID) ;

    }

    // пихаем все потроха команд в MAP


    public static void getCmdPath (HexCommanHistoryRecord iht,
                                   String startVertex,
                                   String icmdPath,
                                   String icmdPathGUID) {
        String cmdP = icmdPath;
        String cmdPGUID = icmdPathGUID;
        Set<DefaultEdge> edges = iht.getcmdAlgorithm().incomingEdgesOf(startVertex);
        if (!(edges.isEmpty())) {
            for (DefaultEdge edge : edges) {
                String v2 = iht.getcmdAlgorithm().getEdgeSource(edge);

                getCmdPath(iht,
                            v2,
                        cmdP +"->"+ v2,
                         cmdPGUID + getString4HARC_HARC_ID(v2));
            }

        } else{
            cmdPa = cmdP;
            cmdPaGUID = cmdPGUID;
        }

    }

    public static void getCmdPaths (HexCommanHistoryRecord iht) {


        Set<String> vertices = iht.getcmdAlgorithm().vertexSet();
        if (!(vertices.isEmpty())) {
            //идем по всем вершинам и проверяем - нам нужны только те, из которых нет ребер
            //они и будут последними - от них мы и пойдем до начала команды вверх
            //таким образом получим весь набор дорог в команде
            for (String vertex : vertices) {
                //нам нужны вершины, из которых ничего не выходит
                if (iht.getcmdAlgorithm().outgoingEdgesOf(vertex).isEmpty())  {
                    getCmdPath (iht,vertex,vertex,getString4HARC_HARC_ID(vertex));
                   // System.out.println (cmdPa);
                  //  System.out.println (cmdPaGUID);
                    iht.addRoad(cmdPaGUID);

                }
            }
        }
    }

    public  String getCmdRootHarcByHARC_HARC_ID (
            String startVertex) {
        internalGetCmdRootHarcByHARC_HARC_ID(startVertex);
        return cmdHARC;
    }

    private  void internalGetCmdRootHarcByHARC_HARC_ID (
                                   String startVertex) {

        Set<DefaultEdge> edges = this.fullHexCommandGraph.getcmdAlgorithm().incomingEdgesOf(startVertex);
        for (DefaultEdge edge : edges) {
            String v1 = this.fullHexCommandGraph.getcmdAlgorithm().getEdgeSource(edge);
            if (!v1.equals("ROOT")) {
                internalGetCmdRootHarcByHARC_HARC_ID(v1);
            } else {cmdHARC = startVertex;}
        }
    }

    // public
    public HexCommandGraphRecord getHexCommandGraph() {
        return this.fullHexCommandGraph;
    }


}
