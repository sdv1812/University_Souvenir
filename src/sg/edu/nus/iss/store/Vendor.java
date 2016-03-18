package sg.nus.iss.ft6.domain;

public class Vendor {
	private String vendorName;
	private String description;
	
	public Vendor(String vendorName,String description){
		this.description=description;
		this.vendorName=vendorName;
	}
	
	public void setVendorName(String vendorName){
		this.vendorName=vendorName;
	}
	
	public String getVendorName(){
		return vendorName;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public String getDescription(){
		return description;
	}
}
