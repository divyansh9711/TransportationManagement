package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.Location;
/**
 * @author Divyansh Singh
 *
 */
public class LocationPayload extends Payload {
    private Location location;
    private String locationId;
    private String zipCode;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
