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
public class Answer implements Serializable{
    
    
      private static final long serialVersionUID=2L;
    String title;
    Boolean correctAnswer;

    public Answer(String title, boolean correctAnswer) {
        this.title = title;
        this.correctAnswer = correctAnswer;

    }
}
