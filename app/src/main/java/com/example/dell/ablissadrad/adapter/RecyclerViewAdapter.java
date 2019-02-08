package com.example.dell.ablissadrad.adapter;

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

import com.example.dell.ablissadrad.CategoryActivity;
import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.data.Category;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {

    private Context mContext;
    private List<Category> mData;

    public RecyclerViewAdapter(Context mContext, List<Category> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_categories,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {

       holder.tv_category_title.setText(mData.get(position).getTitle());
       holder.cat_image_thumbnai.setImageResource(mData.get(position).getThumbnail());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(mContext,CategoryActivity.class);
               intent.putExtra("CategoryTitle",mData.get(position).getTitle());
               mContext.startActivity(intent);



           }
       });




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tv_category_title;
        ImageView cat_image_thumbnai;
        CardView cardView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);


            tv_category_title = (TextView) itemView.findViewById(R.id.category_title);
            cat_image_thumbnai = (ImageView) itemView.findViewById(R.id.category_image);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }
}
