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

import classes.Accidents;
import classes.People;

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
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void addAccident(Date date, String city, String state, String vin[], Float damages[], String driver_ssn[], int numVehicles){
        String sqlAccidents = "Insert into accidents (aid, accident_date, city, state) values(?, ?, ?, ?)";
        String sqlInvolvements = "Insert into involvements (aid, vin, damages, driver_ssn) values(?, ?,?,?)";
        int aid, i;
        //Add Accident
        //date city state -> Accidents (generate aid)
        //VIN, damages, driver_ssn -> Involvements
        try{
            rs = stmt.executeQuery("select count(*) from accidents");   //get number of accidents currently in table
            rs.next();
            aid = rs.getInt(1) + 1;                                     //create aid for new accident to be added 
            System.out.print("\nAccident #" + aid + " being added");
            PreparedStatement prep = conn.prepareStatement(sqlAccidents);   //create prepared statement to insert to accidents table
            prep.setInt(1, aid);                                            //set info for preparedStatement
            prep.setDate(2, date);
            prep.setString(3, city);
            prep.setString(4, state);
            prep.executeUpdate();
            System.out.print("\nDate: " + date + " city: " + city + "state ");
            //insert new rows into involvements for each vehicle in the reported accident
            for(i = 0; i < numVehicles; i++){   
                prep = conn.prepareStatement(sqlInvolvements);
                prep.setInt(1, aid);
                prep.setString(2, vin[i]);
                prep.setFloat(3, damages[i]);
                prep.setString(4, driver_ssn[i]);
                prep.executeUpdate();
            }
            prep.close();
            //stmt.executeUpdate("Insert into accidents (date, city, state) values("+ date + ", " + city + ", " + state + ")");
            //stmt2.executeUpdate("Insert into involvements (vin, damages, driver_ssn) values " + vin + ", " + damages + ", " + driver_ssn + ")");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
	    	    
            }

	       return records;   
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
	return null;
   }

    //change return type to Accidents[]
    public void getByCriteria(Date minDate, Date maxDate, Float minAvg, Float maxAvg, Float minTot, Float maxTot){
    	//Accidents results[] = new Accidents[]; 
    	People peeps = new People();
        String mainQuery = "select a.aid, a.accident_date, a.city, a.state FROM involvements i, accidents a where a.aid == i.aid group by a.aid ";
        String minAvgQuery = " (avg(i.damages) <=  ?) ";  
        String maxAvgQuery = " (avg(i.damages) >= ?) ";  
        String minTotQuery = " (total(i.damages) <= ?) ";
        String maxTotQuery = " (total(i.damages) >= ?) ";
        String minDateQuery = " (a.accident_date <= '?') ";        
        String maxDateQuery = " (a.accident_date >= '?') ";
        int critNum = 0; 		//keep track of filters, used for adding "having" and "and" to query strings
        //check if user included filter criteria and append to mainQuery as needed
        if(minDate != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + minDateQuery;
        	}
        	critNum++;        	
        }
        if(maxDate != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + maxDateQuery;
        	}
        	critNum++;        	
        }
        if(minAvg != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + minAvgQuery;
        	}
        	critNum++;        	
        }
        if(maxAvg != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + maxAvgQuery;
        	}
        	critNum++;        	
        }
        if(minTot != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + minTotQuery;
        	}
        	critNum++;        	
        }
        if(maxTot != null) {
        	if(critNum == 0) {
        		mainQuery += "HAVING";
        	}
        	else {
        		mainQuery += "AND" + maxTotQuery;
        	}
        	critNum++;        	
        }
        /*try{
            //rs = 
            
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;*/
        System.out.print(mainQuery);
    }        
}
