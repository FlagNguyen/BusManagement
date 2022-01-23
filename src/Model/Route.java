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
public class Route {

    private int route_id;
    private int distance;
    private int stations;

    public Route() {
    }

    public Route(int route_id, int distance, int stations) {
        this.route_id = route_id;
        this.distance = distance;
        this.stations = stations;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getStations() {
        return stations;
    }

    public void setStations(int stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-13d| %-7d|", getRoute_id(), getDistance(), getStations());
    }

}
