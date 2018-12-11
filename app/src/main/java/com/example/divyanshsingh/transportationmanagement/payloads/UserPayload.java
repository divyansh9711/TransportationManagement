package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.Vehicle;
/**
 * @author Divyansh Singh
 *
 */
public class UserPayload extends Payload {

    private Vehicle vehicle;
    private String vehicleId;
    private String vehilceRegNum;

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getVehilceRegNum() {
        return vehilceRegNum;
    }
    public void setVehilceRegNum(String vehilceRegNum) {
        this.vehilceRegNum = vehilceRegNum;
    }
}
