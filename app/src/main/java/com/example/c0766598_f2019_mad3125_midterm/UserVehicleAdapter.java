package com.example.c0766598_f2019_mad3125_midterm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c0766598_f2019_mad3125_midterm.Activities.CustomerDetailsActivity;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;

import java.util.List;


public class UserVehicleAdapter extends RecyclerView.Adapter<UserVehicleAdapter.ViewHolder> {

    private Context context;
    private List<Bill> myaaraylist;


    public UserVehicleAdapter(Context context) {
        this.context = context;

    }




    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Bill> getMyaaraylist() {
        return myaaraylist;
    }

    public void setMyaaraylist(List<Bill> myaaraylist) {
        this.myaaraylist = myaaraylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_bill,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {

        final Bill mydata = myaaraylist.get(position);

        String billidtemp = String.valueOf(mydata.getBillId());

        holder.billid.setText("Bill ID: "+ billidtemp);
        holder.billtype.setText("Bill Type: "+mydata.getBillType());
        holder.billdate.setText("CUstomer Age: "+mydata.getBillDate());
        holder.billamount.setText("Customer ID: "+mydata.getBillAmount() + " $");




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

                Intent myintent = new Intent(context, CustomerDetailsActivity.class);
                myintent.putExtra("custobject",mydata);
                context.startActivity(myintent);
              //  Toast.makeText(context,"position = "+position,Toast.LENGTH_LONG).show();

            }
        });

    }





    @Override
    public int getItemCount() {
        return myaaraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView billid, billdate, billtype, billamount;
        CardView mycardview;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);

            mycardview = itemView.findViewById(R.id.newcard);
            billid = itemView.findViewById(R.id.textView1);
            billtype = (TextView)itemView.findViewById(R.id.textView2);
            billdate = (TextView)itemView.findViewById(R.id.textView3);
            billamount = (TextView)itemView.findViewById(R.id.textView4);




        }
    }


}
