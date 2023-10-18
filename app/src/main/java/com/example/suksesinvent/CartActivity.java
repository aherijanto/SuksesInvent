package com.example.suksesinvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.adapters.CartAdapter;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rvCartList;
    private android.view.View View;
    public static TextView txtGrand;
    ArrayList<ItemsModelSales> listCart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        listCart=this.getIntent().getExtras().getParcelableArrayList("MY_CART");
        rvCartList = (RecyclerView) findViewById(R.id.rvCart);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(MasterMenu.this,2);

        txtGrand = (TextView) findViewById(R.id.txtGT);
        ItemsModelSales mylist1 = new ItemsModelSales();
        double myGT= mylist1.setGrandTotal(listCart);
        CartAdapter adapterItemList = new CartAdapter(this.getApplicationContext(),listCart); //should be HomeFragment.datacart ???
        txtGrand.setText(String.format("%,d",(int) myGT));
        rvCartList.setLayoutManager(layoutManager);
        rvCartList.setAdapter(adapterItemList);
        adapterItemList.notifyDataSetChanged();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId()){
//            case (R.id.action_save):
//                try {
//                    WriteToJSON();
//                    Toast.makeText(getApplicationContext(),
//                                    "Data Saved",
//                                    Toast.LENGTH_SHORT)
//                            .show();
//
//                    //adapterItemList.notifyDataSetChanged();
//                    for(int i = 0; i< HomeFragment.dataCart.size(); i++) {
//                        HomeFragment.dataCart.remove(i);
//                    }
//
//                    Intent myIntent = new Intent(CartActivity.this,HomeFragment.class);
//                    startActivity(myIntent);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return true;
//        }
//        return (super.onOptionsItemSelected(item));
//    }
//
//    public void WriteToJSON() throws JSONException, IOException {
//        JSONArray jsonStudent= new JSONArray();
//
//        for(int u=0;u<listCart.size();u++){
//            JSONObject myJObject = new JSONObject();
//            myJObject.put("transid","testing001");
//            myJObject.put("code",listCart.get(u).getItem_code());
//            myJObject.put("name",listCart.get(u).getItem_name());
//            myJObject.put("qty",listCart.get(u).getItem_qty());
//            myJObject.put("price",listCart.get(u).getItem_price());
//            jsonStudent.put(myJObject);
//        }
//        //System.out.println(jsonStudent.toString());
//        ConnectParseURI myUriBuilder = new ConnectParseURI();
//        //URL myAddress =  myUriBuilder.buildURL("https://senang.mimoapps.xyz/apis/savedetail.php");
//        myUriBuilder.postJSON("https://senang.mimoapps.xyz/apis/savedetail.php",jsonStudent.toString());
//    }
}
