package com.example.suksesinvent.ui.gallery;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.suksesinvent.network.Connection;
import com.example.suksesinvent.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private FragmentGalleryBinding binding;
    public RecyclerView rvGrocier;
    private GrocierPriceAdapter adapterGrocierPrice;
    public static ArrayList<GrocierPriceModel> dataGrocierPrice = new ArrayList<>();
    public static ArrayList<ItemsModelSales> _dataDetail= new ArrayList<>();
    private String itemCodeString = "";
    private String _itemCode;
    private String _itemName;
    private String _buyingPrice;
    private String _sellingPrice;
    private String _itemUnit;
    EditText itemCode_;
    EditText itemName_;
    EditText buyingPrice_;
    EditText sellingPrice_;
    EditText itemUnit_ ;
    EditText itemQTY_;
    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        setHasOptionsMenu(true);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvGrocier = binding.rvMasterGrocierPrice;
        Button btnAddGrocier = binding.btnGrocierPrice;
        itemCode_ = binding.masterTxtItemCode;
        itemName_ = binding.masterTxtItemName;
        buyingPrice_ = binding.masterTxtBuyingPrice;
        sellingPrice_ = binding.masterTxtSellingPrice;
        itemUnit_ = binding.masterTxtSatuan;
        itemQTY_ = binding.masterTxtQTY;


        //_dataDetail = getArguments().getParcelableArrayList("MyDetail");
        if(getArguments()!=null){
            _dataDetail = (ArrayList<ItemsModelSales>) getArguments().getSerializable("MyDetail");
            itemCode_.setText(_dataDetail.get(0).get_itemCode());
            itemName_.setText(_dataDetail.get(0).get_itemName());
            buyingPrice_.setText(String.valueOf(_dataDetail.get(0).get_itemBuyingPrice()));
            sellingPrice_.setText(String.format("%,d",_dataDetail.get(0).get_itemPrice()).toString());
            itemUnit_.setText(_dataDetail.get(0).get_itemUnit());
            itemQTY_.setText(String.valueOf(_dataDetail.get(0).get_itemQTY()));
        }
        btnAddGrocier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 _itemCode = itemCode_.getText().toString();
                 _itemName = itemName_.getText().toString();
                 _buyingPrice = buyingPrice_.getText().toString();
                 _sellingPrice = sellingPrice_.getText().toString();
                 _itemUnit = itemUnit_.getText().toString();

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu_input_invent, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_save_invent){
            View myView = getView();

            _itemCode = itemCode_.getText().toString();
            _itemName = itemName_.getText().toString();
            _buyingPrice = buyingPrice_.getText().toString();
            _sellingPrice = sellingPrice_.getText().toString();
            _itemUnit = itemUnit_.getText().toString();
            try {
                if (_itemCode.matches("")){
                    itemCodeString = firstLetterWord(_itemName);
                }else{
                    itemCodeString = _itemCode;
                }

                String response = SaveInventory(itemCodeString,_itemName,_buyingPrice,_sellingPrice,_itemUnit);
                if(dataGrocierPrice.size() != 0){
                    String price = SavePriceGrocier();
                }
                ClearView();
                Toast.makeText(getContext(),"Data berhasil disimpan",Toast.LENGTH_LONG).show();
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String SaveInventory(String _code,String _name, String _buy, String _sell, String _unit) throws JSONException, IOException {

        JSONArray jsonInvent= new JSONArray();
        JSONObject myJObject = new JSONObject();
        myJObject.put("code",_code);
        myJObject.put("name",_name);
        myJObject.put("buying",_buy);
        myJObject.put("selling",_sell);
        myJObject.put("unit",_unit);
        jsonInvent.put(myJObject);

        Connection myUriBuilder = new Connection();
        //URL myAddress =  myUriBuilder.buildURL("https://senang.mimoapps.xyz/apis/savedetail.php");
        return myUriBuilder.postJSON("https://mimoapps.xyz/sukses/apis/savedetail.php",jsonInvent.toString());
    }

    private String SavePriceGrocier() throws JSONException, IOException {
        String mString="";
        JSONArray jsonPrice= new JSONArray();
        for(int i=0;i<dataGrocierPrice.size();i++){
            JSONObject myJObjectPrice = new JSONObject();
            myJObjectPrice.put("code",itemCodeString);
            myJObjectPrice.put("qty",dataGrocierPrice.get(i).getItemQty());
            myJObjectPrice.put("selling",dataGrocierPrice.get(i).getItemPrice());
            jsonPrice.put(myJObjectPrice);
        }

        Connection myUriBuilder = new Connection();
        //URL myAddress =  myUriBuilder.buildURL("https://senang.mimoapps.xyz/apis/savedetail.php");
        myUriBuilder.postJSON("https://mimoapps.xyz/sukses/apis/savegrocierprice.php",jsonPrice.toString());
        return mString;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void ClearView(){
        itemCodeString="";
        itemCode_.setText(null);
        itemName_.setText(null);
        itemUnit_.setText(null);
        buyingPrice_.setText(null);
        sellingPrice_.setText(null);
        itemQTY_.setText(null);
        if(dataGrocierPrice.size()>0){
            for(int pos=0;pos<dataGrocierPrice.size();pos++){
                dataGrocierPrice.remove(pos);
                adapterGrocierPrice.notifyItemRemoved(pos);

                //adapterGrocierPrice.notifyItemRemoved(pos+1);
            }
        }
        dataGrocierPrice = new ArrayList<>();
        adapterGrocierPrice = new GrocierPriceAdapter(getContext(),dataGrocierPrice);
        rvGrocier.setAdapter(null);
        rvGrocier.setAdapter(adapterGrocierPrice);


    }

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
            else if (str.charAt(i) != ' ' && v == true)
            {
                result += (str.charAt(i));
                v = false;
            }
        }
        int min = 1000; // Minimum value of range
        int max = 2000; // Maximum value of range

        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        String rndNumber = String.valueOf(random_int);

        return result+rndNumber;
    }
}