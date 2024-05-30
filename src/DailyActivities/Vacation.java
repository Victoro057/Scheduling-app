package DailyActivities;
import java.time.temporal.ChronoUnit;
public class Vacation extends Task{

    public String location;

    public Vacation (String name, String startDate, String endDate, String location){      //In case the user wants to enter location as option
        super(name, startDate, endDate);
        this.location = location;

    }
    public Vacation (String name, String startDate, String endDate){    //Vacation that lasts for more than one day
        super(name, startDate, endDate);

    }

    public Vacation(String name, String date){  //One day vacation
        super(name, date);

    }

    //=========================================================================================================================================
    //=========================================================================================================================================


    public long getVacationDuration(){  //returns the duration of the vacation in days
            return ChronoUnit.DAYS.between(startDate, endDate);
    }
    public void printVacationDuration(){    //Prints the amount of days the vacation is scheduled for
        System.out.println(taskName + " will last " + getVacationDuration() + " days\n");
    }
    public boolean hasLocation(){
        return location != null && !location.isEmpty();
    }

    public String getLocation() {
        return location;
    }
}
