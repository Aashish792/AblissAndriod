package com.example.dell.ablissadrad.adapter;

//To display contents of home  in recycler view


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.ablissadrad.DocumentCategoryMain;
import com.example.dell.ablissadrad.main.Category_main;
import com.example.dell.ablissadrad.data.Home_Data;
import com.example.dell.ablissadrad.main.Information;
import com.example.dell.ablissadrad.R;

import java.util.List;

public class RecycleViewHomeAdapter extends RecyclerView.Adapter<RecycleViewHomeAdapter.MyHomeViewHolder> {
//implement methods then proceed to step 3

    //3 Declarration of variable
    private Context mcontext;
    //class as list
    String myerror = "This is error";
    private List<Home_Data> mhlist;

    //constructor


    public RecycleViewHomeAdapter(Context mcontext, List<Home_Data> mhlist) {
        this.mcontext = mcontext;
        this.mhlist = mhlist;
    }

    @Override
    public MyHomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mhinflater = LayoutInflater.from(mcontext);
        view = mhinflater.inflate(R.layout.card_home,viewGroup,false);
        return new MyHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHomeViewHolder holder, final int position) {

        //getting the data
        holder.tv_home_title.setText(mhlist.get(position).getHtitle());
        holder.tv_home_description.setText(mhlist.get(position).getHdescription());
        holder.home_thumbnail.setImageResource(mhlist.get(position).getHthumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mhlist.get(position).getHtitle().toUpperCase().equals("CATEGORIES")){

                    Intent intent = new Intent(mcontext,Category_main.class);
                    intent.putExtra("CategoryHomeTitle",mhlist.get(position).getHtitle());
                    mcontext.startActivity(intent);

                }

                else if (mhlist.get(position).getHtitle().toUpperCase().equals("INFORMATION")){


                    Intent intent = new Intent(mcontext, DocumentCategoryMain.class);
                    intent.putExtra("CategoryHomeTitle",mhlist.get(position).getHtitle());
                    mcontext.startActivity(intent);

                }

                else {

                    Intent intent = new Intent(mcontext,Information.class);
                    intent.putExtra("CategoryHomeTitle",mhlist.get(position).getHtitle());
                    mcontext.startActivity(intent);

                }


            }
        });




//


    }

    @Override
    public int getItemCount() {
        return mhlist.size();
    }


    //step 2 making class
    public static class MyHomeViewHolder extends RecyclerView.ViewHolder{
        TextView tv_home_title,tv_home_description;
        ImageView home_thumbnail;
        CardView cardView;

        public MyHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_home_title = (TextView) itemView.findViewById(R.id.category_title);
            home_thumbnail = (ImageView) itemView.findViewById(R.id.imageViewcategory);
            tv_home_description = (TextView) itemView.findViewById(R.id.categorydesc);
            cardView = (CardView) itemView.findViewById(R.id.cardviewhome_id);



        }
    }

}


