package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SampleSystem {
    private Servo linkage1;
    private Servo linkage2;

    private Servo arclinkage1;
    private Servo arclinkage2;

    private CRServo roller1;
    private CRServo roller2;

    public SampleSystem(HardwareMap hardwareMap) {
        linkage1 = hardwareMap.servo.get("linkage1");
        linkage2 = hardwareMap.servo.get("linkage2");

        arclinkage1 = hardwareMap.servo.get("arclinkage1");
        arclinkage2 = hardwareMap.servo.get("arclinkage2");

        roller1 = hardwareMap.crservo.get("roller1");
        roller2 = hardwareMap.crservo.get("roller2");
    }

    public void RunRollers(double range) {
        roller1.setPower(-range);
        roller2.setPower(range);
    }

    public void MoveLinkage(boolean toggle) {
        if (toggle) {
            linkage1.setPosition(0.85);
            linkage2.setPosition(0.15);
        } else {
            linkage1.setPosition(1);
            linkage2.setPosition(0);
        }
    }

    public void MoveArcLinkage(boolean toggle) {
        if (toggle) {
            arclinkage1.setPosition(0.59);
            arclinkage2.setPosition(0.41);
        } else {
            arclinkage1.setPosition(1);
            arclinkage2.setPosition(0);
        }
    }

}
