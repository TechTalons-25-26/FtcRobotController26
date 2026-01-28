package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class outtakeLogic {

    // ---------------- Hardware ----------------
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

    private double idleRPM = 500;     // flywheel RPM when idle
    private double targetRPM = 1100;  // flywheel target RPM
    private double minRPM = 800;      // minimum RPM to start firing

    private double maxSpinupTime = 2.0;    // max wait for flywheel spinup
    private double stageShootTime = 0.25;  // stage forward time
    private double stageResetTime = 0.25;  // stage reverse time

    // ---------------- PIDF ----------------
    private double P = 10.0;  // small initial P for testing
    private double F = 6.38;  // feedforward for 1100 RPM

    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        stageMotor = hardwareMap.get(DcMotorEx.class, "stage");

        outtakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        stageMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        // Remove these lines from init():
        // stageMotor.setPower(-1.0);
        // setVelocityRPM(idleRPM);

        // PIDF setup is fine (does not spin the motor)
        outtakeMotor.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, 0, 0, F));

        outtakeState = OuttakeState.IDLE;
    }

    public void start() {
        // Start idle behavior here, legal in competition
        stageMotor.setPower(0);  // idle reverse
        setVelocityRPM(idleRPM);
        outtakeState = OuttakeState.IDLE;
    }

    // ---------------- Main update loop ----------------
    public void update() {
        double currentRPM = (outtakeMotor.getVelocity() * 60.0) / TICKS_PER_REV;

        switch (outtakeState) {

            case IDLE:
                // Stage constantly in reverse during idle
                stageMotor.setPower(-0.5);
                setVelocityRPM(idleRPM);

                // Immediately transition if shots are requested
                if (shotsRemaining > 0) {
                    setVelocityRPM(targetRPM);
                    outtakeTimer.reset();
                    outtakeState = OuttakeState.SPIN_UP;
                }
                break;

            case SPIN_UP:
                // Keep stage reverse until we are ready to shoot
                stageMotor.setPower(-0.5);

                // Wait for flywheel to reach minRPM or max spinup time
                if (currentRPM >= minRPM || outtakeTimer.seconds() >= maxSpinupTime) {
                    stageMotor.setPower(0.5);  // stage forward
                    outtakeTimer.reset();
                    outtakeState = OuttakeState.LAUNCH;
                }
                break;

            case LAUNCH:
                // Flywheel keeps spinning
                setVelocityRPM(targetRPM);

                if (outtakeTimer.seconds() >= stageShootTime) {
                    shotsRemaining--;
                    stageMotor.setPower(-0.5); // reset stage
                    outtakeTimer.reset();
                    outtakeState = OuttakeState.RESET_STAGE;
                }
                break;

            case RESET_STAGE:
                setVelocityRPM(targetRPM); // keep flywheel spinning

                if (outtakeTimer.seconds() >= stageResetTime) {
                    if (shotsRemaining > 0) {
                        outtakeTimer.reset();
                        outtakeState = OuttakeState.SPIN_UP;
                    } else {
                        setVelocityRPM(idleRPM);
                        stageMotor.setPower(-0.5);
                        outtakeState = OuttakeState.IDLE;
                    }
                }
                break;
        }
    }

    // ---------------- Public API ----------------
    public void fireShots(int numShots) {
        if (numShots <= 0) return;

        shotsRemaining = numShots;

        // Immediately start SPIN_UP if currently idle
        if (outtakeState == OuttakeState.IDLE) {
            setVelocityRPM(targetRPM);
            outtakeTimer.reset();
            outtakeState = OuttakeState.SPIN_UP;
        }
    }

    public boolean isBusy() {
        return outtakeState != OuttakeState.IDLE;
    }

    public void setPIDF(double p, double f) {
        this.P = p;
        this.F = f;
        outtakeMotor.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, 0, 0, F));
    }

    // ---------------- Helpers ----------------
    private void setVelocityRPM(double rpm) {
        double ticksPerSecond = (rpm * TICKS_PER_REV) / 60.0;
        outtakeMotor.setVelocity(ticksPerSecond);
    }

    public double getCurrentRPM() {
        return (outtakeMotor.getVelocity() * 60.0) / TICKS_PER_REV;
    }

    public OuttakeState getState() {
        return outtakeState;
    }

    public int getShotsRemaining() {
        return shotsRemaining;
    }

    public double getTargetVelocity() {
        return (targetRPM * TICKS_PER_REV) / 60.0;
    }
}