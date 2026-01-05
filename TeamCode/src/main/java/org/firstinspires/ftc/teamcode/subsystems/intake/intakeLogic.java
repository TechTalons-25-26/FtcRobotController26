package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class intakeLogic {
    IntakeState intakeState;
    public DcMotor intakeMotor;
    private ElapsedTime intakeTimer = new ElapsedTime();
    //TODO: TUNE THESE
    public double intakePower = 0;
    public boolean intakeIsRunning = false;
    public double intakeRunTime = 0;
    public boolean intakeIsReversed;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        setIntakeState(IntakeState.IDLE);

        intakeMotor.setPower(0);

    }

    public void update() {
        switch (intakeState) {
            case IDLE:
                if (intakeIsRunning) {
                    intakeTimer.reset();
                    if (!intakeIsReversed) {
                        setIntakeState(IntakeState.FORWARD);
                    } else if (intakeIsReversed) {
                        setIntakeState(IntakeState.REVERSE);
                    }
                }
                break;

            case FORWARD:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotor.Direction.FORWARD);
                    intakeMotor.setPower(intakePower);
                    if (intakeTimer.seconds() > intakeRunTime) {
                        intakeMotor.setPower(0);
                        intakeTimer.reset();
                        intakeIsRunning = false;
                        setIntakeState(IntakeState.IDLE);
                    }
                }
                break;

            case REVERSE:
                if (intakeIsRunning) {
                    intakeMotor.setDirection(DcMotor.Direction.REVERSE);
                    intakeMotor.setPower(intakePower);
                    if (intakeTimer.seconds() > intakeRunTime) {
                        intakeMotor.setPower(0);
                        intakeTimer.reset();
                        intakeIsRunning = false;
                        setIntakeState(IntakeState.IDLE);
                    }
                }
        }
    }

    public void runIntake(boolean reversed, double power, double seconds) {
        intakeIsRunning = true;
        intakeIsReversed = reversed;
        intakeRunTime = seconds;
        intakePower = power;
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
        REVERSE
    }
}