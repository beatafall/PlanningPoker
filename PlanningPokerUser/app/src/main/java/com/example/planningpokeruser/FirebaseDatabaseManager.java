package com.example.planningpokeruser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
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
        static DatabaseReference answersReference =database.getReference().child("Answers");

        public static String UploadAnswers(String sUsername, String sQuestion, String sAnswer, String sGroupCode) {
            Answer answer = new Answer(sUsername, sQuestion, sAnswer, sGroupCode);
            String key = answersReference.push().getKey();
            answersReference.child(key).setValue(answer);
            return key;
        }

        public static ArrayList<Question> getQuestionsForGroup(final String groupCode){
            questionsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot item :dataSnapshot.getChildren()){
                        String txt=item.child("groupKey").getValue().toString();

                        if (txt.equals(groupCode)) {
                            String q=item.child("question").getValue().toString();
                            Question q1=new Question(q);
                            questions.add(q1);
                            QuestionUpdate(questions);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return questions;
     }
        public static void QuestionUpdate(ArrayList<Question> q) {
            questions.addAll(q);

        }


}
}