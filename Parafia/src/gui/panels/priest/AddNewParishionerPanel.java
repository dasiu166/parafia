package gui.panels.priest;

import gui.calendar.JDateChooser;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import obsluga.Parishioner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddNewParishionerPanel extends JPanel {


	private static final long serialVersionUID = 7694386932112268103L;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPesel;
	private JTextField textCity;
	private JTextField textStreet;
	private JTextField textHomeNumber;
	private JTextField textPostCode;
	
	JDateChooser dateBirthday;	
	JDateChooser dateBaptism;	
	JDateChooser dateCommunion;	
	JDateChooser dateConfirmation;
	/**
	 * Create the panel.
	 */
	public AddNewParishionerPanel(JFrame owner) {
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
		
		JLabel lblCourses = new JLabel("Przebieg");
		lblCourses.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 14));		
			JLabel lblBaptism_ = new JLabel("Chrzest:");
			lblBaptism_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblConfirmation_ = new JLabel("Bierzmowanie:");
			lblConfirmation_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblMarriage_ = new JLabel("\u015Alub:");
			lblMarriage_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblCommunion_ = new JLabel("Komunia \u015Awi\u0119ta:");
		lblCommunion_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textName = new JTextField();
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setColumns(10);
		
		textPesel = new JTextField();
		textPesel.setColumns(10);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		
		textStreet = new JTextField();
		textStreet.setColumns(10);
		
		textHomeNumber = new JTextField();
		textHomeNumber.setColumns(10);
		
		textPostCode = new JTextField();
		textPostCode.setColumns(10);
		
		dateBirthday = new JDateChooser();
		
		dateBaptism = new JDateChooser();
		
		dateCommunion = new JDateChooser();
		
		dateConfirmation = new JDateChooser();
		
		JDateChooser dateMarriage = new JDateChooser();
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JDateChooser dateDeath = new JDateChooser();
		
		JLabel lblmier = new JLabel("\u015Amier\u0107:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(104)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdres, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
						.addComponent(lblDane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblName_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textName, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblSurname_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textSurname, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCity_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textCity, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblStreet_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textStreet, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHomeNumber_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textHomeNumber, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBaptism_, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateBaptism, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCommunion_, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateCommunion, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblConfirmation_, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateConfirmation, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateBirthday, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPesel_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPesel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblCourses, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPostCode_, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPostCode, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblMarriage_, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
								.addComponent(lblmier))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(dateMarriage, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
									.addGap(1)))))
					.addGap(57))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(139)
					.addComponent(btnDodaj)
					.addContainerGap(244, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblDane, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName_)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSurname_)
						.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesel_)
						.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity_)
						.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStreet_)
						.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHomeNumber_)
						.addComponent(textHomeNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostCode_)
						.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCourses, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblBaptism_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateBaptism, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblCommunion_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateCommunion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblConfirmation_, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateConfirmation, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMarriage_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateMarriage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateDeath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblmier))
					.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
					.addComponent(btnDodaj)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	public Parishioner getNewParishionerData(){
		Parishioner parishioner = new Parishioner();
		parishioner.setName(textName.getText());
		parishioner.setSurName(textSurname.getText());
		parishioner.setPesel(textPesel.getText());
		parishioner.getCourse().setBirthday(dateBirthday.getDate());
		parishioner.getAdress().setCity(textCity.getText());
		parishioner.getAdress().setStreet(textStreet.getText());
		parishioner.getAdress().setHouseNumb(textHomeNumber.getText());
		parishioner.getAdress().setPostcode(textPostCode.getText());
		parishioner.getCourse().setBaptism(dateBaptism.getDate());
		parishioner.getCourse().setCommunion(dateCommunion.getDate());
		parishioner.getCourse().setConfirmation(dateConfirmation.getDate());
		return parishioner;
	}
}
