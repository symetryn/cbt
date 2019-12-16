/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

/**
 *
 * @author Symetryn
 */
public class ResultItem {
    
    private String question;
    private String correctAnswer;
    private String SelectedAnswer;

    public ResultItem(String question, String correctAnswer, String SelectedAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.SelectedAnswer = SelectedAnswer;
    }

    public String getSelectedAnswer() {
        return SelectedAnswer;
    }

    public void setSelectedAnswer(String SelectedAnswer) {
        this.SelectedAnswer = SelectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    
    
}
