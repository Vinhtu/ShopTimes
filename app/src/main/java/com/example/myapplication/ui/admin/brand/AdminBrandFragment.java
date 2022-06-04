package com.example.myapplication.ui.admin.brand;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.RetrofitClient;
import com.example.myapplication.data.requests.BrandRequest;
import com.example.myapplication.data.responses.BrandResponse;
import com.example.myapplication.data.responses.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBrandFragment extends Fragment {
    private static AdminBrandAdapter brandAdapter;
    private static List<BrandResponse> brands;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_brand, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvBrands = view.findViewById(R.id.brand_list);
        brands = new ArrayList<>();
        brandAdapter = new AdminBrandAdapter(brands);
        rvBrands.setAdapter(brandAdapter);
        rvBrands.setLayoutManager(new LinearLayoutManager(getActivity()));

        getBrands();
    }

    public void getBrands() {
        Call<List<BrandResponse>> call = RetrofitClient.getBrandService().GetAll();

        call.enqueue(new Callback<List<BrandResponse>>() {
            @Override
            public void onResponse(Call<List<BrandResponse>> call, Response<List<BrandResponse>> response) {
                if (response.isSuccessful()) {
                    brands.clear();
                    brands.addAll(response.body());
                    brandAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BrandResponse>> call, Throwable t) {
                Log.d("BRAND_ERROR", t.getLocalizedMessage());
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void updateBrand(BrandResponse brand, BrandRequest request, int position) {
        Call<BrandResponse> call = RetrofitClient.getBrandService().Update(brand.getId(), request);
        call.enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()) {
                    brands.set(position, response.body());
                    brandAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {

            }
        });
    }

    public static void deleteBrand(String id, int position) {
        Call<ResponseMessage> call = RetrofitClient.getBrandService().Delete(id);
        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    brands.remove(position);
                    brandAdapter.notifyItemRemoved(position);
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
            }
        });
    }
}