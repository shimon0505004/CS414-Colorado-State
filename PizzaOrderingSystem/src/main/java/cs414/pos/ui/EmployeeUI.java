package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class EmployeeUI {
	private UIController controller;
	private JFrame frame;
	private JScrollPane employeeScrollPane;
	private JList<String> employeeList;
	private JPanel buttonPanel;
	private JButton createButton;
	private JButton editButton;
	private JButton deleteButton;

	public EmployeeUI(UIController c) {
		this.controller = c;
	}

	public void init() {
		frame = new JFrame("Employee Management");
		employeeScrollPane = new JScrollPane();
		employeeList = new JList<>();
		buttonPanel = new JPanel();
		createButton = new JButton("Create Employee");
		editButton = new JButton("Edit Employee");
		deleteButton = new JButton("Delete Employee");

		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	public void updateEmployees() {
		setEmployees(controller.getEmployees());
	}

	public void setEmployees(Iterable<String> employees) {
		DefaultListModel<String> model = (DefaultListModel<String>) employeeList
				.getModel();
		model.removeAllElements();
		for (String employee : employees) {
			model.addElement(employee);
		}
	}

	public void layoutComponents() {
		frame.setLayout(new BorderLayout());
		frame.add(employeeScrollPane, BorderLayout.CENTER);
		employeeScrollPane.setViewportView(employeeList);
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeList.setModel(new DefaultListModel<String>());
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 3, 3));
		buttonPanel.add(createButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);

	}

	public void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeEmployee();
			}
		});
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createEmployeeAction();
			}
		});
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editEmployeeAction();
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteEmployeeAction();
			}
		});
	}

	private void createEmployeeAction() {
		String name = JOptionPane.showInputDialog("Enter employee name:");
		if (name == null) {
			return;
		}
		String[] roleList = { "Manager", "Chef", "Cashier" };
		String loginID = JOptionPane.showInputDialog("Set the loginID:");
		String role = (String) JOptionPane.showInputDialog(null,
				"Choose the role", "Role", JOptionPane.QUESTION_MESSAGE, null,
				roleList, roleList[1]);
		String password = JOptionPane.showInputDialog("Set the password:");

		boolean success = controller.createEmployee(name, loginID, password,
				role);

		if (!success) {
			JOptionPane.showMessageDialog(frame,
					"Error creating employee. Try a different login ID.");
			return;
		}
		updateEmployees();
	}

	private void editEmployeeAction() {
		String employeeString = verifySelected();
		if (employeeString == null)
			return;
		String employeeLoginID = controller.getEmployeeLoginID(employeeString);

		String name = JOptionPane.showInputDialog("Enter employee name:");
		if (name == null) {
			return;
		}
		String[] roleList = { "Manager", "Chef", "Cashier" };
		String loginID = JOptionPane.showInputDialog("Set the loginID:");
		String role = (String) JOptionPane.showInputDialog(null,
				"Choose the role", "Role", JOptionPane.QUESTION_MESSAGE, null,
				roleList, roleList[1]);
		String password = JOptionPane.showInputDialog("Set the password:");

		boolean success = controller.editEmployee(loginID, name, loginID,
				password, role);

		if (!success) {
			JOptionPane.showMessageDialog(frame,
					"Error editing employee. Please try a different loginID.");
			return;
		}

		updateEmployees();
	}

	private void deleteEmployeeAction() {
		//String 
	}

	
	private String verifySelected() {
		String employee = employeeList.getSelectedValue();
		if (employee == null) {
			JOptionPane.showMessageDialog(frame, "Please select an item.");
		}
		return employee;
	}

	public static void main(String[] args){
		final EmployeeUI view = new EmployeeUI(null);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
				view.frame.removeWindowListener(view.frame.getWindowListeners()[0]);
				view.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				});
			}
		});
	}
}
