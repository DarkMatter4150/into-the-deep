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

    Gamepad previousGamepad2 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();


    @Override
    public void init() {
        chassis = new Chassis(
                hardwareMap.get(DcMotor.class, "fl"),
                hardwareMap.get(DcMotor.class, "bl"),
                hardwareMap.get(DcMotor.class, "fr"),
                hardwareMap.get(DcMotor.class, "br"));
        slides = new Slides(hardwareMap);
        specSystem = new SpecSystem(hardwareMap);
        sampleSystem = new SampleSystem(hardwareMap);
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
        if (currentGamepad2.y && !previousGamepad2.y){
            linkageToggle = !linkageToggle;
        }
        if (currentGamepad2.x && !previousGamepad2.x){
            arclinkageToggle = !arclinkageToggle;
        }

        chassis.Drive(gamepad1);

        //TODO Slides PID
        //slides.Move(gamepad2.right_trigger);

        //SpecSystem
//      specSystem.Flip(flipperToggle); //Tested
//      specSystem.Claw(clawToggle); //Zero Servos

        //SampleSystem
        sampleSystem.RunRollers(gamepad2.right_trigger - gamepad2.left_trigger); //Tested
//      sampleSystem.MoveLinkage(linkageToggle); //Zero Servos
//      sampleSystem.MoveArcLinkage(arclinkageToggle); //Zero Servos
    }
}
