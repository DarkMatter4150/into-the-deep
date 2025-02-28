package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Chassis {

    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;

    //private IMU imu = null;

    public Chassis(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br) {

        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;

        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);

        /* imu = hardwareMap.get(IMU.class, "imu");
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters); */
    }

    public void Drive(Gamepad gamepad1) {
        float max;

        float axial   = -gamepad1.left_stick_y;
        float lateral =  gamepad1.left_stick_x;
        float yaw     =  gamepad1.right_stick_x;

        float leftFrontPower  = axial + lateral + yaw;
        float rightFrontPower = axial - lateral - yaw;
        float leftBackPower   = axial - lateral + yaw;
        float rightBackPower  = axial + lateral - yaw;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        float brake = gamepad1.left_trigger;

        leftFrontPower  *= brake;
        rightFrontPower *= brake;
        leftBackPower   *= brake;
        rightBackPower  *= brake;

        fl.setPower(leftFrontPower);
        fr.setPower(rightFrontPower);
        bl.setPower(leftBackPower);
        br.setPower(rightBackPower);
    }

//    public void DriveFieldCentric(Gamepad gamepad1) {
//        double y = -gamepad1.left_stick_y;
//        double x = gamepad1.left_stick_x;
//        double rx = gamepad1.right_stick_x;
//
//        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
//        double rotX = x * Math.cos(-heading) - y * Math.sin(-heading);
//        double rotY = x * Math.sin(-heading) + y * Math.cos(-heading);
//
//        rotX = rotX * 1.1;
//
//        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
//        double leftFrontPower = (rotY + rotX + rx) / denominator;
//        double leftBackPower = (rotY - rotX + rx) / denominator;
//        double rightFrontPower = (rotY - rotX - rx) / denominator;
//        double rightBackPower = (rotY + rotX - rx) / denominator;
//
//        double brake = gamepad1.left_trigger;
//
//        leftFrontPower  *= brake;
//        rightFrontPower *= brake;
//        leftBackPower   *= brake;
//        rightBackPower  *= brake;
//
//        fl.setPower(leftFrontPower);
//        fr.setPower(rightFrontPower);
//        bl.setPower(leftBackPower);
//        br.setPower(rightBackPower);
//    }
}
