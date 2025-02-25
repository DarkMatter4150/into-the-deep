package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.PID;

public class Slides {
    DcMotor primary;
    DcMotor secondary;

    PID controller = new PID(0, 0, 0);

    public Slides(HardwareMap hardwareMap) {
        primary = hardwareMap.dcMotor.get("primaryY");
        secondary = hardwareMap.dcMotor.get("secondaryY");
    }

    public void Move(float throttle) {
        secondary.setPower(throttle);
        primary.setPower(-throttle);
    }

    public void MovePID(int targetPos, ElapsedTime runtime) {

        if(targetPos < -750) targetPos = -750;
        if(targetPos > 0) targetPos = 0;

        // Pos is from the rev encoder; not dependent on the motor
        int currentPos = primary.getCurrentPosition();

        double power = controller.Calculate(targetPos, currentPos, runtime);

        secondary.setPower(power);
        primary.setPower(-power);
    }

    public int getPos() {
        return primary.getCurrentPosition();
    }
}