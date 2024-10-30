package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group="teleop")
public class Tele extends OpMode {

    private Drivebase drivebase;
    private Intake intake;

    @Override
    public void init() {
        drivebase = new Drivebase(hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br"), gamepad1);
        intake = new Intake(hardwareMap.dcMotor.get("intake"), gamepad2);

    }

    @Override
    public void loop() {
        drivebase.drive();
        intake.run();
    }
}
