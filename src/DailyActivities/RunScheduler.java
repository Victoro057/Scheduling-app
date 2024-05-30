package DailyActivities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

        //IMPORTANT NOTICE: This is based off of a 24-hour clock. So no AM and PM times
            //Example: When its 2PM it should be entered as 14 hr and so on until 23


public class RunScheduler {

    private int intInput;
    private String name;
    private String date;

    private String endDate;
    private String location;
    private String note;
    private String agenda;
    private String subject;
    private int startHr;
    private int startMin;
    private int endHr;
    private int endMin;
    private int optionalLocInput = -1;
    private int optionalNoteInput = -1;
    private int optionalEndTime = -1;
    private int optionalEndDate = -1;
    private int optionalAgendaInput = -1;
    private int optionalSubjectInput = -1;
    private int optionalEnterMore = -1;
    private int optionToDisplayTasks = -1;
    private boolean valid = false;
    private boolean donePicking = false;

    public RunScheduler(){
        ArrayList<Task> tasks = new ArrayList<>();
        while(!donePicking) {
            pickTaskType();

            handlePickTaskOptions(tasks);
            if(donePicking){
                break;
            }
            System.out.println("As of now you have " + tasks.size() + " tasks saved!");

            if (optionalEndTime < 1) {
                (tasks.get(0)).displayTheTaskTimeWithoutEndTime();
            } else if (optionalEndTime == 1) {
                (tasks.get(0)).displayTaskName();
            }

            System.out.println("Would you like to enter more tasks? 1=Yes 0=No");
            optionalEnterMore = enterValidYesOrNo();
            if(optionalEnterMore == 0){
                System.out.println("Great, were done adding tasks");
                break;
            }
        }

        displayTasks(tasks);    //Method that displays the tasks



    }



    //==============================================================================================================================================================
    //==============================================================================================================================================================

                                            // Utilities for this class.
                                    //Includes validation, user input, user choices (like 1 or 0 for yes or no questions)
    private int pickTaskType(){
        System.out.println("What type of task would you like to enter?");
        System.out.println("Choices:\n"
                            + "1. Appointment\n"
                            + "2. Meeting\n"
                            + "3. Vacation\n"
                            + "4. Assignment due\n"
                            + "5. Exit\n"
        );
        intInput = 0;
        valid = false;  //resetting the valid boolean
        while (!valid) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the task number here: ");
            try {
                intInput = input.nextInt();
                if(intInput >= 1 && intInput <=5){   //if user entered number between 1 and 5
                    valid = true;
                }
                else {
                    while (intInput > 0 || intInput <6) {
                        System.out.println("Enter only between 1 and 5");
                        intInput = input.nextInt();
                        if (intInput >= 1 && intInput <=5) {   //this is done again to set the valid to true once the user enters a correct value and breaks out of while loop
                            valid = true;
                            break;
                        }
                    }

                }

            } catch (Exception e) {
                System.out.println("Can't take that as an input. Enter again!");
                input.next();   //This clears the input, and lets the next scanner input be able to get user input (if that makes sense lol)
            }
        }

        return intInput;
    }

    private void handlePickTaskOptions(ArrayList<Task> tasks){
        switch(intInput){
            case 1:
                enterAppointment(tasks);
                break;
            case 2:
                enterMeeting(tasks);
                break;
            case 3:
                enterVacation(tasks);
                break;
            case 4:
                enterAssignmentDue(tasks);
                break;
            case 5:
                System.out.println("Leaving...");
                donePicking = true;
                break;
        }
    }


    private void resetAllVariables(){   //Resets all variables that need to be reset    //Doesn't reset endHr and endMin
        intInput = 0;
        name = null;
        date = null;
        endDate = null;
        location = null;
        note = null;
        agenda = null;
        subject = null;
        startHr = -1;
        startMin = -1;
        optionalLocInput = -1;
        optionalNoteInput = -1;
        optionalEndTime = -1;
        optionalEndDate = -1;
        optionalAgendaInput = -1;
        optionalSubjectInput = -1;
        optionalEnterMore = -1;
        optionToDisplayTasks = -1;
    }


    private void choiceToDisplayTasks(ArrayList<Task> tasks){   //This could be used later on, if the program would have more features, this could be one of them. But for now, displays all tasks right away
        if(tasks.size() > 0){
            System.out.println("Would you like to display your tasks? 1=Yes 0=No");
            optionToDisplayTasks = -1;
            optionToDisplayTasks = enterValidYesOrNo();
            if(optionToDisplayTasks == 1){
                //Display the tasks here
                for(Task task : tasks){
                    System.out.println(task.getTaskName());
                    System.out.println(task.getDateEntered());
                }
            }
        }
    }
    private void displayTasks(ArrayList<Task> tasks){   //This method is crucial as it displays the results of what the user entered
                                                                    //Might be a little too long
        int taskNo = 1;
            for(Task task : tasks){

                System.out.println("Task No: " + taskNo);   //displays task number

                System.out.println("Name: " + task.getTaskName()); //display name
                if(hasEndDate()){   //If task has start and end dates
                    System.out.println("Start date: " + task.getStartDate());
                    System.out.println("End date: " + task.getEndDate());
                }
                else if(!hasEndDate()){     //if task has only 1 date
                    System.out.println("Date: " + task.getDateEntered());
                }
                if(task instanceof Vacation || task instanceof AssignmentDue){  //Do nothing if the task is vacation or assignment due because those don't take time or only take one time

                }
                else {
                    if (!task.isEndHourEntered()) {    //if task don't have end time
                        task.displayTheTaskTimeWithoutEndTime();    //display time
                    }
                    else if(task.isEndHourEntered()){    //if task has both end time and start time
                        task.displayTheTaskTime();  //display both times
                    }
                }
                if(task instanceof Appointment){    //This checks if the task is an Appointment object
                    Appointment tempAppointment = (Appointment) task;   //creates a temporary Appointment copy
                    if(tempAppointment.hasLocation()){      //if the Appointment has a location
                        String tempLocation = tempAppointment.getLocation();    //This gets the location from the Appointment object and stores in a temp String
                        System.out.println("Location: " + tempLocation);    //prints the location
                    }
                }
                if(task instanceof Appointment){    //If task is an Appointment
                    Appointment tempAppointment = (Appointment) task;   //Creates a copy of that Appointment
                    if(tempAppointment.hasNote()){  //If appointment has a note after checking for it
                        String tempNote = tempAppointment.getNote();    //Create a tempString that will store the note
                        System.out.println("Note: " + tempNote);    //Print the note
                    }
                }
                if(task instanceof Meeting){    //This checks if the task is a Meeting object
                    Meeting tempMeeting = (Meeting) task;   //creates a temporary Meeting copy
                    if(tempMeeting.hasLocation()){      //if the Meeting has a location
                        String tempLocation = tempMeeting.getLocation();    //This gets the location from the Meeting object and stores in a temp String
                        System.out.println("Location: " + tempLocation);    //prints the location
                    }
                }
                if(task instanceof Meeting){    //If the task is a Meeting
                    Meeting tempMeeting = (Meeting) task;   //Create a copy
                    if(tempMeeting.hasAgenda()){    //If the object has agenda
                        String tempAgenda = tempMeeting.getAgenda();    //Create a copy of agenda
                        System.out.println("Agenda: " + tempAgenda);
                    }
                }
                if(task instanceof Vacation){   //If the task is a Vacation
                    Vacation tempVacation = (Vacation) task;    //create a copy
                    if(tempVacation.hasLocation()){     //If the vacation task has a location, make a copy, and print it
                        String tempLoc = tempVacation.getLocation();
                        System.out.println("Location: " + tempLoc);
                    }
                }
                if(task instanceof AssignmentDue){  //If the task is AssignmentDue
                    AssignmentDue tempAssignmentDue = (AssignmentDue) task; //create a copy
                    if(tempAssignmentDue.hasTime){  //if the assignment due has a time constraint
                        System.out.println("Time due: " + tempAssignmentDue.getHour() + ":" + tempAssignmentDue.getMinute());   //print
                    }
                    if(tempAssignmentDue.hasSubject()){     //If the user chose to add the subject
                        System.out.println("Subject: " + tempAssignmentDue.getSubjectWithAssignmentDue());  //print
                    }
                }
                taskNo++;
                System.out.println();

            }
    }



           // ****  methods below that return boolean will be used to check if the task has any of these characteristics  *** \\
    public boolean hasEndDate(){
        return endDate !=null && !endDate.isEmpty();
    }


    public boolean dateEnteredValidation(String dateEntered){
        boolean validDate;
        validDate = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");    //This sets the format of the date
        LocalDate date = null;
        try{
            date = LocalDate.parse(dateEntered, formatter);

        }
        catch(DateTimeParseException e){
            validDate = false;
        }

        return validDate;
    }

    private int enterValidYesOrNo(){    //This has the user enter either  1 or 0, if they don't, they are not getting out
                //Ask the question if the user wants to enter an optional value prior to calling this method
        valid = false;  //resetting the valid boolean
        int optionalChoice = -1;
        Scanner input = new Scanner(System.in);
        while (!valid) {
            try {
                optionalChoice = input.nextInt();
                if(optionalChoice == 1 || optionalChoice == 0){   //if user entered 1 or 0, that is valid
                    valid = true;
                }
                else {
                    while (optionalChoice != 1 || optionalChoice != 0) {
                        System.out.println("Enter either 1 or 0");
                        optionalChoice = input.nextInt();
                        if (optionalChoice == 1 || optionalChoice == 0) {   //this is done again to set the valid to true once the user enters a correct value and breaks out of while loop
                            valid = true;
                            break;
                        }
                    }

                }

            } catch (Exception e) {
                System.out.println("Can't take that as an input. Enter again!");
                input.next();   //This clears the input, and lets the next scanner input be able to get user input (if that makes sense lol)
            }
        }

        return optionalChoice;
    }
    private void enterValidStartHour(){
        valid = false;  //resets the valid boolean
        startHr = -1;
        Scanner input = new Scanner(System.in);

        while (!valid) {    //validation to make sure the user doesn't enter a invalid time as for hour. Ex... 24,25,-1,etc.
            try{    //This tries doing the code below, if the user ends up entering anything other than an int, it catches it
                System.out.println("At what hour does "+ name + " start?");
                startHr = input.nextInt();

                if (startHr >= 0 && startHr < 24) {    //Checks if the user entered in int in range of 24 hour clock hours
                    valid = true;
                }
                else {  //if the user enters an invalid hour
                    System.out.println("There is no such hour. Enter again.");
                    input.nextLine();
                    startHr = input.nextInt();
                    valid = false;
                    while (startHr < 00 || startHr >= 23){  //input validation to have the user enter between 00 and 23
                        System.out.println("There is no such hour. Enter again.");
                        startHr = input.nextInt();
                    }
                    if (startHr >= 0 && startHr <= 23) {    //Checks again after the user messed up
                        valid = true;                               //If true, sets valid to true, meaning this input is now valid
                        //System.out.println("valid");
                    }
                }

            }
            catch(Exception e) {    //Catches if the user enters anything other than an int
                System.out.println("There is no such integer. Enter an integer!");
                input.next();
            }
        }
    }
    private void enterValidStartMin() {
        Scanner input = new Scanner(System.in);
        valid = false;  //resets boolean value so it can be used once again
        startMin = -1;  //reset the value

        while (!valid) {    //validation to make sure the user doesn't enter a invalid time as for hour. Ex... 24,25,-1,etc.
            //As long as the condition in while loop is met (as long as valid is not true or valid ==false) the loop will repeat
            try {    //This tries doing the code below, if the user ends up entering anything other than an int, it catches it
                System.out.println("At what minute does " + name + " start?");
                startMin = input.nextInt();

                if (startMin < 60 && startMin >= 00) {    //Checks if the user entered in int in range of 24 hour clock hours
                    valid = true;   //valid

                } else {  //if the user enters an invalid hour
                    System.out.println("There is no such minute. Enter again.");
                    input.nextLine();
                    startMin = input.nextInt();
                    valid = false;
                    while (startMin < 60 && startMin >= 00) {  //input validation to have the user enter between 00 and 23
                        System.out.println("There is no such minute. Enter again.");
                        startHr = input.nextInt();
                    }
                    if (startMin < 60 && startMin >= 00) {    //Checks again after the user messed up
                        valid = true;                               //If true, sets valid to true, meaning this input is now valid
                        //System.out.println("valid");
                    }
                }

            } catch (Exception e) {    //Catches if the user enters anything other than an int
                System.out.println("Can't take that input. Enter an integer between 00 and 59!");
                input.next();
            }
        }
    }
        private void enterValidEndHour(){

            Scanner input = new Scanner(System.in);
            if(optionalEndTime == 1) {
                valid = false; //resetting the valid boolean
                endHr = -1;  //resets the value of the endHr
                while (!valid) {    //validation to make sure the user doesn't enter a invalid time as for hour. Ex... 24,25,-1,etc.
                    try {    //This tries doing the code below, if the user ends up entering anything other than an int, it catches it
                        System.out.println("At what hour does " + name + " end?");
                        endHr = input.nextInt();

                        if (endHr >= 0 && endHr < 24) {    //Checks if the user entered in int in range of 24 hour clock hours
                            valid = true;   //valid

                        } else {  //if the user enters an invalid hour
                            System.out.println("There is no such hour. Enter again.");
                            input.nextLine();
                            endHr = input.nextInt();
                            valid = false;
                            while (endHr < 00 || endHr > 23) {  //input validation to have the user enter between 00 and 23
                                System.out.println("There is no such hour. Enter again.");
                                endHr = input.nextInt();
                            }
                            if (endHr >= 0 && endHr <= 23) {    //Checks again after the user messed up
                                valid = true;                               //If true, sets valid to true, meaning this input is now valid
                                //System.out.println("valid");
                            }
                        }
                    } catch (Exception e) {    //Catches if the user enters anything other than an int
                        System.out.println("There is no such integer. Enter an integer!");
                        input.next();
                    }
                }
            }
        }

        private void enterValidEndMin(){

            Scanner input = new Scanner(System.in);
            if(optionalEndTime == 1) {
                valid = false; //resetting the valid boolean
                endMin = -1;  //resets the value of the endHr
                while (!valid) {    //validation to make sure the user doesn't enter a invalid time as for hour. Ex... 24,25,-1,etc.
                    try {    //This tries doing the code below, if the user ends up entering anything other than an int, it catches it
                        System.out.println("At what minute does "+ name + " end?");
                        endMin = input.nextInt();

                        if (endMin >= 00 && endMin <= 59) {    //Checks if the user entered in int in range of 24 hour clock hours
                            valid = true;   //valid

                        } else {  //if the user enters an invalid hour
                            System.out.println("There is no such minute. Enter again.");
                            input.nextLine();
                            endHr = input.nextInt();
                            valid = false;
                            while (endHr < 00 || endHr >= 59) {  //input validation to have the user enter between 00 and 23
                                System.out.println("There is no such minute. Enter again.");
                                endHr = input.nextInt();
                            }
                            if (endHr >= 0 && endHr <= 59) {    //Checks again after the user messed up
                                valid = true;                               //If true, sets valid to true, meaning this input is now valid
                                //System.out.println("valid");
                            }
                        }
                    } catch (Exception e) {    //Catches if the user enters anything other than an int
                        System.out.println("There is no such integer. Enter an integer!");
                        input.next();
                    }
                }
            }
        }


    // ******************************************************** Appointment ******************************************************

    public void enterAppointment(ArrayList<Task> tasks){    //This method takes in the array list of tasks that was previously created.
                //This method is used to create an appointment on the schedule the way the user wants to create it
        //running methods below that will get the user to enter the values for the appointment
        enterAppointmentName();
        enterAppointmentDate();
        enterStartTimeOfAppointment();
        enterEndTimeOfAppointment();
        enterAppointmentLocation();
        enterAppointmentNote();
        //Create if statements for all possibilities of appointment. The user couldve said they don't want to enter endTime or location or note
        //This probably could be done in a separate method


        if (optionalEndTime == 1 && optionalLocInput == 1 && optionalNoteInput == 1){   //If the user chooses to enter all the options
            tasks.add(new Appointment(name,date,startHr,startMin,endHr,endMin,location,note));
        }
        else if (optionalEndTime == 1 && optionalLocInput ==1){ //If the user  chooses to input end time and location
            tasks.add(new Appointment(name,date,startHr,startMin,endHr,endMin,location));
        }
        else if (optionalEndTime == 1 ){    //If the user chose to input only optional end time
            tasks.add(new Appointment(name, date, startHr, startMin, endHr, endMin));
        }
        else if ((optionalEndTime < 1 && optionalLocInput == 1) && optionalNoteInput < 1){ //If the user chose to input only location and not endTime or note
            tasks.add(new Appointment(name,date,startHr,startMin,location));
        }
        else if (optionalLocInput == 1 && optionalNoteInput == 1){
            tasks.add(new Appointment(name,date,startHr,startMin,location,note));   //creating an object with a note in it
        }
        else if(optionalEndTime < 1 && optionalLocInput < 1){   //if the user doesn't want to enter location or end time or note
            tasks.add(new Appointment(name,date,startHr,startMin)); //most basic option
        }

        //resetAllVariables();    //resets all variables for less future confusion and saving from previous tasks
    }




    public void enterAppointmentName(){
        name = null;
        System.out.println("Enter the name of the appointment");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
    }

    public void enterAppointmentDate(){
        date = null;
        System.out.println("Enter the date of the appointment in the format mm/DD/yyyy");
        Scanner input = new Scanner(System.in);
        date = input.nextLine();

        if(dateEnteredValidation(date)){
            System.out.println("Date accepted");
        }
        else {
            while (dateEnteredValidation(date) == false) {
                System.out.println("Can't take that date, please enter again a valid date in the right format");
                date = input.nextLine();
            }
        }


    }

    public void enterStartTimeOfAppointment(){  //This method asks the user what time the appointment is at
        enterValidStartHour();  //asks for hour
        enterValidStartMin();   //asks for min
    }

    public void enterEndTimeOfAppointment(){  //This method asks the user if they want to enter the end hour for their appointment, and if they do it adds the end hour.
                //It is crucial that this method is called before doing the end minute, since only in this method the user enters a value into optionalEndTime.
                    //In minutes, if optional end time is 1, the user has no choice but to enter a value for minutes


        System.out.println("Would you like to enter the end time for " + name + "? 1=Yes 0=No");
        optionalEndTime = enterValidYesOrNo();  //This checks if the user entered yer or no (with validation)
        enterValidEndHour();    //This method has the user enter the hour if they said yes to entering end time
        enterValidEndMin();
    }

    public void enterAppointmentLocation(){
        optionalLocInput = -1;
        location = null;
        System.out.println("Would you like to enter the appointment location? 1=Yes 0=No");
        optionalLocInput = enterValidYesOrNo();

        if(optionalLocInput == 1){    //This is only in case the user chooses 1, or yes to adding a location
            System.out.println("Enter the location:");
            Scanner userInput = new Scanner(System.in);
            location = userInput.nextLine();
        }
    }
    public void enterAppointmentNote(){
        if (optionalLocInput == 1) {    //only if the user chose yes to enter a location
            optionalNoteInput = -1; //resets the values
            note = null;    //reset the value
            System.out.println("Would you like to enter a note to this appointment? 1=Yes, 0=No");
            optionalNoteInput = enterValidYesOrNo();    //Gets the user to enter either 1 or 0. the method that is called should be bulletproof in validation

            if (optionalNoteInput == 1) {
                System.out.println("Enter a note:");
                Scanner userInput = new Scanner(System.in);
                note = userInput.nextLine();
            }
        }

    }


            //********************************************************* Meeting **********************************************************************
    private void enterMeeting(ArrayList<Task> tasks){
        enterMeetingName();
        enterMeetingDate();
        enterStartTimeOfMeeting();
        enterEndTimeOfMeeting();
        enterMeetingLocation();
        enterMeetingAgenda();

        if(optionalEndTime == 1 && optionalLocInput == 1 && optionalAgendaInput == 1){  //Case where the user chooses to enter end time, location and agenda
            tasks.add(new Meeting(name,date,startHr,startMin,endHr,endMin,location,agenda));
        }
        else if(optionalEndTime == 1 && optionalLocInput < 1){
            tasks.add(new Meeting(name,date,startHr,startMin,endHr,endMin));    //Case where the user chooses to enter end time, but not location and/or agenda
        }
        else if(optionalEndTime < 1 && optionalLocInput == 1 && optionalAgendaInput == 1){  //case if the user chooses not to enter end time, but location and agenda
            tasks.add(new Meeting(name,date,startHr,startMin,location,agenda));
        }
        else if(optionalEndTime < 1 && optionalLocInput == 1){  //If the user chooses not to enter end time, but location only
            tasks.add(new Meeting(name,date,startHr,startMin,location));
        }
        else if(optionalEndTime < 1 && optionalLocInput < 1){   //Case where the user chooses to enter only name,date, and start time
            tasks.add(new Meeting(name,date,startHr,startMin)); //most basic option
        }

        //resetAllVariables();
    }
    private void enterMeetingName (){
        name = null;
        System.out.println("Enter the name of the meeting");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
    }
    public void enterMeetingDate(){
        date = null;
        System.out.println("Enter the date of the meeting in the format (mm/DD/yyyy)");
        Scanner input = new Scanner(System.in);
        date = input.nextLine();

        if(dateEnteredValidation(date)){
            System.out.println("Date accepted");
        }
        else {
            while (dateEnteredValidation(date) == false) {
                System.out.println("Date isn't valid. Please enter again.");
                date = input.nextLine();
            }
        }

    }
    public void enterStartTimeOfMeeting(){  //This method asks the user what time the meeting is at
        enterValidStartHour();
        enterValidStartMin();
    }
    public void enterEndTimeOfMeeting(){  //This method asks the user if they want to enter the end hour for their meeting, and if they do it adds the end hour.
        //It is crucial that this method is called before doing the end minute, since only in this method the user enters a value into optionalEndTime.
        //In minutes, if optional end time is 1, the user has no choice but to enter a value for minutes
        System.out.println("Would you like to enter the end time of this meeting? 1=Yes 0=No");
        optionalEndTime = -1;   //resetting the value so this method can be reused
        optionalEndTime = enterValidYesOrNo();
        enterValidEndHour();    //get end hour input
        enterValidEndMin();     //get end min input
    }
    public void enterMeetingLocation(){
        optionalLocInput = -1;
        location = null;
        System.out.println("Would you like to enter a location and/or an agenda for " + name + " ?" + " 1=Yes 0=No");
        optionalLocInput = enterValidYesOrNo(); //gets a 1 or 0 from user

        if(optionalLocInput == 1){    //This is only in case the user chooses 1, or yes to adding a location
            System.out.println("Enter the location:");
            Scanner userInput = new Scanner(System.in);
            location = userInput.nextLine();
        }
    }
    public void enterMeetingAgenda(){

        if(optionalLocInput == 1){
            optionalAgendaInput = -1;  //resets the optionalAgendaInput value
            System.out.println("Would you like to enter an agenda? 1=Yes 0=No");
            optionalAgendaInput = enterValidYesOrNo();  //gets a 1 or 0 from user

            if(optionalAgendaInput == 1){
                agenda = null;  //resets the agenda value
                System.out.println("Enter the agenda: ");
                Scanner input = new Scanner(System.in);
                agenda = input.nextLine();
            }
        }
    }



    //********************************************************** Vacation ************************************************************

    private void enterVacation(ArrayList<Task> tasks){
        enterVacationName();
        enterVacationStartDate();
        enterVacationEndDate();
        enterVacationLocation();

        if(optionalEndDate == 1 && optionalLocInput == 1){
            tasks.add(new Vacation(name,date,endDate,location));
        }
        if(optionalEndDate == 1 && optionalLocInput != 1){
            tasks.add(new Vacation(name,date,endDate));
        }
        if(optionalEndDate != 1 && optionalLocInput != 1){
            tasks.add(new Vacation(name,date));
        }
        else{
            System.out.println("no tasks added");
        }

    }
    private void enterVacationName (){
        name = null;
        System.out.println("Enter the name of the vacation");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
    }
    private void enterVacationStartDate(){
        date = null;    //reset the value, so method can be used many times
        System.out.println("Enter the date of " + name + " in format mm/DD/yyyy");
        Scanner input = new Scanner(System.in);
        date = input.nextLine();

        if(dateEnteredValidation(date)){
            System.out.println("Date accepted");
        }
        else {
            while (dateEnteredValidation(date) == false) {
                System.out.println("Date isn't valid. Please enter again.");
                date = input.nextLine();
            }
        }
    }
    private void enterVacationEndDate(){
        optionalEndDate = -1;   //resets the choice value
        System.out.println("Would you like to enter the end date of " + name + "? 1=Yes 0=No");
        optionalEndDate = enterValidYesOrNo();

        if(optionalEndDate == 1){
            endDate = null;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the end date: ");
            endDate = input.nextLine();
            if(dateEnteredValidation(endDate)){
                System.out.println("Date accepted");
            }
            else {
                while (dateEnteredValidation(endDate) == false) {
                    System.out.println("Date isn't valid. Please enter again.");
                    endDate = input.nextLine();
                }
            }
        }
    }
    private void enterVacationLocation(){    //this methods asks the user if they want to enter a location, and if they choose 1, they get to input the location
        optionalLocInput = -1;
        if(optionalEndDate == 1) {
            System.out.println("Would you like to enter the location for " + name + "? 1=Yes 0=No");
            optionalLocInput = enterValidYesOrNo();

            if (optionalLocInput == 1) {  //If the user inputs 1, they get to enter the location
                location = null;
                Scanner input = new Scanner(System.in);
                System.out.println("Enter the location: ");
                location = input.nextLine();
            }
        }
    }



    //********************************************************** Assignment Due ****************************************************************

    private void enterAssignmentDue(ArrayList<Task> tasks){
        enterAssignmentDueName();
        enterAssignmentDueDueDate();
        enterAssignmentDueTime();
        enterAssignmentDueSubject();

        if(optionalEndTime == 1 && optionalSubjectInput == 1 ){
            tasks.add(new AssignmentDue(name,date,endHr,endMin, subject));
        }
        if(optionalEndTime == 1 && optionalSubjectInput !=1){
            tasks.add(new AssignmentDue(name,date,endHr,endMin));
        }
        if(optionalEndTime !=1 && optionalSubjectInput ==1){
            tasks.add(new AssignmentDue(name,date,subject));
        }
        if(optionalEndTime !=1 && optionalSubjectInput != 1){
            tasks.add(new AssignmentDue(name,date));
        }

        //resetAllVariables();
    }
    private void enterAssignmentDueName(){
        name = null;    //reset name value
        System.out.println("Enter the name of the assignment");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
    }
    private void enterAssignmentDueDueDate(){
        date = null;    //reset the value, so method can be used many times
        System.out.println("Enter the due date of " + name + " in format mm/DD/yyyy");
        Scanner input = new Scanner(System.in);
        date = input.nextLine();
        if(dateEnteredValidation(date)){
            System.out.println("Date accepted");
        }
        else {
            while (dateEnteredValidation(date) == false) {
                System.out.println("Date isn't valid. Please enter again.");
                date = input.nextLine();
            }
        }
    }
    private void enterAssignmentDueTime(){  //This method lets the user choose and enter the due time in hours and minutes
        System.out.println("Would you like to enter the end time for " + name + "? 1=Yes 0=No");
        optionalEndTime = -1;
        optionalEndTime = enterValidYesOrNo();  //This get the user's input either 1 or 0

        if(optionalEndTime == 1){   //If the user chose to enter the due time
            enterValidEndHour();    //sets endHour
            enterValidEndMin();     //sets endMin
        }
    }
    private void enterAssignmentDueSubject(){
        System.out.println("Would you like to enter the subject? 1=Yes 0=No");
        optionalSubjectInput = -1;
        optionalSubjectInput = enterValidYesOrNo(); //Has the user enter either 1 or 0
        if(optionalSubjectInput == 1){
            System.out.println("Enter the subject: ");
            Scanner input = new Scanner(System.in);
            subject = input.nextLine();
        }
    }






    //=========================================getters and setters


    private String getLocation() {
        return location;
    }

    private String getNote() {
        return note;
    }

    private String getAgenda() {
        return agenda;
    }

    private String getSubject() {
        return subject;
    }
}
