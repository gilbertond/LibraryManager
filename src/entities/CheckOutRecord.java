package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CheckOutRecordEntry> checkOutEntrys;

	public CheckOutRecord(CheckOutRecordEntry checkOutEntry) {
		this.checkOutEntrys = new ArrayList<>();
		this.checkOutEntrys.add(checkOutEntry);
		// save the CheckOutRecord object in the checkOutRecord.txt.

	}
        
        public CheckOutRecord(){
            
        }

	public List<CheckOutRecordEntry> getCheckOutEntrys() {
		return checkOutEntrys;
	}

	public void setCheckOutEntrys(List<CheckOutRecordEntry> checkOutEntrys) {
		this.checkOutEntrys = checkOutEntrys;
	}

	/*
	 * Accessory methods.
	 */

}