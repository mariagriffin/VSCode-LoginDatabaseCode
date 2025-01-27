import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LoginFormDatabaseSTUDENT extends javax.swing.JFrame {

	private javax.swing.JButton btnLogin;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelName;
	private javax.swing.JLabel jLabelPswrd;
	private javax.swing.JLabel jLabelDept;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField txtPwd;
	private javax.swing.JTextField txtUsername;
	private javax.swing.JTextField txtDept;
	/**
	 * Creates new form LoginForm
	 */

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public LoginFormDatabaseSTUDENT() {
		initComponents();
		setTitle("Login Form");

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
//part 1	//fix the getText()	warning!
				String usernameEntered = txtUsername.getText();
				String passwordEntered = txtPwd.getText();				
				String departmentEntered = txtDept.getText();

//part 2  	// What changes can you add here to make this login more secure?
			// what if the values are null?? Are we checking?
			// Output an appropriate message for null details

		//Task: Check that the user has entered a value for all 3 inputs.
		//	(if you want to challenge yourself - check, using regex, that the username is in the format firstname.secondname)

				try {

					conn = dbConnect();
					System.out.println("Creating statement...");
						
//PART 3 	//Fix this code to use a prepared Statement
					String sql = "SELECT * FROM users where department = '" + departmentEntered + "'" ;   						
					//System.out.println("sql" + sql);	
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

					while (rs.next()) {				
						System.out.println("Users available  " + rs.getString("username"));					
					}
						
					rs = stmt.executeQuery(sql);             
					boolean matchFound = false;
					while (rs.next()) {

				//Here we validate that the username and password match an entry in the database
//PART 4		//there are no errors in the code, yet this is not working why????  Why do we get : INVALID USER!?
			
						if (usernameEntered == rs.getString("username") && passwordEntered == rs.getString("password")) {
							JOptionPane.showMessageDialog(LoginFormDatabaseSTUDENT.this, "Valid User: " + usernameEntered);
						 	matchFound = true;
							break;
					} 							
				}
				if (matchFound == false) {
					JOptionPane.showMessageDialog(LoginFormDatabaseSTUDENT.this, "Invalid User Details");
				}
				
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				// STEP 6: Clean-up environment
				dbClose();
			}

		});
	}

	public static void main(String args[]) {

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginFormDatabaseSTUDENT().setVisible(true);

			}
		});
	}

	/**
	 * This method is called to connect to the database. WARNING: Do NOT modify this
	 * code.
	 */
	private Connection dbConnect() {
					/** CHANGE TO MATCH YOUR DATABSE **/
		// database URL and credentials
		final String DB_URL = "jdbc:mysql://localhost:3306/mysecureapplication";
		final String USER = "root";
		final String PASS = "password";

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return conn;

	}

	/**
	 * This method is called to connect to the database. WARNING: Do NOT modify this
	 * code.
	 */
	private void dbClose() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**** YOU DO NOT NEED TO MODIFY THE CODE BELOW HERE!! ***/

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code.
	 */
	private void initComponents() {

		// jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabelName = new javax.swing.JLabel();
		jLabelPswrd = new javax.swing.JLabel();
		jLabelDept = new javax.swing.JLabel();
		btnLogin = new javax.swing.JButton();
		txtUsername = new javax.swing.JTextField();
		txtPwd = new javax.swing.JPasswordField();
		txtDept = new javax.swing.JTextField();

		jLabelName.setText("Username");
		jLabelPswrd.setText("Password");
		jLabelDept.setText("Department");
		btnLogin.setText("login");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setText("LYIT Login");
		jPanel1 = new JPanel(new GridBagLayout());

		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		jPanel1.add(jLabelName, cs);

		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 25;
		jPanel1.add(txtUsername, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		jPanel1.add(jLabelPswrd, cs);

		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 25;
		jPanel1.add(txtPwd, cs);
		
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		jPanel1.add(jLabelDept, cs);

		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 25;
		jPanel1.add(txtDept, cs);

		cs.gridx = 3;
		cs.gridy = 3;
		cs.gridwidth = 2;
		jPanel1.add(btnLogin, cs);

		getContentPane().add(jPanel1, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setSize(270, 250);

	}
}
