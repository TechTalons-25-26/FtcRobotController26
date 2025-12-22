package org.firstinspires.ftc.teamcode.auto.qt2Auto;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous (name = "bigBlue", group = "Autonomous")
@Configurable
public class bigBlue extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class
    private Timer pathTimer, actionTimer, opmodeTimer;

    @Override
    public void init() {

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(56, 136, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {

        public PathChain Path1;
        public PathChain Path2;
        public PathChain Path3;
        public PathChain Path4;
        public PathChain Path5;
        public PathChain Path6;
        public PathChain Path7;
        public PathChain Path8;
        public PathChain Path9;
        public PathChain Path10;
        public PathChain Path11;
        public PathChain Path12;
        public PathChain Path13;
        public PathChain Path14;

        public Paths(Follower follower) {
            Path1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(56.000, 136.000),
                                    new Pose(56.000, 115.800),
                                    new Pose(56.500, 85.600),
                                    new Pose(48.000, 96.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(48.000, 96.000),
                                    new Pose(53.200, 89.800),
                                    new Pose(48.200, 84.100),
                                    new Pose(42.000, 84.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(42.000, 84.000), new Pose(14.000, 84.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(14.000, 84.000), new Pose(42.000, 84.000))
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(42.000, 84.000),
                                    new Pose(48.200, 84.100),
                                    new Pose(53.200, 89.800),
                                    new Pose(48.000, 96.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path6 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(48.000, 96.000),
                                    new Pose(55.600, 87.200),
                                    new Pose(58.500, 60.400),
                                    new Pose(42.000, 60.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path7 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(42.000, 60.000), new Pose(8.000, 60.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path8 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(8.000, 60.000), new Pose(42.000, 60.000))
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path9 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(42.000, 60.000),
                                    new Pose(58.500, 60.400),
                                    new Pose(55.600, 87.200),
                                    new Pose(48.000, 96.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path10 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(48.000, 96.000),
                                    new Pose(61.900, 79.800),
                                    new Pose(67.300, 36.300),
                                    new Pose(42.000, 36.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path11 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(42.000, 36.000), new Pose(8.000, 36.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path12 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(8.000, 36.000), new Pose(42.000, 36.000))
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path13 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(42.000, 36.000),
                                    new Pose(67.300, 36.300),
                                    new Pose(61.900, 79.800),
                                    new Pose(48.000, 96.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path14 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(48.000, 96.000),
                                    new Pose(61.500, 80.300),
                                    new Pose(53.700, 72.100),
                                    new Pose(48.000, 72.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(paths.Path1);
                setPathState(1);
                telemetry.addLine("Path 1 Completed");
                telemetry.update();
                break;

            case 1:
                follower.followPath(paths.Path2);
                setPathState(2);
                telemetry.addLine("Path 2 Completed");
                telemetry.update();
                break;

            case 2:
                follower.followPath(paths.Path3);
                setPathState(3);
                telemetry.addLine("Path 3 Completed");
                telemetry.update();
                break;

            case 3:
                follower.followPath(paths.Path4);
                setPathState(4);
                telemetry.addLine("Path 4 Completed");
                telemetry.update();
                break;

            case 4:
                follower.followPath(paths.Path5);
                setPathState(5);
                telemetry.addLine("Path 5 Completed");
                telemetry.update();
                break;

            case 5:
                follower.followPath(paths.Path6);
                setPathState(6);
                telemetry.addLine("Path 6 Completed");
                telemetry.update();
                break;

            case 6:
                follower.followPath(paths.Path7);
                setPathState(7);
                telemetry.addLine("Path 7 Completed");
                telemetry.update();
                break;

            case 7:
                follower.followPath(paths.Path8);
                setPathState(8);
                telemetry.addLine("Path 8 Completed");
                telemetry.update();
                break;

            case 8:
                follower.followPath(paths.Path9);
                setPathState(9);
                telemetry.addLine("Path 9 Completed");
                telemetry.update();
                break;

            case 9:
                follower.followPath(paths.Path10);
                setPathState(10);
                telemetry.addLine("Path 10 Completed");
                telemetry.update();
                break;

            case 10:
                follower.followPath(paths.Path11);
                setPathState(11);
                telemetry.addLine("Path 11 Completed");
                telemetry.update();
                break;

            case 11:
                follower.followPath(paths.Path12);
                setPathState(12);
                telemetry.addLine("Path 12 Completed");
                telemetry.update();
                break;

            case 12:
                follower.followPath(paths.Path13);
                setPathState(13);
                telemetry.addLine("Path 13 Completed");
                telemetry.update(); 
                break;

            case 13:
                follower.followPath(paths.Path14);
                setPathState(14);
                telemetry.addLine("Path 14 Completed");
                telemetry.update();
                break;
        }
        return pathState;
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}
