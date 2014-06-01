import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is a custom user input dialog which is shown when a new game is started. In this dialog
 * the maximum move size and the first mover is taken from the user
 * @author Sonam Bhutia 
 * @version 1
 */
public class UserInputCustomDialog extends JDialog implements ActionListener
{
    private JPanel maximumMovePanel;
    private JTextField maximumMoveTextField;
    private JComboBox choicesComboBox;
    private JLabel maxMoveErrorLabel;
    private int maximumMove;
    private String firstMover;
    private NimUI nimUI;
    
    /**
     * This method will return the maximum move size which has been input by the user
     * @return  the maximum move size
     */
    public int getMaximumMove()
    {
        return maximumMove;
    }
    
    /**
     * This method will return the first mover whether user or computer chosen by the user
     * @return  the first mover as a string
     */
    public String getFirstMover()
    {
        return firstMover;
    }
    
    /**
     * This is the constructor for the custom user input dialog which is used to create a new dialog
     * @param   frame   The frame on which the dialog will be created
     * @NimUI   nimUI   A reference to the UI class which
     */
    public UserInputCustomDialog(Frame frame, NimUI nimUI)
    {
        super(frame,"User Input",Dialog.ModalityType.DOCUMENT_MODAL);
        this.nimUI = nimUI;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        maximumMovePanel = new JPanel();
        maximumMovePanel.setLayout(new BoxLayout(maximumMovePanel,BoxLayout.Y_AXIS));
        
        JPanel maximumMoveTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maximumMoveTopPanel.add(new JLabel("Enter the maximum number of a move"));
        maximumMoveTextField = new JTextField(3);
        maximumMoveTopPanel.add(maximumMoveTextField);
        maximumMovePanel.add(maximumMoveTopPanel);
        
        maxMoveErrorLabel = new JLabel("");
        maxMoveErrorLabel.setForeground(Color.red);
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        errorPanel.add(maxMoveErrorLabel);
        maximumMovePanel.add(errorPanel);
        
        panel.add(maximumMovePanel);
        
        JPanel firstUserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstUserPanel.add(new JLabel("Who goes first?"));
        String[] choices = {"User","Computer"};
        choicesComboBox = new JComboBox(choices);
        firstUserPanel.add(choicesComboBox);
        panel.add(firstUserPanel);
        
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitPanel.add(submitButton);
        panel.add(submitPanel);
        this.setContentPane(panel);
        this.pack();
        setLocationRelativeTo(frame);
    }
    
    /**
     * This is the action event method which is called when the submit button is pressen on the custom
     * dialog. It validates the user input and once validated, it will start a new game using the inputs
     * @param   evt The action event variable which is sent to the method
     */ 
    
    public void actionPerformed(ActionEvent evt){
        if(maximumMoveTextField.getText().matches("^[0-9]+$"))
        {
            maximumMove = Integer.parseInt(maximumMoveTextField.getText());
            firstMover = (String)choicesComboBox.getSelectedItem();
            if(maximumMove>0)
            {
                this.setVisible(false);
                nimUI.startGame(maximumMove, firstMover);
            }
        }else
        {
            if(maximumMoveTextField.getText().equals(""))
            {
                maxMoveErrorLabel.setText("Please enter the maximum move number");
            }else
            {
                maxMoveErrorLabel.setText("The maximum number must be a positive integer");
            }
            this.pack();
        }
     }
}
