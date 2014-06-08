/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.model.helperClass;

import vehicle.model.Graph;
import vehicle.model.Vertex;
import vehicle.model.WeatherArea;

/**
 *
 * @author Aki
 */
public class Calculator {

    public double lenght(Vertex one, Vertex two) {
        double x = (Math.abs(one.getXpoint() - two.getXpoint()));
        double y = (Math.abs(one.getYpoint() - two.getYpoint()));
        double pingfangx = Math.pow(x, 2);
        double pingfangy = Math.pow(y, 2);
        double length = Math.pow(pingfangy + pingfangx, 0.5);
        return (int) length;
    }

    public double perpendiculaDdistance(double x1, double x2, double y1, double y2, WeatherArea wa) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double divisor = Math.pow((Math.pow(dy, 2) + Math.pow(dx, 2)), 0.5);
        double dividend = Math.abs(dx * wa.getX() - dx * wa.getY() - x1 * y2 + x2 * y1);
        return dividend / divisor;
    }

    public double[] givenLengthpoint(double[] startpoints, double[] endpoints, WeatherArea wa) {
        double k1 = (endpoints[1] - startpoints[1]) / (endpoints[0] - startpoints[0]);
        double k2 = -1 / k1;
        double[] newpoint = new double[2];
        double divisor = Math.pow((Math.pow(k2, 2) + 1), 0.5);
        newpoint[0] = wa.getX() - (wa.getD() / divisor);
        newpoint[1] = wa.getY() - (wa.getD() * k2 / divisor);

        return newpoint;

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double[] avoidMovingLength(double[] startpoints, double[] endpoints) {
        double[] len = new double[2];
        len[0] = (endpoints[0] - startpoints[0]) * 1;
        len[1] = (endpoints[1] - startpoints[1]) * 1;
        return len;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public double randomWeight(){
        int min =1;
        int max = 100;
        double weight =min + (double)(Math.random() * ((max - min) + 1));       
        return weight;       
    }

    public void changeWeight(Graph citymap) {
        
        double adj[][] = citymap.getAdj();
         
        adj[0][1] = randomWeight();
        adj[0][1] = randomWeight();
        adj[0][2] = randomWeight();
        adj[0][3] = randomWeight();
        adj[0][4] = randomWeight();
        adj[0][5] = randomWeight();
        adj[0][7] = randomWeight();
        
        adj[1][0] = randomWeight();
        adj[1][2] = randomWeight();
        adj[1][3] = randomWeight();
        adj[1][5] = randomWeight();
         
        adj[2][0] = randomWeight();
        adj[2][1] = randomWeight();
        adj[2][3] = randomWeight();
        adj[2][5] = randomWeight();
        adj[2][7] = randomWeight();
        
        adj[3][0] = randomWeight();
        adj[3][1] = randomWeight();
        adj[3][2] = randomWeight();
        adj[3][5] = randomWeight();

        adj[4][1] = randomWeight();
        adj[4][5] = randomWeight();
        adj[4][6] = randomWeight();
        adj[4][7] = randomWeight();

        adj[5][2] = randomWeight();
        adj[5][4] = randomWeight();
        adj[5][6] = randomWeight();
        
        adj[6][3] = randomWeight();
        adj[6][7] = randomWeight();
        
        adj[7][0] = randomWeight();
        adj[7][4] = randomWeight();
    }

 
    
}
