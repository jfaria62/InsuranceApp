/*Create a connection and manage sql transactions/queries to and from database
*/
package server;
import java.sql.Statement;
import classes.People;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sqlAccidents = "Insert into accidents (aid, date, city, state) values(?, ?, ?, ?)";
        String sqlInvolvements = "Insert into involvements (aid, vin, damages, driver_ssn) values(?, ?,?,?)";
        int aid, i;
        //Add Accident
        //date city state -> Accidents (generate aid)
        //vin, damages, driver_ssn -> Involvements
        try{
            rs = stmt.executeQuery("select count(*) from accidents");   //get number of accidents currently in table
            rs.next();
            aid = rs.getInt(1) + 1;                                     //create aid for new accident to be added 

            PreparedStatement prep = conn.prepareStatement(sqlAccidents);   //create prepared statement to insert to accidents table
            prep.setInt(1, aid);                                            //set info for preparedStatement
            prep.setDate(2, date);
            prep.setString(3, city);
            prep.setString(4, state);
            prep.executeUpdate();
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

   /* public Accidents getAccidents(){
        int aid, count;
        Date accident_date;
        String city;
        String state;
        Float damages;
        String driver_ssn;
        int i = 0;
        rs = stmt.executeQuery("select count(*) from accidents");
        rs.next();
        count = rs.getInt(1);
        System.out.println("Count: " + count);
        try{
            Accidents[] accidents = new Accidents[count];
            rs = stmt.executeQuery("select * from accidents");
            while(rs.next()){
                aid = rs.getInt("aid");
                accident_date = rs.getDate("accident_date");
                city = rs.getString("city");
                state = rs.getString("state");
                damages = rs.getFloat("damages");
                driver_ssn = rs.getString("driver_ssn");
                accidents[i] = new Accidents(aid, accident_date,city, state, damages, driver_ssn);

                return accidents; 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accidents[];
    }
*/
    public People findPeople(String fname, String lname){
        People peeps = new People();
        String fn, ln;
        try{
            rs = stmt.executeQuery("select * from people where fname=" + fname + "AND lname=" + lname);
            fn = rs.getString("fname");//loop through for all data
            ln = rs.getString("lname");
            peeps.setName(fn, ln);
            
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return peeps;
    }        
}
