/**
 * 
 * HeartTransplant class
 * 
 * @author Ananya Saravanan as3660@scarletmail.rutgers.edu as3660
 */
public class HeartTransplant {

    private Patient[] patients;
    private SurvivabilityByAge survivabilityByAge;
    private SurvivabilityByCause survivabilityByCause;
  
    public HeartTransplant() {
  
        patients = null;
        survivabilityByAge = null;
        survivabilityByCause = null;
    }
  
    public Patient[] getPatients() {
        return patients;
     } 
  
    
    public SurvivabilityByAge getSurvivabilityByAge() {
        return survivabilityByAge;
    }
  
    public SurvivabilityByCause getSurvivabilityByCause() {
        return survivabilityByCause;
    }
  
    public void readPatients (int numberOfLines) {
        patients = new Patient[numberOfLines];
  
        for (int i = 0; i< numberOfLines; i++)
        {
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int stateOfHealth = StdIn.readInt();
            patients[i] = new Patient(id, ethnicity, gender, age, cause, urgency, stateOfHealth);
        }
  
        
    }
  
    public void readSurvivabilityByAge (int numberOfLines) {
        survivabilityByAge = new SurvivabilityByAge();
        for (int i = 0; i<numberOfLines; i++)
        {
            int age = StdIn.readInt();
            int year = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge.addData(age, year, rate);
        }
    }
  
    public void readSurvivabilityByCause (int numberOfLines) {
        survivabilityByCause = new SurvivabilityByCause();
        for (int i =0; i<numberOfLines; i++)
        {
            int cause = StdIn.readInt();
            int year = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByCause.addData(cause, year, rate);
        }
    }
    
    public Patient[] getPatientsWithAgeAbove(int age) {
        int size = 0;
        for (Patient patient : patients){
            if (patient.getAge() > age) 
            {
                size++;
            }
        
        }
        if (size == 0) 
        {
          return null;
        }
        Patient[] arr = new Patient[size];
        int j = 0;
        for (int i = 0; i < patients.length; i++)
        {
            if (patients[i].getAge() > age){
                arr[j++] = patients[i];
            }
        }
        return arr;
  
    }
  
    public Patient[] getPatientsByHeartConditionCause(int cause) {
        int size = 0;
        for (Patient patient : patients)
        {
            if (patient.getCause() == cause) 
            {
                size++;
            }
        }
        if (size == 0) 
        {
          return null;
        }  
        Patient[] arr = new Patient[size];
        int j = 0;
        for (int i = 0; i < patients.length; i++)
        {
            if (patients[i].getCause() == cause){
                arr[j++] = patients[i];
            }
        }
        return arr;
    }
  
    public Patient[] getPatientsByUrgency(int urgency) {
        int size = 0;
        for (Patient patient : patients)
        {
            if (patient.getStateOfHealth() == urgency) size++;
        }
        if (size == 0) 
        {
          return null;
        }  
        Patient[] array = new Patient[size];
        int j = 0;
        for (int i = 0; i < patients.length; i++){

            if (patients[i].getCause() == urgency)
            {
                array[j++] = patients[i];
            }
        }
        return array;
    }
  
    public Patient getPatientForTransplant () {
        int highestSurvival = 0;
        for (int i = 0; i < patients.length; i++)
        {
            if (patients[i].getUrgency() > highestSurvival && patients[i].getNeedHeart())
            {
                highestSurvival = patients[i].getUrgency();
            }
        }
        double most = 0;
        for (int i =0;i<patients.length;i++)
        {
            double rate = 0;
            if (patients[i].getUrgency() == highestSurvival && patients[i].getNeedHeart())
            {
                double age = survivabilityByAge.getRate(patients[i].getAge(), 3);
                double cause = survivabilityByCause.getRate(patients[i].getCause(), 3);
                rate = (age+cause)/2;
                if (most < rate)
                {
                    most = rate;
                }
            }
        }
        for (int i=0;i<patients.length;i++)
        {
            double rate = 0;
            if (patients[i].getUrgency() == highestSurvival && patients[i].getNeedHeart())
            {
                double age = survivabilityByAge.getRate(patients[i].getAge(),3);
                double cause = survivabilityByCause.getRate(patients[i].getCause(),3);
                rate = (age+cause)/2;
                if (most == rate){
                    patients[i].setNeedHeart(false);
                    return patients[i];
                } 
            }
        }
        return null;
    }
  }
