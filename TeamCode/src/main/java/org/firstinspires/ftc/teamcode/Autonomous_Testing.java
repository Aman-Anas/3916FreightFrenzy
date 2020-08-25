package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "Test Autonomous", group = "Apex Robotics 3916")
public class Autonomous_Testing extends OpMode {
    private Robot bot = new Robot();
    private ElapsedTime runtime = new ElapsedTime();
    private String timeOfCompletion;

    private int state = 0;
    private String telemetryMsg;

    @Override
    public void init() {
        bot.init(hardwareMap, Robot.DriveType.Mechanum);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void stop() {
        bot.stopDriving();
    }

    @Override
    public void loop() {
        switch (state) {
            // even numbers for performing an action while odd values for state are used for waiting, seen in default case
            case 0:
                // drive forward
                new Thread(new Tasker("drive,0,1,3000")).start();
                state++;

                break;
            case 2:
                //turn for a bit
                new Thread(new Tasker("turn,0,1000"));
                state++;
                break;
            case 4:
                //go forward but sideways
                new Thread(new Tasker("drive,0,1,3000"));
                state++;
                break;
            case 6:
                // autonomous is done as robot has parked
                telemetryMsg = "autonomous completed in " + runtime.toString() + " seconds";
                break;
            default:
                //pause for a bit
                new Thread(new Tasker("pause,1000"));
                //state++;
                break;
        }

        telemetry.addData("runtime", runtime.toString() + " seconds");

        if (telemetryMsg != null) {
            telemetry.addData("*", "-----------");
            telemetry.addData("*", telemetryMsg);
        }

        telemetry.update();
    }

    private class Tasker implements Runnable {
        private String task;
        private String orientation;

        Tasker(String task) {
            this.task = task;
        }

        @Override
        public void run() {
            // first value from split string should be the task to perform
            // other split values are arguments
            String[] split = task.split(",");

            switch (split[0]) {
                case "drive":
                    // drive with args for x and y as in if a controller input
                    double x = Double.parseDouble(split[1]);
                    double y = Double.parseDouble(split[2]);
                    telemetryMsg = "driving at x=" + x + " y=" + y;
                    bot.mech_drive(x, y);
                    pause(Long.parseLong(split[3]));
                    bot.stopDriving();
                    telemetryMsg = "done driving";
                    break;
                case "turn":
                    // rotating the mech drive as in if a controller input
                    int direction = Integer.parseInt(split[1]);
                    telemetryMsg = "turning with direction " + direction;
                    bot.mech_rotate(direction);
                    pause(Long.parseLong(split[2]));
                    telemetryMsg = "done rotating";
                    break;
                case "pause":
                    //wait for a bit
                    telemetryMsg = "waiting for a bit";
                    pause(Long.parseLong(split[1]));
                    telemetryMsg = "done waiting";
                    break;
                default:
                    telemetryMsg = split[0] + " is either spelled wrong or hasn't been implemented yet";
                    break;
            }

            state++;
        }
        private void pause(long ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException ex) {
                telemetry.addData("ERROR", ex.getMessage());
            }
        }
    }
}