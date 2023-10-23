package com.example.suksesinvent.ui.gallery;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.R;
import com.example.suksesinvent.adapters.GrocierPriceAdapter;
import com.example.suksesinvent.adapters.ItemSearchAdapter;
import com.example.suksesinvent.databinding.FragmentGalleryBinding;
import com.example.suksesinvent.model.GrocierPriceModel;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    public RecyclerView rvGrocier;
    private GrocierPriceAdapter adapterGrocierPrice;
    public static ArrayList<GrocierPriceModel> dataGrocierPrice = new ArrayList<>();
    public ArrayList<GrocierPriceModel> _dataArrayGrocierPrice = new ArrayList<>();
    private String itemCodeString = "";
    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvGrocier = binding.rvMasterGrocierPrice;
        Button btnAddGrocier = binding.btnGrocierPrice;
        EditText itemCode_ = binding.masterTxtItemCode;
        EditText itemName_ = binding.masterTxtItemName;
        EditText buyingPrice_ = binding.masterTxtBuyingPrice;
        EditText sellingPrice_ = binding.masterTxtSellingPrice;
        EditText itemUnit_ = binding.masterTxtSatuan;
        btnAddGrocier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _itemCode = itemCode_.getText().toString();
                String _itemName = itemName_.getText().toString();
                String _buyingPrice = buyingPrice_.getText().toString();
                String _sellingPrice = sellingPrice_.getText().toString();
                String _itemUnit = itemUnit_.getText().toString();

                if(_itemName.matches("") || _itemUnit.matches("") || _buyingPrice.matches("") || _sellingPrice.matches("")){
                    Toast.makeText(getContext(), "Kolom harus diisi...",
                            Toast.LENGTH_LONG).show();
                }else {
                    LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());

                    if (_itemCode.matches("")){
                        itemCodeString = firstLetterWord(_itemName);
                    }else{
                        itemCodeString = _itemCode;
                    }

                    View mimoView = inflater.inflate(R.layout.dialog_grocier, null);
                    dialog.setView(mimoView);
                    dialog.setCancelable(false);
                    EditText inputQTYMin = (EditText) mimoView.findViewById(R.id.input_qty_min);
                    EditText inputPriceGrocier = (EditText) mimoView.findViewById(R.id.input_price);
                    dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String _qtyMin = inputQTYMin.getText().toString();
                            String _priceGrocier = inputPriceGrocier.getText().toString();
                            if(_qtyMin.matches("")||_priceGrocier.matches("")){
                                Toast.makeText(getContext(), "Kolom harus QTY dan HARGA harus diisi...",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                GrocierPriceModel p = new GrocierPriceModel();
                                p.setItemQty(Double.parseDouble(inputQTYMin.getText().toString()));
                                p.setItemPrice(Integer.parseInt(inputPriceGrocier.getText().toString()));
                                dataGrocierPrice.add(p);
                                rvGrocier.setAdapter(new GrocierPriceAdapter(getContext(), dataGrocierPrice));
                            }
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }
            }
        });

        adapterGrocierPrice = new GrocierPriceAdapter(getContext(),dataGrocierPrice);
        rvGrocier.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
        rvGrocier.setAdapter(adapterGrocierPrice);
        adapterGrocierPrice.notifyDataSetChanged();
        if (savedInstanceState == null) {
//            btnSearchItem.setOnClickListener( new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    new HomeFragment.FetchItemList().execute();
//                }
//            });
        } else {
            //ArrayList parcelRecipe = savedInstanceState.getParcelableArrayList(MY_KEY);
            rvGrocier.setAdapter(new GrocierPriceAdapter(getContext(), dataGrocierPrice));
        }
        return root;
    }
//
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    static String firstLetterWord(String str)
    {
        String result = "";

        // Traverse the string.
        boolean v = true;
        for (int i = 0; i < str.length(); i++)
        {
            // If it is space, set v as true.
            if (str.charAt(i) == ' ')
            {
                v = true;
            }

            // Else check if v is true or not.
            // If true, copy character in output
            // string and set v as false.
            else if (str.charAt(i) != ' ' && v == true)
            {
                result += (str.charAt(i));
                v = false;
            }
        }
        int min = 1000; // Minimum value of range
        int max = 2000; // Maximum value of range
        // Print the min and max
        // Generate random int value from min to max
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        // Printing the generated random numbers
        String rndNumber = String.valueOf(random_int);

        return result+rndNumber;
    }
}