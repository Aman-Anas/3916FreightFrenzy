/*
Apex Robotics FTC Team 3916
Created with Android Studio v3.3.1, Gradle v4.10.1
Template created by Gabrian Chua (2019)

FTCLib Bot configured with Mecanum Drive.
*/

package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_RPM;

/**
 * -- TEAM 3916 --
 * Robot class using FTCLib library. Used to abstract specific robot functionality and avoid bugs.
 * NOTICE: all dimensions are measured in meters (in case not specified). all measurements should be taken in meters or else calculations will absolutely mess up
 * NOTICE: all angles are measured in radians (in case not specified). please don't ever use degrees for angles
 *
 * @author Will Simpson
 * @author Gabrian Chua
 * @author Aman Anas
 *
 * @since December 2018
 * @version November 2020
 *
 */

public class FTCLibMecanumBot {
    /*
        This class only uses Mecanum Drive. See Junkyard branch for archived code for other drive types.
     */

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
    private static final double TICKS_PER_REV = DriveConstants.TICKS_PER_REV;

    /**
     * Current drivetrain motors
     */

    //Mecanum Drivetrain
    public MotorEx motor_frontLeft;
    public MotorEx motor_frontRight;
    public MotorEx motor_backLeft;
    public MotorEx motor_backRight;
    public MecanumDrive mecanumDrivetrain;

    /**
     * initialize drivetrain
     * @param hw - HardwareMap supplied from drive class
     */

    public void init(HardwareMap hw) {
        //cache the HardwareMap
        this.hw = hw;

        //Assign motors using their hardware map names, each drivetype can have different names if needed
        motor_frontLeft = new MotorEx(hw, "left front", TICKS_PER_REV, MAX_RPM);
        motor_frontRight = new MotorEx(hw, "right front", TICKS_PER_REV, MAX_RPM);
        motor_backLeft = new MotorEx(hw, "left back", TICKS_PER_REV, MAX_RPM);
        motor_backRight = new MotorEx(hw, "right back", TICKS_PER_REV, MAX_RPM);

        //Initialize the FTCLib drivebase
        mecanumDrivetrain = new MecanumDrive(motor_frontLeft, motor_frontRight,
                motor_backLeft, motor_backRight);
    }
}