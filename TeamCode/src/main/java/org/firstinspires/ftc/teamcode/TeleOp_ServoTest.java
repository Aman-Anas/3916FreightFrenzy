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
 * @author Andrew Lynch
 * @author Alek Sun
 * @version October 2021
 *
 */

// This is just a copy of TeleOp_Basic_Rewrite with telemetry added
@TeleOp(name="Testing servos", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_ServoTest extends LinearOpMode {

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
            boolean precisionMode = (Gamepad1.getButton(GamepadKeys.Button.RIGHT_BUMPER) || Gamepad1.getButton(GamepadKeys.Button.LEFT_BUMPER));

            // Rotation Axis
            if (Math.abs(rightX) > TeleOpConfig.STICK_DEAD_ZONE) {
                z = bot.correctDeadZone(rightX);

            } else {
                z = 0;
            }
            // Forward/Back Drive
            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                y = bot.correctDeadZone(leftY);

            } else {
                y = 0;
            }
            // Left/Right Strafe
            if (Math.abs(leftX) > TeleOpConfig.STICK_DEAD_ZONE) {
                x = bot.correctDeadZone(leftX);

            } else {
                x = 0;
            }

            //Insert gamepad 1 code here
            if (Gamepad1.getButton(GamepadKeys.Button.X)) {
                bot.motor_backRight.encoder.reset();
                bot.motor_backLeft.encoder.reset();
                bot.motor_frontRight.encoder.reset();
                bot.motor_frontLeft.encoder.reset();
            }

            //Send the X, Y, and rotation (Z) to the mecanum drive method
            bot.driveRobot(-x, -y, z, precisionMode);




            /*
               ////////////////////////// GAMEPAD 2 //////////////////////////
            */

            //Get stick inputs
            leftY = Gamepad2.getLeftY();
            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                leftY = bot.correctDeadZone(leftY);
            } else {
                leftY = 0;
            }
            double rightY = Gamepad2.getRightY();
            if (Math.abs(rightY) > TeleOpConfig.STICK_DEAD_ZONE) {
                rightY = bot.correctDeadZone(rightY);
            } else {
                rightY = 0;
            }

            //Insert gamepad 2 code here

            if (Gamepad2.getButton(GamepadKeys.Button.A)) {
                //Red Side
                bot.runDuckMotor(-1);
            } else if (Gamepad2.getButton(GamepadKeys.Button.B)) {
                //Blue Side
                bot.runDuckMotor(1);
            } else {
                bot.runDuckMotor(0);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.DPAD_UP)) {
                //bot.intakeBucketServo.rotateBy(.001);
                bot.forearmServo.rotateBy(.001);
            } else if (Gamepad2.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                //bot.intakeBucketServo.rotateBy(-.001);
                bot.forearmServo.rotateBy(-.001);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                //bot.intakeArmServo.rotateBy(.001);
                bot.clawServo.rotateBy(-.001);
            } else if (Gamepad2.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                //bot.intakeArmServo.rotateBy(-.001);
                bot.clawServo.rotateBy(-.001);
            }
            bot.runSlideMotor(leftY);
            bot.runIntakeMotor(rightY);



            /*
               ////////////////////////// TELEMETRY //////////////////////////
            */
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            /*telemetry.addData("Front Left Motor", "pos: "+bot.motor_frontLeft.encoder.getPosition());
            telemetry.addData("Front Right Motor", "pos: "+bot.motor_frontRight.encoder.getPosition());
            telemetry.addData("Back Left Motor", "pos: "+bot.motor_backLeft.encoder.getPosition());
            telemetry.addData("Back Right Motor", "pos: "+bot.motor_backRight.encoder.getPosition());*/
            telemetry.addData("Bucket Servo", "pos: "+bot.intakeBucketServo.getPosition());
            telemetry.addData("Arm Servo", "pos: "+bot.intakeArmServo.getPosition());
            telemetry.addData("Forearm Servo", "pos: " + bot.forearmServo.getPosition());
            telemetry.addData("Claw Servo", "pos: "+bot.clawServo.getPosition());
            telemetry.update();

        }
    }
}
