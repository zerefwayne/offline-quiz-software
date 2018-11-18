/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Classes.Complex;
import Classes.Tough;
import Classes.Good;
import Classes.Question;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.*;

public class QuizInterface extends javax.swing.JFrame {
    
    private String name;
    private String rollNumber;
    
    private ArrayList<Good> goodQuestions;
    private ArrayList<Tough> toughQuestions;
    private ArrayList<Complex> complexQuestions;
    
    private ArrayList<Question> activeQuestionList;
    
    private JButton[] questionButtonList;
    private boolean[] bookmarkList;
    
    
    private int nextOpenQuestionIndex;
    private int activeQuestionIndex;
    private int totalMarks;
    
    Timer timer;
    private int min=60,sec=0;
            

    /**
     * Creates new form Starting
     * @param _name
     * @param _rollNumber
     */
    public QuizInterface(String _name, String _rollNumber) {
        
        initComponents();
        
        this.goodQuestions = new ArrayList();
        this.toughQuestions = new ArrayList<>();
        this.complexQuestions = new ArrayList<>();
        this.bookmarkList = new boolean[20];
        
        Arrays.fill(bookmarkList, false);
        
        this.nextOpenQuestionIndex = 0;
        this.activeQuestionIndex = 0;
        
        this.questionButtonList = new JButton[20];
        this.activeQuestionList = new ArrayList<>();
    
        this.name = _name;
        this.rollNumber = _rollNumber;
        
        fillQuestionsFromCSV();
        initializeInterface();
        
        displayQuestion();
        
    }
    
    public void initializeInterface(){
    
        //Set the name and roll number on the header of the interface
        headerNameLabel.setText(this.name);
        headerRollNumberLabel.setText(this.rollNumber);
        
        fillQuestionsFromCSV();
        startTimer();
        initializeQuestionButtons();
            
    
    }
    
    public void startTimer() {
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(sec == 0) {
                    sec=60;
                    min--;
                }
                
                if(min<0) {
                    min =0;sec =0;
                    //time.setText("00:00");
                    JOptionPane.showMessageDialog(null,"Times UP!!");
                    timer.stop();
                    jButton1.doClick();
                    
                }
                sec--;
                //minute.setText(Integer.toString(min));
                //second.setText(Integer.toString(sec));
                if(min < 10 && sec < 10){
                    time.setText("0"+Integer.toString(min)+":"+"0"+Integer.toString(sec));
                }
                else if(min < 10 && sec >= 10 ) {
                    time.setText("0"+Integer.toString(min)+":"+Integer.toString(sec));
                }
                else{
                    time.setText(Integer.toString(min)+":"+Integer.toString(sec));
                }
                
            }
        });
        timer.start();
        
    }
    
    public void initializeQuestionButtons(){
        
        this.questionButtonList[0] = q1;
        this.questionButtonList[1] = q2;
        this.questionButtonList[2] = q3;
        this.questionButtonList[3] = q4;
        this.questionButtonList[4] = q5;
        this.questionButtonList[5] = q6;
        this.questionButtonList[6] = q7;
        this.questionButtonList[7] = q8;
        this.questionButtonList[8] = q9;
        this.questionButtonList[9] = q10;
        this.questionButtonList[10] = q11;
        this.questionButtonList[11] = q12;
        this.questionButtonList[12] = q13;
        this.questionButtonList[13] = q14;
        this.questionButtonList[14] = q15;
        this.questionButtonList[15] = q16;
        this.questionButtonList[16] = q17;
        this.questionButtonList[17] = q18;
        this.questionButtonList[18] = q19;
        this.questionButtonList[19] = q20;
        
    }
    
    public void fillQuestionsFromCSV(){
        
        // read GOOD Questions

        String goodQuestionFilePath = "/home/beaku/Documents/IOOM332C_OfflineQuizSoftware/src/data/goodQuestions.csv";                       // FILE PATH


        File questionFile = new File(goodQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile);
            while (inputStream.hasNextLine()){
                
                String ques = inputStream.nextLine();
                
                String[] values = ques.split(",");           // reading CSV file
                ArrayList<String>options = new ArrayList<>();
                
                
                options.add(values[1]);options.add(values[2]);options.add(values[3]);options.add(values[4]);
                
                goodQuestions.add(new Good(values[0],options,Integer.parseInt(values[5])));    
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        //read TOUGH Questions
        
        String toughQuestionFilePath = "/home/beaku/Documents/IOOM332C_OfflineQuizSoftware/src/data/toughQuestions.csv";                  // FILE PATH


        File questionFile1 = new File(toughQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile1);
            while (inputStream.hasNextLine()){
                String ques = inputStream.nextLine();
                String[] values = ques.split(",");           // reading CSV file
                toughQuestions.add(new Tough(values[0],Integer.parseInt(values[1])));    
            }
        }catch(FileNotFoundException e){
        }
        
        // COMPLEX Question
        

        String complexQuestionFilePath = "/home/beaku/Documents/IOOM332C_OfflineQuizSoftware/src/data/goodQuestions.csv";                  // FILE PATH


        File questionFile2 = new File(complexQuestionFilePath);

        try{
            Scanner inputStream = new Scanner(questionFile2);
            while (inputStream.hasNextLine()){
                String ques = inputStream.nextLine();
                String[] values = ques.split(",");           // reading CSV file
                ArrayList<String>options = new ArrayList<>();
                options.add(values[1]);options.add(values[2]);options.add(values[3]);options.add(values[4]);
                ArrayList<Integer>correctAns = new ArrayList<>();
                String[] correctAnswers = values[5].split("\\^");
                for(int i=0;i<correctAnswers.length;i++){
                    correctAns.add(Integer.parseInt(correctAnswers[i]));
                }
                Collections.sort(correctAns);
                complexQuestions.add(new Complex(values[0],options,correctAns));    
            }
        }catch(FileNotFoundException e){
        }
        
        
        
        
        
        
    }
    
    public void bookmarkButton(){
        
        if(this.bookmarkList[this.activeQuestionIndex] == true){
            
            this.buttonBookmark.setText("Unbookmark");
            this.questionButtonList[this.activeQuestionIndex].setBackground(Color.red);
           
            
            
        } else {
            
            this.buttonBookmark.setText("Bookmark");
            this.questionButtonList[this.activeQuestionIndex].setBackground(Color.white);
            
            
            
            
        }
        
        
    }
    
    public void displayQuestion(){
      
        
        if(this.activeQuestionIndex == this.nextOpenQuestionIndex){

            this.buttonNext.setEnabled(false);
            this.buttonBookmark.setEnabled(false);

            resetPanel();
            
            
        }
        else {
            
        Question activeQuestion = activeQuestionList.get(this.activeQuestionIndex);
        
        int selectedType = activeQuestion.getType();
        
        if(this.activeQuestionIndex != 19){
            this.buttonNext.setEnabled(true);
        }
        else{
            this.buttonNext.setEnabled(false);
        }
        this.buttonBookmark.setEnabled(true);
        
        bookmarkButton();
        
        if(selectedType == 1)
        {
                
            //implement GoodQuestion Display Here
            
            panelParent.removeAll();
            panelParent.add(panelGood);
            panelParent.repaint();
            panelParent.revalidate();
            labelGoodQuestion.setText(activeQuestion.getContent());
            goodOption1.setText(activeQuestion.getOption(0));
            goodOption2.setText(activeQuestion.getOption(1));
            goodOption3.setText(activeQuestion.getOption(2));
            goodOption4.setText(activeQuestion.getOption(3));
            
            
            
            
            
        }
        else if (selectedType == 2)
        {
            //implement ToughQuestion Display Here
            
            panelParent.removeAll();
            panelParent.add(panelTough);
            panelParent.repaint();
            panelParent.revalidate();
            labelToughQuestion.setText(activeQuestion.getContent());
            
            
            

            
        }
        else if(selectedType == 3)
        {
            //implement ComplexQuestion Display here
            
            panelParent.removeAll();
            panelParent.add(panelComplex);
            panelParent.repaint();
            panelParent.revalidate();
            labelComplexQuestion.setText(activeQuestion.getContent());
            complexOption1.setText(activeQuestion.getOption(0));
            complexOption2.setText(activeQuestion.getOption(1));
            complexOption3.setText(activeQuestion.getOption(2));
            complexOption4.setText(activeQuestion.getOption(3));
            
            
            
            
            
        }
            
        }
        
        
        
        
    }
    
    public void resetPanel(){
        
                
                panelParent.removeAll();
                panelParent.add(panelSelect);
                panelParent.repaint();
                panelParent.revalidate();
        
                questionButtonList[nextOpenQuestionIndex].setEnabled(true);
            
                
        
    }
        
    public void fetchNewQuestion(int _selectedType){
        
        //toggleBookmarkNext(); //turns on buttons
        Random random = new Random();
        
         
        if(_selectedType == 1){
            
            //generateRandomNumberAccordingToGoodQuestionSize
            int index = random.nextInt(goodQuestions.size());
            
            //JOptionPane.showMessageDialog(null, goodQuestions.size());
            
            //fetch Good Question Here  
            
            activeQuestionList.add(goodQuestions.get(index));
            goodQuestions.remove(index);
            
            //take from GoodQuestion List, add it to activeQuestionList[activeQuestionIndex]
           
            
            
            
            
            
            
            
        } else if(_selectedType == 2){
            
            //generateRandomNumberAccordingToToughQuestionSize
            int index = random.nextInt(toughQuestions.size());
            activeQuestionList.add(toughQuestions.get(index));
            toughQuestions.remove(index);
            
            
            
            
            //fetch Tough Question Here
            //take from ToughQuestion List, add it to activeQuestionList[activeQuestionIndex]
           
            
            
            
            
            
        } else if(_selectedType == 3){
            
            //generateRandomNumberAccordingToComplexQuestionSize
            int index = random.nextInt(complexQuestions.size());
            activeQuestionList.add(complexQuestions.get(index));
            complexQuestions.remove(index);
            
            
            
            //fetch Complex Question Here
            //take from GoodQuestion List, add it to activeQuestionList[activeQuestionIndex]
            
            
            
            
            
            
            
            
        }
        
        
        this.nextOpenQuestionIndex++;
        
        displayQuestion();
            
    }
    
    public void checkQuestions()
    {
        for(int i=0; i<activeQuestionList.size();i++)
        {
            Question tempQuestion= activeQuestionList.get(i);
            int tempQuestionType = tempQuestion.getType();
            switch(tempQuestionType) {
                case 1:
                Good tempGoodQuestion =(Good) tempQuestion;
                if(tempGoodQuestion.getSelectedAnswer()==tempGoodQuestion.getCorrectResponse())
                {
                    totalMarks+=3;
                    tempQuestion.setIsCorrect(true);
                }
                else
                {
                    totalMarks-=1;
                    tempQuestion.setIsCorrect(false);
                }
                break;
                
                case 2:
                Tough tempToughQuestion = (Tough) tempQuestion;    
                if(tempToughQuestion.getSelectedAnswer()==tempToughQuestion.getCorrectResponse())
                {
                    totalMarks+=4;
                    tempQuestion.setIsCorrect(true);
                }
                
                else
                {
                    totalMarks-=2;
                    tempQuestion.setIsCorrect(false);
                }
                break;
                
                case 3:
                Complex tempComplexQuestion = (Complex) tempQuestion; 
                ArrayList<Integer> tempCorrectAnswers = tempComplexQuestion.getCorrectResponses();
                ArrayList<Integer> tempSelectedAnswers = tempComplexQuestion.getSelectedAnswer();
                if(tempSelectedAnswers.equals(tempCorrectAnswers))
                {
                    totalMarks+=5;
                    tempQuestion.setIsCorrect(true);
                }
                
                else
                {
                    totalMarks-=3;
                    tempQuestion.setIsCorrect(false);
                }
               break;
       
            }
            
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        headerDetailsPanel = new javax.swing.JPanel();
        headerNameLabel = new javax.swing.JLabel();
        headerRollNumberLabel = new javax.swing.JLabel();
        headerTimePanel = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        headerOptionsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        panelQuestionButtons = new javax.swing.JPanel();
        q3 = new javax.swing.JButton();
        q1 = new javax.swing.JButton();
        q2 = new javax.swing.JButton();
        q4 = new javax.swing.JButton();
        q5 = new javax.swing.JButton();
        q6 = new javax.swing.JButton();
        q11 = new javax.swing.JButton();
        q9 = new javax.swing.JButton();
        q8 = new javax.swing.JButton();
        q7 = new javax.swing.JButton();
        q10 = new javax.swing.JButton();
        q13 = new javax.swing.JButton();
        q12 = new javax.swing.JButton();
        q17 = new javax.swing.JButton();
        q16 = new javax.swing.JButton();
        q15 = new javax.swing.JButton();
        q14 = new javax.swing.JButton();
        q20 = new javax.swing.JButton();
        q18 = new javax.swing.JButton();
        q19 = new javax.swing.JButton();
        buttonBookmark = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        panelParent = new javax.swing.JPanel();
        panelComplex = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        labelComplexQuestion = new javax.swing.JLabel();
        complexOption1 = new javax.swing.JCheckBox();
        complexOption3 = new javax.swing.JCheckBox();
        complexOption2 = new javax.swing.JCheckBox();
        complexOption4 = new javax.swing.JCheckBox();
        panelGood = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        labelGoodQuestion = new javax.swing.JLabel();
        goodOption1 = new javax.swing.JRadioButton();
        goodOption2 = new javax.swing.JRadioButton();
        goodOption3 = new javax.swing.JRadioButton();
        goodOption4 = new javax.swing.JRadioButton();
        panelTough = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        labelToughQuestion = new javax.swing.JLabel();
        toughAnswer = new javax.swing.JTextField();
        panelSelect = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        typeGood = new javax.swing.JRadioButton();
        typeTough = new javax.swing.JRadioButton();
        typeComplex = new javax.swing.JRadioButton();
        buttonTypeSelect = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelQuestionAttemptedCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelQuestionAttemptedCount1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1002, 576));
        setMinimumSize(new java.awt.Dimension(1002, 576));
        setPreferredSize(new java.awt.Dimension(1022, 576));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerDetailsPanel.setBackground(new java.awt.Color(44, 62, 80));

        headerNameLabel.setFont(new java.awt.Font("FreeSans", 0, 18)); // NOI18N
        headerNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerNameLabel.setText("Aayush Joglekar");

        headerRollNumberLabel.setFont(new java.awt.Font("FreeSans", 1, 14)); // NOI18N
        headerRollNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerRollNumberLabel.setText("IIT2017017");

        javax.swing.GroupLayout headerDetailsPanelLayout = new javax.swing.GroupLayout(headerDetailsPanel);
        headerDetailsPanel.setLayout(headerDetailsPanelLayout);
        headerDetailsPanelLayout.setHorizontalGroup(
            headerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerNameLabel)
                    .addComponent(headerRollNumberLabel))
                .addGap(0, 134, Short.MAX_VALUE))
        );
        headerDetailsPanelLayout.setVerticalGroup(
            headerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerDetailsPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(headerNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerRollNumberLabel)
                .addGap(66, 66, 66))
        );

        jPanel1.add(headerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 67));

        headerTimePanel.setBackground(new java.awt.Color(44, 62, 80));

        time.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("60:00");
        time.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("left");

        javax.swing.GroupLayout headerTimePanelLayout = new javax.swing.GroupLayout(headerTimePanel);
        headerTimePanel.setLayout(headerTimePanelLayout);
        headerTimePanelLayout.setHorizontalGroup(
            headerTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerTimePanelLayout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addGroup(headerTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerTimePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)))
                .addGap(160, 160, 160))
        );
        headerTimePanelLayout.setVerticalGroup(
            headerTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerTimePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(time)
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addGap(8, 8, 8))
        );

        jPanel1.add(headerTimePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 0, -1, -1));

        headerOptionsPanel.setBackground(new java.awt.Color(44, 62, 80));

        jButton1.setBackground(new java.awt.Color(46, 204, 113));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Submit");
        jButton1.setFocusPainted(false);

        javax.swing.GroupLayout headerOptionsPanelLayout = new javax.swing.GroupLayout(headerOptionsPanel);
        headerOptionsPanel.setLayout(headerOptionsPanelLayout);
        headerOptionsPanelLayout.setHorizontalGroup(
            headerOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerOptionsPanelLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(29, 29, 29))
        );
        headerOptionsPanelLayout.setVerticalGroup(
            headerOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(headerOptionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 284, -1));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(1022, 32767));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelQuestionButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelQuestionButtons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        q3.setBackground(new java.awt.Color(255, 255, 255));
        q3.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q3.setText("3");
        q3.setAlignmentY(0.0F);
        q3.setEnabled(false);
        q3.setFocusPainted(false);
        q3.setPreferredSize(new java.awt.Dimension(40, 40));
        q3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q3ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 40, 30));

        q1.setBackground(new java.awt.Color(255, 255, 255));
        q1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q1.setText("1");
        q1.setAlignmentY(0.0F);
        q1.setEnabled(false);
        q1.setFocusPainted(false);
        q1.setPreferredSize(new java.awt.Dimension(40, 40));
        q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q1ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        q2.setBackground(new java.awt.Color(255, 255, 255));
        q2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q2.setText("2");
        q2.setAlignmentY(0.0F);
        q2.setEnabled(false);
        q2.setFocusPainted(false);
        q2.setPreferredSize(new java.awt.Dimension(40, 40));
        q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q2ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 40, 30));

        q4.setBackground(new java.awt.Color(255, 255, 255));
        q4.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q4.setText("4");
        q4.setAlignmentY(0.0F);
        q4.setEnabled(false);
        q4.setFocusPainted(false);
        q4.setPreferredSize(new java.awt.Dimension(40, 40));
        q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q4ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 40, 30));

        q5.setBackground(new java.awt.Color(255, 255, 255));
        q5.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q5.setText("5");
        q5.setAlignmentY(0.0F);
        q5.setEnabled(false);
        q5.setFocusPainted(false);
        q5.setPreferredSize(new java.awt.Dimension(40, 40));
        q5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q5ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 40, 30));

        q6.setBackground(new java.awt.Color(255, 255, 255));
        q6.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q6.setText("6");
        q6.setAlignmentY(0.0F);
        q6.setEnabled(false);
        q6.setFocusPainted(false);
        q6.setPreferredSize(new java.awt.Dimension(40, 40));
        q6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q6ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 40, 30));

        q11.setBackground(new java.awt.Color(255, 255, 255));
        q11.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q11.setText("11");
        q11.setAlignmentY(0.0F);
        q11.setEnabled(false);
        q11.setFocusPainted(false);
        q11.setPreferredSize(new java.awt.Dimension(40, 40));
        q11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q11ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 50, 30));

        q9.setBackground(new java.awt.Color(255, 255, 255));
        q9.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q9.setText("9");
        q9.setAlignmentY(0.0F);
        q9.setEnabled(false);
        q9.setFocusPainted(false);
        q9.setPreferredSize(new java.awt.Dimension(40, 40));
        q9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q9ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 40, 30));

        q8.setBackground(new java.awt.Color(255, 255, 255));
        q8.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q8.setText("8");
        q8.setAlignmentY(0.0F);
        q8.setEnabled(false);
        q8.setFocusPainted(false);
        q8.setPreferredSize(new java.awt.Dimension(40, 40));
        q8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q8ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 40, 30));

        q7.setBackground(new java.awt.Color(255, 255, 255));
        q7.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q7.setText("7");
        q7.setAlignmentY(0.0F);
        q7.setEnabled(false);
        q7.setFocusPainted(false);
        q7.setPreferredSize(new java.awt.Dimension(40, 40));
        q7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q7ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 40, 30));

        q10.setBackground(new java.awt.Color(255, 255, 255));
        q10.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q10.setText("10");
        q10.setAlignmentY(0.0F);
        q10.setEnabled(false);
        q10.setFocusPainted(false);
        q10.setPreferredSize(new java.awt.Dimension(40, 40));
        q10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q10ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 50, 30));

        q13.setBackground(new java.awt.Color(255, 255, 255));
        q13.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q13.setText("13");
        q13.setAlignmentY(0.0F);
        q13.setEnabled(false);
        q13.setFocusPainted(false);
        q13.setPreferredSize(new java.awt.Dimension(40, 40));
        q13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q13ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 50, 30));

        q12.setBackground(new java.awt.Color(255, 255, 255));
        q12.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q12.setText("12");
        q12.setAlignmentY(0.0F);
        q12.setEnabled(false);
        q12.setFocusPainted(false);
        q12.setPreferredSize(new java.awt.Dimension(40, 40));
        q12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q12ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 50, 30));

        q17.setBackground(new java.awt.Color(255, 255, 255));
        q17.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q17.setText("17");
        q17.setAlignmentY(0.0F);
        q17.setEnabled(false);
        q17.setFocusPainted(false);
        q17.setPreferredSize(new java.awt.Dimension(40, 40));
        q17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q17ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 50, 30));

        q16.setBackground(new java.awt.Color(255, 255, 255));
        q16.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q16.setText("16");
        q16.setAlignmentY(0.0F);
        q16.setEnabled(false);
        q16.setFocusPainted(false);
        q16.setPreferredSize(new java.awt.Dimension(40, 40));
        q16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q16ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q16, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 50, 30));

        q15.setBackground(new java.awt.Color(255, 255, 255));
        q15.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q15.setText("15");
        q15.setAlignmentY(0.0F);
        q15.setEnabled(false);
        q15.setFocusPainted(false);
        q15.setPreferredSize(new java.awt.Dimension(40, 40));
        q15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q15ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q15, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 50, 30));

        q14.setBackground(new java.awt.Color(255, 255, 255));
        q14.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q14.setText("14");
        q14.setAlignmentY(0.0F);
        q14.setEnabled(false);
        q14.setFocusPainted(false);
        q14.setPreferredSize(new java.awt.Dimension(40, 40));
        q14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q14ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 50, 30));

        q20.setBackground(new java.awt.Color(255, 255, 255));
        q20.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q20.setText("20");
        q20.setAlignmentY(0.0F);
        q20.setEnabled(false);
        q20.setFocusPainted(false);
        q20.setPreferredSize(new java.awt.Dimension(40, 40));
        q20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q20ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q20, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 50, 30));

        q18.setBackground(new java.awt.Color(255, 255, 255));
        q18.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q18.setText("18");
        q18.setAlignmentY(0.0F);
        q18.setEnabled(false);
        q18.setFocusPainted(false);
        q18.setPreferredSize(new java.awt.Dimension(40, 40));
        q18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q18ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q18, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 50, 30));

        q19.setBackground(new java.awt.Color(255, 255, 255));
        q19.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        q19.setText("19");
        q19.setAlignmentY(0.0F);
        q19.setEnabled(false);
        q19.setFocusPainted(false);
        q19.setPreferredSize(new java.awt.Dimension(40, 40));
        q19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q19ActionPerformed(evt);
            }
        });
        panelQuestionButtons.add(q19, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 50, 30));

        mainPanel.add(panelQuestionButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 930, 50));

        buttonBookmark.setBackground(new java.awt.Color(102, 102, 102));
        buttonBookmark.setForeground(new java.awt.Color(255, 255, 255));
        buttonBookmark.setText("Bookmark");
        buttonBookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookmarkActionPerformed(evt);
            }
        });
        mainPanel.add(buttonBookmark, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        buttonNext.setBackground(new java.awt.Color(52, 152, 219));
        buttonNext.setForeground(new java.awt.Color(255, 255, 255));
        buttonNext.setText("Next");
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });
        mainPanel.add(buttonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, -1, -1));

        panelParent.setLayout(new java.awt.CardLayout());

        panelComplex.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        labelComplexQuestion.setBackground(new java.awt.Color(255, 255, 255));
        labelComplexQuestion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelComplexQuestion.setText("jLabel4");
        labelComplexQuestion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane1.setViewportView(labelComplexQuestion);

        complexOption1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(complexOption1);
        complexOption1.setText("jCheckBox1");
        complexOption1.setFocusPainted(false);
        complexOption1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complexOption1ActionPerformed(evt);
            }
        });

        complexOption3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(complexOption3);
        complexOption3.setText("jCheckBox1");
        complexOption3.setFocusPainted(false);

        complexOption2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(complexOption2);
        complexOption2.setText("jCheckBox1");
        complexOption2.setFocusPainted(false);

        complexOption4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(complexOption4);
        complexOption4.setText("jCheckBox1");
        complexOption4.setFocusPainted(false);

        javax.swing.GroupLayout panelComplexLayout = new javax.swing.GroupLayout(panelComplex);
        panelComplex.setLayout(panelComplexLayout);
        panelComplexLayout.setHorizontalGroup(
            panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComplexLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComplexLayout.createSequentialGroup()
                        .addGroup(panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelComplexLayout.createSequentialGroup()
                                .addComponent(complexOption3)
                                .addGap(228, 228, 228)
                                .addComponent(complexOption4)
                                .addGap(0, 180, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelComplexLayout.createSequentialGroup()
                        .addComponent(complexOption1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(complexOption2)
                        .addGap(192, 192, 192))))
        );
        panelComplexLayout.setVerticalGroup(
            panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComplexLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(complexOption1)
                    .addComponent(complexOption2))
                .addGap(18, 18, 18)
                .addGroup(panelComplexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(complexOption4)
                    .addComponent(complexOption3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelParent.add(panelComplex, "card2");

        panelGood.setBackground(new java.awt.Color(255, 255, 255));

        labelGoodQuestion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelGoodQuestion.setText("jLabel4");
        labelGoodQuestion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelGoodQuestion.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jScrollPane2.setViewportView(labelGoodQuestion);

        goodOption1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(goodOption1);
        goodOption1.setText("jRadioButton1");
        goodOption1.setFocusPainted(false);
        goodOption1.setRequestFocusEnabled(false);

        goodOption2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(goodOption2);
        goodOption2.setText("jRadioButton2");
        goodOption2.setFocusPainted(false);

        goodOption3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(goodOption3);
        goodOption3.setText("jRadioButton3");
        goodOption3.setFocusPainted(false);

        goodOption4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(goodOption4);
        goodOption4.setText("jRadioButton4");
        goodOption4.setFocusPainted(false);

        javax.swing.GroupLayout panelGoodLayout = new javax.swing.GroupLayout(panelGood);
        panelGood.setLayout(panelGoodLayout);
        panelGoodLayout.setHorizontalGroup(
            panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGoodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGoodLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGoodLayout.createSequentialGroup()
                        .addGroup(panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelGoodLayout.createSequentialGroup()
                                .addComponent(goodOption3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                                .addComponent(goodOption4))
                            .addGroup(panelGoodLayout.createSequentialGroup()
                                .addComponent(goodOption1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goodOption2)))
                        .addGap(172, 172, 172))))
        );
        panelGoodLayout.setVerticalGroup(
            panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGoodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goodOption1)
                    .addComponent(goodOption2))
                .addGap(18, 18, 18)
                .addGroup(panelGoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goodOption3)
                    .addComponent(goodOption4))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelParent.add(panelGood, "card3");

        panelTough.setBackground(new java.awt.Color(255, 255, 255));

        labelToughQuestion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelToughQuestion.setText("jLabel4");
        labelToughQuestion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane3.setViewportView(labelToughQuestion);

        toughAnswer.setName(""); // NOI18N

        javax.swing.GroupLayout panelToughLayout = new javax.swing.GroupLayout(panelTough);
        panelTough.setLayout(panelToughLayout);
        panelToughLayout.setHorizontalGroup(
            panelToughLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToughLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelToughLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(panelToughLayout.createSequentialGroup()
                        .addComponent(toughAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 422, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelToughLayout.setVerticalGroup(
            panelToughLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelToughLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(toughAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        panelParent.add(panelTough, "card4");

        panelSelect.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("FreeSans", 0, 18)); // NOI18N
        jLabel4.setText("Select The Type Of Question");

        typeGood.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(typeGood);
        typeGood.setText("Good");
        typeGood.setFocusPainted(false);
        typeGood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeGoodActionPerformed(evt);
            }
        });

        typeTough.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(typeTough);
        typeTough.setText("Tough");
        typeTough.setFocusPainted(false);

        typeComplex.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(typeComplex);
        typeComplex.setText("Complex");
        typeComplex.setFocusPainted(false);

        buttonTypeSelect.setBackground(new java.awt.Color(230, 126, 34));
        buttonTypeSelect.setFont(new java.awt.Font("FreeSans", 0, 18)); // NOI18N
        buttonTypeSelect.setForeground(new java.awt.Color(255, 255, 255));
        buttonTypeSelect.setText("Proceed");
        buttonTypeSelect.setFocusPainted(false);
        buttonTypeSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTypeSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSelectLayout = new javax.swing.GroupLayout(panelSelect);
        panelSelect.setLayout(panelSelectLayout);
        panelSelectLayout.setHorizontalGroup(
            panelSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSelectLayout.createSequentialGroup()
                .addGroup(panelSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSelectLayout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel4))
                    .addGroup(panelSelectLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(typeGood)
                        .addGap(46, 46, 46)
                        .addComponent(typeTough)
                        .addGap(41, 41, 41)
                        .addComponent(typeComplex))
                    .addGroup(panelSelectLayout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(buttonTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        panelSelectLayout.setVerticalGroup(
            panelSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSelectLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addGroup(panelSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeGood)
                    .addComponent(typeComplex)
                    .addComponent(typeTough))
                .addGap(33, 33, 33)
                .addComponent(buttonTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        panelParent.add(panelSelect, "card5");

        mainPanel.add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 640, 270));

        jPanel3.setBackground(new java.awt.Color(52, 152, 219));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Questions Bookmarked:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 40));

        labelQuestionAttemptedCount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelQuestionAttemptedCount.setForeground(new java.awt.Color(255, 255, 255));
        labelQuestionAttemptedCount.setText("12");
        jPanel3.add(labelQuestionAttemptedCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, -1, 40));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Questions Attempted:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        labelQuestionAttemptedCount1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelQuestionAttemptedCount1.setForeground(new java.awt.Color(255, 255, 255));
        labelQuestionAttemptedCount1.setText("14/20");
        jPanel3.add(labelQuestionAttemptedCount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookmarkActionPerformed
        // TODO add your handling code here:
        
        bookmarkList[this.activeQuestionIndex] = !bookmarkList[this.activeQuestionIndex];
        displayQuestion();
        
        
    }//GEN-LAST:event_buttonBookmarkActionPerformed

    private void q3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q3ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 2;
        displayQuestion();
        
    }//GEN-LAST:event_q3ActionPerformed

    private void q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q1ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 0;
        displayQuestion();
    }//GEN-LAST:event_q1ActionPerformed

    private void q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q2ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 1;
        displayQuestion();
    }//GEN-LAST:event_q2ActionPerformed

    private void q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q4ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 3;
        displayQuestion();
    }//GEN-LAST:event_q4ActionPerformed

    private void q5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q5ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 4;
        displayQuestion();
    }//GEN-LAST:event_q5ActionPerformed

    private void q6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q6ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 5;
        displayQuestion();
    }//GEN-LAST:event_q6ActionPerformed

    private void q11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q11ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 10;
        displayQuestion();
    }//GEN-LAST:event_q11ActionPerformed

    private void q9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q9ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 8;
        displayQuestion();
    }//GEN-LAST:event_q9ActionPerformed

    private void q8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q8ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 7;
        displayQuestion();
    }//GEN-LAST:event_q8ActionPerformed

    private void q7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q7ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 6;
        displayQuestion();
    }//GEN-LAST:event_q7ActionPerformed

    private void q10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q10ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 9;
        displayQuestion();
    }//GEN-LAST:event_q10ActionPerformed

    private void q13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q13ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 12;
        displayQuestion();
    }//GEN-LAST:event_q13ActionPerformed

    private void q12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q12ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 11;
        displayQuestion();
    }//GEN-LAST:event_q12ActionPerformed

    private void q17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q17ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 16;
        displayQuestion();
    }//GEN-LAST:event_q17ActionPerformed

    private void q16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q16ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 15;
        displayQuestion();
    }//GEN-LAST:event_q16ActionPerformed

    private void q15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q15ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 14;
        displayQuestion();
    }//GEN-LAST:event_q15ActionPerformed

    private void q14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q14ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 13;
    }//GEN-LAST:event_q14ActionPerformed

    private void q20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q20ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 19;
        displayQuestion();
    }//GEN-LAST:event_q20ActionPerformed

    private void q18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q18ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 17;
        displayQuestion();
    }//GEN-LAST:event_q18ActionPerformed

    private void q19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q19ActionPerformed
        // TODO add your handling code here:
        this.activeQuestionIndex = 18;
        displayQuestion();
    }//GEN-LAST:event_q19ActionPerformed

    private void typeGoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeGoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeGoodActionPerformed

    private void buttonTypeSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTypeSelectActionPerformed
        // TODO add your handling code here:
        
        
        int questionTypeSelected;
        
        //1 - goodQuestion 2 - toughQuestion 3 - complexQuestion 0 - questionSelectPanel
        
        if(typeGood.isSelected()){
            questionTypeSelected = 1;
        } else if(typeTough.isSelected()){
            questionTypeSelected = 2;
        } else {
            questionTypeSelected = 3;
        }
        
        fetchNewQuestion(questionTypeSelected);
        
        
        
    }//GEN-LAST:event_buttonTypeSelectActionPerformed

    private void complexOption1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complexOption1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_complexOption1ActionPerformed

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        // TODO add your handling code here:
        
        this.activeQuestionIndex++;
        displayQuestion();
        
    }//GEN-LAST:event_buttonNextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuizInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBookmark;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonTypeSelect;
    private javax.swing.JCheckBox complexOption1;
    private javax.swing.JCheckBox complexOption2;
    private javax.swing.JCheckBox complexOption3;
    private javax.swing.JCheckBox complexOption4;
    private javax.swing.JRadioButton goodOption1;
    private javax.swing.JRadioButton goodOption2;
    private javax.swing.JRadioButton goodOption3;
    private javax.swing.JRadioButton goodOption4;
    private javax.swing.JPanel headerDetailsPanel;
    private javax.swing.JLabel headerNameLabel;
    private javax.swing.JPanel headerOptionsPanel;
    private javax.swing.JLabel headerRollNumberLabel;
    private javax.swing.JPanel headerTimePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelComplexQuestion;
    private javax.swing.JLabel labelGoodQuestion;
    private javax.swing.JLabel labelQuestionAttemptedCount;
    private javax.swing.JLabel labelQuestionAttemptedCount1;
    private javax.swing.JLabel labelToughQuestion;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelComplex;
    private javax.swing.JPanel panelGood;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelQuestionButtons;
    private javax.swing.JPanel panelSelect;
    private javax.swing.JPanel panelTough;
    private javax.swing.JButton q1;
    private javax.swing.JButton q10;
    private javax.swing.JButton q11;
    private javax.swing.JButton q12;
    private javax.swing.JButton q13;
    private javax.swing.JButton q14;
    private javax.swing.JButton q15;
    private javax.swing.JButton q16;
    private javax.swing.JButton q17;
    private javax.swing.JButton q18;
    private javax.swing.JButton q19;
    private javax.swing.JButton q2;
    private javax.swing.JButton q20;
    private javax.swing.JButton q3;
    private javax.swing.JButton q4;
    private javax.swing.JButton q5;
    private javax.swing.JButton q6;
    private javax.swing.JButton q7;
    private javax.swing.JButton q8;
    private javax.swing.JButton q9;
    private javax.swing.JLabel time;
    private javax.swing.JTextField toughAnswer;
    private javax.swing.JRadioButton typeComplex;
    private javax.swing.JRadioButton typeGood;
    private javax.swing.JRadioButton typeTough;
    // End of variables declaration//GEN-END:variables
}
