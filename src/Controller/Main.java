/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Assignment;
import Model.Driver;
import Model.Route;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Management mn = new Management();
        Utility util = new Utility();
        ArrayList<Driver> drivers = new ArrayList<>();
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();

        //Test data:
        drivers.add(new Driver(10001, "A", "Nguyen Dang Tran", "Ha Noi", "0739539256"));
        drivers.add(new Driver(10002, "B", "Nguyen Tuan Kiet", "Ha Nam", "0734579256"));
        drivers.add(drivers.size(), new Driver(10003, "C", "Hoang Tuan Kien", "Ha Tay", "934520234"));
        routes.add(new Route(101, 50, 4));
        routes.add(new Route(102, 80, 12));
        
        while (true) {            
            System.out.println("1.  Enter new driver");
            System.out.println("2.  Enter new route");
            System.out.println("3.  Assign for driver");
            System.out.println("4.  Sort");
            System.out.println("5.  Print assginment list");
            System.out.println("6.  Exit");
            
            int choice = util.checkChoice("Enter your choice: ", 1, 6);
            
            switch (choice) {
                case 1:
                    mn.add_Driver(drivers);
                    util.print_driverList(drivers);
                    mn.output_DriverFile(drivers);
                    break;
                case 2:
                    mn.add_Route(routes);
                    util.print_routeList(routes);
                    mn.output_RouteFile(routes);
                    break;
                case 3:
                    mn.assign(assignments, drivers, routes);
                    util.print_AssignList(assignments);                    
                    break;
                case 4:
                    System.out.println("1.  Sort by name");
                    System.out.println("2.  Sort by turn");
                    int c = util.checkChoice("Enter your choice: ", 1, 2);
                    switch (c) {
                        case 1:
                            mn.sort_by_name(assignments);
                            break;
                        case 2:
                            mn.sort_by_turn(assignments);
                            break;
                    }
                    break;
                case 5:
                    mn.print_AssignmenList(assignments);
                    mn.output_AssignmentFile(assignments);
                    break;
                case 6:
                    return;                
            }
        }
        
    }
    
}
