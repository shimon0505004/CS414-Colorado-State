package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathan Lighthart
 */
public class CustomerInfoUI {

	private UIController controller;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel bottomPanel;
	private JButton closeButton;

	public CustomerInfoUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Customer Information");
		scrollPane = new JScrollPane();
		table = new JTable();
		bottomPanel = new JPanel();
		closeButton = new JButton("Close");

		layoutComponents();

		addListeners();

		frame.pack();
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	public void updateInformation() {
		table.setModel(new DefaultTableModel(controller.getCustomerData(), controller.getCustomerColumnNames()) {
			private static final long serialVersionUID = -4082996669139353200L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
	}

	private void layoutComponents() {
		frame.setLayout(new BorderLayout());
		frame.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		table.getTableHeader().setReorderingAllowed(false);

		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		bottomPanel.add(closeButton);
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeCustomerInformation();
			}
		});
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.closeCustomerInformation();
			}
		});
	}

	// Used to view the interface with nothing working
	public static void main(String[] args) {
		final CustomerInfoUI view = new CustomerInfoUI(null);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
				view.frame.removeWindowListener(view.frame.getWindowListeners()[0]);
				view.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
	}
}
