package com.example.divyanshsingh.transportationmanagement.fragments;

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
import android.widget.TextView;

import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.acitivity.VehicleDetail;
import com.example.divyanshsingh.transportationmanagement.adapters.VehicleAdapter;
import com.example.divyanshsingh.transportationmanagement.models.Vehicle;

import java.util.List;
import java.util.Objects;

public class SearchResultFragment extends Fragment {

    private RecyclerView vehicleRecycler;
    private VehicleAdapter vehicleAdapter;
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
        vehicleRecycler = view.findViewById(R.id.vehicle_recycler);
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        List<Vehicle> vehicleList = (List<Vehicle>) Objects.requireNonNull(intent.getExtras()).get("VEHICLE_LIST");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),1,false);
        vehicleRecycler.setLayoutManager(linearLayoutManager);
        vehicleAdapter = new VehicleAdapter(getActivity(),vehicleList);
        vehicleRecycler.setAdapter(vehicleAdapter);
        return view;
    }


}
