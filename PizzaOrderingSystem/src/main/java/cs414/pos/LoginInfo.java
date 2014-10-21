/**
 * 
 */
package cs414.pos;

/**
 * @author SHAIKHSHAWON
 *
 */
public class LoginInfo {

	/**
	 * 
	 */
	private String loginId;
	private String password;
	private Employee loginEmployee;
	
	public LoginInfo(String ID, String PW) {
		// TODO Auto-generated constructor stub
		setLoginId(ID);
		setPassword(PW);
		setLoginEmployee(null);
	}
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the loginEmployee
	 */
	public Employee getLoginEmployee() {
		return loginEmployee;
	}
	/**
	 * @param loginEmployee the loginEmployee to set
	 */
	public void setLoginEmployee(Employee loginEmployee) {
		this.loginEmployee = loginEmployee;
	}

	public boolean matchPassword(String password){
		return this.getPassword().equals(password);
	}
	
}
