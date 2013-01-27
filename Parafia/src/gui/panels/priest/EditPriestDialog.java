package gui.panels.priest;

import gui.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import obsluga.Adress;
import obsluga.Priest;

public class EditPriestDialog extends JDialog implements ActionListener{

	private final JScrollPane scrollContentPanel = new JScrollPane();
	private Priest priest;
	private boolean isOk = false;
	private boolean isCansel = false;
	private JButton btnReset;
	private JButton btnOk;
	private JButton btnCansel;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPesel;
	private JTextField textCity;
	private JTextField textStreet;
	private JTextField textHomeNr;
	private JTextField textPostCode;
	private JDateChooser dateSecularity;
	private JDateChooser dateBeginWork;
	private JComboBox comboPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditPriestDialog dialog = new EditPriestDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public EditPriestDialog(JFrame owner, Priest priest){
		this(owner);
		setPriestData(priest);
	}
	public EditPriestDialog(JFrame owner) {
		super(owner, "Edycja Ksiêdza", true);
		setResizable(false);
		setBounds(100, 100, 494, 576);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		scrollContentPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollContentPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(scrollContentPanel, BorderLayout.CENTER);
		
		JPanel panelContent = new JPanel();
		scrollContentPanel.setViewportView(panelContent);
		
		JLabel label_Data_ = new JLabel("Dane");
		label_Data_.setHorizontalAlignment(SwingConstants.CENTER);
		label_Data_.setForeground(new Color(128, 0, 128));
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
		
		JLabel label_Address = new JLabel("Adres");
		label_Address.setHorizontalAlignment(SwingConstants.CENTER);
		label_Address.setForeground(new Color(128, 0, 128));
		label_Address.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblCity_ = new JLabel("Miasto:");
		lblCity_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		
		JLabel lblStreet_ = new JLabel("Ulica:");
		lblStreet_.setHorizontalAlignment(SwingConstants.RIGHT);
		
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
		
		JLabel lblSecularity_ = new JLabel("Data \u015Awi\u0119ce\u0144:");
		lblSecularity_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dateSecularity = new JDateChooser(true);
		
		JLabel label_Other_ = new JLabel("Inne");
		label_Other_.setHorizontalAlignment(SwingConstants.CENTER);
		label_Other_.setForeground(new Color(128, 0, 128));
		label_Other_.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblPosition_ = new JLabel("Stanowisko:");
		lblPosition_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBeginWork_ = new JLabel("Data Przybycia:");
		lblBeginWork_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboPosition = new JComboBox();
		comboPosition.setModel(new DefaultComboBoxModel(new String[] {"Wikary", "Proboszcz"}));
		
		dateBeginWork = new JDateChooser(true);
		
		JButton btnNewButton = new JButton("New button");
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JButton btnNewButton_2 = new JButton("New button");
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lblCity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lblStreet_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
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
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lblSecularity_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(dateSecularity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lblSurname_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lblName_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelContent.createSequentialGroup()
									.addGap(14)
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_Data_, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lblPesel_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(19)
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(label_Address, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(23)
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(label_Other_, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBeginWork_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPosition_, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
								.addComponent(comboPosition, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateBeginWork, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_Data_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName_)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSurname_)
						.addComponent(textSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesel_)
						.addComponent(textPesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(label_Address, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity_))
					.addGap(6)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(3)
							.addComponent(lblStreet_))
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
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(label_Other_, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateSecularity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecularity_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateBeginWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBeginWork_, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPosition_))
					.addGap(41))
		);
		panelContent.setLayout(gl_panelContent);
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
	
	public void setPriestData(Priest priest){
		this.priest = priest;
		resetPriestData();
	}
	
	private void resetPriestData(){
		clearPriestPanel();
		textName.setText(priest.getName());
		textSurname.setText(priest.getSurName());
		textPesel.setText(priest.getPesel());
		if(priest.getAdress() != null){
			textCity.setText(priest.getAdress().getCity());
			textStreet.setText(priest.getAdress().getStreet());
			textHomeNr.setText(priest.getAdress().getHouse());
			textPostCode.setText(priest.getAdress().getPostcode());
		}
		Date date = priest.getSecularityDate();
		if(date != null) dateSecularity.setDate(date);		
		date = priest.getArrivalDate();
		if(date != null) dateBeginWork.setDate(date);
		int i = -1;
		if(priest.getPosition().compareTo("Wikary") == 0) i=0;
		else if(priest.getPosition().compareTo("Proboszcz") == 0) i=1;
		comboPosition.setSelectedIndex(i);
	}
	
	private void clearPriestPanel(){
		textName.setText("");
		textSurname.setText("");
		textPesel.setText("");
		textCity.setText("");
		textStreet.setText("");
		textHomeNr.setText("");
		textPostCode.setText("");
		dateSecularity.setEmpty();
		dateBeginWork.setEmpty();
		comboPosition.setSelectedIndex(-1);
	}
	
	public Priest getPriestEdited(){
		priest.setName(textName.getText());
		priest.setSurName(textSurname.getText());
		priest.setPesel(textPesel.getText());
		if(priest.getAdress() == null) priest.setAdress(new Adress());
		priest.getAdress().setCity(textCity.getText());
		priest.getAdress().setStreet(textStreet.getText());
		priest.getAdress().setHouseNumb(textHomeNr.getText());
		priest.getAdress().setPostcode(textPostCode.getText());
		priest.setSecularityDate((!dateSecularity.isEmpty())?dateSecularity.getDate():null);
		priest.setArrivalDate((!dateBeginWork.isEmpty())?dateBeginWork.getDate():null);
		priest.setPossition((String)comboPosition.getSelectedItem());
		return priest;
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
			resetPriestData();
		}
		
	}
}
