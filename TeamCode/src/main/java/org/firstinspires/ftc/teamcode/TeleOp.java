package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TeleOp extends OpMode {

    private Drivebase drivebase;

    @Override
    public void init() {
        drivebase = new Drivebase(hardwareMap.dcMotor.get("fl"), hardwareMap.dcMotor.get("fr"), hardwareMap.dcMotor.get("bl"), hardwareMap.dcMotor.get("br"), gamepad1);
    }

    @Override
    public void loop() {
        drivebase.drive();
    }
}
