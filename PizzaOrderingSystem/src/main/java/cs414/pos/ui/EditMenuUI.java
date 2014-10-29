package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Nathan Lighthart
 */
public class EditMenuUI {
	private UIController controller;
	private JFrame frame;
	private JComboBox<String> menuComboBox;
	private JScrollPane menuItemScrollPane;
	private JList<String> menuItemList;
	private JPanel buttonPanel;
	private JButton createButton;
	private JButton deleteButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton editButton;
	
	public EditMenuUI(UIController controller) {
		this.controller = controller;
	}

	public void init() {
		frame = new JFrame("Edit Menu");
		menuComboBox = new JComboBox<>();
		menuItemScrollPane = new JScrollPane();
		menuItemList = new JList<>();
		buttonPanel = new JPanel();
		createButton = new JButton("Create Menu");
		editButton = new JButton("Edit Menu");
		deleteButton = new JButton("Delete Menu");
		addButton = new JButton("Add Item");
		removeButton = new JButton("Remove Item");

		
		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
	}

	public void updateMenus() {
		setMenus(controller.getMenus());
		if(menuComboBox.getModel().getSize() == 0) {
			((DefaultListModel<String>) menuItemList.getModel()).removeAllElements();
		}
	}

	public void setMenus(Iterable<String> menus) {
		menuComboBox.removeAllItems();
		for(String menu : menus) {
			menuComboBox.addItem(menu);
		}
	}

	public void setMenuItems(Iterable<String> items) {
		DefaultListModel<String> model = (DefaultListModel<String>) menuItemList.getModel();
		model.removeAllElements();
		for(String item : items) {
			model.addElement(item);
		}
	}

	public void setVisible(boolean visible) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}

	private void layoutComponents() {
		frame.setLayout(new BorderLayout());
		frame.add(menuComboBox, BorderLayout.NORTH);
		frame.add(menuItemScrollPane, BorderLayout.CENTER);
		menuItemScrollPane.setViewportView(menuItemList);
		menuItemList.setModel(new DefaultListModel<String>());
		menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(createButton);

		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeEditMenu();
			}
		});
		menuComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadItemsAction();
			}
		});
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createMenuAction();
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMenuAction();
			}
		});
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItemAction();
			}
		});
		
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				editMenuAction();
			}
		});
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItemAction();
			}
		});
	}

	private void loadItemsAction() {
		if(menuComboBox.getModel().getSize() == 0) {
			return; // do nothing as nothing to load
		}
		String menu = (String) menuComboBox.getSelectedItem();
		Iterable<String> menuItems = controller.getFullMenuItems(menu);
		setMenuItems(menuItems);
	}

	private void createMenuAction() {
		String name = JOptionPane.showInputDialog("Enter menu name:");
		String description = JOptionPane.showInputDialog("Enter menu description:");

		boolean success = controller.createMenu(name, description);
		if(!success) {
			JOptionPane.showMessageDialog(frame, "Error creating menu. Please try a different name.");
			return;
		}

		updateMenus();
		menuComboBox.setSelectedItem(name);
	}

	private void editMenuAction(){
		String name = JOptionPane.showInputDialog("Enter new menu name:");
		String description = JOptionPane.showInputDialog("Enter new description:");
		String menu = (String) menuComboBox.getSelectedItem();
		
		boolean success = controller.editMenu(menu, name, description);
		if(!success){
			JOptionPane.showMessageDialog(frame, "Error editing menu. Please try a different name.");
			return;
		}
		
		updateMenus();
		menuComboBox.setSelectedItem(name);
	}
	
	private void deleteMenuAction() {
		if(menuComboBox.getModel().getSize() == 0) {
			return; // do nothing as nothing to load
		}
		String menu = (String) menuComboBox.getSelectedItem();
		controller.deleteMenu(menu);
		updateMenus();
	}

	private void addItemAction() {
		if(menuComboBox.getModel().getSize() == 0) {
			return; // do nothing as nothing to load
		}
		String menu = (String) menuComboBox.getSelectedItem();
		Iterable<String> menuItems = controller.getMenuItemsNotOnMenu(menu);
		List<String> items = new ArrayList<>();
		for(String item : menuItems) {
			items.add(item);
		}
		if(items.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No menu items available to be added.");
			return;
		}
		String selectedItem = (String) JOptionPane.showInputDialog(frame, "Please select an Item.", "Item Selector", JOptionPane.PLAIN_MESSAGE, null, items.toArray(), items.get(0));
		if(selectedItem == null) {
			return; // cancel
		}
		controller.addMenuItem(menu, selectedItem);
		loadItemsAction();
	}

	private void removeItemAction() {
		if(menuComboBox.getModel().getSize() == 0) {
			return; // do nothing as nothing to load
		}
		String menu = (String) menuComboBox.getSelectedItem();
		String itemString = menuItemList.getSelectedValue();
		if(itemString == null) {
			JOptionPane.showMessageDialog(frame, "Please select an item to remove.");
			return;
		}
		String itemName = controller.getItemName(itemString);
		controller.removeMenuItem(menu, itemName);
		loadItemsAction();
	}

	// Used to view the interface with nothing working
	public static void main(String[] args) {
		final EditMenuUI view = new EditMenuUI(null);

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
