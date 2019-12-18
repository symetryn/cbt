/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import java.io.Serializable;

/**
 *
 * @author Symetryn
 */
public class ResultItem implements Serializable {

    private static final long serialVersionUID = 5L;
    private Integer questionId;
    private String correctAnswer;
    private String selectedAnswer;
    private Boolean correct;

    public ResultItem(Integer question, String correctAnswer, String selectedAnswer, Boolean correct) {
        this.questionId = question;
        this.correctAnswer = correctAnswer;
        this.selectedAnswer = selectedAnswer;
        this.correct = correct;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public ResultItem() {
    }

}
