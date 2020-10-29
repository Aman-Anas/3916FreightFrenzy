package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class FTCLibRobotFunctions extends FTCLibMecanumBot {
    /*
       Put extra game-specific robot functionality here,
       such as additional motors, servos, and sensors for arms, claws, and lifts.
     */
    final int CPR = 28;
    final int RPM = 6000;
    final double MAXTICKSPERSECOND = (double)CPR * (double)RPM / 60;
    MotorEx flywheelMotor = new MotorEx(hw, "flywheel", 28, 6000);
    public void SetFlywheel(double speed) {
        flywheelMotor.setVelocity(speed * MAXTICKSPERSECOND);
    }
}
