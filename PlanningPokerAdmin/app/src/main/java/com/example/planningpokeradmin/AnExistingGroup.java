package com.example.planningpokeradmin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnExistingGroup extends Fragment {
    Button newquestion, viewanswers;
    TextView groupCode;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Question> list;
    QuestionAdapter adapter;
    DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Questions");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_an_existing_group, container, false);
        newquestion=v.findViewById(R.id.btnnewquestion);
        viewanswers=v.findViewById(R.id.btnanswers);
        groupCode=v.findViewById(R.id.groupcode);

        String myStr=getArguments().getString("GroupCode");
        groupCode.setText(myStr);

        newquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewQuestion gr=new AddNewQuestion();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, gr);
                String myStr=getArguments().getString("GroupCode");
                Bundle args=new Bundle();
                args.putString("TheGroupCode",myStr);
                gr.setArguments(args);
                fr.commit();
            }
        });

        recyclerView=v.findViewById(R.id.recyclerview_questions);
        list = new ArrayList<>();

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    String txt = item.child("groupKey").getValue().toString();
                    String gr = groupCode.getText().toString();
                    if (txt.equals(gr)) {
                        String q = item.child("question").getValue().toString();
                        Question q1 = new Question(q);
                        list.add(q1);
                    }
                }

                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                adapter = new QuestionAdapter(list);
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
