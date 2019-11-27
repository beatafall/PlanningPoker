package com.example.planningpokeruser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import static com.example.planningpokeruser.FirebaseDatabaseManager.Instance.questionsReference;

public class Questions extends Fragment {
    MyRecyclerViewAdapter adapter;
    TextView userName, questions, groupcode;
    ArrayList<Question> questionlist;
    RecyclerView recyclerView;
    TextView selected;
    Button vote,back,viewAnswers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_questions, container, false);

        questions = v.findViewById(R.id.tv_question);
        userName = v.findViewById(R.id.tv_votername);
        groupcode=v.findViewById(R.id.tv_groupcode);
        selected = v.findViewById(R.id.tv_selectednumber);
        vote=v.findViewById(R.id.button_vote);
        back=v.findViewById(R.id.back);
        viewAnswers=v.findViewById(R.id.viewanswers);

        String userNameString = getArguments().getString("UserName");
        userName.setText(userNameString);

        String groupCodeString = getArguments().getString("GroupCode");
        groupcode.setText(groupCodeString);

        String questionString = getArguments().getString("TheQuestion");
        questions.setText(questionString);

        String[] data = {"0", "2", "3", "5", "8", "13", "20", "40", "80", "100", "?", "c"};

        recyclerView=v.findViewById(R.id.recyclerview_answers);
        int numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter=new MyRecyclerViewAdapter(data);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
                selected.setText(adapter.getItem(position));
            }
        });


        recyclerView.setAdapter(adapter);

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtoDataBase();
            }
        });

        viewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answers answer=new Answers();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, answer);
                fr.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getActivity().getSupportFragmentManager();
                fm.popBackStack();

            }
        });

        return v;
    }
    public void  AddtoDataBase() {
        String susername=userName.getText().toString();
        String squestion=questions.getText().toString();
        String sgroupcode=groupcode.getText().toString();
        String sanswer=selected.getText().toString();
        String key = FirebaseDatabaseManager.Instance.UploadAnswers(susername,squestion,sanswer,sgroupcode);
        if (key== "Invalid") {
            Toast.makeText(getContext(),"Nem sikerult",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), key, Toast.LENGTH_SHORT).show();
        }

    }


}


