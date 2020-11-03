/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FTCLib TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_FTCLib extends LinearOpMode {

    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize code and variables

        bot.init(hardwareMap);

        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);

        final double STICK_DEAD_ZONE = 0.1;
        double x = 0;
        double y = 0;
        double z = 0;

        RevIMU imuAngle = new RevIMU(hardwareMap, "imu");
        imuAngle.init();
        imuAngle.reset();

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {
            //gamepad 1 controls
            if (Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > STICK_DEAD_ZONE) {
                //update z with left trigger, negative since left
                z = -Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            } else if (Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > STICK_DEAD_ZONE) {
                //update z with right trigger
                z = Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
            } else {
                z = 0;
            }

            if (Math.abs(Gamepad1.getLeftY()) > STICK_DEAD_ZONE) {
                //update y with current y position
                y = Gamepad1.getLeftY();
            } else {
                y = 0;
            }
            if (Math.abs(Gamepad1.getLeftX()) > STICK_DEAD_ZONE) {
                //update x with current x position
                x = Gamepad1.getLeftX();
            } else {
                x = 0;
            }

            //gamepad 2 controls
            if(Math.abs(Gamepad2.getRightY())>STICK_DEAD_ZONE) {
                bot.setFlywheel(Gamepad2.getRightY());
            } else {
                bot.setFlywheel(0);
            }


            /*
            example code
            if (Gamepad1.getButton(GamepadKeys.Button.X)){
                bot.moveArmUpALittleBit();
            }
            */

            //Send the X, Y, and rotation (Z) to the mecanum method
            bot.mecanumDrivetrain.driveFieldCentric(x, y, z, imuAngle.getHeading());

            //Add a little telemetry
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z + "angle:" + imuAngle.getHeading());
            telemetry.update();
        }
    }
}