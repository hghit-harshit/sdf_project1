import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arbritraryarithmetic.*;

public class MainGui {
    enum State {INT,FLOAT};

    private JFrame m_mainFrame;

    private JButton m_mult;
    private JButton m_div;
    private JButton m_sub;
    private JButton m_add;
    private JButton m_switch;

    private JTextField m_first;
    private JTextField m_second;
    private JTextField m_answer;
    
    private State m_state;

    public MainGui()
    {
        prepareGui();
    }

    private void prepareGui()
    {
        m_mainFrame = new JFrame("Test window");
        m_mainFrame.setSize(600,600);
        m_mainFrame.setLayout(null);
        m_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        m_state = State.INT;

        // setting up all the buttons
        m_mult   = new JButton("Multiply");
        m_div    = new JButton("Divide");
        m_add    = new JButton("Add");
        m_sub    = new JButton("Subtract");
        m_switch = new JButton("Int");
        
        //Adding the text fields
        m_first  = new JTextField();
        m_second = new JTextField();
        m_answer = new JTextField("Answer");

        m_first.setEditable(true);
        m_first.setBounds(0,0,200,50);
        m_second.setEditable(true);
        m_second.setBounds(0,60,200,50);
        m_answer.setEditable(false); // we dont want the answer field to be modifiable
        m_answer.setBounds(0,170,200,50);;
 
        // setting up the positions of elements in the frame
        m_mult.setBounds(0,120,100,40);
        m_div.setBounds(110,120,100,40);
        m_add.setBounds(220,120,100,40);
        m_sub.setBounds(330,120,100,40);
        m_switch.setBounds(440,120,100,40);

        Listener l_listener = new Listener();

        // Adding action listeners to buttons
        m_mult.addActionListener(l_listener);
        m_div.addActionListener(l_listener);
        m_add.addActionListener(l_listener);
        m_sub.addActionListener(l_listener);
        m_switch.addActionListener(l_listener);

        // Adding elements to frame
        m_mainFrame.add(m_mult);
        m_mainFrame.add(m_div);
        m_mainFrame.add(m_add);
        m_mainFrame.add(m_sub);
        m_mainFrame.add(m_switch);

        m_mainFrame.add(m_first);
        m_mainFrame.add(m_second);
        m_mainFrame.add(m_answer);

        // ill continue this later
        m_mainFrame.setVisible(true);
    }

    public  class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent l_event)
        {
            // get the source that is the button that is pressed
            Object l_source = l_event.getSource();

            if(l_source == m_switch)
            {
                if(m_state == State.INT)
                {
                    m_state = State.FLOAT;
                    m_switch.setText("Float");
                }
                else
                {
                    m_state = State.INT;
                    m_switch.setText("Int");
                } 
                return;
            }
            if(m_state == State.INT)
            {
                AInteger a = AInteger.Parse(m_first.getText());
                AInteger b = AInteger.Parse(m_second.getText());
                AInteger c = AInteger.Parse("0");

                if(l_source == m_mult){     c = a.Mult(b); }
                else if(l_source == m_div){ c = a.Divide(b); }
                else if(l_source == m_add){ c = a.Add(b); }
                else if(l_source == m_sub){ c = a.Subtract(b); }

                m_answer.setText(c.GetValue());
            }
            else if (m_state == State.FLOAT)
            {
                AFloat a = AFloat.Parse(m_first.getText());
                AFloat b = AFloat.Parse(m_second.getText());
                AFloat c = AFloat.Parse("0");

                if(l_source == m_mult){     c = a.Mult(b); }
                else if(l_source == m_div){ c = a.Divide(b); }
                else if(l_source == m_add){ c = a.Add(b); }
                else if(l_source == m_sub){ c = a.Subtract(b); }

                m_answer.setText(c.GetValue());
            }
        }
    }
    static public void main(String[] args)
    {
        MainGui test = new MainGui();
    }
}
