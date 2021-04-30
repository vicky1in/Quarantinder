package com.groun24.quarantinder.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int locationID;

	@Column
	private String city;

	@Column
	private String country;

	@Column
    private double latitude;
    
    @Column
    private double longitude;


	public Location() {
		// default constructor
	}

	public int getLocationID() {
        return this.locationID;
    }
    public void setLocationID(int id) {
        this.locationID = id;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double lng) {
        this.longitude = lng;
    }
}
