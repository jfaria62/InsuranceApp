package classes;
public class People {
    private String ssn;
    private String fname;
    private String lname;
    private String city;
    private String state;

    public People(){}

    public People(String ssn, String fName, String lName, String city, String state){
        setName(fName,lName);
        setLocation(city, state);
        setSSN(ssn);
    }
    
    public String getFName(){        
        return this.fname;
    }
    
    public String getLName(){        
        return this.lname;
    }
    
    public void setName(String fName, String lName){
        this.fname = fName;
        this.lname = lName;
    }
    
    public String getSSN(){
        return this.ssn;
    }

    public void setSSN(String ssn){
        this.ssn = ssn;
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
}
