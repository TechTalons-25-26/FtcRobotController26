package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class outtakeLogic {

    private DcMotorEx outtakeMotor;
    private DcMotorEx stageMotor;

    private ElapsedTime outtakeTimer = new ElapsedTime();
    private OuttakeState outtakeState = OuttakeState.IDLE;

    public enum OuttakeState {
        IDLE,
        SPIN_UP,
        LAUNCH,
        RESET_STAGE
    }

    // ---------------- Constants ----------------
    private static final double TICKS_PER_REV = 28.0;

    private int shotsRemaining = 0;

    private double idleRPM = 500;     // you will tune this
    private double targetRPM = 1100;
    private double minRPM = 800;

    private double maxSpinupTime = 2.0;
    private double stageShootTime = 0.25;
    private double stageResetTime = 0.25;

    // PIDF (tune later)
    private double P = 0.0;
    private double F = 6.4;

    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        stageMotor = hardwareMap.get(DcMotorEx.class, "stage");

        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        stageMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        stageMotor.setPower(0);
        setVelocityRPM(0);

        outtakeMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, 0, 0, F));
        setOuttakeState(OuttakeState.IDLE);
    }

    public void update() {

        double currentRPM =
                (outtakeMotor.getVelocity() * 60.0) / TICKS_PER_REV;

        switch (outtakeState) {

            case IDLE:
                stageMotor.setPower(-1.0);
                setVelocityRPM(idleRPM);

                if (shotsRemaining > 0) {
                    setVelocityRPM(targetRPM);
                    outtakeTimer.reset();
                    setOuttakeState(OuttakeState.SPIN_UP);
                }
                break;

            case SPIN_UP:
                if (currentRPM >= minRPM || outtakeTimer.seconds() >= maxSpinupTime) {
                    stageMotor.setPower(1.0);
                    outtakeTimer.reset();
                    setOuttakeState(OuttakeState.LAUNCH);
                }
                break;

            case LAUNCH:
                if (outtakeTimer.seconds() >= stageShootTime) {
                    shotsRemaining--;
                    stageMotor.setPower(-1.0);
                    outtakeTimer.reset();
                    setOuttakeState(OuttakeState.RESET_STAGE);
                }
                break;

            case RESET_STAGE:
                if (outtakeTimer.seconds() >= stageResetTime) {
                    if (shotsRemaining > 0) {
                        outtakeTimer.reset();
                        setOuttakeState(OuttakeState.SPIN_UP);
                    } else {
                        setVelocityRPM(idleRPM);
                        setOuttakeState(OuttakeState.IDLE);
                    }
                }
                break;
        }
    }

    // ---------------- API ----------------
    public void fireShots(int numShots) {
        if (outtakeState == OuttakeState.IDLE && numShots > 0) {
            shotsRemaining = numShots;
        }
    }

    public boolean isBusy() {
        return outtakeState != OuttakeState.IDLE;
    }

    // ---------------- Helpers ----------------
    private void setVelocityRPM(double rpm) {
        double ticksPerSecond = (rpm * TICKS_PER_REV) / 60.0;
        outtakeMotor.setVelocity(ticksPerSecond);
    }


    private void setOuttakeState(OuttakeState newState) {
        outtakeState = newState;
    }
}