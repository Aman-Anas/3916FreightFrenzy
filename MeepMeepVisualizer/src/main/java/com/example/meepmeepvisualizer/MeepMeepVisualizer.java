package com.example.meepmeepvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepVisualizer {
    public static void main (String args[]){

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

        mm
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)

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