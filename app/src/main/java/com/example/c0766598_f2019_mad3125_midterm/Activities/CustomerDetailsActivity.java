package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_vehicle,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_vehicle:

                Intent myintent = new Intent(this,AddCustomerActivity.class);
                startActivity(myintent);
             //   Toast.makeText(HomeActivity.this,"Select Values",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_customer:

                showDialog(CustomerDetailsActivity.this, "Are you sure you want to delete this customer");





                //  Toast.makeText(HomeActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
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

                Customer custtemp =getIntent().getParcelableExtra("custobject");


                final UserDatabase uData = UserDatabase.getInstance(CustomerDetailsActivity.this);
                //Gson gson = new Gson();


                uData.daoObjct().delete(custtemp);
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();

    }
}
