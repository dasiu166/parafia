package gui.panels.parishioner;


import gui.Events;
import gui.calendar.JCalendar;
import gui.calendar.JDateChooser;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import obsluga.Adress;
import obsluga.Course;
import obsluga.Parishioner;
import obsluga.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import javax.swing.LayoutStyle.ComponentPlacement;

//import oracle.net.jdbc.TNSAddress.Address;
import stale.KindRange;
import stale.KindRestriction;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import pomoce.Pomoc;

import java.awt.Color;


public class UpdateLoginDataPanel extends JPanel implements ActionListener{

	private final static int ADD_MODE = 1;
	private final static int EDIT_MODE = 2;
	//private int workingMode;
	
	private Parishioner parishioner = new Parishioner();
	private User user = new User();
	
	private static final long serialVersionUID = 7694386932112268103L;
	private JTextField textLogin;
	private JPasswordField passwordPassword;
	private JPasswordField passwordRepeatPassword;
	private JLabel lblDataLogin;
	private JLabel lblLogin_;
	private JLabel lblPassword_;
	private JLabel lblRepeatPassword_;
	private JButton btnAdd;
	private JButton btnClear;
	private Events events = Events.getInstance();
	private JLabel label;
	private JPasswordField stareHaslo;
	JLabel lblStareHaslo;
	private JButton btnGenPas;
	private JLabel lblNewPas;
	

	public UpdateLoginDataPanel(JFrame owner, Parishioner parishioner){
		this(owner);
		this.parishioner = parishioner;
	
	}
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public UpdateLoginDataPanel(JFrame owner) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 204)));
		panel.setBounds(0, 0, 508, 900);
		scrollPane.setViewportView(panel);
		
		
		lblDataLogin = new JLabel("Zmiana danych logowania");
		lblDataLogin.setForeground(new Color(0, 0, 204));
		lblDataLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDataLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		lblLogin_ = new JLabel("Login:");
		lblLogin_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textLogin = new JTextField();
		textLogin.setText(events.getUser().getLogin());
		textLogin.setColumns(10);
		
		lblPassword_ = new JLabel("Nowe has\u0142o:");
		lblPassword_.setHorizontalAlignment(SwingConstants.RIGHT);		
		passwordPassword = new JPasswordField();
		
		lblRepeatPassword_ = new JLabel("Powt\u00F3rz nowe has\u0142o:");
		lblRepeatPassword_.setHorizontalAlignment(SwingConstants.RIGHT);		
		passwordRepeatPassword = new JPasswordField();
		
		
		btnAdd = new JButton("Zmieñ");
		btnAdd.setIcon(new ImageIcon(UpdateLoginDataPanel.class.getResource("/icons/Add-icon.png")));
		btnAdd.addActionListener(this);
		btnClear = new JButton("Reset");
		btnClear.setIcon(new ImageIcon(UpdateLoginDataPanel.class.getResource("/icons/reset.png")));
		btnClear.addActionListener(this);
		
		label = new JLabel("");
		
		lblStareHaslo = new JLabel("Stare has\u0142o");
		
		stareHaslo = new JPasswordField();
		stareHaslo.setColumns(10);
		
		btnGenPas = new JButton("Stw\u00F3rz has\u0142o");
		btnGenPas.addActionListener(this);
		
		lblNewPas = new JLabel("#####");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnGenPas)
									.addGap(18)
									.addComponent(lblNewPas)
									.addGap(74)
									.addComponent(btnClear)
									.addGap(10)
									.addComponent(btnAdd))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRepeatPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStareHaslo)
								.addComponent(lblLogin_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(stareHaslo)
								.addComponent(textLogin, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin_))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStareHaslo)
						.addComponent(stareHaslo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword_)
						.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepeatPassword_)
						.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnClear)
							.addComponent(btnAdd))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnGenPas)
							.addComponent(lblNewPas)))
					.addGap(20)
					.addComponent(label)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);		
		
		setLayout(groupLayout);
		
		
	}
	
	


	
	public User getUserData(){
		return this.user;
	}
	
	public boolean setUserData(User user){
		//User user = new User();
		//user.setId(0);
		
	if(stareHaslo.getText().equals(events.getUser().getPassword())){	
		
		if(textLogin.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótki login (min 5 znaków)");
		    return false;
		} 
		if(textLogin.getText().equals(events.getUser().getLogin())){
			user.setLogin(null);
		} else user.setLogin(textLogin.getText());
		
		if (passwordPassword.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótkie has³o (min 5 znaków)");
		    return false;
		} else if(!passwordPassword.getText().equals(passwordRepeatPassword.getText())){
	    	JOptionPane.showMessageDialog(null, "Powtórzone has³o siê nie zgadza");
		    return false;	
	    }else user.setPassword(passwordPassword.getText());
		
		user.setRestriction(events.getUser().getRestriction());
		user.setRange(events.getUser().getRange());		
		return true;
	} else {
		JOptionPane.showMessageDialog(null, "Poda³eœ z³e has³o");
	    return false;
	}
	
	}
	
	public void setLoginField(String s){
		textLogin.setText(s);
	}
	
	private void reset(){
		textLogin.setText("");
		passwordPassword.setText("");
		passwordRepeatPassword.setText("");
		stareHaslo.setText("");
		
		
	}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		if(z == btnAdd){
			
			if(!this.setUserData(user)) return;
			try{
			events.updateUzytkownik(user.getLogin(), user.getPassword());	
			}catch(ClassNotFoundException ee){
				
			}catch(IOException eee){
				
			}
			JOptionPane.showMessageDialog(null, events.getLastErrData());
		}else if(z == btnClear){
			
				reset();
			
		} else if (z==btnGenPas){
			String pas = Pomoc.losujString(5);
			lblNewPas.setText(pas);
			passwordPassword.setText(pas);
			passwordRepeatPassword.setText(pas);
		}
	}
}

