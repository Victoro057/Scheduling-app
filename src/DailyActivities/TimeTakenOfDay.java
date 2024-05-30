package DailyActivities;


    //This is based off of a 24 hour day clock



public class TimeTakenOfDay extends MonthYearDate{
    public int startHour, startMinute;
    public int endHour, endMinute;
    public TimeTakenOfDay (String date, int startHour, int startMinute, int endHour, int endMinute){
        super(date);    //Passes the date to the higher class
        if(validTimeOfDayEntered(startHour,startMinute,endHour,endMinute)) {   //If the times entered by the user are valid in the format of 24hrs in day and 60 mins in hr.
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
        }
        else{
            System.out.println("invalid time!");
        }
    }
    public TimeTakenOfDay(String date, int startHour, int startMinute){     //This will be used for tasks that don't have a set end time
        super(date);
        this.startHour = startHour;
        this.startMinute = startMinute;
    }

    public TimeTakenOfDay(String startDate, String endDate){
        super(startDate, endDate);

    }
    public TimeTakenOfDay(String date){
        super(date);
    }

    //====================================================================================================================================
    //====================================================================================================================================


    public boolean validTimeOfDayEntered(int startHour, int startMinute, int endHour, int endMinute){
        boolean valid = true;
        if ((startHour > 23 || startHour < 0) || (endHour > 23 || endHour < 00)){
            valid = false;
            System.out.println("You entered an invalid hour!");
        }
        else if ((startMinute > 59 || startMinute < 00) || (endMinute > 59) || (endMinute <00)){
            valid = false;
            System.out.println("You entered an invalid minute!");
        }

        return valid;
    }

    public boolean validTimeOfDayEnteredNoEnd(int startHour, int startMinute){
        boolean valid = true;
        if ((startHour > 23 || startHour < 0)){
            valid = false;
            System.out.println("You entered an invalid hour!");
        }
        else if (startMinute > 59 || startMinute < 00){
            valid = false;
            System.out.println("You entered an invalid minute!");
        }
        return valid;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }
}


