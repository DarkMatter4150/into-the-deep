package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class   SpecSystem {
    private Servo claw;
    private Servo flip;

    public SpecSystem(HardwareMap hardwareMap) {
        flip = hardwareMap.servo.get("flip");
        claw = hardwareMap.servo.get("claw");
    }

    public void Claw (boolean clawToggle){
        if (clawToggle) {
            claw.setPosition(0.8);
        }
        else{
            claw.setPosition(1);
        }
    }

    public void Flip (boolean flipperToggle){
        if (flipperToggle){
            flip.setPosition(0.3);
        }
        else{
            flip.setPosition(0);
        }
    }

}
