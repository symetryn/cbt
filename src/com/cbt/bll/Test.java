/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import com.google.gson.Gson;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class Test implements Serializable {

    private Integer id;
    private String title;
    private ArrayList<Question> questions;
    private Date date;
    private int level;
    private int semester;
    private Time startTime;
    private Time endTime;
    private int duration;
    private String password;
    private int passMarks;
    private int fullMarks;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPassMarks() {
        return passMarks;
    }

    public void setPassMarks(int passMarks) {
        this.passMarks = passMarks;
    }

    public int getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(int fullMarks) {
        this.fullMarks = fullMarks;
    }

    @Override
    public String toString() {
        Gson g = new Gson();
        return g.toJson(this);
//        return Gso questions.toString();

    }

}
