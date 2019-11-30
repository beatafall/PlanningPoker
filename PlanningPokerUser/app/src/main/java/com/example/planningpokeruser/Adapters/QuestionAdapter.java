package com.example.planningpokeruser.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokeruser.Classes.Question;
import com.example.planningpokeruser.R;

import java.util.ArrayList;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    ArrayList<Question> questionlist;
    private MyAdapter.OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MyAdapter.OnItemClickListener listener){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(questionlist.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView,final MyAdapter.OnItemClickListener listener ) {
            super(itemView);
            textView = itemView.findViewById(R.id.questionitemtext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }


}
