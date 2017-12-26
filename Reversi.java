import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**A class that used to Create the Reversi Game 
  * This class creates a game board and many buttons that could be pressed
  * Author Renren Deng, rxd256
  */
public class Reversi extends JFrame implements ActionListener{
  
  /**A field that stores the buttons of the game
    */
  private static JButton[][] buttons;
  
  /**A field that stores the boardsize of the game
    */
  private static int boardsize;
  
  /**A field that stores the height of the game
    */
  private static int height;
  
  /**A field that stores the width of the game
    */
  private static int width;
  
  /**A field that used to stores the order of the player in the game
    * if the field is an odd number, then that means it's White turn
    * if the field is a even number, then that means it's Black turn
    */
  private static int count;
  
  /**A field that stores the current row location on the game board when the button is pressed
    */
  private static int currentRow;
  
  /**A field that stores the current column location on the game board when the button is pressed
    */
  private static int currentCol;
  
  /**A field that stores the total numbers of white buttons on the game board
    */
  private static int whiteButtonNumber = 0;
  
  /**A field that stores the total numbers of black buttons on the game board
    */
  private static int blackButtonNumber = 0;
  
  /** The main method of this class
    * it can creat a default 8*8 buttons' board
    * it can also create a boardsize*boardsize buttons' board
    * it can also create a height*boardsize buttons' board
    */
  public static void main(String[] args) {
    if (args.length == 0)
      new Reversi();
    else if (args.length == 1)
      new Reversi (Integer.parseInt(args[0]));
    else if (args.length == 2)
      new Reversi (Integer.parseInt(args[0]), Integer.parseInt(args[1]));
  }
  
  /**
   * Create the game board, set the frame size, and add 8*8 buttons frame
   * @param board, the JPanel represents the new grid layout of the game board.
   * @param buttons, the JButton represents the buttons on the board.
   * @param c, the Container represents the content pane of the board.
   */
  public Reversi(){    
    try { /* make the board available in both Windows system and Mac system */
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    
    JPanel board = new JPanel(new GridLayout(8,8)); 
    buttons = new JButton[8][8];
    
    /* set the background color of every button to be light gray*/
    for(int i = 0; i < buttons.length; i++){
      for (int j = 0; j< buttons[i].length; j++){
        buttons[i][j] = new JButton();
        buttons[i][j].setBackground(Color.lightGray);
        buttons[i][j].addActionListener(this);
        board.add(buttons[i][j]);
      }
    }   
    /* Set the center of the game board with 2 white buttons and 2 black buttons.*/
    buttons[4][4].setBackground(Color.BLACK);
    buttons[3][3].setBackground(Color.BLACK);
    buttons[3][4].setBackground(Color.WHITE);
    buttons[4][3].setBackground(Color.WHITE);
    
    Container c = this.getContentPane();
    c.add(board, "Center");
    this.setSize(800,800);  
    this.setVisible(true);
  }
  
  /**
   * Create the game board, set the frame size, and add boardsize*boardsize buttons frame
   * @param board, the JPanel represents the new grid layout of the game board.
   * @param buttons, the JButton represents the buttons on the board.
   * @param c, the Container represents the content pane of the board.
   */
  public Reversi (int boardsize){  
    try { /* make the board available in both Windows system and Mac system */
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }    
    
    JPanel board = new JPanel(new GridLayout(boardsize,boardsize));
    buttons = new JButton [boardsize][boardsize];
    
    /* set the background color of every button to be light gray*/
    for(int i = 0; i < buttons.length; i++){
      for (int j = 0; j< buttons[i].length; j++){
        buttons[i][j] = new JButton();
        board.add(buttons[i][j]);
        buttons[i][j].setBackground (Color.lightGray);
        buttons[i][j].addActionListener(this);
      }
    }
    
    /* Set the center of the game board with 2 white buttons and 2 black buttons.*/
    buttons[boardsize/2][boardsize/2].setBackground(Color.BLACK);
    buttons[boardsize/2 - 1][boardsize/2 - 1].setBackground(Color.BLACK);
    buttons[boardsize/2 - 1][boardsize/2].setBackground(Color.WHITE);
    buttons[boardsize/2][boardsize/2 - 1].setBackground(Color.WHITE);
    
    Container c = this.getContentPane();
    c.add(board); 
    this.setSize(800,800);  
    this.setVisible(true);
  }
  
  /**
   * Create the game board, set the frame size, and add height*width buttons frame
   * @param board, the JPanel represents the new grid layout of the game board.
   * @param buttons, the JButton represents the buttons on the board.
   * @param c, the Container represents the content pane of the board.
   */
  public Reversi (int height, int width){
    try {/* make the board available in both Windows system and Mac system */
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    
    JPanel board = new JPanel(new GridLayout(height , width));
    buttons = new JButton [height][width];
    
    /* set the background color of every button to be light gray*/
    for(int i = 0; i < buttons.length; i++){
      for (int j = 0; j< buttons[i].length; j++){
        buttons[i][j] = new JButton();
        board.add(buttons[i][j]);
        buttons[i][j].setBackground (Color.lightGray);
        buttons[i][j].addActionListener(this);
      }
    }     
    /* Set the center of the game board with 2 white buttons and 2 black buttons.*/
    buttons[height/2][width/2].setBackground(Color.BLACK);
    buttons[height/2 - 1][width/2 - 1].setBackground(Color.BLACK);
    buttons[height/2 - 1][width/2].setBackground(Color.WHITE);
    buttons[height/2][width/2 - 1].setBackground(Color.WHITE);
    
    Container c = this.getContentPane();
    c.add(board);
    if (height < 2 || width < 2) /*throw exception when the board size is smaller than 2*/
      throw new ArrayIndexOutOfBoundsException ("Illegal inputs: the height/width should be larger than 2");   
    this.setSize(800,800);   
    this.setVisible(true);    
  }
  
  /** 
   * Override the actionPerformed menthod in ActionListener class.
   * set the rule of the game: 
   * @param JButton b, used to represent the button that is pressed
   */
  @Override
  public void actionPerformed (ActionEvent e){
    JButton b = (JButton)e.getSource();
    for(int i = 0; i < buttons.length; i++){    /*search all the buttons on the board*/
      for (int j = 0; j< buttons[i].length; j++){ /*search all the buttons on the board*/
        if (buttons[i][j].getBackground() == Color.lightGray ){ /* when the button has not been pressed*/
          /*When it's Black turn*/
          if (b == buttons[i][j] && (count % 2 == 0)){ 
            /*check every direction of the pressed buttons and flip the buttons if the move is legal*/
            if(this.lookRow(b) || this.lookCol(b) ||this.lookLeftUpDiagonal(b) || this.lookLeftDownDiagonal(b) ||
               this.lookRightUpDiagonal(b) || this.lookRightDownDiagonal(b)){
              b.setBackground(Color.BLACK); /*set the color of the button pressed to be black*/
              count = count + 1; /*now it's white turn*/
            }
          }
          /*when it's white turn*/
          else if (b == buttons[i][j] && (count % 2 == 1)){
            if(this.lookRow(b) || this.lookCol(b) ||this.lookLeftUpDiagonal(b) ||this.lookLeftDownDiagonal(b) ||
               this.lookRightUpDiagonal(b) || this.lookRightDownDiagonal(b)){
              b.setBackground(Color.WHITE);
              count = count + 1;
            }
          }
        }
        /*when the button has been pressed, throw a new frame and notice the reader that this move is not allowed*/
        else if (b == buttons[i][j] && buttons[i][j].getBackground() != Color.lightGray){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame,"This button has been pressed!","Illegal Move!", JOptionPane.ERROR_MESSAGE);          
        }
        /*when there is no more legal move, stop the game and announce the winner*/
        else 
          this.finish(b);
      }
    }    
  }
  
  /** get the current row location of the button being pressed
    * @return the current row number
    */
  public static int getCurrentRow (JButton b1){
    for(int i = 0; i < buttons.length; i++){
      for (int j = 0; j< buttons[i].length; j++){
        if (b1 == buttons[i][j])
          currentRow = j;
      }
    }
    return currentRow;
  }
  
  /** get the current column location of the button being pressed
    * @return the current column number
    */
  public static int getCurrentCol (JButton b1){
    for(int i = 0; i < buttons.length; i++){
      for (int j = 0; j< buttons[i].length; j++){
        if (b1 == buttons[i][j])
          currentCol = i;
      }
    }
    return currentCol;
  }
  
  /** look through the whole row of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the row
    */
  public static boolean lookRow(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    for (int i = 0; i < buttons.length; i++){ /*check all the buttons in the same row*/
      if (count % 2 == 0){  /*when it's black turn*/
        if (buttons[i][getCurrentRow(b1)].getBackground() == Color.BLACK){ /*when find a black button/buttons in the same row*/ 
          if (getCurrentCol(b1) > i && Math.abs(getCurrentCol(b1) - i) - 1 > 0){ /* when the black button/buttons is/are on the left side*/
            for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){ /*search all the buttons between the black buttons*/
              if (buttons[i+k][getCurrentRow(b1)].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[i+k][getCurrentRow(b1)].getBackground() == Color.WHITE)
                  least = least + 1;
              }
              else
                illegalNumber = illegalNumber + 1;          
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same row*/
            if(legalNumber >= Math.abs(getCurrentCol(b1) - i) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){
                if (buttons[i + k][getCurrentRow(b1)].getBackground() == Color.WHITE){
                  buttons[i + k][getCurrentRow(b1)].setBackground(Color.BLACK);
                  lookCol(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          }    
          if (getCurrentCol(b1) < i && Math.abs(getCurrentCol(b1) - i) - 1 > 0){ /* when the black button/buttons is/are on the right side*/
            for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){ /*search all the buttons between the black buttons*/
              if (buttons[i - k][getCurrentRow(b1)].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[i - k][getCurrentRow(b1)].getBackground() == Color.WHITE)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same row*/
            if(legalNumber >= Math.abs(getCurrentCol(b1) - i) - 1 && illegalNumber == 0  && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){
                if (buttons[i - k][getCurrentRow(b1)].getBackground() == Color.WHITE){
                  buttons[i - k][getCurrentRow(b1)].setBackground(Color.BLACK);
                  lookCol(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          } 
        }
      }  
      else if (count % 2 == 1){ /*when it's white turn*/
        if (buttons[i][getCurrentRow(b1)].getBackground() == Color.WHITE){ /*when find a white button/buttons in the same row*/ 
          if (getCurrentCol(b1) > i && Math.abs(getCurrentCol(b1) - i) - 1 > 0){ /* when the white button/buttons is/are on the left side*/
            for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){ /*search all the buttons between the white buttons*/
              if (buttons[i + k][getCurrentRow(b1)].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[i + k][getCurrentRow(b1)].getBackground() == Color.BLACK)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same row*/
            if(legalNumber >= Math.abs(getCurrentCol(b1) - i) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){
                if (buttons[i + k][getCurrentRow(b1)].getBackground() == Color.BLACK){
                  buttons[i + k][getCurrentRow(b1)].setBackground(Color.WHITE);
                  lookCol(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          }       
          if (getCurrentCol(b1) < i && Math.abs(getCurrentCol(b1) - i) - 1 > 0){ /* when the white button/buttons is/are on the right side*/
            for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){  /*search all the buttons between the white buttons*/
              if (buttons[i - k][getCurrentRow(b1)].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[i - k][getCurrentRow(b1)].getBackground() == Color.BLACK)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same row*/
            if(legalNumber >= Math.abs(getCurrentCol(b1) - i) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentCol(b1) - i) - 1 ; k++){
                if (buttons[i - k][getCurrentRow(b1)].getBackground() == Color.BLACK){
                  buttons[i - k][getCurrentRow(b1)].setBackground(Color.WHITE);
                  lookCol(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else  /*otherwise, the move is not allowed and nothing changes*/
              legal = false; 
          }
        }   
      }
    }    
    return legal;
  }
  
  /** look through the whole column of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the column
    */
  public static boolean lookCol(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    for (int j = 0; j < buttons[getCurrentCol(b1)].length; j++){ /*check all the buttons in the same column*/
      if (count % 2 == 0){ /*when it's black turn*/
        if (buttons[getCurrentCol(b1)][j].getBackground() == Color.BLACK){  /*when find a black button/buttons in the same column*/ 
          if (getCurrentRow(b1) > j && Math.abs(getCurrentRow(b1) - j) - 1 > 0){ /* when the black button/buttons is/are on the up side*/
            for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){ /*search all the buttons between the black buttons*/
              if (buttons[getCurrentCol(b1)][j + k].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[getCurrentCol(b1)][j + k].getBackground() == Color.WHITE)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same column*/
            if(legalNumber >= Math.abs(getCurrentRow(b1) - j) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){
                if (buttons[getCurrentCol(b1)][j + k].getBackground() == Color.WHITE){
                  buttons[getCurrentCol(b1)][j + k].setBackground(Color.BLACK);
                  lookRow(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          }        
          if (getCurrentRow(b1) < j && Math.abs(getCurrentRow(b1) - j) - 1 > 0){ /* when the white button/buttons is/are on the down side*/
            for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){ /*search all the buttons between the black buttons*/
              if (buttons[getCurrentCol(b1)][j - k].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[getCurrentCol(b1)][j - k].getBackground() == Color.WHITE)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same column*/
            if(legalNumber >= Math.abs(getCurrentRow(b1) - j) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){
                if (buttons[getCurrentCol(b1)][j - k].getBackground() == Color.WHITE){
                  buttons[getCurrentCol(b1)][j - k].setBackground(Color.BLACK);
                  lookRow(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          } 
        }
      }      
      else if (count % 2 == 1){ /*when it's white turn*/
        if (buttons[getCurrentCol(b1)][j].getBackground() == Color.WHITE){ /*when find a white button/buttons in the same column*/ 
          if (getCurrentRow(b1) > j && Math.abs(getCurrentRow(b1) - j) - 1 > 0){ /* when the white button/buttons is/are on the up side*/
            for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){ /*search all the buttons between the white buttons*/
              if (buttons[getCurrentCol(b1)][j + k].getBackground() != Color.lightGray){ /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[getCurrentCol(b1)][j + k].getBackground() == Color.BLACK)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same column*/
            if(legalNumber >= Math.abs(getCurrentRow(b1) - j) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){
                if (buttons[getCurrentCol(b1)][j + k].getBackground() == Color.BLACK){
                  buttons[getCurrentCol(b1)][j + k].setBackground(Color.WHITE);
                  lookRow(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false;
          }
          if (getCurrentRow(b1) < j && Math.abs(getCurrentRow(b1) - j) - 1 > 0){/* when the white button/buttons is/are on the down side*/
            for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){ /*search all the buttons between the white buttons*/
              if (buttons[getCurrentCol(b1)][j - k].getBackground() != Color.lightGray){  /*decide weather the move is legal*/
                legalNumber = legalNumber + 1;
                if (buttons[getCurrentCol(b1)][j - k].getBackground() == Color.BLACK)
                  least = least + 1;
              }
              else 
                illegalNumber = illegalNumber + 1;
            }
            /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
             * flip all the buttons that are legal to flip, including buttons in the same column*/
            if(legalNumber >= Math.abs(getCurrentRow(b1) - j) - 1 && illegalNumber == 0 && least >= 1){
              legal = true;
              for (int k = 1; k <= Math.abs(getCurrentRow(b1) - j) - 1 ; k++){
                if (buttons[getCurrentCol(b1)][j - k].getBackground() == Color.BLACK){
                  buttons[getCurrentCol(b1)][j - k].setBackground(Color.WHITE);
                  lookRow(b1);
                  lookLeftUpDiagonal(b1); 
                  lookLeftDownDiagonal(b1);
                  lookRightUpDiagonal(b1);
                  lookRightDownDiagonal(b1);
                }
              }
            }
            else /*otherwise, the move is not allowed and nothing changes*/
              legal = false; 
          }
        }   
      }
    }
    return legal;
  }
  
  /** look through the whole right down side diagonal of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the right down side diagonal
    */ 
  public static boolean lookRightDownDiagonal(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    if (count % 2 == 0){ /*when it's black turn*/
      for (int i = 1; getCurrentCol(b1) + i < buttons.length && 
           getCurrentRow(b1) + i < buttons[getCurrentCol(b1)].length; i++){ /*seach all the buttons of the right down side diagonal*/       
        if (buttons[getCurrentCol(b1) + i][getCurrentRow(b1) + i].getBackground() == Color.BLACK){ /*find all black buttons of the right down side diagonal*/
          for (int k = 1; k <= i; k++){ /*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() == Color.WHITE)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same right down side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() == Color.WHITE){
                buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].setBackground(Color.BLACK);
                lookRow(b1);
                lookCol(b1);
                lookLeftUpDiagonal(b1); 
                lookLeftDownDiagonal(b1);
                lookRightUpDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }  
    else  if (count % 2 == 1){  /*when it's white turn*/
      for (int i = 1; getCurrentCol(b1) + i < buttons.length && 
           getCurrentRow(b1) + i < buttons[getCurrentCol(b1)].length; i++){ /*seach all the buttons of the right down side diagonal*/     
        if (buttons[getCurrentCol(b1) + i][getCurrentRow(b1) + i].getBackground() == Color.WHITE){ /*find all white buttons of the right down side diagonal*/
          for (int k = 1; k <= i; k++){/*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() == Color.BLACK)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same right down side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].getBackground() == Color.BLACK){
                buttons[getCurrentCol(b1) + k][getCurrentRow(b1) + k].setBackground(Color.WHITE);
                lookRow(b1);
                lookCol(b1);
                lookLeftUpDiagonal(b1); 
                lookLeftDownDiagonal(b1);
                lookRightUpDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }    
    return legal;
  }
  
  /** look through the whole left up side diagonal of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the left up side diagonal
    */ 
  public static boolean lookLeftUpDiagonal(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    if (count % 2 == 0){ /*when it's black turn*/
      for (int i = 1; getCurrentCol(b1) - i >= 0 && getCurrentRow(b1) - i >= 0; i++){ /*seach all the buttons of the left up side diagonal*/       
        if (buttons[getCurrentCol(b1) - i][getCurrentRow(b1) - i].getBackground() == Color.BLACK){ /*find all black buttons of the left up side diagonal*/
          for (int k = 1; k <= i; k++){ /*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() == Color.WHITE)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same left up side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() == Color.WHITE){
                buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].setBackground(Color.BLACK);
                lookRow(b1);
                lookCol(b1);
                lookLeftDownDiagonal(b1);
                lookRightUpDiagonal(b1);
                lookRightDownDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }  
    else  if (count % 2 == 1){  /*when it's white turn*/
      for (int i = 1; getCurrentCol(b1) - i >= 0 && getCurrentRow(b1) - i >= 0; i++){ /*seach all the buttons of the left up side diagonal*/ 
        if (buttons[getCurrentCol(b1) - i][getCurrentRow(b1) - i].getBackground() == Color.WHITE){  /*find all white buttons of the left up side diagonal*/
          for (int k = 1; k <= i; k++){ /*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() == Color.BLACK)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same left up side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].getBackground() == Color.BLACK){
                buttons[getCurrentCol(b1) - k][getCurrentRow(b1) - k].setBackground(Color.WHITE);
                lookRow(b1);
                lookCol(b1);
                lookLeftDownDiagonal(b1);
                lookRightUpDiagonal(b1);
                lookRightDownDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }    
    return legal;
  }
  
  /** look through the whole right up side diagonal of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the right up side diagonal
    */ 
  public static boolean lookRightUpDiagonal(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    if (count % 2 == 0){ /*when it's black turn*/
      for (int i = 1; getCurrentCol(b1) + i < buttons.length && getCurrentRow(b1) - i >= 0; i++){ /*seach all the buttons of the right up side diagonal*/
        if (buttons[getCurrentCol(b1) + i][getCurrentRow(b1) - i].getBackground() == Color.BLACK){ /*find all black buttons of the right up side diagonal*/
          for (int k = 1; k <= i; k++){/*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() == Color.WHITE)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same right up side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() == Color.WHITE){
                buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].setBackground(Color.BLACK);
                lookRow(b1);
                lookCol(b1);
                lookLeftDownDiagonal(b1);
                lookLeftUpDiagonal(b1);
                lookRightDownDiagonal(b1);
              }
            }
          }
          else  /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        }
      }
    }
    else if (count % 2 == 1){  /*when it's white turn*/
      for (int i = 1; getCurrentCol(b1) + i < buttons.length && getCurrentRow(b1) - i >= 0; i++){/*seach all the buttons of the right up side diagonal*/
        if (buttons[getCurrentCol(b1) + i][getCurrentRow(b1) - i].getBackground() == Color.WHITE){ /*find all white buttons of the right up side diagonal*/
          for (int k = 1; k <= i; k++){/*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() == Color.BLACK)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same right up side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].getBackground() == Color.BLACK){
                buttons[getCurrentCol(b1) + k][getCurrentRow(b1) - k].setBackground(Color.WHITE);
                lookRow(b1);
                lookCol(b1);
                lookLeftDownDiagonal(b1);
                lookLeftUpDiagonal(b1);
                lookRightDownDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        }
      }
    }     
    return legal;     
  }
  
  /** look through the whole left down side diagonal of the pressed button,
    * find the legal move and flip the button
    * @param legal, boolean variable used to store the return value
    * @param legalNumber, int variable used to store the total numbers of buttons that have been pressed
    * @param least, int variable used to store the total numbers of buttons that have the opposite color
    * @param illegalNumber, int variable used to store the total numbers of buttons that are not ppressed
    * @return the value that represents that if it is legal to pressed the button in the left down side diagonal
    */ 
  public static boolean lookLeftDownDiagonal(JButton b1){
    boolean legal = false;
    int legalNumber = 0;
    int least = 0;
    int illegalNumber = 0;
    if (count % 2 == 0){ /*when it's black turn*/
      for (int i = 1; getCurrentCol(b1) - i >= 0 && 
           getCurrentRow(b1) + i < buttons[getCurrentCol(b1)].length; i++){  /*seach all the buttons of the left down side diagonal*/      
        if (buttons[getCurrentCol(b1) - i][getCurrentRow(b1) + i].getBackground() == Color.BLACK){ /*find all black buttons of the left down side diagonal*/
          for (int k = 1; k <= i; k++){ /*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() == Color.WHITE)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same left down side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() == Color.WHITE){
                buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].setBackground(Color.BLACK);
                lookRow(b1);
                lookCol(b1);
                lookLeftUpDiagonal(b1);
                lookRightDownDiagonal(b1);
                lookRightUpDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }  
    else  if (count % 2 == 1){  /*when it's white turn*/
      for (int i = 1; getCurrentCol(b1) - i >= 0 &&
           getCurrentRow(b1) + i < buttons[getCurrentCol(b1)].length; i++){ /*seach all the buttons of the left down side diagonal*/    
        if (buttons[getCurrentCol(b1) - i][getCurrentRow(b1) + i].getBackground() == Color.WHITE){ /*find all white buttons of the left down side diagonal*/
          for (int k = 1; k <= i; k++){ /*decide if those buttons are legal to pressed*/
            if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() != Color.lightGray){
              legalNumber = legalNumber + 1;
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() == Color.BLACK)
                least = least + 1;
            }
            else
              illegalNumber = illegalNumber + 1;             
          }
          /* when the move is legal, then check all other directions and see if there is any buttons that could be flip, and then 
           * flip all the buttons that are legal to flip, including buttons in the same left down side diagonal*/
          if(legalNumber >= i && illegalNumber == 0 && least >= 1){
            legal = true;
            for (int k = 1; k <= i; k++){
              if (buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].getBackground() == Color.BLACK){
                buttons[getCurrentCol(b1) - k][getCurrentRow(b1) + k].setBackground(Color.WHITE);
                lookRow(b1);
                lookCol(b1);
                lookLeftUpDiagonal(b1);
                lookRightDownDiagonal(b1);
                lookRightUpDiagonal(b1);
              }
            }
          }
          else /*otherwise, the move is not allowed and nothing changes*/
            legal = false;
        } 
      }
    }    
    return legal;
  }
  
  /** count the total number of white buttons
    * @return the value that represents the total numbers of white buttons
    */
  public static int countWhiteButton (){
    for (int i = 0; i < buttons.length; i++){
      for (int j = 0; j < buttons[i].length; j++){
        if (buttons[i][j].getBackground() == Color.WHITE)
          whiteButtonNumber = whiteButtonNumber + 1;
      }
    }
    return whiteButtonNumber;
  }
  
  /** count the total number of black buttons
    * @return the value that represents the total numbers of black buttons
    */
  public static int countBlackButton (){
    for (int i = 0; i < buttons.length; i++){
      for (int j = 0; j < buttons[i].length; j++){
        if (buttons[i][j].getBackground() == Color.BLACK)
          blackButtonNumber = blackButtonNumber + 1;
      }
    }
    return blackButtonNumber;
  }
  
  /** decide and show who is the winner
    *show a frame that tell the player who is the winner
    */ 
  public static void gameEnd(){
    countWhiteButton ();
    countBlackButton ();
    JFrame frame = new JFrame();
    if (whiteButtonNumber > blackButtonNumber){
      JOptionPane.showMessageDialog(frame,"White is winner.","Game Over", JOptionPane.ERROR_MESSAGE);
    }
    else if (whiteButtonNumber < blackButtonNumber){
      JOptionPane.showMessageDialog(frame,"Black is winner.","Game Over",JOptionPane.ERROR_MESSAGE);
    }
    else if (whiteButtonNumber == blackButtonNumber){
      JOptionPane.showMessageDialog(frame,  "Tie game!","Game Over", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /** end this game when there is no more legal move to make
    * and announce the winner
    */ 
  public static void finish(JButton b1){
    int occupy = 0;
    for (int i = 0; i < buttons.length; i++){
      for (int j = 0; j < buttons[i].length; j++){
        b1 = buttons[i][j];
        if (buttons[i][j].getBackground() != Color.lightGray){
          occupy = occupy + 1;
        }         
      }
    }
    if (occupy == buttons.length*buttons[height].length)
      gameEnd(); 
  } 
}





