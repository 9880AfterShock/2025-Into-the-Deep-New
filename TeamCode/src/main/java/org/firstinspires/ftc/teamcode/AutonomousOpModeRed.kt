package org.firstinspires.ftc.teamcode

// RR-specific imports
// Non-RR imports
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.TrajectoryActionBuilder
import com.acmerobotics.roadrunner.ftc.*
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.SpecimenSwivel.initSwivel


@Config
@Autonomous(name = "9880 2024 Red Basket", group = "Autonomous")
class AutonomousOpModeRed : LinearOpMode() {

// lift class

    override fun runOpMode() {
        initSwivel(this) // swivel is inited at the start, this works
        SpecimenClaw.initClaw(this) //
        // instantiate your MecanumDrive at a particular pose.
        //val initialPose = Pose2d(11.8, 61.7, Math.toRadians(90.0))
        val drive = MecanumDrive(hardwareMap, startPoseRed)



        // actionBuilder builds from the drive steps passed to it
        var startToClipRed: TrajectoryActionBuilder = drive.actionBuilder(startPoseRed)
            .splineToSplineHeading(clipPoseRed, Math.toRadians(90.0))
        var waitSecondsFive: TrajectoryActionBuilder = drive.actionBuilder(clipPoseRed)
            .waitSeconds(5.0)
        var waitSecondsTwo: TrajectoryActionBuilder = drive.actionBuilder(clipPoseRed)
            .waitSeconds(2.0)
        var clipToParkRed: TrajectoryActionBuilder = drive.actionBuilder(clipPoseRed)
            .splineToSplineHeading(parkPoseRed, Math.toRadians(-50.0))


        // help!!! get rowan, or look at what they do in github, alt use the prev years github, may be the same?
        /*var tab2: TrajectoryActionBuilder = drive.actionBuilder(initialPose)
            .lineToY(37.0)
            .setTangent(Math.toRadians(0.0))
            .lineToX(18.0)
            .waitSeconds(3.0)
            .setTangent(Math.toRadians(0.0))
            .lineToXSplineHeading(46.0, Math.toRadians(180.0))
            .waitSeconds(3.0)

        var tab3: TrajectoryActionBuilder = drive.actionBuilder(initialPose)
            .lineToYSplineHeading(33.0, Math.toRadians(180.0))
            .waitSeconds(2.0)
            .strafeTo(Vector2d(46.0, 30.0))
            .waitSeconds(3.0)*/

        /*var trajectoryActionCloseOut: Action = tab1.fresh()
            .strafeTo(Vector2d(48.0, 12.0))
            .build()*/

        while (!isStopRequested && !opModeIsActive()) {
            // Do nothing
        }

        waitForStart()

        if (isStopRequested) return

        // val startPosition = 1


        runBlocking(
            SequentialAction(
                ParallelAction(
                    startToClipRed.build(),
                    // get things to move
                    SpecimenSwivel.autoSpecSwivOut(),
                ),
                ParallelAction(

                    SpecimenLift.autoSpecLiftUp(), // might work
                    waitSecondsFive.build(),
                    //SpecimenClaw.autoSpecClawSwap()
                    SpecimenLift.autoSpecLiftScore(),
                    waitSecondsTwo.build()
                ),
                clipToParkRed.build()

                //trajectoryActionChosen,
                //trajectoryActionCloseOut
            )
        )
    }
}