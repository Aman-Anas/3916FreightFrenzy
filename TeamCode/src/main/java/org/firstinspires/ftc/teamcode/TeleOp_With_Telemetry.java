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
 * @version April 2022
 *
 */

// This is the main TeleOp, with full bot functionality as well as telemetry
@TeleOp(name="TeleOp with Telemetry", group="Apex Robotics 3916")
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
        bot.forearmMotor.encoder.reset();

        //Initialize working variables
        double x = 0;
        double y = 0;
        double z = 0;
        double g1triggers;
        double slidePos = 0;


        //Wait for the driver to hit Start
        waitForStart();

        bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
        bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);

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
            z = bot.correctDeadZoneRemap(rightX);
            // Forward/Back Drive
            y = bot.correctDeadZoneRemap(leftY);
            // Left/Right Strafe
            x = bot.correctDeadZoneRemap(leftX);

            //Send the X, Y, and rotation (Z) to the mecanum drive method
            bot.driveRobotCentric(x, y, z, precisionMode);


            //Trigger Inputs
            g1triggers = bot.getTriggers(Gamepad1);

            //Run Intake
            bot.runIntakeMotor(Gamepad1);


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
                bot.slideMotorController(leftY,false);
            } else {
                bot.slideMotorController(g1triggers,true);
            }


            //Button inputs
            bot.runDuckMotor(bot.getDuckState(Gamepad1.getButton(GamepadKeys.Button.Y) || Gamepad2.getButton(GamepadKeys.Button.Y)));

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




            bot.runIntakeMotor(Gamepad2.getRightY());

            //Update Bucket
            bot.updateBucketServo(leftY, bot.slideMotor.encoder.getPosition());

            //Run Duck Motor


            /*
               ////////////////////////// TELEMETRY //////////////////////////
            */
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " z:" + z);
            telemetry.addData("Front Left Motor", "pos: "+bot.motor_frontLeft.encoder.getPosition());
            telemetry.addData("Front Right Motor", "pos: "+bot.motor_frontRight.encoder.getPosition());
            telemetry.addData("Back Left Motor", "pos: "+bot.motor_backLeft.encoder.getPosition());
            telemetry.addData("Back Right Motor", "pos: "+bot.motor_backRight.encoder.getPosition());
            telemetry.addData("Slide Motor", "pos: "+bot.slideMotor.encoder.getPosition());
            telemetry.addData("Limit Switch", "isTouched"+bot.slideLimit.isPressed());
            telemetry.update();
        }
    }
}
