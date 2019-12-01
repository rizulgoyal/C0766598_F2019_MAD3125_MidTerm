package com.example.c0766598_f2019_mad3125_midterm.Activities;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;

public class AddVehicleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText id,fname,lname,age,email;
    Button mybutton;
    String custid ;
    String custage ;
    String custfname ;
    String custlname ;
    String custemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        id = findViewById(R.id.editTextCustID);
        fname = findViewById(R.id.editTextCustfname);
        lname = findViewById(R.id.editTextCustlname);
        age = findViewById(R.id.editTextCustage);
        email = findViewById(R.id.editTextCustemail);

mybutton = findViewById(R.id.buttoncustomer);
        mybutton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        createCust();

        //Intent myintent = new Intent(this,MainActivity.class);
        //startActivity(myintent);






    }

    public void createCust()
    {
        custid = id.getText().toString();
         custage = age.getText().toString();
         custfname = fname.getText().toString();
         custlname = lname.getText().toString();
         custemail = email.getText().toString();



        if (custid.equals(""))
        {
            showtextDialog(AddVehicleActivity.this,"Please add Customer ID to complete the Customer Form");
        }
        else if(custfname.equals("") || custlname.equals("") || custage.equals("") || custemail.equals(""))
        {
            showtextDialog(AddVehicleActivity.this,"Please add Customer values to complete the Customer Form");

        }
        else {

            showDialog(AddVehicleActivity.this,"Are you sure you want to submit?");



            //Toast.makeText(AddCustomerActivity.this, "Added obj", Toast.LENGTH_SHORT).show();
        }

    }

    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.dialogtext);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.customButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Customer tempobject = new Customer(Integer.parseInt(custid),custfname,custlname,Integer.parseInt(custage),custemail);

                final UserDatabase uData = UserDatabase.getInstance(AddVehicleActivity.this);
                //Gson gson = new Gson();


                uData.daoObjct().insert(tempobject);


                dialog.dismiss();
                finish();
            }
        });

        dialog.show();

    }

    public void showtextDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.dialogtext);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.customButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();

            }
        });

        dialog.show();

    }
}


