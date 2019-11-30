package com.example.planningpokeradmin.Fragments;


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
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpokeradmin.Classes.Group;
import com.example.planningpokeradmin.Database.FirebaseDatabaseManager;
import com.example.planningpokeradmin.Adapters.MyAdapter;
import com.example.planningpokeradmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddNewGroup extends Fragment {
    EditText groupCode;
    Button createGroup, viewanswer;
    DatabaseReference reff;
    Group group;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Group> list;
    MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v=inflater.inflate(R.layout.fragment_add_new_group, container, false);

        group=new Group();

        reff = FirebaseDatabase.getInstance().getReference().child("Groups");

        groupCode=v.findViewById(R.id.groupcode);
        createGroup=v.findViewById(R.id.btnnewgroup);
        viewanswer=v.findViewById(R.id.btnanswers);

        createGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AddtoDataBase();
            }
        });

        recyclerView=v.findViewById(R.id.recyclerview_groups);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        list=new ArrayList<>();

        adapter=new MyAdapter(list);

        getGroupCodeFirebase();

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list.get(position);
                String saveGroupCode=list.get(position).getGroupCode();
                Toast.makeText(getContext(), saveGroupCode, Toast.LENGTH_SHORT).show();
                AnExistingGroup gr=new AnExistingGroup();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, gr);
                Bundle args=new Bundle();
                args.putString("GroupCode",saveGroupCode);
                gr.setArguments(args);
                fr.commit();
            }
        });
        viewanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswersFragment a= new AnswersFragment();
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,a);
                fr.addToBackStack(null);
                fr.commit();
            }
        });


        return v;
    }


    public void  AddtoDataBase() {
        String sgroupcode = groupCode.getText().toString();
        String key = FirebaseDatabaseManager.Instance.UploadGroup(sgroupcode);
        if (key== "Invalid") {
            Toast.makeText(getContext(),"Nem sikerult",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), key, Toast.LENGTH_SHORT).show();
        }

    }

    void getGroupCodeFirebase(){
        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Group data=dataSnapshot.getValue(Group.class);
                list.add(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
