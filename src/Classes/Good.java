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
public class Good extends Question{

    private String content;
    private ArrayList<String> options;
    private int correctResponse;
    private int selectedAnswer;
    
    
    public Good(String _content,ArrayList<String> _options,int _correctResponse){
        
        super(1,_content);

        this.content = _content;
        this.options = _options;
        this.correctResponse = _correctResponse;
    
    }

    public String getContent() {
        return content;
    }

//    public ArrayList<String> getOptions() {
//        return options;
//    }

    public int getCorrectResponse() {
        return correctResponse;
    }
    
    public String getOption(int index){
        return options.get(index);
    }
    
    public int getSelectedAnswer() {
        return selectedAnswer;
    }
    
    
}
