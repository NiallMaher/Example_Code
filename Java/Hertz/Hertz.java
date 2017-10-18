import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Hertz extends JFrame implements ActionListener {

	private String username;
	private String password;
	JPasswordField passtf;
	JTextField usertf;

	ImageIcon logo = new ImageIcon("picture.png");
	ImageIcon car1 = new ImageIcon("car1.jpg");
	ImageIcon car2 = new ImageIcon("car2.jpg");

	JLabel display = new JLabel (logo);
	JLabel Car1 = new JLabel(car1);
	JLabel Car2 = new JLabel(car2);
	JPanel pnl = new JPanel();

	JButton loginbt;
    JButton shutdownbt;

    JLabel jcomp4 = new JLabel ("Welcome to Hertz");
    JLabel userlb;
    JLabel passlb;

    Font customFont = new Font("Serif", Font.PLAIN, 32);



public Hertz()
{


	super("Hertz Login");
			setSize(650, 450);
			setLayout (null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pnl.add(Car1);
			pnl.add(Car2);
			loginbt = new JButton ("Login");
       		shutdownbt = new JButton ("Shutdown");
       		usertf = new JTextField (5);
       		jcomp4.setFont(customFont);
       		userlb = new JLabel ("Username:");
       		passlb = new JLabel ("Password:");
       		passtf = new JPasswordField (5);
       		username="niall";
			password="1234";

  		   jcomp4.setEnabled (false);


       		 add (loginbt);
      		 add (shutdownbt);
        	 add (usertf);
       		 add (jcomp4);
       		 add (userlb);
       		 add (passlb);
        	 add (passtf);
      	     add(pnl);
   		   	 add (Car1);
   			 add (Car2);
   			 add (display);

   			 loginbt.setBounds (265, 170, 85, 30);
       		 shutdownbt.setBounds (30, 280, 100, 25);
      		 usertf.setBounds (175, 95, 175, 25);
      		 jcomp4.setBounds (105, 15, 445, 55);
      		 userlb.setBounds (30, 95, 70, 25);
      		 passlb.setBounds (30, 140, 65, 25);
       		 passtf.setBounds (175, 135, 175, 25);
       		 Car1.setBounds (5, 315, 155, 110);
       		 Car2.setBounds (475, 315, 155, 110);
       		 display.setBounds(270, 10, 455, 100);

	loginbt.addActionListener(this);
	shutdownbt.addActionListener(this);


	getContentPane().setBackground(Color.yellow);
		setVisible(true);


}

public void actionPerformed(ActionEvent event)
{

Object target = event.getSource();

	{

		if(target == loginbt)
		{

		String usern =usertf.getText();

		char inputpass[]=((JPasswordField) passtf).getPassword();
		String pw = new String(password);

		if((usern.equalsIgnoreCase(username))&& (pw.equalsIgnoreCase(password)))
		{
				new CusDetails();		//open GUI
		}

		else JOptionPane.showMessageDialog(this, "Login Error ", "Warring Message", JOptionPane.INFORMATION_MESSAGE);
		}

	if(target == shutdownbt)
			{
			new exit();
			}
	}
}
	public static void main(String[] args)
	{
		Hertz her = new Hertz();
	}
}