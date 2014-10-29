package cs414.pos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Serializable {

    private String menuName, menuDescription;

	private Set<Item> menuItemsSet;

	
    public void setMenuName(String menuName) {
		this.menuName = menuName;
	}	
    /**
     *
     * @param name
     * @param desc
     */
    public Menu(String name, String desc) {
        setMenuName(name);
        setMenuDescription(desc);
        this.menuItemsSet = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getMenuName(){
    	return this.menuName; 
    }

    /**
     *
     * @return
     */
    public String getMenuDescription(){
    	return this.menuDescription; 
    }

    /**
     *
     * @return
     */
    public Set<Item> getMenuItems(){
    	return this.menuItemsSet; 
    }

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
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
}
