/**
 * 
 */
package cs414.a5.eid;

import java.io.Serializable;

/**
 * @author SHAIKHSHAWON
 *
 */
public class LoginInfo implements Serializable {

	/**
	 * 
	 */
	private String loginId;
	private String password;

	public LoginInfo(String ID, String PW) {
		// TODO Auto-generated constructor stub
		setLoginId(ID);
		setPassword(PW);
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

	public boolean matchPassword(String password){
		return this.getPassword().equals(password);
	}
	
}
