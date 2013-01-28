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
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import pomoce.Pomoc;

import java.awt.Color;


public class AddNewPriestPanel extends JPanel implements ActionListener{

	private final static int ADD_MODE = 1;
	private final static int EDIT_MODE = 2;
	private int workingMode;
	private Priest priest = new Priest();
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
	private JLabel lblDataLogin;
	private JLabel lblLogin_;
	private JLabel lblPassword_;
	private JLabel lblRepeatPassword_;
	private JButton btnAdd;
	private JButton btnClear;
	private JDateChooser dateSecularity;
	private JDateChooser dateBeginWork;
	private JComboBox comboPosition;
	private JButton btnGenPas;
	private JLabel lblGenPas;
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
		panel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(128, 0, 128)));
		panel.setBounds(0, 0, 508, 900);
		scrollPane.setViewportView(panel);
		
		
		lblDataLogin = new JLabel("Dane Logowania");
		lblDataLogin.setForeground(new Color(128, 0, 128));
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
		lblData.setForeground(new Color(128, 0, 128));
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
		
		
		JLabel lblAdress = new JLabel("Adres");
		lblAdress.setForeground(new Color(128, 0, 128));
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
		
		
		JLabel lblOthers = new JLabel("Inne");
		lblOthers.setForeground(new Color(128, 0, 128));
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		
		btnAdd = new JButton("Dodaj");
		btnAdd.setIcon(new ImageIcon(AddNewPriestPanel.class.getResource("/icons/Add-icon.png")));
		btnAdd.addActionListener(this);
		btnClear = new JButton("Reset");
		btnClear.setIcon(new ImageIcon(AddNewPriestPanel.class.getResource("/icons/reset.png")));
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
		
		btnGenPas = new JButton("Stw\u00F3rz has\u0142o");
		btnGenPas.addActionListener(this);
		lblGenPas = new JLabel("#####");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblName_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSurname_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPesel_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAdress, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblStreet_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHomeNumber_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textHomeNumber, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPostCode_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDataLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
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
											.addComponent(dateBeginWork, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lblPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(passwordPassword))
											.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
												.addComponent(lblRepeatPassword_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(btnGenPas)
											.addComponent(lblGenPas)))
									.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
										.addComponent(lblLogin_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(272)
							.addComponent(btnClear)
							.addGap(10)
							.addComponent(btnAdd)))
					.addContainerGap(110, Short.MAX_VALUE))
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
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGenPas))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRepeatPassword_))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(passwordRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnGenPas)))
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
					.addGap(32)
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
		return this.priest;
	}
	
	public boolean setPriestData(Priest pr){
		Adress adress = new Adress();
		boolean tryCatch=true;
		//Priest priest = new Priest();
		
		if(textName.getText().length()<3){
			JOptionPane.showMessageDialog(null, "Za krótkie imiê (min 3 znaki)");
			return false;
		} else pr.setName(textName.getText());
		
		if(textSurname.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Za krótkie nazwisko (min 2 znaki)");
			return false;
		} else pr.setSurName(textSurname.getText());
		
		
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
		
		if(!tryCatch) return false; else pr.setPesel(textPesel.getText());
		
		int i;
		//JOptionPane.showMessageDialog(null, "przechodze do dat");
		
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
		
		if(dateSecularity.isEmpty()){
			JOptionPane.showMessageDialog(null, "Podaj datê œwiêceñ");
		    return false;
		} else  i = dateSecularity.getDate().compareTo(new Date()); 
		if(i>0){
			JOptionPane.showMessageDialog(null, "Data œwiêceñ jest b³edna \n" +
					"Taki dzien jeszcze nie nast¹pi³");
		    return false;
		} else pr.setSecularityDate(dateSecularity.getDate());
		
		
		if(dateBeginWork.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Podaj date przybycia do parafii");
		    return false;
			}else
			if(dateBeginWork.getDate().compareTo(dateSecularity.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data przybycia jest przed dat¹ œwiêceñ");
			    return false;
			} else pr.setArrivalDate(dateBeginWork.getDate());
		
		
		pr.setAdress(adress);
		
		//pobieranie pozycji ksiedza
		pr.setPass(passwordPassword.getPassword().toString());
		if(comboPosition.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Wybierz stanowisko");
		    return false;
		} else
		if(comboPosition.getSelectedIndex()==1){
			pr.setPossition("Wikary");
			user.setRestriction(KindRestriction.WORKS_R);
			user.setRange(KindRange.WORKER_RANG);	
			}
		else if(comboPosition.getSelectedIndex()==2){
			pr.setPossition("Proboszcz");
			user.setRestriction(KindRestriction.GOD_R);
			user.setRange(KindRange.GOD_RANG);
		}
		
		return true;
	}
	
	
	public User getUserData(){
		return this.user;
	}
	
	public boolean setUserData(User user){
		//User user = new User();
		if(textLogin.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótki login (min 5 znaków)");
		    return false;
		} else user.setLogin(textLogin.getText());
		
		if (passwordPassword.getText().length()<5){
			JOptionPane.showMessageDialog(null, "Za krótkie has³o (min 5 znaków)");
		    return false;
		} else user.setPassword(passwordPassword.getText());
		
		//user.setRestriction(KindRestriction.WORKS_R);
		//user.setRange(KindRange.WORKER_RANG);		
		return true;
	}
	
	private void reset(){
		textLogin.setText("");
		passwordPassword.setText("");
		passwordRepeatPassword.setText("");
		textName.setText("");
		textSurname.setText("");
		textPesel.setText("");
		//dateBirthday.setEmpty();
		textCity.setText("");
		textStreet.setText("");
		textHomeNumber.setText("");
		textPostCode.setText("");
		//textPhoneNumber.setText("");
		//dateBaptism = new JDateChooser(new JCalendar(new Date()));
		dateSecularity.setEmpty();
		dateBeginWork.setEmpty();
		comboPosition.setSelectedIndex(0);
		lblGenPas.setText("#####");
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
				if(!this.setUserData(this.user)) return;
				if(passwordPassword.getText().equals(passwordRepeatPassword.getText())){
					if(!this.setPriestData(this.priest)) return;
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
		} else if (z==btnGenPas){
			String s = Pomoc.losujString(5);
			lblGenPas.setText(s);
			passwordPassword.setText(s);
			passwordRepeatPassword.setText(s);
		}
	}
}
