package DailyActivities;

public class Test extends Task{     //This class will extend Task

    public String subject,location,note;
    public Test(String name, String date, String subject, String location, String note){
        super(name, date);
        this.subject = subject;
        this.location = location;
        this.note = note;

    }
    public Test(String name, String date, String subject, String location){ //Constructor without a note
        super(name, date);
        this.subject = subject;
        this.location = location;


    }
    public Test(String name, String date, String subject){  //Constructor without note and location
        super(name, date);
        this.subject = subject;


    }
    public Test(String name, String date){  //Constructor without note, location, and subject
        super(name, date);


    }

}
