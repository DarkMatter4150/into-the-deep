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

    private double sampleSystemPosDown = .5;

    public SampleSystem(HardwareMap hardwareMap) {
        linkage1 = hardwareMap.servo.get("linkageOne");
        linkage2 = hardwareMap.servo.get("linkageTwo");

        arclinkage1 = hardwareMap.servo.get("leftLifter");
        arclinkage2 = hardwareMap.servo.get("rightLifter");

        roller1 = hardwareMap.crservo.get("leftWheel");
        roller2 = hardwareMap.crservo.get("rightWheel");
    }

    public void RunRollers(double range) {
        roller1.setPower(-range);
        roller2.setPower(range);
    }

    public void MoveLinkage(boolean toggle) {
        if (toggle) {
            linkage1.setPosition(sampleSystemPosDown);
            linkage2.setPosition(1 - sampleSystemPosDown);
        } else {
            linkage1.setPosition(0);
            linkage2.setPosition(1);
        }
    }

    public void MoveArcLinkage(boolean toggle) {
        if (toggle) {
            arclinkage1.setPosition(sampleSystemPosDown);
            arclinkage2.setPosition(1 - sampleSystemPosDown);
        } else {
            arclinkage1.setPosition(0);
            arclinkage2.setPosition(1);
        }
    }

}
