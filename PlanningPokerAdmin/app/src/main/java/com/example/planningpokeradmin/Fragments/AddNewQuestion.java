package com.example.planningpokeradmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planningpokeradmin.Classes.Group;
import com.example.planningpokeradmin.Classes.Question;
import com.example.planningpokeradmin.Database.FirebaseDatabaseManager;
import com.example.planningpokeradmin.R;


public class AddNewQuestion extends Fragment {
    EditText questiontext;
    TextView groupcode;
    Button add;
    Group group;
    Question question;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_add_new_question, container, false);
        questiontext = v.findViewById(R.id.theQuestion);
        add = v.findViewById(R.id.btnaddquestion);
        groupcode=v.findViewById(R.id.whatgroup);

        String myStr=getArguments().getString("TheGroupCode");
        groupcode.setText(myStr);

        group = new Group();
        question = new Question();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtoDataBase();
            }
        });

        return v;
    }

    public void  AddtoDataBase() {
        String sQuestion = questiontext.getText().toString();
        String sGroupcode=getArguments().getString("TheGroupCode");
        String sActivitas="Activ";
        String key = FirebaseDatabaseManager.Instance.UploadQuestions(sGroupcode,sQuestion,sActivitas);
        if (key== "Invalid") {
            Toast.makeText(getContext(),"Nem sikerult",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), key, Toast.LENGTH_SHORT).show();
        }

    }

}

