import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Orbito implements ActionListener {
   private JFrame frame;
   private JPanel panel;
   private int[][] tracker = new int[4][4];
   private JButton[][] buttons = new JButton[4][4];
   private int phase;
   private int switchRow = -1;
   private int switchCol = -1;
   private int row = -1;
   private int col = -1;


   public Orbito(){
       frame = new JFrame("Orbito");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      
       panel = new JPanel();
       panel.setLayout(new GridLayout(4, 4));
       panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      
      


       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               buttons[r][c] = new JButton();
               buttons[r][c].setFont(new Font("Arial", Font.PLAIN, 40));
               buttons[r][c].addActionListener(this);
               panel.add(buttons[r][c]);
           }
       }
      
      


       frame.add(panel, BorderLayout.CENTER);
       frame.setSize(400, 400);
       frame.setVisible(true);


       phase = 1;
   }
   public void actionPerformed(ActionEvent e) {
       JButton button = (JButton) e.getSource();
       for (int r = 0; r < buttons.length; r++){
           for (int c = 0; c < buttons[r].length; c++){
               if (button == buttons[r][c]){
                   row = r;
                   col = c;
               }
           }
       }
       switch (phase){
           case 1:
               //first move
               tracker[row][col] = 1;
               updateBoard();
               javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       rotateMatrix();
                       updateBoard();
                       checkForWinner();
                       phase = 2;
                       //
                       for (int r = 0; r < 4; r++){
                           for (int c = 0; c < 4; c++){
                               if (tracker[r][c] != 1){
                                   buttons[r][c].setEnabled(false);
                               }
                           }
                       }
                   }
                });
               t.setRepeats(false);
               t.start();
              
               break;
           case 2:
               //p2 move phase part 1
               switchRow = row;
               switchCol = col;
               //enables (empties && adjacent) && clicked
               for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(false);
                       if (r == row && c == col){
                           buttons[r][c].setEnabled(true);
                       }
                       if (Math.abs(r - row) == 1 && c == col && tracker[r][c] == 0){
                           buttons[r][c].setEnabled(true);
                       }
                       if (Math.abs(c - col) == 1 && r == row && tracker[r][c] == 0){
                           buttons[r][c].setEnabled(true);
                       }
                   }
               }
               phase = 3;
               break;
           case 3:
               //p2 move phase part 2
               int temp = tracker[row][col];
               tracker[row][col] = tracker[switchRow][switchCol];
               tracker[switchRow][switchCol] = temp;
               updateBoard();
               phase = 4;
               //enables empties
               /*for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                       if (tracker[r][c] != 0){
                           buttons[r][c].removeActionListener(this);
                       }
                   }
               }*/
               for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                   }
               }
               break;
           case 4:
               //p2 play phase
               tracker[row][col] = 2;
               updateBoard();
               javax.swing.Timer t2 = new javax.swing.Timer(1000, new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       rotateMatrix();
                       updateBoard();
                       checkForWinner();
                       phase = 5;
                       //
                       for (int r = 0; r < 4; r++){
                           for (int c = 0; c < 4; c++){
                               if (tracker[r][c] != 2){
                                   buttons[r][c].setEnabled(false);
                               }
                           }
                       }
                   }
                });
               t2.setRepeats(false);
               t2.start();
              
               //enables p2
               /*for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                       if (tracker[r][c] != 2){
                           buttons[r][c].setEnabled(false);
                       }
                   }
               }*/
              
               break;
           case 5:
               //p1 move phase part 1
               switchRow = row;
               switchCol = col;
               for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(false);
                       if (r == row && c == col){
                           buttons[r][c].setEnabled(true);
                       }
                       if (Math.abs(r - row) == 1 && c == col && tracker[r][c] == 0){
                           buttons[r][c].setEnabled(true);
                       }
                       if (Math.abs(c - col) == 1 && r == row && tracker[r][c] == 0){
                           buttons[r][c].setEnabled(true);
                       }
                   }
               }
               phase = 6;
               break;
           case 6:
               //p1 move phase part 2
               int temp2 = tracker[row][col];
               tracker[row][col] = tracker[switchRow][switchCol];
               tracker[switchRow][switchCol] = temp2;
               updateBoard();
               checkForWinner();
               //enables empties
               /*for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                       if (tracker[r][c] != 0){
                           buttons[r][c].setEnabled(false);
                       }
                   }
               }*/
               phase = 7;
               for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                   }
               }
               break;
           case 7:
               //p1 play phase
               tracker[row][col] = 1;
               updateBoard();
               javax.swing.Timer t3 = new javax.swing.Timer(1000, new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       rotateMatrix();
                       updateBoard();
                       checkForWinner();
                       phase = 2;


                       //


                       for (int r = 0; r < 4; r++){
                           for (int c = 0; c < 4; c++){
                               if (tracker[r][c] != 1){
                                   buttons[r][c].setEnabled(false);
                               }
                           }
                       }
                      
                   }
                });
               t3.setRepeats(false);
               t3.start();


               //enables p1
               /*for (int r = 0; r < 4; r++){
                   for (int c = 0; c < 4; c++){
                       buttons[r][c].setEnabled(true);
                       if (tracker[r][c] != 1){
                           buttons[r][c].setEnabled(false);
                       }
                   }
               }*/
              


               break;
       }
       


   }
   private void updateBoard(){
       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               buttons[r][c].setText(" ");
               if (tracker[r][c] == 1){
                   buttons[r][c].setText("X");
                   buttons[r][c].setForeground(Color.red);
               } else if (tracker[r][c] == 2) {
                   buttons[r][c].setText("O");
                   buttons[r][c].setForeground(Color.blue);
               }
           }
       }
   }
   private void rotateMatrix(){
       int m = 4;
       int n = 4;
       int[][] mat = tracker;
       int row = 0, col = 0;
       int prev, curr;
       /*
       row - Starting row index
       m - ending row index
       col - starting column index
       n - ending column index
       i - iterator
       */
       while (row < m && col < n) {
           if (row + 1 == m || col + 1 == n)
               break;
           // Store the first element of next
           // row, this element will replace
           // first element of current row
           prev = mat[row + 1][col];
           // Move elements of first row
           // from the remaining rows
           for (int i = col; i < n; i++) {
               curr = mat[row][i];
               mat[row][i] = prev;
               prev = curr;
           }
           row++;
           // Move elements of last column
           // from the remaining columns
           for (int i = row; i < m; i++) {
               curr = mat[i][n - 1];
               mat[i][n - 1] = prev;
               prev = curr;
           }
           n--;
           // Move elements of last row
           // from the remaining rows
           if (row < m) {
               for (int i = n - 1; i >= col; i--) {
                   curr = mat[m - 1][i];
                   mat[m - 1][i] = prev;
                   prev = curr;
               }
           }
           m--;
           // Move elements of first column
           // from the remaining rows
           if (col < n) {
               for (int i = m - 1; i >= row; i--) {
                   curr = mat[i][col];
                   mat[i][col] = prev;
                   prev = curr;
               }
           }
           col++;
       }
       tracker = mat;
   }
   private void checkForWinner(){
       for (int i = 0; i < 4; i++){
           if (tracker[i][0] == tracker[i][1] && tracker[i][1] == tracker[i][2] && tracker[i][2] == tracker[i][3] && tracker[i][0] != 0){
               JOptionPane.showMessageDialog(frame, "Player " + tracker[i][0] + " wins!");
               reset();
               return;
           }
       }
       for (int i = 0; i < 4; i++){
           if (tracker[0][i] == tracker[1][i] && tracker[1][i] == tracker[2][i] && tracker[2][i] == tracker[3][i] && tracker[0][i] != 0){
               JOptionPane.showMessageDialog(frame, "Player " + tracker[i][0] + " wins!");
               reset();
               return;
           }
       }
       if (tracker[0][0] == tracker[1][1] && tracker[1][1] == tracker[2][2] && tracker[2][2] == tracker[3][3] && tracker[0][0] != 0){
           JOptionPane.showMessageDialog(frame, "Player " + tracker[0][0] + " wins!");
           reset();
           return;
       }
       if (tracker[3][0] == tracker[2][1] && tracker[2][1] == tracker[1][2] && tracker[1][2] == tracker[0][3] && tracker[3][0] != 0){
           JOptionPane.showMessageDialog(frame, "Player " + tracker[3][0] + " wins!");
           reset();
           return;
       }
       int filled = 0;
       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               if (tracker[r][c] != 0){
                   filled++;
               }
           }
       }
       if (filled == 16){
           for (int i = 0; i < 5; i++){
               rotateMatrix();
               updateBoard();
               checkForWinner();
           }
           JOptionPane.showMessageDialog(frame, "Tie game!");
           reset();
           return;
       }
   }
   public void reset(){
       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               buttons[r][c].setText("");
               buttons[r][c].setEnabled(true);
               tracker[r][c] = 0;
               phase = 1;
           }
       }
   }
   //testing purposes
   public String trackerToString(){
       String result = "";
       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               result += tracker[r][c] + ", ";
           }
           result += "\n";
       }
       return result;
   }
   public String buttonsToString(){
       String result = "";
       for (int r = 0; r < 4; r++){
           for (int c = 0; c < 4; c++){
               result += buttons[r][c].getText() + ", ";
           }
           result += "\n";
       }
       return result;
   }
}


/*
javax.swing.Timer t4 = new javax.swing.Timer(1000, new ActionListener() {
                   public void actionPerformed(ActionEvent e){
                      
                   }
               });
               javax.swing.Timer t3 = new javax.swing.Timer(1000, new ActionListener() {
                   public void actionPerformed(ActionEvent e){
                      
                       t4.start();
                   }
               });
               t3.setRepeats(false);
               t4.setRepeats(false);
               t3.start();
*/


