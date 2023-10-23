package com.example.suksesinvent.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GrocierPriceModel implements Parcelable {
    private  String ItemCode;
    private  double ItemQty;

    protected GrocierPriceModel(Parcel in) {
        ItemCode = in.readString();
        ItemQty = in.readDouble();
        ItemPrice = in.readInt();
    }

    public static final Creator<GrocierPriceModel> CREATOR = new Creator<GrocierPriceModel>() {
        @Override
        public GrocierPriceModel createFromParcel(Parcel in) {
            return new GrocierPriceModel(in);
        }

        @Override
        public GrocierPriceModel[] newArray(int size) {
            return new GrocierPriceModel[size];
        }
    };

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public double getItemQty() {
        return ItemQty;
    }

    public void setItemQty(double itemQty) {
        ItemQty = itemQty;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }

    private  int ItemPrice;

    public GrocierPriceModel(){
    }

    public GrocierPriceModel(String ItemCode, double ItemQTY, int ItemPrice){
        this.ItemCode = ItemCode;
        this.ItemPrice = ItemPrice;
        this.ItemQty = ItemQTY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(ItemCode);
        parcel.writeDouble(ItemQty);
        parcel.writeInt(ItemPrice);
    }
}
