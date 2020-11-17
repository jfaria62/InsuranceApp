package classes;
import java.sql.Blob;

public class Agents extends People {
    private Float salary;
    private Blob photo;
    
    public Agents(String ssn, String fName, String lName, String city, String state, Float salary, Blob photo){
        setName(fName,lName);
        setLocation(city, state);
        setSSN(ssn);
        setSalary(salary);
        setPhoto(photo);
    }
    
    public Float getSalary(){        
        return this.salary;
    }
    
    public void setSalary(Float salary){        
         this.salary = salary;
    }

    public Blob getPhoto(){        
        return this.photo;
    }
    
    public void setPhoto(Blob photo){        
        this.photo = photo;
    }
    

}
