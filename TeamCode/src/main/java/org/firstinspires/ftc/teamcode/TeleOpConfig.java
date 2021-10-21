package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {

    //Variables to be tuned from FTCDashboard

    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed

    public static double STICK_DEAD_ZONE = 0.1;
    public static double FLYWHEEL_KP = 25;
    public static double FLYWHEEL_KI = 0;
    public static double FLYWHEEL_KD = 10;
    public static double WOBBLE_ARM_MULTIPLIER = 800.0;
    public static double WOBBLE_SERVO_MULTIPLIER = 3.0;
    public static double TRANSFER_SERVO_MULTIPLIER = 0.9;
    public static double INTAKE_MULTIPLIER = 0.3;

    public static double ANCHOR_POINT_X = 110;
    public static double ANCHOR_POINT_Y = 245;
    public static double REGION_WIDTH_X = 60;
    public static double REGION_HEIGHT_Y = 24;

    public static double FOUR_RING_THRESHOLD_CONFIG = 145;
    public static double ONE_RING_THRESHOLD_CONFIG = 129;


}
