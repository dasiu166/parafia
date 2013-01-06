package gui.panels;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tLogin;
	private JPasswordField pwdPassword;
	private JButton bOK, bCansel;
	private boolean okData = false;

	/**
	 * Create the dialog.
	 */
	public LoginDialog(JFrame owner) {
		super(owner, "Wprowadzanie has³a", true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				okData = false;
			}
		});
		setResizable(false);
		setBounds(100, 100, 300, 220);
		getContentPane().setLayout(null);

		tLogin = new JTextField();
		tLogin.setText("ania");
		tLogin.setBounds(117, 38, 110, 23);
		tLogin.setColumns(10);
		getContentPane().add(tLogin);		
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("an11");
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
		
		JButton btnParishioner = new JButton("Parishioner");
		btnParishioner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setLoginData("ania", "an11");
				okData = true;
				setVisible(false);
			}
		});
		btnParishioner.setBounds(10, 158, 89, 23);
		getContentPane().add(btnParishioner);
		
		JButton btnWorker = new JButton("Worker");
		btnWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLoginData("pop2", "pop66");
				okData = true;
				setVisible(false);
			}
		});
		btnWorker.setBounds(109, 158, 76, 23);
		getContentPane().add(btnWorker);
		
		JButton btnGood = new JButton("Good");
		btnGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLoginData("pop_master", "pop55");
				okData = true;
				setVisible(false);
			}
		});
		btnGood.setBounds(195, 158, 89, 23);
		getContentPane().add(btnGood);
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
	
	private void setLoginData(String login, String password){
		tLogin.setText(login);
		pwdPassword.setText(password);
	}
}
