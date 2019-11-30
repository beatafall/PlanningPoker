package com.example.planningpokeruser.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.planningpokeruser.Classes.Question;
import com.example.planningpokeruser.Adapters.MyAdapter;
import com.example.planningpokeruser.Adapters.QuestionAdapter;
import com.example.planningpokeruser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupQuestions extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Question> list;
    TextView grcode, username;
    QuestionAdapter adapter;
    RecyclerView recyclerView;
    DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Questions");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_group_questions, container, false);
        grcode=v.findViewById(R.id.grcode);
        username=v.findViewById(R.id.username);

        String myStr=getArguments().getString("GroupCode");
        grcode.setText(myStr);

        String myStr2=getArguments().getString("UserName");
        username.setText(myStr2);

        recyclerView=v.findViewById(R.id.recyclerview_questions);
        list = new ArrayList<>();

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    String txt = item.child("groupKey").getValue().toString();
                    String aktivitas =item.child("aktivitas").getValue().toString();
                    String  gr=grcode.getText().toString();
                    if (txt.equals(gr) && aktivitas.equals("Activ")) {
                        String q = item.child("question").getValue().toString();
                        Question q1 = new Question(q);
                        list.add(q1);
                    }
                }

                recyclerView.setHasFixedSize(true);
                layoutManager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
                adapter=new QuestionAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        list.get(position);
                        String saveQuestione=list.get(position).getQuestion();
                        String saveGroupCode=grcode.getText().toString();
                        String saveUserName=username.getText().toString();
                        Questions quest=new Questions();
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.fragment_container, quest,null);
                        Bundle args=new Bundle();
                        args.putString("UserName",saveUserName);
                        args.putString("GroupCode",saveGroupCode);
                        args.putString("TheQuestion",saveQuestione);
                        quest.setArguments(args);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}