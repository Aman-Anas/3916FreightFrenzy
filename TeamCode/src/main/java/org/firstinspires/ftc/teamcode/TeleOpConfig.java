package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {

    //Variables to be tuned from FTCDashboard

    //Drivetrain control tuning
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed
    public static double STICK_DEAD_ZONE = 0.03;
    
}
