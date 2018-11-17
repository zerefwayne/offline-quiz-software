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
    
    
    public Good(String _content,ArrayList<String> _options,int _correctResponse){
        
        super(1);

        this.content = _content;
        this.options = _options;
        this.correctResponse = _correctResponse;
    
    }
}
