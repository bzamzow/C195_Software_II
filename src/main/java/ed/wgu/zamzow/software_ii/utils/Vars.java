package ed.wgu.zamzow.software_ii.utils;

import ed.wgu.zamzow.software_ii.objects.Appointment;
import ed.wgu.zamzow.software_ii.objects.User;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class Vars {

    public static Connection con;
    public static String lang;

    public static User currentUser;

    public static String dbError;

    public static ObservableList<Appointment> appointments;
    public static ObservableList<Appointment> futureAppointments;

    public static ObservableList<Appointment> weeklyAppointments;

    public static ObservableList<Appointment> monthlyAppointments;
}
