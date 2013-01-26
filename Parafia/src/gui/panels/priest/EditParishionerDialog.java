package gui.panels.priest;

import gui.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import obsluga.Adress;
import obsluga.Course;
import obsluga.Parishioner;

public class EditParishionerDialog extends JDialog implements ActionListener{

	private final JScrollPane scrollContentPanel = new JScrollPane();
	private Parishioner parishioner;
	private JTextField textLogin;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPesel;
	private JTextField textCity;
	private JTextField textStreet;
	private JTextField textHomeNr;
	private JTextField textPostCode;
	private JDateChooser dateBirthday;
	private JDateChooser dateBaptism;
	private JDateChooser dateCommunion;
	private JDateChooser dateConfirmation;
	private JDateChooser dateMarriage;
	private JDateChooser dateDeath;
	private boolean isOk = false;
	private boolean isCansel = false;
	private JButton btnReset;
	private JButton btnOk;
	private JButton btnCansel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditParishionerDialog dialog = new EditParishionerDialog(null, new Parishioner());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditParishionerDialog(JFrame owner, Parishioner parishioner){
		this(owner);
		setParishionerData(parishioner);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public EditParishionerDialog(JFrame owner) {
		super(owner, "Edycja Parafianina", true);
		setResizable(false);
		setBounds(100, 100, 437, 577);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		scrollContentPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollContentPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(scrollContentPanel, BorderLayout.CENTER);
		{
			JPanel panelContent = new JPanel();
			scrollContentPanel.setViewportView(panelContent);
			
			JLabel lblLogin_ = new JLabel("Login:");
			lblLogin_.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textLogin = new JTextField();
			textLogin.setColumns(10);
			
			JLabel label_Data_ = new JLabel("Dane");
			label_Data_.setHorizontalAlignment(SwingConstants.CENTER);
			label_Data_.setForeground(new Color(0, 0, 204));
			label_Data_.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			
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
			
			JLabel label_Address_ = new JLabel("Adres");
			label_Address_.setHorizontalAlignment(SwingConstants.CENTER);
			label_Address_.setForeground(new Color(0, 0, 204));
			label_Address_.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			
			JLabel lblCity_ = new JLabel("Miasto:");
			lblCity_.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textCity = new JTextField();
			textCity.setColumns(10);
			
			JLabel lblStreet = new JLabel("Ulica:");
			lblStreet.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textStreet = new JTextField();
			textStreet.setColumns(10);
			
			JLabel lblHomeNr_ = new JLabel("Nr domu/mieszkania:");
			lblHomeNr_.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textHomeNr = new JTextField();
			textHomeNr.setColumns(10);
			
			JLabel lblPostCode_ = new JLabel("Kod pocztowy:");
			lblPostCode_.setHorizontalAlignment(SwingConstants.RIGHT);
			
			textPostCode = new JTextField();
			textPostCode.setColumns(10);
			
			JLabel label_Holy_Orders_ = new JLabel("\u015Awi\u0119cenia");
			label_Holy_Orders_.setHorizontalAlignment(SwingConstants.CENTER);
			label_Holy_Orders_.setForeground(new Color(0, 0, 204));
			label_Holy_Orders_.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			
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
			
			JLabel lblDeath_ = new JLabel("\u015Amier\u0107:");
			lblDeath_.setHorizontalAlignment(SwingConstants.RIGHT);
			
			dateDeath = new JDateChooser(true);
			
			JLabel label_login_data_ = new JLabel("Dane Logowania");
			label_login_data_.setVerticalAlignment(SwingConstants.BOTTOM);
			label_login_data_.setHorizontalAlignment(SwingConstants.CENTER);
			label_login_data_.setForeground(new Color(0, 0, 204));
			label_login_data_.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			
			
			GroupLayout gl_panelContent = new GroupLayout(panelContent);
			gl_panelContent.setHorizontalGroup(
				gl_panelContent.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelContent.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label_login_data_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblLogin_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblName_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblSurname_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblPesel_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addComponent(label_Address_, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblCity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblHomeNr_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textHomeNr, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblPostCode_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addComponent(label_Holy_Orders_, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(1)
								.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(1)
								.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(1)
								.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addComponent(label_Data_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(44, Short.MAX_VALUE))
			);
			gl_panelContent.setVerticalGroup(
				gl_panelContent.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelContent.createSequentialGroup()
						.addContainerGap()
						.addComponent(label_login_data_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(8)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblLogin_))
							.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_Data_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblName_))
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblSurname_))
							.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblPesel_))
							.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addComponent(label_Address_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblCity_))
							.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblStreet))
							.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblHomeNr_))
							.addComponent(textHomeNr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGap(3)
								.addComponent(lblPostCode_))
							.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_Holy_Orders_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(15, Short.MAX_VALUE))
			);
			panelContent.setLayout(gl_panelContent);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnReset = new JButton("Reset");
				btnReset.setIcon(new ImageIcon(AddNewParishionerPanel.class.getResource("/icons/reset.png")));
				btnReset.addActionListener(this);
				buttonPane.add(btnReset);
			}
			{
				btnOk = new JButton("OK");
				btnOk.setIcon(new ImageIcon(OrderDialog.class.getResource("/icons/Accept-icon.png")));
				btnOk.addActionListener(this);
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCansel = new JButton("Cancel");
				btnCansel.setIcon(new ImageIcon(OrderDialog.class.getResource("/icons/anuluj-icon.png")));
				btnCansel.addActionListener(this);
				btnCansel.setActionCommand("Cancel");
				buttonPane.add(btnCansel);
			}
		}
	}
	
	public void setParishionerData(Parishioner parishioner){
		this.parishioner = parishioner;
		resetParishionerData();
	}
	
	private void resetParishionerData(){
		clearParishionerPanel();
		textLogin.setText("brak danych");
		textName.setText(parishioner.getName());
		textSurname.setText(parishioner.getSurName());
		textPesel.setText(parishioner.getPesel());
		if(parishioner.getAdress() != null){
			textCity.setText(parishioner.getAdress().getCity());
			textStreet.setText(parishioner.getAdress().getStreet());
			textHomeNr.setText(parishioner.getAdress().getHouse());
			textPostCode.setText(parishioner.getAdress().getPostcode());
		}
		if(parishioner.getCourse() != null){
			Date date = parishioner.getCourse().getBirthDay();
			if(date != null) dateBirthday.setDate(date);		
			date = parishioner.getCourse().getBaptism();
			if(date != null) dateBaptism.setDate(date);		
			date = parishioner.getCourse().getCommunion();
			if(date != null) dateCommunion.setDate(date);		
			date = parishioner.getCourse().getConfirmation();
			if(date != null) dateConfirmation.setDate(date);		
			date = parishioner.getCourse().getMarriage();
			if(date != null) dateMarriage.setDate(date);		
			date = parishioner.getCourse().getDeath();
			if(date != null) dateDeath.setDate(date);
		}
	}
	
	private void clearParishionerPanel(){
		textLogin.setText("");
		textName.setText("");
		textSurname.setText("");
		textPesel.setText("");
		textCity.setText("");
		textStreet.setText("");
		textHomeNr.setText("");
		textPostCode.setText("");
		dateBirthday.setEmpty();
		dateBaptism.setEmpty();
		dateCommunion.setEmpty();
		dateConfirmation.setEmpty();
		dateMarriage.setEmpty();
		dateDeath.setEmpty();
	}
	
	public Parishioner getParishionerEdited(){
		parishioner.setName(textName.getText());
		parishioner.setSurName(textSurname.getText());
		parishioner.setPesel(textPesel.getText());
		if(parishioner.getAdress() == null) parishioner.setAdress(new Adress());
		parishioner.getAdress().setCity(textCity.getText());
		parishioner.getAdress().setStreet(textStreet.getText());
		parishioner.getAdress().setHouseNumb(textHomeNr.getText());
		parishioner.getAdress().setPostcode(textPostCode.getText());
		if(parishioner.getCourse() == null) parishioner.setCourse(new Course());
		parishioner.getCourse().setBirthday((!dateBirthday.isEmpty())?dateBirthday.getDate():null);
		parishioner.getCourse().setCommunion((!dateCommunion.isEmpty())?dateCommunion.getDate():null);
		parishioner.getCourse().setConfirmation((!dateConfirmation.isEmpty())?dateConfirmation.getDate():null);
		parishioner.getCourse().setMarriage((!dateMarriage.isEmpty())?dateMarriage.getDate():null);
		parishioner.getCourse().setDeath((!dateDeath.isEmpty())?dateDeath.getDate():null);
		return parishioner;
	}
	
	public boolean isOk(){
		return isOk;
	}
	public boolean isCansel(){
		return isCansel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object z = arg0.getSource();
		isOk = false;
		isCansel = false;
		
		if(z == btnOk){
			isOk = true;
			setVisible(false);
		}else if(z == btnCansel){
			isCansel = true;
			setVisible(false);
		}else if(z == btnReset){
			resetParishionerData();
		}
		
	}
}
