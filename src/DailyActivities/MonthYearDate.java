package DailyActivities;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;

public class MonthYearDate {

        //Problem im encountering right now is that the dateEntered,startDate,endDate are not keeping their format for some reason. Even after they are assigned in a method

    public LocalDate dateEntered;
    public LocalDate startDate, endDate;
    boolean validDate;

    public MonthYearDate(String date){  //This is used for tasks that will be done in one day or during the day
        dateEntered = handleAndReturnUserInputInSetFormat(date);


    }
    public MonthYearDate(String startDate, String endDate){ //This is used for bit events like vacation to plan it to have a start date and a end date
        this.startDate = handleAndReturnUserInputInSetFormat(startDate);
        this.endDate = handleAndReturnUserInputInSetFormat(endDate);


    }

    //===================================================================================================================================================
    //===================================================================================================================================================

    private LocalDate handleAndReturnUserInputInSetFormat(String dateEntered){  //This method handles if the date passed through in the wrong format, transforms the String date into LocalDate, and returns the LocalDate

        validDate = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");    //This sets the format of the date
            LocalDate date = null;
        try{    //This is the valid day case
            date = LocalDate.parse(dateEntered, formatter);
            validDate = true;
            //System.out.println("Date is valid!\n");
        }
        catch (DateTimeParseException e){
            System.out.println("Date entered in invalid format!\n");
            validDate = false;
        }
        catch (Exception e){
            System.out.println("Can't take that date. Enter again");
        }
        return date;
    }


    public void printDateEntered(){
        System.out.println("Date entered is " + dateEntered);
    }
    public String dateEnteredtoString() {
        return  '{' +
                "dateEntered=" + dateEntered +
                '}';
    }
    public String startAndEndDateToString() {
        return
                "startDate=" + startDate +
                ", endDate=" + endDate
                ;
    }

    public LocalDate getDateEntered(){
        return dateEntered;
    }
    public boolean checkIfTaskHasEndDate(){
        return endDate != null;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /*
    public boolean hasEndDate(){
        return endDate !=null;
    }

     */

    //BEWARE! WACK CODE BELOW

    //=======================================================================================================================================
    /*
    private Date parseDate(String dateEntered) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.parse(dateEntered);
    }

    private Date handleAndReturnUserInputInDateFormat(String dateEntered){
        Date date = null;
        try {
            date = parseDate(dateEntered);
            System.out.println("Date entered is: " + date);
        }
        catch(ParseException e) {
            System.out.println("Date entered in an invalid format!");

        }

        return date;
    }
    */


}



