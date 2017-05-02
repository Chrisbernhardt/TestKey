import javax.swing.*;
import java.util.*;
import java.awt.*;;

public class makeTest
{
  int numberQ;
  String[][] questionsAnswers;
  String stuName;
  String key;
  String[][] doneTest;
  int[] answerKey;
  int mult;
  
  public makeTest(int numQ, String[][] qAndA, String name, int numMultiply)
  {
    mult = numMultiply;
    numberQ = numQ;
    questionsAnswers = qAndA;
    stuName = name;
    key = new keyMaker(stuName).getKey();
    scrambleTest();
    doneTest = questionsAnswers;
  }
  
  /* This scrables the test, using
   * each students name to scramble.
   */
  public void scrambleTest()
  {
    
    for(int x = 0; x < questionsAnswers.length; x ++)
    {
      System.out.println(Long.valueOf(Character.getNumericValue(key.charAt(x))) + "charlong");
      int toChange = (int)(randomGenerator(Long.valueOf(Character.getNumericValue(key.charAt(x)))) * questionsAnswers.length - 1);
      System.out.println(toChange + "");
      for(int y = 1; y < questionsAnswers[x].length; y ++)
      {
        toChange = (int)(randomGenerator(Long.valueOf(Character.getNumericValue(key.charAt(x)))) * questionsAnswers[x].length - 1);
        if(toChange == 0)
          toChange = 1;
        String tempOne = questionsAnswers[x][y];
        String TempTwo = questionsAnswers[x][toChange];
        questionsAnswers[x][y] = TempTwo;
        questionsAnswers[x][toChange] = tempOne;
      }
      toChange = (int)(randomGenerator(Long.valueOf(Character.getNumericValue(key.charAt(x)))) * questionsAnswers.length - 1);
      String[] temp1;
      String[] temp2;
      temp1 = questionsAnswers[x];
      temp2 = questionsAnswers[toChange];
      questionsAnswers[x] = temp2;
      questionsAnswers[toChange] = temp1;
    }
  }
  
  /* This simply returns the finished
   * test, in order to use in other
   * classes, possibly for statistics?
   */
  public String[][] returnTest()
  {
    return doneTest;
  }
  
  
  public double giveTest()
  {
    int correct = 0;
    int incorrect = 0;
    
    for(int x = 0; x < doneTest.length; x ++)
    {
      // Initialization variables
      JFrame frame = new JFrame("Test: " + stuName);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      frame.setUndecorated(true);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(false);
      int pressed;
      
      JPanel panel = new JPanel();
      
      panel.setLayout(new FlowLayout());
      
      JLabel question = new JLabel(doneTest[x][0]);

      JButton button1 = new JButton();
      button1.setText(doneTest[x][1]);
      JButton button2 = new JButton();
      button2.setText(doneTest[x][2]);
      JButton button3 = new JButton();
      button3.setText(doneTest[x][3]);
      JButton button4 = new JButton();
      button4.setText(doneTest[x][4]);
      
      question.updateUI();
      panel.add(question);
      button1.updateUI();
      panel.add(button1);
      button2.updateUI();
      panel.add(button2);
      button3.updateUI();
      panel.add(button3);
      button4.updateUI();
      panel.add(button4);
      
      panel.revalidate();
      panel.repaint();
      frame.remove(panel);
      frame.add(panel);
      frame.revalidate();
      frame.repaint();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      while(!button1.getModel().isPressed() || !button2.getModel().isPressed() || !button3.getModel().isPressed() || !button4.getModel().isPressed())
      {
        System.out.print("");
        if(button1.getModel().isPressed())
        {
          System.out.println("Bt1");
          pressed = 1;
          if(pressed == answerKey[x])
            correct += 1;
          else
            incorrect += 1;
          frame.setVisible(false);
          break;
        }
        if(button2.getModel().isPressed())
        {
          System.out.println("Bt2");
          pressed = 2;
          if(pressed == answerKey[x])
            correct += 1;
          else
            incorrect += 1;
          frame.setVisible(false);
          break;
        }
        if(button3.getModel().isPressed())
        {
          System.out.println("Bt3");
          pressed = 3;
          if(pressed == answerKey[x])
            correct += 1;
          else
            incorrect += 1;
          frame.setVisible(false);
          break;
        }
        if(button4.getModel().isPressed())
        {
          System.out.println("Bt4");
          pressed = 4;
          if(pressed == answerKey[x])
            correct += 1;
          else
            incorrect += 1;
          frame.setVisible(false);
          break;
        }
      }
      button1.setVisible(false);
      button2.setVisible(false);
      button3.setVisible(false);
      button4.setVisible(false);
      question.setVisible(false);
      System.out.println( ((double)correct)/((double)(incorrect + correct)) + " ");
    }
    
    JFrame frame = new JFrame("Test: " + stuName); 
    JLabel text = new JLabel("You scored a : " + (int)(((double)correct*100)/((double)(incorrect + correct))) + "%");
    frame.add(text);
    frame.setUndecorated(true);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(500,400);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return 999.99;
  }
  
  public int[] makeAnswerKey()
  {
    answerKey = new int[numberQ];
    for(int x = 0; x < doneTest.length; x ++)
    {
      for(int y = 0; y < doneTest[x].length; y ++)
      {
        if(doneTest[x][y].length() >= 8)
        {
          if(doneTest[x][y].substring(doneTest[x][y].length() - 7, doneTest[x][y].length()).equals("correct"))
          {
            answerKey[x] = y;
            doneTest[x][y] = doneTest[x][y].substring(0, doneTest[x][y].length() - 7);
          }
        }
      }
    }
    return answerKey;
  }
  
  double randomGenerator(long seed) {
    Random generator = new Random(seed * mult);
    double num = generator.nextDouble();
    
    return num;
  }

  /* This is a simple toString in
   * order to display the whole
   * test in a printable, digestable
   * format.
   */
  public String toString()
  {
    String toRet = "";
    for(int x = 0; x < answerKey.length; x ++)
    {
      toRet += answerKey[x] + " ";
    }
    return toRet;
  }
}