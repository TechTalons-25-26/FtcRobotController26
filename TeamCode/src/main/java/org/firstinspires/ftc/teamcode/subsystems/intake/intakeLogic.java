package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class intakeLogic {
    public DcMotor intakeMotor;
    public double intakePower = 0;
    public boolean intakeIsRunning = false;
    public double stagePowerTime = 0;
    public boolean intakeIsReversed;
    IntakeState intakeState;
    private DcMotor stageMotor;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        stageMotor = hardwareMap.get(DcMotor.class, "stage");

        //setIntakeState(IntakeState.IDLE);

        intakeMotor.setPower(0);
        //stageMotor.setPower(0);

    }

    public void update() {
        switch (intakeState) {
            case IDLE:
                intakeMotor.setPower(0);
                stageMotor.setPower(0);
                break;

            case FORWARD:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotor.Direction.FORWARD);
                    stageMotor.setDirection(DcMotor.Direction.REVERSE);
                    intakeMotor.setPower(intakePower);
                    stageMotor.setPower(intakePower);
                }
                break;

            case REVERSE:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotor.Direction.REVERSE);
                    stageMotor.setDirection(DcMotor.Direction.REVERSE);
                    intakeMotor.setPower(intakePower);
                    stageMotor.setPower(intakePower);
                }

            case SHOOT:
                if (intakeIsRunning) {
                    stageMotor.setDirection(DcMotor.Direction.FORWARD);
                    intakeMotor.setPower(0);
                    stageMotor.setPower(intakePower);
                }
        }
    }

    public void runIntake(boolean reversed, double power) {
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setPower(power);
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