package cs414.a5.eid;

import java.io.Serializable;

public class Address implements Serializable {

	private String location;
	private AddressType addressType;
	
	/**
	 * 
	 */
	public Address() {
		// TODO Auto-generated constructor stub
		this.setLocation("");
		this.setAddressType(AddressType.Unknown);
	}

	/**
	 * 
	 * @param address
	 * @param type
	 */
	public Address(String address, AddressType type) {
		this.setLocation(address);
		this.setAddressType(type);		
	}
	
	/**
	 * 
	 * @param address
	 */
	public Address(String address) {
		this.setLocation(address);
		this.setAddressType(AddressType.Unknown);		
	}
	
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		if(location!=null){
			this.location = location;			
		}
		else{
			this.location = "";			
		}
	}


	/**
	 * @return the addressType
	 */
	public AddressType getAddressType() {
		return addressType;
	}


	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(AddressType addressType) {
		if(addressType!=null){
			this.addressType = addressType;			
		}else{
			this.addressType = AddressType.Unknown;			
		}
	}
	

}
