/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class Test implements Serializable {

    private int id;
    private String title;
    private ArrayList<Question> questions;
    private Date date;
    private int level;
    private int semester;
    private Time startTime;
    private Time endTime;
    private Time duration;
    private String password;

    private static final long serialVersionUID = 1L;

    public Test() {
        questions = new ArrayList();
    }

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

    public void pushQuestion(Question q) {
        this.questions.add(q);
    }

    public void removeQuestion(int i) {
        this.questions.remove(i);
    }

    public void updateQuestion(Question q, int index) {
        this.questions.set(index, q);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

 

}
