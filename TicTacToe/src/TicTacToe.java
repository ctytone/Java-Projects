//Written by Christopher Tytone

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    //Defining Variables
    int boardWidth = 600;
    int boardHeight = 650; //Adding 50px for the text panel

    //Creating window
    JFrame frame = new JFrame("Tic-Tac-Toe" );

    //Adding panels
    //1. Panel for text (Label)
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    //2. Creating board panel
    JPanel boardPanel = new JPanel();

    //Creating the tiles which are buttons
    JButton[][] board = new JButton[3][3]; //Using array to keep track of text and placement and check

    //Creating the text that each player puts on their turn
    String playerX = "X";
    String playerO = "O";
    //Keep track of which text to use when click on a button
    String currentPlayer = playerX;

    //Checking for win boolean
    boolean gameOver = false;

    //Checking for tie
    int turns = 0;

    //TODO Add Restart Button
    //TODO Add Score

    //Constructor
    TicTacToe()
    {
        //Make frame visible
        frame.setVisible(true);
        //Set the size
        frame.setSize(boardWidth, boardHeight);
        //Open window at center of screen
        frame.setLocationRelativeTo(null);
        //Cant resize
        frame.setResizable(false);
        //Clicking the x at the top also terminates program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        frame.setLayout(new BorderLayout());

        //Setting the background of the label to dark gray
        textLabel.setBackground(Color.darkGray);
        //Setting the font color to white
        textLabel.setForeground(Color.white);
        //Setting the font
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        //Center the text instead of it starting on the left side
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        //Default text
        textLabel.setText("Tic-Tac-Toe");
        //
        textLabel.setOpaque(true);

        //Adding the text label to the text panel
        //
        textPanel.setLayout(new BorderLayout());
        //Adding label to panel
        textPanel.add(textLabel);
        //Adding panel to frame
        frame.add(textPanel, BorderLayout.NORTH); //Setting the panel to the top(north)

        //Creating a 3x3 grid layout for the board
        boardPanel.setLayout(new GridLayout(3, 3));
        //Setting the background color
        boardPanel.setBackground(Color.darkGray);
        //Adding board panel to the frame
        frame.add(boardPanel);

        //Adding buttons
        for(int r = 0; r < 3; r++) //r Stands for rows
        {
            for(int c = 0; c < 3; c++) //c Stands for columns
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                //Adding button to board panel
                boardPanel.add(tile);

                //Properties
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                //Adding the action listener for each tile
                tile.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {

                        if(gameOver) return;

                        //
                        JButton tile = (JButton) e.getSource();

                        if(tile.getText() == "")
                        {
                            tile.setText(currentPlayer);

                            //Adding to turns
                            turns++;

                            //Checking winner
                            checkWinner();

                            if(!gameOver)
                            {
                                //Terniary expression
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;

                                //Updating label
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner()
    {
        //Horizontal
        for(int r = 0; r < 3; r++)
        {
            //If the first horizonal tile is empty, no possibility of a winner at that row
            if(board[r][0].getText() == "") continue;

            //If all three in the row are true
            if(board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText())
            {
                for(int i = 0; i < 3; i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //Vertical
        for(int c = 0; c < 3; c++)
        {
            if(board[0][c].getText() == "") continue;

            if(board[0][c].getText() == board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText())
            {
                for(int i = 0; i < 3; i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //Diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "")
        {
            for(int i = 0; i < 3; i++)
            {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //AntiDiagonal
        if(board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "")
        {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        //Checking for tie
        if(turns == 9)
        {
            for(int r = 0; r < 3; r++)
            {
                for(int c = 0; c < 3; c++)
                {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile)
    {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
