package gui;


import gui.calendar.JCalendar;

import java.awt.CardLayout;
import java.awt.Component;
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

import com.jgoodies.looks.FontPolicies;
import com.jgoodies.looks.FontPolicy;
import com.jgoodies.looks.FontSet;
import com.jgoodies.looks.FontSets;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;

public class CardLayoutExp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cl;
	private JFrame frame = this;
	//private Client c;
	
	/*
	 * Aby dzia³a³ nowy wygl¹d który doda³em nale¿y dodaæ biblioteki z folderu lib do Bildera
	 * projekt -> properties -> java build patch -> libraries -> add jar / folder lib
	 */
	

	/**
	 * Create the frame.
	 */
	public CardLayoutExp() {
		
		// Set the JGoodies Plastic 3D look and feel
				initializeLookAndFeels();
		
		//ustawienie wygl¹du okienek na typ Nimbus
		//try {
	//		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	//	} catch (ClassNotFoundException e1) {e1.printStackTrace();} catch (InstantiationException e1){e1.printStackTrace();}catch(IllegalAccessException e1){e1.printStackTrace();}catch(UnsupportedLookAndFeelException e1){e1.printStackTrace();}
		//SwingUtilities.updateComponentTreeUI(this);
				
        setTitle("System zarz¹dzania parafi¹");
        
        
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//#################### PANEL LOGOWANIA ####################
		JPanel panelLogowania = new PanelLogowania(this);
		
		
		//#################### PANEL CONTENT ####################
		final JPanel panelContent = new JPanel();
		cl = new CardLayout();
		panelContent.setLayout(cl);
		
		JPanel jpAktualnosci = new PAktualnosci(this);
        panelContent.add(jpAktualnosci, "login");
        
		JPanel jp2 = new Panel2(this);
        panelContent.add(jp2, "tab");
        
        JPanel jp3 = new DateChooserPanel();
       // JLabel jl3 = new JLabel("Card3");
        //jp3.add(jl3);
        panelContent.add(jp3, "panel3");
        
        JPanel jp4 = new JPanel();        
        JLabel jl4 = new JLabel("Card4");
        jp4.add(jl4);
        panelContent.add(jp4, "panel4");
		
		JPanel panelCalendar = new JCalendar();
		
		JScrollPane scrollPaneInfo = new JScrollPane();
		
		JPanel panelTime = new JPanel();
		
		//#################### CONTENT PANEL ####################
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPaneInfo)
						.addComponent(panelCalendar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelLogowania, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(panelTime, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelContent, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panelTime, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(panelCalendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneInfo, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
				.addComponent(panelContent, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
		);
		panelTime.setLayout(null);
		
		JLabel lblTime = new JLabel("15:38");
		lblTime.setBounds(57, 0, 43, 15);
		lblTime.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		panelTime.add(lblTime);
		
		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setText("fdsfgvsdfgvsdf");
		scrollPaneInfo.setViewportView(txtpnInfo);
		contentPane.setLayout(gl_contentPane);
		
		
		
		//#################### MENU BAR ####################
			//utworzenie paska menu
        final JMenuBar menuBar = new JMenuBar();
        
        	// menu Plik
        JMenu menuPlik = new JMenu("Plik");
        menuPlik.setMnemonic('P'); //po wciœniêciu p otworzy siê menu je¿eli jest aktywne aktualnie trzeba wybraæ alt+p;
        menuBar.add(menuPlik); // dodanie menu plik do paska menu
	       
        	final JMenuItem menuPlikPanel1 = new JMenuItem("Panel1",'1');
	        menuPlikPanel1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "login");
				}
			});
	        menuPlik.add(menuPlikPanel1);	        
			final JMenuItem menuPlikPanel2 = new JMenuItem("Panel2",'2');
			menuPlikPanel2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "tab");
				}
			});
			menuPlik.add(menuPlikPanel2);			
			final JMenuItem menuPlikPanel3 = new JMenuItem("Panel3",'3');
			menuPlikPanel3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "panel" + 3);
				}
			});
			menuPlik.add(menuPlikPanel3);			
			final JMenuItem menuPlikPanel4 = new JMenuItem("Panel4",'4');
			menuPlikPanel4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cl.show(panelContent, "panel" + 4);
				}
			});
			menuPlik.add(menuPlikPanel4);			
			JSeparator separator = new JSeparator(); // tworzy linie poziom¹ w menu plik taki separator
			menuPlik.add(separator);
			final JMenuItem menuPlikClose = new JMenuItem("Close");
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
		
		Component horizontalGlue = Box.createHorizontalGlue(); // dziêki temu menuPomoc jest po prawej stronie
		menuBar.add(horizontalGlue);
		
			// menu Pomoc
		JMenu menuPomoc = new JMenu("Pomoc");
		menuPomoc.setMnemonic('H');
		menuBar.add(menuPomoc);
			final JMenuItem mnPomocOProgramie = new JMenuItem("O Programie");
			mnPomocOProgramie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Projekt z In¿ynierii Programowania\nProwadz¹cy:\n       Krzysztof Sapiecha\nZespó³:\n       Mariusz Charczuk\n       Pawe³ Dziarmaga\n       Grzegorz Chrab¹szcz\n       Ewiak Piotr", "O Programie",JOptionPane.PLAIN_MESSAGE);
				}
			});
			mnPomocOProgramie.setAccelerator(KeyStroke.getKeyStroke("F1"));
			menuPomoc.add(mnPomocOProgramie);
				
        
		
		setJMenuBar(menuBar);
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				WindowListener l = new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
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
					e.printStackTrace();
				}
			}
		});
	}
}
