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
import obsluga.Priest;
import obsluga.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import javax.swing.LayoutStyle.ComponentPlacement;

//import oracle.net.jdbc.TNSAddress.Address;
import stale.KindRange;
import stale.KindRestriction;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class AddNewPriestPanel extends JPanel implements ActionListener{

	private final static int ADD_MODE = 1;
	private final static int EDIT_MODE = 2;
	private int workingMode;
	private Priest priest;
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
	private JLabel lblDataLogin;
	private JLabel lblLogin_;
	private JLabel lblPassword_;
	private JLabel lblRepeatPassword_;
	private JButton btnAdd;
	private JButton btnClear;
	private JDateChooser dateSecularity;
	private JDateChooser dateBeginWork;
	private JComboBox comboPosition;
	private Events events = Events.getInstance();

	public AddNewPriestPanel(JFrame owner, Priest priest){
		this(owner);
		this.priest = priest;
		this.workingMode = EDIT_MODE;
	}
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public AddNewPriestPanel(JFrame owner) {
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
		
		
		JLabel lblOthers = new JLabel("Inne");
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthers.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		btnAdd = new JButton("Dodaj");
		btnAdd.addActionListener(this);
		btnClear = new JButton("Reset");
		btnClear.addActionListener(this);
		
		comboPosition = new JComboBox();
		comboPosition.setModel(new DefaultComboBoxModel(new String[] {"<wybierz Stanowisko>", "Wikary", "Proboszcz"}));
		
		dateSecularity = new JDateChooser(true);
		
		dateBeginWork = new JDateChooser(true);
		
		JLabel lblSecularity_ = new JLabel("Data \u015Awi\u0119ce\u0144:");
		lblSecularity_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBeginWork_ = new JLabel("Data Przybycia:");
		lblBeginWork_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPosition_ = new JLabel("Stanowisko:");
		lblPosition_.setHorizontalAlignment(SwingConstants.RIGHT);
		
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
							.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSecularity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dateSecularity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblOthers, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPosition_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBeginWork_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboPosition, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(dateBeginWork, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))))
					.addContainerGap(63, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(272)
					.addComponent(btnClear)
					.addGap(10)
					.addComponent(btnAdd)
					.addContainerGap(85, Short.MAX_VALUE))
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
					.addComponent(lblOthers, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateSecularity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecularity_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(dateBeginWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPosition_)))
						.addComponent(lblBeginWork_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClear)
						.addComponent(btnAdd))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);		
		
		setLayout(groupLayout);
		setMode();
		
	}

	public Priest getPriestData(){
		Adress adress = new Adress();
		Priest priest = new Priest();
		priest.setName(textName.getText());
		priest.setSurName(textSurname.getText());
		priest.setPesel(textPesel.getText());
		//((!dateBirthday.isEmpty())?dateBirthday.getDate():null);
		adress.setCity(textCity.getText());
		adress.setStreet(textStreet.getText());
		adress.setHouseNumb(textHomeNumber.getText());
		adress.setPostcode(textPostCode.getText());
		//adress.setPhone(textPhoneNumber.getText());
		priest.setSecularityDate(((!dateSecularity.isEmpty())?dateSecularity.getDate():null));
		priest.setArrivalDate(((!dateBeginWork.isEmpty())?dateBeginWork.getDate():null));
	
		priest.setAdress(adress);
		
		//pobieranie pozycji ksiedza
		priest.setPass(passwordPassword.getPassword().toString());
		if(comboPosition.getSelectedIndex()==1)
			priest.setPossition("Wikary");
		else if(comboPosition.getSelectedIndex()==2)
			priest.setPossition("Proboszcz");
		return priest;
	}
	
	public User getUserData(){
		User user = new User();
		user.setId(0);
		user.setLogin(textLogin.getText());
		user.setPassword(passwordPassword.getText());
		user.setRestriction(KindRestriction.WORKS_R);
		user.setRange(KindRange.WORKER_RANG);		
		return user;
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
		dateSecularity.setEmpty();
		dateBeginWork.setEmpty();
		comboPosition.setSelectedIndex(0);
	}
	
	public void setEditMode(Priest priest){
		workingMode = EDIT_MODE;
		this.priest = priest;
		editMode();
	}
	
	private void setMode(){
		if(workingMode == EDIT_MODE)
			editMode();
		else if(workingMode == ADD_MODE)
			addMode();
	}
	
	private void addMode(){
		
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
		
		textName.setText(priest.getName());
		textSurname.setText(priest.getSurName());
		textPesel.setText(priest.getPesel());
		//date = priest.getCourse().getBirthDay();		if(date!=null)	dateBirthday.setDate(date);
		textCity.setText(priest.getAdress().getCity());
		textStreet.setText(priest.getAdress().getStreet());
		textHomeNumber.setText(priest.getAdress().getHouse());
		textPostCode.setText(priest.getAdress().getPostcode());
		date = priest.getSecularityDate();			if(date!=null)	dateSecularity.setDate(date);
		date = priest.getArrivalDate();				if(date!=null)	dateBeginWork.setDate(date);
		
		//jeszcze brak obslugi wybierania pozycji ksiedza
		//priest.getPosition();
		//comboPosition.setSelectedIndex(index);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		if(z == btnAdd){
			if(workingMode == ADD_MODE){
				if(passwordPassword.getText().equals(passwordRepeatPassword.getText())){
					Priest priest = getPriestData();
					try {
						events.dodajUzytkownika(getUserData(), priest.getAdress(), priest);
						if(events.getLastErr().equals("OK+")){
							JOptionPane.showMessageDialog(null, "Ksiadz dodany");
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
		}
	}
}
