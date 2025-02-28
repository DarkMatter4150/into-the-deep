package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.systems.subsystems.Chassis;
import org.firstinspires.ftc.teamcode.systems.subsystems.Slides;
import org.firstinspires.ftc.teamcode.systems.subsystems.SpecSystem;
import org.firstinspires.ftc.teamcode.systems.subsystems.SampleSystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    private Chassis chassis;

    private Slides slides;

    private SpecSystem specSystem;
    private boolean flipperToggle = false;
    private boolean clawToggle = false;

    private SampleSystem sampleSystem;
    private boolean linkageToggle = false;
    private boolean arclinkageToggle = false;

    private boolean stateMachineToggle = false;
    private int stateCounter = 0;
    private String stateButtonSequence = "XYX";

    Gamepad previousGamepad2 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    @Override
    public void init() {
        chassis = new Chassis(hardwareMap);
        slides = new Slides(hardwareMap);
        sampleSystem = new SampleSystem(hardwareMap);
        specSystem = new SpecSystem(hardwareMap);
    }

    @Override
    public void loop() {
        previousGamepad2.copy(currentGamepad2);
        currentGamepad2.copy(gamepad2);
        if (currentGamepad2.b && !previousGamepad2.b){
            flipperToggle = !flipperToggle;
        }
        if (currentGamepad2.a && !previousGamepad2.a){
            clawToggle = !clawToggle;
        }

        if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up){
            stateCounter++;
        }
        if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down){
            stateCounter--;
        }
        if (stateCounter < 0){
            stateCounter = stateButtonSequence.length() - 1;
        }
        if (stateCounter >= stateButtonSequence.length()){
            stateCounter = 0;
        }

        if (stateMachineToggle){
            StateMachine();
        } else {
            ManualControl();
        }

        if(currentGamepad2.right_bumper && !previousGamepad2.right_bumper){
            stateMachineToggle = !stateMachineToggle;
            stateCounter = 0;
            linkageToggle = false;
            arclinkageToggle = false;
        }

        chassis.Drive(gamepad1);

        //Truncate joystick negative values
        float rightStick = -gamepad2.right_stick_y;
        if (rightStick < 0) {
            rightStick = 0;
        }
        float slidePowerScaler = 0.5f;

        slides.Move(rightStick * slidePowerScaler);

        //SpecSystem
        specSystem.Flip(flipperToggle);
        specSystem.Claw(clawToggle);

        //SampleSystem
        sampleSystem.RunRollers(gamepad2.left_trigger - gamepad2.right_trigger);
        sampleSystem.MoveLinkage(linkageToggle);
        sampleSystem.MoveArcLinkage(arclinkageToggle);
    }

    private void StateMachine(){
        switch (stateButtonSequence.charAt(stateCounter)){
            case 'X':
                arclinkageToggle = !arclinkageToggle;
                break;
            case 'Y':
                linkageToggle = !linkageToggle;
                break;
        }
    }

    private void ManualControl(){
        if (currentGamepad2.y && !previousGamepad2.y){
            linkageToggle = !linkageToggle;
        }
        if (currentGamepad2.x && !previousGamepad2.x){
            arclinkageToggle = !arclinkageToggle;
        }
    }
}
