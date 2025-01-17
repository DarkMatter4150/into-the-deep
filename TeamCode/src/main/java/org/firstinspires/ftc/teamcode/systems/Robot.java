package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.systems.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.systems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.systems.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.systems.subsystems.Slides;

public class Robot {

    private Chassis chassis;

    private PID YController = new PID(0.1, 0, 0);
    private Slides YSlides;

    private PID XController = new PID(0.1, 0, 0);
    private Slides XSlides;

    private Intake intake;
    private Outtake outtake;

    public Robot(HardwareMap hardwareMap) {
        chassis = new Chassis(hardwareMap);
        YSlides = new Slides(hardwareMap, YController, true);
        XSlides = new Slides(hardwareMap, XController, false);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
    }

    public void Drive(Gamepad gamepad) {
        chassis.Drive(gamepad);
    }

    public void MoveYSlides(int targetPos) {
        YSlides.Move(targetPos);
    }

    public void MoveXSlides(int targetPos) {
        XSlides.Move(targetPos);
    }
}
