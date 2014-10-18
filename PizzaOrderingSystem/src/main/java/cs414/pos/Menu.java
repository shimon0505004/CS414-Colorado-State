package cs414.pos;

import java.util.HashSet;
import java.util.Set;

public class Menu {

    private String menuName, menuDescription;
    Set<Item> menuItemsSet;

    public Menu(String name, String desc) {
        this.menuName        = name;
        this.menuDescription = desc;
        this.menuItemsSet = new HashSet<>();
    }

    public String getMenuName()         { return this.menuName; }
    public String getMenuDescription()  { return this.menuDescription; }
    public Set<Item> getMenuItems()     { return this.menuItemsSet; }

    public boolean deleteItem(Item i) {
        return menuItemsSet.remove(i);
    }
}
