package com.example.myapplication.ui.admin.brand;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.RetrofitClient;
import com.example.myapplication.data.requests.BrandRequest;
import com.example.myapplication.data.responses.BrandResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminBrandAdapter extends RecyclerView.Adapter<AdminBrandAdapter.ViewHolder> {
    public List<BrandResponse> brands;

    public AdminBrandAdapter(List<BrandResponse> brands) {
        this.brands = brands;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View brandView = inflater.inflate(R.layout.brand_item, parent, false);
        return new ViewHolder(brandView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrandResponse brand = brands.get(position);
        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(brand.getName());

        holder.editButton.setOnClickListener(view -> {
            LayoutInflater inflater = LayoutInflater.from(view.getContext());
            final View updateView = inflater.inflate(R.layout.brand_update_dialog, null);
            AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
            alertDialog.setView(updateView);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Lưu", (dialog, which) -> {
                EditText editText = alertDialog.findViewById(R.id.brand_update_edit_text);
                BrandRequest request = new BrandRequest();
                request.Name = editText.getText().toString();

                AdminBrandFragment.updateBrand(brand, request, position);
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Huỷ", ((dialog, which) -> {

            }));

            alertDialog.show();
        });

        holder.deleteButton.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
            alertDialog.setMessage("Bạn có chắc muốn xoá thương hiệu này không ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có", (dialog, which) -> {
                AdminBrandFragment.deleteBrand(brand.Id, position);
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không", ((dialog, which) -> {

            }));

            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.brand_name);
            editButton = itemView.findViewById(R.id.brand_btn_update);
            deleteButton = itemView.findViewById(R.id.brand_btn_delete);
        }
    }
}
