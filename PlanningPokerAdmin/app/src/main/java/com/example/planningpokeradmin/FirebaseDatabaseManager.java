package com.example.planningpokeradmin;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabaseManager {
    public static ArrayList<Question> questions = new ArrayList<>();

    public FirebaseDatabaseManager() {

    }

    public static class Instance {
        static FirebaseDatabase database = FirebaseDatabase.getInstance();
        static DatabaseReference qroupReference = database.getReference().child("Groups");
        static DatabaseReference questionsReference = database.getReference().child("Questions");

        public static String UploadGroup(String sGroupCode) {
            Group group = new Group(sGroupCode);
            String key = qroupReference.push().getKey();
            qroupReference.child(key).setValue(group);
            return key;
        }

        public static String UploadQuestions(String sGroupCode, String sQuestion, String sActivity) {
            Question question = new Question(sGroupCode,sQuestion,sActivity);
            String key = questionsReference.push().getKey();
            questionsReference.child(key).setValue(question);
            return key;
        }

    }
}