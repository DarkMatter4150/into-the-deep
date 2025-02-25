package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SampleSystem {
    private Servo linkage1;
    private Servo linkage2;

    private CRServo roller1;
    private CRServo roller2;

    private CRServo angular1;
    private CRServo angular2;

    private double intakePosDown = .5;

    public SampleSystem(HardwareMap hardwareMap) {
        linkageOne = hardwareMap.servo.get("linkageOne");
        linkageTwo = hardwareMap.servo.get("linkageTwo");

        roller1 = hardwareMap.crservo.get("leftWheel");
        roller2 = hardwareMap.crservo.get("rightWheel");

        angular1 = hardwareMap.crservo.get("leftLifter");
        angular2 = hardwareMap.crservo.get("rightLifter");
    }

    public void FlipDown() {
        intake.setPosition(intakePosDown);

    }

    public void FlipUp() {
        intake.setPosition(0);
    }

    public void In(double range) {
        roller1.setPower(-range);
        roller2.setPower(range);
    }

    public void Stop() {
        roller1.setPower(0);
        roller2.setPower(0);
    }

    public void Out() {
        roller1.setPower(-1);
        roller2.setPower(-1);
    }

}
