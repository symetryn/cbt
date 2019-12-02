/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class Test implements Serializable{
    String title;
    ArrayList<Question> questions;
    
    private static final long serialVersionUID=1L;
   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    
}
