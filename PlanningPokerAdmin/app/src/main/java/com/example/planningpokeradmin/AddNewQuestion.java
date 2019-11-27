package com.example.planningpokeradmin;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class AddNewQuestion extends Fragment {
    EditText questiontext;
    TextView groupcode;
    Button add,goback;
    Group group;
    Question question;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_add_new_question, container, false);
        questiontext = v.findViewById(R.id.theQuestion);
        add = v.findViewById(R.id.btnaddquestion);
        //goback=v.findViewById(R.id.goback);
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

