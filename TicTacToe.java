import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
public class TicTacToe  implements ActionListener{
    Random random=new Random();
    JFrame frame=new JFrame();
    JPanel title_panel=new JPanel();
    JPanel button_panel=new JPanel();
    JLabel textfield=new JLabel();
    JButton[] buttons=new JButton[9];
    boolean player1_turn;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("TIC-TAC-TOE");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));
        
        for(int i=0;i<9;i++){
            buttons[i]=new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        
        firstTurn();
    }

    private void firstTurn() {
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            player1_turn=true;
            textfield.setText("X Turn");
        }else{
            player1_turn=false;
            textfield.setText("O Turn");
        }
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O Turn");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X Turn");
                        check();
                    }
                }
                        
            }
        }
    }

                        
    public void check() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] pattern : winPatterns) {
            if (buttons[pattern[0]].getText().equals("X") &&
                buttons[pattern[1]].getText().equals("X") &&
                buttons[pattern[2]].getText().equals("X")) {
                xWins(pattern[0], pattern[1], pattern[2]);
                return;
            }
            if (buttons[pattern[0]].getText().equals("O") &&
                buttons[pattern[1]].getText().equals("O") &&
                buttons[pattern[2]].getText().equals("O")) {
                oWins(pattern[0], pattern[1], pattern[2]);
                return;
            }
        }

        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            textfield.setText("Draw Match");
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
        }  
		
    }
    private void xWins(int i, int j, int k) {
        buttons[i].setBackground(Color.GREEN);
        buttons[j].setBackground(Color.GREEN);
        buttons[k].setBackground(Color.GREEN);

        for(int t=0;t<9;t++){
            buttons[t].setEnabled(false);
        }
        textfield.setText("X Wins");
    }

    private void oWins(int i, int j, int k) {
        
        buttons[i].setBackground(Color.GREEN);
        buttons[j].setBackground(Color.GREEN);
        buttons[k].setBackground(Color.GREEN);

        for(int t=0;t<9;t++){
            buttons[t].setEnabled(false);
            
        }
        textfield.setText("O Wins");
    
    }
}
