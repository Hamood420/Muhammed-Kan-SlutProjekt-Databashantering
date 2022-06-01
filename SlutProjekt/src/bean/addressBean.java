package bean;

public class addressBean {
	private String _address;
	private String _state;
	private String _postalcode;
	private int _id;
	
	
	public void setAddress(String address) {
		this._address = address;
	}
	
	public String getAddress() {
		return _address;
	}
	
	public void setState(String state) {
		this._state = state;
	}
	
	public String getState() {
		return _state;
	}
	
	public void setPostalCode(String postalcode) {
		this._postalcode = postalcode;
	}
	
	public String getPostalCode() {
		return _postalcode;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public int getId() {
		return _id;
	}
	
	public String toJson() {
		String pattern = "{ \"Address\": \"%s\", \"Postal Code\": \"%s\", \"State\": \"%s\" }";
		String returnString = String.format(pattern, this._address, this._postalcode, this._state);
		
		return returnString;
	}
	
	public String toString() {
		String pattern = "Address = %s, Postal Code = %s, State = %s";
		String returnString = String.format(pattern, this._postalcode, this._state, this._address);
		
		return returnString;
	}
}