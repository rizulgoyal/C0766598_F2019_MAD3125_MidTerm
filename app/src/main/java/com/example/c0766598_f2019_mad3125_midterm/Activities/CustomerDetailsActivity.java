package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;
import com.example.c0766598_f2019_mad3125_midterm.Adapters.UserVehicleAdapter;

import java.util.List;

public class CustomerDetailsActivity extends AppCompatActivity {

    TextView custid, custname, custemail, custage;
    RecyclerView myrecycler;
    TextView detailtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        custid = findViewById(R.id.textEmpID);
        custname = findViewById(R.id.textEmpName);
        custage = findViewById(R.id.textEmpAge);
        custemail = findViewById(R.id.textEmpEmail);
        detailtext = findViewById(R.id.detailtext1);

        Customer custtemp =getIntent().getParcelableExtra("custobject");
        String empid = String.valueOf(custtemp.getId());
        custid.setText(empid);

        String fname = custtemp.getFirstName() + " " + custtemp.getLastName();

        custname.setText(fname);

        String empage = String.valueOf(custtemp.getAge()) + " years";

        custage.setText(empage);
        custemail.setText(custtemp.getEmail());



        List<Bill> myBillList = custtemp.getBill();

        if (myBillList == null)
        {
            detailtext.setText("Customer has no Bills to pay");
        }
        else
        {

            myrecycler = (RecyclerView) findViewById(R.id.recycler_vehicle);

            final UserVehicleAdapter myadapter = new UserVehicleAdapter(this);
            myadapter.setMyaaraylist(myBillList);

            LinearLayoutManager mylinearlayout = new LinearLayoutManager(this);
            myrecycler.setLayoutManager(mylinearlayout);
            myrecycler.setAdapter(myadapter);
        }








    }
}
