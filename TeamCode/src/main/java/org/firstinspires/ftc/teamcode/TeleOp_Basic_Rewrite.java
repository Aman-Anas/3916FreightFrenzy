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

            //This next section uses some janky math magic to correct for the dead zone. Feel free to simplify it if you can.
            /*
                How it works:
                    First, it removes the dead zone, either subtracting it (from a positive input) or adding it (to a negative input).
                    It does this by multiplying the dead zone by the input divided by the abs val of the input, effectively multiplying by 1 for a positive input,
                        or multiplying by -1 for a negative input.
                    After removing the dead zone, it rescales the value by dividing by 1 minus the dead zone, which ensures that full inputs produce full power,
                        zero value inputs (at or below the dead zone) are at zero, and everything in between is scaled proportionally.
                    Direction inversions and Precision Mode are implemented after this correction for simplicity.
             */
            // Rotation Axis
            if (Math.abs(rightX) > TeleOpConfig.STICK_DEAD_ZONE) {
                z = bot.correctDeadZone(rightX);
                if (precisionModeEnabled) {
                    z = z * TeleOpConfig.PRECISION_TURN_MULTIPLIER;
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

            //Send the X, Y, and rotation (Z) to the mecanum method
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