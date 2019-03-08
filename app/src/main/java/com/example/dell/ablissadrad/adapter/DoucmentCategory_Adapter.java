package com.example.dell.ablissadrad.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.ablissadrad.Category_docs_activity;
import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.data.DocsCatData;

import java.util.List;

public class DoucmentCategory_Adapter extends RecyclerView.Adapter<DoucmentCategory_Adapter.MyviewHolder> {

    private Context mcontext;
    private List<DocsCatData> docsCatData;

    public DoucmentCategory_Adapter(Context mcontext, List<DocsCatData> docsCatData) {
        this.mcontext = mcontext;
        this.docsCatData = docsCatData;
    }

    @NonNull
    @Override
    public DoucmentCategory_Adapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mhinflater = LayoutInflater.from(mcontext);
        view = mhinflater.inflate(R.layout.card_documentscategories,viewGroup,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoucmentCategory_Adapter.MyviewHolder myviewHolder, final int i) {

       myviewHolder.tv_docs_category_title.setText(docsCatData.get(i).getTitle());
        myviewHolder.tv_docs_description.setText(docsCatData.get(i).getDescription());
        myviewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, Category_docs_activity.class);
                intent.putExtra("Docscattitle",docsCatData.get(i).getTitle());
                mcontext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return docsCatData.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tv_docs_category_title,tv_docs_description;
        CardView cardView;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardviewdocscat_id);
            tv_docs_category_title = itemView.findViewById(R.id.docscategory_title);
            tv_docs_description = itemView.findViewById(R.id.docscategorydesc);




        }
    }

}

