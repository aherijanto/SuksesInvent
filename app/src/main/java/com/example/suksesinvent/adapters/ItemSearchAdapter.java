package com.example.suksesinvent.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.suksesinvent.R;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder> {
    private Context mContext;
    private ArrayList<ItemsModelSales> itemList;
    private final String imageUrl = "https://mimoapps.xyz/sukses/anyar/images/products/";
    public ItemSearchAdapter(Context context, ArrayList<ItemsModelSales> itemList) {
        this.mContext = context;
        this.itemList = itemList;
    }

    public class ItemSearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView _textItemCode;
        private final TextView _textItemName;
        private final TextView _textItemPrice;
        private final TextView _textItemUnit;
        private final EditText _textItemQTY;
        private final ImageButton _btnPlus;
        private final ImageButton _btnMinus;
        private final ImageButton _btnAddToCart;
        private final CardView card;
        private final ImageView _imageProduct;



        public ItemSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            _imageProduct = itemView.findViewById(R.id.imageProduct);
            _textItemCode = itemView.findViewById(R.id.txtItemCode);
            _textItemName = itemView.findViewById(R.id.txtItemName);
            _textItemPrice = itemView.findViewById(R.id.txtPrice);
            _textItemUnit = itemView.findViewById(R.id.txtUnit);
            _textItemQTY = itemView.findViewById(R.id.txtQTY);
            _btnPlus = itemView.findViewById(R.id.btnPlus);
            _btnMinus = itemView.findViewById(R.id.btnMinus);
            _btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

            card = itemView.findViewById(R.id.card_item_sales);

            _btnPlus.setOnClickListener(v -> {
                try {
                    String qtyStr = _textItemQTY.getText().toString();
                    int currentVal = qtyStr.isEmpty() ? 0 : Integer.parseInt(qtyStr);
                    _textItemQTY.setText(String.valueOf(currentVal + 1));
                } catch (NumberFormatException e) {
                    _textItemQTY.setText("1");
                }
            });

            _btnMinus.setOnClickListener(v -> {
                try {
                    String qtyStr = _textItemQTY.getText().toString();
                    int currentVal = qtyStr.isEmpty() ? 0 : Integer.parseInt(qtyStr);
                    if (currentVal > 1) {
                        _textItemQTY.setText(String.valueOf(currentVal - 1));
                    }
                } catch (NumberFormatException e) {
                    _textItemQTY.setText("1");
                }
            });

            _textItemQTY.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && s.length() > 0) {
                        try {
                            itemList.get(pos).set_itemQTY(Double.parseDouble(s.toString()));
                        } catch (NumberFormatException ignored) {}
                    }
                }
                @Override public void afterTextChanged(Editable s) {}
            });

            _btnAddToCart.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    ItemsModelSales clickedItem = itemList.get(pos);
                    double qty;
                    try {
                        qty = Double.parseDouble(_textItemQTY.getText().toString());
                    } catch (NumberFormatException e) {
                        qty = 1.0;
                    }
                    
                    ItemsModelSales cartItem = new ItemsModelSales();
                    cartItem.set_itemCode(clickedItem.get_itemCode());
                    cartItem.set_itemName(clickedItem.get_itemName());
                    cartItem.set_itemPrice(clickedItem.get_itemPrice());
                    cartItem.set_itemQTY(qty);
                    cartItem.set_itemUnit(clickedItem.get_itemUnit());

                    HomeFragment.dataCart.add(cartItem);
                    Snackbar.make(view, clickedItem.get_itemName() + " ditambahkan ke keranjang", Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemSearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cardview_item_sales, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemSearchViewHolder holder, int position) {
        ItemsModelSales item = itemList.get(position);

        String imgeUrl = imageUrl
                        + item.get_itemCode()
                        + ".jpeg";

        Glide.with(mContext)
                .load(imgeUrl)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(holder._imageProduct);

        holder._textItemCode.setText(item.get_itemCode());
        holder._textItemName.setText(item.get_itemName());
        holder._textItemPrice.setText(String.format("%,d", item.get_itemPrice()));
        if (holder._textItemUnit != null) {
            holder._textItemUnit.setText(item.get_itemUnit());
        }
        holder._textItemQTY.setText("1");
    }

    @Override
    public int getItemCount() {
        return (itemList != null) ? itemList.size() : 0;
    }
}