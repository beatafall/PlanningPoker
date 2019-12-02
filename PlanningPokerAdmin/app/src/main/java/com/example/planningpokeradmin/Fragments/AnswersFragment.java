package com.example.planningpokeradmin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.planningpokeradmin.Adapters.AnswerAdapter;
import com.example.planningpokeradmin.Adapters.QuestionAdapter;
import com.example.planningpokeradmin.Classes.Answer;
import com.example.planningpokeradmin.Classes.Question;
import com.example.planningpokeradmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnswersFragment extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Answer> list;
    AnswerAdapter adapter;
    RecyclerView recyclerView;
    DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Answers");
    TextView groupcode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_answers, container, false);
        groupcode = v.findViewById(R.id.grcode);
        String myStr = getArguments().getString("TheGroupCode");
        groupcode.setText(myStr);

        recyclerView = v.findViewById(R.id.theanswers);
        list = new ArrayList<>();

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    String txt = item.child("groupCode").getValue().toString();
                    String gr = groupcode.getText().toString();

                    if (txt.equals(gr)) {
                        String q = item.child("question").getValue().toString();
                        String u = item.child("userName").getValue().toString();
                        String a = item.child("answer").getValue().toString();
                        Answer answ = new Answer(u, q, a);
                        list.add(answ);
                    }
                }

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                adapter = new AnswerAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}