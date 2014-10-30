package cs414.pos.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private JButton employeeButton;

	public MainUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Pizza Ordering System");
		editMenuItemButton = new JButton("Manage Menu Item");
		editMenuButton = new JButton("Manage Menu");
		placeOrderButton = new JButton("Place Order");
		completeOrderButton = new JButton("Complete Order");
		employeeButton = new JButton("Manage Employees");

		layoutComponents();

		addListeners();

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

<<<<<<< HEAD
<<<<<<< HEAD
	public void setCanManageEmployee(boolean canManageEmployee) {
		employeeButton.setEnabled(canManageEmployee);
	}

	public void setCanEditEmployee(boolean canEditMenu) {
		employeeButton.setEnabled(canEditMenu);
	}

=======

	public void setCanManageEmployee(boolean canManageEmployee){
		employeeButton.setEnabled(canManageEmployee);
	}
=======

	public void setCanManageEmployee(boolean canManageEmployee){
		employeeButton.setEnabled(canManageEmployee);
	}
>>>>>>> FETCH_HEAD
	public void setCanEditEmployee(boolean canEditMenu) {
		employeeButton.setEnabled(canEditMenu);
	}
		
	
>>>>>>> FETCH_HEAD
	private void layoutComponents() {
		GridLayout layout = new GridLayout(4, 1);
		layout.setHgap(5);
		layout.setVgap(5);
		frame.setLayout(layout);

		frame.add(employeeButton);
		frame.add(editMenuItemButton);
		frame.add(editMenuButton);
		frame.add(placeOrderButton);
		frame.add(completeOrderButton);
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeMain();
			}
		});
		employeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayEmployee();
			}
		});
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
