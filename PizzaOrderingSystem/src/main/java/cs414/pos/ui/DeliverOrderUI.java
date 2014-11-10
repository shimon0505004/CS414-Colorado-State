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
import javax.swing.JOptionPane;

/**
 *
 * @author Nathan Lighthart
 */
public class DeliverOrderUI {

	private UIController controller;
	private JFrame frame;
	private JComboBox<String> orderComboBox;
	private JButton completeOrderButton;
	private JButton closeButton;

	public DeliverOrderUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Deliver Order");
		orderComboBox = new JComboBox<>();
		completeOrderButton = new JButton("Deliver Order");
		closeButton = new JButton("Close");

		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(300, 100));
		frame.pack();
		frame.setResizable(false);
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	public void updateOrders() {
		setOrderList(controller.getCompletedButUnderliveredOrders());
	}

	public void setOrderList(Iterable<String> orders) {
		orderComboBox.removeAllItems();
		for(String order : orders) {
			orderComboBox.addItem(order);
		}
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
				controller.closeDeliverOrder();
			}
		});
		completeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deliverOrderAction();
			}
		});
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.closeDeliverOrder();
			}
		});
	}

	private void deliverOrderAction() {
		if(orderComboBox.getModel().getSize() == 0) {
			return;
		}
		String orderString = (String) orderComboBox.getSelectedItem();
		int orderID = controller.getOrderID(orderString);
		if(controller.deliverOrder(orderID)) {
			JOptionPane.showMessageDialog(null, "Order " + orderID + " marked as delivered.");
			updateOrders();
		} else {
			JOptionPane.showMessageDialog(null, "Order " + orderID + " cannot be marked as delivered. Current employee does not have priviledge to complete order.");
		}
	}

	// Used to view the interface with nothing working
	public static void main(String[] args) {
		final DeliverOrderUI view = new DeliverOrderUI(null);

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
