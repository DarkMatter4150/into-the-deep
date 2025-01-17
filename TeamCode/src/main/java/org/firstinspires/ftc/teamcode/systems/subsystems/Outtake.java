package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake {
    private Servo retainer;
    private double retainerClose = 0.5;
    private double retainerOpen = 0.5;
    private Servo outtake;
    private double collectPos = 0.5;
    private double depostPos = 0.5;

    public Outtake(HardwareMap hardwareMap) {
        retainer = hardwareMap.servo.get("retainer");
        outtake = hardwareMap.servo.get("outtake");
    }

    public void CloseRetainer() {
        retainer.setPosition(retainerClose);
    }

    public void OpenRetainer() {
        retainer.setPosition(retainerOpen);
    }

    public void Collect() {
        outtake.setPosition(collectPos);
    }

    public void Deposit() {
        outtake.setPosition(depostPos);
    }
}
