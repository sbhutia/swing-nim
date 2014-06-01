
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.text.DefaultCaret;
/**
 * This class is used to create the main UI for the Nim Game. It also contains the action methods for handling the user inputs for making a new move
 * or starting a new game
 * @author Sonam Bhutia
 * @version 1
 */
public class NimUI implements ActionListener
{
    private JFrame nimFrame;
    private NimGame nimGame;
    private JTextField moveSizeField;
    private JTextField pileSizeField;
    private JTextField initialPileSize;
    private JTextField winnerField;
    private JTextArea messageTextArea;
    private JTextField userMove;
    private JScrollPane scrollPane;
    
    /**
     * The default constructor will create the UI for the game and then show the custom dialog to start a new game
     */
    public NimUI()
    {
        //frame.setSize(200,100);
        createUI();
        newGame();
    }
    
    /**
     * This method creates the UI for the NIM game with blank fields
     */
    private void createUI()
    {
        nimFrame = new JFrame("Nim Game");
        nimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nimFrame.setLayout(new BoxLayout(nimFrame.getContentPane(),BoxLayout.Y_AXIS));
        nimFrame.setSize(500,350);
        nimFrame.setResizable(false);
        
        //creating top panel
        JPanel topPanel = new JPanel();
        
        JPanel initialPileSizePanel = new JPanel();
        initialPileSize = new JTextField(4);
        initialPileSize.setEditable(false);
        initialPileSizePanel.add(new JLabel("Pile Size"));
        initialPileSizePanel.add(initialPileSize);
        topPanel.add(initialPileSizePanel);
        
        
        JPanel moveSizePanel = new JPanel();
        moveSizeField = new JTextField(4);
        moveSizeField.setEditable(false);
        moveSizePanel.add(new JLabel("Move Size"));
        moveSizePanel.add(moveSizeField);
        topPanel.add(moveSizePanel);
      
        JPanel winnerPanel = new JPanel();
        winnerField = new JTextField(10);
        winnerField.setEditable(false);
        winnerPanel.add(new JLabel("Winner"));
        winnerPanel.add(winnerField);
        topPanel.add(winnerPanel);
        
        nimFrame.add(topPanel);
        
        //creating middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.X_AXIS));
        
        JPanel scrollPanePanel = new JPanel();
        scrollPanePanel.setBorder(BorderFactory.createTitledBorder("Game Log"));
        messageTextArea = new JTextArea(8,25);
        messageTextArea.setEditable(false);
        //messageTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
        DefaultCaret caret = (DefaultCaret)messageTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollPane = new JScrollPane(messageTextArea);
        scrollPanePanel.add(scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scrollPanePanel);       
        
        JPanel middleRightPanel = new JPanel();
        middleRightPanel.setLayout(new BoxLayout(middleRightPanel,BoxLayout.Y_AXIS));
        
        JPanel pileSizePanel = new JPanel();
        pileSizeField = new JTextField(4);
        pileSizeField.setEditable(false);
        pileSizePanel.add(new JLabel("Pieces Remaining"));
        pileSizePanel.add(pileSizeField);
        middleRightPanel.add(pileSizePanel);
        
        JPanel userMovePanel = new JPanel();
        userMovePanel.add(new JLabel("Your Move"));
        userMove = new JTextField(4);
        userMove.setEditable(false);
        userMove.setActionCommand("User Move");
        userMove.addActionListener(this);
        userMovePanel.add(userMove);
        
        middleRightPanel.add(userMovePanel);
        
        JPanel submitPanel = new JPanel();
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        submitPanel.add(submit);
        middleRightPanel.add(submitPanel);
        
        middlePanel.add(middleRightPanel);
       
        nimFrame.add(middlePanel);
        
        //creating bottom panel
        JPanel bottomPanel = new JPanel();
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        bottomPanel.add(newGameButton);
        
        nimFrame.add(bottomPanel);
        
        nimFrame.setLocationRelativeTo(null);
        nimFrame.setVisible(true);
    }
    
    /**
     * This method is used to display the dialog for starting a new game to the user when the program is first started or a new game is created
     * Once the dialog is shown the user input is taken from the user
     */
    private void newGame()
    {
        UserInputCustomDialog nimUserInput = new UserInputCustomDialog(nimFrame, this);
        nimUserInput.setVisible(true);
        int maxMove = nimUserInput.getMaximumMove();
        String firstMover = nimUserInput.getFirstMover();   
    }
    
    /**
     * This method is called once the user fills in the fields for starting a new game and submits it. Using the fields entered by the user, a new game is created
     * and the UI is populated with the created values. If the first mover is chosen as the computer, then a computer move is also made
     * @param   maxMove The maximum number of moves as entered by the user
     * @param   firstMover  the first mover chosen by the userd
     */
    public void startGame(int maxMove, String firstMover)
    {        
        try{
            nimGame = new NimGame(maxMove);
            
            initialPileSize.setText(Integer.toString(nimGame.getPileSize()));
            pileSizeField.setText(Integer.toString(nimGame.getPileSize()));
            moveSizeField.setText(Integer.toString(nimGame.getMaxMove()));
            messageTextArea.setText("");
            winnerField.setText("");
            userMove.setText("");
            userMove.setEditable(true);
            
            messageTextArea.append("New game started with "+nimGame.getPileSize()+" pieces.\n");
            
            if(firstMover.equals("Computer")){
                makeComputerMove();
            }
            userMove.requestFocusInWindow();
        }catch(Exception e)
        {
            showError("Sorry an error occured");
            System.exit(0);
        }
    }
    
    /**
     * This method makes a computer move and updates the game log with the number of pieces taken out by the computer and the number of pieces remaining.
     * If no pieces remain, then it declares the computer as the winner of the game
     */
    private void makeComputerMove()
    {
        try
        {
            int computerMove = nimGame.nextComputerMove();
            messageTextArea.append("Computer made a move by taking out "+computerMove+" pieces.\n");
            messageTextArea.append(nimGame.getPileSize()+" pieces remaining.\n");
            pileSizeField.setText(Integer.toString(nimGame.getPileSize()));
            
            if(nimGame.getPileSize()==0)
            {
                messageTextArea.append("The computer player won the game.\n");
                winnerField.setText("Computer");
                userMove.setEditable(false);
            }
        }catch(Exception e)
        {
            showError("Sorry an error occured");
            System.exit(0);
        }
    }
    
    /**
     * This method is called when a user makes a move by entering a move size number in the UI and clicks enter or Submit or starts a new game. 
     * If the user has made a move the user input is validated, and a player move is made the nim game object and UI updated. If the remaining pieces is 0 then
     * the user is declared the winner.
     * If a new game is started, then the new game method is called to take the user input for the new game.
     * @param   evt The action event object passed to the method
     */
    public void actionPerformed(ActionEvent evt) 
    {
        try
        {
            if(evt.getActionCommand().equals("Submit")  || evt.getActionCommand().equals("User Move"))
            {
                if(nimGame == null)
                {
                    showError("No game started yet. Please start a new game.");
                    return;
                }
                if(nimGame.getPileSize() == 0)
                {
                    showError("Sorry this game is over. Please start a new game.");
                    return;
                }
                
                String userMoveVal = userMove.getText();
                if(userMoveVal.matches("^[0-9]+$"))
                {
                    int userMoveIntVal = Integer.parseInt(userMoveVal);
                    if(userMoveIntVal <= nimGame.getPileSize() && userMoveIntVal <= nimGame.getMaxMove() && userMoveIntVal > 0)
                    {
                        nimGame.nextPlayerMove(userMoveIntVal);
                        messageTextArea.append("Player made a move by taking out "+userMoveIntVal+" pieces.\n");
                        messageTextArea.append(nimGame.getPileSize()+" pieces remaining.\n");
                        pileSizeField.setText(Integer.toString(nimGame.getPileSize()));
                        
                        if(nimGame.getPileSize()==0)
                        {
                            messageTextArea.append("Congratulations you have won the game.\n");
                            winnerField.setText("Player");
                            userMove.setEditable(false);
                        }else
                        {
                            makeComputerMove();
                        }
                    }
                    else
                    {
                      showError("The move value should be less than the pile size and maximum move size and more than 0.");  
                    }
                }else
                {
                    showError("Please enter a positive integer value.");
                }
                userMove.setText("");
                userMove.requestFocusInWindow();
            }else if (evt.getActionCommand().equals("New Game"))
            {
                newGame();
            }
        }catch(Exception e)
        {
            showError("Sorry an error occured");
            System.exit(0);
        }
        
    }
    
    /**
     * This method displays an error to the user in a dialog box
     */
    private void showError(String message)
    {
        JOptionPane.showMessageDialog(nimFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
        
    }
    
}
