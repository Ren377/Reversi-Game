import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**A class that used to test the Reversi class 
  * Author Renren Deng, rxd256
  */

public class ReversiGameTester{
  
  
  /*Test the countWhiteButton method
   */
  @Test
  public void TestcountWhiteButton(){
    Reversi r1 = new Reversi();
    int expect = 2;   
    assertEquals("Test : Wrong amount", expect , Reversi.countWhiteButton());
  }
  
  /*Test the countBlackButton method
  */
  @Test
  public void TestcountBlackButton(){
      Reversi r1 = new Reversi();
    int expect = 2;
    assertEquals("Test : Wrong amount", expect , Reversi.countBlackButton());
  }
  

  }

