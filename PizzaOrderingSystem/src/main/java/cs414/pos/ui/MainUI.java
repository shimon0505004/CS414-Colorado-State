package cs414.pos.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Nathan Lighthart
 */
public class MainUI {
	private UIController controller;
	private JFrame frame;
	private JButton editMenuItemButton;
	private JButton editMenuButton;
	private JButton placeOrderButton;
	private JButton completeOrderButton;

	public MainUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Pizza Ordering System");
		editMenuItemButton = new JButton("Edit Menu Item");
		editMenuButton = new JButton("Edit Menu");
		placeOrderButton = new JButton("Place Order");
		completeOrderButton = new JButton("Complete Order");

		layoutComponents();

		addListeners();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 300));
		frame.pack();
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	public void setCanEditMenu(boolean canEditMenu) {
		editMenuButton.setEnabled(canEditMenu);
		editMenuItemButton.setEnabled(canEditMenu);
	}

	public void setCanPlaceOrder(boolean canPlaceOrder) {
		placeOrderButton.setEnabled(canPlaceOrder);
	}

	public void setCanCompleteOrder(boolean canCompleteOrder) {
		completeOrderButton.setEnabled(canCompleteOrder);
	}

	private void layoutComponents() {
		GridLayout layout = new GridLayout(4, 1);
		layout.setHgap(5);
		layout.setVgap(5);
		frame.setLayout(layout);

		frame.add(editMenuItemButton);
		frame.add(editMenuButton);
		frame.add(placeOrderButton);
		frame.add(completeOrderButton);
	}

	private void addListeners() {
		editMenuItemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayEditMenuItem();
			}
		});
		editMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayEditMenu();
			}
		});
		placeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayPlaceOrder();
			}
		});
		completeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayCompleteOrder();
			}
		});
	}

	// Used to view the interface with nothing working
	public static void main(String[] args) {
		final MainUI view = new MainUI(null);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
			}
		});
	}
}
