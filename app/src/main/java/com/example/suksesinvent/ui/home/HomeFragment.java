package com.example.suksesinvent.ui.home;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public static ArrayList<ItemsModelSales> dataItemList = new ArrayList<>();
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

        // Initialize adapter with the static list to maintain state when navigating back
        adapterItemList = new ItemSearchAdapter(getContext(), dataItemList);
        rvItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItemList.setAdapter(adapterItemList);

        // ALWAYS set the click listener so it works every time the view is created
        btnSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = txtSearchItem.getText().toString();
                new FetchItemList().execute(query);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class FetchItemList extends AsyncTask<String, Void, ArrayList<ItemsModelSales>> {
        @Override
        protected ArrayList<ItemsModelSales> doInBackground(String... params) {
            String searchQuery = (params != null && params.length > 0) ? params[0] : "";
            URL ItemDataUrl = Connection.buildURL("https://mimoapps.xyz/sukses/apis/getitemlist_sukses.php?itemname=" + searchQuery);
            try {
                String ItemListResponse = Connection.getResponseFromHttpUrl(ItemDataUrl);
                if (getActivity() != null) {
                    return ItemSalesJSON.GetItemList(getActivity().getApplicationContext(), ItemListResponse);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        protected void onPostExecute(ArrayList<ItemsModelSales> result) {
            // Check isAdded() to ensure fragment is still attached
            if (result != null && isAdded()) {
                dataItemList.clear();
                dataItemList.addAll(result);
                adapterItemList.notifyDataSetChanged();
            }
        }
    }
}