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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.LayoutStyle.ComponentPlacement;

import oracle.net.jdbc.TNSAddress.Address;


public class AddNewParishionerPanel extends JPanel implements ActionListener{

	private final static int ADD_MODE = 1;
	private final static int EDIT_MODE = 2;
	private int workingMode;
	private Parishioner parishioner;
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
	private JTextField textPhoneNumber;
	private JDateChooser dateBirthday;
	private JDateChooser dateBaptism;
	private JDateChooser dateCommunion;
	private JDateChooser dateConfirmation;
	private JDateChooser dateMarriage;
	private JDateChooser dateDeath;
	JLabel lblDataLogin;
	JLabel lblLogin_;
	JLabel lblPassword_;
	JLabel lblRepeatPassword_;
	JLabel lblDeath_;
	JButton btnAdd;
	JButton btnClear;
	Events events = Events.getInstance();
	private JLabel label;

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
		panel.setBounds(0, 0, 508, 900);
		scrollPane.setViewportView(panel);
		
		
		lblDataLogin = new JLabel("Dane Logowania");
		lblDataLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDataLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		
		
		JLabel lblPhoneNumber_ = new JLabel("Nr Telefonu:");
		lblPhoneNumber_.setHorizontalAlignment(SwingConstants.RIGHT);
		textPhoneNumber = new JTextField();
		textPhoneNumber.setColumns(10);
		
		
		JLabel lblPrzebieg = new JLabel("Przebieg");
		lblPrzebieg.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrzebieg.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		btnAdd.addActionListener(this);
		btnClear = new JButton("Reset");
		btnClear.addActionListener(this);
		
		label = new JLabel("");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblLogin_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblRepeatPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(lblPhoneNumber_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textPhoneNumber, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblLogin_))
						.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPassword_))
						.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRepeatPassword_))
						.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
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
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPhoneNumber_))
						.addComponent(textPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
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
		Course course = new Course();
		Adress adress = new Adress();
		Parishioner parishioner = new Parishioner();
		parishioner.setName(textName.getText());
		parishioner.setSurName(textSurname.getText());
		parishioner.setPesel(textPesel.getText());
		course.setBirthday((!dateBirthday.isEmpty())?dateBirthday.getDate():null);
		adress.setCity(textCity.getText());
		adress.setStreet(textStreet.getText());
		adress.setHouseNumb(textHomeNumber.getText());
		adress.setPostcode(textPostCode.getText());
		//adress.setPhone(textPhoneNumber.getText());
		course.setBaptism((!dateBaptism.isEmpty())?dateBaptism.getDate():null);
		course.setCommunion((!dateCommunion.isEmpty())?dateCommunion.getDate():null);
		course.setConfirmation((!dateConfirmation.isEmpty())?dateConfirmation.getDate():null);
		course.setMarriage((!dateMarriage.isEmpty())?dateMarriage.getDate():null);
		course.setDeath((!dateDeath.isEmpty())?dateDeath.getDate():null);
		parishioner.setAdress(adress);
		parishioner.setCourse(course);
		return parishioner;
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
		textPhoneNumber.setText("");
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
			if(workingMode == ADD_MODE)
				;//events.addParishioner(getParishionerData());
			else if(workingMode == EDIT_MODE)
				;//events.editParishioner(getParishionerData());
		}else if(z == btnClear){
			if(workingMode == ADD_MODE)
				reset();
			else if(workingMode == EDIT_MODE)
				editMode();
		}
	}
	
	
}
