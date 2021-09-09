package model;

import java.util.List;

public class InfrastructureDepartment {

	private List<Billboard> billboards;
	
	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";

	public List<Billboard> getBillboards() {
		return billboards;
	}

	public void setBillboards(List<Billboard> billboards) {
		this.billboards = billboards;
	}

	public void addBillboard(double w, double h, boolean iu, String b) {
		
		
	}
	
	public void saveBillboards() {
		
	}
	
	public void loadBillboards() {
		
	}
	
	public void exportDangerousBillboards(String fn) {
		
	}
	
	public void importData(String fn) {
		
	}

	@Override
	public String toString() {
		return null;
	}
}
