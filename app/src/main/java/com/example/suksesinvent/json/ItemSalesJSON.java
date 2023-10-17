package com.example.suksesinvent.json;

import android.content.Context;

import com.example.suksesinvent.model.ItemsModelSales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemSalesJSON {
    public static ArrayList<ItemsModelSales> GetItemList(Context context, String itemListJSON)
            throws JSONException {

        final String mItemCode="i_code";
        final String mItemName="i_name";
        final String mItemPrice="i_sell";
        final String mItemSatuan = "i_unit";

        JSONArray ItemListJSONArray = new JSONArray(itemListJSON);
        JSONObject myJSONObject;
        ArrayList<ItemsModelSales> dataItemList=new ArrayList<>();
        for (int i = 0; i < ItemListJSONArray.length(); i++){
            ItemsModelSales mdataItemList=new ItemsModelSales();
            myJSONObject = ItemListJSONArray.getJSONObject(i);
            mdataItemList.set_itemCode(myJSONObject.getString(mItemCode));
            mdataItemList.set_itemName((myJSONObject.getString(mItemName)));
            mdataItemList.set_itemPrice(myJSONObject.getInt(mItemPrice));
            mdataItemList.set_itemUnit(myJSONObject.getString(mItemSatuan));
            dataItemList.add(mdataItemList);
        }
        return dataItemList;
    }
}
