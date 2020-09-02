/*
Apex Robotics FTC Team 3916: Robot Class
Created with Android Studio v3.3.1, Gradle v4.10.1
Template created by Gabrian Chua (2019)

This Robot class is the main class that defines a Robot.
Most classes will create a Robot object from this class for behavior.
 */

package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.SimpleMotor;
import com.arcrobotics.ftclib.hardware.motors.SimpleMotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.List;

/*
 * -- TEAM 3916 --
 * Robot class for easier component control across multiple drive mode classes
 * Robot functionality handled here for consistency and avoiding bugs and simplicity in driver classes
 * NOTICE: all dimensions are measured in meters (in case not specified). all measurements should be taken in meters or else calculations will absolutely mess up
 * NOTICE: all angles are measured in radians (in case not specified). please don't ever use degrees for angles
 *
 * @author Will Simpson
 * @author Gabrian Chua
 * @author Aman Anas
 *
 * @since December 2018
 * @version August 2020
 *
 */
public class FTCLibDriveCode {
    // defines which drivetrain types can be used
    public enum DriveType {Tank, Omni, Mecanum}

    /**
     * Tank drive: 2 motor drivetrain, very simple
     * Omni drive: 4 motor holonomic drivetrain, can strafe. Wheels mounted at 45 degree angles facing outwards.
     * Mecanum drive: 4 motor holonomic drivetrain, can strafe. Wheels mounted similarly to a tank drivetrain.
     */

    // stores what kind of DriveType is currently being used
    public DriveType activeDriveType;


    // cached HardwareMap in case needed for future
    public HardwareMap hw;

    /**
     * Robot drivetrain dimensions and wheel Information
     */
    // METERS; radius of the robot based on the circle traced by its wheels when turning
    private static final double BOT_RADIUS = .535; // not real, actual value tbd
    // METERS; radius of one of the robot's wheels
    private static final double WHEEL_RADIUS = .1; // not real, actual value tbd
    // Number of motor ticks per revolution for current drivetrain motors.
    private static final double TICKS_PER_REV = 1120;

    /**
     * Current drivetrain motors
     */

    //Mecanum Drivetrain
    private SimpleMotorEx mecanum_frontLeft;
    private SimpleMotorEx mecanum_frontRight;
    private SimpleMotorEx mecanum_backLeft;
    private SimpleMotorEx mecanum_backRight;

    public MecanumDrive mecanumDrivetrain;


    //Holonomic Omni Drivetrain
    private SimpleMotorEx omni_frontLeft;
    private SimpleMotorEx omni_frontRight;
    private SimpleMotorEx omni_backLeft;
    private SimpleMotorEx omni_backRight;

    public HDrive omniDrivetrain;


    //Tank Drivetrain
    private SimpleMotorEx tank_left;
    private SimpleMotorEx tank_right;

    public Tank_Drive tankDrivetrain;

    /**
     * initialize drivetrain
     * @param hw - HardwareMap supplied from drive class
     */

    public void init(HardwareMap hw, DriveType driveType) {
        activeDriveType = driveType;
        this.hw = hw;

        switch (driveType){
            case Mecanum:
                //Assign motors using their hardware map names, each drivetype can have different names if needed
                mecanum_frontLeft = new SimpleMotorEx("left front", hw, TICKS_PER_REV);
                mecanum_frontRight = new SimpleMotorEx("right front", hw, TICKS_PER_REV);
                mecanum_backLeft = new SimpleMotorEx("left back", hw, TICKS_PER_REV);
                mecanum_backRight = new SimpleMotorEx("right back", hw, TICKS_PER_REV);
                //Initialize the FTCLib drivebase
                mecanumDrivetrain = new MecanumDrive(mecanum_frontLeft,mecanum_frontRight,
                                               mecanum_backLeft, mecanum_backRight);
            case Omni:
                //Assign motors using their hardware map names, each drivetype can have different names if needed
                omni_frontLeft = new SimpleMotorEx("left front", hw, TICKS_PER_REV);
                omni_frontRight = new SimpleMotorEx("right front", hw, TICKS_PER_REV);
                omni_backLeft = new SimpleMotorEx("left back", hw, TICKS_PER_REV);
                omni_backRight = new SimpleMotorEx("right back", hw, TICKS_PER_REV);
                //Initialize the FTCLib drivebase
                omniDrivetrain = new HDrive(omni_frontLeft, omni_frontRight,
                                    omni_backLeft, omni_backRight);

            case Tank:
                //Assign motors using their hardware map names, each drivetype can have different names if needed
                tank_left = new SimpleMotorEx("left front", hw, TICKS_PER_REV);
                tank_right = new SimpleMotorEx("right front", hw, TICKS_PER_REV);
                //Initialize a simple custom drivebase
                tankDrivetrain = new Tank_Drive(tank_left, tank_right);


        }

    }
}