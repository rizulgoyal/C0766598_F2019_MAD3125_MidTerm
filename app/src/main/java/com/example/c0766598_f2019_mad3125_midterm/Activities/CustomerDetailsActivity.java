package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;

public class CustomerDetailsActivity extends AppCompatActivity {

    TextView custid, custname, custemail, custage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        custid = findViewById(R.id.textEmpID);
        custname = findViewById(R.id.textEmpName);
        custage = findViewById(R.id.textEmpAge);
        custemail = findViewById(R.id.textEmpEmail);

        Customer custtemp =getIntent().getParcelableExtra("custobject");

        custid.setText(custtemp.getId());

        String fname = custtemp.getFirstName() + " " + custtemp.getLastName();

        custname.setText(fname);

        custage.setText(custtemp.getAge());
        custemail.setText(custtemp.getEmail());



    }
}
