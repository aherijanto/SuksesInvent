package com.example.suksesinvent.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.CartActivity;
import com.example.suksesinvent.R;
import com.example.suksesinvent.interfaces.onClickedItem;
import com.example.suksesinvent.model.GrocierPriceModel;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.home.HomeFragment;

import java.util.ArrayList;

public class GrocierPriceAdapter extends RecyclerView.Adapter<GrocierPriceAdapter.GrocierPriceHolder> implements onClickedItem {

    private onClickedItem onClick;
    private Context mContext;
    private ArrayList<GrocierPriceModel> GrocierList;


    public GrocierPriceAdapter(Context xFBContext, ArrayList<GrocierPriceModel> itemList1) {
        this.mContext=xFBContext;
        this.GrocierList = itemList1;
    }

    @Override
    public GrocierPriceAdapter.GrocierPriceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_grocier_price, parent, false);
        return new GrocierPriceAdapter.GrocierPriceHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(GrocierPriceAdapter.GrocierPriceHolder holder, @SuppressLint("RecyclerView") int position) {

        holder._qtyGrocier.setText(String.valueOf((double) GrocierList.get(position).getItemQty()));
        holder._priceGrocier.setText(String.format("%,d",((int) GrocierList.get(position).getItemPrice())));

//        holder.txtItemName1.setText(itemList1.get(position).get_itemName());
//        holder.txtItemPrice1.setText(String.format("%,d",(int)itemList1.get(position).get_itemPrice()));
//        holder.txtQty1.setText(String.valueOf(itemList1.get(position).get_itemQTY()));
//        holder.txtSatuan.setText(itemList1.get(position).get_itemUnit());
//        double myQTY = (double) itemList1.get(position).get_itemQTY();
//        int myPrice = (int) itemList1.get(position).get_itemPrice();
//        double subTotal = myPrice * myQTY;
//        holder.txtSubTotal.setText(String.format("%,d",(int)subTotal));
//        holder.myDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Remove the item on remove/button click
//                String itemName = itemList1.get(position).get_itemName();
//                itemList1.remove(position);
//                HomeFragment.dataCart.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position,itemList1.size());
//
//                // Show the removed item label`enter code here`
//                Toast.makeText(mContext,"Removed : " + itemName, Toast.LENGTH_SHORT).show();
//                new ItemsModelSales();
//                ItemsModelSales myGrandTotal = new ItemsModelSales();
//                double grand = myGrandTotal.setGrandTotal(itemList1);
//                CartActivity.txtGrand.setText(String.format("%,d",(int)grand));
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        return (GrocierList != null) ? GrocierList.size() : 0;
    }

    @Override
    public void onItemClick(int position) {
        onClick.onItemClick(position);
    }
    public void onItemRemoved(int position, GrocierPriceAdapter mimoAdapter){
        mimoAdapter.notifyItemRemoved(position);
        notifyItemRangeChanged(position,GrocierList.size());
    }
    public class GrocierPriceHolder extends RecyclerView.ViewHolder {

        private TextView _qtyGrocier;
        private TextView _priceGrocier;

        public GrocierPriceHolder(View itemView) {
            super(itemView);
            _qtyGrocier = (TextView) itemView.findViewById(R.id.txtQTYGrosir);
            _priceGrocier = (TextView) itemView.findViewById(R.id.txtHargaGrosir);
        }
    }
}
