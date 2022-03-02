package org.firstinspires.ftc.teamcode;


import static java.lang.Thread.sleep;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
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

    /*
               ////////////////////////// Methods for extra components //////////////////////////
    */
    public void runForearmMotor(double speed) {
        forearmMotor.set(speed * TeleOpConfig.FOREARM_MOTOR_MULTIPLIER);
    }

    public void runIntakeMotor(double speed) {
        intakeMotor.set(speed*(TeleOpConfig.INTAKE_MOTOR_MULTIPLIER));
    }

    public void runSlideMotor(double speed) {
        slideMotor.set(speed*(TeleOpConfig.LINEAR_SLIDE_MULTIPLIER));
    }

    public void runDuckMotor(double speed) {
        duckMotor.set(speed*(TeleOpConfig.DUCK_MOTOR_MULTIPLIER));
    }
    public void runIntakeBucketServo(double pos){
        intakeBucketServo.setPosition(pos);
    }
    public void runIntakeArmServo(double pos){
        intakeArmServo.setPosition(pos);
    }

    public void runForearmServo(double speed) {
        forearmServo.setPosition(speed*(TeleOpConfig.FOREARM_SERVO_MULTIPLIER));
    }
    public void runClawServo(double speed) {
        clawServo.setPosition(speed*(TeleOpConfig.CLAW_SERVO_MULTIPLIER));
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
