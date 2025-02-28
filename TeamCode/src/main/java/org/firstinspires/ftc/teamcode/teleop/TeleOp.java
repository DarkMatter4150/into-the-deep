package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.systems.subsystems.Slides;
import org.firstinspires.ftc.teamcode.systems.subsystems.SpecSystem;
import org.firstinspires.ftc.teamcode.systems.subsystems.SampleSystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    private Slides slides;

    private SpecSystem specSystem;
    //private boolean flipperToggle = false;
    private boolean clawToggle = false;

    private SampleSystem sampleSystem;
    private boolean linkageToggle = false;
    private boolean arclinkageToggle = false;

    private boolean stateMachineToggle = false;
    private int stateCounter = 0;
    private String stateButtonSequence = "XYX";

    Gamepad previousGamepad2 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    private boolean firstLoop = false;

    @Override
    public void init() {
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "fl");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "bl");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "fr");
        rightBackDrive = hardwareMap.get(DcMotor.class, "br");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        slides = new Slides(hardwareMap);
        sampleSystem = new SampleSystem(hardwareMap);
        specSystem = new SpecSystem(hardwareMap);
    }

    @Override
    public void loop() {

//        if(!firstLoop) {
//            firstLoop = true;
//        }

        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
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

        previousGamepad2.copy(currentGamepad2);
        currentGamepad2.copy(gamepad2);
//        if (currentGamepad2.b && !previousGamepad2.b){
//            flipperToggle = !flipperToggle;
//        }
        if (currentGamepad2.a && !previousGamepad2.a){
            clawToggle = !clawToggle;
        }

//        if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up){
//            stateCounter++;
//        }
//        if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down){
//            stateCounter--;
//        }
//        if (stateCounter < 0){
//            stateCounter = 2;
//        }
//        if (stateCounter > 2){
//            stateCounter = 0;
//        }

//        if(stateCounter == 0) {
//            arclinkageToggle = !arclinkageToggle;
//        }
//        else if(stateCounter == 1) {
//            linkageToggle = !linkageToggle;
//        }
//        else if (stateCounter == 2) {
//            arclinkageToggle = !arclinkageToggle;
//        }

        if (currentGamepad2.x && !previousGamepad2.x) {
            linkageToggle = !linkageToggle;
        }
        if (currentGamepad2.y && !previousGamepad2.y) {
            arclinkageToggle = !arclinkageToggle;
        }

        float rightStick = -gamepad2.right_stick_y;
        float slidePowerScaler = 0.5f;

        slides.Move(rightStick * slidePowerScaler);

        //SpecSystem
        specSystem.Flip(true);
        specSystem.Claw(clawToggle);

        //SampleSystem
        sampleSystem.RunRollers(gamepad2.left_trigger - gamepad2.right_trigger);
        sampleSystem.MoveLinkage(linkageToggle);
        sampleSystem.MoveArcLinkage(arclinkageToggle);
    }
}
