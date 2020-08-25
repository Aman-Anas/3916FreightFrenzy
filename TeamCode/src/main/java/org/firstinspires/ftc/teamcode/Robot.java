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
public class Robot {
    // defines what omni_drive types can be used
    public enum DriveType {Tank, Omni, Mechanum}

    // stores what kind of DriveType is currently being used
    public DriveType currentDriveType;

    /*
     * all electronic components for the robot
     */

    // drive motors required for Omni drive
    private List<DcMotor> omni_leftDrive = new ArrayList<>();
    private List<DcMotor> omni_rightDrive = new ArrayList<>();

    // drive motors required for Tank drive
    private DcMotor tank_leftDrive;
    private DcMotor tank_rightDrive;

    // drive motors required for Mechanum drive
    private DcMotor mech_leftFront;
    private DcMotor mech_leftBack;
    private DcMotor mech_rightFront;
    private DcMotor mech_rightBack;

    // cached HardwareMap in case needed for future
    private HardwareMap hw;

    // auxiliary motors and servos
    private DcMotor aux_motor_1;
    private CRServo aux_servo_1;
    private CRServo aux_servo_2;
    private Servo aux_servo_3;
    private CRServo aux_servo_4;
    private double servo3pos;
    private double servo4pos;

    /*
     * any arbitrary constants required for certain calculations
     */

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

    // constants for the mechanum directions
    private static final MechPower FORWARD = new MechPower(1, 1, 1, 1);
    private static final MechPower BACKWARD = new MechPower(-1, -1, -1, -1);
    private static final MechPower LEFT = new MechPower(1, -1, -1, 1);
    private static final MechPower RIGHT = new MechPower(-1, 1, 1, -1);
    private static final MechPower CLOCKWISE = new MechPower(1,1,-1,-1);
    private static final MechPower COUNTERCLOCKWISE = new MechPower(-1,-1,1,1);

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
        currentDriveType = driveType;
        this.hw = hw;
        switch (driveType) {
            case Tank:
                // get and assign left and right tank drive motors
                tank_leftDrive = hw.get(DcMotor.class, "left drive");
                tank_rightDrive = hw.get(DcMotor.class, "right drive");

                // reverse one motor for correct tank drive pattern
                tank_leftDrive.setDirection(DcMotor.Direction.REVERSE);

                // set motors to no power
                tank_rightDrive.setPower(0);
                tank_leftDrive.setPower(0);
                break;
            case Omni: // defines and assigns electronic components required for Omni Drive
                // add to omni_leftDrive and omni_rightDrive lists the four omni_drive motors
                omni_leftDrive.add(hw.get(DcMotor.class, "left drive | left"));
                omni_leftDrive.add(hw.get(DcMotor.class, "left drive | right"));
                omni_rightDrive.add(hw.get(DcMotor.class, "right drive | left"));
                omni_rightDrive.add(hw.get(DcMotor.class, "right drive | right"));

                // reverse 1 motor per omni_drive side due to corresponding motors being flipped
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);

                // set motors to no power
                for (DcMotor m : omni_leftDrive)
                    m.setPower(0);
                for (DcMotor m : omni_rightDrive)
                    m.setPower(0);
                break;
            case Mechanum:
                // add drive motors from hw
                mech_leftBack = hw.get(DcMotor.class, "left back");
                mech_leftFront = hw.get(DcMotor.class, "left front");
                mech_rightBack = hw.get(DcMotor.class, "right back");
                mech_rightFront = hw.get(DcMotor.class, "right front");

                try {
                    aux_motor_1 = hw.get(DcMotor.class, "aux motor 1");
                } catch (Exception ex) {
                    // no aux motor 1
                }
                try {
                    aux_servo_1 = hw.get(CRServo.class, "aux servo 1");
                } catch (Exception ex) {
                    // no aux servo 1
                }
                try {
                    aux_servo_2 = hw.get(CRServo.class, "aux servo 2");
                } catch (Exception ex) {
                    // no aux servo 2
                }
                /*try {
                    aux_servo_3 = hw.get(Servo.class, "aux servo 3");
                } catch (Exception ex) {
                    // no aux servo 3
                }*/
                aux_servo_3 = hw.get(Servo.class, "aux servo 3");
                try {
                    aux_servo_4 = hw.get(CRServo.class, "aux servo 4");
                } catch (Exception ex) {
                    // no aux servo 4
                }

                // reverse the right motors
                mech_rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
                mech_rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

                // set motors to 0 power
                mech_leftBack.setPower(0);
                mech_leftFront.setPower(0);
                mech_rightBack.setPower(0);
                mech_rightFront.setPower(0);
                break;
            default:
                // an error occurred or an invalid drive type was specified
                // this code should never be called
                throw new RuntimeException();
        }
    }
    public void stopDriving() {
        mech_leftBack.setPower(0);
        mech_leftFront.setPower(0);
        mech_rightBack.setPower(0);
        mech_rightFront.setPower(0);
    }

    /**
     * Move a mechanum style drive using the stick values
     * @param stickX - stick value in the x direction
     * @param stickY - stick value in the y direction
     */
    public MechPower mech_drive(double stickX, double stickY) {
        double finalPowerMultiplier = Math.sqrt(Math.pow(stickX, 2) + Math.pow(stickY, 2));
        double interpolationValue = Math.atan(stickY / stickX) / (Math.PI / 2);
        MechPower finalPwr;
        if (stickX >= 0 && stickY >= 0) {
            //northeast
            finalPwr = FORWARD.interpolate(RIGHT, interpolationValue, finalPowerMultiplier);
        } else if (stickX >= 0 && stickY < 0) {
            //southeast
            finalPwr = BACKWARD.interpolate(RIGHT, interpolationValue, finalPowerMultiplier);
        } else if (stickX < 0 && stickY >= 0) {
            //northwest
            finalPwr = FORWARD.interpolate(LEFT, interpolationValue, finalPowerMultiplier);
        } else if (stickX < 0 && stickY < 0) {
            //southwest
            finalPwr = BACKWARD.interpolate(LEFT, interpolationValue, finalPowerMultiplier);
        } else {
            finalPwr = new MechPower(0,0,0,0);
        }
        mech_leftBack.setPower(finalPwr.leftBack);
        mech_leftFront.setPower(finalPwr.leftFront);
        mech_rightBack.setPower(finalPwr.rightBack);
        mech_rightFront.setPower(finalPwr.rightFront);
        return finalPwr;
    }

    public void mech_rotate(int direction) {
        double turnPower = 0.5;
        if (direction == 0) {
            //clockwise
            mech_leftBack.setPower(CLOCKWISE.leftBack * turnPower);
            mech_leftFront.setPower(CLOCKWISE.leftFront * turnPower);
            mech_rightBack.setPower(CLOCKWISE.rightBack * turnPower);
            mech_rightFront.setPower(CLOCKWISE.rightFront * turnPower);
        } else {
            //counterclockwise
            mech_leftBack.setPower(COUNTERCLOCKWISE.leftBack * turnPower);
            mech_leftFront.setPower(COUNTERCLOCKWISE.leftFront * turnPower);
            mech_rightBack.setPower(COUNTERCLOCKWISE.rightBack * turnPower);
            mech_rightFront.setPower(COUNTERCLOCKWISE.rightFront * turnPower);
        }
    }

    public void mech_rotate(int direction, double turnPower) {
        if (direction == 0) {
            //clockwise
            mech_leftBack.setPower(CLOCKWISE.leftBack * turnPower);
            mech_leftFront.setPower(CLOCKWISE.leftFront * turnPower);
            mech_rightBack.setPower(CLOCKWISE.rightBack * turnPower);
            mech_rightFront.setPower(CLOCKWISE.rightFront * turnPower);
        } else {
            //counterclockwise
            mech_leftBack.setPower(COUNTERCLOCKWISE.leftBack * turnPower);
            mech_leftFront.setPower(COUNTERCLOCKWISE.leftFront * turnPower);
            mech_rightBack.setPower(COUNTERCLOCKWISE.rightBack * turnPower);
            mech_rightFront.setPower(COUNTERCLOCKWISE.rightFront * turnPower);
        }
    }

    public void aux_lift(double power) {
        if (aux_motor_1 != null) {
            aux_motor_1.setPower(power);
        }
    }

    public void aux_claw(double power) {
        if (aux_servo_1 != null) {
            aux_servo_1.setPower(power);
        }
    }

    public void aux_claw2(double power) {
        if (aux_servo_2 != null) {
            aux_servo_2.setPower(power);
        }
    }
    public void aux_claw3(double power) {
        if (aux_servo_3 != null) {
            servo3pos += power;
            servo3pos = Clamp(servo3pos, 0, 1);
            aux_servo_3.setPosition(servo3pos);
        }
    }
    public void aux_claw3_direct(double position) {
        if (aux_servo_3 != null) {
            servo3pos = Clamp(position, 0, 1);
            aux_servo_3.setPosition(servo3pos);
        }
    }
    public void aux_claw4(double power) {
        if (aux_servo_4 != null) {
            aux_servo_4.setPower(power);
        }
    }

    private double Clamp(double i, double min, double max) {
        if (i < min) {
            return min;
        } else if (i > max) {
            return max;
        } else {
            return i;
        }
    }

    public void omni_setDirection(String direction) {
        switch (direction) {
            case "forward":
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);

                omni_rightDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
                break;
            case "backward":
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.FORWARD);

                omni_rightDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
                break;
            case "left":
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.FORWARD);

                omni_rightDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
                break;
            case "right":
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);

                omni_rightDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
                break;
        }
    }

    public void omni_flip(String drive) {
        List<DcMotor> motors = new ArrayList<>();

        if (drive.equals("left") || drive.equals("all")) {
            motors.addAll(omni_leftDrive);
        }

        if (drive.equals("right") || drive.equals("all")) {
            motors.addAll(omni_rightDrive);
        }

        for (DcMotor m : motors) {
            if (m.getDirection() == DcMotor.Direction.FORWARD)
                m.setDirection(DcMotor.Direction.REVERSE);
            else
                m.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    /**
     * simple motor-power-based linear omni_drive method
     * @param drive - specify left or right side of motor omni_drive
     * @param power - -1 to 1; power to supply to each motor; motors should all have same power for smooth turning
     */
    public void omni_drive(String drive, double power) {
        if (drive.equals("left") || drive.equals("all")) {
            for (DcMotor m : omni_leftDrive)
                m.setPower(power);
        }
        if (drive.equals("right") || drive.equals("all")) {
            for (DcMotor m : omni_rightDrive)
                m.setPower(power);
        }
    }

    /**
     * stops motor-power-based movement by setting motor power to 0
     */
    public void omni_stopDriving() {
        omni_drive("all", 0);
    }

    /**
     * simple motor-power-based omni_turn method
     * @param power - -1 to 1; power to supply to each motor; motors should all have same power for smooth turning
     */
    public void omni_turn(double power) {
        // set reversed motors to forward
        omni_leftDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
        omni_rightDrive.get(1).setDirection(DcMotor.Direction.FORWARD);

        for (DcMotor m : omni_leftDrive)
            m.setPower(power);

        for (DcMotor m : omni_rightDrive)
            m.setPower(power);

        // re-reverse proper motors
        omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
        omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
    }

    /**
     * encoder-based omni_turn method for turning a specific amount
     * @param theta - RADIANS; angle of omni_turn relative to the circle drawn by the rotating robot measured in the robot's center
     * @param power - -1 to 1; power to supply to each motor; motors should all have same power for smooth turning
     */
    public void omni_turn(double theta, double power) {
        // all motors must be in same direction
        if (theta > 0) {
            omni_leftDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
            omni_rightDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
        } else {
            omni_leftDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
            omni_rightDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
        }

        /*
         * arc length needed in order to know the distance for each encoder to reach for turning
         * arc length = theta * radius
         * pass arc length into omni_encoderDrive()
         */
        if (theta > 0)
            omni_encoderDrive("all", theta * BOT_RADIUS, power);
        else
            omni_encoderDrive("all", -theta * BOT_RADIUS, power);

        // re-reverse proper motors
        if (theta > 0) {
            omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
            omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
        } else {
            omni_leftDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
            omni_rightDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
        }
    }

    /**
     * Tank/Tread drive that allows setting the motor power to only one, or all, motor.
     * @param power The power to set the motor to, a value from -1 to 1
     * @param drive String to set the power to, either "left" or "right"
     */
    public void tank_drive(double power, String drive) {
        if (drive.equals("left")) {
            tank_leftDrive.setPower(power);
        } else if (drive.equals("right")) {
            tank_rightDrive.setPower(power);
        } else if (drive.equals("all")){
            tank_leftDrive.setPower(power);
            tank_rightDrive.setPower(power);
        } else {
            tank_rightDrive.setPower(0);
            tank_leftDrive.setPower(0);
        }
    }

    /**
     * encoder-based linear omni_drive method for moving a specific distance
     * only calls omni_encoderDrive() but should still exist for simpler implementation and avoiding repeating code in encoder omni_turn()
     * @param drive - specify left or right side of motor omni_drive
     * @param distance - METERS; projected distance for robot to move
     * @param power - -1 to 1; power to supply to each motor; motors should all have same power for smooth turning
     */
    public void omni_driveToPosition(String drive, double distance, double power) {
        if (distance > 0)
            omni_encoderDrive(drive, distance, power);
        else {
            // reverse current direction of both motors in order to move
            // in opposite direction
            if (drive.equals("left")) {
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
            } else {
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.FORWARD);
                omni_rightDrive.get(0).setDirection(DcMotor.Direction.REVERSE);
            }

            omni_encoderDrive(drive, -distance, power);

            // re-reverse current direction of both motors
            if (drive.equals("left")) {
                omni_leftDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
                omni_leftDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
            } else {
                omni_rightDrive.get(1).setDirection(DcMotor.Direction.REVERSE);
                omni_rightDrive.get(0).setDirection(DcMotor.Direction.FORWARD);
            }
        }
    }

    /**
     * actual implementation of encoder-based omni_drive. not to be accessed outside the Robot class for simplicity of implementation of other encoder methods in the omni_drive classes
     * use of separate classes for designated functions removes repeated code :)
     * @param drive - specify left or right side of motor omni_drive
     * @param distance - METERS; projected distance for robot to move
     * @param power - -1 to 1; power to supply to each motor; motors should all have same power for smooth turning
     */
    private void omni_encoderDrive(String drive, double distance, double power) {
        // list of motors in use to avoid repeated code and lots of if statements
        List<DcMotor> motorsInUse = new ArrayList<>();

        if (drive.equals("left") || drive.equals("all"))
            motorsInUse.addAll(omni_leftDrive);

        if (drive.equals("right") || drive.equals("all"))
            motorsInUse.addAll(omni_rightDrive);

        // stop and reset all encoders
        for (DcMotor m : omni_leftDrive)
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        for (DcMotor m : omni_rightDrive)
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // find how many turns to omni_drive distance; (distance / circumference) * ticks per revolution
        int target = (int)((distance / (2 * Math.PI * WHEEL_RADIUS)) * TICKS_PER_REV);

        // apply target to each motor being used to omni_drive
        for (DcMotor m : motorsInUse) {
            m.setTargetPosition(target);
            m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        // set power to motors
        omni_drive(drive, power);

        // wait until all motors are done running
        while (motorsAreBusy(motorsInUse));

        omni_stopDriving();
    }

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