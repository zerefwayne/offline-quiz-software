/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author warlock
 */
public class Complex extends Question {
    
    private String content;
    private ArrayList<String>options;
    private ArrayList<String>correctResponses;
    
    public Complex(String _content,ArrayList<String>_options,ArrayList<String>_correctResponses){
        
        super(3,_content);
        
        this.content = _content;
        this.options = _options;
        this.correctResponses = _correctResponses;
    }

    public String getContent() {
        return content;
    }

//    public ArrayList<String> getOptions() {
//        return options;
//    }

    public ArrayList<String> getCorrectResponses() {
        return correctResponses;
    }
    
    public String getOption(int index){
        return options.get(index);
    }
    
}
