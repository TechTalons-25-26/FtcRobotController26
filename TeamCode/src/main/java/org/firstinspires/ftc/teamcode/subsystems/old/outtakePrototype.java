package org.firstinspires.ftc.teamcode.subsystems.old;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Yum", group = "Test Drive")
public class outtakePrototype extends LinearOpMode {

    private DcMotor leftOuttake;
    private DcMotor rightOuttake;

    double wheelSpeed = 1.0; // adjust if needed

    @Override
    public void runOpMode() {
        leftOuttake = hardwareMap.get(DcMotor.class, "frontLeft");
        rightOuttake = hardwareMap.get(DcMotor.class, "frontRight");

        // Reverse one motor if needed (depends on wiring)
        rightOuttake.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Ready to start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Read stick value directly
            double intakePower = -gamepad2.left_stick_y * wheelSpeed;

            // Optional deadzone
            if (Math.abs(intakePower) < 0.05) intakePower = 0;

            leftOuttake.setPower(intakePower);
            rightOuttake.setPower(intakePower);

            telemetry.addData("Intake Power", intakePower);
            telemetry.update();
        }
    }
}
