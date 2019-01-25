package com.example.divyanshsingh.transportationmanagement.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.divyanshsingh.transportationmanagement.models.Location;
import com.example.divyanshsingh.transportationmanagement.models.Timing;
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
    private TextView no;
    private boolean loading;
    int previousTotal = 0,visibleThreshold =10;

    private Timing  timing;
    private String startLocation, endLocation;
    int firstVisibleItem, visibleItemCount, totalItemCount;
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
        no = view.findViewById(R.id.no);
        no.setVisibility(View.INVISIBLE);
        vehicleRecycler = view.findViewById(R.id.vehicle_recycler);
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        List<Vehicle> vehicleList = (List<Vehicle>) Objects.requireNonNull(intent.getExtras()).get("VEHICLE_LIST");
        timing = (Timing) intent.getExtras().get("TIMING");
        startLocation = (String) intent.getExtras().get("START_LCT");
        endLocation = (String) intent.getExtras().get("END_LCT");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
        vehicleRecycler.setLayoutManager(linearLayoutManager);
        if(vehicleList.size() == 0){
            no.setVisibility(View.VISIBLE);
        }
        loading = true;
        vehicleAdapter = new VehicleAdapter(getActivity(), vehicleList,this);
        vehicleRecycler.setAdapter(vehicleAdapter);

        previousTotal = 0;
        vehicleRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                            getVehicle(totalItemCount);
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem + visibleThreshold)) {

                        loading = true;
                    }
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchVehicleActivity.class);
                startActivity(intent);
                getActivity().finish();
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

    private void getVehicle(int start) {
        progressDialog.show();
        VehiclePayload vehiclePayload = new VehiclePayload(startLocation, endLocation, timing,start,10);
        RestClient.getApiInterfaceInt(getActivity()).getVehicle(vehiclePayload)
                .enqueue(new ResponseResolver<VehicleResponse>(getActivity(), false, true) {
                    @Override
                    public void success(VehicleResponse vehicleResponse) {
                        vehicleAdapter.addData(vehicleResponse.getVehicleList());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(APIError error) {
                        progressDialog.dismiss();
                    }
                });
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
