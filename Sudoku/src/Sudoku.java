//Written by Christopher Tytone

//Libraries for the GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sudoku {

    //Another Class
    //"Extends" is inheritance: the new class "Tile" has all of the same
    //properties as a JButton, but we can add more things to it
    public class Tile extends JButton {
        //Instance Variables: keeps track of position
        int r;
        int c;
        //Constructor
        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    //Create a window
    int boardWidth = 600;
    int boardHeight = 650;

    //Adding arrays for the puzzle and solution
    String[] puzzle = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };

    String[] solution = {
            "387491625",
            "241568379",
            "569327418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "675832941",
            "812945763"
    };

    JFrame frame = new JFrame("Sudoku");

    //Adding panels for the title bar at the top, the game itself, and the buttons at the bottom
    //Label is going to hold text,
    //Panel is going to hold the label,
    //And then we add the panel onto the frame
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();

    //Adding JPanel for the board
    JPanel boardPanel = new JPanel();

    //Adding panel for numbers
    JPanel buttonsPanel = new JPanel();

    //Variable to keep track of the number you selected
    JButton numSelected = null;
    int errors = 0;

    //Adding constructor
    Sudoku() {
        //Add settings for window
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centers frame
        frame.setLocationRelativeTo(null);
        //Adding a layout
        //With a border layout, I can place components in N,S,E,W or center, and can organize them and place them in a specific position
        frame.setLayout(new BorderLayout());

        //Adding properties for text label
        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Sudoku: 0");

        //Adding label to panel
        textPanel.add(textLabel);

        //Adding panel to window
        frame.add(textPanel, BorderLayout.NORTH);

        //Setting properies for the board
        boardPanel.setLayout(new GridLayout(9, 9));
        setupTiles();

        frame.add(boardPanel, BorderLayout.CENTER);

        buttonsPanel.setLayout(new GridLayout(1, 9));
        setupButtons();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        //Dont want to set the frame visible until you've added all of the components
        frame.setVisible(true);
    }

    //Defining a function to setup the tiles on the grid
    public void setupTiles() {
        //Iterate through each row and each column
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char tileChar = puzzle[r].charAt(c);
                //If there is a dash, dont add anything
                if(tileChar != '-') {
                    tile.setFont(new Font("Arial", Font.BOLD, 20));
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(Color.lightGray);
                }
                else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.WHITE);
                }

                //Making a check to add the thick border lines to split the game into the 3x3 grid
                if((r == 2 && c == 2) || (r == 2 && c == 5) || (r == 5 && c == 2) || (r == 5 && c == 5)) {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,5,5,Color.black));
                }
                else if(r == 2 || r == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,5,1,Color.black));
                }
                else if(c == 2 || c == 5) {
                    tile.setBorder(BorderFactory.createMatteBorder(1,1,1,5,Color.black));
                }
                else {
                    tile.setBorder(BorderFactory.createLineBorder(Color.black));
                }


                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if(numSelected != null) {
                            //If a tile has the correct number in it
                            if(tile.getText() != "") {
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String tileSolution = String.valueOf(solution[r].charAt(c));
                            if(tileSolution.equalsIgnoreCase(numSelectedText)) {
                                tile.setText(numSelectedText);
                            }
                            else {
                                errors += 1;
                                textLabel.setText("Sudoku: " + String.valueOf(errors));
                            }
                        }
                    }
                });
            }
        }
    }

    public void setupButtons() {
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            button.setBackground(Color.white);
            buttonsPanel.add(button);

            //Adding actionListener
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (numSelected != null) {
                        numSelected.setBackground(Color.white);
                    }
                    numSelected = button;
                    numSelected.setBackground(Color.lightGray);
                }
            });
        }
    }
}
