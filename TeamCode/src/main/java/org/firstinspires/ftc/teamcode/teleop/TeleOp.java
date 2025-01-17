package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    private Robot robot;

    public int targetPosY;
    public int targetPosX;


    @Override
    public void init() {
        robot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        robot.Drive(gamepad1);

        targetPosY -= (int) (-gamepad2.left_stick_y * 10);
        robot.MoveYSlides(targetPosY);

        targetPosX -= (int) (-gamepad2.right_stick_y * 10);
        robot.MoveXSlides(targetPosX);


    }
}
