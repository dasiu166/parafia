package gui.panels.parishioner;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.MatteBorder;


public class ParishionerDataPanel extends JPanel {


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
	public ParishionerDataPanel(JFrame owner) {
		setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(100, 149, 237)));
		
		JLabel lblDane = new JLabel("Dane osobowe");
		lblDane.setForeground(new Color(30, 144, 255));
		lblDane.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
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
		lblAdres.setForeground(new Color(30, 144, 255));
		lblAdres.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdres.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));		
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
		
		JLabel lblCourses = new JLabel("Sakramenty");
		lblCourses.setForeground(new Color(30, 144, 255));
		lblCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourses.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));		
			JLabel lblBaptism_ = new JLabel("Chrzest:");
			lblBaptism_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblConfirmation_ = new JLabel("Bierzmowanie:");
			lblConfirmation_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblMarriage_ = new JLabel("\u015Alub:");
			lblMarriage_.setHorizontalAlignment(SwingConstants.RIGHT);			
				lblBaptism = new JLabel("NULL_Baptism Data");
				lblCommunion = new JLabel("NULL_Communion Data");
				lblConfirmation = new JLabel("NULL_Confirmation Data");
				lblMarriage = new JLabel("NULL_Marriage Data");
		
		JLabel lblCommunion_ = new JLabel("Komunia \u015Awi\u0119ta:");
		lblCommunion_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDownline = new JLabel("");
		
		JLabel DaneImage = new JLabel("");
		DaneImage.setIcon(new ImageIcon(ParishionerDataPanel.class.getResource("/icons/User-Files-icon.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(DaneImage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCourses, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
						.addComponent(lblAdres, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
						.addComponent(lblDane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblName_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblSurname_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSurname, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblPesel_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPesel, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBirthday, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCity_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblStreet_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStreet, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHomeNumber_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHomenumber, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblPostCode_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPostcode, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblBaptism_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBaptism, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCommunion_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCommunion, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblConfirmation_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblConfirmation, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDownline, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMarriage_, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMarriage, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))))
					.addGap(64))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(DaneImage)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDane, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName_)
								.addComponent(lblName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSurname)
								.addComponent(lblSurname_))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPesel_)
								.addComponent(lblPesel))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBirthday_)
								.addComponent(lblBirthday))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity_)
						.addComponent(lblCity))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStreet_)
						.addComponent(lblStreet))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHomeNumber_)
						.addComponent(lblHomenumber))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(lblPostCode_))
					.addGap(17)
					.addComponent(lblCourses, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBaptism_)
						.addComponent(lblBaptism))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCommunion_)
						.addComponent(lblCommunion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmation_)
						.addComponent(lblConfirmation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarriage_)
						.addComponent(lblMarriage))
					.addGap(5)
					.addComponent(lblDownline, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
					.addGap(29))
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
			lblPostcode.setText(parishioner.getAdress().getPostcode());
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
