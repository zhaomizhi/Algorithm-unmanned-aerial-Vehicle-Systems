/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicle.model;

import vehicle.model.helperClass.WeatherStates;

/**
 *
 * @author Aki
 */
public class WeatherReport {
    
    String state;
    
    public WeatherReport(){
        state = WeatherStates.CLEAR;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
