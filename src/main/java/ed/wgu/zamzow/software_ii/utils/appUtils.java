package ed.wgu.zamzow.software_ii.utils;

import ed.wgu.zamzow.software_ii.objects.Appointment;
import javafx.scene.control.Alert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class appUtils {

    public static String getLanguange() {
        return Locale.getDefault().getLanguage();
    }

    public static void ShowDBWriteError(String type, String table, String error) {
        Alert warn = new Alert(Alert.AlertType.ERROR);
        warn.setTitle("Error");
        warn.setContentText("There was an error " + type + " a " + table + "\n" + error);
        warn.show();
    }

    public static Timestamp convertDateTime(String initDate, String hour, String minute) {
        Timestamp returnDateTime = null;
        Calendar cal = getCalendarDateTime(initDate, hour, minute);

        returnDateTime = new Timestamp(cal.getTimeInMillis());
        return returnDateTime;
    }

    public static Timestamp localToGMT(Timestamp cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = new Date(sdf.format(cal));

        return new Timestamp(date.getTime());
    }

    public static Timestamp GMTTolocal(Timestamp cal) {
        Date date = new Date(cal.getTime());
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Date local = new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
        return new Timestamp(local.getTime());
    }

    private static Calendar getCalendarDateTime(String initDate, String hour, String minute) {
        Calendar cal  = Calendar.getInstance();
        if (initDate.contains("/")) {
            String[] splitDate = initDate.split("/");

            cal.set(Calendar.YEAR, parseInt(splitDate[2]));
            cal.set(Calendar.MONTH, parseInt(splitDate[1]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, parseInt(splitDate[0]));
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

    public static String splitDate(boolean isDate, String dateTime) {
        String[] splitDateTime = dateTime.split(" ");
        if (isDate) {
            return splitDateTime[0];
        } else {
            return splitDateTime[1];
        }
    }

    public static String splitTime(int type, String time) {
        String[] splitTime = time.split(":");
        if (type == 1) {
            return splitTime[0];
        } else {
            return splitTime[1];
        }
    }

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

    public static int hourOffset() {
        Calendar est = Calendar.getInstance(TimeZone.getTimeZone("EST"));

        String timeZone = Calendar.getInstance().getTimeZone().getID();
        Calendar local = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        long diffInMillies = Math.abs(est.getTimeInMillis() - local.getTimeInMillis());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int)diff;
    }


}
