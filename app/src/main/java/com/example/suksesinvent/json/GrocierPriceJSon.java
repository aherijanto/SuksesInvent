package com.example.suksesinvent.json;

import android.content.Context;

import com.example.suksesinvent.model.GrocierPriceModel;
import com.example.suksesinvent.model.ItemsModelSales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GrocierPriceJSon {
    public static ArrayList<GrocierPriceModel> GetItemList(Context context, String itemListJSON)
            throws JSONException {

        final String mItemCode="item_code";
        final String mItemQty = "item_qty";
        final String mItemSellingPrice = "item_price";

        JSONArray priceListJSONArray = new JSONArray(itemListJSON);
        JSONObject priceJSONObject;
        ArrayList<GrocierPriceModel> priceItemList=new ArrayList<>();
        for (int i = 0; i < priceListJSONArray.length(); i++){
            GrocierPriceModel mdataItemList=new GrocierPriceModel();
            priceJSONObject = priceListJSONArray.getJSONObject(i);
            mdataItemList.setItemCode(priceJSONObject.getString(mItemCode));
            mdataItemList.setItemPrice(priceJSONObject.getInt(mItemSellingPrice));
            mdataItemList.setItemQty(priceJSONObject.getDouble(mItemQty));
            priceItemList.add(mdataItemList);
        }
        return priceItemList;
    }
}
