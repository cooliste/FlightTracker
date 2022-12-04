package io.trackflight_app.models;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public  class Flights  extends AppCompatActivity implements Serializable {

    public long id;
    public String airline;
    public String flight_number;
    public String departure_city;
    public String arrival_city;
    public String departure_time;
    public String arrival_time;
    public String departure_day;
    public String arrival_day;
    public String departure_airport;
    public String arrival_airport;



    public  Flights(long id, String airline,String flight_number,String departure_city,String arrival_city,
                    String departure_time,String arrival_time,String departure_day,String arrival_day,
                    String departure_airport,String arrival_airport){
        this.id=id;
        this.airline=airline;
        this.flight_number=flight_number;
        this.departure_city=departure_city;
        this.arrival_city=arrival_city;
        this.departure_time=departure_time;
        this.arrival_time=arrival_time;
        this.departure_day=departure_day;
        this.arrival_day=arrival_day;
        this.departure_airport=departure_airport;
        this.arrival_airport=arrival_airport;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "airline='" + airline + '\'' +
                ", flight_number='" + flight_number + '\'' +
                ", departure_city='" + departure_city + '\'' +
                ", arrival_city='" + arrival_city + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", departure_day='" + departure_day + '\'' +
                ", arrival_day='" + arrival_day + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", arrival_airport='" + arrival_airport + '\'' +
                '}';
    }
}
