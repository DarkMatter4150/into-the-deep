package org.firstinspires.ftc.teamcode.systems.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous
public class SampleAuto extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private ElapsedTime time;

    private boolean started = false;
    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        time = new ElapsedTime();
    }

    @Override
    public void loop(){
        if(!started){
            time = new ElapsedTime();
            started=true;
        }
        if(time.seconds() < 0.6){ // go straight to zone
            straight(0.5);
        }else if(time.seconds() < 1){
            stopMoving();
        }else if(time.seconds()<1.5){
            strafeLeft(0.5);
        }
        else if(time.seconds()<2){
            stopMoving();
        }else if(time.seconds() < 2.5){
            turnLeft(0.5);
        }else if (time.seconds() < 4){
            stopMoving();
        }else if(time.seconds() < 5.5){
            turnRight(0.5);
        }else if(time.seconds() < 6){
            stopMoving();
        }else if(time.seconds() < 7.7){
            straight(0.5);
        }else if(time.seconds() < 8.5){
            stopMoving();
        }else if(time.seconds()<9.2){
            back(0.5);
        }else if(time.seconds( ) < 10){
            stopMoving();
        }else if(time.seconds() < 10.5){
            turnLeft(0.5);
        }else if (time.seconds() < 12){
            stopMoving();
        }else if(time.seconds() < 12.5){
            turnRight(0.5);
        }else if(time.seconds() < 13) {
            stopMoving();
        }else if(time.seconds() < 14){
            strafeRight(0.5);
        }else if(time.seconds()<14.3){
            stopMoving();
        }
        else if(time.seconds() < 16){
            stopMoving();
        }else if(time.seconds() < 16.8){
            straight(0.5);
        }else if(time.seconds() < 18.5){
            stopMoving();
        }else if(time.seconds()<20.2){
            back(0.5);
        }else if(time.seconds( ) < 22){
            stopMoving();
        }else if(time.seconds()<22.5){
            strafeLeft(0.5);
        }else if(time.seconds()<23){
            stopMoving();
        }else if(time.seconds() < 23.5){
            turnLeft(0.5);
        }else if (time.seconds() < 24){
            stopMoving();
        }else if(time.seconds() < 25.5){
            turnRight(0.5);
        }else if(time.seconds() < 26){
            stopMoving();
        }else if(time.seconds() < 28){
            straight(0.5);
        }
        else{
            strafeRight(0.5);
        }

    }
    public void strafeRight(double power){
        fl.setPower(-power);
        bl.setPower(power);
        fr.setPower(-power);
        br.setPower(power);
    }
    public void strafeLeft(double power){
        fl.setPower(power);
        bl.setPower(-power);
        fr.setPower(power);
        br.setPower(-power);
    }
    public void straight(double power){
        fl.setPower(power);
        fr.setPower(power);
        br.setPower(power);
        bl.setPower(power);
    }
    public void back(double power){
        fl.setPower(-power);
        fr.setPower(-power);
        bl.setPower(-power);
        br.setPower(-power);
    }
    public void stopMoving(){
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
    }
    public void turnLeft(double power){
        fl.setPower(-power);
        bl.setPower(-power);
        fr.setPower(power);
        br.setPower(-power);
    }
    public void turnRight(double power){
        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(-power);
        br.setPower(power);
    }
}