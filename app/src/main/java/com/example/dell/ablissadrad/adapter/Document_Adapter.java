package com.example.dell.ablissadrad.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.data.Documents;
import java.util.List;

import retrofit2.Callback;


public class Document_Adapter extends RecyclerView.Adapter<Document_Adapter.DocumentViewHolder> {

    private List<Documents> documentsList;
    private Callback<List<Documents>> mContext;

    public Document_Adapter(Callback<List<Documents>> mContext,List<Documents> documentsList) {
        this.documentsList = documentsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_documents,viewGroup,false);

        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder documentViewHolder, int i) {

        final Documents documents = documentsList.get(i);
        documentViewHolder.documentcat.setText(documents.getCategory());

    }

    @Override
    public int getItemCount() {
        return documentsList.size();
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder{
        CardView documentcardview;
        TextView documentcat;




        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);



            documentcardview = itemView.findViewById(R.id.cardviewinformation_id);
            documentcat = itemView.findViewById(R.id.textviewcatdocs);





        }
    }
}
