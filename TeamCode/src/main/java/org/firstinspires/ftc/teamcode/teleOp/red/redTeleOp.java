package org.firstinspires.ftc.teamcode.teleOp.red;

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
public class redTeleOp extends OpMode {
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
        follower.setStartingPose(/*startingPose == null ? */new Pose(107.300, 71.9000,0)/* : startingPose*/); //TODO
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();

        pathChain = () -> follower.pathBuilder() // lazy curve stuff
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(60, 84,130))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();

        pathChain2 = () -> follower.pathBuilder() // lazy curve stuff
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(60, 84,130))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();

        robot = new robot(hardwareMap);
        robot.init();

    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        follower.update();
        telemetryM.update();

        if (!automatedDrive) {
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y * slowModeMultiplier,
                    -gamepad1.left_stick_x * slowModeMultiplier,
                    -gamepad1.right_stick_x * slowModeMultiplier,
                    false // True: Robot Centric
            );

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
            if (gamepad2.dpadLeftWasPressed()) robot.outtake.fireShots(1);
            if (gamepad2.dpadDownWasPressed()) robot.outtake.fireShots(2);
            if (gamepad2.dpadRightWasPressed()) robot.outtake.fireShots(3);
        }

        //Automated PathFollowing
        if (gamepad1.bWasPressed()) {
            follower.followPath(pathChain.get());
            automatedDrive = true;
        }

        if (gamepad1.xWasPressed()) {
            follower.followPath(pathChain2.get());
            automatedDrive = true;
        }

        //Stop automated following if the follower is done OR manual override
        if (automatedDrive && (gamepad1.yWasPressed() || !follower.isBusy())) {
            follower.startTeleopDrive();
            automatedDrive = false;
        }

        telemetryM.debug("position", follower.getPose());
        telemetryM.debug("velocity", follower.getVelocity());
        telemetryM.debug("automatedDrive", automatedDrive);
    }
}