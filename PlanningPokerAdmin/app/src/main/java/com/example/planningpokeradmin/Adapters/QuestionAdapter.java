package com.example.planningpokeradmin.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokeradmin.Classes.Question;
import com.example.planningpokeradmin.R;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    ArrayList<Question> questionlist;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void SetInactiv(int position);
        void SetActiv(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    public QuestionAdapter(ArrayList<Question> questionlist){
        this.questionlist=questionlist;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionitem, parent, false);
        return new ViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(questionlist.get(position).getQuestion());
       /* holder.activQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionlist.get(position).getAktivitas();
                Log.i("b",questionlist.get(position).getAktivitas());
            }
        });
        holder.inatctivQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionlist.get(position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        DataSnapshot dataSnapshot;
        Button activQuestion,inatctivQuestion;
        public ViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.questionitemtext);
            activQuestion=itemView.findViewById(R.id.aktiv);
            inatctivQuestion=itemView.findViewById(R.id.inaktiv);

            inatctivQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION && listener!=null){
                            listener.SetInactiv(position);
                        }
                    }
                }
            });

            activQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.SetActiv(position);
                        }
                    }
                }
            });

        }
    }


}
