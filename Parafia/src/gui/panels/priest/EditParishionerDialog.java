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
	private boolean isOk, isDane, isAdres, isDaty = false;
	private boolean isCansel = false;
	private JButton btnReset;
	private JButton btnCansel;
	private JButton btnDane;
	private JButton btnAdres;
	private JButton btnDaty;

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
		setBounds(100, 100, 520, 678);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		scrollContentPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollContentPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(scrollContentPanel, BorderLayout.CENTER);
		{
			JPanel panelContent = new JPanel();
			scrollContentPanel.setViewportView(panelContent);
			
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
			
			JLabel lblDaty = new JLabel("Daty");
			lblDaty.setHorizontalAlignment(SwingConstants.CENTER);
			lblDaty.setForeground(new Color(0, 0, 204));
			lblDaty.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
			
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
			
			btnDane = new JButton("Aktualizuj");
			btnDane.addActionListener(this);
			
			btnAdres = new JButton("Aktualizuj");
			btnAdres.addActionListener(this);
			
			btnDaty = new JButton("Aktualizuj");
			btnDaty.addActionListener(this);
			
			GroupLayout gl_panelContent = new GroupLayout(panelContent);
			gl_panelContent.setHorizontalGroup(
				gl_panelContent.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelContent.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(btnDane)
								.addGap(18)
								.addComponent(label_Data_, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblName_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblSurname_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblPesel_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(btnAdres)
								.addGap(18)
								.addComponent(label_Address_, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblCity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblHomeNr_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textHomeNr, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lblPostCode_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(btnDaty)
								.addGap(18)
								.addComponent(lblDaty, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(318, Short.MAX_VALUE))
			);
			gl_panelContent.setVerticalGroup(
				gl_panelContent.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelContent.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDane)
							.addComponent(label_Data_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblName_)
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSurname_)
							.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblPesel_)
							.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAdres)
							.addComponent(label_Address_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCity_)
							.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblStreet)
							.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHomeNr_)
							.addComponent(textHomeNr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPostCode_)
							.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDaty)
							.addComponent(lblDaty, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblBirthday_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblBaptism_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblCommunion_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblConfirmation_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateConfirmation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMarriage_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDeath_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(49, Short.MAX_VALUE))
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
				btnCansel = new JButton("Cancel");
				btnCansel.setIcon(new ImageIcon(EditParishionerDialog.class.getResource("/icons/cancel-icon.png")));
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
		boolean tryCatch=true;
		
		if(textName.getText().length()<3){
			JOptionPane.showMessageDialog(null, "Za krótkie imie (min 3 znaki)");
			parishioner.setData("ERR");
			return parishioner;
		} else parishioner.setName(textName.getText());
		
		
		if(textSurname.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Za krótkie nazwisko (min 2 znaki)");
			parishioner.setData("ERR");
			return parishioner;
		}else parishioner.setSurName(textSurname.getText());
		
		
		if((textPesel.getText().length()<11)||(textPesel.getText().length()>11)){
		    JOptionPane.showMessageDialog(null, "Pesel musi mieæ 11 liczb");
		    parishioner.setData("ERR");
		    return parishioner;
		}else 
		if(textPesel.getText().contains(".")){
			JOptionPane.showMessageDialog(null, "Pesel mo¿e zawieraæ tylko liczby");
			parishioner.setData("ERR");
			return parishioner;
		} else {
			try{
				Double.parseDouble(textPesel.getText());
				
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Pesel mo¿e zawieraæ tylko liczby");
			    //return false;
				tryCatch=false;
				parishioner.setData("ERR");
				return parishioner;
			}
		}
		
		if(!tryCatch) {
		parishioner.setData("ERR"); 
		return parishioner;
		}else parishioner.setPesel(textPesel.getText());
		
		
		
		
		if(parishioner.getAdress() == null) parishioner.setAdress(new Adress());
		
		if(textCity.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Nazwa miasta jest za krótka (min 2 znaki)");
			parishioner.setData("ERR"); 
			return parishioner;
		} else parishioner.getAdress().setCity(textCity.getText());
		
		
		if(textStreet.getText().length()<2){
			JOptionPane.showMessageDialog(null, "Nazwa ulicy jest za krótka (min 2 znaki)");
			parishioner.setData("ERR"); 
			return parishioner;
		}else parishioner.getAdress().setStreet(textStreet.getText());
		
		
		parishioner.getAdress().setHouseNumb(textHomeNr.getText());
		
		if(textPostCode.getText().length()<6){
			JOptionPane.showMessageDialog(null, "Kod pocztowy jest za krótki (min 6 znaki)");
			parishioner.setData("ERR"); 
		}else parishioner.getAdress().setPostcode(textPostCode.getText());
		
		if(parishioner.getCourse() == null) parishioner.setCourse(new Course());
		int i=0;
		
		if(dateBirthday.isEmpty()){
			JOptionPane.showMessageDialog(null, "Podaj datê urodzenia");
			parishioner.setData("ERR");
			return parishioner;
		} else  i = dateBirthday.getDate().compareTo(new Date()); 
		if(i>0){
			JOptionPane.showMessageDialog(null, "Data urodzenie jest b³edna \n" +
					"Taki dzien jeszcze nie nast¹pi³");
			parishioner.setData("ERR");
			return parishioner;
		} else parishioner.getCourse().setBirthday((!dateBirthday.isEmpty())?dateBirthday.getDate():null);
		
		
		
		
		
			if(dateBaptism.getDate().compareTo(dateBirthday.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data chrztu jest przed dat¹ urodzenia");
				parishioner.setData("ERR");
				return parishioner;
			} else parishioner.getCourse().setBaptism((!dateBaptism.isEmpty())?dateBaptism.getDate():null);
		
		
		
		
			if(dateCommunion.getDate().compareTo(dateBaptism.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data komunii œw. jest przed dat¹ chrztu");
				parishioner.setData("ERR");
				return parishioner;
			} else parishioner.getCourse().setCommunion((!dateCommunion.isEmpty())?dateCommunion.getDate():null);
		
		
			if(dateConfirmation.getDate().compareTo(dateCommunion.getDate())<0){
				JOptionPane.showMessageDialog(null, "Data bierzmowania jest przed dat¹ komunii œw.");
				parishioner.setData("ERR");
				return parishioner;
			} else parishioner.getCourse().setConfirmation((!dateConfirmation.isEmpty())?dateConfirmation.getDate():null);
		
		
				if(dateMarriage.getDate().compareTo(dateConfirmation.getDate())<0){
					JOptionPane.showMessageDialog(null, "Data œlubu jest przed dat¹ bierzmowania");
					parishioner.setData("ERR");
					return parishioner;
				} else parishioner.getCourse().setMarriage((!dateMarriage.isEmpty())?dateMarriage.getDate():null);
		
					if(dateDeath.getDate().compareTo(dateBirthday.getDate())<0){
						JOptionPane.showMessageDialog(null, "Data œmierci jest przed dat¹ urodzenia");
						parishioner.setData("ERR");
						return parishioner;
			   } else parishioner.getCourse().setDeath((!dateDeath.isEmpty())?dateDeath.getDate():null);
		
		
		
		parishioner.setData("");
		return parishioner;
	}
	
	public boolean isOk(){
		return isOk;
	}
	public boolean isDane(){
		return isDane;
	}
	public boolean isAdres(){
		return isAdres;
	}
	public boolean isDaty(){
		return isDaty;
	}
	public boolean isCansel(){
		return isCansel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object z = arg0.getSource();
		isOk = false;
		isCansel = false;
		isDane=false;
		isDaty=false;
		isAdres=false;
		
		if(z == btnCansel){
			isCansel = true;
			setVisible(false);
		}else if(z == btnReset){
			resetParishionerData();
		}else if(z == btnDane){
			if(this.getParishionerEdited().getData().equals("ERR")) return;
			isDane = true;
			setVisible(false);
		}else if(z == btnAdres){
			if(this.getParishionerEdited().getData().equals("ERR")) return;
			isAdres = true;
			setVisible(false);
		}else if(z == btnDaty){
			if(this.getParishionerEdited().getData().equals("ERR")) return;
			isDaty = true;
			setVisible(false);
		}
		
	}
}
