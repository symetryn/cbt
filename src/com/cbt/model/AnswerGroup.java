/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.model;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 *
 * @author Symetryn
 */
public class AnswerGroup {

    private RadioButton check;

    private Label label;

    public AnswerGroup(RadioButton check, Label label) {
        this.check = check;
        this.label = label;
    }
// Getters and setter method for answer model
    public void setLabel(Label label) {
        this.label = label;
    }

    public void setCheck(RadioButton check) {
        this.check = check;
    }

    public RadioButton getCheck() {
        return check;
    }

    public Label getLabel() {
        return label;
    }
    
    public Answer getAnswer(){
        return new Answer(label.getText(), check.isSelected());
    }

}
