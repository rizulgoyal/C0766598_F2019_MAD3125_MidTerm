package com.example.c0766598_f2019_mad3125_midterm.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0766598_f2019_mad3125_midterm.Activities.AddBillActivity;
import com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles.UserDatabase;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;
import com.example.c0766598_f2019_mad3125_midterm.R;

import java.util.List;


public class UserVehicleAdapter extends RecyclerView.Adapter<UserVehicleAdapter.ViewHolder> {

    private Activity context;
    public Customer mycustomer;
    public List<Bill> myaaraylist;

    public Customer getMycustomer() {
        return mycustomer;
    }

    public void setMycustomer(Customer mycustomer) {
        this.mycustomer = mycustomer;
    }





    public UserVehicleAdapter(Activity context) {
        this.context = context;

    }



    public Context getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public List<Bill> getMyaaraylist() {
        return myaaraylist;
    }

    public void refreshList(){
        notifyDataSetChanged();
    }

    public void setMyaaraylist(List<Bill> myaaraylist) {
        this.myaaraylist = myaaraylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_bill, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

      //  myaaraylist = mycustomer.getBill();

        final Bill mydata = myaaraylist.get(position);

        final String billidtemp = String.valueOf(mydata.getBillId());

        holder.billid.setText("Bill ID: " + billidtemp);
        holder.billtype.setText("Bill Type: " + mydata.getBillType());
        holder.billdate.setText("Bill Date: " + mydata.getBillDate());
        holder.billamount.setText("Bill Amount: " + mydata.getBillAmount() + " $");




//        if(myvehicle == null)
//        {
//            holder.type.setText("No Vehicle");
//            holder.type.setTextColor(Color.RED);
//            holder.type.setTextSize(20);
//
//            holder.plate.setVisibility(View.GONE);
//            holder.make.setVisibility(View.GONE);
//            holder.model.setVisibility(View.GONE);
//
//
//        }
//        else {
//            holder.type.setText("Vehicle Type: "+myvehicle.getType());
//            holder.make.setText("Vehicle Make: "+myvehicle.getMake());
//            holder.model.setText("Vehicle Model: "+myvehicle.getModel());
//            holder.plate.setText("Vehicle Plate: "+myvehicle.getPlate());
//
//        }


        holder.mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String alldatavehicle = "Bill ID: " + billidtemp + "\n" + "Bill Type: " + mydata.getBillType() + "\n" + "Bill Date: " + mydata.getBillDate() + "\n" + "Bill Amount: " + mydata.getBillAmount() + " $";

                showtextDialog(context, alldatavehicle);
                //  Toast.makeText(context,"position = "+position,Toast.LENGTH_LONG).show();

            }
        });

//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Integer myindex = myaaraylist.indexOf(mydata);
//                myaaraylist.remove(myindex);
//                final UserDatabase uData = UserDatabase.getInstance(context);
//
//                uData.daoObjct().update(mycustomer);
//
//
//                Toast.makeText(context,"deleted position = "+position,Toast.LENGTH_LONG).show();
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return myaaraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView billid, billdate, billtype, billamount;
        CardView mycardview;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            //delete = itemView.findViewById(R.id.imageViewdelete);
            mycardview = itemView.findViewById(R.id.newcard);
            billid = itemView.findViewById(R.id.textView1);
            billtype = (TextView) itemView.findViewById(R.id.textView2);
            billdate = (TextView) itemView.findViewById(R.id.textView3);
            billamount = (TextView) itemView.findViewById(R.id.textView4);


        }
    }

    public void showtextDialog(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_vehicle);

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


    public void showdeleteDialog(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_vehicle);

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
