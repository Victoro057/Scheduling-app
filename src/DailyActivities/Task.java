package DailyActivities;

                                                        //This is based on a 24-hr clock
public class Task extends TimeTakenOfDay {


            //Constructors for all different kinds of tasks/activities
    public String taskName;
    public boolean endHourEntered;

    public Task (String name, String date, int taskStartHr, int taskStartMin, int taskEndHr, int taskEndMin ){  //Task that is scheduled for a certain time of the day
        super(date,taskStartHr, taskStartMin, taskEndHr, taskEndMin);
        taskName = name;
        endHourEntered = true;
    }
    public Task (String name, String startDate, String endDate){    //Task that is scheduled for more than one day and doesn't include times
        super(startDate, endDate);
        taskName = name;
        endHourEntered = false;
    }
    public Task (String name, String date){ //Used for task that don't necessarily need a time of the day
        super(date);
        taskName = name;
        endHourEntered = false;
    }
    public Task (String name, String date, int taskStartHr, int taskStartMin){  //This is for tasks that might not have a set end time
        super(date, taskStartHr, taskStartMin);
        taskName = name;
        endHourEntered = false;
    }


    //=====================================================================================================================================
    //=====================================================================================================================================


    public void displayTheTaskTime(){
        if(validTimeOfDayEntered(startHour,startMinute,endHour,endMinute)) {    //This validation doesn't work for some reason

            if(getStartMinute() == 0) {  //if user enters 0 for a minute, in default it would just display as 0 Ex: 17:0  We don't want that.
                System.out.println("Start time is " + getStartHour() + ":" + getStartMinute() + "0");   //This makes it be Ex: 17:00
            }
            else {
                System.out.println("Start time is " + getStartHour() + ":" + getStartMinute());
            }
            if(getEndMinute() == 0) {
                System.out.println("End time is " + getEndHour() + ":" + getEndMinute() + "0");
            }
            else {
                System.out.println("End time is " + getEndHour() + ":" + getEndMinute());
            }
        }

        //Good idea to incorporate tha same user interaction and display accuracy as with displayTheTaskTimeWithoutEndTime() method
    }
    public void displayTheTaskTimeWithoutEndTime(){
        if (validTimeOfDayEnteredNoEnd(startHour,startMinute) && (checkIfStartHrLessThanTen() && !checkIfStartHrIsZero()) && (checkIfStartMinLessThanTen() && !checkIfStartMinIsZero())){  //if the user enters values lower than 10 and not 0 for both hours and minutes. Ex: entered 3 for hrs and 2 for mins. Displays as 03:02
            System.out.println("Start time is " + "0" + getStartHour() + ":" + "0" + getStartMinute());
        }
        else if (validTimeOfDayEnteredNoEnd(startHour, startMinute) && checkIfStartMinIsZero()) {   //When the user enters just num 0 as for minutes, this makes it display as hh:00
            System.out.println("Start time is " + getStartHour() + ":" + getStartMinute() + "0");
        }
        else if(validTimeOfDayEnteredNoEnd(startHour, startMinute) && checkIfStartMinLessThanTen() && !checkIfStartMinIsZero()){     //This case is when the user entered 3 as for minutes and it makes it display as hh:03
            System.out.println("Start time is " + getStartHour() + ":" + "0" + getStartMinute());
        }
        else if(validTimeOfDayEnteredNoEnd(startHour, startMinute) && checkIfStartHrIsZero()){  //This is the case when the user enters 0 for hour, which this method makes it display as 00:mm
            System.out.println("Start time is " + getStartHour() + "0" + ":" + getStartMinute());
        }
        else if(validTimeOfDayEnteredNoEnd(startHour, startMinute) && checkIfStartHrLessThanTen() && !checkIfStartHrIsZero()){    //This case if the user enters 3 for example for hour, this makes it display as 03:mm
            System.out.println("Start time is " + "0" + getStartHour() + ":" + getStartMinute());
        }
        else if(validTimeOfDayEnteredNoEnd(startHour, startMinute)){    //When the user entered everything right
            System.out.println("Start time is " + getStartHour() + ":" + getStartMinute());
        }
        else{
            System.out.println("Error displaying the task time!");
        }
    }

    private boolean checkIfStartHrLessThanTen(){
        return startHour < 10;
    }
    private boolean checkIfStartHrIsZero(){
        return startHour == 0;
    }
    private boolean checkIfStartMinLessThanTen(){
        return startMinute < 10;
    }
    private boolean checkIfStartMinIsZero(){
        return startMinute == 0;
    }


    public void displayTaskName(){
        System.out.println(getTaskName());
    }

    public int getWholeHoursTaken(){    //this method returns the amount of hours a task is planned to take
        int hrsCount = 0;
        if(getStartHour() < getEndHour()){
            hrsCount = getEndHour() - getStartHour();
        }
        else if(getStartHour() > getEndHour()){     //This is in case of having something scheduled for 23:sum to 0:sum
            hrsCount = (getEndHour() + 24) - getStartHour();    //adds 24 to the end hours to create the actual difference between the start hour and the end hour
        }
        //no need to for an else statement because if no parameters are met that means the task took less than an hour to do. Meaning 0 whole hours take
                                                                                                                                    //This is assigned to hrsCount already

        return hrsCount;
    }

    public int getWholeMinutesTaken(){
        int minCount = 0;
        if(getStartMinute() < getEndMinute()){
            minCount = getEndMinute() - getStartMinute();
        }
        else if(getStartMinute() > getEndMinute()){
            minCount = (getEndMinute() + 60) - getStartMinute();    //adds 60 to the end minutes to create the actual difference between the start minute and the end minutes
        }

        return minCount;
    }

    public void printTimeTakenForTask(){    //prints the amount of time the task is planned to take
        if(getWholeHoursTaken() == 1) {
            System.out.println(taskName + " is planned to take " + getWholeHoursTaken() + "hr and " + getWholeMinutesTaken() + "min");
        }
        else{
            System.out.println(taskName + " is planned to take " + getWholeHoursTaken() + "hrs and " + getWholeMinutesTaken() + "min");
        }

    }

    public boolean isEndHourEntered(){
        return endHourEntered;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /*
    public boolean hasEndHr(){
        return endHour >-1;
    }
    */
}
