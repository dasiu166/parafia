package gui.panels;

import gui.CardLayoutExp;
import gui.Events;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import obsluga.Parishioner;
import obsluga.Priest;
import stale.KindRestriction;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    private CardLayout cl;
    private LoginDialog dialogLogowania;
    private Events event = Events.getInstance();
	/**
	 * Create the panel.
	 */
	public LoginPanel(final CardLayoutExp owner) {
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
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserName.setBounds(0, 24, 150, 14);
        logged.add(lblUserName);
        
        JButton bLogOut = new JButton("Wyloguj");
        // akcja po wciœniêciu przycisku ### WYLOGUJ ###
        bLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(event.wyloguj()){
        			owner.resetSettings();
	        		cl.show(ja, "unlogged");
	        		JOptionPane.showMessageDialog(null, "Wylogowano");
        		} else {
        			JOptionPane.showMessageDialog(null, "B³¹d wylogowania", "B³¹d", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        bLogOut.setBounds(31, 44, 90, 20);
        logged.add(bLogOut);
        
        // akcja po wciœniêciu przycisku ### ZALOGUJ ###
        bZaloguj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(dialogLogowania==null)
        			dialogLogowania = new LoginDialog(null);
        		dialogLogowania.setVisible(true);
        		dialogLogowania.setFocus();
        		
        		if(dialogLogowania.isOK()){
        			
        			if(event.zaloguj(dialogLogowania.getLogin(), dialogLogowania.getPassword())){
        				
        				if(event.getRestriction()==KindRestriction.LOGED_R){
        				Parishioner p = event.getParishioner();
        				lblUserName.setText(p.getName()+" "+p.getSurName());
        				}
        				
        				if (event.getRestriction()>=KindRestriction.WORKS_R){
        					Priest pr = event.getPriest();
        					lblUserName.setText(pr.getName()+" "+pr.getSurName());
        				}
        				//JOptionPane.showMessageDialog(null, "Zalogowano jako: "+p.getName()+" "+p.getSurName()+"\nAdres: "+p.getAdress().getCity()+"\nPesel: "+p.getPesel());
        				owner.loginUser();
        				cl.show(ja, "logged");
        			}
        		}
        	}
        });
	}
}
