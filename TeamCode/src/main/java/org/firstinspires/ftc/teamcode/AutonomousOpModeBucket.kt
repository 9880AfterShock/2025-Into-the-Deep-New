package org.firstinspires.ftc.teamcode

// RR-specific imports
// Non-RR imports
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.TrajectoryActionBuilder
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.ftc.*
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.subsystems.MainLift


@Config
@Autonomous(name = "9880 2024 Blue Bucket", group = "Autonomous")
class AutonomousOpModeBucket : LinearOpMode() {

// lift class

    override fun runOpMode() {
        Raiser.initRaiser(this) //does run
        Wrist.initWrist(this)
        Claw.initClaw(this)
        MainLift.initLift(this)

        val drive = MecanumDrive(hardwareMap, startPoseBlueBucket)
        var firstBucket = drive.actionBuilder(startPoseBlueBucket)
            .splineToLinearHeading(bucketPoseBlue, 0.0)
        var pickUpNeutral = drive.actionBuilder(bucketPoseBlue)
            .strafeToLinearHeading(Vector2d(35.0, 46.5), 0.0)
            .strafeToLinearHeading(neutralPoseBlue.position, neutralPoseBlue.heading)
        var secondBucket = drive.actionBuilder(neutralPoseBlue)
            .strafeToLinearHeading(Vector2d(35.0, 46.0), 0.0)
            .splineToLinearHeading(bucketPoseBlue, 0.0)
        var park = drive.actionBuilder(bucketPoseBlue)
            .strafeToLinearHeading(Vector2d(35.0, 0.0), Math.toRadians(135.0))
            .strafeToLinearHeading(bucketParkPoseBlue.position, bucketParkPoseBlue.heading)
        var waitSecondsTwo: TrajectoryActionBuilder = drive.actionBuilder(clipPoseBlue) //fix if clipPoseBLue doesnt work
            .waitSeconds(2.0)

        while (!isStopRequested && !opModeIsActive()) {
            // Do nothing
        }

        waitForStart()

        if (isStopRequested) return
        runBlocking(
            SequentialAction(
                ParallelAction(
                    Raiser.autoRaiserUp(), //this runs
                    MainLift.autoLiftMax(), //not this?
                    firstBucket.build(), //not tested
                ),

                Wrist.autoWristGoToPos(Wrist.positions[1]), //not run
                Claw.autoClawOpen(),
                Wrist.autoWristGoToPos(Wrist.positions[2]),

                ParallelAction(
                    pickUpNeutral.build(),
                    SequentialAction(
                        MainLift.autoLiftMaxLow(),
                        Raiser.autoRaiserDown(),
                    ),
                    Wrist.autoWristGoToPos(Wrist.positions[1]),
                ),
                Claw.autoClawOpen(),
                ParallelAction(
                    Wrist.autoWristGoToPos(Wrist.positions[0]),
                    Claw.autoClawClose(),
                ),
                ParallelAction(
                    SequentialAction(
                        Raiser.autoRaiserUp(),
                        MainLift.autoLiftMax(),
                    ),
                    secondBucket.build(),
                ),

                Wrist.autoWristGoToPos(Wrist.positions[1]),
                Claw.autoClawOpen(),
                Wrist.autoWristGoToPos(Wrist.positions[2]),

                MainLift.autoLiftMin(),
                park.build(),
                Wrist.autoWristGoToPos(Wrist.initPos)
            )
        )
    }
}