package org.firstinspires.ftc.teamcode.teleOp.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.subsystems.path.poseStorage;

import java.util.function.Supplier;

@Configurable
@TeleOp
public class blueTeleOp extends OpMode {

    public static Pose startingPose = poseStorage.currentPose;
    private Follower follower;
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;

    private Supplier<PathChain> pathChain2;
    private TelemetryManager telemetryM;
    private double slowModeMultiplier = 0.5;
    private robot robot;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();

        pathChain = () -> follower.pathBuilder()
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(-12.2, -23.3))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(-50), 0.8))
                .build();

        pathChain2 = () -> follower.pathBuilder()
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(-56.9, 39.7, -67.6)))) //CHANGE
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();

        robot = new robot(hardwareMap);
        robot.init();
    }

    @Override
    public void start() {
        robot.start();
        follower.startTeleopDrive();
        follower.setStartingPose(new Pose(0, 0, 0));
    }

    @Override
    public void loop() {
        // ---------------- Manual Drive ----------------
        robot.update();
        follower.update();
        telemetryM.update();

        if (!automatedDrive) {
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y * slowModeMultiplier,
                    -gamepad1.left_stick_x * slowModeMultiplier,
                    -gamepad1.right_stick_x * slowModeMultiplier,
                    false // Robot-centric = true
            );

            // Intake control
            double intakePower = gamepad2.right_trigger;
            robot.intake.intakeMotor.setPower(intakePower);

            // ---------------- Outtake: Using bWasPressed() style ----------------
            if (gamepad2.dpadLeftWasPressed()) robot.outtake.fireShots(1);
            if (gamepad2.dpadDownWasPressed()) robot.outtake.fireShots(2);
            if (gamepad2.dpadRightWasPressed()) robot.outtake.fireShots(3);
        }

        // ---------------- Automated PathFollowing ----------------

        if (gamepad1.bWasPressed()) {
            follower.followPath(pathChain.get());
            automatedDrive = true;
        }

        if (gamepad1.xWasPressed()) {
            follower.followPath(pathChain2.get());
            automatedDrive = true;
        }

        // Stop automated following if done OR manual override
        if (automatedDrive && (gamepad1.yWasPressed() || !follower.isBusy())) {
            follower.startTeleopDrive();
            automatedDrive = false;
        }

        // ---------------- Telemetry ----------------
        telemetry.addData("Position", follower.getPose());
        telemetry.addData("Outtake State", robot.outtake.getState());
        telemetry.addData("Shots Remaining", robot.outtake.getShotsRemaining());
        telemetry.addData("Current RPM", "%.1f", robot.outtake.getCurrentRPM());
        telemetry.addData("Target Velocity (ticks/s)", "%.1f", robot.outtake.getTargetVelocity());
        telemetry.addLine("D-pad Left/Down/Right: fire 1/2/3 shots");
        telemetry.addLine("Right Trigger: Intake Power");
        telemetry.addLine("B: Start auto path | X: Stop auto path");
        telemetry.update();

        telemetryM.debug("position", follower.getPose());
        telemetryM.debug("velocity", follower.getVelocity());
        telemetryM.debug("automatedDrive", automatedDrive);
    }
}