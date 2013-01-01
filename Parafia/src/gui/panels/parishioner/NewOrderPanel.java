package gui.panels.parishioner;

import gui.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class NewOrderPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NewOrderPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblOrder = new JLabel("Typ Zamowienia:");
		lblOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox cbOrder = new JComboBox();
		cbOrder.setModel(new DefaultComboBoxModel(new String[] {"<wybierz typ>", "Chrzest", "\u015Alub", "Pogrzeb", "Msza \u015Awi\u0119ta", "Wypominki", "inne"}));
		
		JLabel lblDateStart = new JLabel("Data Pocz\u0105tku:");
		lblDateStart.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JDateChooser pDateStart = new JDateChooser();
		pDateStart.getSpinner().setEnabled(false);
		
		JLabel lblIlo = new JLabel("Ilo\u015B\u0107:");
		lblIlo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JSpinner spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		
		JButton btnSend = new JButton("Wy\u015Blij");
		
		JButton btnReset = new JButton("Reset");
		
		JLabel lblWiadomo = new JLabel("Wiadomo\u015B\u0107:");
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(190)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
								.addComponent(lblWiadomo, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))))
					.addGap(40))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOrder, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbOrder, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblIlo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDateStart, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pDateStart, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrder, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbOrder, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDateStart, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(pDateStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIlo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(lblWiadomo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnReset)
						.addComponent(btnSend)))
		);
		
		JTextPane textPane = new JTextPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
