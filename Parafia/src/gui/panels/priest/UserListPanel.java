package gui.panels.priest;


import gui.Events;
import gui.Obserwator;
import gui.Obserwowany;
import gui.panels.LoginDialog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import obsluga.Parishioner;
import obsluga.Person;
import obsluga.Priest;
import stale.KindRestriction;

public class UserListPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	
	//Grzesiek - moje zmiany
	GroupLayout gl_userPanel;
	JPanel panel_1 = new JPanel();
	//
	
	
	private static final long serialVersionUID = 1L;
	private LoginDialog dialogLogowania;
	private JFrame owner;
	private JPanel panel_UsersList;
	private int userNumber = 0;
	private Events events = Events.getInstance();
	private long time = System.currentTimeMillis();
	private ObserwatorStanSortowania obserwatorStanSortowania = new ObserwatorStanSortowania(this);
	private LinkedList<Person> userList;
	private JTextField text_Surname;
	private JTextField text_Name;
	private JTextField text_Pesel;
	private JComboBox combo_Range;
	private JButton btnApply;
	private JButton btnPdf;
	
	
	/**
	 * Create the panel.
	 */
	public UserListPanel(JFrame owner) {
		setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		this.owner = owner;
		
		//#################### Nag��wek (Wyszukiwarka) ########################
		JPanel panel_Headline = new JPanel();
		panel_Headline.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				
		JLabel lbl_Surname_ = new JLabel("Nazwisko:");
		lbl_Surname_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lbl_Name_ = new JLabel("Imie:");
		lbl_Name_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		text_Surname = new JTextField();
		text_Surname.setColumns(10);
		
		text_Name = new JTextField();
		text_Name.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lbl_Pesel_ = new JLabel("Pesel:");
		lbl_Pesel_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lbl_Range_ = new JLabel("Ranga:");
		lbl_Range_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		text_Pesel = new JTextField();
		text_Pesel.setColumns(10);
		
		combo_Range = new JComboBox();
		combo_Range.setModel(new DefaultComboBoxModel(new String[] {"Wszystkie", "Parafianin", "Ksi\u0105dz", "Proboszcz"}));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		btnApply = new JButton("Poka\u017C");
		btnApply.setIcon(new ImageIcon(OrdersListPanel.class.getResource("/icons/get.png")));
		btnApply.addActionListener(this);
		
		btnPdf = new JButton("Generuj PDF");
		btnPdf.setIcon(new ImageIcon(OrdersListPanel.class.getResource("/icons/pdf24png.png")));
		btnPdf.addActionListener(this);
		
		GroupLayout gl_panel_Headline = new GroupLayout(panel_Headline);
		gl_panel_Headline.setHorizontalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Surname_, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Name_, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(text_Name, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(text_Surname, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Pesel_, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Range_, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addComponent(text_Pesel, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(combo_Range, 0, 111, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApply, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPdf, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_panel_Headline.setVerticalGroup(
			gl_panel_Headline.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Headline.createSequentialGroup()
					.addGroup(gl_panel_Headline.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_Surname_)
								.addComponent(text_Surname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
								.addComponent(text_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Name_)))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_Pesel_)
								.addComponent(text_Pesel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel_Headline.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_Range_)
								.addComponent(combo_Range, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(15)
							.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Headline.createSequentialGroup()
							.addGap(15)
							.addComponent(btnPdf, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_Headline.setLayout(gl_panel_Headline);

		
		
		//#################### Scroll Panel ########################		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel_UsersList = new JPanel();
		panel_UsersList.setBorder(null);
		panel_UsersList.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		scrollPane.setViewportView(panel_UsersList);
		panel_UsersList.setLayout(new MigLayout("", "[572.00px:572.00px,grow]", "[]"));		
				
		//#################### Scroll Panel | Panel Header ########################
		JPanel panel_Header_ = new JPanel();
		scrollPane.setColumnHeaderView(panel_Header_);		
		JPanel panelSurname_ = new JPanel();
			panelSurname_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
			panelSurname_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblSurname_ = new JLabel("Nazwisko");
			panelSurname_.add(lblSurname_);			
			final JLabel lblSurname_Icon = new JLabel("");
			panelSurname_.add(lblSurname_Icon);
			final StanSortowania stanSortowaniaSurname = new StanSortowania(lblSurname_Icon, "surname", StanuSortowania.IMG_UP);
			obserwatorStanSortowania.dodajObserwatora(stanSortowaniaSurname);
			panelSurname_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					obserwatorStanSortowania.powiadomObserwatorow(stanSortowaniaSurname);
				}
			});
		JPanel panelName_ = new JPanel();
			panelName_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) new Color(0, 0, 0)));
			panelName_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JLabel lblName_ = new JLabel("Imie");
			panelName_.add(lblName_);		
			JLabel lblName_Icon = new JLabel("");
			panelName_.add(lblName_Icon);
			final StanSortowania stanSortowaniaName = new StanSortowania(lblName_Icon, "name");
			obserwatorStanSortowania.dodajObserwatora(stanSortowaniaName);
			panelName_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					obserwatorStanSortowania.powiadomObserwatorow(stanSortowaniaName);
				}
			});
		
		JPanel panelPesel_ = new JPanel();
			panelPesel_.setBorder(new MatteBorder(2, 2, 2, 0, (Color) Color.DARK_GRAY));
			panelPesel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblPesel_ = new JLabel("PESEL");
			panelPesel_.add(lblPesel_);		
			final JLabel lblPesel_Icon = new JLabel("");
			panelPesel_.add(lblPesel_Icon);
			final StanSortowania stanSortowaniaPesel= new StanSortowania(lblPesel_Icon, "pesel");
			obserwatorStanSortowania.dodajObserwatora(stanSortowaniaPesel);
			panelPesel_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					obserwatorStanSortowania.powiadomObserwatorow(stanSortowaniaPesel);
				}
			});		
		JPanel panelRange_ = new JPanel();
			panelRange_.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(64, 64, 64)));
			panelRange_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
			JLabel lblRange_ = new JLabel("Ranga");
			panelRange_.add(lblRange_);			
			final JLabel lblRange_Icon = new JLabel("");
			panelRange_.add(lblRange_Icon);
			final StanSortowania stanSortowaniaRange = new StanSortowania(lblRange_Icon, "range");
			obserwatorStanSortowania.dodajObserwatora(stanSortowaniaRange);
			panelRange_.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					obserwatorStanSortowania.powiadomObserwatorow(stanSortowaniaRange);
				}
			});
		
		GroupLayout gl_panel_Header_ = new GroupLayout(panel_Header_);
		gl_panel_Header_.setHorizontalGroup(
			gl_panel_Header_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Header_.createSequentialGroup()
					.addGap(7)
					.addComponent(panelSurname_, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelName_, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelPesel_, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelRange_, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel_Header_.setVerticalGroup(
			gl_panel_Header_.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSurname_, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
				.addComponent(panelPesel_, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
				.addComponent(panelRange_, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
				.addComponent(panelName_, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
		);
		panel_Header_.setLayout(gl_panel_Header_);
		
				
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_Headline, GroupLayout.PREFERRED_SIZE, 589, Short.MAX_VALUE)
					.addGap(7))
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_Headline, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
		
		
		Parishioner user = new Parishioner();
		user.setName("Mariusz");
		user.setSurName("Charczuk");
		user.setPesel("90031105210");
		user.setRestriction(KindRestriction.GOD_R);
		addUser(user);
		userList = new LinkedList<Person>();
		userList.add(user);
		Priest ksiadz = new Priest();
		ksiadz.setName("Ala");
		ksiadz.setSurName("Ma");
		ksiadz.setPesel("12345678901");
		ksiadz.setRestriction(KindRestriction.LOGED_R);
		ksiadz.setPossition("Wikary");
		addUser(ksiadz);
		userList.add(ksiadz);
		
		//listUsers();
	}
	
	/**
	 * @param constraint - ograniczenie "[[40px:]40px[,grow]]"
	 * @return void - dodaje domy�lne rozmiary w pionie dla nowego Zam�wienia
	 */
	private synchronized void addRowConstraint(String constraint){
		String rowConstraints = (String)((MigLayout)panel_UsersList.getLayout()).getRowConstraints();
		if(rowConstraints.equals("[]"))
			((MigLayout)panel_UsersList.getLayout()).setRowConstraints( constraint );
		else
			((MigLayout)panel_UsersList.getLayout()).setRowConstraints( rowConstraints+constraint );
	}
	
	private synchronized void resetRowConstraint(){
			((MigLayout)panel_UsersList.getLayout()).setRowConstraints("[]");
	}
	
	public void addUser(final Person user){
		addRowConstraint("[19.00px]");		
				
		final JPanel userPanel = new JPanel();
		userPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_UsersList.add(userPanel, "cell 0 "+userNumber+",grow");		
		userNumber++;
		
		final JPanel panelSurname = new JPanel();
		panelSurname.setBorder(new MatteBorder(0, 0, 0, 0, new Color(128, 128, 128)));		
		final JPanel panelName = new JPanel();
		panelName.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));		
		final JPanel panelRank = new JPanel();
		panelRank.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));		
		final JPanel panelRange = new JPanel();
		panelRange.setBorder(new MatteBorder(0, 1, 0, 0, new Color(128, 128, 128)));
		
		//################## WYSWIETLANE POLA ######################				
		JLabel lblSurname = new JLabel(user.getSurName());
		panelSurname.add(lblSurname);
		
		JLabel lblName = new JLabel(user.getName());
		panelName.add(lblName);
		
		final JLabel lblPesel = new JLabel(user.getPesel());
		panelRank.add(lblPesel);
		
		JLabel lblRange = new JLabel(Integer.toString(user.getRestriction()));
		panelRange.add(lblRange);
		
		//################## ON CLICK ######################
		
		userPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long currTime = System.currentTimeMillis();
				if(currTime-time < 500){
					if(user.getClass().getName().compareTo("obsluga.Parishioner") == 0){
						EditParishionerDialog editParishionerDialog = new EditParishionerDialog(owner, (Parishioner)user);
						editParishionerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						editParishionerDialog.setVisible(true);
						
						if(editParishionerDialog.isOk()){
							Parishioner parishioner = editParishionerDialog.getParishionerEdited();
							// w tym miejscu trzeba wywo�a� metode edytuj�c� parafianina
						}else if(editParishionerDialog.isCansel()){
							
						}
					}else if(user.getClass().getName().compareTo("obsluga.Priest") == 0){
						EditPriestDialog editPriestDialog = new EditPriestDialog(owner, (Priest)user);
						editPriestDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						editPriestDialog.setVisible(true);
						
						if(editPriestDialog.isOk()){
							Priest priest = editPriestDialog.getPriestEdited();
							// w tym miejscu trzeba wywo�a� metode edytuj�c� parafianina
						}else if(editPriestDialog.isCansel()){
							
						}
					}else
						JOptionPane.showMessageDialog(null, "Klasa nie obs�ugiwana","Class Error",JOptionPane.ERROR_MESSAGE);
				}	
				time = currTime;
			}
		});
		
		GroupLayout gl_userPanel = new GroupLayout(userPanel);
		gl_userPanel.setHorizontalGroup(
			gl_userPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_userPanel.createSequentialGroup()
					.addComponent(panelSurname, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelName, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelRank, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelRange, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
		);
		gl_userPanel.setVerticalGroup(
			gl_userPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSurname, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panelName, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panelRank, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
				.addComponent(panelRange, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
		);
		userPanel.setLayout(gl_userPanel);
		
	}

	public void listUsers(LinkedList<Person> userList){
		this.userList = userList;
		listUsers();
	}
	
	public void setUserList(LinkedList<Person> userList){
		this.userList = userList;
	}
	
	public LinkedList<Person> getUserList(){
		return userList;
	}
	
	public void listUsers(){
		panel_UsersList.removeAll();
		userNumber=0;
		resetRowConstraint();
		
		Iterator<Person> iterator = userList.iterator();

		while(iterator.hasNext()){
			addUser(iterator.next());
		}
	}
	
	public String getSurname(){
		return text_Surname.getText();
	}
	public String getName(){
		return text_Name.getText();
	}
	public String getPesel(){
		return text_Pesel.getText();
	}
	public int getRestrictionSelected(){
		int item = combo_Range.getSelectedIndex();
		switch (item){
		case 1:
			return KindRestriction.LOGED_R;
		case 2:
			return KindRestriction.WORKS_R;
		case 3:
			return KindRestriction.GOD_R;
		default:
			return -1;
		}
	}
	public int getRestrictionSelectedIndex(){
		return combo_Range.getSelectedIndex();
	}
	public String getRestrictionSelectedItem(){
		return (String)combo_Range.getSelectedItem();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnApply){
			String surname = getSurname();
			String name = getName();
			String pesel = getPesel();
			int restriction = getRestrictionSelected();
			
			//userList = events.getUserList(surname, name, pesel, restriction);
			//listUsers();			
		}
		
		if(z==btnPdf){
			/*PdfCreator pdf = new PdfCreator();
			try{
			pdf.setMyEventList(events.getClient().getEventKindList());
			pdf.createUserPdf(this.getUserList(), Pomoc.saveFileWindow());
			}catch(IOException rrr){
				JOptionPane.showMessageDialog(null, "B��d w miejscu zapisu");
			}catch(DocumentException rrr){
				JOptionPane.showMessageDialog(null, "B��d kreatora dokumentu");

			}*/
		}
	}
}











class ObserwatorStanSortowania implements Obserwowany
{
    protected List<Obserwator> obserwatorzy = new ArrayList<Obserwator>();
    private Obserwator obserwator;
    private UserListPanel usersListPanel;
 
    public ObserwatorStanSortowania(UserListPanel usersListPanel){
    	super();
    	this.usersListPanel = usersListPanel;
    }
    
    public void dodajObserwatora( Obserwator obserwator ) {
        obserwatorzy.add( obserwator );
        if(obserwator.getStan() != StanSortowania.IMG_CLEAR)
        	this.obserwator = obserwator;
    }
 
    public void powiadomObserwatorow(Obserwator obserwator) {
		this.obserwator = obserwator;
        Iterator<Obserwator> it = obserwatorzy.iterator();
        sortUserList();
        while( it.hasNext() )
        {
        	Obserwator actual = it.next();
        	if(actual != obserwator)
        		actual.uaktualnij(StanSortowania.IMG_CLEAR);
        	else{
        		if(actual.getStan() == StanSortowania.IMG_UP)
        			actual.uaktualnij(StanSortowania.IMG_DOWN);
        		else
        			actual.uaktualnij(StanSortowania.IMG_UP);
        	}
        }        
    }
    
    private void sortUserList(){
    	LinkedList<Person> userList = usersListPanel.getUserList();
    	Person[] userArray = userList.toArray(new Person[userList.size()]);
    	Person tmp;
    	if(obserwator.getName().equals("name")){
    		if(obserwator.getStan() == StanSortowania.IMG_UP){
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getName().compareToIgnoreCase(userArray[i].getName()) < 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getName().compareToIgnoreCase(userArray[i].getName()) > 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		}
    	} else if(obserwator.getName().equals("surname")){
    		if(obserwator.getStan() == StanSortowania.IMG_UP){
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getSurName().compareToIgnoreCase(userArray[i].getSurName()) < 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getSurName().compareToIgnoreCase(userArray[i].getSurName()) > 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		}
    	} else if(obserwator.getName().equals("pesel")){
    		if(obserwator.getStan() == StanSortowania.IMG_UP){
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getPesel().compareToIgnoreCase(userArray[i].getPesel()) < 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getPesel().compareToIgnoreCase(userArray[i].getPesel()) > 0){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		}
    	} else if(obserwator.getName().equals("range")){
    		if(obserwator.getStan() == StanSortowania.IMG_UP){
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getRestriction() < userArray[i].getRestriction()){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		} else {
		    	for(int j=0;j<userArray.length-1;j++)
			    	for(int i=1; i<userArray.length;i++){
			    		if(userArray[i-1].getRestriction() > userArray[i].getRestriction()){
			    			tmp = userArray[i-1];
			    			userArray[i-1] = userArray[i];
			    			userArray[i] = tmp;
			    		}
			    	}
    		}
    	}
    	
    	userList.clear();

    	for(int i=0; i<userArray.length;i++){
    		userList.add(userArray[i]);
    	}
    	usersListPanel.setUserList(userList);
    	usersListPanel.listUsers();
    	
    }
 
    public void usunObserwatora( Obserwator obserwator ) {
    	obserwatorzy.remove( obserwator );
    }
    
    public Obserwator getActualObserwator(){
    	return obserwator;
    }
}

class StanSortowania implements Obserwator
{
    public static final int IMG_CLEAR = 0;
    public static final int IMG_UP = 1;
    public static final int IMG_DOWN = 2;
	private static final ImageIcon ICON_UP = new ImageIcon("icons/sortup.png");
	private static final ImageIcon ICON_DOWN = new ImageIcon("icons/sortdown.png");
	private static final ImageIcon ICON_CLEAR = new ImageIcon("icons/sortclean.png");

    
	private String name;
	private JLabel label_icon;
	private int stan;
 
    /**
     * @param label - JLabel gdzie ma znajdowa� si� ikona
     * @param name - Nadana nazwa dla Stanu Sortowania
     */
    public StanSortowania(JLabel label_icon, String name) {
        this.name = name;
        this.label_icon = label_icon;
        this.uaktualnij(IMG_CLEAR);
    }
    
    /**
     * @param label - JLabel gdzie ma znajdowa� si� ikona
     * @param name - Nadana nazwa dla Stanu Sortowania
     * @param stan - IMG_CLEAR, IMG_UP lub IMG_DOWN
     */
    public StanSortowania(JLabel label, String name, int stan) {
    	this(label,name);
    	this.uaktualnij(stan);
    }
 
    public void uaktualnij(int stan) {
    	this.stan = stan;
    	if(stan == IMG_CLEAR)
    		label_icon.setIcon(ICON_CLEAR);
    	else if(stan == IMG_UP)
    		label_icon.setIcon(ICON_UP);
    	else if(stan == IMG_DOWN)
    		label_icon.setIcon(ICON_DOWN);
    	else
    		label_icon.setIcon(ICON_CLEAR);
    }
    
    public String getName(){
    	return name;
    }
        
    public int getStan(){
    	return stan;
    }
}
