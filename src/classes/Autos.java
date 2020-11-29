package classes;
public class Autos {
    String vin;
    String make;
    int year;
    String agent_ssn;
    String owner_ssn;

    public Autos(){};
    public Autos(String vin, String make, int year, String agent_ssn, String owner_ssn){
        setVin(vin);
        setMake(make);
        setYear(year);
        setAgent_ssn(agent_ssn);
        setOwner_ssn(owner_ssn);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    
    public String getOwner_ssn() {
        return owner_ssn;
    }
    
    public void setOwner_ssn(String owner_ssn) {
        this.owner_ssn = owner_ssn;
    }

    public String getAgent_ssn() {
        return agent_ssn;
    }
    
    public void setAgent_ssn(String agent_ssn) {
        this.agent_ssn = agent_ssn;
    }
    
    public String getVin(){
        return this.vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
    

}
