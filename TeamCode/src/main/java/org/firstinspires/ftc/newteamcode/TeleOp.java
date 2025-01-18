package org.firstinspires.ftc.teamcode.newteamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    DcMotor primarySlide = null;
    DcMotor secondarySlide = null;

    private CRServo roller1;
    private CRServo roller2;

    private Servo retracter;

    boolean retracterToggle = false;

    private Servo retainer;
    boolean retainerToggle = false;

    private Servo flipper;
    boolean flipperToggle = false;

    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    @Override
    public void init() {
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "fl");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "bl");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "fr");
        rightBackDrive = hardwareMap.get(DcMotor.class, "br");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        primarySlide = hardwareMap.dcMotor.get("primaryY");
        secondarySlide = hardwareMap.dcMotor.get("secondaryY");

        roller1 = hardwareMap.crservo.get("leftWheel");
        roller2 = hardwareMap.crservo.get("rightWheel");

        retracter = hardwareMap.servo.get("retracter");
        retainer = hardwareMap.servo.get("retainer");
        flipper = hardwareMap.servo.get("flipper");
    }

    @Override
    public void loop() {
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);

        if (currentGamepad2.a && !previousGamepad2.a) {
            retracterToggle = !retracterToggle;
        }
        if (currentGamepad2.b && !previousGamepad2.b) {
            retainerToggle = !retainerToggle;
        }
        if (currentGamepad2.x && !previousGamepad2.x) {
            flipperToggle = !flipperToggle;
        }

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

        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);

        double throttle = gamepad2.right_trigger;
        double dethrottle = gamepad2.left_trigger;

        secondarySlide.setPower(-throttle + dethrottle);
        primarySlide.setPower(throttle - dethrottle);

        double range = -gamepad2.right_stick_y;

        roller1.setPower(-range);
        roller2.setPower(range);

        if(retracterToggle) {
            retracter.setPosition(1);
        }
        else {
            retracter.setPosition(0);
        }

        if(retainerToggle) {
            retainer.setPosition(1);
        }
        else {
            retainer.setPosition(0);
        }

        if(flipperToggle) {
            flipper.setPosition(1);
        }
        else {
            flipper.setPosition(0);
        }

    }
}
