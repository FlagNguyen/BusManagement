/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author asus
 */
public class Driver extends Person {

    private int driver_id;
    private String level;

    public Driver(int driver_id, String level, String name, String address, String phone) {
        super(name, address, phone);
        this.driver_id = driver_id;
        this.level = level;
    }

    public Driver(int driver_id, String level) {
        this.driver_id = driver_id;
        this.level = level;
    }

    public Driver() {
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
