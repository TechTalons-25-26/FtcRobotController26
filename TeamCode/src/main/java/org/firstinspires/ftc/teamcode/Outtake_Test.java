package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake_Test", group = "test drive")
public class Outtake_Test extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;

    double wheelSpeed = 1.0;


    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad2.left_stick_y != 0){
                frontLeft.setPower(wheelSpeed);
                frontRight.setPower(wheelSpeed);
            }
            else {
                frontLeft.setPower(0);
            }

        }
    }

}

