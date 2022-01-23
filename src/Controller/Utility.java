/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Assignment;
import Model.Driver;
import Model.Route;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asus
 */
public class Utility {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public int checkChoice(String mess, int a, int b) {
        int output = 0;
        try {
            do {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                if (output >= a && output <= b) {
                    break;
                }
                System.err.printf("\nPlease enter %d-%d !!", a, b);

            } while (true);
        } catch (Exception e) {
            System.err.println("Please enter integer !!!");
        }
        return output;
    }

    public String checkString(String mess) throws IOException {
        String out = "";
        try {
            do {
                System.out.print(mess);
                out = in.readLine();
                if (out.isEmpty()) {
                    System.err.println("Please Enter String");
                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) {
        }

        return out.trim();
    }

    public int checkInterger(String mess) {
        int output = 0;
        do {
            try {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                break;
            } catch (Exception e) {
                System.err.println("Please Enter Interger");
            }

        } while (true);

        return output;
    }

    protected String standardlizeString(String s) {
        String strOut = "";
        String st = "";
        st = s.trim().toLowerCase().replaceAll("\\s+", " ");

        String[] temp = new String[100];
        temp = st.split(" ");
        for (int i = 0; i <= temp.length - 1; i++) {
            strOut += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                strOut += " ";
            }
        }
        return strOut;
    }

    protected String print_driver(Driver driver) {
        return String.format("%-5d| %-20s| %-10s| %-12s| %-5s|", driver.getDriver_id(), driver.getName(), driver.getAddress(), driver.getPhone(), driver.getLevel());
    }

    protected void print_driverList(ArrayList<Driver> drivers) {
        Iterator<Driver> iter = drivers.iterator();
        System.out.println(String.format("%-5s| %-20s| %-10s| %-12s| %-5s|", "ID", "Full Name", "Address", "Phone", "Level"));
        while (iter.hasNext()) {
            System.out.println(print_driver(iter.next()));
        }
    }

    protected String print_drivers(ArrayList<Driver> drivers) {
        String out = "";
        Iterator<Driver> iter = drivers.iterator();
        out = String.format("%-5s| %-20s| %-10s| %-12s| %-5s|\n", "ID", "Full Name", "Address", "Phone", "Level");
        while (iter.hasNext()) {
            out += print_driver(iter.next()) + "\n";
        }
        return out;
    }

    protected void print_routeList(ArrayList<Route> routes) {
        Iterator<Route> i = routes.iterator();
        System.out.println(String.format("%-5s| %-10s| %-5s|", "ID", "Distance (km)", "Station"));
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
    }

    protected String print_routes(ArrayList<Route> rs) {
        String out = "";
        Iterator<Route> i = rs.iterator();
        out = String.format("%-5s| %-10s| %-5s|\n", "ID", "Distance (km)", "Station");
        while (i.hasNext()) {
            out += i.next().toString() + "\n";
        }
        return out;
    }

    protected Driver find_Driver(ArrayList<Driver> drivers) {

        do {
            int id = checkInterger("Enter driver's id: ");
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).getDriver_id() == id) {
                    return drivers.get(i);
                }
            }
            System.err.println("This driver doesn't exist in system !");
        } while (true);
    }

    protected boolean find_Driver(ArrayList<Assignment> assignments, Driver driver) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getDriver().equals(driver)) {
                return true;
            }
        }
        return false;
    }

    protected int turn(HashMap<Route, Integer> assign) {
        int sum = 0;
        Collection<Integer> values = assign.values();
        Iterator<Integer> iter = values.iterator();
        while (iter.hasNext()) {
            sum += iter.next();
        }
        return sum;
    }

    protected Route find_Route(ArrayList<Route> routes) {
        do {
            int id = checkInterger("Enter route's id: ");
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getRoute_id() == id) {
                    return routes.get(i);
                }
            }
            System.err.println("This route doesn't exist in system !");
        } while (true);
    }

    protected void print_AssignList(ArrayList<Assignment> a) {
        System.out.println("");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(print_driver(a.get(i).getDriver()));
            Set<Route> keySet = a.get(i).getAssign().keySet();
            System.out.println("Assignment list:");
            for (Route key : keySet) {
                System.out.printf("Route id: %d - %d turns\n", key.getRoute_id(), a.get(i).getAssign().get(key));
            }
            System.out.println("-------------------------------------------------------------\n");
        }
    }

    String print_Assignment(ArrayList<Assignment> as) {
        String out = "";
        for (int i = 0; i < as.size(); i++) {
            out += print_driver(as.get(i).getDriver()) + "\n";
            Set<Route> keySet = as.get(i).getAssign().keySet();
            out += "Assignment List: \n";
            for (Route key : keySet) {
                out += String.format("Route id: %d - %d turns\n", key.getRoute_id(), as.get(i).getAssign().get(key));
            }
            out += String.format("Total Distance: %d\n", total_distance(as.get(i).getAssign()));
            out += "-------------------------------------------------------------\n";
        }
        return out;
    }

    protected int total_distance(HashMap<Route, Integer> a) {
        int sum = 0;
        Set<Route> keySet = a.keySet();
        for (Route key : keySet) {
            int distance = key.getDistance() * a.get(key);
            sum += distance;
        }
        return sum;
    }

}
