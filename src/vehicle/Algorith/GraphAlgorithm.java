package vehicle.Algorith;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import vehicle.model.Cargo;

import vehicle.model.Graph;
import vehicle.model.Vertex;
import vehicle.model.helperClass.Color;

/**
 *
 * @author Aki
 */
public class GraphAlgorithm {

    public ArrayList<Vertex> ShorttestPath(Graph graph, Vertex startPoint) {
        for (Vertex v : graph.getVertexList()) {
            if (v == startPoint) {
                v.setValue(0);
            } else {
                v.setValue(Integer.MAX_VALUE);
            }
        }
        ArrayList<Vertex> S = new ArrayList<>();
        ArrayList<Vertex> vertexlistQ = new ArrayList<>();
        for (Vertex v : graph.getVertexList()) {
            vertexlistQ.add(v);
        }
        while (!vertexlistQ.isEmpty()) {
            Vertex u = extractMin(vertexlistQ);
            S.add(u);
            for (Vertex v : vertexlistQ) {
                if (graph.getAdj()[u.getId()][v.getId()] != 0) {
                    relax(u, v, graph);
                }
            }
        }
        return S;
    }

    public void relax(Vertex u, Vertex v, Graph mg) {
        if (v.getValue() > u.getValue() + mg.getAdj()[u.getId()][v.getId()]) {
            v.setValue((int) (u.getValue() + mg.getAdj()[u.getId()][v.getId()]));
            v.setDpriviousOne(u);

        }

    }

    public Vertex extractMin(ArrayList vertexList) {
        int smallV = 1000000;
        Vertex samllestV = null;
        for (Object v : vertexList) {
            if (((Vertex) v).getValue() < smallV) {
                smallV = ((Vertex) v).getValue();
                samllestV = (Vertex) v;
            }
        }

        for (Object v : vertexList) {
            if (((Vertex) v).getValue() == smallV) {
                vertexList.remove(v);
                break;
            }
        }
        return samllestV;
    }

    public void DFSGreatestSupply(Graph graph, String cargoname) {

        for (Vertex u : graph.getVertexList()) {
            if (u.getColor() == Color.WHITE) {
                DFSVisit(u, cargoname, graph);
            }
        }
    }

    public void DFSVisit(Vertex u, String cargoname, Graph graph) {

         //Vertex greatestSupply = null;
        u.setColor(Color.GREY);
        for (Vertex v : graph.getVertexList()) {
            if (graph.getAdj()[u.getId()][v.getId()] != 0) {
                if (v.getColor() == Color.WHITE) {
                    v.setCpriviousone(v);
                    DFSVisit(v, cargoname, graph);
                }
            }
        }
        u.setColor(Color.BLACK);
        // System.out.println(u.getCityName());
        for (Cargo carg : u.getCargos()) {
            if (carg.getName() == cargoname) {
                if (carg.getQuantity() > graph.getGreateQun()) {
                    graph.setGreateQun(carg.getQuantity());                     //greatestSupply = u;
                    graph.setGreateSupplyCity(u);
                }
            }
        }
    }

    public Vertex findDestinatin(String cityname, Vertex[] path) {
        for (Vertex destination : path) {
            if (destination.getCityName() == cityname) {
                return destination;
            }
        }
        return null;
    }

    public ArrayList<Vertex> findCertainPath(ArrayList<Vertex> wholepath, String destination, String startpoint) {
        ArrayList<Vertex> path = new ArrayList<>();

        for (Vertex v : wholepath) {
            if (v.getCityName() == destination) {
                while (v.getCityName() != startpoint) {
                    path.add(v);
                    v = v.getDpriviousOne();
                }
                path.add(v);
            }
        }
        return path;

    }

    public Vertex searchForVertex(Graph citymap, String name) {
        for (Vertex vertex : citymap.getVertexList()) {
            if (vertex.getCityName().equals(name)) {
                return vertex;
            }
        }
        return null;
    }
    
    
    public Vertex searchForVertex(Graph citymap, double[] points){
        for(Vertex v: citymap.getVertexList()){
            if(v.getXpoint()== points[0] && v.getYpoint()== points[1]){
                return v;
            }
        }
        return null;
    }

    public int calculateVertexCity(ArrayList<String> pathnames, Graph ga) {
        int i = 0;
        for (Vertex v : ga.getVertexList()) {
            for (String name : pathnames) {
                if (name.equals(v.getCityName())) {

                    i++;
                }
            }
        }
        return i;
    }
}
