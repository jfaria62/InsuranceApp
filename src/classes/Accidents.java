package classes;
import java.sql.Date;

public class Accidents {
    private int aid;
    private Date accident_date;
    private String city;
    private String state;
    private Float damages;
    private String driver_ssn;

    public Accidents(){}

    public Accidents(Date a_Date, String city, String state, Float damages, String ssn){        
        setLocation(city, state);
        setDate(a_Date);
        setDamages(damages);
        setDriver(ssn);
    }


    public Float getDamages(){
        return this.damages;
    }

    public void setDamages(Float damages){
        this.damages = damages;
    }

    public String getDriver(){
        return this.driver_ssn;
    }

    public void setDriver(String ssn){
        this.driver_ssn = ssn;
    }

    public int getID(){        
        return this.aid;
    }
    
    public void setID(int aid){
        this.aid = aid;
    }

    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }

    public void setLocation(String city, String state){
        this.city = city;
        this.state = state;
    }
    public Date getDate(){
        return this.accident_date;
    }

    public void setDate(Date aDate){
    	this.accident_date = aDate;
    }
}