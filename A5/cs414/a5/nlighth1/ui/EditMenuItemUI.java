package cs414.a5.nlighth1.ui;

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
public class EditMenuItemUI {
    private UIController controller;
    private JFrame frame;
    private JScrollPane itemScrollPane;
    private JList<String> itemList;
    private JPanel buttonPanel;
    private JButton createButton;
    private JButton deleteButton;
    private JButton changeNameButton;
    private JButton changeDescriptionButton;
    private JButton specialButton;
    private JButton editPriceButton;

    public EditMenuItemUI(UIController controller) {
        this.controller = controller;
    }

    public void init() {
        frame = new JFrame("Edit Menu Item");
        itemScrollPane = new JScrollPane();
        itemList = new JList<>();
        buttonPanel = new JPanel();
        createButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        changeNameButton = new JButton("Change Name");
        changeDescriptionButton = new JButton("Change Description");
        specialButton = new JButton("Mark/Unmark Special");
        editPriceButton = new JButton("Change Price");
        layoutComponents();

        addListeners();

        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
    }

    public void setVisible(boolean visible) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }

    public void updateItems() {
        setMenuItems(controller.getAllFullMenuItems());

    }

    public void setMenuItems(Iterable<String> items) {
        DefaultListModel<String> model = (DefaultListModel<String>) itemList
                .getModel();
        model.removeAllElements();
        for(String item : items) {
            model.addElement(item);
        }
    }

    private void layoutComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(itemScrollPane, BorderLayout.CENTER);
        itemScrollPane.setViewportView(itemList);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setModel(new DefaultListModel<String>());
        frame.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 3, 3));
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(changeNameButton);
        buttonPanel.add(changeDescriptionButton);
        buttonPanel.add(editPriceButton);
        buttonPanel.add(specialButton);
    }

    private void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.closeEditMenuItem();
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItemAction();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItemAction();
            }
        });
        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeNameAction();
            }
        });
        editPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePriceAction();
            }
        });
        changeDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDescriptionAction();
            }
        });
        specialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialAction();
            }
        });

    }

    private void createItemAction() {
        String name = JOptionPane.showInputDialog("Enter item name:");
        if(name == null) {
            return; // cancel
        }
        String description = JOptionPane
                .showInputDialog("Enter item description:");
        if(description == null) {
            return; // cancel
        }
        String priceString = JOptionPane.showInputDialog("Enter item price:");
        if(priceString == null) {
            return; // cancel
        }
        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid item price");
            return;
        }

        boolean success = controller.createMenuItem(name, description, price);

        if(!success) {
            JOptionPane.showMessageDialog(frame,
                    "Error creating item. Please try a different name.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Item successfully edited.");
        updateItems();
    }

    private void deleteItemAction() {
        String itemString = verifySelected();
        if(itemString == null) {
            return;
        }
        String itemName = controller.getItemName(itemString);
        if(controller.deleteMenuItem(itemName)) {
            JOptionPane.showMessageDialog(null, "Item deleted.");
        }
        updateItems();
    }

    private void changeNameAction() {
        String itemString = verifySelected();
        if(itemString == null) {
            return;
        }
        String itemName = controller.getItemName(itemString);
        String name = JOptionPane.showInputDialog("Enter item name:");
        if(name == null) {
            return; // cancel
        }

        boolean success = controller.changeMenuItemName(itemName, name);
        if(!success) {
            JOptionPane.showMessageDialog(frame,
                    "Error changing name. Please try a different name.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Item name changed.");
        updateItems();
    }

    private void changeDescriptionAction() {
        String itemString = verifySelected();
        if(itemString == null) {
            return;
        }
        String itemName = controller.getItemName(itemString);
        String description = JOptionPane
                .showInputDialog("Enter item description:");
        if(description == null) {
            return; // cancel
        }

        controller.changeMenuItemDescription(itemName, description);
        JOptionPane.showMessageDialog(null, "Item description changed.");
        updateItems();
    }

    private void changePriceAction() {
        String itemString = verifySelected();
        if(itemString == null) {
            return;
        }
        String itemName = controller.getItemName(itemString);
        String priceString = JOptionPane.showInputDialog("Enter Price:");
        if(priceString == null) {
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid item price");
            return;
        }
        controller.changeItemPrice(itemName, price);
        JOptionPane.showMessageDialog(null, "Item price changed.");
        updateItems();
    }

    private void specialAction() {
        String itemString = verifySelected();
        if(itemString == null) {
            return;
        }
        String itemName = controller.getItemName(itemString);
        controller.changeMenuItemSpecial(itemName);
        JOptionPane.showMessageDialog(null, "Item set special.");
        updateItems();
    }

    private String verifySelected() {
        String item = itemList.getSelectedValue();
        if(item == null) {
            JOptionPane.showMessageDialog(frame, "Please select an item.");
        }
        return item;
    }

    // Used to view the interface with nothing working
    public static void main(String[] args) {
        final EditMenuItemUI view = new EditMenuItemUI(null);

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
