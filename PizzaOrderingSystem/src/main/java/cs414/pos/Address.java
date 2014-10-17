package cs414.pos;

public class Address {

	private String location;
	private AddressType addressType;
	
	
	public Address() {
		// TODO Auto-generated constructor stub
		this.setLocation("");
		this.setAddressType(AddressType.Unknown);
	}


	public Address(String address, AddressType type) {
		this.setLocation(address);
		this.setAddressType(type);		
	}
	
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
		this.location = location;
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
		this.addressType = addressType;
	}

}
