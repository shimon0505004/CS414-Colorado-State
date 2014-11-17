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
import javax.swing.JOptionPane;

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
	private JButton viewCustomersButton;
	private JButton freePizzaButton;
	private JButton createAccountButton;
	private JButton deliverOrderButton;

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
		viewCustomersButton = new JButton("View Customers");
		freePizzaButton = new JButton("Configure Free Pizza Points");
		createAccountButton = new JButton("Create Account");
		deliverOrderButton = new JButton("Deliver Order");

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
		freePizzaButton.setEnabled(canEditMenu);
	}

	public void setCanPlaceOrder(boolean canPlaceOrder) {
		placeOrderButton.setEnabled(canPlaceOrder);
		createAccountButton.setEnabled(canPlaceOrder);
	}

	public void setCanCompleteOrder(boolean canCompleteOrder) {
		completeOrderButton.setEnabled(canCompleteOrder);
	}

	public void setCanViewCustomers(boolean canViewCustomers) {
		viewCustomersButton.setEnabled(canViewCustomers);
	}

	public void setCanManageEmployee(boolean canManageEmployee) {
		employeeButton.setEnabled(canManageEmployee);
	}

	public void setCanEditEmployee(boolean canEditMenu) {
		employeeButton.setEnabled(canEditMenu);
	}

	public void setCanDeliverOrder(boolean canDeliverOrder) {
		deliverOrderButton.setEnabled(canDeliverOrder);
	}

	private void layoutComponents() {
		GridLayout layout = new GridLayout(0, 2);
		layout.setHgap(5);
		layout.setVgap(5);
		frame.setLayout(layout);

		frame.add(createAccountButton);
		frame.add(placeOrderButton);
		frame.add(employeeButton);
		frame.add(viewCustomersButton);
		frame.add(editMenuItemButton);
		frame.add(editMenuButton);
		frame.add(freePizzaButton);
		frame.add(completeOrderButton);
		frame.add(deliverOrderButton);
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
		viewCustomersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayCustomers();
			}
		});
		freePizzaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configureFreePizzaAction();
			}
		});
		createAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createAccountAction();
			}
		});
		deliverOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.displayDeliverOrder();
			}
		});
	}

	private void configureFreePizzaAction() {
		String message = "Please enter the amount of minimum points required for a free pizza.\n The current number required is " + controller.getRequiredPointForFreePizzaCertificate() + ".";
		String valueString = JOptionPane.showInputDialog(frame, message);
		if(valueString == null) {
			return;
		}
		int value;
		try {
			value = Integer.parseInt(valueString);
			if(value < 1) {
				throw new NumberFormatException();
			}
		} catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(frame, "The value enter is not a positive integer. Please Try again.");
			return;
		}
		controller.setRequiredPointForFreePizzaCertificate(value);
		controller.saveToFile();
	}

	private void createAccountAction() {
		String nameString = JOptionPane.showInputDialog(frame, "Please enter your name (last, first):");
		if(nameString == null) {
			JOptionPane.showMessageDialog(frame, "User Account creation cancelled");
			return;
		}
		String[] nameSplit = nameString.split(",");
		if(nameSplit.length != 2) {
			JOptionPane.showMessageDialog(frame, "Invalid name format. Please try again.");
			return;
		}
		String phoneNumber = JOptionPane.showInputDialog(frame, "Please enter your phone number:");
		if(phoneNumber == null) {
			JOptionPane.showMessageDialog(frame, "User Account creation cancelled");
			return;
		}
		String id = controller.createAccount(nameSplit[1].trim(), nameSplit[0].trim(), phoneNumber);
		controller.saveToFile();
		JOptionPane.showMessageDialog(frame, "Customer account creation successful. Your membership ID is " + id + ".");
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
