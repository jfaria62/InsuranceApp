/*Create a connection and manage sql transactions/queries to and from database
*/
package server;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import classes.Accidents;

public class Connect {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;	

    
    public static void connect() {
    	
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String url = "jdbc:sqlite:C:/Users/Jonathan/Documents/UMASS/CIS452/autosDB.sqlite";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            System.out.println("Connection to SQLite has been established.");            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//END CONNECT
    
    public void addAccident(Date date, String city, String state, String vin[], Float damages[], String driver_ssn[], int numVehicles){
       
    	String sqlAccidents = "Insert into accidents (aid, accident_date, city, state) values(?, ?, ?, ?)";
        String sqlInvolvements = "Insert into involvements (aid, vin, damages, driver_ssn) values(?, ?,?,?)";
        int aid, i;
        
        try{
        	rs = stmt.executeQuery("select count(*) from accidents");   //get number of accidents currently in table
            rs.next();
            aid = rs.getInt(1) + 1;                                     //create aid for new accident to be added 
            PreparedStatement prep = conn.prepareStatement(sqlAccidents);   //create prepared statement to insert to accidents table
            prep.setInt(1, aid);                                            //set info for preparedStatement
            prep.setDate(2, date);
            prep.setString(3, city);
            prep.setString(4, state);
            try {    			
    			prep.executeUpdate();
    			System.out.print("\nAccident #" + aid + " being added" );
                
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}            
             //insert new rows into involvements for each vehicle in the reported accident
            for(i = 0; i < numVehicles; i++){  
            	System.out.print("\nexecuting Involvements update vin: " + vin[i]);
                 
                prep = conn.prepareStatement(sqlInvolvements);
                prep.setInt(1, aid);
                prep.setString(2, vin[i]);
                prep.setFloat(3, damages[i]);
                prep.setString(4, driver_ssn[i]);
                //System.out.print("\n"+prep.toString());
                try {        			
        			prep.executeUpdate();
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}     
            }
            prep.close();
            //stmt.executeUpdate("Insert into accidents (date, city, state) values("+ date + ", " + city + ", " + state + ")");
            //stmt2.executeUpdate("Insert into involvements (vin, damages, driver_ssn) values " + vin + ", " + damages + ", " + driver_ssn + ")");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }//END ADDACCIDENT

    public Accidents[] getAccidentsById(int aid) {   	
       
	   int count;
	   Date accident_date;
	   String city;
	   String state;
	   Float damages;
	   String driver_ssn;
	   int i = 0;
	   
	   try{
		   String sqlAccidents = "SELECT a.accident_date, a.city, a.state, i.vin, i.damages FROM accidents a, involvements i where a.aid == i.aid and a.aid == ?";
		   rs = stmt.executeQuery("select count(*) FROM accidents a, involvements i where a.aid == i.aid and a.aid == ?");
		   rs.next();
		   count = rs.getInt(1);
		   Accidents[] records = new Accidents[count];
	       
		   System.out.println("Count: " + count);
	   
	       PreparedStatement prep = conn.prepareStatement(sqlAccidents);   //create prepared statement to insert to accidents table
	       
	       rs = prep.executeQuery();
	       while(rs.next()){
	    	   records[i].setDate(rs.getDate("accident_date"));  
	    	   records[i].setLocation(rs.getString("city"), rs.getString("state"));
	    	   records[i].setDamages(rs.getFloat("damages")); 
	    	   i++;
            }

	       return records;   
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
	return null;
   }//END GETACCIDENTBYID */


    //change return type to Accidents[]
    public Accidents[] getByCriteria(Date minDate, Date maxDate, Float minAvg, Float maxAvg, Float minTot, Float maxTot){
    	String mainQuery = "select a.aid, date(a.accident_date) as date, a.city, a.state FROM involvements i, accidents a where a.aid == i.aid group by a.aid ";
       
        int numRows = 0;
        int i = 0;
       
        int critNum = 0; 		//keep track of filters, used for adding "having" and "and" to query strings
        //check if user included filter criteria and append to mainQuery as needed
        if(minDate != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING" + " (a.accident_date >= '"+ minDate + "') ";
        	}
        	else {
        		mainQuery += "AND" + " (a.accident_date >= '"+ minDate + "') ";
        	}
        	critNum++;        	
        }
        if(maxDate != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING" + " (a.accident_date <= '" + maxDate + "') ";
        	}
        	else {
        		mainQuery += "AND" + " (a.accident_date <= '" + maxDate + "') ";
        	}
        	critNum++;        	
        }
        if(minAvg != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING" + " (avg(i.damages) >= "+ minAvg +") ";
        	}
        	else {
        		mainQuery += "AND" + " (avg(i.damages) >= "+ minAvg +") ";
        	}
        	critNum++;        	
        }
        if(maxAvg != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING"  + " (avg(i.damages) <=  "+ maxAvg +") ";
        	}
        	else {
        		mainQuery += "AND" + " (avg(i.damages) <=  "+ maxAvg +") ";
        	}
        	critNum++;        	
        }
        if(minTot != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING" + " (total(i.damages) >= "+ minTot + ") ";
        	}
        	else {
        		mainQuery += "AND" + " (total(i.damages) >= "+ minTot + ") ";
        	}
        	critNum++;        	
        }
        if(maxTot != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING" + " (total(i.damages) <= "+ maxTot +") ";
        	}
        	else {
        		mainQuery += "AND" + " (total(i.damages) <= "+ maxTot +") ";
        	}
        	critNum++;        	
        }
        
        PreparedStatement prep;
        //create prepared statement to insert to accidents table
        //query returns multiple rows with multiple columns
        try {
        	rs = stmt.executeQuery("select count(*) FROM (" + mainQuery + ")");
        	rs.next();        	
 		    numRows = rs.getInt(1);
 		    System.out.print("\nRows: " + numRows);
			if(numRows > 0) {
				Accidents[] records = new Accidents[numRows];
	 		    i = 0;
	 		    System.out.print("\nQuery: " +mainQuery);
				prep = conn.prepareStatement(mainQuery);			
				rs = prep.executeQuery();			
				while(rs.next()){
					records[i] = new Accidents();
					String strDate = rs.getString("date");
					Date dDate = Date.valueOf(strDate);
					records[i].setID(rs.getInt("aid"));
					records[i].setDate(dDate);  
					records[i].setLocation(rs.getString("city"), rs.getString("state"));
					
		    	    i++;
	            }			
				prep.close();
				return records;
			}
			return null;
 		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n" + e);
			e.printStackTrace();
		}       
        return null;
    } //END GETBYCRITERIA 
    
   /* public void testMeth() {      	
    	int numRows = 0; 
    	try {
			//System.out.print("\nQuery: select count(*) FROM (" + mainQuery + ")\n");
    		rs = stmt.executeQuery("select count(*) FROM (" + mainQuery + ")"); 
         	System.out.print("\n\ttest2");
         	rs.next();        	
  		    numRows = rs.getInt(1);
  		    System.out.print("\nrows #: " + numRows);  		    
  		    
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\nhere " + e);
			e.printStackTrace();
		}   
    }*/
}
