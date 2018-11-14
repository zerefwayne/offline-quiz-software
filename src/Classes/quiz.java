import Classes.Complex;
import Classes.Tough;
import Classes.good;
import interfaces.QuizInterface;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author warlock
 */
public class quiz {
        public ArrayList<good>goodQuestions = new ArrayList<>();
    public ArrayList<Tough>toughQuestions = new ArrayList<>();
    public ArrayList<Complex>complexQuestions = new ArrayList<>();
    public static void main(String[] args) {
        quiz quiz = new quiz();
        quiz.fillQuestionsFromCSV();
        interfaces.QuizInterface quizInterface;
        quizInterface = new QuizInterface();
    }
    public void fillQuestionsFromCSV(){
        
        // read GOOD Questions

        //UPDATE FILE PATH TO RUN PROJECT
        String goodQuestionFilePath = "..//data//goodQuestions.csv";                       // FILE PATH

        File questionFile = new File(goodQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile);
            while (inputStream.hasNextLine()){
                String ques = inputStream.nextLine();
                String[] values = ques.split(",");           // reading CSV file
                ArrayList<String>options = new ArrayList<>();
                options.add(values[1]);options.add(values[2]);options.add(values[3]);options.add(values[4]);
                goodQuestions.add(new good(values[0],options,Integer.parseInt(values[5])));    
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        //read TOUGH Question
        
        String toughQuestionFilePath = "";                  // FILE PATH

        File questionFile1 = new File(toughQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile1);
            while (inputStream.hasNextLine()){
                String ques = inputStream.nextLine();
                String[] values = ques.split(",");           // reading CSV file
                toughQuestions.add(new Tough(values[0],Integer.parseInt(values[1])));    
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        // COMPLEX Question
        
        String complexQuestionFilePath = "";                  // FILE PATH

        File questionFile2 = new File(complexQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile2);
            while (inputStream.hasNextLine()){
                String ques = inputStream.nextLine();
                String[] values = ques.split(",");           // reading CSV file
                ArrayList<String>options = new ArrayList<>();
                options.add(values[1]);options.add(values[2]);options.add(values[3]);options.add(values[4]);
                ArrayList<String>correctAns = new ArrayList<>();
                String[] correctAnswers = values[5].split("\\^");
                for(int i=0;i<correctAnswers.length;i++){
                    correctAns.add(correctAnswers[i]);
                }
                complexQuestions.add(new Complex(values[0],options,correctAns));    
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        
    }
    
}
