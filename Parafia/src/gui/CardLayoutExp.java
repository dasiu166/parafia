package gui;

import gui.calendar.JCalendar;
import gui.panels.LoginPanel;
import gui.panels.parishioner.NewOrderPanel;
import gui.panels.parishioner.ParishionerDataPanel;
import gui.panels.priest.AddNewParishionerPanel;
import gui.panels.priest.AddNewPriestPanel;
import gui.panels.priest.AddNewsPanel;
import gui.panels.priest.OrdersListPanel;
import gui.panels.priest.PriestDataPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import obsluga.Parishioner;
import obsluga.Priest;
import stale.KindRestriction;

import com.jgoodies.looks.FontPolicies;
import com.jgoodies.looks.FontPolicy;
import com.jgoodies.looks.FontSet;
import com.jgoodies.looks.FontSets;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class CardLayoutExp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelContent;
	private JMenuBar menuBar;
	private CardLayout cl;
	private CardLayoutExp frame = this;
	private Events events;// = Events.getInstance();
	
	/*
	 * Aby dzia³a³ nowy wygl¹d który doda³em nale¿y dodaæ biblioteki z folderu lib do Bildera
	 * projekt -> properties -> java build patch -> libraries -> add jar / folder lib
	 */
	

	/**
	 * Create the frame.
	 * ############# COSTRUCTOR ############
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public CardLayoutExp() throws ClassNotFoundException, IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CardLayoutExp.class.getResource("/icons/church-icon.png")));
		events = Events.getInstance(this);
		
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		
		// Inicjalizacja i ustawienie nowego wygl¹du: JGoodies Plastic 3D look and feel
		initializeLookAndFeels();
				
        setTitle("System zarz¹dzania uroczystosciami");
                
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 	// ustawienie akcji dla przycisku X
		setBounds(100, 100, 800, 600);						  	// ustwienie rozmieru okna
		contentPane = new JPanel();								// stworzenie panelu g³ównego formatki
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));			// ustawienie obramowania
		setContentPane(contentPane);								// ustawienie panelu g³ównego dla formatki
		
		//#################### PANEL LOGOWANIA ####################
		LoginPanel panelLogowania = new LoginPanel(frame); 
		panelLogowania.setBounds(new Rectangle(0, 0, 250, 250));
		panelLogowania.setAlignmentY(Component.TOP_ALIGNMENT);
		panelLogowania.setMaximumSize(new Dimension(200, 200));
		
		//#################### PANEL KALENDARZA ####################
		JPanel panelCalendar =  new JCalendar();
		
		//#################### PANEL CONTENT ####################
		panelContent = new JPanel();				// stworzenie panelu logowania
		cl = new CardLayout();									// stworzenie zarz¹dcy faormatki
		panelContent.setLayout(cl);								// ustawienie zarz¹dcy formatki dla panelu z zawartoœci¹
		
		PanelNews jpNews = new PanelNews(this);
        panelContent.add(jpNews, "news");
        //jpNews.addNews("Aktualnosc 5", new Date(), "Zdzichu Kasprowicz", 56,"<p style=\"color:orange; margin:0px; padding:0px;\"><b>Lorem ipsum</b> - Zawarto\u015B\u0107 aktualno\u015Bci 5</p>");
        
        final ParishionerDataPanel jpParishionerData = new ParishionerDataPanel(this);
        panelContent.add(jpParishionerData, "parishionerData");
        
        final NewOrderPanel jpNewOrder = new NewOrderPanel();
       // JLabel jl3 = new JLabel("Card3");
        //jp3.add(jl3);
        panelContent.add(jpNewOrder, "newOrder");
        
        final PriestDataPanel jpPriestData = new PriestDataPanel(this);
        panelContent.add(jpPriestData, "priestData");
        
     	final AddNewParishionerPanel jpAddNewParishioner = new AddNewParishionerPanel(this);
      	panelContent.add(jpAddNewParishioner, "addNewParishioner");
      	
      	final AddNewsPanel jpAddNews = new AddNewsPanel(this);
      	panelContent.add(jpAddNews, "addNews");
      	
      	final AddNewPriestPanel jpAddNewPriest = new AddNewPriestPanel(this);
      	panelContent.add(jpAddNewPriest, "addNewPriest");
      	
      	final OrdersListPanel jpOrdersList = new OrdersListPanel(this);
      	panelContent.add(jpOrdersList, "ordersList");
		Zegar lblTime = new Zegar();
		lblTime.setAutoscrolls(true);
		lblTime.setIcon(new ImageIcon("C:\\Documents and Settings\\hp\\git\\parafia\\Parafia\\icons\\Clock-icon.png"));
		lblTime.start();
		lblTime.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        
		//#################### CONTENT PANEL ####################
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(panelCalendar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(8)
								.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelContent, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(panelCalendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(280, Short.MAX_VALUE))
				.addComponent(panelContent, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
		);
		
		contentPane.setLayout(gl_contentPane);
		
		//#################### MENU BAR ####################
			//utworzenie paska menu
        menuBar = new JMenuBar();
        
        	// menu Plik
        JMenu menuPlik = new JMenu("Plik");
        menuPlik.setMnemonic('P'); //po wciœniêciu p otworzy siê menu je¿eli jest aktywne aktualnie trzeba wybraæ alt+p;
        menuBar.add(menuPlik); // dodanie menu plik do paska menu
	       
        	JMenuItem menuPlikPanelNews = new JMenuItem("Aktualno\u015Bci",'1');
        	menuPlikPanelNews.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/Clipboard-Full-icon.png")));
	        menuPlikPanelNews.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "news");
				}
			});
	        menuPlik.add(menuPlikPanelNews);
			JMenuItem menuPlikPanel2 = new JMenuItem("parafianin/dane",'2');
			menuPlikPanel2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//JOptionPane.showMessageDialog(null, "Logged: "+events.getLogged()+"\nParishioner:\n"+events.getParishioner());
					events = Events.getInstance();
					
					if(events.getLogged()){ 
						/*try {
							events.pobierzDane();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						if (events.getRestriction()==KindRestriction.LOGED_R)
						{
							
							jpParishionerData.setUserData(events.getParishioner());
						} else{
							jpParishionerData.setUserData(new Parishioner());
						}
					}
					cl.show(panelContent, "parishionerData");
				}
			});
			menuPlik.add(menuPlikPanel2);
			JMenuItem menuPlikPanel3 = new JMenuItem("user/noweZamowienie",'3');
			menuPlikPanel3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "newOrder");
				}
			});
			menuPlik.add(menuPlikPanel3);
			JMenuItem menuPlikPanel4 = new JMenuItem("ksiadz/dane",'4');
			menuPlikPanel4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(events.getLogged()){ 
						/*try {
							events.pobierzDane();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						if (events.getRestriction()>KindRestriction.LOGED_R)
						{
							jpPriestData.setPriestData(events.getPriest());
						} else{
							jpPriestData.setPriestData(new Priest());
						}
					}
					cl.show(panelContent, "priestData");
				}
			});
			menuPlik.add(menuPlikPanel4);
			
			JMenuItem mntmPanel = new JMenuItem("ksiadz/addNewParishioner");
			mntmPanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "addNewParishioner");
				}
			});
			menuPlik.add(mntmPanel);
			
			JMenuItem mntmAddNews = new JMenuItem("ksiadz/addNews");
			mntmAddNews.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cl.show(panelContent, "addNews");
				}
			});
			menuPlik.add(mntmAddNews);
			
			JMenuItem mntmAddnewpriestpanel = new JMenuItem("ksiadz/addNewPriestPanel");
			mntmAddnewpriestpanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "addNewPriest");
				}
			});
			menuPlik.add(mntmAddnewpriestpanel);
			JSeparator separator = new JSeparator(); // tworzy linie poziom¹ w menu plik taki separator
			menuPlik.add(separator);
			JMenuItem menuPlikClose = new JMenuItem("Close");
			menuPlikClose.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/Error-icon.png")));
			menuPlikClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// wys³anie chêci zamkniêcia aplikacji do systemu (symulowanie wciœniêcia X)
					java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(new java.awt.event.WindowEvent(frame, java.awt.event.WindowEvent.WINDOW_CLOSING));
				}
			});
			menuPlikClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK)); // po wciœniêciu alt+f4 zostanie wywo³ane wciœniêcie menu colse
			menuPlik.add(menuPlikClose);
		
		// Menu for the look and feels (lnfs).
		UIManager.LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();

		ButtonGroup lnfGroup = new ButtonGroup();
		JMenu menuLookFeel = new JMenu("Look&Feel");
		menuLookFeel.setMnemonic('L');

		menuBar.add(menuLookFeel);

		for (int i = 0; i < lnfs.length; i++) {
			if (!lnfs[i].getName().equals("CDE/Motif")) {
				JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(
						lnfs[i].getName());
				menuLookFeel.add(rbmi);

				// preselect the current Look & feel
				rbmi.setSelected(UIManager.getLookAndFeel().getName().equals(lnfs[i].getName()));

				// store lool & feel info as client property
				rbmi.putClientProperty("lnf name", lnfs[i]);

				// create and add the item listener
				rbmi.addItemListener(
				// inlining
				new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						JRadioButtonMenuItem rbmi2 = (JRadioButtonMenuItem) ie.getSource();

						if (rbmi2.isSelected()) {
							// get the stored look & feel info
							UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rbmi2.getClientProperty("lnf name");

							try {
								//menuBar.putClientProperty("jgoodies.headerStyle", "Both");
								UIManager.setLookAndFeel(info.getClassName());
								SwingUtilities.updateComponentTreeUI(frame);
							} catch (Exception e) {e.printStackTrace();	System.err.println("Unable to set UI "+ e.getMessage());}
						}
					}
				});
				lnfGroup.add(rbmi);
			}
		}
		
		JMenu menuParishioner = new JMenu("Parafianin");
		menuBar.add(menuParishioner);
		
		JMenuItem menuParishionerDane = new JMenuItem("Dane");
		menuParishionerDane.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/User-2-icon.png")));
		menuParishionerDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Logged: "+events.getLogged()+"\nParishioner:\n"+events.getParishioner().getName());
				if(events.getLogged()) {
					try {
						events.pobierzDane();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (events.getRestriction()==KindRestriction.LOGED_R)
					{
						jpParishionerData.setUserData(events.getParishioner());
					}
					
				}
				cl.show(panelContent, "parishionerData");
			}
		});
		menuParishioner.add(menuParishionerDane);
		
		JMenuItem mntmParishionerZamowienie = new JMenuItem("Zamowienie");
		mntmParishionerZamowienie.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/Add-icon.png")));
		mntmParishionerZamowienie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				events.pobierzZdarzenia();
				}catch(ClassNotFoundException e){
					
				}catch(IOException e){
					
				}
				jpNewOrder.setOrderList(events.getClient().getEventKindList());
				cl.show(panelContent, "newOrder");
			}
		});
		menuParishioner.add(mntmParishionerZamowienie);
		
		JMenu mnKsidz = new JMenu("Ksi\u0105dz");
		menuBar.add(mnKsidz);
		
		JMenuItem mntmParishionerDane = new JMenuItem("Dane");
		mntmParishionerDane.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/User-icon.png")));
		mntmParishionerDane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(events.getLogged()){ 
					try {
						events.pobierzDane();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (events.getRestriction()>KindRestriction.LOGED_R)
					{
						jpPriestData.setPriestData(events.getPriest());
					}
				}
				cl.show(panelContent, "priestData");
			}
		});
		mnKsidz.add(mntmParishionerDane);
		
		JMenuItem mntmAddnewparishioner = new JMenuItem("addNewParishioner");
		mntmAddnewparishioner.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/addparishioner.png")));
		mntmAddnewparishioner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "addNewParishioner");
			}
		});
		mnKsidz.add(mntmAddnewparishioner);
		
		JMenuItem mntmKsiadzAddNews = new JMenuItem("addNews");
		mntmKsiadzAddNews.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/File-List-icon.png")));
		mntmKsiadzAddNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "addNews");
			}
		});
		mnKsidz.add(mntmKsiadzAddNews);
		
		JMenuItem mntmAddnewpriest = new JMenuItem("addNewPriest");
		mntmAddnewpriest.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/priest-add-icon.png")));
		mntmAddnewpriest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panelContent, "addNewPriest");
			}
		});
		mnKsidz.add(mntmAddnewpriest);
		
		JMenuItem mntmOrderslist = new JMenuItem("ordersList");
		mntmOrderslist.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/Bookmark-icon.png")));
		mntmOrderslist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					events.pobierzZdarzenia();
					}catch(ClassNotFoundException e){
						
					}catch(IOException e){
						
					}
				
				jpOrdersList.setEventList(events.getClient().getEventKindList());
				jpOrdersList.loadListOrder();
				cl.show(panelContent, "ordersList");
				//jpOrdersList.removeAll();
			}
		});
		mnKsidz.add(mntmOrderslist);
		
		Component horizontalGlue = Box.createHorizontalGlue(); // dziêki temu menuPomoc jest po prawej stronie
		menuBar.add(horizontalGlue);
		
			// menu Pomoc
		JMenu menuPomoc = new JMenu("Pomoc");
		menuPomoc.setMnemonic('H');
		menuBar.add(menuPomoc);
			final JMenuItem mnPomocOProgramie = new JMenuItem("O Programie");
			mnPomocOProgramie.setIcon(new ImageIcon(CardLayoutExp.class.getResource("/icons/Help-icon.png")));
			mnPomocOProgramie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Projekt z In¿ynierii Programowania\nProwadz¹cy:\n       prof. zw. dr hab. in¿. Krzysztof Sapiecha\nZespó³:\n       Mariusz Charczuk\n       Pawe³ Dziarmaga\n       Grzegorz Chrab¹szcz\n       Ewiak Piotr", "O Programie",JOptionPane.PLAIN_MESSAGE);
				}
			});
			mnPomocOProgramie.setAccelerator(KeyStroke.getKeyStroke("F1"));
			menuPomoc.add(mnPomocOProgramie);
				
        
		
		setJMenuBar(menuBar);
		
		events.getNewsList().addNewsListToPanel(jpNews);
		resetSettings();
	}
	
	public void resetSettings(){
		menuBar.getComponent(2).setVisible(false);
		menuBar.getComponent(2).setEnabled(false);
		menuBar.getComponent(3).setVisible(false);
		menuBar.getComponent(3).setEnabled(false);
		cl.show(panelContent, "news");
	}
	
	public void loginUser(){
		if(events.getRestriction() == KindRestriction.LOGED_R){
			menuBar.getComponent(2).setVisible(true);
			menuBar.getComponent(2).setEnabled(true);
		} else if (events.getRestriction()>KindRestriction.LOGED_R){
			menuBar.getComponent(3).setVisible(true);
			menuBar.getComponent(3).setEnabled(true);
		}
	}
	
	/**
	 * Installs the JGoodies Look & Feels, if available, in classpath.
	 * Wczytanie nowego wygl¹du okienek
	 */
	public final void initializeLookAndFeels() {
		// if in classpath thry to load JGoodies Plastic Look & Feel
		try {
			LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();
			boolean found = false;
			for (int i = 0; i < lnfs.length; i++) {
				if (lnfs[i].getName().equals("JGoodies Plastic 3D")) {
					found = true;
				}
			}
			if (!found) {
				UIManager.installLookAndFeel("JGoodies Plastic 3D",
						"com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			}
			String os = System.getProperty("os.name");
			FontSet fontSet = null;
			if (os.startsWith("Windows")) {
				fontSet = FontSets.createDefaultFontSet(new Font(
						"arial unicode MS", Font.PLAIN, 12));
			} else {
				fontSet = FontSets.createDefaultFontSet(new Font(
						"arial unicode", Font.PLAIN, 12));				
			}
			FontPolicy fixedPolicy = FontPolicies.createFixedPolicy(fontSet);
			PlasticLookAndFeel.setFontPolicy(fixedPolicy);

			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch (Throwable t) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void connectionError(){
		JOptionPane.showMessageDialog(null, "Blad polaczenia");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				WindowListener l = new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						//JOptionPane.showMessageDialog(null, e.getSource());
						int odt = JOptionPane.showConfirmDialog(null, "Czy na pewno Chcesz wyjœæ?","Wyjœcie",JOptionPane.YES_NO_OPTION);
						if(odt == JOptionPane.YES_OPTION){
							System.exit(0);
						}
					}
				};
				
				try {					
					CardLayoutExp frame = new CardLayoutExp();
					frame.addWindowListener(l);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Blad po³¹czenia z serwerem");
					e.printStackTrace();
				}
			}
		});
	}
}
