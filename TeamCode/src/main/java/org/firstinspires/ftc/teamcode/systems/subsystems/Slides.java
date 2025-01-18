package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.PID;

public class Slides {
    PID controller;
    DcMotor primary;
    DcMotor secondary;
    DcMotor primaryX;

    public Slides(HardwareMap hardwareMap, PID controller) {
        this.controller = controller;

        primary = hardwareMap.dcMotor.get("primaryY");
        secondary = hardwareMap.dcMotor.get("secondaryY");
    }

    public Slides(HardwareMap hardwareMap) {
        this.controller = controller;

        primary = hardwareMap.dcMotor.get("primaryY");
        secondary = hardwareMap.dcMotor.get("secondaryY");

        primaryX = hardwareMap.dcMotor.get("primaryX");
    }

    public void Up(float throttle) {
        secondary.setPower(throttle);
        primary.setPower(-throttle);
    }

    public void Move(int targetPos, ElapsedTime runtime) {

        if(targetPos < -750) targetPos = -750;
        if(targetPos > 0) targetPos = 0;

        // Pos is from the rev encoder; not dependent on the motor
        int currentPos = primary.getCurrentPosition();

        double power = controller.Calculate(targetPos, currentPos, runtime);

        //secondary.setPower(power);
        primary.setPower(-power);
    }

    public int getPos() {
        return primary.getCurrentPosition();
    }
}
