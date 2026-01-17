package org.firstinspires.ftc.teamcode.teleOp.qt2TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot;
import com.pedropathing.geometry.Pose;

@TeleOp(name = "redTeleOp")
public class redTeleOp extends OpMode {

    private robot robot;

    @Override
    public void init() {
        robot = new robot(hardwareMap);
        robot.init();

        // RED TELEOP TARGET POSE - change to accurate position after tuning
        robot.path.setTargetPose(
                new Pose(83.959, 86.004, Math.toRadians(-2.226))
        );

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {

        robot.update();

        // Start path on B
        robot.path.handleStartPath(gamepad1.b);

        // Drive only when not pathing
        if (!robot.path.isBusy()) {
            robot.drive.drive(
                    -gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x
            );
        } else {
            robot.drive.stop();
        }

        // Intake buttons
        if (gamepad2.x) robot.intake.runIntake(false, 1.0, 1.0);
        if (gamepad2.b) robot.intake.runIntake(true, 1.0, 0.5);

        // Outtake buttons
        if (gamepad2.y) robot.outtake.fireShots(1);
        if (gamepad2.a) robot.outtake.fireShots(3);

        telemetry.update();
    }
}