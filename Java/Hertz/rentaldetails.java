import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class rentaldetails extends  JFrame {
    JLabel rentaldetails;
     JRadioButton businessrb;
     JRadioButton socialrb;
     JRadioButton otherrb;
    JSlider numberofpeplepercar;
    JLabel pickupdatejl;
     JLabel pickuploacationjl;
     JLabel dropoffdatejl;
     JLabel dropoflocationjl;
     JTextField pudjtf;
     JTextField dodjtf;
     JTextField puljtf;
     JTextField doljtf;
     JLabel noofpeoppercarjl;
     JLabel rentaltypjl;
     JLabel rentalnumjl;
     JTextField rentalnum;
     JLabel cartypjl;
     JLabel costperdayjl;
     JComboBox costperdaycb;
     JLabel extrasjl;
     JList cartypejl;
    JCheckBox dvdcb;
     JCheckBox bcsck;
     JCheckBox csjcb;
     JCheckBox snjcb;

    public rentaldetails() 
    {
    	
    	super("Rental Details");
    	setSize(1000, 540);
        //construct preComponents
        String[] costperdaycbItems = {"Family Car €40 P/D", "Green Car €30 P/D", "Fun Car €50 P/D", "Presatige Car €60 P/D", "Vans €60 P/D"};
        String[] cartypejlItems = {"Family", "Green", "Fun", "Prestige", "Vans"};

        //construct components
        rentaldetails = new JLabel ("Rental Details");
        businessrb = new JRadioButton ("Business");
        socialrb = new JRadioButton ("Social");
        otherrb = new JRadioButton ("Other");
        numberofpeplepercar = new JSlider (0, 5);
        pickupdatejl = new JLabel ("Pick Up Date");
        pickuploacationjl = new JLabel ("Pick Up Location");
        dropoffdatejl = new JLabel ("Drop Off Date");
        dropoflocationjl = new JLabel ("Drop Off Location");
        pudjtf = new JTextField (5);
        dodjtf = new JTextField (5);
        puljtf = new JTextField (5);
        doljtf = new JTextField (5);
        noofpeoppercarjl = new JLabel ("Number Of People Per Car");
        rentaltypjl = new JLabel ("Rental Type");
        rentalnumjl = new JLabel ("Rental Number");
        rentalnum = new JTextField (5);
        cartypjl = new JLabel ("Car Type");
        costperdayjl = new JLabel ("Cost Per Day");
        costperdaycb = new JComboBox (costperdaycbItems);
        extrasjl = new JLabel ("Extras");
        cartypejl = new JList (cartypejlItems);
        dvdcb = new JCheckBox ("DVD Player €10 P/W");
        bcsck = new JCheckBox ("Booster Car Seat €10 P/W");
        csjcb = new JCheckBox ("Child Seat €10 P/W");
        snjcb = new JCheckBox ("Sat Nav €15 P/W");

        //set components properties
        numberofpeplepercar.setOrientation (JSlider.VERTICAL);
        numberofpeplepercar.setMinorTickSpacing (0);
        numberofpeplepercar.setMajorTickSpacing (1);
        numberofpeplepercar.setPaintTicks (true);
        numberofpeplepercar.setPaintLabels (true);

        //adjust size and set layout
        setPreferredSize (new Dimension (1000, 540));
        setLayout (null);

        //add components
        add (rentaldetails);
        add (businessrb);
        add (socialrb);
        add (otherrb);
        add (numberofpeplepercar);
        add (pickupdatejl);
        add (pickuploacationjl);
        add (dropoffdatejl);
        add (dropoflocationjl);
        add (pudjtf);
        add (dodjtf);
        add (puljtf);
        add (doljtf);
        add (noofpeoppercarjl);
        add (rentaltypjl);
        add (rentalnumjl);
        add (rentalnum);
        add (cartypjl);
        add (costperdayjl);
        add (costperdaycb);
        add (extrasjl);
        add (cartypejl);
        add (dvdcb);
        add (bcsck);
        add (csjcb);
        add (snjcb);

        //set component bounds (only needed by Absolute Positioning)
       rentaldetails.setBounds (350, 5, 225, 40);
        businessrb.setBounds (140, 105, 100, 25);
        socialrb.setBounds (140, 130, 100, 25);
        otherrb.setBounds (140, 155, 100, 25);
        numberofpeplepercar.setBounds (830, 110, 145, 120);
        pickupdatejl.setBounds (15, 255, 100, 25);
        pickuploacationjl.setBounds (250, 250, 100, 25);
        dropoffdatejl.setBounds (15, 300, 100, 25);
        dropoflocationjl.setBounds (250, 300, 100, 25);
        pudjtf.setBounds (110, 255, 100, 25);
        dodjtf.setBounds (110, 300, 100, 25);
        puljtf.setBounds (365, 250, 100, 25);
        doljtf.setBounds (370, 300, 100, 25);
        noofpeoppercarjl.setBounds (820, 70, 155, 30);
        rentaltypjl.setBounds (150, 70, 90, 30);
        rentalnumjl.setBounds (15, 70, 165, 30);
        rentalnum.setBounds (10, 110, 115, 25);
        cartypjl.setBounds (290, 75, 50, 25);
        costperdayjl.setBounds (455, 75, 90, 25);
        costperdaycb.setBounds (425, 110, 145, 35);
        extrasjl.setBounds (630, 75, 100, 25);
        cartypejl.setBounds (275, 105, 105, 95);
        dvdcb.setBounds (620, 110, 190, 30);
        bcsck.setBounds (620, 140, 190, 30);
        csjcb.setBounds (620, 170, 190, 30);
        snjcb.setBounds (620, 200, 190, 30);
        
        getContentPane().setBackground(Color.yellow);
		setVisible(true);
    }


    public static void main (String[] args)
     {
	rentaldetails rent = new rentaldetails();
    }
}
