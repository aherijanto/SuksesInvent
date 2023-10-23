package com.example.suksesinvent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ItemsModelSales implements Parcelable {
    private String _itemCode;
    private String _itemName;
    private int _itemPrice;//selling price
    private String _itemUnit;
    private double _itemQTY;
    private int _itemBuyingPrice;

    public int get_itemBuyingPrice() {
        return _itemBuyingPrice;
    }

    public void set_itemBuyingPrice(int _itemBuyingPrice) {
        this._itemBuyingPrice = _itemBuyingPrice;
    }




    public double get_itemQTY() {
        return _itemQTY;
    }

    public void set_itemQTY(double _itemQTY) {
        this._itemQTY = _itemQTY;
    }


    public String get_itemUnit() {
        return _itemUnit;
    }

    public void set_itemUnit(String _itemUnit) {
        this._itemUnit = _itemUnit;
    }

    public ItemsModelSales(){
    }

    protected ItemsModelSales(Parcel in) {
        _itemCode = in.readString();
        _itemName = in.readString();
        _itemPrice = in.readInt();
        _itemUnit = in.readString();
        _itemQTY = in.readDouble();
        _itemBuyingPrice = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_itemCode);
        dest.writeString(_itemName);
        dest.writeInt(_itemPrice);
        dest.writeString(_itemUnit);
        dest.writeDouble(_itemQTY);
        dest.writeInt(_itemBuyingPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemsModelSales> CREATOR = new Creator<ItemsModelSales>() {
        @Override
        public ItemsModelSales createFromParcel(Parcel in) {
            return new ItemsModelSales(in);
        }

        @Override
        public ItemsModelSales[] newArray(int size) {
            return new ItemsModelSales[size];
        }
    };

    public String get_itemCode() {
        return _itemCode;
    }

    public void set_itemCode(String _itemCode) {
        this._itemCode = _itemCode;
    }

    public String get_itemName() {
        return _itemName;
    }

    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    public int get_itemPrice() {
        return _itemPrice;
    }

    public void set_itemPrice(int _itemPrice) {
        this._itemPrice = _itemPrice;
    }

    public ItemsModelSales(String itemCode_,String itemName_,Integer itemPrice_, String itemUnit_,double itemQTY_, Integer itemBuyingPrice_){
        this._itemCode = itemCode_;
        this._itemName = itemName_;
        this._itemPrice = itemPrice_;
        this._itemUnit = itemUnit_;
        this._itemQTY = itemQTY_;
        this._itemBuyingPrice = itemBuyingPrice_;
    }

    public double setGrandTotal(ArrayList<ItemsModelSales> myList){
        double grandtot = 0;
        for(int i=0;i<myList.size();i++){
            double qty = myList.get(i).get_itemQTY();
            double price = myList.get(i).get_itemPrice();
            double subtotal = qty*price;
            grandtot = grandtot+subtotal;
        }
        return grandtot;
    }

}
