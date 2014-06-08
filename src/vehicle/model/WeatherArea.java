/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicle.model;

/**
 *
 * @author Aki
 */
public class WeatherArea {
    
    int x;
    int y;
    int r;
    String state;
    double d;

    public WeatherArea(int x, int y){
        this.x = x;
        this.y = y;
        this.r = 40;
        this.d = r*1.1;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
    
    
}
