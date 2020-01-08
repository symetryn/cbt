/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.model;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * model for storing reference option elements
 * @author Symetryn
 */
public class OptionGroup {

    private TextField textField;

    private Button btn;

    private RadioButton box;

    public OptionGroup(TextField textField, Button btn, RadioButton box) {
        this.textField = textField;
        this.btn = btn;
        this.box = box;
    }

    
    //Getters and setters for option group model
    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public RadioButton getBox() {
        return box;
    }

    public void setBox(RadioButton box) {
        this.box = box;
    }
    
    

}
