/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author warlock
 */
public class Complex extends Question {
    
    private String content;
    private ArrayList<String>options;
    private ArrayList<Integer>correctResponses;
    private ArrayList<Integer>selectedAnswer;
    
    public Complex(String _content,ArrayList<String>_options,ArrayList<Integer>_correctResponses){
        
        super(3,_content);
        
        this.content = _content;
        this.options = _options;
        this.correctResponses = _correctResponses;
    }

    public ArrayList<Integer> getCorrectResponses() {
        return correctResponses;
    }
    
    public String getOption(int index){
        return options.get(index);
    }

    public ArrayList<Integer> getSelectedAnswer() {
        return selectedAnswer;
    }
    
    public void setSelectedAnswer(ArrayList<Integer> answers){
      
        this.selectedAnswer = answers;
       
        
    }
    
    
    
    
}
