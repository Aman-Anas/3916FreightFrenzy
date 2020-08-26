/*
Apex Robotics FTC Team 3916: Robot Class
Created with Android Studio v3.3.1, Gradle v4.10.1
Template created by Gabrian Chua (2019)

This Robot class is the main class that defines a Robot.
Most classes will create a Robot object from this class for behavior.
 */

package org.firstinspires.ftc.teamcode;

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
 * @author will simpson of apex robotics 3916
 * @since December 2018
 */
public class FTCLibRobot {
    // defines what omni_drive types can be used
    public enum DriveType {Tank, Omni, Mechanum}

    // stores what kind of DriveType is currently being used

    public DriveType currentDriveType;
    //all electronic components for the robot


    // METERS; radius of the robot based on the circle traced by its wheels when turning
    private static final double BOT_RADIUS = .535; // not real, actual value tbd
    // METERS; radius of one of the robot's wheels
    private static final double WHEEL_RADIUS = .1; // not real, actual value tbd
    // RADIANS; angle created by the grabber system completely raised against the arm and an abstract horizontal line level with servo
    // NOTICE: this is specific to how the servos are mounted and may differ for other configurations
    public static final double GRABBER_LIFT_UP = 1; // not real, actual value tbd
    // RADIANS; angle created by grabber system equal to abstract horizontal line level with servo
    public static final double GRABBER_LIFT_DOWN = 0; // not real, actual value tbd

    // amount of motor ticks per revolution for 40:1 REV hex motor. other motors may have different TPR
    private static final double TICKS_PER_REV = 2440;




    /**
     * initialize robot components; will allow motors and servos to tighten and not freely move
     *
     * COMPONENT SPECS:
     * motor power range - -1 to 1
     * servo power range - 0 to 1
     * servo angle range (default 180 deg/ pi rad) - 0 to 1
     *
     * @param hw - HardwareMap supplied from drive class
     */
    public void init(HardwareMap hw, DriveType driveType) {

    }


    /**
     * Move a mechanum style drive using the stick values
     * @param stickX - stick value in the x direction
     * @param stickY - stick value in the y direction
     */


    /**
     * determines if any motors supplied are busy (only return false when !isBusy() for all)
     * @param motors - list of motors to test
     * @return - true if >= 1 are busy, false if 0 are busy
     */

    private boolean motorsAreBusy(List<DcMotor> motors) {
        int finished = 0;

        for (DcMotor m : motors) {
            if (!m.isBusy())
                finished++;

            if (finished == motors.size())
                return false;
        }

        return true;
    }

    /**
     * start a thread to pause an action but not pausing all other concurrent actions
     */
    private class Wait implements Runnable {
        private String component;
        private long duration;

        Wait(String component, long duration) {
            this.component = component;
            this.duration = duration;
        }

        @Override
        public void run() {
            try {
                // do something if we need to use this

                Thread.sleep(duration);
            } catch (InterruptedException e) {
                return;
            }
        }
    }


}