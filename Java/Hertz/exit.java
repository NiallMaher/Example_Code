import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class exit extends JFrame implements ActionListener {
    JButton yesbt;
    JButton nobt;
    JLabel jlable1;
    JMenuBar jmenu;
    Font customFont = new Font("Serif", Font.PLAIN, 32);

    public exit()
     {
     	super("exit system");
     	setSize(667, 367);
        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        JMenuItem printItem = new JMenuItem ("Print");
        fileMenu.add (printItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);
        

        //construct components
        yesbt = new JButton ("Yes");
        nobt = new JButton ("No");
        jlable1 = new JLabel ("Are You Sure You Want To Exit System ??");
        jmenu = new JMenuBar();
        jmenu.add (fileMenu);
        jmenu.add (helpMenu);
		jlable1.setFont(customFont);
		
        //adjust size and set layout
        setPreferredSize (new Dimension (667, 367));
        setLayout (null);

        //add components
        add (yesbt);
        add (nobt);
        add (jlable1);
        add (jmenu);

        //set component bounds (only needed by Absolute Positioning)
        yesbt.setBounds (115, 165, 105, 55);
        nobt.setBounds (405, 165, 105, 55);
        jlable1.setBounds (70, 65, 550, 65);
        jmenu.setBounds (0, 0, 670, 20);
        
    yesbt.addActionListener(this);
	nobt.addActionListener(this);
	getContentPane().setBackground(Color.blue);
	setVisible(true);
	
    }
    
    public void actionPerformed(ActionEvent event)
{

Object target = event.getSource();

	if (target == yesbt)
	{
		System.exit(0);
	}
	
}


    public static void main (String[] args)
    {
	exit ex = new exit();
    }
}