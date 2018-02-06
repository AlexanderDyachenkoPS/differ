package ru.billing.hextypes;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;

public class HexCommanHistoryRecord implements Comparable<HexCommanHistoryRecord> {
    // стартовая точка аглоритма
    private String HARC_HARC_ID;

    private String ENTITY_GUID;

    // алгоритм команды
    private DirectedGraph<String, DefaultEdge> cmdAlgorithm;
    //cписок список всех путей в алгоритме команды
    //его как раз и будем использовать для сравнения алгоритмов
    private ArrayList<String> cmdRoads;

    public boolean isRoadExists(String iRoad) {
        //compare cmd Algorithm

        for (int i=0; i<this.getCmdRoads().size(); i++)
        {
            if (this.getCmdRoads().get(i).equals(iRoad)) {return true;}
        }
        return false;


    }

    public HexCommanHistoryRecord(String iENTITY_GUID,
                           String iHARC_HARC_ID)
    {
        this.HARC_HARC_ID                   = iHARC_HARC_ID;
        this.ENTITY_GUID                    = iENTITY_GUID;
        cmdRoads = new ArrayList<String>();
    }
    public String  getHARC_HARC_ID ()
        {return this.HARC_HARC_ID;}

    public void setCmdAlgorithm (DirectedGraph<String, DefaultEdge> cmdGraph)
    {
        this.cmdAlgorithm = cmdGraph;
    }

    public DirectedGraph<String, DefaultEdge> getcmdAlgorithm() {
        return cmdAlgorithm;
    }

    public void addRoad (String iRoad) {
        cmdRoads.add(iRoad);
    }

    public ArrayList<String> getCmdRoads () {
        return cmdRoads;
    }

    public String getENTITY_GUID() {
        return ENTITY_GUID;
    }

    @Override
    public int compareTo(HexCommanHistoryRecord otherHexCommanHistoryRecord) {
        //compare cmd Algorithm
        String road;
        for (int i=0; i<this.getCmdRoads().size(); i++)
        {
            road = this.getCmdRoads().get(i);
            if (!(otherHexCommanHistoryRecord.isRoadExists(road))) {return -1;}
        }
        return 0;


    }

}
