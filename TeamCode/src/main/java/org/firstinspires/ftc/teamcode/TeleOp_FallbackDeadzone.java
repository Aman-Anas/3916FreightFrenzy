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
@TeleOp(name="Fallback Robotcentric TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_FallbackDeadzone extends LinearOpMode {

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

            //update x with current x position from left stick
            x = Math.abs(Gamepad1.getLeftX()) > STICK_DEAD_ZONE ? Gamepad1.getLeftX() : 0;
            //update y with current y position from left stick
            y = Math.abs(Gamepad1.getLeftY()) > STICK_DEAD_ZONE ? Gamepad1.getLeftY() : 0;
            //update z with current z position from right stick
            z = Math.abs(Gamepad1.getRightX()) > STICK_DEAD_ZONE ? Gamepad1.getRightX() : 0;

            if (motorsCameUpNull) {
                telemetry.addData("Status", "AAAAAAAAAAA MOTORS BAD");
            } else {
                //Send the X, Y, and rotation (Z) to the mecanum method
                bot.mecanumDrivetrain.driveRobotCentric(x, y, z);
                //Add a little telemetry
                telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
                telemetry.addData("Encoders Status", "FR:" +
                        bot.motor_frontRight.encoder.getRawVelocity() + " BR:" +
                        bot.motor_backRight.encoder.getRawVelocity() + " FL:" +
                        bot.motor_frontLeft.encoder.getRawVelocity() + " BL:" +
                        bot.motor_backLeft.encoder.getRawVelocity());
            }
            telemetry.update();
            dashboardTelemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            dashboardTelemetry.addData("Encoders Status", "FR:" +
                    bot.motor_frontRight.encoder.getRawVelocity() + " BR:" +
                    bot.motor_backRight.encoder.getRawVelocity() + " FL:" +
                    bot.motor_frontLeft.encoder.getRawVelocity() + " BL:" +
                    bot.motor_backLeft.encoder.getRawVelocity());
            dashboardTelemetry.update();
        }
    }
}