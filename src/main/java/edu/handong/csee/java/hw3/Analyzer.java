package edu.handong.csee.java.hw3;
import java.util.ArrayList;

public class Analyzer {
	
	private String[][] newData;
	private String[] column;
	private String[] patients;
	private String[] country;
	
	private int numberOfCountries;
	private int numberOfProvince;
	private int numberOfAllPatients;
	private int numberOfPatientsOfACountry;
	private int numberOfPatientsFromASpecifiedDate;
	private int numberOfPatientsBeforeASpecifiedDate;
	private int numberOfPatientsBetweenTwoDates;
	
	public Analyzer(String[] data){
		numberOfProvince=data.length-1;
		column=data[0].split(",");
		patients=new String[data.length];
		newData=new String[data.length][column.length];
		
		for(int j=0;j<column.length;j++) {
			newData[0][j]=column[j];
		}
		for(int i=1;i<data.length;i++) {
			patients=data[i].split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
			for(int j=0;j<column.length;j++) {
				if(patients[j].startsWith("\"")) {
					patients[j]=patients[j].substring(1, patients[j].length()-1);
				}
				newData[i][j]=patients[j];
			}
		}
		//데이터가 2차원배열에 올바르게 저장되었는지 확인하기 위한 코드
		/*for(int i=0;i<data.length;i++) {
			for(int j=0;j<column.length;j++) {
				System.out.printf("%s ",newData[i][j]);
			}
			System.out.println();
		}*/
	}
	
	public int getNumberOfCountries(){
		country=new String[numberOfProvince];
		for(int i=1;i<=numberOfProvince;i++) {
			country[i-1]=newData[i][1];
		}
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0 ; i<country.length;i++){
			if(!list.contains(country[i])){
				list.add(country[i]);
			}
		}
		numberOfCountries=list.size();
		return numberOfCountries;
	}
	
	public int getNumberOfAllPatients(){
		numberOfAllPatients=0;
		for(int i=1;i<=numberOfProvince;i++) {
			numberOfAllPatients+=Util.convertStringToInteger(newData[i][column.length-1]);
		}
		return numberOfAllPatients;
	}
	
	public int getNumberOfPatientsOfACountry(String countryName){		
		numberOfPatientsOfACountry=0;
		for(int i=1;i<=numberOfProvince;i++) {
			if(countryName.equals(newData[i][1])) {
				numberOfPatientsOfACountry+=Util.convertStringToInteger(newData[i][column.length-1]);
			}
		}
		return numberOfPatientsOfACountry;
	}
	
	
	public int getNumberOfPatientsFromASpecifiedDate(String date){
		int idx=0;
		numberOfPatientsFromASpecifiedDate=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx==0) {
			return -1;
		}
		if(idx==4) {
			for(int i=1;i<=numberOfProvince;i++) {
				numberOfPatientsFromASpecifiedDate+=Util.convertStringToInteger(newData[i][column.length-1]);
			}
		}
		if(idx>4) {
			for(int i=1;i<=numberOfProvince;i++) {
				numberOfPatientsFromASpecifiedDate+=(Util.convertStringToInteger(newData[i][column.length-1])-Util.convertStringToInteger(newData[i][idx-1]));
			}
		}
		return numberOfPatientsFromASpecifiedDate;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String date){
		int idx=0;
		numberOfPatientsBeforeASpecifiedDate=0;
		
		for(int j=4;j<column.length;j++) {
			if(date.equals(newData[0][j])) {
				idx=j;
			}
		}
		
		if(idx<5) {
			return 0;
		}else{
			for(int i=1;i<=numberOfProvince;i++) {
				numberOfPatientsBeforeASpecifiedDate+=Util.convertStringToInteger(newData[i][idx-1]);
			}
		}
		
		return numberOfPatientsBeforeASpecifiedDate;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String fdate,String sdate){
		int fidx=0;
		int sidx=0;
		numberOfPatientsBetweenTwoDates=0;
		
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
		
		for(int i=1;i<=numberOfProvince;i++) {
			if(fidx==4) {
				numberOfPatientsBetweenTwoDates+=Util.convertStringToInteger(newData[i][sidx]);
			}else {
				numberOfPatientsBetweenTwoDates+=(Util.convertStringToInteger(newData[i][sidx])-Util.convertStringToInteger(newData[i][fidx-1]));
			}
		}
		
		return numberOfPatientsBetweenTwoDates;
	}	
}
