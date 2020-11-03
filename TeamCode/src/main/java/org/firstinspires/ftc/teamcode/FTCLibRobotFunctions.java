package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */
    private final static int CPR = 28;
    private final static int RPM = 6000;
    private final static double MAX_TICKS_PER_SECOND = (double)CPR * (double)RPM / 60;
    MotorEx flywheelMotor = new MotorEx(hw, "flywheel", CPR, RPM);
    SimpleServo leftPincer = new SimpleServo(hw, "leftPincer", 180, 0);
    SimpleServo rightPincer = new SimpleServo(hw, "rightPincer", 180, 0);
    private boolean pincerOpen = false;
    //constructor
    public FTCLibRobotFunctions() {
        //reset our pincer servos
        leftPincer.turnToAngle(0);
        rightPincer.turnToAngle(0);
    }

    //methods for extra components
    public void setFlywheel(double speed) {
        flywheelMotor.setVelocity(speed * MAX_TICKS_PER_SECOND);
    }
    public void togglePincers() {
        if (pincerOpen) {
            leftPincer.turnToAngle(0);
            rightPincer.turnToAngle(0);
        } else {
            leftPincer.turnToAngle(180);
            rightPincer.turnToAngle(180);
        }
        pincerOpen = !pincerOpen;
    }
}
