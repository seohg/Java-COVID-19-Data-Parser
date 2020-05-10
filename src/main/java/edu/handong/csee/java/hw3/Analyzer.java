package edu.handong.csee.java.hw3;

public class Analyzer {
	
	private String[][] newData;
	private String[] column;
	private String[] row;
	private int NumberOfCountries;
	private int NumberOfPatients;
	private int NumberOfAllPatients;
	
	public Analyzer(String[] data){
		NumberOfCountries=data.length-1;
		column=data[0].split(",");
		row=new String[data.length];
		newData=new String[data.length][column.length];
		
		for(int j=0;j<column.length;j++) {
			newData[0][j]=column[j];
		}
		for(int i=1;i<data.length;i++) {
			row=data[i].split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
			for(int j=0;j<column.length;j++) {
				if(row[j].startsWith("\"")) {
					row[j]=row[j].substring(1, row[j].length()-1);
				}
				newData[i][j]=row[j];
			}
		}
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<column.length;j++) {
				System.out.printf("%s ",newData[i][j]);
			}
			System.out.println();
		}
	}
	
	public int getNumberOfCountries(){
		return NumberOfCountries;
	}
	
	public int getNumberOfAllPatients(){
		for(int i=1;i<=NumberOfCountries;i++) {
			for(int j=4;j<column.length;j++) {
				NumberOfAllPatients+=Util.convertStringToInteger(newData[i][j]);
			}
		}
		return NumberOfAllPatients;
	}
	
	public int getNumberOfPatientsOfACountry(String countryName){
		int idx=0;
		
		for(int i=1;i<=NumberOfCountries;i++) {
			if(countryName.equals(newData[i][1])) {
				idx=i;
			}
		}
		if(idx==0) {
			return -1;
		}
		
		NumberOfPatients=0;
		for(int j=4;j<column.length;j++) {
			NumberOfPatients+=Util.convertStringToInteger(newData[idx][j]);
		}
		
		return NumberOfPatients;
	}
	
	
	public int getNumberOfPatientsFromASpecifiedDate(String date){
		int idx=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx==0) {
			return -1;
		}
		
		NumberOfPatients=0;
		for(int i=1;i<=NumberOfCountries;i++) {
			for(int j=4;j<=idx;j++) {
				NumberOfPatients+=Util.convertStringToInteger(newData[i][j]);
			}
		}
		
		return NumberOfPatients;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date){
		int idx=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx==0) {
			return -1;
		}
		
		NumberOfPatients=0;
		for(int i=1;i<=NumberOfCountries;i++) {
			for(int j=4;j<idx;j++) {
				NumberOfPatients+=Util.convertStringToInteger(newData[i][j]);
			}
		}
		
		return NumberOfPatients;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String fdate,String sdate){
		int fidx=0;
		int sidx=0;
		for(int j=4;j<column.length;j++) {
			if(fdate.equals(newData[0][j])) {
				fidx=j;
			}
			if(sdate.equals(newData[0][j])) {
				sidx=j;
			}
		}
		
		if(fidx==0||sidx==0) {
			return -1;
		}
		
		NumberOfPatients=0;
		for(int i=1;i<=NumberOfCountries;i++) {
			for(int j=fidx;j<=sidx;j++) {
				NumberOfPatients+=Util.convertStringToInteger(newData[i][j]);
			}
		}
		
		return NumberOfPatients;
	}
	
}
