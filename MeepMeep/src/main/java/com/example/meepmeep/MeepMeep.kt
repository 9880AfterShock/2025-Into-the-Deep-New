package com.example.meepmeeptesting

import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity

object MeepMeepTesting {
    @JvmStatic
    fun main(args: Array<String>) {
        val meepMeep: MeepMeep = MeepMeep(800)

        val myBot: RoadRunnerBotEntity =
            DefaultBotBuilder(meepMeep) // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180.0), Math.toRadians(180.0), 15)
                .build()

        myBot.runAction(
            myBot.getDrive().actionBuilder(Pose2d(0.0, 0.0, 0.0))
                .lineToX(30)
                .turn(Math.toRadians(90.0))
                .lineToY(30)
                .turn(Math.toRadians(90.0))
                .lineToX(0)
                .turn(Math.toRadians(90.0))
                .lineToY(0)
                .turn(Math.toRadians(90.0))
                .build()
        )

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
            .setDarkMode(true)
            .setBackgroundAlpha(0.95f)
            .addEntity(myBot)
            .start()
    }
}