package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chassis {

    private DcMotor[] motors = new DcMotor[4];

    public Chassis(HardwareMap hardwareMap) {
        motors[0] = hardwareMap.dcMotor.get("fl");
        motors[1] = hardwareMap.dcMotor.get("fr");
        motors[2] = hardwareMap.dcMotor.get("bl");
        motors[3] = hardwareMap.dcMotor.get("br");
    }

    public void Drive(Gamepad gamepad) {
        float strafe = 1.1f;
        double y = -gamepad.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad.left_stick_x * strafe; // Counteract imperfect strafing
        double rx = gamepad.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // Vary the speed multiplier based on the left trigger
        double brake = gamepad.left_trigger;

        double[] powers = {(y + x + rx), (y - x + rx), (y - x - rx), (y + x - rx)};

        for(int i = 0; i < 4; i++) {
            powers[i] = (powers[i] / denominator) * brake;
            motors[i].setPower(powers[i]);
        }
    }

}
