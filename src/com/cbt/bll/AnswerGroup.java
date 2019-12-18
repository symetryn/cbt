/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.bll;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author Symetryn
 */
public class AnswerGroup {

    private CheckBox check;

    private Label label;

    public AnswerGroup(CheckBox check, Label label) {
        this.check = check;
        this.label = label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public CheckBox getCheck() {
        return check;
    }

    public Label getLabel() {
        return label;
    }
    
    public Answer getAnswer(){
        return new Answer(label.getText(), check.isSelected());
    }

}
