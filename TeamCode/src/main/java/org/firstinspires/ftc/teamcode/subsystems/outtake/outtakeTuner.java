package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "Auto PIDF Tuner")
public class outtakeTuner extends OpMode {

    private DcMotorEx outtake;

    private static final double TICKS_PER_REV = 28.0;

    private double targetRPM = 1100;
    private double currentTargetRPM = targetRPM;

    // PIDF values
    private double P = 0.0;
    private double F = 0.0;

    // Step sizes
    private final double[] steps = {10, 1, 0.1, 0.01, 0.001};
    private int stepIndex = 1;

    @Override
    public void init() {
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");

        outtake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // Automatic starting F estimate
        double targetTicksPerSec = targetRPM * TICKS_PER_REV / 60.0;
        F = 32767 / targetTicksPerSec;

        applyPIDF();
        setVelocityRPM(targetRPM);
    }

    @Override
    public void loop() {

        // -------- Adjust target RPM --------
        if (gamepad1.a) currentTargetRPM = 500;   // idle
        if (gamepad1.y) currentTargetRPM = targetRPM;

        setVelocityRPM(currentTargetRPM);

        // -------- Adjust PIDF --------
        if (gamepad1.right_bumper) stepIndex = (stepIndex + 1) % steps.length;
        if (gamepad1.left_bumper) stepIndex = (stepIndex - 1 + steps.length) % steps.length;

        if (gamepad1.dpad_up) P += steps[stepIndex];
        if (gamepad1.dpad_down) P -= steps[stepIndex];
        if (gamepad1.dpad_right) F += steps[stepIndex];
        if (gamepad1.dpad_left) F -= steps[stepIndex];

        applyPIDF();

        // -------- Telemetry --------
        double currentRPM = outtake.getVelocity() * 60.0 / TICKS_PER_REV;
        double error = currentTargetRPM - currentRPM;

        telemetry.addData("Target RPM", currentTargetRPM);
        telemetry.addData("Current RPM", "%.1f", currentRPM);
        telemetry.addData("Error", "%.1f", error);
        telemetry.addLine("------------------------");
        telemetry.addData("P", "%.5f", P);
        telemetry.addData("F", "%.5f", F);
        telemetry.addData("Step", steps[stepIndex]);
        telemetry.addLine("A = Idle | Y = Target");
        telemetry.addLine("Dpad = Adjust P/F | Bumpers = Step");
        telemetry.update();
    }

    // ---------------- Helpers ----------------
    private void setVelocityRPM(double rpm) {
        double ticksPerSecond = rpm * TICKS_PER_REV / 60.0;
        outtake.setVelocity(ticksPerSecond);
    }

    private void applyPIDF() {
        outtake.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, 0, 0, F));
    }
}