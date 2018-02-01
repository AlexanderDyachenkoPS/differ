package ru.billing.hextypes;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class HexCommandGraphRecord {
  private  DirectedGraph<String, DefaultEdge> cmdAlgorithm;

    public HexCommandGraphRecord() {
      cmdAlgorithm =
              new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
      cmdAlgorithm.addVertex("ROOT");

  }

  public void addVertex(String vertex) {
      //добавляем вершину только, если ее еще нет
      if (!(cmdAlgorithm.containsVertex(vertex))) {
      cmdAlgorithm.addVertex(vertex);}
  }


  public void addEdge(String vertex1, String vertex2) {
        cmdAlgorithm.addEdge(vertex1, vertex2);
  }

  public DirectedGraph<String, DefaultEdge> getcmdAlgorithm() {return cmdAlgorithm;}
}
