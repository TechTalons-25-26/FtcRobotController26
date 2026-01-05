package org.firstinspires.ftc.teamcode.subsystems.old;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Outtake_Stuff #284 - 0.38", group = "test drive")
public class Outtake_Full extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Servo axon;

    double wheelSpeed = 0.38;
    double axonPosition = 0;  // start centered
    double step = 0.01;         // how much to move each press

    boolean lastA = false;
    boolean lastY = false;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        axon = hardwareMap.get(Servo.class, "axon");

        frontRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            // Drive using left stick
            double drive = -gamepad2.left_stick_y;
            frontLeft.setPower(drive * wheelSpeed);
            frontRight.setPower(drive * wheelSpeed);

            // Increment servo position on button press
            if (gamepad2.a && !lastA) {
                axonPosition += step;
            } else if (gamepad2.y && !lastY) {
                axonPosition -= step;
            }

            // Clamp servo position between 0 and 1
            axonPosition = Math.max(0, Math.min(1, axonPosition));

            // Update servo
            axon.setPosition(axonPosition);

            // Save button states for next loop
            lastA = gamepad2.a;
            lastY = gamepad2.y;

            telemetry.addData("Axon Position", axonPosition);
            telemetry.addData("Drive Power", drive);
            telemetry.update();
        }
    }
}
