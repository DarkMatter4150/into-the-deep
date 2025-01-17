package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo intake;
    private double intakePosDown = 0.5;
    private double intakePosUp = 0.5;

    private CRServo roller1;
    private CRServo roller2;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.servo.get("intake");
        roller1 = hardwareMap.crservo.get("roller1");
        roller2 = hardwareMap.crservo.get("roller2");
    }

    public void FlipDown() {
        intake.setPosition(intakePosDown);
    }

    public void FlipUp() {
        intake.setPosition(intakePosUp);
    }

    public void In() {
        roller1.setPower(1);
        roller2.setPower(1);
    }

    public void Stop() {
        roller1.setPower(0);
        roller2.setPower(0);
    }

    public void Out() {
        roller1.setPower(0);
        roller2.setPower(0);
    }

}
