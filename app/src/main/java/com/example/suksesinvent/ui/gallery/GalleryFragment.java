package com.example.suksesinvent.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.adapters.GrocierPriceAdapter;
import com.example.suksesinvent.adapters.ItemSearchAdapter;
import com.example.suksesinvent.databinding.FragmentGalleryBinding;
import com.example.suksesinvent.model.GrocierPriceModel;
import com.example.suksesinvent.model.ItemsModelSales;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecyclerView rvGrocier;
    private GrocierPriceAdapter adapterGrocierPrice;
    public static ArrayList<GrocierPriceModel> dataGrocierPrice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvGrocier = binding.rvMasterGrocierPrice;

        dataGrocierPrice = new ArrayList<>();
        GrocierPriceModel p = new GrocierPriceModel();
        p.setItemCode("001");
        p.setItemQty(20.4);
        p.setItemPrice(8000);
        dataGrocierPrice.add(p);
        p.setItemCode("002");
        p.setItemQty(10.4);
        p.setItemPrice(9000);
//      dataGrocierPrice.add(p);
        p.setItemCode("003");
        p.setItemQty(30.4);
        p.setItemPrice(10000);
        dataGrocierPrice.add(p);
        p.setItemCode("004");
        p.setItemQty(40.4);
        p.setItemPrice(18000);
        dataGrocierPrice.add(p);
        adapterGrocierPrice = new GrocierPriceAdapter(getContext(),dataGrocierPrice);
        rvGrocier.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvGrocier.setAdapter(adapterGrocierPrice);
        adapterGrocierPrice.notifyDataSetChanged();
        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}