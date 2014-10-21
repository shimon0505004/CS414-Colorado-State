package cs414.pos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Serializable {

    private String menuName, menuDescription;
    Set<Item> menuItemsSet;

    /**
     *
     * @param name
     * @param desc
     */
    public Menu(String name, String desc) {
        this.menuName        = name;
        this.menuDescription = desc;
        this.menuItemsSet = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getMenuName()         { return this.menuName; }

    /**
     *
     * @return
     */
    public String getMenuDescription()  { return this.menuDescription; }

    /**
     *
     * @return
     */
    public Set<Item> getMenuItems()     { return this.menuItemsSet; }

    /**
     *
     * @param i
     * @return
     */
    public boolean addItem(Item i) {
        return menuItemsSet.add(i);
    }

    /**
     *
     * @param i
     * @return
     */
    public boolean deleteItem(Item i) {
        return menuItemsSet.remove(i);
    }

    @Override public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return ((Menu) o).getMenuName().equals(this.getMenuName());
    }
}
