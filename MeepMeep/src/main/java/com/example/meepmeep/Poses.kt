package com.example.meepmeep
//imports
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d


// Poses
// blue side
var startPoseBlueBucket = Pose2d(32.8, 59.2, 0.0)
var neutralPoseBlue = Pose2d(35.0, 26.0,0.0)
var bucketPoseBlue = Pose2d(47.1, 46.5, Math.toRadians(45.0))
var bucketParkPoseBlue = Pose2d(26.0, 0.0, Math.toRadians(180.0))
var startPoseBlue = Pose2d(-14.0, 61.0, 0.0) // use for big
var clipPoseBlue = Pose2d(-7.0, 33.25, 0.0) // use for big
var backPoseBlue = Pose2d(0.0, 40.0, 0.0)
var parkPoseBlue = Pose2d(-57.0, 57.0, Math.toRadians(-90.0))

// red side
var startPoseRed = Pose2d(14.0, -61.0, Math.toRadians(180.0))
var clipPoseRed = Pose2d(7.0, -33.25, Math.toRadians(180.0))
var backPoseRed = Pose2d(0.0, -40.0, Math.toRadians(180.0))
var parkPoseRed = Pose2d(57.0, -57.0, Math.toRadians(90.0))
// big auto
var pushPrepPoseBig = Pose2d(-34.8,12.1, Math.PI/2)
var pushPrepPoseRightBig = Vector2d(-43.9,12.1) //good
var pushPoseRightBig = Vector2d(-43.9, 50.0) //good
var pushPrepPoseMidBig = Vector2d(-52.2,12.1) // good
var pushPoseMidBig = Vector2d(-52.2, 50.0) //good
var specStartPickupPoseBig = Pose2d(-35.2, 58.0, Math.PI) // good original x is -36.2
var specEndPickupPoseBig = Pose2d(-42.2,57.95, Math.PI) // test for moving
var specStartPickupPoseLastBig = Pose2d(-36.2, 59.2, Math.PI)
var specEndPickupPoseLastBig = Pose2d(-41.2, 60.2, Math.PI)
var clipPoseBlueTheSecond = Pose2d(0.0, 32.8, 0.0)
var clipPoseBlueTheThird = Pose2d(5.0, 33.85, 0.0)
var parkPoseBlueBig = Pose2d(-53.0, 57.0, Math.toRadians(0.0)) // use for big
// Paths