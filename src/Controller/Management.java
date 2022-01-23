/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Assignment;
import Model.Driver;
import Model.Route;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author asus
 */
public class Management {

    Utility util = new Utility();

    protected void add_Driver(ArrayList<Driver> drivers) throws IOException {
        int id = 0;
        if (drivers.isEmpty()) {
            id = 10001;
        } else {
            id = drivers.get(drivers.size() - 1).getDriver_id() + 1;
        }

        String name = util.standardlizeString(util.checkString("Enter driver's name: "));
        String address = util.standardlizeString(util.checkString("Enter driver's address: "));
        String phone = "";
        do {
            phone = util.checkString("Enter driver's phonenumber: ");
            if (phone.matches("[0-9]+") && phone.length() >= 5 && phone.length() <= 10) {
                break;
            }
            System.err.println("Phone number must be integer and has length 5-10");
        } while (true);

        String level = "";

        do {
            level = util.checkString("Enter driver's level (A-F): ");
            if (level.matches("[a-fA-F]{1}")) {
                level = level.toUpperCase();
                break;
            }
            System.err.println("Level must be A-F");
        } while (true);

        drivers.add(new Driver(id, level, name, address, phone));
    }

    protected void add_Route(ArrayList<Route> routes) {
        int id = 0;
        if (routes.isEmpty()) {
            id = 101;
        } else {
            id = routes.get(routes.size() - 1).getRoute_id() + 1;
        }

        int distance = util.checkInterger("Enter distance of this route: ");
        int station = util.checkInterger("Enter number station of this route: ");

        routes.add(new Route(id, distance, station));
    }

    protected void assign(ArrayList<Assignment> assignments, ArrayList<Driver> drivers, ArrayList<Route> routes) {

        Driver driver = util.find_Driver(drivers);
        System.out.println("Driver's Info: ");
        System.out.println(util.print_driver(driver));

        int pos = 0;
        if (util.find_Driver(assignments, driver)) {
            for (int i = 0; i < assignments.size(); i++) {
                if (assignments.get(i).getDriver().equals(driver)) {
                    if (util.turn(assignments.get(i).getAssign()) < 15) {
                        System.out.printf("This driver has been assigned %d turn in this day.\n", util.turn(assignments.get(i).getAssign()));
                        Route route = util.find_Route(routes);
                        if (assignments.get(i).getAssign().containsKey(route)) {
                            int turn = util.checkChoice("Enter turn your want to change: ", 1, 15 - util.turn(assignments.get(i).getAssign()));
                            assignments.get(i).getAssign().replace(route, turn);
                        } else {
                            int turn = util.checkChoice("Enter turn of this route: ", 1, 15 - util.turn(assignments.get(i).getAssign()));
                            assignments.get(i).getAssign().put(route, turn);
                        }
                        break;
                    } else {
                        System.out.println("This driver can't be assigned more route !");
                        break;
                    }
                }
            }
        } else {
            Route route = util.find_Route(routes);
            int turn = util.checkChoice("Enter turn of this route: ", 1, 15);

            HashMap<Route, Integer> assign = new HashMap<>();
            assign.put(route, turn);
            assignments.add(new Assignment(driver, assign));
        }
    }

    protected void sort_by_name(ArrayList<Assignment> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).getDriver().getName().compareTo(a.get(j).getDriver().getName()) > 0) {
                    Assignment temp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, temp);
                }
            }
        }
        util.print_AssignList(a);
    }

    protected void sort_by_turn(ArrayList<Assignment> a) {
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (util.turn(a.get(i).getAssign()) > util.turn(a.get(j).getAssign())) {
                    Assignment temp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, temp);
                }
            }
        }
        util.print_AssignList(a);
    }

    protected void print_AssignmenList(ArrayList<Assignment> a) {
        System.out.printf("%-20s| %-10s|\n", "Driver Name", "Distance");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%-20s| %-10d|\n", a.get(i).getDriver().getName(), util.total_distance(a.get(i).getAssign()));
        }
    }

    protected void output_DriverFile(ArrayList<Driver> ds) throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("Driver List.txt");
        try (PrintWriter out = new PrintWriter(fo)) {
            out.print(util.print_drivers(ds));
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    protected void output_RouteFile(ArrayList<Route> rs) throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("Route List.txt");
        try (PrintWriter out = new PrintWriter(fo)) {
            out.print(util.print_routes(rs));
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    protected void output_AssignmentFile(ArrayList<Assignment> as) throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("Assignment List.txt");
        try (PrintWriter out = new PrintWriter(fo)) {
            out.print(util.print_Assignment(as));
        } catch (Exception e) {
            System.err.println("Error");
        }
    }
}
