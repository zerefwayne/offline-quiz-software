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
public class Tough extends Question{
    
    private String content;
    private int correctResponse;
    
    public Tough(String _content,int _correctResponse){
        super(2);
        this.content = _content;
        this.correctResponse = _correctResponse;
    }

}
