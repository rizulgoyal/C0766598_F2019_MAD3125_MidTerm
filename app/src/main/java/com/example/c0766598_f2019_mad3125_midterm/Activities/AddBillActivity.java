package com.example.c0766598_f2019_mad3125_midterm.Activities;




import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddBillActivity extends AppCompatActivity implements View.OnClickListener{

    EditText id,date,amount,type;
    final Calendar myCalendar = Calendar.getInstance();

    Button mybutton;
    String bid ;
    String bdate ;
    String bamount ;
    String btype ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        id = findViewById(R.id.editTextbillID);
        date = findViewById(R.id.editTextbilldate);
        amount = findViewById(R.id.editTextbillamount);
        type = findViewById(R.id.editTextBillType);



        mybutton = findViewById(R.id.buttonbill);
        mybutton.setOnClickListener(this);

        date.setOnClickListener(this);






    }

    DatePickerDialog.OnDateSetListener mydate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "MMM-dd-yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case  R.id.buttonbill:
                createBill();
        break;
            case  R.id.editTextbilldate:
                new DatePickerDialog(v.getContext(), mydate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        }


        //Intent myintent = new Intent(this,MainActivity.class);
        //startActivity(myintent);






    }

    public void createBill()
    {
        bid = id.getText().toString();
         btype = type.getText().toString();
         bdate = date.getText().toString();
         bamount = amount.getText().toString();



        if(bid.equals("") || btype.equals("") || bdate.equals("") || bamount.equals(""))
        {
            showtextDialog(AddBillActivity.this,"Please add All Bill values to complete the Customer Form");

        }
        else {

            showDialog(AddBillActivity.this,"Are you sure you want to submit?");



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

                Customer custtemp =getIntent().getParcelableExtra("custobjectvehicle");


                Bill tempobject = new Bill(Integer.parseInt(bid),bdate,btype,Double.parseDouble(bamount));

                final UserDatabase uData = UserDatabase.getInstance(AddBillActivity.this);


                custtemp.setmyBill(tempobject);
                //Gson gson = new Gson();


                uData.daoObjct().update(custtemp);



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


