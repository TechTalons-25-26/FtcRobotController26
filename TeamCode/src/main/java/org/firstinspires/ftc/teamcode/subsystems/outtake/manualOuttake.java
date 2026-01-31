package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class manualOuttake {

    private DcMotorEx outtakeMotor;
    private DcMotorEx intakeMotor;
    private DcMotorEx stageMotor;

    private ElapsedTime timer = new ElapsedTime();


    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        stageMotor = hardwareMap.get(DcMotorEx.class, "stage");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");

        outtakeMotor.setDirection(DcMotorEx.Direction.REVERSE);
        outtakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        stageMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    // BLOCKING — for Auto only
    public void run() {
        timer.reset();
        outtakeMotor.setPower(1);

        while (timer.seconds() < 4) {
            while (timer.seconds() < 2) {
                intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
                stageMotor.setDirection(DcMotorEx.Direction.REVERSE);
                intakeMotor.setPower(1);
                stageMotor.setPower(1);
            }
            intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
            stageMotor.setDirection(DcMotorEx.Direction.FORWARD);
            intakeMotor.setPower(1);
            stageMotor.setPower(1);

        }

        outtakeMotor.setPower(0);
        intakeMotor.setPower(0);
        stageMotor.setPower(0);
    }

}
