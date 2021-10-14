/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.NEW_MIN;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.NEW_RANGE;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.OLD_MIN;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.OLD_RANGE;
@Disabled
@TeleOp(name="Fieldcentric TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Fieldcentric extends LinearOpMode {

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

        RevIMU imuAngle = new RevIMU(hardwareMap, "imu");
        imuAngle.init();
        imuAngle.reset();

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {
            //gamepad 1 controls
            double leftTrigger = Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            double rightTrigger = Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
            double leftY = Gamepad1.getLeftY();
            double leftX = Gamepad1.getLeftX();

            if (leftTrigger > STICK_DEAD_ZONE) {
                //update z with left trigger, negative since left
                z = -((leftTrigger - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else if (rightTrigger > STICK_DEAD_ZONE) {
                //update z with right trigger
                z = ((rightTrigger - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else {
                z = 0;
            }

            if (leftY > STICK_DEAD_ZONE) {
                //update y with current y position
                y = ((leftY - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else if (leftY < -STICK_DEAD_ZONE) {
                y = -((Math.abs(leftY) - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else {
                y = 0;
            }

            if (leftX > STICK_DEAD_ZONE) {
                //update x with current x position
                x = ((leftX - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else if (leftX < -STICK_DEAD_ZONE) {
                x = -((Math.abs(leftX) - OLD_MIN) * NEW_RANGE / OLD_RANGE) + NEW_MIN;
            } else {
                x = 0;
            }

            //gamepad 2 controls
            if(Math.abs(Gamepad2.getRightY())>STICK_DEAD_ZONE) {
                //Commented out as flywheel has not been installed on robot yet
                //bot.setFlywheel(Gamepad2.getRightY());
            } else {
                //bot.setFlywheel(0);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.A)) {
                //bot.togglePincers();
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
            dashboardTelemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z + "angle:" + imuAngle.getHeading());
            dashboardTelemetry.update();
        }
    }
}