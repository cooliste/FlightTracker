package io.trackflight_app.models;

import android.app.Activity;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import io.trackflight_app.adapter.SecondAirportAdapter;

public class Airports  extends AppCompatActivity implements Serializable {
    String id;
    String name;
    String code;
    String city;

    public Airports(String id, String name, String code, String city) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airports{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }
}
