import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.net.*;

public class CusDetails extends JFrame implements ActionListener  {

	ImageIcon car2 = new ImageIcon("car2.jpg");
	ImageIcon car1 = new ImageIcon("car1.jpg");
	ImageIcon logo = new ImageIcon("picture.png");
	
	JLabel Car1 = new JLabel(car1);
	JLabel Car2 = new JLabel(car2);
	JLabel display = new JLabel (logo);
	
  JLabel headLb;
  JLabel rentLb;
  JLabel townLb;
  JLabel addrLb;
  JLabel countyLb;
  JLabel countryLb;
  JLabel nameLb;
  JTextField rentaltf;
  JTextField nametf;
  JTextField towntf;
  JTextField couuntytf;
  JTextField countrytf;
  JLabel emailLb;
  JLabel phoneLb;
  JTextField emailtf;
  JTextField phonetf;
  JButton findbt;
  JButton prevbt;
  JButton nextbt;
  JButton exitbt;
  JButton createnewrnetbt;
  JTextArea addrta;
  JButton cnewcusbt;
  JButton clearbt;
  JButton deletebt;

  	Connection con;	// database connection
    Statement stmt;      // used for executing database stmts
    ResultSet rs;	// holds result of stmt, moves to next row in database
    int count;
    int current;// all these are nedd for the database !!

	String RentalNo;
	String cusName;
	String Adress;
	String town;
	String County;
	String Country;
	String Email;
	int PhoneNo;

	   Font customFont = new Font("Serif", Font.PLAIN, 32);

    public CusDetails()
     {

     	super ("Customer Details");

     		con = null;
        	stmt = null;
        	rs = null;
        	count = 0;
        	current = 0;

     	setSize(650, 600);
        //construct components
        headLb = new JLabel ("Customer Details");
        rentLb = new JLabel ("Rental No");
        townLb = new JLabel ("Town");
        addrLb = new JLabel ("Address");
        countyLb = new JLabel ("County");
        countryLb = new JLabel ("Country");
        nameLb = new JLabel ("Name");
        rentaltf = new JTextField (5);
        nametf = new JTextField (5);
        towntf = new JTextField (5);
        couuntytf = new JTextField (5);
        countrytf = new JTextField (5);
        emailLb = new JLabel ("E mail");
        phoneLb = new JLabel ("Phone No");
        emailtf = new JTextField (5);
        phonetf = new JTextField (5);
        findbt = new JButton ("Find");
        prevbt = new JButton ("Prev");
        nextbt = new JButton ("Next");
        exitbt = new JButton ("Exit");
        createnewrnetbt = new JButton ("Create New Rental");
        addrta = new JTextArea (5, 5);
        cnewcusbt = new JButton ("Create New Customer");
        clearbt = new JButton ("clear");
        headLb.setFont(customFont);
        deletebt = new JButton ("delete");


        dbConn();		// method to connect to database using odbc-jdbc bridge
        initDB();		// method to initialise gui with database info


        //adjust size and set layout
        setPreferredSize (new Dimension (650, 600));
        setLayout (null);

        //add components
          add (headLb);
        add (rentLb);
        add (townLb);
        add (addrLb);
        add (countyLb);
        add (countryLb);
        add (nameLb);
        add (rentaltf);
        add (nametf);
        add (towntf);
        add (couuntytf);
        add (countrytf);
        add (emailLb);
        add (phoneLb);
        add (emailtf);
        add (phonetf);
        add (findbt);
        add (prevbt);
        add (nextbt);
        add (exitbt);
        add (createnewrnetbt);
        add (addrta);
        add (cnewcusbt);
        add (clearbt);
        add (Car1);
   		add (Car2);
   		add (display);
   		add (deletebt);
   		  

        //set component bounds (only needed by Absolute Positioning)
        headLb.setBounds (100, 10, 250, 25);
        rentLb.setBounds (40, 50, 100, 25);
        townLb.setBounds (40, 170, 100, 25);
        addrLb.setBounds (40, 110, 100, 25);
        countyLb.setBounds (40, 200, 100, 25);
        countryLb.setBounds (40, 230, 100, 25);
        nameLb.setBounds (40, 80, 100, 25);
        rentaltf.setBounds (150, 50, 100, 25);
        nametf.setBounds (150, 80, 100, 25);
        towntf.setBounds (150, 170, 100, 25);
        couuntytf.setBounds (150, 200, 100, 25);
        countrytf.setBounds (150, 230, 100, 25);
        emailLb.setBounds (40, 260, 100, 25);
        phoneLb.setBounds (40, 290, 100, 25);
        emailtf.setBounds (150, 260, 100, 25);
        phonetf.setBounds (150, 290, 100, 25);
        findbt.setBounds (10, 320, 70, 25);
        prevbt.setBounds (100, 320, 70, 25);
        nextbt.setBounds (190, 320, 70, 25);
        exitbt.setBounds (10, 390, 340, 25);
        createnewrnetbt.setBounds (190, 355, 160, 25);
        addrta.setBounds (150, 110, 165, 50);
        cnewcusbt.setBounds (10, 355, 160, 25);
        clearbt.setBounds (280, 320, 70, 25);
        Car1.setBounds (5, 430, 155, 110);
       	Car2.setBounds (475, 430, 155, 110);
       	display.setBounds(270, 10, 455, 100);
       	deletebt.setBounds(370, 320, 70, 25);


       	 exitbt.addActionListener(this);
       	 createnewrnetbt.addActionListener(this);
       	 nextbt.addActionListener(this);
       	 prevbt.addActionListener(this);
       	 findbt.addActionListener(this);
       	 clearbt.addActionListener(this);
       	 deletebt.addActionListener(this);
       	 cnewcusbt.addActionListener(this);

        getContentPane().setBackground(Color.yellow);
        setVisible(true);

    RentalNo = "";
	cusName = "";
	Adress = "";
	town = "";
	County = "";
	Country = "";
	Email = "";
	PhoneNo = 0;


    }

        public void dbConn()
    {
	try
	{		// load the jdbc-odbc bridge driver manager
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// driver to use with named database
		String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=F:\\Hertz.mdb";

			// connection represents a session with a specific database
		con = DriverManager.getConnection(url);

			// stmt used for executing sql statements and obtaining results
		stmt = con.createStatement();

		rs = stmt.executeQuery("SELECT * FROM CustomerDetails");

		while(rs.next())	// count number of rows in table
			count++;
		rs.close();
	}
	catch(Exception e) { System.out.println("Error in start up......");}
    }

    public void initDB()
    {
	try		// display database info in gui
	{
		rs = stmt.executeQuery("SELECT * FROM CustomerDetails");
		rs.next();


	RentalNo = rs.getString("RentalNo");
	cusName = rs.getString("CusName");
	Adress = rs.getString("Adress");
	town = rs.getString("Town");
	County = rs.getString("County");
	Country = rs.getString("Country");
	Email = rs.getString("Email");
	PhoneNo = rs.getInt("PhoneNo");

		rentaltf.setText(" " +RentalNo);
        nametf.setText(" " +cusName);
        addrta.setText(" " +Adress);
        towntf.setText(" " +town);
        couuntytf.setText(" " +County);
        countrytf.setText(" " +Country);
        emailtf.setText(" " +Email);
        phonetf.setText(" " +PhoneNo);

		current = 1;
		rs.close();
	}
	catch(Exception e) {System.out.println("Error in initialising DB info to GUI");}
    }

    public void moveToRow(int index)
    {
	try
	{
		rs = stmt.executeQuery("SELECT * FROM CustomerDetails");

		for(int i = 0; i < index; i++)
			rs.next();  	// move to specific row in table (at index)
	RentalNo = rs.getString("RentalNo");
	cusName = rs.getString("CusName");
	Adress = rs.getString("Adress");
	town = rs.getString("Town");
	County = rs.getString("County");
	Country = rs.getString("Country");
	Email = rs.getString("Email");
	PhoneNo = rs.getInt("PhoneNo");

		rentaltf.setText(" " +RentalNo);
        nametf.setText(" " +cusName);
        addrta.setText(" " +Adress);
        towntf.setText(" " +town);
        couuntytf.setText(" " +County);
        countrytf.setText(" " +Country);
        emailtf.setText(" " +Email);
        phonetf.setText(" " +PhoneNo);
		current = index;
		rs.close();
	}
	catch(Exception e) {System.out.println("Error in moving to row at index specified");}
    }

public void findCus(String nameToFind)
    {
	int foundCus = 0;
	boolean found = false;
			
	try		// get name to search database for, use .equals() to match names
	{
		System.out.println("Finding: " +nameToFind);
		rs = stmt.executeQuery("SELECT * FROM CustomerDetails");

		while(rs.next() && found == false)
		{
			foundCus++;
			String curName = rs.getString("RentalNo");			
			curName.trim();	
						
			if(curName.equals(nameToFind))
				found = true;
		}
		rs.close();
	}
	
		catch(Exception e) {System.out.println("Error in finding Customer in database");}

	if(found == true)
		moveToRow(foundCus);
	else
		rentaltf.setText("Not Found");	
    }



public void actionPerformed(ActionEvent event)
{

	Object target = event.getSource();
	{
		if(target == cnewcusbt)		// update database with new doc info
	{
		try
		{
			
		String rent =	rentaltf.getText();
        String name = nametf.getText();
        String address = addrta.getText();
        String town = towntf.getText();
        String county = couuntytf.getText();
        String country = countrytf.getText();
        String email = emailtf.getText();
        String phone = phonetf.getText();
        
        

			
			String newCus = "INSERT INTO Hertz(RentalNo, CusName, Adress,Town, County,Country, Email, PhoneNo)VALUES('"+rent+"','"+name+"','"+town+"','"+county+"','"+country+"','"+email+"','"+phone+"')";
			stmt.executeUpdate(newCus);

			System.out.println(rent +" stored in database");
		}
		catch(Exception e) {System.out.println("Error in creating new Customer row in database");}

		count++;
	}

		if(target == exitbt)
			{
			new	exit();
			}
		if (target == createnewrnetbt)
			{
				new rentaldetails();
			}
		if(target == nextbt)
			{
		if(current != count)
			moveToRow(current + 1);	
			}
		if(target == prevbt)
			{
		if(current != 1)
			moveToRow(current - 1);
			}
		if(target == findbt)
			{
		try
		{
			RentalNo = rentaltf.getText();
			findCus(RentalNo);
		}
		catch(Exception e) {System.out.println("Error in find button");}
		}
		
		if(target == clearbt)
		{
		rentaltf.setText(" ");
        nametf.setText(" ");
        addrta.setText(" ");
        towntf.setText(" ");
        couuntytf.setText(" ");
        countrytf.setText(" ");
        emailtf.setText(" ");
        phonetf.setText(" ");
		}
		
		if(target == deletebt)
		{
		try	// get name to delete from database, use preparedstmt with wildcard
		{
			RentalNo = rentaltf.getText();
			
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM Hertz WHERE Name = ?");
			pstmt.setString(1,RentalNo);   		//sets the wildcard parameter to RentalNo
			pstmt.executeUpdate();		// execute delete

			System.out.println(RentalNo +" deleted from database");
		}
		catch(Exception e) {System.out.println("Error in delete button");}

		count --;
		}

		
		

	}
	
}

 	public static void main(String args[])
	{
CusDetails cus = new CusDetails();
	}
}

