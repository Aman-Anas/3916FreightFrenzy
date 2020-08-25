/*
Apex Robotics FTC Team 3916: Main TeleOp for SkyStone season (2019-2020)

Uses a Mechanum-style drivetrain for movement.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Basic TeleOp", group="Apex Robotics 3916")
//@Disabled
public class TeleOp_Basic extends OpMode {

    private Robot bot = new Robot();

    @Override
    public void init() {
        bot.init(hardwareMap, Robot.DriveType.Mechanum);
    }

    @Override
    public void loop() {
        final double STICK_DEAD_ZONE = 0.1;

        double x = 0;
        double y = 0;
        if (gamepad1.left_bumper) {
            bot.mech_rotate(0);
            telemetry.addData("Status", "Rotating Counterclockwise");
            telemetry.update();
        } else if (gamepad1.right_bumper) {
            bot.mech_rotate(1);
            telemetry.addData("Status", "Rotating Clockwise");
            telemetry.update();
        } else {
            if (Math.abs(gamepad1.left_stick_y) > STICK_DEAD_ZONE) {
                y = gamepad1.left_stick_y;
            }
            if (Math.abs(gamepad1.left_stick_x) > STICK_DEAD_ZONE) {
                x = gamepad1.left_stick_x;
            }
            MechPower pwr = bot.mech_drive(x, y);
            telemetry.addData("Status", "power: x:" + x + " y:" + y + " =pwr:" + pwr.toString());
            telemetry.update();
        }
    }
}