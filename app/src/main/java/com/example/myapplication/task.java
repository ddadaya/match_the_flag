package com.example.myapplication;

public class task {
    int id;
    String que,ans1,ans2,correct;
    public task() {
    }

   public task(int id, String questionText, String ans1, String ans2,String correct) {
        this.id = id;
        this.que = questionText;
        this.ans1=ans1;
        this.ans2=ans2;
        this.correct=correct;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return que;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getCorrect() {
        return correct;
    }
}
