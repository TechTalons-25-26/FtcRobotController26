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

@Autonomous(name = "smallBlue with bigTri preload")
@Configurable
public class smallBlueAlt extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    PathState pathState;
    public enum PathState {
        SMALLBLUESTART_BLUESHOOT,
        BLUESHOOT_BLUETOPSTART,
        BLUETOPSTART_BLUETOPEND,
        BLUETOPEND_BLUETOPSTART,
        BLUETOPSTART_BLUESHOOT,
        BLUESHOOT_BLUEMIDDLESTART,
        BLUEMIDDLESTART_BLUEMIDDLEEND,
        BLUEMIDDLEEND_BLUEMIDDLESTART,
        BLUEMIDDLESTART_BLUESHOOT,
        BLUESHOOT_BLUEBOTTOMSTART,
        BLUEBOTTOM_BLUEBOTTOMEND,
        BLUEBOTTOMEND_BLUEBOTTOMSTART,
        BLUEBOTTOMSTART_BLUESHOOT,
        BLUESHOOT_BLUEEND
    }

    private PathChain smallBlueStart_blueShoot;
    private PathChain blueShoot_blueTopStart;
    private PathChain blueTopStart_blueTopEnd;
    private PathChain blueTopEnd_blueTopStart;
    private PathChain blueTopStart_blueShoot;
    private PathChain blueShoot_blueMiddleStart;
    private PathChain blueMiddleStart_blueMiddleEnd;
    private PathChain blueMiddleEnd_blueMiddleStart;
    private PathChain blueMiddleStart_blueShoot;
    private PathChain blueShoot_blueBottomStart;
    private PathChain blueBottom_blueBottomEnd;
    private PathChain blueBottomEnd_blueBottomStart;
    private PathChain blueBottomStart_blueShoot;
    private PathChain blueShoot_blueEnd;



    public void buildPaths() {
        smallBlueStart_blueShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(56.000, 8.000),
                                new Pose(55.800, 44.500),
                                new Pose(81.800, 58.000),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueTopStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(52.300, 93.200),
                                new Pose(51.500, 84.100),
                                new Pose(42.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        blueTopStart_blueTopEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(42.000, 84.000), new Pose(14.000, 84.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        blueTopEnd_blueTopStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.000, 84.000), new Pose(42.000, 84.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueTopStart_blueShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(42.000, 84.000),
                                new Pose(51.500, 84.100),
                                new Pose(52.300, 93.200),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueMiddleStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(52.300, 93.200),
                                new Pose(53.000, 60.400),
                                new Pose(42.000, 60.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        blueMiddleStart_blueMiddleEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(42.000, 60.000), new Pose(8.000, 60.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        blueMiddleEnd_blueMiddleStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(8.000, 60.000), new Pose(42.000, 60.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueMiddleStart_blueShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(42.000, 60.000),
                                new Pose(53.000, 60.400),
                                new Pose(52.300, 93.200),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueBottomStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(50.000, 96.200),
                                new Pose(54.300, 35.600),
                                new Pose(42.000, 36.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        blueBottom_blueBottomEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(42.000, 36.000), new Pose(8.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        blueBottomEnd_blueBottomStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(8.000, 36.000), new Pose(42.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueBottomStart_blueShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(42.000, 36.000),
                                new Pose(54.300, 35.600),
                                new Pose(50.000, 96.200),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(50.000, 96.200),
                                new Pose(46.000, 72.100),
                                new Pose(36.700, 71.900)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
    }

    public void statePathUpdate() {
        switch (pathState) {

            case SMALLBLUESTART_BLUESHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(smallBlueStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUETOPSTART);
                    break;
                }

            case BLUESHOOT_BLUETOPSTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueShoot_blueTopStart, true);
                    setPathState(PathState.BLUETOPSTART_BLUETOPEND);
                    break;
                }

            case BLUETOPSTART_BLUETOPEND:
                if(!follower.isBusy()) {
                    follower.followPath(blueTopStart_blueTopEnd, true);
                    setPathState(PathState.BLUETOPEND_BLUETOPSTART);
                    break;
                }

            case BLUETOPEND_BLUETOPSTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueTopEnd_blueTopStart, true);
                    setPathState(PathState.BLUETOPSTART_BLUESHOOT);
                    break;
                }

            case BLUETOPSTART_BLUESHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(blueTopStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUEMIDDLESTART);
                    break;
                }

            case BLUESHOOT_BLUEMIDDLESTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueShoot_blueMiddleStart, true);
                    setPathState(PathState.BLUEMIDDLESTART_BLUEMIDDLEEND);
                    break;
                }

            case BLUEMIDDLESTART_BLUEMIDDLEEND:
                if(!follower.isBusy()) {
                    follower.followPath(blueMiddleStart_blueMiddleEnd, true);
                    setPathState(PathState.BLUEMIDDLEEND_BLUEMIDDLESTART);
                    break;
                }

            case BLUEMIDDLEEND_BLUEMIDDLESTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueMiddleEnd_blueMiddleStart, true);
                    setPathState(PathState.BLUEMIDDLESTART_BLUESHOOT);
                    break;
                }

            case BLUEMIDDLESTART_BLUESHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(blueMiddleStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUEBOTTOMSTART);
                    break;
                }

            case BLUESHOOT_BLUEBOTTOMSTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueShoot_blueBottomStart, true);
                    setPathState(PathState.BLUEBOTTOM_BLUEBOTTOMEND);
                    break;
                }

            case BLUEBOTTOM_BLUEBOTTOMEND:
                if(!follower.isBusy()) {
                    follower.followPath(blueBottom_blueBottomEnd, true);
                    setPathState(PathState.BLUEBOTTOMEND_BLUEBOTTOMSTART);
                    break;
                }

            case BLUEBOTTOMEND_BLUEBOTTOMSTART:
                if(!follower.isBusy()) {
                    follower.followPath(blueBottomEnd_blueBottomStart, true);
                    setPathState(PathState.BLUEBOTTOMSTART_BLUESHOOT);
                    break;
                }

            case BLUEBOTTOMSTART_BLUESHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(blueBottomStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUEEND);
                    break;
                }

            case BLUESHOOT_BLUEEND:
                if(!follower.isBusy()) {
                    follower.followPath(blueShoot_blueEnd, true);
                    break;
                }
        }

    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }

    @Override
    public void init() {
        pathState = PathState.SMALLBLUESTART_BLUESHOOT;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        //Add any other init mechanisms
        buildPaths();
        follower.setStartingPose(new Pose(20.800, 123.100,Math.toRadians(144)));
    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);

    }
}