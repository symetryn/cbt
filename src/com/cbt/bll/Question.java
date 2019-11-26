/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import com.cbt.bll.Answer;

import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class Question {

    private int questionId;
    private String title;
    private ArrayList <Answer> answers;
    private int testId;
    
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    
    public void addAnswer(Answer answer) {
       this.answers.add(answer);
    }

    public void removeAnswer(int index) {        
        this.answers.remove(index);       
    }
  
   
    

}
