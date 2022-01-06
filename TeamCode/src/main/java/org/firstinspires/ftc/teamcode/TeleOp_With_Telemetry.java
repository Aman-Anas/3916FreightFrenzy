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
 * @version October 2021
 *
 */

// This is the main TeleOp, with full bot functionality as well as telemetry
@TeleOp(name="TeleOp With Telemetry", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_With_Telemetry extends LinearOpMode {

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

        bot.slideMotor.encoder.reset();

        //Initialize working variables
        double x = 0;
        double y = 0;
        double z = 0;
        boolean bucketLift = false;
        double slidePos = 0;
        double prevSlidePos;
        boolean slideLimit;

        //Wait for the driver to hit Start
        waitForStart();

        while (opModeIsActive()) {

            //Sensor Inputs
            //slideLimit = bot.slideLimit.isPressed();

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
                leftY = bot.correctDeadZone(leftY) * TeleOpConfig.LINEAR_SLIDE_MULTIPLIER;
            } else {
                leftY = 0;
            }

            if ((slidePos >= TeleOpConfig.SLIDE_MOTOR_MAX && leftY > 0) || (slidePos <= 0 && leftY < 0)) {
                leftY = 0;
            }
            if (slidePos == 0 && leftY > 0) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_LIFT_ANGLE);
            }
            /*if (slideLimit) {
                bot.slideMotor.encoder.reset();
                if (leftY < 0) {
                    leftY = 0;
                }
            }*/

            double rightY = Gamepad2.getRightY();
            if (Math.abs(rightY) > TeleOpConfig.STICK_DEAD_ZONE) {
                rightY = bot.correctDeadZone(rightY) * TeleOpConfig.INTAKE_MOTOR_MULTIPLIER;
            } else {
                rightY = 0;
            }

            //Button inputs
            if (Gamepad2.getButton(GamepadKeys.Button.B)) {
                //Red Side
                bot.runDuckMotor(-1);
            } else if (Gamepad2.getButton(GamepadKeys.Button.X)) {
                //Blue Side
                bot.runDuckMotor(1);
            } else {
                bot.runDuckMotor(0);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.DPAD_UP)) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            } else if (Gamepad2.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            } else if (Gamepad2.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            }
            if (Gamepad2.getButton(GamepadKeys.Button.A)) {
                bot.slideMotor.encoder.reset();
            }
            bot.runSlideMotor(leftY);
            bot.runIntakeMotor(rightY);
            prevSlidePos = slidePos;
            slidePos = bot.slideMotor.encoder.getPosition();

            if (prevSlidePos < TeleOpConfig.BUCKET_LIFT_POINT && TeleOpConfig.BUCKET_LIFT_POINT < slidePos) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            }
            else if (prevSlidePos > TeleOpConfig.BUCKET_DROP_POINT && TeleOpConfig.BUCKET_DROP_POINT > slidePos) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            }

            /*
               ////////////////////////// TELEMETRY //////////////////////////
            */
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            telemetry.addData("Front Left Motor", "pos: "+bot.motor_frontLeft.encoder.getPosition());
            telemetry.addData("Front Right Motor", "pos: "+bot.motor_frontRight.encoder.getPosition());
            telemetry.addData("Back Left Motor", "pos: "+bot.motor_backLeft.encoder.getPosition());
            telemetry.addData("Back Right Motor", "pos: "+bot.motor_backRight.encoder.getPosition());
            telemetry.addData("Slide Motor", "pos: "+slidePos);
            telemetry.update();
        }
    }
}
