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
public class WorkQueue {
    ArrayList<WorkRequest> workqueue; 
    
    public WorkQueue (){
        workqueue = new ArrayList<>();
    }

    public ArrayList<WorkRequest> getWorkqueue() {
        return workqueue;
    }

    public void setWorkqueue(ArrayList<WorkRequest> workqueue) {
        this.workqueue = workqueue;
    }
    
    
}
