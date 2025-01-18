package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.PID;
import org.firstinspires.ftc.teamcode.systems.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.systems.subsystems.Slides;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    private Chassis chassis;
    private Slides slidesY;

    private Slides slidesX;

    @Override
    public void init() {
        chassis = new Chassis(hardwareMap);
        slidesY = new Slides(hardwareMap);
        slidesX = new Slides(hardwareMap);
    }

    @Override
    public void loop() {
        ElapsedTime runtime = new ElapsedTime();

        chassis.Drive(gamepad1);

        slidesY.Up(gamepad2.right_trigger);



//        targetPosY -= (int) (-gamepad2.left_stick_y * 10);
//        YSlides.Move(targetPosY, runtime);
//
//        telemetry.addData("Lift Pos", YSlides.getPos());
    }
}
