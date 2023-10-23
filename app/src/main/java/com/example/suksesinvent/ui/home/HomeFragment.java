package com.example.suksesinvent.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.R;
import com.example.suksesinvent.adapters.ItemSearchAdapter;
import com.example.suksesinvent.databinding.FragmentHomeBinding;
import com.example.suksesinvent.json.ItemSalesJSON;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.network.Connection;

import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView rvItemList;
    private ItemSearchAdapter adapterItemList;
    public static ArrayList<ItemsModelSales> dataItemList;
    public static ArrayList<ItemsModelSales> dataCart = new ArrayList<>();
    private EditText txtSearchItem;
    private Button btnSearchItem;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        txtSearchItem = binding.txtSearchItem;
        btnSearchItem = binding.btnSearch;
        rvItemList = binding.rvItemSales;

        dataItemList = new ArrayList<>();
        adapterItemList = new ItemSearchAdapter(getContext(),dataItemList);
        rvItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItemList.setAdapter(adapterItemList);
        adapterItemList.notifyDataSetChanged();


        if (savedInstanceState == null) {
            btnSearchItem.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new FetchItemList().execute();
                }
            });
        } else {
            //ArrayList parcelRecipe = savedInstanceState.getParcelableArrayList(MY_KEY);
            rvItemList.setAdapter(new ItemSearchAdapter(getContext(), dataItemList));
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class FetchItemList extends AsyncTask<String,Void,ArrayList<ItemsModelSales>> {
        @Override
        protected ArrayList<ItemsModelSales> doInBackground(String... params) {
            URL ItemDataUrl = Connection.buildURL("https://mimoapps.xyz/sukses/apis/getitemlist_sukses.php?itemname=" + txtSearchItem.getText().toString());
            //https://senang.mimoapps.xyz/apis/getlistitems.php?itemname=
            try {
                String ItemListResponse = Connection.getResponseFromHttpUrl(ItemDataUrl);
                return ItemSalesJSON.GetItemList(getActivity().getApplicationContext(), ItemListResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(ArrayList<ItemsModelSales> strings) {
            if (strings != null) {
                adapterItemList = new ItemSearchAdapter(getContext(),strings);
                rvItemList.setAdapter(adapterItemList);
                dataItemList=strings;
            }
        }
    }

}