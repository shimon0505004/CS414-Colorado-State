package cs414.pos.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Nathan Lighthart
 */
public class CompleteOrderUI {
	private UIController controller;
	private JFrame frame;
	private JComboBox<String> orderComboBox;
	private JButton completeOrderButton;
	private JButton closeButton;

	public CompleteOrderUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Complete Order");
		orderComboBox = new JComboBox<>();
		completeOrderButton = new JButton("Complete Order");
		closeButton = new JButton("Close");

		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(300, 100));
		frame.pack();
		frame.setResizable(false);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	private void layoutComponents() {
		frame.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints;

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 3, 5);
		frame.add(orderComboBox, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(2, 5, 5, 5);
		frame.add(completeOrderButton, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(2, 5, 5, 5);
		frame.add(closeButton, gridBagConstraints);
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); // temporary close action should be done
			}
		});
		completeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

	public static void main(String[] args) {
		final UIController controller = new UIController();
		final CompleteOrderUI view = new CompleteOrderUI(controller);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
			}
		});
	}
}
