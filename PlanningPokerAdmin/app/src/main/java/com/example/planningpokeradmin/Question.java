package com.example.planningpokeradmin;

public class Question {
    String question;
    String groupKey;
    String aktivitas;

    public  Question(){
    }

    public Question(String question){
        this.question=question;
    }
    public Question(String groupKey,String question,String aktivitas) {
        this.question = question;
        this.groupKey=groupKey;
        this.aktivitas=aktivitas;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
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
