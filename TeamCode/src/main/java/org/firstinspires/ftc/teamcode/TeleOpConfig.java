package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {

    //Variables to be tuned from FTCDashboard

    //Drivetrain control tuning
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed
    public static double STICK_DEAD_ZONE = 0.03;
    public static double LINEAR_SLIDE_MULTIPLIER = 1; // This is a dummy variable, I have no clue what this should be
    public static double INTAKE_MOTOR_MULTIPLIER = 1; // idk does stuff ig, might work in the future when I'm not here
    public static double DUCK_MOTOR_MULTIPLIER = 1;
    public static double INTAKE_BUCKET_MULTIPLIER = 1;
    public static double INTAKE_ARM_MULTIPLIER = 1;
}
