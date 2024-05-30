package DailyActivities;

public class Meeting extends Task{      //This class inherits Task

    public String location, agenda;

    public Meeting(String name, String date, int meetingStartHour, int meetingStartMin, int meetingEndHour, int meetingEndMin){ //This is option to create appointment on schedule without notes or location
        super(name,date,meetingStartHour,meetingStartMin,meetingEndHour, meetingEndMin);



    }
    public Meeting(String name, String date, int meetingStartHour, int meetingStartMin, int meetingEndHour, int meetingEndMin, String location){ //This is option to create appointment on schedule without notes or location
        super(name,date,meetingStartHour,meetingStartMin,meetingEndHour,meetingEndMin);
        this.location = location;


    }

    public Meeting(String name, String date, int meetingStartHour, int meetingStartMin, int meetingEndHour, int meetingEndMin, String location, String agenda){ //This is option to create appointment on schedule without notes or location
        super(name,date,meetingStartHour,meetingStartMin,meetingEndHour,meetingEndMin);
        this.location = location;
        this.agenda = agenda;
    }
    public Meeting (String name, String date, int meetingStartHour, int meetingStartMin, String location, String agenda){   //Will be used if the user enters location and agenda but not end time
        super(name,date,meetingStartHour,meetingStartMin);
        this.location = location;
        this.agenda = agenda;
    }
    public Meeting (String name, String date, int meetingStartHour, int meetingStartMin, String location){  //Case if user only enters location on top of name,date, and start time
        super(name,date,meetingStartHour,meetingStartMin);
        this.location = location;
    }
    public Meeting (String name, String date, int meetingStartHour, int meetingStartMin){   //Case if user only enters the required parts (name,date,start time)
        super(name,date,meetingStartHour,meetingStartMin);
    }

    public String getLocation() {
        return location;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
    public boolean hasLocation(){
        return location != null && !location.isEmpty();
    }
    public boolean hasAgenda(){
        return agenda !=null && !agenda.isEmpty();
    }



}
