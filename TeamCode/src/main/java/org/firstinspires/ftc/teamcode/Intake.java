package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {

    private DcMotor intakeMotor;
    Gamepad manipulator;

    public Intake(DcMotor intakeMotor, Gamepad manipulator) {
        this.intakeMotor = intakeMotor;

        this.manipulator = manipulator;
    }

    public void run() {
        float power = manipulator.right_trigger - manipulator.left_trigger;
        intakeMotor.setPower(power);

    }

}
