package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {

    //Variables to be tuned from FTCDashboard

    //Drivetrain control tuning
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed (for precision mode)
    public static double STICK_DEAD_ZONE = 0.03;
    public static double LINEAR_SLIDE_MULTIPLIER = 1;
    public static double INTAKE_MOTOR_MULTIPLIER = 0.5;
    public static double DUCK_MOTOR_MULTIPLIER = 1;
    public static double FOREARM_SERVO_MULTIPLIER = 1;
    public static double CLAW_SERVO_MULTIPLIER = 1;
    public static double BUCKET_SERVO_MAX = 0.515;
    public static double BUCKET_SERVO_MIN = 0.273;
    public static double GATE_SERVO_MAX = 0.6;
    public static double GATE_SERVO_MIN = 0.276;
    public static double SLIDE_MOTOR_MAX = 3450;
    public static double BUCKET_LIFT_POINT = 650; // Point where the bucket lifts a little bit to prevent freight falling out
    public static double BUCKET_DROP_POINT = 1500; // Point where the bucket drops down to intake again
    public static double BUCKET_LIFT_ANGLE = 0.416; // Intermediate angle the bucket goes to while moving past the intake bar (only going up)
}
