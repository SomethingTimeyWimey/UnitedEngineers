/*
* Object to be created by stationlistfragment
* -Needs only ID, name, and description(stream link)
* */
package com.engineers.united.unitedengineers.beans;

/**
 * Created by darren on 2017-11-18.
 */

public class Station {
    private int id;
    private String name;
    private String description;

    public Station() {
        super();
    }

    public Station(int id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Station other = (Station) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Station[id=" + id + ", name=" + name + ", description="
                + description + "]";
    }
}