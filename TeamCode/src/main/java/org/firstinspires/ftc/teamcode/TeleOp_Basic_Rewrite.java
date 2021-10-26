/*
Apex Robotics FTC Team 3916: Basic TeleOp for Freight Frenzy season (2021-2022)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Simple TeleOp base method, build season code on top of this.
 *
 * @author Aman Anas
 * @author Gabrian Chua
 * @author Nathan Battle
 *
 * @version October 2021
 *
 */

@TeleOp(name="Basic Controls TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Basic_Rewrite extends LinearOpMode {

    //Define our robot class
    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        //Initialize bot
        bot.initBot(hardwareMap);
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);

        //Initialize working variables
        double x = 0;
        double y = 0;
        double z = 0;
        boolean precisionModeEnabled = false;


        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {

            /*
               ////////////////////////// GAMEPAD 1 //////////////////////////
            */

            //Get stick inputs
            double leftY = Gamepad1.getLeftY();
            double leftX = Gamepad1.getLeftX();
            double rightX = Gamepad1.getRightX();

            if (Gamepad1.getButton(GamepadKeys.Button.RIGHT_BUMPER) || Gamepad1.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                precisionModeEnabled = true;
            }
            else{
                precisionModeEnabled = false;
            }


            // Rotation Axis
            if (Math.abs(rightX) > TeleOpConfig.STICK_DEAD_ZONE) {
                z = bot.correctDeadZone(rightX);
                if (precisionModeEnabled) {
                    z *= -1 * TeleOpConfig.PRECISION_TURN_MULTIPLIER;
                }
            } else {
                z = 0;
            }
            // Forward/Back Drive
            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                y = bot.correctDeadZone(leftY);
                if (precisionModeEnabled) {
                    y *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
                }
            } else {
                y = 0;
            }
            // Left/Right Strafe
            if (Math.abs(leftX) > TeleOpConfig.STICK_DEAD_ZONE) {
                x = bot.correctDeadZone(leftX);
                if (precisionModeEnabled) {
                    x *= TeleOpConfig.PRECISION_POWER_MULTIPLIER;
                }
            } else {
                x = 0;
            }

            //Send the X, Y, and rotation (Z) to the mecanum drive method
            bot.mecanumDrivetrain.driveRobotCentric(x, y, z);


            /*
               ////////////////////////// GAMEPAD 2 //////////////////////////
            */

            //Get stick inputs


            //Insert gamepad 2 code here




            /*
               ////////////////////////// TELEMETRY //////////////////////////
            */
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            telemetry.update();

        }
    }
}