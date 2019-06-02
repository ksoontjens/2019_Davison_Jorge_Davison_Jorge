package hellotvxlet;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

import java.awt.event.ActionEvent;

import org.havi.ui.HState;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Timer;


/**
 * Just a simple xlet that draws a String in the center of the screen.
 */
public class HelloTVXlet implements Xlet {

     Font font;
     HScene scene;
     Container gui;
     HStaticText labell;
     HStaticText currentAllighnemnt;
     HTextButton knop1;
     HTextButton knop2;
     HStaticText timer;
     HStaticText FinalAllignment;
     
     int currentAllignment = 0;
     
     String[] buttonNames = {"knop1", "knop2"};
     
    int index = 0;
     
     String[] questions = new String[] {"Dogs are more loyal than cats", "Dogs are kinder than cats", "dogs make for better pets than cats"};
     
    // String[] answers = new String[] {"yes", "no"};
     String answerYes = "yes";
     String answerNo = "No";
     int RightAnswer = 1;
     
     String CorrectAnswer = "Yes" ;
     

    /** Creates a new instance of HelloTVXlet */
    public HelloTVXlet() {
    }

    public void initXlet(XletContext context ) {
        scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.validate();
        
        currentAllighnemnt = new HStaticText("0", 0, 0, 20, 20);
        currentAllighnemnt.setBackground(Color.cyan);
        font = new Font(null, Font.PLAIN, 48);

        scene = HSceneFactory.getInstance().getDefaultHScene();
        currentAllighnemnt.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        FinalAllignment = new HStaticText("", 120, 215, 480, 300);
        FinalAllignment.setBackground(Color.blue);
        FinalAllignment.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        timer = new HStaticText("0", 650, 0, 30, 20);
        timer.setBackground(Color.ORANGE);
        timer.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        labell = new HStaticText("Wat voor dier vind u het beste?", 20, 100, 680, 100);
        labell.setBackground(Color.YELLOW);
        labell.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop1 = new HTextButton("Ja", 50, 250, 300, 100);
        knop1.setBackground(Color.GREEN);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setName(buttonNames[0]);
        
        knop2 = new HTextButton("Ja", 50, 400, 300, 100);
        knop2.setBackground(Color.RED);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setName(buttonNames[1]);
        
        knop1.setFocusTraversal(null, null, null, knop2);
        knop2.setFocusTraversal(null, null, knop1, null);
        
        knop1.setActionCommand(Integer.toString(1));
        knop2.setActionCommand(Integer.toString(2));
        
        knop1.addHActionListener((HActionListener) this);
        knop2.addHActionListener((HActionListener) this);
        
        scene.add(labell);
        scene.add(knop1);
        scene.add(knop2);
        scene.add(currentAllighnemnt);
        scene.add(timer);
        
        scene.validate();
        scene.setVisible(true);
        knop1.requestFocus();
        
        
        
    }

    public void startXlet() {
        
        TimerClass myTimeClass = new TimerClass(this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(myTimeClass, 1000, 1000);
        
    }

    public void pauseXlet() {
        
    }

    
    
    public void destroyXlet(boolean unconditional) {
    
      
    }
    
    
    public void actionPerformed(ActionEvent arg0) 
    {
       if(arg0.getActionCommand() == (CorrectAnswer))
       {
       currentAllignment ++;
       
       currentAllighnemnt.setTextContent(Integer.toString(currentAllignment), HState.NORMAL_STATE);
       if(index == questions.length) // check if the discussion is voer or not
       {
       onFinish();       
       }
       else 
       {
       askQuestions();
       }
       }
        
    scene.repaint();
        }
    
    public void askQuestions() 
    {
    labell.setTextContent(questions[index], HState.NORMAL_STATE);
    knop1.setTextContent(answerYes, HState.NORMAL_STATE);
    knop2.setTextContent(answerNo,HState.NORMAL_STATE);
    
    index++;
    }
    
    public void onFinish() 
    {
    labell.setTextContent("Je word gescoord op 3, hoe hoger je score hoe meer je van honden houd!", HState.NORMAL_STATE);
    scene.remove(knop1);
    scene.remove(knop2);
    scene.add(FinalAllignment);
    
    FinalAllignment.setTextContent(Integer.toString(currentAllignment), HState.NORMAL_STATE);
    
    }
    
}
