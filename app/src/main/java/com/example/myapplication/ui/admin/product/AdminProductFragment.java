package com.example.myapplication.ui.admin.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.AdminFragmentProductBinding;
import com.example.myapplication.databinding.FragmentCartBinding;

public class AdminProductFragment extends Fragment {

    private AdminFragmentProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AdminProductViewModel cartViewModel =
                new ViewModelProvider(this).get(AdminProductViewModel.class);

        binding = AdminFragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}