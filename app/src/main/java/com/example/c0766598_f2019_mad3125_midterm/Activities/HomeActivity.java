package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;
import com.example.c0766598_f2019_mad3125_midterm.UserDataAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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



        String info = "";
        for(Customer mynewcustomer : myarraylist) {

        List<Bill> mybills = mynewcustomer.getBill();

        for(Bill mybill : mybills)
        {
            info = info + mybill.getBillType();
        }

            info = info + mynewcustomer.getFirstName() + " " + mynewcustomer.getLastName();


        }
        mytext.setText(info);




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
}
