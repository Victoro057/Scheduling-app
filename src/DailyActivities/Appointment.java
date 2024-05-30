package DailyActivities;

public class Appointment extends Task{  //This class inherits Task

    public String note, location; // These will be used to store the location and extra notes about the appointment


    public Appointment(String name, String date, int appointmentStartHour, int appointmentStartMin, int appointmentEndHour, int appointmentEndMin){ //This is option to create appointment on schedule without notes or location
        super(name,date,appointmentStartHour,appointmentStartMin,appointmentEndHour,appointmentEndMin);

    }
    public Appointment(String name, String date, int appointmentStartHour, int appointmentStartMin, int appointmentEndHour, int appointmentEndMin, String location){   //Extra option to add just the location
        super(name,date,appointmentStartHour,appointmentStartMin,appointmentEndHour,appointmentEndMin);
        this.location = location;

    }
    public Appointment(String name, String date, int appointmentStartHour, int appointmentStartMin, int appointmentEndHour, int appointmentEndMin, String location, String note){   //Extra option to add the location and a note
        super(name,date,appointmentStartHour,appointmentStartMin,appointmentEndHour,appointmentEndMin);
        this.location = location;
        this.note = note;

    }
    public Appointment(String name, String date, int appointmentStartHour, int appointmentStartMin, String location, String note){
        super(name, date, appointmentStartHour, appointmentStartMin);
        this.location = location;
        this.note = note;

    }
    public Appointment (String name, String date, int appointmentStartHour, int appointmentStartMin, String location){
        super(name, date,appointmentStartHour,appointmentStartMin);
        this.location = location;

    }
    public Appointment(String name, String date, int appointmentStartHour, int appointmentStartMin){
        super(name, date, appointmentStartHour, appointmentStartMin);
    }


    public String getNote() {
        return note;
    }

    public String getLocation() {
        return location;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public boolean hasLocation(){
        return location != null && !location.isEmpty();
    }
    public boolean hasNote(){
        return note !=null && !note.isEmpty();
    }
}
