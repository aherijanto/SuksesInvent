package com.example.suksesinvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suksesinvent.adapters.CartAdapter;
import com.example.suksesinvent.model.ItemsModelSales;
import com.example.suksesinvent.network.Connection;
import com.example.suksesinvent.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rvCartList;
    private android.view.View View;
    public static TextView txtGrand;
    ArrayList<ItemsModelSales> listCart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Keranjang Belanja");
        }

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

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id== R.id.action_save){
            try {
                WriteToJSON();
                Toast.makeText(getApplicationContext(),
                                "Order Terkirim, Silakan Menuju Ke Toko Untuk Proses Selanjutnya...",
                                Toast.LENGTH_SHORT)
                        .show();

                //adapterItemList.notifyDataSetChanged();
//                for(int i = 0; i< HomeFragment.dataCart.size(); i++) {
//                    HomeFragment.dataCart.remove(i);
//                }
                HomeFragment.dataCart.clear();
                finish();
//                Intent myIntent = new Intent(CartActivity.this,HomeFragment.class);
//                startActivity(myIntent);

                //Navigation.findNavController(View).navigate(R.id.nav_home);
//                Navigation.findNavController(findViewById(R.id.mobile_navigation))
//                        .navigate(R.id.nav_home);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return (super.onOptionsItemSelected(item));
    }
//
    public void WriteToJSON() throws JSONException, IOException {
        JSONArray jsonMobileOrder= new JSONArray();

        for(int u=0;u<listCart.size();u++){
            JSONObject myJObject = new JSONObject();
            //myJObject.put("order_code","testing001");
            myJObject.put("order_type","mobile");
            myJObject.put("u_code","username");
            myJObject.put("order_status","neworder");
            myJObject.put("i_code",listCart.get(u).get_itemCode());
            myJObject.put("i_name",listCart.get(u).get_itemName());
            myJObject.put("i_qty",listCart.get(u).get_itemQTY());
            myJObject.put("i_sell",listCart.get(u).get_itemPrice());
            jsonMobileOrder.put(myJObject);
        }

        Connection myUriBuilder = new Connection();
        URL myURL = myUriBuilder.buildURL("https://mimoapps.xyz/sukses/apis/savemobileorder.php");
        String postOrder = myUriBuilder.postJSON(myURL.toString(),jsonMobileOrder.toString());
        System.out.println(postOrder);
    }
}
