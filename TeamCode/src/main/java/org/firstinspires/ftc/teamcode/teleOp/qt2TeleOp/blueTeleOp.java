package org.firstinspires.ftc.teamcode.teleOp.qt2TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot;

import com.pedropathing.geometry.Pose;

@TeleOp(name = "blueTeleOp")
public class blueTeleOp extends OpMode {

    private robot robot;

    @Override
    public void init() {
        robot = new robot(hardwareMap);
        robot.init();

        // BLUE TELEOP TARGET POSE - change to accurate position after tuning
        robot.path.setTargetPose(
                new Pose(60, 84, Math.toRadians(130))
        );

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {

        robot.update();

        // Start path on B
        robot.path.handleStartPath(gamepad1.bWasPressed());

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

        double intakeForwardPower = gamepad2.right_trigger;
        double intakeReversePower = gamepad2.left_trigger;

        // Intake buttons
        if (intakeForwardPower > 0.05 && (intakeForwardPower > intakeReversePower)) {
            robot.intake.runIntake(false, intakeForwardPower);
        }
        if (intakeReversePower > 0.05 && !(intakeForwardPower >= intakeReversePower)) {
            robot.intake.runIntake(true, intakeReversePower);
        }

        // Outtake buttons
        if (gamepad2.xWasPressed()) robot.outtake.fireShots(1);
        if (gamepad2.aWasPressed()) robot.outtake.fireShots(2);
        if (gamepad2.bWasPressed()) robot.outtake.fireShots(3);

        telemetry.update();
    }
}