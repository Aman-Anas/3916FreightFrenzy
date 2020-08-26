package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.SimpleMotorEx;

public class Tank_Drive {
    private double leftPower;
    private double rightPower;
    SimpleMotorEx leftMotor;
    SimpleMotorEx rightMotor;
    public Tank_Drive (SimpleMotorEx left, SimpleMotorEx right){
        leftMotor = left;
        rightMotor = right;
    }

    public void tankDriveUpdate(double leftStick, double rightStick){
        leftMotor.set(leftStick);
        rightMotor.set(rightStick);
    }


}
