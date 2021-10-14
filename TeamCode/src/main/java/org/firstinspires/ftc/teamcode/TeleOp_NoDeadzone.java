/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
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

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        //Wait for the driver to hit Start
        waitForStart();

        boolean motorsCameUpNull = false;
        if (bot.motor_frontLeft == null || bot.motor_frontRight == null || bot.motor_backLeft == null || bot.motor_backRight == null) {
            motorsCameUpNull = true;
        }

        while (opModeIsActive()) {
            //gamepad 1 controls

            //update z with current z position from right stick
            z = Gamepad1.getRightX();
            //update y with current y position from left stick
            y = Gamepad1.getLeftY();
            //update x with current x position from left stick
            x = Gamepad1.getLeftX();

            if (motorsCameUpNull) {
                telemetry.addData("Status", "AAAAAAAAAAA MOTORS BAD");
            } else {
                //Send the X, Y, and rotation (Z) to the mecanum method
                bot.mecanumDrivetrain.driveRobotCentric(x, y, z);
                //Add a little telemetry
                telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
                dashboardTelemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
                dashboardTelemetry.update();
            }
            telemetry.update();
        }
    }
}