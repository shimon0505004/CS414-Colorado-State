/**
 * 
 */
package cs414.pos;

/**
 * @author SHAIKHSHAWON
 *
 */
public abstract class Role {
	
	private String roleName;
	private String roleDescription;

	
	
	Role(String roleName, String Description){
		setRoleName(roleName);
		setRoleDescription(Description);
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	
	
}
