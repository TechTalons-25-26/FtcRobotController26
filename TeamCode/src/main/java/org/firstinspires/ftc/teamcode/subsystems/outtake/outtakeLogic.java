package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.intake.intakeLogic;

public class outtakeLogic {
    private DcMotorEx outtakeMotor;
    private DcMotorEx stageMotor;
    private intakeLogic intake = new intakeLogic();
    private ElapsedTime outtakeTimer = new ElapsedTime();
    OuttakeState outtakeState;

    public enum OuttakeState {
        IDLE,
        LAUNCH,
        RESET
    }

    //TODO: TUNE THESE
    //----------- Outtake Constants--------
    private int shotsRemaining = 0;
    private double outtakeVelocity = 0;
    private double minOuttakeRPM = 800;
    private double targetOuttakeRPM = 1100;

    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        stageMotor = hardwareMap.get(DcMotorEx.class, "stage");


        setOuttakeState(OuttakeState.IDLE);

        outtakeMotor.setPower(0);
        stageMotor.setPower(0);
    }

    public void update() {
        switch (outtakeState) {
            case IDLE:
                outtakeMotor.setVelocity(outtakeVelocity);
                outtakeMotor.setPower(targetOuttakeRPM);

                if (shotsRemaining > 0) {
                    outtakeTimer.reset();
                    setOuttakeState(OuttakeState.LAUNCH);
                }
                break;

            case LAUNCH:
                outtakeTimer.reset();
                if (outtakeVelocity > minOuttakeRPM) {
                    stageMotor.setDirection(DcMotorEx.Direction.FORWARD);
                    outtakeTimer.reset();
                    intake.setIntakeState(intakeLogic.IntakeState.SHOOT);
                    setOuttakeState(OuttakeState.RESET);
                }
                break;

            case RESET:
                if (outtakeTimer.seconds() > intake.stagePowerTime) {
                    shotsRemaining--; //increment by 1
                    stageMotor.setDirection(DcMotorEx.Direction.REVERSE);
                    outtakeTimer.reset();

                    if (shotsRemaining > 0) {
                        outtakeTimer.reset();
                        setOuttakeState(OuttakeState.LAUNCH);
                    } else {
                        outtakeMotor.setPower(0);
                        setOuttakeState(OuttakeState.IDLE);
                    }
                }
                break;
        }
    }

    public void fireShots(int numShots) {
        if (outtakeState.equals(OuttakeState.IDLE)) {
            shotsRemaining = numShots;
        }
    }

    public boolean isBusy() {
        return outtakeState != OuttakeState.IDLE;
    }

    public void setOuttakeState(OuttakeState newState) {
        outtakeState = newState;
    }


}