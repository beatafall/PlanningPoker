package com.example.planningpokeradmin.Classes;

public class Question {
    String question;
    String groupKey;
    String activity;

    public  Question(){
    }

    public Question(String question){
        this.question=question;
    }
    public Question(String groupKey,String question,String activity) {
        this.question = question;
        this.groupKey=groupKey;
        this.activity=activity;
    }

    public String getAktivitas() {
        return activity;
    }

    public void setAktivitas(String activity) {
        this.activity = activity;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
}
