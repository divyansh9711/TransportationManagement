package com.example.divyanshsingh.transportationmanagement.payloads;

import com.example.divyanshsingh.transportationmanagement.models.Timing;
import com.example.divyanshsingh.transportationmanagement.models.Vehicle;
/**
 * @author Divyansh Singh
 *
 */
public class VehiclePayload extends Payload {

    private Vehicle vehicle;
    private String vehicleId;
    private String vehilceRegNum;
    private Timing timing;
    private String startLocation;
    private String endLocation;


    public VehiclePayload() {
        super();
    }

    public VehiclePayload(String startLocation ,String endLocation , Timing timing,int start , int limit){
        super(start,limit);
        this.startLocation = startLocation;
        this.timing = timing;
        this.endLocation = endLocation;
    }

    public Timing getTiming() {
        return timing;
    }
    public void setTiming(Timing timing) {
        this.timing = timing;
    }
    public String getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }
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
