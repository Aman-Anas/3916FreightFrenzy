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
 * @author Jason Armbruster
 * @author Jude Naramor
 *
 * @version January 2022
 *
 */

// This is the main TeleOp, with full bot functionality as well as telemetry
@TeleOp(name="Wheel Tester", group="Apex Robotics 3916")
//@Disabled
public class Wheel_Tester extends LinearOpMode {

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

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {

            /*
               ////////////////////////// GAMEPAD 1 //////////////////////////
            */

            //Button inputs
            if (Gamepad1.getButton(GamepadKeys.Button.B)) {
                bot.motor_frontRight.set(1);
            } else {
                bot.motor_frontRight.set(0);
            }
            if (Gamepad1.getButton(GamepadKeys.Button.A)) {
                bot.motor_backRight.set(1);
            } else {
                bot.motor_backRight.set(0);
            }
            if (Gamepad1.getButton(GamepadKeys.Button.X)) {
                bot.motor_backLeft.set(1);
            } else {
                bot.motor_backLeft.set(0);
            }
            if (Gamepad1.getButton(GamepadKeys.Button.Y)) {
                bot.motor_frontLeft.set(1);
            } else {
                bot.motor_frontLeft.set(0);
            }

            /*
               ////////////////////////// TELEMETRY //////////////////////////
            */
            telemetry.addData("Front Left Motor", "pos: "+bot.motor_frontLeft.encoder.getPosition());
            telemetry.addData("Front Right Motor", "pos: "+bot.motor_frontRight.encoder.getPosition());
            telemetry.addData("Back Left Motor", "pos: "+bot.motor_backLeft.encoder.getPosition());
            telemetry.addData("Back Right Motor", "pos: "+bot.motor_backRight.encoder.getPosition());
            telemetry.update();
        }
    }
}
