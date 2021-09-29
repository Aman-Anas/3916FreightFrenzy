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

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.NEW_MIN;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.NEW_RANGE;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.OLD_MIN;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.OLD_RANGE;


@TeleOp(name="Basic Controls TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Basic extends LinearOpMode {

    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize code and variables
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot.initBot(hardwareMap);

        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        //GamepadEx Gamepad2 = new GamepadEx(gamepad2);

        double STICK_DEAD_ZONE = TeleOpConfig.STICK_DEAD_ZONE;
        double x = 0;
        double y = 0;
        double z = 0;
        double powerMultiplier = 1; //Multiplier for motor power (for precision mode)
        double turnMultiplier = 1; // Multiplier for turning speed

        FtcDashboard dashboard = FtcDashboard.getInstance();
        //Telemetry dashboardTelemetry = dashboard.getTelemetry();

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {
            /*
                GAMEPAD 1
            */
            double leftY = Gamepad1.getLeftY();
            double leftX = Gamepad1.getLeftX();
            double rightX = Gamepad1.getRightX();

            //Precision mode, multipliers can be adjusted as needed
            if (Gamepad1.getButton(GamepadKeys.Button.RIGHT_BUMPER) || Gamepad1.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                powerMultiplier = 0.5;
                turnMultiplier = 0.5;
            } else {
                powerMultiplier = 1;
                turnMultiplier = 1;
            }

            if (Math.abs(rightX) > TeleOpConfig.STICK_DEAD_ZONE) {
                z = rightX * turnMultiplier;
            } else {
                z = 0;
            }

            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                y = -(leftY) * powerMultiplier;
            } else {
                y = 0;
            }

            if (Math.abs(leftX) > TeleOpConfig.STICK_DEAD_ZONE) {
                //update x with current x position
                x = -(leftX) * powerMultiplier;
            } else {
                x = 0;
            }

            /*
                GAMEPAD 2
            */

            //Send the X, Y, and rotation (Z) to the mecanum method
            bot.mecanumDrivetrain.driveRobotCentric(x, y, z);

            //Add a little telemetry
            //telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            //telemetry.update();
            //dashboardTelemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            //dashboardTelemetry.update();
        }
    }
}