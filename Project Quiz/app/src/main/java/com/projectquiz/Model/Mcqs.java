package com.projectquiz.Model;

/**
 * Created by omani on 12/22/2017.
 */
import java.io.Serializable;
import java.io.StringReader;

public class Mcqs implements Serializable {

    public static final long serialVersionUID = 1L;
    public String Question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String correct;

   public Mcqs() {
    };

    public Mcqs(String Quest, String A, String B,String C,String D,String correct) {
        this.Question = Quest;
        this.optionA = A;
        this.optionB = B;
        this.optionC=C;
        this.optionD=D;
        correct=correct;
    }
    public void SetMcqs(String Quest, String A, String B,String C,String D,String correct) {
        this.Question = Quest;
        this.optionA = A;
        this.optionB = B;
        this.optionC=C;
        this.optionD=D;
        correct=correct;
    }
    public String getQuestion()
    {
        return Question;
    }
    public String getOptionA()
    {

        return optionA;
    }
    public String getOptionB()
    {
        return optionB;
    }
    public String getOptionC()
    {
        return optionC;
    }
    public String getOptionD()
    {
        return optionD;
    }
    public String getCorrect()
    {
        return correct;
    }

    @Override
    public String toString() {
        return "Question:" + Question + "\noptionA: " + optionA + "\noptionB: " + optionB;
    }
}