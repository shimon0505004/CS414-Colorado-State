package cs414.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		deleteButton = new JButton("Delete Menu");
		addButton = new JButton("Add Item");
		removeButton = new JButton("Remove Item");

		layoutComponents();

		addListeners();

		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
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
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(createButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
	}

	private void addListeners() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); // temporary cancel action should be done
			}
		});
		menuComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

	public static void main(String[] args) {
		final UIController controller = new UIController();
		final EditMenuUI view = new EditMenuUI(controller);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.init();
				view.setVisible(true);
			}
		});
	}

}
