/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.model;

import vehicle.model.helperClass.Calculator;
import vehicle.model.helperClass.CargoName;
import vehicle.model.helperClass.CityName;
import vehicle.model.helperClass.WeatherStates;

/**
 *
 * @author Aki
 */
public class ConfigureMap {

    public static void configure(Graph citys) {

        Calculator calculator = new Calculator();

        Vertex boston = new Vertex(CityName.BOSTON);
        boston.setXpoint(250);
        boston.setYpoint(170);

        Vertex newYork = new Vertex(CityName.NEW_YORK_CITY);
        newYork.setXpoint(400);
        newYork.setYpoint(30);

        Vertex Phi = new Vertex(CityName.PHILADELPHIA);
        Phi.setXpoint(520);
        Phi.setYpoint(110);

        Vertex Worcester = new Vertex(CityName.WORCESTER);
        Worcester.setXpoint(100);
        Worcester.setYpoint(130);

        Vertex Baltimoe = new Vertex(CityName.BALTIMORE);
        Baltimoe.setXpoint(80);
        Baltimoe.setYpoint(340);

        Vertex Washington = new Vertex(CityName.WASHINGTON);
        Washington.setXpoint(580);
        Washington.setYpoint(200);

        Vertex Dover = new Vertex(CityName.DOVER);
        Dover.setXpoint(700);
        Dover.setYpoint(400);

        Vertex Annapolis = new Vertex(CityName.ANNAPOLIS);
        Annapolis.setXpoint(420);
        Annapolis.setYpoint(360);

        Vertex[] defVertexs = {boston, newYork, Phi, Worcester, Baltimoe, Washington, Dover, Annapolis};

        //0 boston, 1 nyc, 2 phi, 3 worcester, 4 baltmore, 
        //5 washington, 6 dover, 7 annapolis
        //the value of the adj represents the cost time of the flight
        double adj[][] = citys.getAdj();
        
       
        adj[0][1] = 205;
        adj[0][1] = calculator.lenght(boston, newYork);
        adj[0][2] = 276;
        adj[0][3] = 155;
        adj[0][4] = 240;
        adj[0][5] = 331;
        adj[0][7] = 330;

        adj[1][0] = 205;
        adj[1][2] = 144;
        adj[1][3] = 316;
        // adj[1][4] = 445;
        adj[1][5] = 247;
        // adj[1][6] = 476;

        adj[2][0] = 275;
        adj[2][1] = 144;
        adj[2][3] = 429;
        adj[2][5] = 108;
        adj[2][7] = calculator.lenght(Phi, Annapolis);

        adj[3][0] = 155;
        adj[3][1] = 316;
        adj[3][2] = 429;
        adj[3][5] = 485;

        adj[4][1] = 445;
        adj[4][5] = 519;
        adj[4][6] = 523;
        adj[4][7] = 440;

        adj[5][2] = 108;
        adj[5][4] = 519;
        adj[5][6] = 297;

        //adj[6][1] = 476;
        adj[6][3] = 623;
        adj[6][7] = calculator.lenght(Dover, Annapolis);
        // adj[6][4] = 523;

        adj[7][0] = 330;
        adj[7][4] = calculator.lenght(Annapolis, Baltimoe);
        
        
        
        // BLOOD
        boston.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 100));
        newYork.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 200));
        Worcester.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 700));
        Washington.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 238));
        Baltimoe.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 23));
        Dover.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 2));
        Phi.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 180));
        Annapolis.getCargos().add(new Cargo(CargoName.BLOOD_SUPPLY, 649));

        boston.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 2000));
        newYork.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 435));
        Worcester.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 12));
        Washington.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 345));
        Baltimoe.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 785));
        Dover.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 332));
        Phi.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 180));
        Annapolis.getCargos().add(new Cargo(CargoName.MEDCINE_SUPPLY, 220));

        boston.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 33));
        newYork.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 4536));
        Worcester.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 352));
        Washington.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 44));
        Baltimoe.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 224));
        Dover.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 231));
        Phi.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 45));
        Annapolis.getCargos().add(new Cargo(CargoName.MEDICAL_FACILITIES, 450));

        WeatherArea wa;
        for (int x = 125; x <= 700; x = x + 80) {
            for (int y = 125; y <= 500; y = y + 80) {
                wa = new WeatherArea(x, y);
                wa.setState(WeatherStates.CLEAR);
                citys.getWeatherAreas().add(wa);
            }
        }

        citys.setAdj(adj);
        citys.setVertexList(defVertexs);

    }
    }

