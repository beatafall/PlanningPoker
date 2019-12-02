package com.example.planningpokeruser.Classes;

public class Answer {
    String userName;
    String question;
    String answer;
    String groupCode;

    public Answer(){
    }

    public Answer(String userName, String question, String answer) {
        this.userName = userName;
        this.question = question;
        this.answer = answer;
    }

    public Answer(String userName, String question, String answer, String groupCode) {
        this.userName = userName;
        this.question = question;
        this.answer = answer;
        this.groupCode = groupCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
