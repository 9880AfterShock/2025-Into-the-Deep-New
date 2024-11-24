package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Raiser.motor
import org.firstinspires.ftc.teamcode.Raiser.upPos


object Claw {
    private lateinit var claw: Servo
    @JvmField
    var openPos = 1.0 //the positions
    @JvmField
    var closePos = 0.6 //the positions
    private var state = "Closed"
    private var clawButtonCurrentlyPressed = false
    private var clawButtonPreviouslyPressed = false

    lateinit var opmode:OpMode
    fun initClaw(opmode: OpMode){
        claw = opmode.hardwareMap.get(Servo::class.java, "Claw") //config name
        this.opmode = opmode
        state = "Closed"
    }
    private fun open() {
        if (Wrist.currentPos != 0) { //prevent from opening if far down
            claw.position = openPos
            state = "Open"
        }
    }
    fun close(){
        claw.position = closePos //claw doesnt move
        state = "Close" //this runs
    }
    /*private*/ fun clawSwap(){ //removed private and renamed for auto purposes -Oscar
        if (state == "Open") {
            close()
        } else {
            open()
        }
    }
    fun updateClaw() {
        opmode.telemetry.addData("Claw State", state)
        // Check the status of the claw button on the gamepad
        clawButtonCurrentlyPressed = opmode.gamepad1.a //change this to change the button

        // If the button state is different than what it was, then act
        if (clawButtonCurrentlyPressed != clawButtonPreviouslyPressed) {
            // If the button is (now) down
            if (clawButtonCurrentlyPressed) {
                clawSwap()
            }
        }
        clawButtonPreviouslyPressed = clawButtonCurrentlyPressed

    }
    class autoClawOpen: Action {
        override fun run(p: TelemetryPacket): Boolean {
            open()
            return false
        }
    }
    class autoClawClose: Action {
        override fun run(p: TelemetryPacket): Boolean {
            close()
            return false
        }
    }
}