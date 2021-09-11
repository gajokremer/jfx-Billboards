package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfrDep {

	private int totalBillboards = 0;
	private List<Billboard> billboards;
	
//	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	public String BILLBOARD_CSV_LIST = "data/BillboardDataExported.csv";

	public int getTotalBillboards() {
		return totalBillboards;
	}

	public void setTotalBillboards(int totalBillboards) {
		this.totalBillboards = totalBillboards;
	}

	public List<Billboard> getBillboards() {
		return billboards;
	}

	public void setBillboards(List<Billboard> billboards) {
		this.billboards = billboards;
	}
	
	public InfrDep() {
		
		billboards = new ArrayList<Billboard>();
//		billboards.add(new Billboard("200", "200", "True", "ICESI"));
	}

//	public boolean addBillboard(String w, String h, String iu, String b) {
//
//		Billboard newBillboard = new Billboard(w, h, iu, b);
//		
//		if(billboards.add(newBillboard)) {
//
//			return true;
//
//		} else {
//
//			return false;
//		}
//	}
	
	public boolean addBillboard(Billboard newBillboard) {
		
//		Billboard newBillboard = new Billboard(w, h, iu, b);
		
		if(billboards.add(newBillboard)) {
			
			totalBillboards++;
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public void saveBillboards() {
		
	}
	
	public void loadBillboards() {
		
	}
	
	public void exportDangerousBillboards(String fn) {
		
	}
	
	public void importData(/*String fn*/) throws IOException {
		
		billboards.removeAll(billboards);
		
		BufferedReader br = new BufferedReader(new FileReader(BILLBOARD_CSV_LIST));
		String line = br.readLine();
		
		while(line != null) {
			
			String [] parts = line.split("\\|");
			Billboard  newBillboard = new Billboard(parts[0], parts[1], parts[2], parts[3]);
			addBillboard(newBillboard);
			line = br.readLine();
		}
		
		br.close();
	}
	
	public void exportData() throws IOException {

		FileWriter fw = new FileWriter(BILLBOARD_CSV_LIST, false);
		
		for(int i = 0; i < billboards.size(); i++) {
			
			Billboard aBillboard = billboards.get(i);
			fw.write(aBillboard.getWidth() + "|" + aBillboard.getHeight() + "|" + 
					aBillboard.isInUse() + "|" + aBillboard.getBrand() + "\n");
		}
		
		fw.close();
	}

	@Override
	public String toString() {
		return null;
	}
}
