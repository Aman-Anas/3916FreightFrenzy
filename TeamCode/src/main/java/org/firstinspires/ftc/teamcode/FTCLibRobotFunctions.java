package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * -- TEAM 3916 --
 * Robot functions using FTCLib library. Used to program season-specific robot functionality.
 * NOTICE: all dimensions are measured in meters (in case not specified). all measurements should be taken in meters or else calculations will absolutely mess up
 *
 * NOTICE: all angles are measured in radians (in case not specified). please don't ever use degrees for angles
 *
 * @author Aman Anas
 * @author Gabrian Chua
 *
 * @since November 2020
 * @version January 2021
 *
 */

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */
    private final static int CPR = 28;
    private final static int RPM = 6000;
    public final static double MAX_TICKS_PER_SECOND = (double)CPR * (double)RPM / 60;
    private boolean pincerOpen = false;

    //motors and servos
    public MotorEx flywheelMotor;
    public MotorEx wobbleArmMotor;
    public SimpleServo wobbleArmServo;
    public SimpleServo transferServo;
    public MotorEx intakeMotor;
    public CRServo intakeServo;

    //methods for extra components
    public void setFlywheelMotor(double speed) {
        flywheelMotor.setVeloCoefficients(TeleOpConfig.FLYWHEEL_KP,TeleOpConfig.FLYWHEEL_KI, TeleOpConfig.FLYWHEEL_KD);
        flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);
    }

    public void runWobbleMotor(double speed) {
        //wobbleArmMotor.setVeloCoefficients(16,0,0);

        wobbleArmMotor.set(speed*(TeleOpConfig.WOBBLE_ARM_MULTIPLIER));
        //wobbleArmMotor.setTargetPosition((int)targetPos);
    }
    public void runWobbleServo(double speed) {
        wobbleArmServo.rotateDegrees(speed*(TeleOpConfig.WOBBLE_SERVO_MULTIPLIER));
    }
    public void runTransferServo(double speed){
        transferServo.rotateDegrees(speed*(TeleOpConfig.TRANSFER_SERVO_MULTIPLIER));
    }

    public void runIntakeMotor(double speed){
        intakeMotor.set(speed*(TeleOpConfig.INTAKE_MULTIPLIER));
    }

    public void runIntakeServo(double speed) {
        intakeServo.set(speed);
    }
    /*
    public void togglePincers() {
        if (pincerOpen) {
            leftPincer.turnToAngle(0);
            rightPincer.turnToAngle(0);
        } else {
            leftPincer.turnToAngle(180);
            rightPincer.turnToAngle(180);
        }
        pincerOpen = !pincerOpen;
    }*/


    //reset our pincer servos
    /*
    public void resetPincers() {
        leftPincer.turnToAngle(0);
        rightPincer.turnToAngle(0);
    }
    */
    //reset bot
    public void initBot(HardwareMap hw) {
        super.init(hw);
        wobbleArmMotor = new MotorEx(hw, "wobbleMotor");
        wobbleArmMotor.setRunMode(Motor.RunMode.RawPower);
        wobbleArmMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        //Constructor needs degrees for angle
        wobbleArmServo = new SimpleServo(hw, "wobbleServo", 180, 0);

        transferServo = new SimpleServo(hw, "transferServo");
        //transferServo.setRunMode(Motor.RunMode.RawPower);
        //transferServo.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE); NOT APPLICABLE since servo and not motor

        flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);

        intakeMotor = new MotorEx(hw,"intake");
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        intakeServo = new CRServo(hw,"intakeServo");



        //Commented out as these motors have not been installed on robot yet

        //leftPincer = new SimpleServo(hw, "leftPincer", 180, 0);
        //rightPincer = new SimpleServo(hw, "rightPincer", 180, 0);
        //resetPincers();
    }
}
