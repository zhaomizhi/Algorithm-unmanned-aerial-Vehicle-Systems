/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.model;

import vehicle.model.helperClass.Color;
import java.util.ArrayList;

/**
 *
 * @author Aki
 */
public class Vertex {

    private int id;
    private String cityName;
    private String color;
    private Vertex dpriviousOne;
    private Vertex dnextOne;
    private int value;

    private boolean requirementFulfilled;
    private static int counter = 0;

    private ArrayList<Cargo> cargos;
    private WeatherReport weatherReport;

    private Vertex cpriviousone;
    private double xpoint;
    private double ypoint;
    
  

    public Vertex(String name) {
        this.value = Integer.MAX_VALUE;
        this.id = counter;
        ++counter;
        color = Color.WHITE;
        this.cityName = name;
        cargos = new ArrayList<Cargo>();
        weatherReport = new WeatherReport();
        xpoint = 0;
        ypoint = 0;
    }

    public Vertex() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRequirementFulfilled() {
        return requirementFulfilled;
    }

    public void setRequirementFulfilled(boolean requirementFulfilled) {
        this.requirementFulfilled = requirementFulfilled;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Vertex.counter = counter;
    }

    public ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(ArrayList<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Vertex getDpriviousOne() {
        return dpriviousOne;
    }

    public void setDpriviousOne(Vertex dpriviousOne) {
        this.dpriviousOne = dpriviousOne;
    }

    public Vertex getDnextOne() {
        return dnextOne;
    }

    public void setDnextOne(Vertex dnextOne) {
        this.dnextOne = dnextOne;
    }

    public Vertex getCpriviousone() {
        return cpriviousone;
    }

    public void setCpriviousone(Vertex cpriviousone) {
        this.cpriviousone = cpriviousone;
    }

    public WeatherReport getWeatherReport() {
        return weatherReport;
    }

    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    public double getXpoint() {
        return xpoint;
    }

    public void setXpoint(double xpoint) {
        this.xpoint = xpoint;
    }

    public double getYpoint() {
        return ypoint;
    }

    public void setYpoint(double ypoint) {
        this.ypoint = ypoint;
    }

    
    

}
