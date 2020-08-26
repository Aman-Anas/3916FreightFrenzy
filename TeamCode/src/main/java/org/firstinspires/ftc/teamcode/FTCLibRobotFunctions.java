package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.EncoderEx;
import com.arcrobotics.ftclib.hardware.motors.SimpleMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Base64;


public class FTCLibRobotFunctions extends FTCLibDriveCode {
    /**
     * Put extra game-specific robot functionality here,
     * such as additional motors, servos, and sensors for arms, claws, and lifts.
     */
    SimpleMotorEx armMotor = new SimpleMotorEx("arm thingy", hw, 1125);

    EncoderEx encoderArm = new EncoderEx(armMotor);

    public void moveArmUpALittleBit (){
        encoderArm.stopAndReset();
        encoderArm.runToPosition(3000, 1.0);
    }



}
