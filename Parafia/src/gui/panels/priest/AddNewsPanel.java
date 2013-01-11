package gui.panels.priest;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class AddNewsPanel extends JPanel implements ActionListener {
	private JTextField textTitle;

	/**
	 * Create the panel.
	 */
	public AddNewsPanel(JFrame owner) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		JButton btnSubmit = new JButton("Dodaj");
		btnSubmit.addActionListener(this);
		
		JButton btnReset = new JButton("Wyczy\u015B\u0107");
		btnReset.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(17))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSubmit))
					.addContainerGap())
		);
		
		JEditorPane editorContent = new JEditorPane();
		scrollPane.setViewportView(editorContent);
		
		JLabel lblNowaAktualno = new JLabel("Nowa Aktualno\u015B\u0107");
		lblNowaAktualno.setHorizontalAlignment(SwingConstants.CENTER);
		lblNowaAktualno.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblTitle_ = new JLabel("Tytu\u0142:");
		lblTitle_.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		
		JLabel lblContent = new JLabel("Tre\u015B\u0107:");
		lblContent.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNowaAktualno, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(70)
							.addComponent(lblTitle_, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
						.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(70))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNowaAktualno)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitle_))
							.addContainerGap(20, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblContent, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
