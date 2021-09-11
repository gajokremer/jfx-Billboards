package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrDep {

	private int totalBillboards = 0;
	private List<Billboard> billboards;
	
	public String BILLBOARD_BIN_FILE = "data/billboard.bin";
	public String BILLBOARD_CSV_LIST = "data/BillboardDataExported.csv";
	public String DANGEROUS_BILLBOARDS_REPORT = "data/DangerReport.txt";

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
	}

	public boolean addBillboard(Billboard newBillboard) {
		
		if(billboards.add(newBillboard)) {
			
			totalBillboards++;
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	public void saveData() throws FileNotFoundException, IOException {

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_BIN_FILE));
		oos.writeObject(billboards);
		oos.close();
	}
	
	public void importData() throws IOException {
		
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
	
	public void exportDangerousBillboards() throws IOException {

		FileWriter fw = new FileWriter(DANGEROUS_BILLBOARDS_REPORT, false);

		fw.write("=============================\n" + 
				" DANGEROUS BILLBOARDS REPORT\n" + 
				"=============================\n\n" +
				"The dangerous Billboards are: \n\n");

		for(int i = 0; i < billboards.size(); i++) {

			Billboard aBillboard = billboards.get(i);

			double w = Double.parseDouble(aBillboard.getWidth());
			double h = Double.parseDouble(aBillboard.getHeight());

			if(aBillboard.calculateArea(w, h) >= 160) {

				int j = i + 1;
				fw.write("	" + j + ". " + "Billboards " + aBillboard.getBrand() + 
						" with an area of " + aBillboard.calculateArea(w, h) + " cm2\n\n");
			}
		}

		fw.close();
	}
}