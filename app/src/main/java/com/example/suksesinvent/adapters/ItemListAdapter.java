package com.example.suksesinvent.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.CartActivity;
import com.example.suksesinvent.MainActivity;
import com.example.suksesinvent.R;
import com.example.suksesinvent.interfaces.RecyclerViewClickListener;
import com.example.suksesinvent.interfaces.onClickedItem;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.gallery.GalleryFragment;
import com.example.suksesinvent.ui.home.HomeFragment;
import com.example.suksesinvent.ui.slideshow.SlideshowFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> implements onClickedItem {
    private onClickedItem onClick;
    private Context mContextmimo;
    private ArrayList<ItemsModelSales> itemListmimo;
    private static RecyclerViewClickListener itemListener;

    public ItemListAdapter(Context xFBContext, ArrayList<ItemsModelSales> itemListmimo){
        this.mContextmimo = xFBContext;
        this.itemListmimo = itemListmimo;
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder  {
        private final TextView _textItemCodeMimo;
        private final TextView _textItemNameMimo;
        private final TextView _textItemBuyingPriceMimo;
        private final TextView _textItemSellingPriceMimo;
        private final TextView _textItemUnitMimo;
        private final TextView _textItemQTYMimo;
        private CardView cardMimo;

        public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            _textItemCodeMimo = (TextView) itemView.findViewById(R.id.txtItemCodeMimo);
            _textItemNameMimo = (TextView) itemView.findViewById(R.id.txtItemNameMimo);
            _textItemSellingPriceMimo = (TextView) itemView.findViewById(R.id.txtPriceMimo);
            _textItemBuyingPriceMimo = (TextView) itemView.findViewById(R.id.txtBuyingPriceMimo);
            _textItemUnitMimo = (TextView)  itemView.findViewById((R.id.txtUnitMimo));
           _textItemQTYMimo = (TextView) itemView.findViewById(R.id.txtQTYMimo);
            cardMimo = (CardView) itemView.findViewById(R.id.card_item_search);
           cardMimo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        Bundle myBundleItem = new Bundle();
                        ItemsModelSales clickeddataItem = itemListmimo.get(pos);
                        String myitemcode = clickeddataItem.get_itemCode();
                        String myitemname = clickeddataItem.get_itemName();
                        String myitemsatuan = clickeddataItem.get_itemUnit();
                        int myitembuyingprice = clickeddataItem.get_itemBuyingPrice();
                        int myitemsellingprice = clickeddataItem.get_itemPrice();
                        double myitemqty = clickeddataItem.get_itemQTY();

                        ItemsModelSales setDetail = new ItemsModelSales();
                        setDetail.set_itemCode(myitemcode);
                        setDetail.set_itemName(myitemname);
                        setDetail.set_itemBuyingPrice(myitembuyingprice);
                        setDetail.set_itemPrice(myitemsellingprice);
                        setDetail.set_itemQTY(myitemqty);
                        setDetail.set_itemUnit(myitemsatuan);

                        ArrayList<ItemsModelSales> ArrayDetailItem = new ArrayList<>();
                        ArrayDetailItem.add(setDetail);
                        myBundleItem.putSerializable("MyDetail",ArrayDetailItem);
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.nav_gallery, myBundleItem);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemListAdapter.ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_search, parent, false);
        return new ItemListAdapter.ItemListViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ItemListViewHolder holder, int position) {
        holder._textItemCodeMimo.setText(itemListmimo.get(position).get_itemCode());
        holder._textItemNameMimo.setText(itemListmimo.get(position).get_itemName());
        holder._textItemSellingPriceMimo.setText(String.format("%,d",itemListmimo.get(position).get_itemPrice()));
        holder._textItemBuyingPriceMimo.setText(String.format("%,d",itemListmimo.get(position).get_itemBuyingPrice()));
        holder._textItemUnitMimo.setText(itemListmimo.get(position).get_itemUnit());
        holder._textItemQTYMimo.setText(String.valueOf(itemListmimo.get(position).get_itemQTY()));
    }

    @Override
    public int getItemCount() {
        return (itemListmimo != null) ? itemListmimo.size() : 0;
    }

    @Override
    public void onItemClick(int position) {

    }
}
