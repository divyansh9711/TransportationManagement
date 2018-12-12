package com.example.divyanshsingh.transportationmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.acitivity.VehicleDetail;
import com.example.divyanshsingh.transportationmanagement.models.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleAdapterViewHolder> {

    private Context context;
    private List<Vehicle> vehicleList;
    private OnClickListener listener;
    public VehicleAdapter(Context context, List<Vehicle> vehicleList,OnClickListener listener) {
        this.context = context;
        this.vehicleList = vehicleList;
        this.listener = listener;
    }
    public interface OnClickListener{
        void onClick(Vehicle vehicle);
    }
    public void setData(List<Vehicle> list){
        vehicleList.clear();
        vehicleList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VehicleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vehicle_item, viewGroup, false);
        return new VehicleAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapterViewHolder vehicleAdapterViewHolder, int i) {
        Vehicle item = vehicleList.get(i);
        if (item != null) {
            vehicleAdapterViewHolder.vehicleName.setText(item.getVehicleName());
            if (item.getTiming() != null && item.getTiming().getStartTime() != null) {
                vehicleAdapterViewHolder.vehicleTime.setText(item.getTiming().getStartTime());
            }
            if (item.getStartLocation() != null) {
                vehicleAdapterViewHolder.origin.setText(item.getStartLocation().getTitle());
            }
            if (item.getEndLocation() != null) {
                vehicleAdapterViewHolder.destination.setText(item.getEndLocation().getTitle());
            }if(item.getTiming().getEndDate().equals("0")){
                vehicleAdapterViewHolder.day.setText("Week Day");
            }else{
                vehicleAdapterViewHolder.day.setText("Holiday");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (vehicleList == null) {
            return 0;
        }
        return vehicleList.size();
    }

    class VehicleAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleName;
        TextView vehicleTime;
        TextView origin;
        TextView destination,day;

        public VehicleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleName = itemView.findViewById(R.id.vehicles_name);
            vehicleTime = itemView.findViewById(R.id.vehicle_time);
            origin = itemView.findViewById(R.id.org);
            destination = itemView.findViewById(R.id.dest);
            day = itemView.findViewById(R.id.day);
            vehicleName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(vehicleList.get(getAdapterPosition()));
                }
            });
        }
    }
}
