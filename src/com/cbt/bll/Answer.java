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
public class Answer {
    int id;
    Boolean correctAnswer;
    int qid;
    
    public Answer(int id, boolean correctAnswer,int qid){
        this.id=id;
        this.correctAnswer=correctAnswer;
        this.qid=qid;
    }
}
