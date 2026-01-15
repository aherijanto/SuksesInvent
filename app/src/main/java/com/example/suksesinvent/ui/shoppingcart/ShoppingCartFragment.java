package com.example.suksesinvent.ui.shoppingcart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suksesinvent.databinding.FragmentShoppingcartBinding;

public class ShoppingCartFragment extends Fragment {

    private ShoppingCartViewModel mViewModel;
    private FragmentShoppingcartBinding binding;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        binding = FragmentShoppingcartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(ShoppingCartViewModel.class);
        
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}