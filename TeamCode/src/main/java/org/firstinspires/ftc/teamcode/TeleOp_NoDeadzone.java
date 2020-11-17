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


@TeleOp(name="No Deadzone Robotcentric TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_NoDeadzone extends LinearOpMode {

    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize code and variables

        bot.initBot(hardwareMap);

        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);

        final double STICK_DEAD_ZONE = 0.1;
        double x = 0;
        double y = 0;
        double z = 0;

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {
            //gamepad 1 controls

            //update z with current z position from right stick
            z = Gamepad1.getRightX();
            //update y with current y position from left stick
            y = Gamepad1.getLeftY();
            //update x with current x position from left stick
            x = Gamepad1.getLeftX();


            //Send the X, Y, and rotation (Z) to the mecanum method
            bot.mecanumDrivetrain.driveRobotCentric(x, y, z);

            //Add a little telemetry
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            telemetry.update();
        }
    }
}