package com.example.planningpokeruser.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpokeruser.R;

public class LoginUser extends Fragment {
    EditText userName;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v=inflater.inflate(R.layout.fragment_login_user, container, false);
        userName=v.findViewById(R.id.name);
        login=v.findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String sUserName=userName.getText().toString();
                Toast.makeText(getContext(), sUserName, Toast.LENGTH_SHORT).show();
                ViewGroups vg=new ViewGroups();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, vg);
                Bundle args=new Bundle();
                args.putString("UserName",sUserName);
                vg.setArguments(args);
                fr.commit();
            }
        });
        return v;
    }

}
