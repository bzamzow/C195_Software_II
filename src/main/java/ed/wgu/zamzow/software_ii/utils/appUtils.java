package ed.wgu.zamzow.software_ii.utils;

import ed.wgu.zamzow.software_ii.objects.Appointment;
import javafx.scene.control.Alert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

/**
 * Class that contains common methods used throughout the application
 *
 * @author Bret Zamzow
 */
public class appUtils {

    /**
     * Method to get the current language
     * @return
     */
    public static String getLanguange() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * Method to show an error writing to the database
     * @param type
     * @param table
     * @param error
     */
    public static void ShowDBWriteError(String type, String table, String error) {
        Alert warn = new Alert(Alert.AlertType.ERROR);
        warn.setTitle("Error");
        warn.setContentText("There was an error " + type + " a " + table + "\n" + error);
        warn.show();
    }

    /**
     * Method to convert the date and time into a timestamp for use int he databse
     * @param initDate
     * @param hour
     * @param minute
     * @return
     */
    public static Timestamp convertDateTime(String initDate, String hour, String minute) {
        Timestamp returnDateTime = null;
        Calendar cal = getCalendarDateTime(initDate, hour, minute);

        returnDateTime = new Timestamp(cal.getTimeInMillis());
        return returnDateTime;
    }

    /**
     * Method to convert local time into GMT or UTC
     * @param cal
     * @return
     */
    public static Timestamp localToGMT(Timestamp cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = new Date(sdf.format(cal));

        return new Timestamp(date.getTime());
    }

    /**
     * Method to convert GMT or UTC to local time
     * @param cal
     * @return
     */
    public static Timestamp GMTTolocal(Timestamp cal) {
        Date date = new Date(cal.getTime());
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        return new Timestamp(local.getTime());
    }

    /**
     * Method to get a calendar object of the date and time
     * @param initDate
     * @param hour
     * @param minute
     * @return
     */
    private static Calendar getCalendarDateTime(String initDate, String hour, String minute) {
        Calendar cal  = Calendar.getInstance();
        if (initDate.contains("/")) {
            String[] splitDate = initDate.split("/");

            cal.set(Calendar.YEAR, parseInt(splitDate[2]));
            cal.set(Calendar.MONTH, parseInt(splitDate[0]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, parseInt(splitDate[1]));
            cal.set(Calendar.HOUR_OF_DAY, parseInt(hour));
            cal.set(Calendar.MINUTE, parseInt(minute));
            cal.set(Calendar.SECOND, 0);
        }
        else {
            String[] splitDate = initDate.split("-");

            cal.set(Calendar.YEAR, parseInt(splitDate[0]));
            cal.set(Calendar.MONTH, parseInt(splitDate[1]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, parseInt(splitDate[2]));
            cal.set(Calendar.HOUR_OF_DAY, parseInt(hour));
            cal.set(Calendar.MINUTE, parseInt(minute));
            cal.set(Calendar.SECOND, 0);
        }

       return cal;
    }

    /**
     * Method to split the date for various uses
     * @param isDate
     * @param dateTime
     * @return
     */
    public static String splitDate(boolean isDate, String dateTime) {
        String[] splitDateTime = dateTime.split(" ");
        if (isDate) {
            return splitDateTime[0];
        } else {
            return splitDateTime[1];
        }
    }

    /**
     * Method to split the time for various uses
     * @param type
     * @param time
     * @return
     */
    public static String splitTime(int type, String time) {
        String[] splitTime = time.split(":");
        if (type == 1) {
            return splitTime[0];
        } else {
            return splitTime[1];
        }
    }

    /**
     * Method to determine if an appointment is starting soon
     * @param appointment
     * @return
     */
    public static Boolean isAppointmentStartingSoon(Appointment appointment) {
        boolean isAppointmentStartingSoon = false;
        String date = splitDate(true, appointment.getStartDate().toString());
        String time = splitDate(false, appointment.getStartDate().toString());
        String hour = splitTime(1,time);
        String min = splitTime(2, time);
        Calendar cal = getCalendarDateTime(date, hour, min);
        Calendar now = Calendar.getInstance();

        long diffInMillies = Math.abs(cal.getTimeInMillis() - now.getTimeInMillis());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diff <= 15) {
            isAppointmentStartingSoon = true;
        }
        return  isAppointmentStartingSoon;
    }

    /**
     * Method to determine the number of hours ahead of or behind GMT or UTC
     * @return
     */
    public static int hourOffset() {

        ZoneOffset est = getESTOffset();
        ZoneOffset local = getLocalOffset();

        int estStart = Integer.parseInt(est.toString().split(":")[0]);
        int localStart = Integer.parseInt(local.toString().split(":")[0]);

        return localStart - estStart;
    }

    private static ZoneOffset getLocalOffset() {
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        TimeZone curTimeZone = TimeZone.getTimeZone(timeZone);
        ZoneOffset offset = ZonedDateTime.now(ZoneId.of(curTimeZone.getID(), ZoneId.SHORT_IDS)).getOffset();
        return offset;
    }

    private static ZoneOffset getESTOffset() {
        TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
        ZoneOffset offset = ZonedDateTime.now(ZoneId.of(etTimeZone.getID(), ZoneId.SHORT_IDS)).getOffset();
        return offset;
    }


}
