package com.example.divyanshsingh.transportationmanagement.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divyanshsingh.transportationmanagement.API.APIError;
import com.example.divyanshsingh.transportationmanagement.API.ResponseResolver;
import com.example.divyanshsingh.transportationmanagement.API.RestClient;
import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.acitivity.DashboardActivity;
import com.example.divyanshsingh.transportationmanagement.acitivity.SearchVehicleActivity;
import com.example.divyanshsingh.transportationmanagement.acitivity.VehicleDetail;
import com.example.divyanshsingh.transportationmanagement.adapters.VehicleAdapter;
import com.example.divyanshsingh.transportationmanagement.models.Vehicle;
import com.example.divyanshsingh.transportationmanagement.payloads.RoutePayload;
import com.example.divyanshsingh.transportationmanagement.payloads.VehiclePayload;
import com.example.divyanshsingh.transportationmanagement.response.RouteResponse;
import com.example.divyanshsingh.transportationmanagement.response.VehicleResponse;
import com.example.divyanshsingh.transportationmanagement.utils.CommonProgressDialog;

import java.util.List;
import java.util.Objects;

public class SearchResultFragment extends Fragment implements VehicleAdapter.OnClickListener {

    private RecyclerView vehicleRecycler;
    private ImageView search;
    private VehicleAdapter vehicleAdapter;
    private Dialog progressDialog;


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        progressDialog = CommonProgressDialog.LoadingSpinner(getContext());
        search = view.findViewById(R.id.search);
        vehicleRecycler = view.findViewById(R.id.vehicle_recycler);
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        List<Vehicle> vehicleList = (List<Vehicle>) Objects.requireNonNull(intent.getExtras()).get("VEHICLE_LIST");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        vehicleRecycler.setLayoutManager(linearLayoutManager);
        vehicleAdapter = new VehicleAdapter(getActivity(), vehicleList,this);
        vehicleRecycler.setAdapter(vehicleAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchVehicleActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void setData(List<Vehicle> list) {
        if (vehicleAdapter != null) {
            vehicleAdapter.setData(list);
        }else{
            vehicleAdapter = new VehicleAdapter(getActivity(), list,this);
            vehicleRecycler.setAdapter(vehicleAdapter);
        }
    }

    @Override
    public void onClick(Vehicle vehicle) {
        getSubs(vehicle);
    }
    private void getSubs(final Vehicle vehicle){
        progressDialog.show();
        RoutePayload routePayload = new RoutePayload(vehicle.getRoute().getuId());
        RestClient.getApiInterfaceInt(Objects.requireNonNull(getContext())).getSubs(routePayload)
                .enqueue(new ResponseResolver<RouteResponse>(getActivity(), false, true) {
                    @Override
                    public void success(RouteResponse routeResponse) {
                        Intent intent = new Intent(getActivity(), VehicleDetail.class);
                        progressDialog.dismiss();
                        vehicle.setSub(routeResponse.getRouteList());
                        intent.putExtra("VEHICLE",vehicle);
                        startActivity(intent);

                    }

                    @Override
                    public void failure(APIError error) {
                        progressDialog.dismiss();
                    }
                });
    }
}
