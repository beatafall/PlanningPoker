package com.example.planningpokeradmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.planningpokeradmin.Fragments.AddNewGroup;
import com.example.planningpokeradmin.R;

public class LoginAdmin extends Fragment {

    EditText adminName;
        Button login;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v;
            v = inflater.inflate(R.layout.fragment_login_admin, container, false);

            adminName=v.findViewById(R.id.name);
            login=v.findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new AddNewGroup());
                fr.commit();
            }
        });


        return v;
    }

}
