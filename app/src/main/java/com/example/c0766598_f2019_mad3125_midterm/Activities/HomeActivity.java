package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;
import com.example.c0766598_f2019_mad3125_midterm.Adapters.UserDataAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView mytext;
    List<Customer> myarraylist = new ArrayList<>();
    String temps;
    RecyclerView myrecycler;
    CardView mycardview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mytext =  findViewById(R.id.hometext);
        myrecycler = (RecyclerView) findViewById(R.id.recycler_main);



        final UserDatabase uData = UserDatabase.getInstance(this);
        final Integer count = uData.daoObjct().count();
        if(count == 0)
        {
            //uData.clearAllTables();

            String temp1 = loadJSONFromAsset();
            Gson gson = new Gson();

            try {
                JSONArray jsonarray = new JSONArray(temp1);

                for(int i =0 ; i<=jsonarray.length();i++)
                {

                    temps = jsonarray.get(i).toString();
                    Customer cst = gson.fromJson(temps,Customer.class);
                    uData.daoObjct().insert(cst);

                }
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            //end of data from json

        }

        myarraylist = uData.daoObjct().getdefaultUserDetails();




        final UserDataAdapter myadapter = new UserDataAdapter(this);
        myadapter.setMyaaraylist(myarraylist);

        LinearLayoutManager mylinearlayout = new LinearLayoutManager(this);
        myrecycler.setLayoutManager(mylinearlayout);
        myrecycler.setAdapter(myadapter);

        uData.daoObjct().getUserDetails().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable List<Customer> customers) {
                myadapter.setMyaaraylist(customers);
                myadapter.notifyDataSetChanged();
            }
        });



//        String info = "";
//        for(Customer mynewcustomer : myarraylist) {
//
//        List<Bill> mybills = mynewcustomer.getBill();
//
//        for(Bill mybill : mybills)
//        {
//            info = info + mybill.getBillType();
//        }
//
//            info = info + mynewcustomer.getFirstName() + " " + mynewcustomer.getLastName();
//
//
//        }
//        mytext.setText(info);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_addcustomer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_customer:
                Intent myintent = new Intent(this,AddCustomerActivity.class);
                startActivity(myintent);
                Toast.makeText(HomeActivity.this,"Select Values",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                showDialog(HomeActivity.this,"Are you sure you want to Log Out");
             //   Toast.makeText(HomeActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }






    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = HomeActivity.this.getAssets().open("sample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_twobutton);

        TextView text = (TextView) dialog.findViewById(R.id.dialogtext);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.customButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();
            }
        });
        Button dialogNoButton = (Button) dialog.findViewById(R.id.customButtonNo);
        dialogNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
