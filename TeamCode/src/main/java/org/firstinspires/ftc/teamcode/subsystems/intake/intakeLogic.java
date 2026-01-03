package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class intakeLogic {
    private DcMotor intakeMotor;
    private ElapsedTime intakeTimer = new ElapsedTime();
    IntakeState intakeState;
    public enum IntakeState {
        IDLE,
        RUN
    }

    //TODO: TUNE THESE
    private double intakePower = 0; // could be velocity idk
    private boolean intakeIsRunning = false;
    private double intakeRunTime = 0;

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
                    setIntakeState(IntakeState.RUN);
                }
                break;

            case RUN:
                intakeMotor.setPower(intakePower);
                if (intakeTimer.seconds() > intakeRunTime) {
                    intakeMotor.setPower(0);
                    intakeTimer.reset();
                    intakeIsRunning = false;
                    setIntakeState(IntakeState.IDLE);
                }
                break;
        }
    }

    public void runIntake(double seconds) {
        intakeIsRunning = true;
        intakeRunTime = seconds;
    }

    public boolean isBusy() {
        return intakeState != IntakeState.IDLE;
    }

    public void setIntakeState(IntakeState newState) {
        intakeState = newState;
    }


}