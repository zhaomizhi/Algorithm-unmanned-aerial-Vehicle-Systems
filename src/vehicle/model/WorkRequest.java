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
public class WorkRequest {

    private String destinationCityName;
    private String deliveryname;
    private Vertex pickupCargoCity;
    private String state;
    
   
    private ArrayList<Vertex> path;
    
    
    private Vertex currentCity;
    private double[] currentCitypoints;
    private double[] priviousLocationPoint ;
    
    
    private Vertex destiinationVertex;
    private Vertex nextVertex;
    
    private Vertex kpoint;
    
    private double x;
    private double y;
    
    private double k;
    private double[] lengths;
    
    private Cargo cargo;
    
    private boolean visited;
    
    private ArrayList<WeatherArea> was;
    
    private double[] startpoints;
   
    

    //initialize the city where to pick up cargo is the start point,
    //which is the curretnCity when first start
    public WorkRequest(Vertex destinationCity, String deliveryname,
            Vertex pickupcity, ArrayList<Vertex> path, Cargo cargo) {
        this.destiinationVertex = destinationCity;
        this.deliveryname = deliveryname;
        this.pickupCargoCity = pickupcity;
        this.path = path;
        this.currentCity = pickupcity;
        this.kpoint = pickupcity.getDnextOne();
        this.currentCitypoints = new double[2];
        currentCitypoints[0] = pickupcity.getXpoint();
        currentCitypoints[1] = pickupcity.getYpoint();
        this.priviousLocationPoint = new double[2];
        priviousLocationPoint[0] = pickupcity.getXpoint();
        priviousLocationPoint[1] = pickupcity.getYpoint();
        this.cargo = cargo;
        this.visited = false;
        this.was = new ArrayList<>();
        this.nextVertex = new Vertex();
        this.startpoints = new double[2];
        
    }

    public Vertex getDestiinationVertex() {
        return destiinationVertex;
    }

    public void setDestiinationVertex(Vertex destiinationVertex) {
        this.destiinationVertex = destiinationVertex;
    }
    
    

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname;
    }

    public Vertex getPickupCargoCity() {
        return pickupCargoCity;
    }

    public void setPickupCargoCity(Vertex pickupCargoCity) {
        this.pickupCargoCity = pickupCargoCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Vertex> getPath() {
        return path;
    }

    public void setPath(ArrayList<Vertex> path) {
        this.path = path;
    }

    public Vertex getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(Vertex currentCity) {
        this.currentCity = currentCity;
    }

    public Vertex getKpoint() {
        return kpoint;
    }

    public void setKpoint(Vertex kpoint) {
        this.kpoint = kpoint;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double[] getLengths() {
        return lengths;
    }

    public void setLengths(double[] lengths) {
        this.lengths = lengths;
    }

    public double[] getCurrentCitypoints() {
        return currentCitypoints;
    }

    public void setCurrentCitypoints(double[] currentCitypoints) {
        this.currentCitypoints = currentCitypoints;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double[] getPriviousLocationPoint() {
        return priviousLocationPoint;
    }

    public void setPriviousLocationPoint(double[] priviousLocationPoint) {
        this.priviousLocationPoint = priviousLocationPoint;
    }

    
    public void setPriviousPoint(double x, double y){
        this.priviousLocationPoint[0] = x;
        this.priviousLocationPoint[1] = y;
    }

    public ArrayList<WeatherArea> getWas() {
        return was;
    }

    public void setWas(ArrayList<WeatherArea> was) {
        this.was = was;
    }

    public Vertex getNextVertex() {
        return nextVertex;
    }

    public void setNextVertex(Vertex nextVertex) {
        this.nextVertex = nextVertex;
    }

    public double[] getStartpoints() {
        return startpoints;
    }

    public void setStartpoints(double[] startpoints) {
        this.startpoints = startpoints;
    }

    
    
}
