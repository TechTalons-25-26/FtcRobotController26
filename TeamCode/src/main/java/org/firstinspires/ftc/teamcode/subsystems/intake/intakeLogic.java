package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.outtake.outtakeLogic;

public class intakeLogic {
    public DcMotorEx intakeMotor;
    private DcMotorEx stageMotor;

    public double intakePower = 0;
    public boolean intakeIsRunning = false;
    IntakeState intakeState;
    private outtakeLogic outtake = new outtakeLogic();

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        stageMotor = hardwareMap.get(DcMotorEx.class, "stage");

        intakeMotor.setPower(0);
        stageMotor.setPower(0);

    }

    public void update() {
        switch (intakeState) {
            case IDLE:
                intakeMotor.setPower(0);
                stageMotor.setPower(0);
                break;

            case FORWARD:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
                    stageMotor.setDirection(DcMotorEx.Direction.REVERSE);
                    intakeMotor.setPower(intakePower);
                    stageMotor.setPower(intakePower);
                }
                break;

            case REVERSE:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotorEx.Direction.REVERSE);
                    stageMotor.setDirection(DcMotorEx.Direction.REVERSE);
                    intakeMotor.setPower(intakePower);
                    stageMotor.setPower(intakePower);
                }

            case SHOOT:
                if (intakeIsRunning) {
                    stageMotor.setDirection(DcMotorEx.Direction.FORWARD);
                    intakeMotor.setPower(0);
                    stageMotor.setPower(intakePower);
                }
        }
    }

    public void runIntake(boolean reversed, double power) {
        if (!outtake.isBusy()) {
            if (!reversed) {
                intakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
                stageMotor.setDirection(DcMotorEx.Direction.REVERSE);

            }
            else {
                intakeMotor.setDirection(DcMotorEx.Direction.REVERSE);
                stageMotor.setDirection(DcMotorEx.Direction.FORWARD);

            }
            intakeMotor.setPower(power);
            stageMotor.setPower(power);
        }
    }

    public boolean isBusy() {
        return intakeState != IntakeState.IDLE;
    }

    public void setIntakeState(IntakeState newState) {
        intakeState = newState;
    }

    public enum IntakeState {
        IDLE,
        FORWARD,
        REVERSE,
        SHOOT
    }
}