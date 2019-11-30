package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mytext =  findViewById(R.id.hometext);


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

            myarraylist = uData.daoObjct().getdefaultUserDetails();
        }

        String info = "";
        for(Customer mynewcustomer : myarraylist) {

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
