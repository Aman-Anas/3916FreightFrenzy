/*
Apex Robotics FTC Team 3916: Basic TeleOp for Freight Frenzy season (2021-2022)

Uses a Mecanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * FieldCentric TeleOp method, build season code on top of this.
 *
 * @author Aman Anas
 * @author Gabrian Chua
 * @author Nathan Battle
 * @author Jason Armbruster
 * @author Jude Naramor
 *
 * @version April 2022
 *
 */


// This is the main TeleOp, with full bot functionality as well as telemetry
@TeleOp(name="TeleOp with Fieldcentric", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_With_FieldCentric extends LinearOpMode {

    //Define our robot class
    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();

    public enum DuckState {
        RED,
        BLUE,
        STOPPED
    };

    public enum SlideState {
        GOING_UP,
        UP,
        GOING_DOWN,
        DOWN
    };

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize telemetry and dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        //Initialize bot
        bot.initBot(hardwareMap);
        RevIMU imu = new RevIMU(hardwareMap);
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);
        imu.init();

        bot.slideMotor.encoder.reset();
        bot.forearmMotor.encoder.reset();

        //Initialize working variables
        double x = 0;
        double y = 0;
        double z = 0;
        double g1triggers;
        double slidePos = 0;
        boolean slideLimit;
        boolean XPressed = false;
        boolean YPressed = false;
        double slideStateVal = 0;

        TeleOp_With_Telemetry.DuckState duckState = TeleOp_With_Telemetry.DuckState.STOPPED;
        TeleOp_With_Telemetry.SlideState slideState = TeleOp_With_Telemetry.SlideState.DOWN;

        //Wait for the driver to hit Start
        waitForStart();

        bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
        bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);

        while (opModeIsActive()) {

            //Sensor Inputs
            slideLimit = bot.slideLimit.isPressed();

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
                z = (rightX);
            } else {
                z = 0;
            }
            // Forward/Back Drive
            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                y = (leftY);
            } else {
                y = 0;
            }
            // Left/Right Strafe
            if (Math.abs(leftX) > TeleOpConfig.STICK_DEAD_ZONE) {
                x = (leftX);
            } else {
                x = 0;
            }

            //Trigger Inputs
            g1triggers = 0;
            if (Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > TeleOpConfig.STICK_DEAD_ZONE) {
                g1triggers += Gamepad1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
            }
            if (Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > TeleOpConfig.STICK_DEAD_ZONE) {
                g1triggers -= Gamepad1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
            }
            if (Gamepad1.getButton(GamepadKeys.Button.A)) {
                bot.runIntakeMotor(1);
            }
            else if (Gamepad1.getButton(GamepadKeys.Button.B)) {
                bot.runIntakeMotor(-1);
            }
            else {
                bot.runIntakeMotor(0);
            }

            // Reset Encoders
            /*if (Gamepad1.getButton(GamepadKeys.Button.X)) {
                bot.motor_backRight.encoder.reset();
                bot.motor_backLeft.encoder.reset();
                bot.motor_frontRight.encoder.reset();
                bot.motor_frontLeft.encoder.reset();
            }*/

            //Send the X, Y, and rotation (Z) to the mecanum drive method
            bot.driveFieldCentric(x, y, z, precisionMode,imu);

            // Gamepad 2 Redundancies
            if (Gamepad1.getButton(GamepadKeys.Button.DPAD_UP)) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MIN);
            } else if (Gamepad1.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);
            }
            if (Gamepad1.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
            } else if (Gamepad1.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MAX);
            }


            /*
               ////////////////////////// GAMEPAD 2 //////////////////////////
            */

            //Get stick inputs
            leftY = Gamepad2.getLeftY();
            if (Math.abs(leftY) > TeleOpConfig.STICK_DEAD_ZONE) {
                leftY = bot.correctDeadZoneRemap(leftY) * TeleOpConfig.LINEAR_SLIDE_MULTIPLIER;
            } else {
                leftY = g1triggers;
            }

            double rightY = Gamepad2.getRightY();
            if (Math.abs(rightY) > TeleOpConfig.STICK_DEAD_ZONE) {
                rightY = bot.correctDeadZoneRemap(rightY) * TeleOpConfig.INTAKE_MOTOR_MULTIPLIER;
            } else {
                rightY = 0;
            }

            //Button inputs
            if (Gamepad2.getButton(GamepadKeys.Button.Y) || Gamepad1.getButton(GamepadKeys.Button.Y)) {
                if (!YPressed) {
                    YPressed = true;
                    switch (duckState) {
                        case STOPPED:
                            duckState = TeleOp_With_Telemetry.DuckState.BLUE;
                            break;
                        case BLUE:
                            duckState = TeleOp_With_Telemetry.DuckState.RED;
                            break;
                        case RED:
                            duckState = TeleOp_With_Telemetry.DuckState.STOPPED;
                            break;
                    }
                }
            }
            else {
                YPressed = false;
            }
            if (Gamepad2.getButton(GamepadKeys.Button.X) || Gamepad1.getButton(GamepadKeys.Button.X)) {
                if (!XPressed) {
                    XPressed = true;
                    switch (slideState) {
                        case DOWN:
                        case GOING_DOWN:
                            slideState = TeleOp_With_Telemetry.SlideState.GOING_UP;
                            break;
                        case UP:
                        case GOING_UP:
                            slideState = TeleOp_With_Telemetry.SlideState.GOING_DOWN;
                            break;
                    }
                }
            }
            else {
                XPressed = false;
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

            /*if (Gamepad2.getButton(GamepadKeys.Button.A)) {
                bot.slideMotor.encoder.reset();
            }*/

            if (leftY > 0) {
                slideState = TeleOp_With_Telemetry.SlideState.UP;
            }
            if (leftY < 0) {
                slideState = TeleOp_With_Telemetry.SlideState.DOWN;
            }

            slidePos = bot.slideMotor.encoder.getPosition();

            //Run Slide
            switch (slideState) {
                case GOING_UP:
                    if (slidePos < TeleOpConfig.SLIDE_MOTOR_MAX) {
                        slideStateVal = 1;
                    }
                    else {
                        slideStateVal = 0;
                        slideState = TeleOp_With_Telemetry.SlideState.UP;
                    }
                    break;
                case GOING_DOWN:
                    if (!slideLimit) {
                        slideStateVal = -1;
                    }
                    else {
                        slideStateVal = 0;
                        slideState = TeleOp_With_Telemetry.SlideState.DOWN;
                    }
                    break;
                default:
                    slideStateVal = 0;
                    break;
            }
            if (leftY == 0) {
                leftY = slideStateVal;
            }
            bot.runSlideMotor(leftY, slideLimit);
            if (rightY != 0) {
                bot.runIntakeMotor(rightY);
            }

            //Update Bucket
            bot.updateBucketServo(leftY, slidePos);

            //Run Duck Motor
            switch (duckState) {
                case STOPPED:
                    bot.runDuckMotor(0);
                    break;
                case BLUE:
                    bot.runDuckMotor(1);
                    break;
                case RED:
                    bot.runDuckMotor(-1);
                    break;
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
            telemetry.addData("Forearm Motor", "pos: "+bot.forearmMotor.encoder.getPosition());
            telemetry.addData("Limit Switch", "val: "+bot.slideLimit.getValue());
            telemetry.addData("Limit Switch", "isTouched"+bot.slideLimit.isPressed());
            telemetry.update();
        }
    }
}