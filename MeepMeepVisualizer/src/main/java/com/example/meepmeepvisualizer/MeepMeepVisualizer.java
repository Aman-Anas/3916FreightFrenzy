package com.example.meepmeepvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MeepMeepVisualizer {

    public static void main (String args[]){
        Image lightFieldImage = null;
        Image darkFieldImage = null;

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep mm = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(mm)

                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                // Set bot color
                .setColorScheme(new ColorSchemeRedDark())

                // Set path for bot to follow
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                                .forward(30)
                                .turn(Math.toRadians(90))
                                .forward(30)
                                .turn(Math.toRadians(90))
                                .forward(30)
                                .turn(Math.toRadians(90))
                                .forward(30)
                                .turn(Math.toRadians(90))
                                .build()
                );

        try {
            lightFieldImage = ImageIO.read(new File("ffLight.png"));
            darkFieldImage = ImageIO.read(new File("ffDark.png"));
        } catch (IOException e) {
            //basically do nothing in case of error
            assert true;
        }


        mm
                // Set field image

                //.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_OFFICIAL) //Default
                //.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK) //Default Dark

                .setBackground(darkFieldImage) //Custom Dark
                //.setBackground(lightFieldImage) //Custom Light

                // Set theme
                .setTheme(new ColorSchemeRedDark())

                // Background opacity from 0-1
                .setBackgroundAlpha(1f)

                // Add the bot(s) we want
                .addEntity(myBot)
                //.addEntity(secondBot)

                // Start the visualization
                .start();


    }
}