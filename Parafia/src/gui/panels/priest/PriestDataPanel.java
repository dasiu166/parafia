package gui.panels.priest;
import java.awt.Font;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import obsluga.Priest;


public class PriestDataPanel extends JPanel {


	private static final long serialVersionUID = 7694386932112268103L;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblPesel;
	private JLabel lblBirthday;
	private JLabel lblCity;
	private JLabel lblStreet;
	private JLabel lblHomenumber;
	private JLabel lblPostcode;
	private JLabel lblSecularityData;
	private JLabel lblArrivalData;	
	private JLabel lblPosition;	
	/**
	 * Create the panel.
	 */
	public PriestDataPanel(JFrame owner) {
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
			lblBirthday_.setVisible(false);
			lblBirthday_.setEnabled(false);
			lblBirthday_.setHorizontalAlignment(SwingConstants.RIGHT);		
				lblName = new JLabel("NULL_Name");
				lblSurname = new JLabel("NULL_Surname");
				lblPesel = new JLabel("NULL_pesel");
				lblBirthday = new JLabel("NULL_Birthday");
				lblBirthday.setVisible(false);
				lblBirthday.setEnabled(false);
		
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
		
		JLabel lblOthers = new JLabel("Inne");
		lblOthers.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthers.setFont(new Font("Tahoma", Font.BOLD, 14));		
			JLabel lblSecularityData_ = new JLabel("Data \u015Awi\u0119ce\u0144:");
			lblSecularityData_.setHorizontalAlignment(SwingConstants.RIGHT);
			JLabel lblPosition_ = new JLabel("Stanowisko:");
			lblPosition_.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSecularityData = new JLabel("NULL_SecularityData");
				lblArrivalData = new JLabel("NULL_Arrival Data");
				lblPosition = new JLabel("NULL_Confirmation Data");
		
		JLabel lblArrivalData_ = new JLabel("Data przybycia do parafi:");
		lblArrivalData_.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdres, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(lblDane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblName_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblSurname_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSurname, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblPesel_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPesel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblBirthday_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBirthday, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCity_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblStreet_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStreet, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblHomeNumber_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHomenumber, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPostCode_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPostcode, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
								.addComponent(lblOthers, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblSecularityData_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSecularityData, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblArrivalData_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblArrivalData, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblPosition_, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPosition, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))))
					.addGap(59))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
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
						.addComponent(lblBirthday))
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
					.addGap(31)
					.addComponent(lblOthers, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSecularityData_)
						.addComponent(lblSecularityData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArrivalData_)
						.addComponent(lblArrivalData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPosition)
						.addComponent(lblPosition_))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@SuppressWarnings("deprecation")
	public void setPriestData(Priest priest){
		lblName.setText(priest.getName());
		lblSurname.setText(priest.getSurName());
		lblPesel.setText(priest.getPesel());
		if(priest.getAdress() != null){
			lblCity.setText(priest.getAdress().getCity());
			lblStreet.setText(priest.getAdress().getStreet());
			lblHomenumber.setText(priest.getAdress().getHouse());
			lblPostcode.setText(priest.getAdress().getPostcode());
			//lblPhone.setText(priest.getAdress().getPhone());
		} else {
			JOptionPane.showMessageDialog(null, "priest.getAdress() == null", "NullException", JOptionPane.ERROR_MESSAGE);
		}
		
		Date data = priest.getSecularityDate();
		if(data != null)
			lblSecularityData.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
		else
			lblSecularityData.setText("brak_SecularityData");
		data = priest.getArrivalDate();
		if(data != null)
			lblArrivalData.setText(data.getDate()+"."+(data.getMonth()+1)+"."+(data.getYear()+1900));
		else
			lblArrivalData.setText("brak_lblArrivalData");
		
		lblPosition.setText(priest.getPosition());
		
	}
}
