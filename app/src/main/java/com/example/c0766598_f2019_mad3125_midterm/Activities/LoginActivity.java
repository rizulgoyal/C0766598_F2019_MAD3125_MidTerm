package com.example.c0766598_f2019_mad3125_midterm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c0766598_f2019_mad3125_midterm.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;

    private EditText password;
    private Button login;
    private String usernamestore;
    private String passwordstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editUserName);
        password = findViewById(R.id.editPassword);
        login = findViewById(R.id.buttonlogin);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //        if (view.getId() == R.id.buttonlogin)

                usernamestore = username.getText().toString();
                passwordstore = password.getText().toString();

                if (usernamestore.isEmpty() || passwordstore.isEmpty())
                {
showtextDialog(LoginActivity.this,"Please Enter Username and Password");
                }

                else if(username.getText().toString().equals("Rizul") && password.getText().toString().equals("8029"))
                {

                    showFinalDialog(LoginActivity.this,"Are You sure you want to login into Billing System");


                }
                else
                {

                    showtextDialog(LoginActivity.this,"Please Enter Correct Username and Password");

                    //username.setError("Please add name");
                    //Toast.makeText(LoginActivity.this,"Not correct",Toast.LENGTH_SHORT).show();
                }




            }


        });



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



    public void showFinalDialog(final Activity activity, String msg){
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


                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username",usernamestore);
                intent.putExtra("password",passwordstore);
                dialog.dismiss();


                startActivity(intent);



            }
        });



        dialog.show();

    }




}

