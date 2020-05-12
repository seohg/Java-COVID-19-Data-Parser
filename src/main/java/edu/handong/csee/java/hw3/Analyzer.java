package edu.handong.csee.java.hw3;

public class Analyzer {
	
	private String[][] newData;
	private String[] column;
	private String[] row;
	
	private int NumberOfCountries;
	private int NumberOfAllPatients;
	private int NumberOfPatientsOfACountry;
	private int NumberOfPatientsFromASpecifiedDate;
	private int NumberOfPatientsBeforeASpecifiedDate;
	private int NumberOfPatientsBetweenTwoDates;
	
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
		NumberOfAllPatients=0;
		for(int i=1;i<=NumberOfCountries;i++) {
			NumberOfAllPatients+=Util.convertStringToInteger(newData[i][column.length-1]);
		}
		return NumberOfAllPatients;
	}
	
	public int getNumberOfPatientsOfACountry(String countryName){		
		NumberOfPatientsOfACountry=0;
		for(int i=1;i<=NumberOfCountries;i++) {
			if(countryName.equals(newData[i][1])) {
				NumberOfPatientsOfACountry+=Util.convertStringToInteger(newData[i][column.length-1]);
			}
		}

		return NumberOfPatientsOfACountry;
	}
	
	
	public int getNumberOfPatientsFromASpecifiedDate(String date){
		int idx=0;
		NumberOfPatientsFromASpecifiedDate=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx==0) {
			return -1;
		}
		if(idx==4) {
			for(int i=1;i<=NumberOfCountries;i++) {
				NumberOfPatientsFromASpecifiedDate+=Util.convertStringToInteger(newData[i][idx]);
			}
		}
		if(idx>4) {
			for(int i=1;i<=NumberOfCountries;i++) {
				NumberOfPatientsFromASpecifiedDate+=(Util.convertStringToInteger(newData[i][idx])-Util.convertStringToInteger(newData[i][idx-1]));
			}
		}
		return NumberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date){
		int idx=0;
		NumberOfPatientsBeforeASpecifiedDate=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx<5) {
			return -1;
		}
		if(idx==5) {
			for(int i=1;i<=NumberOfCountries;i++) {
				NumberOfPatientsFromASpecifiedDate+=Util.convertStringToInteger(newData[i][idx-1]);
			}
		}
		if(idx>5) {
			for(int i=1;i<=NumberOfCountries;i++) {
				NumberOfPatientsBeforeASpecifiedDate+=(Util.convertStringToInteger(newData[i][idx-1])-Util.convertStringToInteger(newData[i][idx-2]));
			}
		}
		return NumberOfPatientsBeforeASpecifiedDate;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String fdate,String sdate){
		int fidx=0;
		int sidx=0;
		NumberOfPatientsBetweenTwoDates=0;
		
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
		
		if(sidx<fidx) {
			int tmp;
			
			tmp=fidx;
			fidx=sidx;
			sidx=tmp;
		}
		
		for(int i=1;i<=NumberOfCountries;i++) {
			NumberOfPatientsBetweenTwoDates+=(Util.convertStringToInteger(newData[i][sidx])-Util.convertStringToInteger(newData[i][fidx]));
		}
		
		return NumberOfPatientsBetweenTwoDates;
	}	
}
