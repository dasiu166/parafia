package gui;
import java.awt.Font;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import obsluga.Parishioner;


public class PanelUserData extends JPanel {


	private static final long serialVersionUID = 7694386932112268103L;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblPesel;
	private JLabel lblBirthday;
	private JLabel lblCity;
	private JLabel lblStreet;
	private JLabel lblHomenumber;
	private JLabel lblPostcode;
	private JLabel lblBaptism;
	private JLabel lblCommunion;	
	private JLabel lblConfirmation;	
	private JLabel lblMarriage;
	/**
	 * Create the panel.
	 */
	public PanelUserData(JFrame owner) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblDane = new JLabel("Dane");
		lblDane.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDane.setHorizontalAlignment(SwingConstants.CENTER);		
			JLabel lblName_ = new JLabel("Imie:");
			lblName_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblSurname_ = new JLabel("Nazwisko:");
			lblSurname_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblPesel_ = new JLabel("pesel:");
			lblPesel_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblBirthday_ = new JLabel("Data urodzenia:");
			lblBirthday_.setHorizontalAlignment(SwingConstants.RIGHT);		
				lblName = new JLabel("NULL_Name");		
				lblSurname = new JLabel("NULL_Surname");		
				lblPesel = new JLabel("NULL_pesel");		
				lblBirthday = new JLabel("NULL_Birthday");
		
		JLabel lblAdres = new JLabel("Adres");
		lblAdres.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdres.setFont(new Font("Tahoma", Font.BOLD, 14));		
			JLabel lblCity_ = new JLabel("Miasto:");
			lblCity_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblStreet_ = new JLabel("Ulica:");
			lblStreet_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblHomeNumber_ = new JLabel("Nr domu/mieszkania:");
			lblHomeNumber_.setHorizontalAlignment(SwingConstants.RIGHT);		
			JLabel lblPostCode_ = new JLabel("Kod pocztowy:");
			lblPostCode_.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCity = new JLabel("NULL_City");		
				lblStreet = new JLabel("NULL_Street");		
				lblHomenumber = new JLabel("NULL_HomeNumber");		
				lblPostcode = new JLabel("NULL_PostCode");
		
		JLabel lblCourses = new JLabel("\u015Awi\u0119cenia");
		lblCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 14));		
			JLabel lblBaptism_ = new JLabel("Chrzest:");
			lblBaptism_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblCommunion_ = new JLabel("Komunia \u015Bwi\u0119ta:");
			lblCommunion_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblConfirmation_ = new JLabel("Bierzmowanie:");
			lblConfirmation_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblMarriage_ = new JLabel("\u015Alub:");
			lblMarriage_.setHorizontalAlignment(SwingConstants.RIGHT);			
				lblBaptism = new JLabel("NULL_Baptism Data");		
				lblCommunion = new JLabel("NULL_Communion Data");		
				lblConfirmation = new JLabel("NULL_Confirmation Data");		
				lblMarriage = new JLabel("NULL_Marriage Data");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(109)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDane, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSurname_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblSurname, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPesel_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblPesel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblBirthday, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addComponent(lblAdres, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCity_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStreet_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblStreet, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblHomeNumber_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblHomenumber, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPostCode_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblPostcode, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addComponent(lblCourses, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBaptism_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblBaptism, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCommunion_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblCommunion, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblConfirmation_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblConfirmation, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMarriage_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblMarriage, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
					.addGap(105))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblDane, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName_)
						.addComponent(lblName))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSurname_)
						.addComponent(lblSurname))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPesel_)
						.addComponent(lblPesel))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBirthday_)
						.addComponent(lblBirthday))
					.addGap(11)
					.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCity_)
						.addComponent(lblCity))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStreet_)
						.addComponent(lblStreet))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHomeNumber_)
						.addComponent(lblHomenumber))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPostCode_)
						.addComponent(lblPostcode))
					.addGap(11)
					.addComponent(lblCourses, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBaptism_)
						.addComponent(lblBaptism))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCommunion_)
						.addComponent(lblCommunion))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConfirmation_)
						.addComponent(lblConfirmation))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMarriage_)
						.addComponent(lblMarriage))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@SuppressWarnings("deprecation")
	public void setUserData(Parishioner parishioner){
		lblName.setText(parishioner.getName());
		lblSurname.setText(parishioner.getSurName());
		lblPesel.setText(parishioner.getPesel());
		if(parishioner.getAdress() != null){
			lblCity.setText(parishioner.getAdress().getCity());
			lblStreet.setText(parishioner.getAdress().getStreet());
			lblHomenumber.setText(parishioner.getAdress().getHouse());
			//lblPostcode.setText(parishioner.getAdress().getPostCode());
		} else {
			JOptionPane.showMessageDialog(null, "parishioner.getAdress() == null", "NullException", JOptionPane.ERROR_MESSAGE);
		}
		if(parishioner.getCourse() != null){
			Date data = parishioner.getCourse().getBirthDay();
			if(data != null)
				lblBirthday.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
			else
				lblBirthday.setText("brak");
			data = parishioner.getCourse().getBaptism();
			if(data != null)
				lblBaptism.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
			else
				lblBaptism.setText("brak");
			data = parishioner.getCourse().getCommunion();
			if(data != null)
				lblCommunion.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
			else
				lblCommunion.setText("brak");
			data = parishioner.getCourse().getConfirmation();
			if(data != null)
				lblConfirmation.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
			else
				lblConfirmation.setText("brak");
			data = parishioner.getCourse().getMarriage();
			if(data != null)
				lblMarriage.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
			else
				lblMarriage.setText("brak");
		}else{
			JOptionPane.showMessageDialog(null, "parishioner.getCourse() == null", "NullException", JOptionPane.ERROR_MESSAGE);
		}
	}


}
