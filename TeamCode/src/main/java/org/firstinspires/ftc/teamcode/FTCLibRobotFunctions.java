package org.firstinspires.ftc.teamcode;


import static java.lang.Thread.sleep;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * -- TEAM 3916 --
 * Robot functions using FTCLib library. Used to program season-specific robot functionality.
 * NOTICE: all dimensions are measured in meters (unless specified). all measurements should be taken in meters or else calculations will absolutely mess up
 *
 * NOTICE: all angles are measured in radians (unless specified). please don't ever use degrees for angles
 *
 * @author Aman Anas
 * @author Gabrian Chua
 *
 * @since November 2020
 * @version October 2021
 *
 */

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */


    //Set motors and servos

    //Example:
    //public MotorEx flywheelMotor;

    public MotorEx slideMotor, intakeMotor, duckMotor, forearmMotor;
    public ServoEx intakeBucketServo, intakeArmServo, forearmServo, clawServo;
    public TouchSensor slideLimit;

    //Initialize enums
    public enum DuckState {
        RED,
        BLUE,
        STOPPED
    };

    public enum SlideState {
        GOING_UP,
        UP,
        GOING_DOWN,
        DOWN
    };

    //initialize motors and servos
    public void initBot(HardwareMap hw) {
        super.init(hw);

        //Example:
        //flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);

        intakeBucketServo = new SimpleServo(hw, "intake bucket", 0, 180);

        intakeArmServo = new SimpleServo(hw, "intake arm", 0, 270);

        forearmServo = new SimpleServo(hw, "forearm servo", 0, 180);

        clawServo = new SimpleServo(hw, "claw servo", 0, 180);

        slideMotor = new MotorEx(hw, "slide");
        slideMotor.setRunMode(Motor.RunMode.RawPower);
        slideMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        intakeMotor = new MotorEx(hw, "intake");
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        duckMotor = new MotorEx(hw, "duck motor");
        duckMotor.setRunMode(Motor.RunMode.RawPower);
        duckMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        forearmMotor = new MotorEx(hw, "forearm motor");
        forearmMotor.setRunMode(Motor.RunMode.RawPower);
        forearmMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        slideLimit = hw.get(TouchSensor.class, "limit switch");
    }


    public double getTriggers (GamepadEx gamepad){
        double g1triggers = 0;
        if (gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > TeleOpConfig.STICK_DEAD_ZONE) {
            g1triggers += gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        }
        if (gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > TeleOpConfig.STICK_DEAD_ZONE) {
            g1triggers -= gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        }
        return g1triggers;
    }

    public double returnDeadZoneClip(double input){
        double corrected;
        if (Math.abs(input) > TeleOpConfig.STICK_DEAD_ZONE) {
             corrected = input;
        } else {
            corrected = 0;
        }
        return corrected;
    }



    /*
               ////////////////////////// Methods for extra components //////////////////////////
    */
    public void runForearmMotor(double speed) {
        forearmMotor.set(speed * TeleOpConfig.FOREARM_MOTOR_MULTIPLIER);
    }

    double power;
    public void runIntakeMotor(GamepadEx Gamepad1) {

        if (Gamepad1.getButton(GamepadKeys.Button.A)) {
            power = 1;
        }
        else if (Gamepad1.getButton(GamepadKeys.Button.B)) {
            power = -1;
        }
        else {
            power = 0;
        }
        intakeMotor.set(power*(TeleOpConfig.INTAKE_MOTOR_MULTIPLIER));

    }
    public void runIntakeMotor(double stickInput) {
        stickInput = correctDeadZoneRemap(stickInput);
        intakeMotor.set(stickInput*(TeleOpConfig.INTAKE_MOTOR_MULTIPLIER));
    }

    double slidePos;
    public void runSlideMotor(double speed, boolean limit) {
        slidePos = slideMotor.encoder.getPosition();
        if (slidePos >= TeleOpConfig.SLIDE_MOTOR_MAX && speed > 0) {
            speed = 0;
        }
        if (limit) {
            slideMotor.encoder.reset();
            if (speed < 0) {
                speed = 0;
            }
        }
        slideMotor.set(speed * TeleOpConfig.LINEAR_SLIDE_MULTIPLIER * (speed < 0 && slidePos < TeleOpConfig.SLIDE_SLOW_POINT ? TeleOpConfig.SLIDE_SLOW_SPEED : 1));
    }
    public void runSlideMotor(double speed) {
        runSlideMotor(speed, slideLimit.isPressed());
    }

    SlideState slideState = SlideState.DOWN;
    public double sentToSlide = 0;
    public double sentToSlide2 = 0;
    public double sentToSlide3 = 0;
    double slideStateVal;
    public double slideMotorController (double input,Boolean hasDeadZone, Boolean stop){
        slidePos = slideMotor.encoder.getPosition();
        if (!hasDeadZone){
            input = correctDeadZoneRemap(input);
        }
        input *= TeleOpConfig.LINEAR_SLIDE_MULTIPLIER;
        sentToSlide = input;

        if (input > 0) {
            slideState = SlideState.GOING_UP;
        }
        if (input < 0) {
            slideState = SlideState.GOING_DOWN;
        }


        switch (slideState) {
            case GOING_UP:
                if (stop){
                    slideStateVal = 0;
                    slideState = SlideState.UP;
                }
                else if (slidePos < TeleOpConfig.SLIDE_MOTOR_MAX) {
                    slideStateVal = 1;
                }
                else {
                    slideStateVal = 0;
                    slideState = SlideState.UP;
                }
                break;
            case GOING_DOWN:
                if (!slideLimit.isPressed() && slidePos > TeleOpConfig.SLIDE_FLOOR) {
                    slideStateVal = -1;
                }
                else {
                    slideStateVal = 0;
                    slideState = SlideState.DOWN;
                }
                break;
            default:
                slideStateVal = 0;
                break;
        }
        sentToSlide2 = slideStateVal;
        input = slideStateVal;


        runSlideMotor(input, slideLimit.isPressed());
        return input;
    }



    public void autoTipBucket(){
        if ((slideState == SlideState.GOING_UP) && (slideMotor.encoder.getPosition() > TeleOpConfig.BUCKET_LIFT_POINT)){
            runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
        }
        if ((slideState == SlideState.GOING_DOWN) && (slideMotor.encoder.getPosition() < TeleOpConfig.BUCKET_DROP_POINT)){
            runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
        }
    }








    double duckSpeed;
    public void runDuckMotor(DuckState state) {
        switch (state) {
            case STOPPED:
                duckSpeed = (0);
                break;
            case BLUE:
                duckSpeed = (1);
                break;
            case RED:
                duckSpeed = (-1);
                break;
        }
        duckMotor.set(duckSpeed*(TeleOpConfig.DUCK_MOTOR_MULTIPLIER));
    }

    Boolean YPressed = false;
    DuckState duckState = DuckState.STOPPED;
    public DuckState getDuckState (Boolean pressed){
        if (pressed) {
            if (!YPressed) {
                YPressed = true;
                switch (duckState) {
                    case STOPPED:
                        duckState = DuckState.BLUE;
                        break;
                    case BLUE:
                        duckState = DuckState.RED;
                        break;
                    case RED:
                        duckState = DuckState.STOPPED;
                        break;
                }
            }
        }
        else {
            YPressed = false;
        }
        return duckState;
    }

    public void runIntakeBucketServo(double pos){
        intakeBucketServo.setPosition(pos);
    }
    public void runIntakeArmServo(double pos){
        intakeArmServo.setPosition(pos);
    }

    public void updateBucketServo(double leftY, double slidePos) {
        if (leftY > 0) {
            runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
        }
        if (leftY < 0) {
            runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
        }
    }

    public void deliverFreight() {
        double slidePos = slideMotor.encoder.getPosition();
        double prevSlidePos;

        while (slidePos < TeleOpConfig.SLIDE_MOTOR_MAX) {
            runSlideMotor(1);
            prevSlidePos = slidePos;
            slidePos = slideMotor.encoder.getPosition();
            if (prevSlidePos < TeleOpConfig.BUCKET_LIFT_POINT && TeleOpConfig.BUCKET_LIFT_POINT < slidePos) {
                runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);

            }
        }
        runSlideMotor(0);

    }
    public void resetSlide() {
        runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
        runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
        while (slideMotor.encoder.getPosition() > 0) {
            runSlideMotor(-1);
        }
        runSlideMotor(0);
    }



    /* Example:

    //Flywheel
    public void setFlywheelMotor(double speed) {
       //Set PID Coefficients
       flywheelMotor.setVeloCoefficients(TeleOpConfig.FLYWHEEL_KP, TeleOpConfig.FLYWHEEL_KI, TeleOpConfig.FLYWHEEL_KD);
       //Set Velocity
       flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);
    }

    Refer to FTCLib docs here: https://docs.ftclib.org/ftclib/features/hardware
    */

}
