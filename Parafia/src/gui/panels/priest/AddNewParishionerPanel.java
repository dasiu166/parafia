package gui.panels.priest;

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


public class AddNewParishionerPanel extends JPanel implements ActionListener{

	private final static int ADD_MODE = 1;
	private final static int EDIT_MODE = 2;
	private int workingMode;
	
	private Parishioner parishioner = new Parishioner();
	private User user = new User();
	
	private static final long serialVersionUID = 7694386932112268103L;
	private JTextField textLogin;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textCity;
	private JTextField textStreet;
	private JTextField textHomeNumber;
	private JTextField textPesel;
	private JTextField textPostCode;
	private JPasswordField passwordPassword;
	private JPasswordField passwordRepeatPassword;
	private JDateChooser dateBirthday;
	private JDateChooser dateBaptism;
	private JDateChooser dateCommunion;
	private JDateChooser dateConfirmation;
	private JDateChooser dateMarriage;
	private JDateChooser dateDeath;
	private JLabel lblDataLogin;
	private JLabel lblLogin_;
	private JLabel lblPassword_;
	private JLabel lblRepeatPassword_;
	private JLabel lblDeath_;
	private JButton btnAdd;

	private JButton btnClear;
	private Events events = Events.getInstance();
	private JLabel label;
	private JButton btnGenPass;
	private JLabel lblNewPas;

	public AddNewParishionerPanel(JFrame owner, Parishioner parishioner){
		this(owner);
		this.parishioner = parishioner;
		this.workingMode = EDIT_MODE;
	}
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public AddNewParishionerPanel(JFrame owner) {
		this.workingMode = ADD_MODE;
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
		
		
		lblDataLogin = new JLabel("Dane Logowania");
		lblDataLogin.setForeground(new Color(0, 0, 204));
		lblDataLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDataLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		lblLogin_ = new JLabel("Login:");
		lblLogin_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textLogin = new JTextField();
		textLogin.setColumns(10);
		
		lblPassword_ = new JLabel("Has\u0142o:");
		lblPassword_.setHorizontalAlignment(SwingConstants.RIGHT);		
		passwordPassword = new JPasswordField();
		
		lblRepeatPassword_ = new JLabel("Powt\u00F3rz has\u0142o:");
		lblRepeatPassword_.setHorizontalAlignment(SwingConstants.RIGHT);		
		passwordRepeatPassword = new JPasswordField();
		
		
		JLabel lblData = new JLabel("Dane");
		lblData.setForeground(new Color(0, 0, 204));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblName_ = new JLabel("Imie:");
		lblName_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textName = new JTextField();
		textName.setColumns(10);
		
		JLabel lblSurname_ = new JLabel("Nazwisko:");
		lblSurname_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textSurname = new JTextField();
		textSurname.setColumns(10);
		
		JLabel lblPesel_ = new JLabel("pesel:");
		lblPesel_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textPesel = new JTextField();
		textPesel.setColumns(10);
		
		JLabel lblBirthday_ = new JLabel("Data urodzenia:");
		lblBirthday_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateBirthday = new JDateChooser(true);
		
		
		JLabel lblAdress = new JLabel("Adres");
		lblAdress.setForeground(new Color(0, 0, 204));
		lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdress.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblCity_ = new JLabel("Miasto:");
		lblCity_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textCity = new JTextField();
		textCity.setColumns(10);
		
		JLabel lblStreet_ = new JLabel("Ulica:");
		lblStreet_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textStreet = new JTextField();
		textStreet.setColumns(10);
		
		JLabel lblHomeNumber_ = new JLabel("Nr domu/mieszkania:");
		lblHomeNumber_.setHorizontalAlignment(SwingConstants.RIGHT);		
		textHomeNumber = new JTextField();
		textHomeNumber.setColumns(10);		

		JLabel lblPostCode_ = new JLabel("Kod pocztowy:");
		lblPostCode_.setHorizontalAlignment(SwingConstants.RIGHT);
		textPostCode = new JTextField();
		textPostCode.setColumns(10);
		
		
		JLabel lblPrzebieg = new JLabel("Przebieg");
		lblPrzebieg.setForeground(new Color(0, 0, 204));
		lblPrzebieg.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrzebieg.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblBaptism_ = new JLabel("Chrzest:");
		lblBaptism_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateBaptism = new JDateChooser(true);
		
		JLabel lblCommunion_ = new JLabel("Komunia \u015Awi\u0119ta:");
		lblCommunion_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateCommunion = new JDateChooser(true);
		
		JLabel lblConfirmation_ = new JLabel("Bierzmowanie:");
		lblConfirmation_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateConfirmation = new JDateChooser(true);
		
		JLabel lblMarriage_ = new JLabel("\u015Alub:");
		lblMarriage_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateMarriage = new JDateChooser(true);
		
		lblDeath_ = new JLabel("\u015Amier\u0107:");
		lblDeath_.setHorizontalAlignment(SwingConstants.RIGHT);		
		dateDeath = new JDateChooser(true);
		
		
		btnAdd = new JButton("Dodaj");
		btnAdd.setIcon(new ImageIcon(AddNewParishionerPanel.class.getResource("/icons/Add-icon.png")));
		btnAdd.addActionListener(this);
		btnClear = new JButton("Reset");
		btnClear.setIcon(new ImageIcon(AddNewParishionerPanel.class.getResource("/icons/reset.png")));
		btnClear.addActionListener(this);
		
		
		label = new JLabel("");
		
		btnGenPass = new JButton("Stw\u00F3rz has\u0142o");
		btnGenPass.addActionListener(this);
		
		lblNewPas = new JLabel("#####");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblName_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblSurname_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPesel_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblAdress, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblCity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblStreet_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblHomeNumber_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textHomeNumber, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPostCode_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPrzebieg, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(71)
							.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(71)
							.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(71)
							.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(274)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnClear)
									.addGap(10)
									.addComponent(btnAdd))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblRepeatPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(passwordRepeatPassword))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
									.addGap(30)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewPas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnGenPass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblLogin_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin_)
						.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblPassword_))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewPas)
								.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblRepeatPassword_))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGenPass))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblName_))
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSurname_))
						.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPesel_))
						.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(lblAdress, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCity_))
						.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblStreet_))
						.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblHomeNumber_))
						.addComponent(textHomeNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPostCode_))
						.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(lblPrzebieg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClear)
						.addComponent(btnAdd))
					.addGap(20)
					.addComponent(label)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);		
		
		setLayout(groupLayout);
		setMode();
		
	}
	
	public Parishioner getParishionerData(){
		return this.parishioner;
	}

	public boolean setParishionerData(Parishioner prr){
		boolean tryCatch=true;
		
		Course course = new Course();
		Adress adress = new Adress();
		//Parishioner parishioner = new Parishioner();
		
		//imie
		if(textName.getText().length()<3){
			JOptionPane.showMessageDialog(null, "Za krótkie imie (min 3 znaki)");
			return false;
		} else prr.setName(textName.getText());
			
		
		//nazwisko
		if(textSurname.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Za krótkie nazwisko (min 2 znaki)");
			return false;
		}else prr.setSurName(textSurname.getText());
		
		
		//pesel
		if((textPesel.getText().length()<11)||(textPesel.getText().length()>11)){
		    JOptionPane.showMessageDialog(null, "Pesel musi mieæ 11 liczb");
		    return false;
		}else 
		if(textPesel.getText().contains(".")){
			JOptionPane.showMessageDialog(null, "Pesel mo¿e zawieraæ tylko liczby");
		    return false;
		} else {
			try{
				Double.parseDouble(textPesel.getText());
				
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Pesel mo¿e zawieraæ tylko liczby");
			    //return false;
				tryCatch=false;
			}
		}
		
		if(!tryCatch) return false; else prr.setPesel(textPesel.getText());
		
		//data urodzenia
		int i;
		//JOptionPane.showMessageDialog(null, "przechodze do dat");

		if(dateBirthday.isEmpty()){
			JOptionPane.showMessageDialog(null, "Podaj datê urodzenia");
		    return false;
		} else  i = dateBirthday.getDate().compareTo(new Date()); 
		if(i>0){
			JOptionPane.showMessageDialog(null, "Data urodzenie jest b³edna \n" +
					"Taki dzien jeszcze nie nast¹pi³");
		    return false;
		} else course.setBirthday(dateBirthday.getDate());
		
		
		//adress
		if(textCity.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Nazwa miasta jest za krótka (min 2 znaki)");
		    return false;
		} else adress.setCity(textCity.getText());
		if(textStreet.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Nazwa ulicy jest za krótka (min 2 znaki)");
		    return false;
		}else adress.setStreet(textStreet.getText());
		if(textHomeNumber.getText().length()<1){
			JOptionPane.showMessageDialog(null, "Numer domu jest za krótki (min 1 znaki)");
		    return false;
		}else adress.setHouseNumb(textHomeNumber.getText());
		if(textPostCode.getText().length()<6){
			JOptionPane.showMessageDialog(null, "Kod pocztowy jest za krótki (min 6 znaki)");
		    return false;
		}else adress.setPostcode(textPostCode.getText());
		
		
		//course
		
		if(dateBaptism.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Podaj date chrztu");
		    return false;
			}else
			if(dateBaptism.getDate().compareTo(dateBirthday.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data chrztu jest przed dat¹ urodzenia");
			    return false;
			} else course.setBaptism(dateBaptism.getDate());
		
		if(dateCommunion.isEmpty()) course.setCommunion(null); else
			if(dateCommunion.getDate().compareTo(dateBaptism.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data komunii œw. jest przed dat¹ chrztu");
			    return false;
			} else course.setCommunion(dateCommunion.getDate());
		
		if(dateConfirmation.isEmpty()) course.setConfirmation(null); else
			if(dateConfirmation.getDate().compareTo(dateCommunion.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data bierzmowania jest przed dat¹ komunii œw.");
			    return false;
			} else course.setConfirmation(dateConfirmation.getDate());
		
		
		if(dateMarriage.isEmpty()) course.setMarriage(null); else
			if(dateMarriage.getDate().compareTo(dateConfirmation.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data œlubu jest przed dat¹ bierzmowania");
			    return false;
			} else course.setMarriage(dateMarriage.getDate());
		
		if(dateDeath.isEmpty()) course.setDeath(null); else
			if(dateDeath.getDate().compareTo(dateBirthday.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data œmierci jest przed dat¹ urodzenia");
			    return false;
			} else course.setMarriage(dateDeath.getDate());
		
		
		
		prr.setAdress(adress);
		prr.setCourse(course);
		prr.setPass(passwordPassword.getPassword().toString());
		return true;
	}
	
	public User getUserData(){
		return this.user;
	}
	
	public boolean setUserData(User user){
		//User user = new User();
		//user.setId(0);
		if(textLogin.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótki login (min 5 znaków)");
		    return false;
		} else user.setLogin(textLogin.getText());
		
		if (passwordPassword.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótkie has³o (min 5 znaków)");
		    return false;
		} else user.setPassword(passwordPassword.getText());
		
		user.setRestriction(KindRestriction.LOGED_R);
		user.setRange(KindRange.LOGG_RANG);		
		return true;
	}
	
	private void reset(){
		textLogin.setText("");
		passwordPassword.setText("");
		passwordRepeatPassword.setText("");
		textName.setText("");
		textSurname.setText("");
		textPesel.setText("");
		dateBirthday.setEmpty();
		textCity.setText("");
		textStreet.setText("");
		textHomeNumber.setText("");
		textPostCode.setText("");
		//textPhoneNumber.setText("");
		//dateBaptism = new JDateChooser(new JCalendar(new Date()));
		dateBaptism.setEmpty();
		dateCommunion.setEmpty();
		dateConfirmation.setEmpty();
		dateMarriage.setEmpty();
		dateDeath.setEmpty();
	}
	
	public void setEditMode(Parishioner parishioner){
		workingMode = EDIT_MODE;
		this.parishioner = parishioner;
		editMode();
	}
	
	private void setMode(){
		if(workingMode == EDIT_MODE)
			editMode();
		else if(workingMode == ADD_MODE)
			addMode();
	}
	
	private void addMode(){
		dateDeath.setVisible(false);
		dateDeath.setEnabled(false);
		lblDeath_.setVisible(false);
		lblDeath_.setEnabled(false);
	}
	
	private void editMode(){
		Date date;
		lblDataLogin.setVisible(false);
		lblDataLogin.setEnabled(false);
		lblLogin_.setVisible(false);
		lblLogin_.setEnabled(false);
		textLogin.setVisible(false);
		textLogin.setEnabled(false);
		lblPassword_.setVisible(false);
		lblPassword_.setEnabled(false);
		passwordPassword.setVisible(false);
		passwordPassword.setEnabled(false);
		lblRepeatPassword_.setVisible(false);
		lblRepeatPassword_.setEnabled(false);
		passwordRepeatPassword.setVisible(false);
		passwordRepeatPassword.setEnabled(false);
		lblDeath_.setVisible(true);
		lblDeath_.setEnabled(true);
		dateDeath.setVisible(true);
		dateDeath.setEnabled(true);
		
		textName.setText(parishioner.getName());
		textSurname.setText(parishioner.getSurName());
		textPesel.setText(parishioner.getPesel());
		date = parishioner.getCourse().getBirthDay();		if(date!=null)	dateBirthday.setDate(date);
		textCity.setText(parishioner.getAdress().getCity());
		textStreet.setText(parishioner.getAdress().getStreet());
		textHomeNumber.setText(parishioner.getAdress().getHouse());
		textPostCode.setText(parishioner.getAdress().getPostcode());
		date = parishioner.getCourse().getBaptism();		if(date!=null)	dateBaptism.setDate(date);
		date = parishioner.getCourse().getCommunion();		if(date!=null)	dateCommunion.setDate(date);
		date = parishioner.getCourse().getConfirmation();	if(date!=null)	dateConfirmation.setDate(date);
		date = parishioner.getCourse().getMarriage();		if(date!=null)	dateMarriage.setDate(date);
		date = parishioner.getCourse().getDeath();			if(date!=null)	dateDeath.setDate(date);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		if(z == btnAdd){
			if(workingMode == ADD_MODE){
				if(!this.setUserData(user)) return;//wyjdz jezeli blad w danych usera
				
				if(passwordPassword.getText().equals(passwordRepeatPassword.getText())){
					if(!this.setParishionerData(parishioner)) return;//wyjdz (zle dane)
					Parishioner pr = getParishionerData();
					try {
						events.dodajUzytkownika(getUserData(), pr.getAdress(), pr.getCourse(), pr);
						if(events.getLastErr().equals("OK+")){
							JOptionPane.showMessageDialog(null, "Parafianin dodany");
						} else {
							JOptionPane.showMessageDialog(null, events.getLastErrData());
						}
					} catch (ClassNotFoundException e1) {e1.printStackTrace();	} catch (IOException e1) { e1.printStackTrace();}
				} else {
					JOptionPane.showMessageDialog(null, "Has³a musz¹ byæ takie same", "B³¹d Has³a", JOptionPane.WARNING_MESSAGE);
				}
			} else if(workingMode == EDIT_MODE)
				;//events.editParishioner(getParishionerData());
		}else if(z == btnClear){
			if(workingMode == ADD_MODE)
				reset();
			else if(workingMode == EDIT_MODE)
				editMode();
		} else if (z==btnGenPass){
			String pas = Pomoc.losujString(5);
			lblNewPas.setText(pas);
			passwordPassword.setText(pas);
			passwordRepeatPassword.setText(pas);
		}
	}
}
