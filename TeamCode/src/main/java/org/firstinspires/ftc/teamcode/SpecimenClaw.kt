package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.SpecimenSwivel.inPos
import org.firstinspires.ftc.teamcode.SpecimenSwivel.swivel


object SpecimenClaw {
    private lateinit var claw: Servo
    @JvmField
    var openPos = 0.7 //the positions
    @JvmField
    var closePos = 1.0 //the positions
    var state = "Closed"
    private var clawButtonCurrentlyPressed = false
    private var clawButtonPreviouslyPressed = false

    lateinit var opmode:OpMode
    fun initClaw(opmode: OpMode){
        claw = opmode.hardwareMap.get(Servo::class.java, "Specimen Claw") //config name
        this.opmode = opmode
        state = "Closed"
    }
    fun open() {
        claw.position = openPos
        state = "Open"
    }
    private fun close(){
        claw.position = closePos //claw doesnt move
        state = "Closed" //this runs
    }
    private fun swap(){
        if (state == "Open") {
            close()
        } else {
            open()
        }
    }
    fun updateClaw() {
        opmode.telemetry.addData("Specimen Claw State", state)
        // Check the status of the claw button on the gamepad
        clawButtonCurrentlyPressed = opmode.gamepad1.b //change this to change the button

        // If the button state is different than what it was, then act
        if (clawButtonCurrentlyPressed != clawButtonPreviouslyPressed) {
            // If the button is (now) down
            if (clawButtonCurrentlyPressed) {
                swap()
            }
        }
        clawButtonPreviouslyPressed = clawButtonCurrentlyPressed

    }
    class autoSpecClawSwap: Action {
        override fun run(p: TelemetryPacket): Boolean {
            //LiftRun.currTargetInTicks = maxPos.toInt() * encoderTicks.toInt()
            swap()
            return true
        }
    }
}