package classes;

public class Accidents {
    private int aid;
    private String accidentDate;
    private String city;
    private String state;
    private Float damages;
    private String driver_ssn;
    private String vin;

    public Accidents(){}

    public Accidents(String vin,String accidentDate, String city, String state, Float damages, String driver_ssn){        
        setLocation(city, state);
        setDate(accidentDate);
        setDamages(damages);
        setDriver(driver_ssn);
        setVin(vin);
    }

    public String getVin(){
        return this.vin;
    }

    public void setVin(String vin){
        this.vin= vin;
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

    public void setDriver(String driver_ssn){
        this.driver_ssn = driver_ssn;
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
    public String getDate(){
        return this.accidentDate;
    }

    public void setDate(String accidentDate){
    	this.accidentDate = accidentDate;
    }
}