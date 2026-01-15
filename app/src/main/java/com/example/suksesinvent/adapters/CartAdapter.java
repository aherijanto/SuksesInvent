package com.example.suksesinvent.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.suksesinvent.CartActivity;
import com.example.suksesinvent.R;
import com.example.suksesinvent.interfaces.onClickedItem;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.home.HomeFragment;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements onClickedItem {
    private onClickedItem onClick;
    private Context mContext;
    private ArrayList<ItemsModelSales> itemList1;
    private final String imageUrl = "https://mimoapps.xyz/sukses/anyar/images/products/";


    public CartAdapter(Context xFBContext, ArrayList<ItemsModelSales> itemList1) {
        this.mContext=xFBContext;
        this.itemList1 = itemList1;
    }

    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_card, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String imgeUrl = imageUrl
                + itemList1.get(position).get_itemCode()
                + ".jpeg";

        Glide.with(mContext)
                .load(imgeUrl)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(holder._imageProduct);
        holder.txtItemName1.setText(itemList1.get(position).get_itemName());
        holder.txtItemPrice1.setText(String.format("%,d",(int)itemList1.get(position).get_itemPrice()));
        holder.txtQty1.setText(String.valueOf(itemList1.get(position).get_itemQTY()));
        holder.txtSatuan.setText(itemList1.get(position).get_itemUnit());
        int myQTY = (int) itemList1.get(position).get_itemQTY();
        int myPrice = (int) itemList1.get(position).get_itemPrice();
        double subTotal = myPrice * myQTY;
        holder.txtSubTotal.setText(String.format("%,d",(int)subTotal));
        holder.myDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Remove the item on remove/button click
                String itemName = itemList1.get(position).get_itemName();
                itemList1.remove(position);
                HomeFragment.dataCart.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,itemList1.size());

                // Show the removed item label`enter code here`
                Toast.makeText(mContext,"Removed : " + itemName, Toast.LENGTH_SHORT).show();
                new ItemsModelSales();
                ItemsModelSales myGrandTotal = new ItemsModelSales();
                double grand = myGrandTotal.setGrandTotal(itemList1);
                CartActivity.txtGrand.setText(String.format("%,d",(int)grand));
            }

        });
    }

    @Override
    public int getItemCount() {
        return (itemList1 != null) ? itemList1.size() : 0;
    }

    @Override
    public void onItemClick(int position) {
        onClick.onItemClick(position);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName1;
        private TextView txtItemPrice1;
        private TextView txtQty1;
        private TextView txtSubTotal;
        private ImageView myDel;
        private TextView txtSatuan;

        private ImageView _imageProduct;


        public CartViewHolder(View itemView) {
            super(itemView);
            _imageProduct = itemView.findViewById(R.id.imageProduct);
            txtItemName1 = (TextView) itemView.findViewById(R.id.txtItemName1);
            txtItemPrice1 = (TextView) itemView.findViewById(R.id.txtItemPrice1);
            txtQty1 = (TextView) itemView.findViewById((R.id.txtQty1));
            txtSubTotal = (TextView) itemView.findViewById(R.id.textSubTotal);
            myDel = (ImageView) itemView.findViewById(R.id.imgDel);
            txtSatuan = (TextView) itemView.findViewById(R.id.txtSatuan);
        }
    }
}
