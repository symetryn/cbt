/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 4L;
    private int id;
    private int testId;
    private int userId;
    private int marks = 0;
    private Boolean status;
    private ArrayList<ResultItem> resultItem;
    private Test test;
    private User user;

    public Result() {
        resultItem = new ArrayList<>();
    }

    public Result(int id, int testId, int userId, int marks, Boolean status, ArrayList<ResultItem> resultItem) {
        this.id = id;
        this.testId = testId;
        this.userId = userId;
        this.marks = marks;
        this.status = status;
        this.resultItem = resultItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<ResultItem> getResultItem() {
        return resultItem;
    }

    public void setResultItem(ArrayList<ResultItem> resultItem) {
        this.resultItem = resultItem;
    }

    public void pushResultItem(ResultItem r) {
        resultItem.add(r);
    }

    public void addMarks(Integer i) {
        this.marks += i;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        Gson g = new Gson();
        return g.toJson(this);
    }

}
