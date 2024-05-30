package DailyActivities;

public class AssignmentDue extends Task{        //This class will extend Task

    int hour, minute;
    String subjectWithAssignmentDue;
    boolean hasTime;
    public AssignmentDue(String name, String date, int hr, int min, String subject){
        super(name, date);
        hour = hr;
        minute = min;
        subjectWithAssignmentDue = subject;
        hasTime = true;
    }
    public AssignmentDue(String name, String date, String subject){
        super(name, date);
        subjectWithAssignmentDue = subject;
        hasTime = false;
    }
    public AssignmentDue(String name, String date, int hr, int min){    //Assignment due with name,date,hr and min of when its due
        super(name, date);
        hour = hr;
        minute = min;
        hasTime = true;
    }
    public AssignmentDue(String name, String date){
        super(name, date);
        hasTime = false;
    }


    //==================================================================================================================================================

    public boolean hasTime(){
        return hasTime;
    }
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getSubjectWithAssignmentDue() {
        return subjectWithAssignmentDue;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSubjectWithAssignmentDue(String subjectWithAssignmentDue) {
        this.subjectWithAssignmentDue = subjectWithAssignmentDue;
    }

    public boolean hasSubject(){
        return subjectWithAssignmentDue !=null && !subjectWithAssignmentDue.isEmpty();
    }
}
