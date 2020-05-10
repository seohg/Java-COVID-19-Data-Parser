package edu.handong.csee.java.hw3;

public class Analyzer {
	
	private String[][] newData;
	private String[] column;
	private String[] row;
	
	private int NumberOfCountries;
	private int NumberOfPatients;
	
	public Analyzer(String[] data){
		column=data[0].split(",");
		row=new String[data.length];
		newData=new String[data.length][column.length];
		for(int j=0;j<column.length;j++) {
			newData[0][j]=column[j];
		}
		for(int i=1;i<data.length;i++) {
			row=data[i].split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
			for(int j=0;j<column.length;j++) {
				newData[i][j]=row[j];
			}
		}
		/*for(int i=0;i<data.length;i++) {
			for(int j=0;j<column.length;j++) {
				System.out.printf("%s ",newData[i][j]);
			}
			System.out.println();
		}*/
		
	}
	/*public int getNumberOfCountries(String[] data){
		
	}
	public int getNumberOfAllPatients(){
		
	}
	public int getNumberOfPatientsOfACountry("Korea, South"){
		
	}
	public int getNumberOfPatientsFromASpecifiedDate("1/24/20"){
		
	}
	public int getNumberOfPatientsBeforeASpecifiedDate("1/24/20"){
		
	}
	public int getNumberOfPatientsBetweenTwoDates("1/24/20","2/7/20"){
		
	}*/
	
}
