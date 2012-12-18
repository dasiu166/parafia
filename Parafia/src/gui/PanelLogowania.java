package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import obsluga.Parishioner;

public class PanelLogowania extends JPanel {

	private static final long serialVersionUID = 1L;
    private CardLayout cl;
    private DialogLogowania dialogLogowania;
    private Events event = new Events();
	/**
	 * Create the panel.
	 */
	public PanelLogowania(final JFrame owner) {
        setSize(150, 70);        
        cl = new CardLayout();
        setLayout(cl);
        final JPanel ja = this;
        							// Panel przed Zalogowaniem
        JPanel unlogged = new JPanel();
        add(unlogged, "unlogged");
        unlogged.setLayout(null);
        
        JButton bZaloguj = new JButton("Zaloguj");
        bZaloguj.setBounds(40, 23, 70, 24);
        unlogged.add(bZaloguj);
        
        							// Panel po zalogowaniu
        JPanel logged = new JPanel();
        add(logged, "logged");
        logged.setLayout(null);
        
        JLabel lblJesteZalogowany = new JLabel("Jeste\u015B zalogowany jako:");
        lblJesteZalogowany.setHorizontalAlignment(SwingConstants.CENTER);
        lblJesteZalogowany.setBounds(0, 4, 150, 14);
        logged.add(lblJesteZalogowany);
        
        final JLabel lblUserName = new JLabel("Nazwa U\u017Cytkownika");
        lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserName.setBounds(0, 24, 150, 14);
        logged.add(lblUserName);
        
        JButton bLogOut = new JButton("Wyloguj");
        // akcja po wci�ni�ciu przycisku WYLOGUJ
        bLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(event.wyloguj()){
	        		cl.show(ja, "unlogged");
	        		JOptionPane.showMessageDialog(null, "Wylogowano");
        		} else {
        			JOptionPane.showMessageDialog(null, "B��d wylogowania", "B��d", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        bLogOut.setBounds(31, 44, 90, 20);
        logged.add(bLogOut);
        
        // akcja po wci�ni�ciu przycisku ZALOGUJ
        bZaloguj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(dialogLogowania==null)
        			dialogLogowania = new DialogLogowania(owner);
        		dialogLogowania.setVisible(true);
        		dialogLogowania.setFocus();
        		
        		if(dialogLogowania.isOK()){
        			
        			if(event.zaloguj(dialogLogowania.getLogin(), dialogLogowania.getPassword())){
        				Parishioner p = event.getParishioner();
        				lblUserName.setText(p.getName()+" "+p.getSurName());
        				cl.show(ja, "logged");
        			}
        		}
        	}
        });
	}
}
