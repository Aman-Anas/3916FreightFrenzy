package com.example.meepmeepvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepVisualizer {
    public static void main(String args[]) {
        Image lightFieldImage = null;
        Image darkFieldImage = null;
        Image realisticFieldImage = null;

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep mm = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(mm)

                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                // Set bot color
                //.setColorScheme(new ColorSchemeRedDark())

                // Set path for bot to follow
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, -63.0, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(-52.0, -53.0, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-57.0, -59.1, Math.toRadians(130)))
                                .lineToLinearHeading(new Pose2d(-24.7, -32.1, Math.toRadians(-90)+Math.toRadians(-46)))
                                .lineToLinearHeading(new Pose2d(-30.0, -35.0, Math.toRadians(-136)))
                                .lineToLinearHeading(new Pose2d(-60.0, -35.0, Math.toRadians(-90)+Math.toRadians(-0)))
                                .build()
                );

        try {
            lightFieldImage = ImageIO.read(new File("ffLight.png"));
            darkFieldImage = ImageIO.read(new File("ffDark.png"));
            realisticFieldImage = ImageIO.read(new File("ffRealistic.png"));
        } catch (IOException e) {
            //basically do nothing in case of error
            assert true;
        }


        mm
                // Set field image

                //.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_OFFICIAL) //Default
                //.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK) //Default Dark

                //.setBackground(darkFieldImage) //Custom Dark
                //.setBackground(lightFieldImage) //Custom Light
                .setBackground(realisticFieldImage) //Custom Realistic

                // Set theme
                //.setTheme(new ColorSchemeRedDark())

                // Background opacity from 0-1
                .setBackgroundAlpha(1f)

                // Add the bot(s) we want
                .addEntity(myBot)
                //.addEntity(secondBot)

                // Start the visualization
                .start();
    }
}