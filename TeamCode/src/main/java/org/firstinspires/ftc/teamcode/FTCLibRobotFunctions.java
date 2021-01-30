package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */
    private final static int CPR = 28;
    private final static int RPM = 6000;
    private final static double MAX_TICKS_PER_SECOND = (double)CPR * (double)RPM / 60;
    private boolean pincerOpen = false;

    //motors and servos
    public MotorEx flywheelMotor;
    public MotorEx wobbleArmMotor;
    public SimpleServo wobbleArmServo;
    public SimpleServo transferServo;

    //methods for extra components
    public void setFlywheelMotor(double speed) {
        flywheelMotor.setVeloCoefficients(16,0,0);
        flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);

    }
    public void runWobbleMotor(double speed) {
        //wobbleArmMotor.setVeloCoefficients(16,0,0);

        wobbleArmMotor.set(speed*0.7);
        //wobbleArmMotor.setTargetPosition((int)targetPos);
    }
    public void runWobbleServo(double speed) {
        wobbleArmServo.turnToAngle(speed*180);
    }
    public void runTransferServo(double speed){
        transferServo.rotate(speed);
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
        wobbleArmServo = new SimpleServo(hw, "wobbleServo", 180, 0);
        transferServo = new SimpleServo(hw, "transferServo", 180, 0);
        //Commented out as these motors have not been installed on robot yet
        flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);
        //leftPincer = new SimpleServo(hw, "leftPincer", 180, 0);
        //rightPincer = new SimpleServo(hw, "rightPincer", 180, 0);
        //resetPincers();
    }
}
