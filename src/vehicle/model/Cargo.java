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
public class Cargo {

    private String name;
    private int quantity;
  

    public Cargo(String name,int qu) {
        this.name = name;
        quantity = qu;
      
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
    

}
