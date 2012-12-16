package gui;


import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class CardLayoutExp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cl;
	//private Client c;
	

	/**
	 * Create the frame.
	 */
	public CardLayoutExp() {
		//ustawienie wygl¹du okienek na typ Nimbus
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {e1.printStackTrace();} catch (InstantiationException e1){e1.printStackTrace();}catch(IllegalAccessException e1){e1.printStackTrace();}catch(UnsupportedLookAndFeelException e1){e1.printStackTrace();}
		SwingUtilities.updateComponentTreeUI(this);
				
        setTitle("System zarz¹dzania parafi¹");
        
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//#################### PANEL LOGOWANIA ####################
		JPanel panelLogowania = new PanelLogowania(this);
		
		
		//#################### PANEL CONTENT ####################
		final JPanel panelContent = new JPanel();
		cl = new CardLayout();
		panelContent.setLayout(cl);
		
		JPanel jp1 = new Panel1(this);
        panelContent.add(jp1, "login");
        
		JPanel jp2 = new Panel2(this);
        panelContent.add(jp2, "tab");
        
        JPanel jp3 = new JPanel();
        JLabel jl3 = new JLabel("Card3");
        jp3.add(jl3);
        panelContent.add(jp3, "panel3");
        
        JPanel jp4 = new JPanel();        
        JLabel jl4 = new JLabel("Card4");
        jp4.add(jl4);
        panelContent.add(jp4, "panel4");
		
		//#################### CONTENT PANEL ####################
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelLogowania, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(panelContent, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelContent, GroupLayout.PREFERRED_SIZE, 211, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
		//#################### MENU BAR ####################
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPlik = new JMenu("Plik");
        final JMenuItem menuPlikPanel1 = new JMenuItem("Panel1",'1');
		final JMenuItem menuPlikPanel2 = new JMenuItem("Panel2",'2');
		final JMenuItem menuPlikPanel3 = new JMenuItem("Panel3",'3');
		final JMenuItem menuPlikPanel4 = new JMenuItem("Panel4",'4');
		JSeparator separator = new JSeparator();
		final JMenuItem menuPlikClose = new JMenuItem("Close");
		Component horizontalGlue = Box.createHorizontalGlue();
		JMenu mnPomoc = new JMenu("Pomoc");
		final JMenuItem mnPomocOProgramie = new JMenuItem("O Programie");

		setJMenuBar(menuBar);
		menuBar.add(menuPlik);
			menuPlik.add(menuPlikPanel1);
			menuPlik.add(menuPlikPanel2);
			menuPlik.add(menuPlikPanel3);
			menuPlik.add(menuPlikPanel4);
			menuPlik.add(separator);
			menuPlik.add(menuPlikClose);
		menuBar.add(horizontalGlue);
		menuBar.add(mnPomoc);
			mnPomoc.add(mnPomocOProgramie);
		
        ActionListener menuActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				
				if(o == menuPlikClose)
				{
					if(e.getModifiers() == 8){
						dispose();
					} else {
						int odt = JOptionPane.showConfirmDialog(null, "Czy na pewno Chcesz wyjœæ?","Wyjœcie",JOptionPane.YES_NO_OPTION);
						if(odt == JOptionPane.YES_OPTION)
							dispose();
					}
				}
				else if(o == mnPomocOProgramie)
				{
					JOptionPane.showMessageDialog(null, "Projekt z In¿ynierii Programowania\nProwadz¹cy:\n       Krzysztof Sapiecha\nZespó³:\n       Mariusz Charczuk\n       Pawe³ Dziarmaga\n       Grzegorz Chrab¹szcz\n       Piotr Ewiak", "O Programie",JOptionPane.PLAIN_MESSAGE);
				}else if(o == menuPlikPanel1)
				{
					//JOptionPane.showMessageDialog(null, "Panel1");
					cl.show(panelContent, "login");
				}
				else if(o == menuPlikPanel2)
				{
					//JOptionPane.showMessageDialog(null, "Panel2");
					cl.show(panelContent, "tab");
				}
				else if(o == menuPlikPanel3)
				{
					//JOptionPane.showMessageDialog(null, "Panel3");
					cl.show(panelContent, "panel" + 3);
				}
				else if(o == menuPlikPanel4)
				{
					//JOptionPane.showMessageDialog(null, "Panel4");
					cl.show(panelContent, "panel" + 4);
				}
			}
		};
		
		menuPlikPanel1.addActionListener(menuActionListener);
		menuPlikPanel2.addActionListener(menuActionListener);
		menuPlikPanel3.addActionListener(menuActionListener);
		menuPlikPanel4.addActionListener(menuActionListener);
		menuPlikClose.addActionListener(menuActionListener);
		menuPlikClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnPomocOProgramie.addActionListener(menuActionListener);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardLayoutExp frame = new CardLayoutExp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
