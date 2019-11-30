package com.example.planningpokeradmin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.planningpokeradmin.Classes.Question;
import com.example.planningpokeradmin.Adapters.QuestionAdapter;
import com.example.planningpokeradmin.R;
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
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        recyclerView=v.findViewById(R.id.recyclerview_questions);
        list = new ArrayList<>();

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
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

                /*adapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
                    @Override
                    public void SetInactiv(final int position) {
                        for (DataSnapshot item : dataSnapshot.getChildren()) {
                            String activity = item.child("aktivitas").getValue().toString();
                            Log.i("aaa",activity);
                            if (activity.equals("Activ")) {
                                String q = list.get(position).getQuestion();
                                list.get(position).setAktivitas("Inactiv");
                            }
                        }
                    }

                    @Override
                    public void SetActiv(int position) {

                    }
                });*/

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}
