package gui.panels.priest;
import gui.calendar.JDateChooser;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import obsluga.Priest;


public class AddNewPriestPanel extends JPanel {


	private static final long serialVersionUID = 7694386932112268103L;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPesel;
	private JTextField textCity;
	private JTextField textStreet;
	private JTextField textHomeNumber;
	private JTextField textPostCode;
	private JTextField textPhone;
	
	JDateChooser dateSecularityData;	
	JDateChooser dateArrivalData;
	
	JComboBox<String> comboPosition;
	/**
	 * Create the panel.
	 */
	public AddNewPriestPanel(JFrame owner) {
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
			lblBirthday_.setEnabled(false);
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
		
		JLabel lblOthers = new JLabel("Inne");
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthers.setFont(new Font("Tahoma", Font.BOLD, 14));		
			JLabel lblSecularityData_ = new JLabel("Data \u015Awi\u0119ce\u0144:");
			lblSecularityData_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblPosition_ = new JLabel("Stanowisko:");
			lblPosition_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblPhone_ = new JLabel("Nr. Telefonu:");
			lblPhone_.setEnabled(false);
			lblPhone_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblArrivalData_ = new JLabel("Data przybycia do parafi:");
		lblArrivalData_.setHorizontalAlignment(SwingConstants.RIGHT);
		
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
		
		textPhone = new JTextField();
		textPhone.setEnabled(false);
		textPhone.setColumns(10);
		
		JDateChooser dateBirthday = new JDateChooser();
		dateBirthday.getSpinner().setEnabled(false);
		dateBirthday.setEnabled(false);
		
		dateSecularityData = new JDateChooser();		
		dateArrivalData = new JDateChooser();
		
		comboPosition = new JComboBox<String>();
		
		comboPosition.setModel(new DefaultComboBoxModel<String>(new String[] {"<wybierz Stanowisko>", "Ksi\u0105dz", "Proboszcz", "Sektertaka"}));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAdres, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(lblDane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblName_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblSurname_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCity_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblStreet_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textStreet, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHomeNumber_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textHomeNumber, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPostCode_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPostCode, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPhone_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblOthers, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSecularityData_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateSecularityData, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPosition_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
										.addComponent(lblArrivalData_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboPosition, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(dateArrivalData, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateBirthday, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPesel_, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))))
					.addGap(58))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone_)
						.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOthers, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblSecularityData_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateSecularityData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblArrivalData_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateArrivalData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosition_)
						.addComponent(comboPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public Priest getNewPriestData(){
		Priest priest = new Priest();
		priest.setName(textName.getText());
		priest.setSurName(textSurname.getText());
		priest.setPesel(textPesel.getText());		
		priest.getAdress().setCity(textCity.getText());
		priest.getAdress().setStreet(textStreet.getText());
		priest.getAdress().setHouseNumb(textHomeNumber.getText());
		priest.getAdress().setPostcode(textPostCode.getText());
		//priest.getAdress().setPhone(textPhone.getText());
		priest.setSecularityDate(dateSecularityData.getDate());
		priest.setArrivalDate(dateArrivalData.getDate());
		priest.setPossition(comboPosition.getToolTipText());
		return priest;
	}
}
