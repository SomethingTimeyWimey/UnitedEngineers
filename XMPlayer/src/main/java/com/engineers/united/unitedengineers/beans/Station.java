/*
UNITED ENGINEERS
Object to be created by stationlistfragment
-Needs only ID, name, and description(stream link)
*/
package com.engineers.united.unitedengineers.beans;

/**
 * Created by darren on 2017-11-18.
 */

public class Station {
    private int id;
    private String name;
    private String link;
    private String description;
    private String imageURL;

    public Station() {
        super();
    }

    public Station(int id, String name, String link ,String description, String imageURL) {
        super();
        this.id = id;
        this.link = link;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
        return "Station[id=" + id + ", name=" + name + ", link= " + link + ", description="
                + description + ", imageURL=" + imageURL + "]";
    }
}