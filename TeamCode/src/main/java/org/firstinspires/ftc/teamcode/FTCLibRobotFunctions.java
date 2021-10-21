package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

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


    //motors and servos
    public MotorEx flywheelMotor;
    public MotorEx wobbleArmMotor;
    public CRServo wobbleArmServo;
    public SimpleServo transferServo;
    public MotorEx intakeMotor;
    public CRServo intakeServo;


    //initialize motors and servos
    public void initBot(HardwareMap hw) {
        super.init(hw);

        //Example:
        //flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);

    }

    /*
               ////////////////////////// Methods for extra components //////////////////////////
    */

    /* Example:

    //Flywheel
    public void setFlywheelMotor(double speed) {
       //Set PID Coefficients
       flywheelMotor.setVeloCoefficients(TeleOpConfig.FLYWHEEL_KP, TeleOpConfig.FLYWHEEL_KI, TeleOpConfig.FLYWHEEL_KD);
       //Set Velocity
       flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);
    }

    */






}
