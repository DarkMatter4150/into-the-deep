package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.PID;

public class Slides {
    PID controller;

    boolean isY;

    DcMotor primary;
    DcMotor secondary;

    public Slides(HardwareMap hardwareMap, PID controller, boolean isY) {
        this.controller = controller;
        this.isY = isY;
        if(isY) {
            primary = hardwareMap.dcMotor.get("primaryY");
            secondary = hardwareMap.dcMotor.get("secondaryY");
        } else {
            primary = hardwareMap.dcMotor.get("primaryX");
        }
    }

    public void Move(int targetPos) {
        // Pos is from the rev encoder; not dependent on the motor
        int currentPos = primary.getCurrentPosition();

        ElapsedTime runtime = new ElapsedTime();

        double power = controller.Calculate(targetPos, currentPos, runtime);

        if(isY) {
            secondary.setPower(-power);
            primary.setPower(power);
        }
        else {
            primary.setPower(power);
        }
    }
}
