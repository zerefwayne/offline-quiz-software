/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author warlock
 */
public class Question {
    
    private int type;
    private String content;
    private Dictionary options;
    private int correctAnswer;
    private int selectedAnswer;
    private boolean isAttempted;
    private boolean isCorrect;
    private int marks;
    
    public Question(int _type){
        
        this.type = _type;
    
    }
    
    public String getContent() {
        return content;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public Dictionary getOptions() {
        return options;
    }

    public boolean isAttempted() {
        return isAttempted;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    
    public int getType(){
        return this.type;
    }

   
}
