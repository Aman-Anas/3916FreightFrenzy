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

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
@TeleOp(name="TeleOp Lua", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Lua extends LinearOpMode {

    //Define our robot class
    private FTCLibRobotFunctions bot = new FTCLibRobotFunctions();
    //This is temp and usually is changed
    private String luaCode = "return function()\n"+
                                           "    telemetry:addData('UhOh', 'The code did not download :(')" +
                                           "end";


    @Override
    public void runOpMode() throws InterruptedException {


        //Initialize telemetry and dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        FtcDashboard dashboard = FtcDashboard.getInstance();

        //Get External Code
        //Whoever made java http requests this terrible has the sincerest hate I can muster
        //ripped from https://www.javaguides.net/2019/07/java-http-getpost-request-example.html
        try {
            URL url = new URL("http://"+TeleOpConfig.EXTERNAL_COMPUTER_IP+":8484/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int resCode = connection.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inpLine;
                StringBuffer res = new StringBuffer();

                while ((inpLine = in.readLine()) != null) {
                    res.append(inpLine);
                } in.close();
                luaCode = res.toString();
            } else {
                System.out.println("EVERYTHING IS ON FIRE!!");
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Boolean stopButton;

        //Wait for the driver to hit Start
        waitForStart();

        bot.runIntakeArmServo(TeleOpConfig.GATE_SERVO_MIN);
        bot.runIntakeBucketServo(TeleOpConfig.BUCKET_SERVO_MAX);

        Globals _G = JsePlatform.standardGlobals();
        _G.rawset("bot", CoerceJavaToLua.coerce(bot));
        _G.rawset("gamepad1", CoerceJavaToLua.coerce(gamepad1));
        _G.rawset("gamepad2", CoerceJavaToLua.coerce(gamepad2));
        _G.rawset("telemetry", CoerceJavaToLua.coerce(telemetry));
        LuaFunction func = _G.load(luaCode).call().checkfunction();

        while (opModeIsActive()) {
            func.call();
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
            if (Gamepad2.getRightY() > TeleOpConfig.STICK_DEAD_ZONE){
                bot.runIntakeMotor(Gamepad2.getRightY());
            }
            else {
                bot.runIntakeMotor(Gamepad1);
            }


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
            //double var = 0;
            //Get stick inputs
            leftY = Gamepad2.getLeftY();
            stopButton = Gamepad1.getButton(GamepadKeys.Button.X) || Gamepad2.getButton(GamepadKeys.Button.X);

            bot.slideMotorController(g1triggers+leftY,true,stopButton);



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

            bot.autoTipBucket();





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
            telemetry.addData("speed sent to slide", g1triggers+" "+bot.sentToSlide+" "+bot.sentToSlide2);
            telemetry.update();
        }
    }
}
