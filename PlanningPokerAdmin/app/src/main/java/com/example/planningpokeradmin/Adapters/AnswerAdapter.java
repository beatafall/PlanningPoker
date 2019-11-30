package com.example.planningpokeradmin.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokeradmin.Classes.Answer;
import com.example.planningpokeradmin.R;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    ArrayList<Answer> answerlist;

    public AnswerAdapter(ArrayList<Answer> answerlist){
        this.answerlist=answerlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.voters_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.user.setText(answerlist.get(position).getUserName());
        holder.question.setText(answerlist.get(position).getQuestion());
        holder.answer.setText(answerlist.get(position).getAnswer());
    }


    @Override
    public int getItemCount() {
        return answerlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, question,answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.voter_name);
            question= itemView.findViewById(R.id.voter_question);
            answer=itemView.findViewById(R.id.voter_answer);
        }
    }
}
