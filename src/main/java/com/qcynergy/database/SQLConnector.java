package com.qcynergy.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.qcynergy.baseTest.BaseTest;

public class SQLConnector extends BaseTest{

	public String InitialRvwComplete() {
		int IntRvwComp1 = 0;
		Connection conn = null;
		StringBuilder str;
		 String result = null;
		try{
		String dbUrl = "jdbc:sqlserver://vstgsql01;databaseName=Lusy";					

		//Database Username		
		String username = "SahilSingh";	
        
		//Database Password		
		String password = "Digital123";				

		//Query to fetch no. of loans cancelled		
		String  NoOfLoansNotCancelled= "Select Count(DISTINCT HE.HeaderID_1003) as 'col' from [1003_Header] HE "+
										"Full Join Batches BA on HE.BatchID = BA.ID "+
										"Full Join Findings FD on HE.HeaderID_1003=FD.HeaderID "+
										"Full Join Issue ISS on FD.IssueID = ISS.IssueID "+
										"Full Join Strength ST on ST.StrengthID = FD.strengthID "+
										"Full Join RemediationStatus RSS on RSS.ID=FD.RemediationStatusID "+
										"Full Join IssueCategory IC on IC.IssueCategoryID = ISS.IssueCategoryID "+
										"Full Join Reviewstatus RS on RS.StatusID=HE.Review_StatusID "+
										"Full Join Reviewstatus2 RS2 on RS2.Status2ID=HE.Review_Status2ID "+
										"Full Join Reviewstatus4 RS4 on RS4.StatusID=HE.Review_Status4ID "+ 
										"Full Join ReviewComponentType RCT on ISS.component = RCT.ReviewComponentID "+
										"Where BA.FileName like '"+properties.getProperty("DealName")+"' "+ 
										"and  NOT RS.StatusName = 'Cancelled'; ";	
		
		String  IntRvwComp= "Select Count(DISTINCT HE.HeaderID_1003) as 'intRvwComp' from [1003_Header] HE "+
								"Full Join Batches BA on HE.BatchID = BA.ID "+
								"Full Join Findings FD on HE.HeaderID_1003=FD.HeaderID "+
								"Full Join Issue ISS on FD.IssueID = ISS.IssueID "+
								"Full Join Strength ST on ST.StrengthID = FD.strengthID "+
								"Full Join RemediationStatus RSS on RSS.ID=FD.RemediationStatusID "+
								"Full Join IssueCategory IC on IC.IssueCategoryID = ISS.IssueCategoryID "+
								"Full Join Reviewstatus RS on RS.StatusID=HE.Review_StatusID "+
								"Full Join Reviewstatus2 RS2 on RS2.Status2ID=HE.Review_Status2ID "+
								"Full Join Reviewstatus4 RS4 on RS4.StatusID=HE.Review_Status4ID "+ 
								"Full Join ReviewComponentType RCT on ISS.component = RCT.ReviewComponentID "+
								"Where BA.FileName like '"+properties.getProperty("DealName")+"' "+  
								"and RS.StatusName IN ('Client Review In Process','Underwriting Client Review Complete','Underwriting Ready for DR Response','No Underwriting Client Review Needed','Sent to Client','Client QC Complete') "+
								"and RS2.Status2Name IN ('Valuation Client Review In Process','Valuation Client Review Complete','Valuation Ready for DR Response','Valuation Cancelled','No Valuation Client Review Needed') "+
								"and RS4.StatusName IN ('Compliance Client Review In Process' ,'Compliance Client Review Complete','Compliance Ready for DR Response','Compliance Cancelled','No Compliance Client Review Needed'); ";
        
 	    //Load mssql jdbc driver		
   	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
   
   		//Create Connection to DB		
   	    conn = DriverManager.getConnection(dbUrl,username,password);
  
  		//Create Statement Object		
	    Statement stmt = conn.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
 		ResultSet rs1= stmt.executeQuery(NoOfLoansNotCancelled);	
 			
 		float rs3=0;
 		float rs4=0;
 		while(rs1.next()){
 			rs3= rs1.getInt("col");
 			System.out.println("NoOfLoansNotCancelled are "+rs3);
 		}
 		ResultSet rs2= stmt.executeQuery(IntRvwComp);	
 		while(rs2.next()){
 			rs4= rs2.getInt("intRvwComp");
 			System.out.println("NoOfLoansIntRvwComp are "+rs4);
 		}
 		
	    IntRvwComp1= (int) ((rs4 / rs3)*100);
	    
	    String strIntRvwComp1 = Integer.toString(IntRvwComp1);
	    str = new StringBuilder(strIntRvwComp1);
	    
	     result=str.append("%").toString();

		// closing DB Connection	
 	    stmt.close();
		conn.close();			
		}
	catch(Exception e){
		e.printStackTrace();
		System.out.println(e);
	}
		return result;
	}
}
