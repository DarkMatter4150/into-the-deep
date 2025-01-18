package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chassis {

//  private DcMotor[] motors = new DcMotor[4];

    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    public Chassis(DcMotor fl, DcMotor bl, DcMotor fr, DcMotor br) {
//        motors[0] = hardwareMap.dcMotor.get("fl");
//        motors[1] = hardwareMap.dcMotor.get("fr");
//        motors[2] = hardwareMap.dcMotor.get("bl");
//        motors[3] = hardwareMap.dcMotor.get("br");

        leftFrontDrive = fl;
        leftBackDrive = bl;
        rightFrontDrive = fr;
        rightBackDrive = br;

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void Drive(Gamepad gamepad) {
        double strafe = 1.1;
        double y = -gamepad.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad.left_stick_x * strafe; // Counteract imperfect strafing
        double rx = gamepad.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // Vary the speed multiplier based on the left trigger
        double brake = gamepad.left_trigger;

        double flPow = ((y + x + rx) / denominator) * brake;
        double frPow = ((y - x + rx) / denominator) * brake;
        double blPow = ((y - x - rx) / denominator) * brake;
        double brPow = ((y + x - rx) / denominator) * brake;

//        motors[0].setPower(flPow);
//        motors[1].setPower(frPow);
//        motors[2].setPower(blPow);
//        motors[3].setPower(brPow);
    }

    public void NewDrive(Gamepad gamepad1) {
        double max;

        double axial   = -gamepad1.left_stick_y;
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }
    }

}
