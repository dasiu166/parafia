package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLogowania extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tLogin;
	private JPasswordField pwdPassword;
	private JButton bOK, bCansel;
	private boolean okData;

	/**
	 * Create the dialog.
	 */
	public DialogLogowania(JFrame owner) {
		super(owner, "Wprowadzanie has³a", true);
		setResizable(false);
		setBounds(100, 100, 300, 220);
		getContentPane().setLayout(null);

		tLogin = new JTextField();
		tLogin.setText("user");
		tLogin.setBounds(117, 38, 110, 23);
		tLogin.setColumns(10);
		getContentPane().add(tLogin);		
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("user");
		pwdPassword.setToolTipText("");
		pwdPassword.setBounds(117, 67, 110, 23);
		getContentPane().add(pwdPassword);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(61, 41, 46, 14);
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblLogin);
		
		
		JLabel lblPassword = new JLabel("Has\u0142o:");
		lblPassword.setBounds(61, 72, 46, 14);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblPassword);		

		bOK = new JButton("Zaloguj");
		bOK.setBounds(61, 114, 76, 23);
		bOK.setActionCommand("OK");
		getContentPane().add(bOK);
		getRootPane().setDefaultButton(bOK);
		bOK.addActionListener(this);


		bCansel = new JButton("Anuluj");
		bCansel.setBounds(154, 114, 73, 23);
		bCansel.setActionCommand("Cancel");
		getContentPane().add(bCansel);
		bCansel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		if(z == bOK){
			okData = true;
		}else{
			okData = false;
		}
		
		setVisible(false);
	}
	
	public String getLogin(){
		return tLogin.getText();
	}
	
	public String getPassword(){
		return new String(pwdPassword.getPassword());
	}
	
	public boolean isOK(){
		return okData;
	}
	
	public void setFocus(){
		tLogin.requestFocusInWindow();
	}
}
