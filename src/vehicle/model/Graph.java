/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.model;

import java.util.ArrayList;

/**
 *
 * @author Aki
 */
public class Graph {

    int vertexNumber;
    Vertex[] vertexList;
    double adj[][];

    Vertex greateSupplyCity;
    int greateQun;

    ArrayList<WeatherArea> weatherAreas;

    //initialize graph
    //all the vertex are new vertex with id
    //they do not have connection
    public Graph(int vertexNumber) {
        this.vertexList = new Vertex[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            vertexList[i] = new Vertex();
        }
        adj = new double[vertexNumber][vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
                adj[i][j] = 0;
            }
        }
        weatherAreas = new ArrayList<>();
    }

    public Vertex[] getVertexList() {
        return vertexList;
    }

    public void setVertexList(Vertex[] vertexList) {
        this.vertexList = vertexList;
    }

    public double[][] getAdj() {
        return adj;
    }

    public void setAdj(double[][] adj) {
        this.adj = adj;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    public Vertex getGreateSupplyCity() {
        return greateSupplyCity;
    }

    public void setGreateSupplyCity(Vertex greateSupplyCity) {
        this.greateSupplyCity = greateSupplyCity;
    }

    public int getGreateQun() {
        return greateQun;
    }

    public void setGreateQun(int greateQun) {
        this.greateQun = greateQun;
    }

    public ArrayList<WeatherArea> getWeatherAreas() {
        return weatherAreas;
    }

    public void setWeatherAreas(ArrayList<WeatherArea> weatherAreas) {
        this.weatherAreas = weatherAreas;
    }

 
    
  

}
