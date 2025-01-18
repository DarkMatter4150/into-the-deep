package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.systems.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.systems.subsystems.Slides;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    private Chassis chassis;

    private Slides slides;

    @Override
    public void init() {
        chassis = new Chassis(
                hardwareMap.get(DcMotor.class, "fl"),
                hardwareMap.get(DcMotor.class, "bl"),
                hardwareMap.get(DcMotor.class, "fr"),
                hardwareMap.get(DcMotor.class, "br"));
        slides = new Slides(hardwareMap);
    }

    @Override
    public void loop() {
        chassis.NewDrive(gamepad1);

        slides.Move(gamepad2.right_trigger);
    }
}
