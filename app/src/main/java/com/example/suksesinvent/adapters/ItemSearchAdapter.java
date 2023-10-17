package com.example.suksesinvent.adapters;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.suksesinvent.interfaces.RecyclerViewClickListener;
import com.example.suksesinvent.interfaces.onClickedItem;
import com.example.suksesinvent.model.ItemsModelSales;

import java.util.ArrayList;


public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder> implements onClickedItem {
    private onClickedItem onClick;
    private Context mContext;
    private ArrayList<ItemsModelSales> itemList;
    private static RecyclerViewClickListener itemListener;

    public ItemSearchAdapter(Context xFBContext, ArrayList<ItemsModelSales> itemList){
        this.mContext = xFBContext;
        this.itemList = itemList;
    }


    public class ItemSearchViewHolder extends RecyclerView.ViewHolder  {

        private TextView _textItemCode;
        private TextView _textItemName;
        private TextView _textItemPrice;
        private TextView _textItemUnit;

        private CardView card;

        public ItemSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            _textItemCode = (TextView) itemView.findViewById(com.example.suksesinvent.R.id.txtItemCode);
            _textItemName = (TextView) itemView.findViewById(com.example.suksesinvent.R.id.txtItemName);
            _textItemPrice = (TextView) itemView.findViewById(com.example.suksesinvent.R.id.txtPrice);
            _textItemUnit = (TextView)  itemView.findViewById((com.example.suksesinvent.R.id.txtUnit));
            card = (CardView) itemView.findViewById(com.example.suksesinvent.R.id.card_item_sales);
        }
    }

    @NonNull
    @Override
    public ItemSearchAdapter.ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(com.example.suksesinvent.R.layout.cardview_item_sales, parent, false);
        return new ItemSearchAdapter.ItemSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSearchAdapter.ItemSearchViewHolder holder, int position) {
//        Random rnd = new Random();
//        int currentColor = Color.argb(255, rnd.nextInt(300), rnd.nextInt(256), rnd.nextInt(256));
//        holder.card.setCardBackgroundColor(currentColor);
        holder._textItemCode.setText(itemList.get(position).get_itemCode());
        holder._textItemName.setText(itemList.get(position).get_itemName());
        holder._textItemPrice.setText(String.format("%,d",itemList.get(position).get_itemPrice()).toString());
        holder._textItemUnit.setText(itemList.get(position).get_itemUnit());
    }

    @Override
    public int getItemCount() {
        return (itemList != null) ? itemList.size() : 0;
    }

    @Override
    public void onItemClick(int position) {

    }
}
