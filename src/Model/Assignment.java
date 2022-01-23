/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author asus
 */
public class Assignment {

    private Driver driver;
    private HashMap<Route, Integer> assign;

    public Assignment() {
    }

    public Assignment(Driver driver, HashMap<Route, Integer> assign) {
        this.driver = driver;
        this.assign = assign;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public HashMap<Route, Integer> getAssign() {
        return assign;
    }

    public void setAssign(HashMap<Route, Integer> assign) {
        this.assign = assign;
    }

}
