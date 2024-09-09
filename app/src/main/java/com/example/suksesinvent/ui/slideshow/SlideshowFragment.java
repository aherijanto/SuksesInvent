package com.example.suksesinvent.ui.slideshow;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.adapters.ItemListAdapter;
import com.example.suksesinvent.adapters.ItemSearchAdapter;
import com.example.suksesinvent.databinding.FragmentSlideshowBinding;
import com.example.suksesinvent.json.ItemSalesJSON;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.network.Connection;
import com.example.suksesinvent.ui.home.HomeFragment;

import java.net.URL;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private ItemListAdapter adapterItemListMimo;
    public static ArrayList<ItemsModelSales> dataItemListmimo;
    private EditText txtSearchItemMimo;
    private Button btnSearchItemMimo;
    private RecyclerView rvItemListMimo;


    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtSearchItemMimo = binding.txtSearchItemMimo;
        btnSearchItemMimo = binding.btnSearchMimo;
        rvItemListMimo = binding.rvItemListMimo;

        dataItemListmimo = new ArrayList<>();
        adapterItemListMimo = new ItemListAdapter(getContext(),dataItemListmimo);
        rvItemListMimo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItemListMimo.setAdapter(adapterItemListMimo);
        adapterItemListMimo.notifyDataSetChanged();

        if (savedInstanceState == null) {
            btnSearchItemMimo.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SlideshowFragment.FetchItemListMimo().execute();
                }
            });
        } else {
            //ArrayList parcelRecipe = savedInstanceState.getParcelableArrayList(MY_KEY);
            rvItemListMimo.setAdapter(new ItemListAdapter(getContext(), dataItemListmimo));
        }

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class FetchItemListMimo extends AsyncTask<String,Void,ArrayList<ItemsModelSales>> {
        @Override
        protected ArrayList<ItemsModelSales> doInBackground(String... params) {
            URL ItemDataUrl = Connection.buildURL("https://mimoapps.xyz/sukses/apis/getitemlist_sukses.php?itemname=" + txtSearchItemMimo.getText().toString());
            //https://senang.mimoapps.xyz/apis/getlistitems.php?itemname=

            try {
                String ItemListResponse = Connection.getResponseFromHttpUrl(ItemDataUrl);
                System.out.println(ItemListResponse);
                return ItemSalesJSON.GetItemList(getActivity().getApplicationContext(), ItemListResponse);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(ArrayList<ItemsModelSales> strings) {
            if (strings != null) {
                adapterItemListMimo = new ItemListAdapter(getContext(),strings);
                rvItemListMimo.setAdapter(adapterItemListMimo);
                dataItemListmimo=strings;
            }
        }
    }
}