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
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtakeLogic;

@Autonomous(name = "smallRede")
@Configurable
public class smallRed extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    // ----------- OUTTAKE LOGIC ----------
    private outtakeLogic outtake = new outtakeLogic();
    private boolean shotsTriggered = false;

    PathState pathState;
    public enum PathState {
        SMALLREDSTART_SMALLREDPRELOAD,
        SMALLREDPRELOAD_REDBOTTOMSTART,
        REDBOTTOMSTART_REDBOTTOMEND,
        REDBOTTOMEND_REDBOTTOMSTART,
        REDBOTTOMSTART_REDSHOOT,
        REDSHOOT_REDMIDDLESTART,
        REDMIDDLESTART_REDMIDDLEEND,
        REDMIDDLEEND_REDMIDDLESTART,
        REDMIDDLESTART_REDSHOOT,
        REDSHOOT_REDTOPSTART,
        REDTOPSTART_REDTOPEND,
        REDTOPEND_REDTOPSTART,
        REDTOPSTART_REDSHOOT,
        REDSHOOT_REDEND
    }


    private PathChain smallRedStart_smallRedPreload;
    private PathChain smallRedPreload_redBottomStart;
    private PathChain redBottomStart_redBottomEnd;
    private PathChain redBottomEnd_redBottomStart;
    private PathChain redBottomStart_redShoot;
    private PathChain redShoot_redMiddleStart;
    private PathChain redMiddleStart_redMiddleEnd;
    private PathChain redMiddleEnd_redMiddleStart;
    private PathChain redMiddleStart_redShoot;
    private PathChain redShoot_redTopStart;
    private PathChain redTopStart_redTopEnd;
    private PathChain redTopEnd_redTopStart;
    private PathChain redTopStart_redShoot;
    private PathChain redShoot_redEnd;



    public void buildPaths() {
        smallRedStart_smallRedPreload = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(88.000, 8.000), new Pose(88.000, 16.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(70))
                .build();

        smallRedPreload_redBottomStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(88.000, 16.000),
                                new Pose(96.000, 38.000),
                                new Pose(94.900, 36.000),
                                new Pose(102.000, 36.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        redBottomStart_redBottomEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.000, 36.000), new Pose(136.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        redBottomEnd_redBottomStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(136.000, 36.000), new Pose(102.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redBottomStart_redShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(102.000, 36.000),
                                new Pose(89.700, 36.550),
                                new Pose(94.000, 96.200),
                                new Pose(84.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redMiddleStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(84.000, 84.000),
                                new Pose(92.200, 93.100),
                                new Pose(88.500, 60.400),
                                new Pose(102.000, 60.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        redMiddleStart_redMiddleEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.000, 60.000), new Pose(136.000, 60.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        redMiddleEnd_redMiddleStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(136.000, 60.000), new Pose(102.000, 60.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redMiddleStart_redShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(102.000, 60.000),
                                new Pose(88.500, 60.400),
                                new Pose(92.200, 93.100),
                                new Pose(84.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redTopStart = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(84.000, 84.000),
                                new Pose(91.700, 93.200),
                                new Pose(92.500, 84.000),
                                new Pose(102.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        redTopStart_redTopEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.000, 84.000), new Pose(130.000, 84.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        redTopEnd_redTopStart = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(130.000, 84.000), new Pose(102.000, 84.000))
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redTopStart_redShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(102.000, 84.000),
                                new Pose(92.500, 84.000),
                                new Pose(91.700, 93.300),
                                new Pose(84.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redEnd = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(84.000, 84.000),
                                new Pose(94.000, 96.200),
                                new Pose(98.000, 72.100),
                                new Pose(107.300, 71.900)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
    }

    public void pathStateUpdate() {
        switch (pathState) {

            case SMALLREDSTART_SMALLREDPRELOAD:
                if(!follower.isBusy()) {
                    follower.followPath(smallRedStart_smallRedPreload, true);
                    setPathState(PathState.SMALLREDPRELOAD_REDBOTTOMSTART);
                    break;
                }

            case SMALLREDPRELOAD_REDBOTTOMSTART:
                if(!follower.isBusy()) {
                    follower.followPath(smallRedPreload_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDBOTTOMEND);
                    break;
                }

            case REDBOTTOMSTART_REDBOTTOMEND:
                if(!follower.isBusy()) {
                    follower.followPath(redBottomStart_redBottomEnd, true);
                    setPathState(PathState.REDBOTTOMEND_REDBOTTOMSTART);
                    break;
                }

            case REDBOTTOMEND_REDBOTTOMSTART:
                if(!follower.isBusy()) {
                    follower.followPath(redBottomEnd_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDSHOOT);
                    break;
                }

            case REDBOTTOMSTART_REDSHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(redBottomStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDMIDDLESTART);
                    break;
                }

            case REDSHOOT_REDMIDDLESTART:
                if(!follower.isBusy()) {
                    follower.followPath(redShoot_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDMIDDLEEND);
                    break;
                }

            case REDMIDDLESTART_REDMIDDLEEND:
                if(!follower.isBusy()) {
                    follower.followPath(redMiddleStart_redMiddleEnd, true);
                    setPathState(PathState.REDMIDDLEEND_REDMIDDLESTART);
                    break;
                }

            case REDMIDDLEEND_REDMIDDLESTART:
                if(!follower.isBusy()) {
                    follower.followPath(redMiddleEnd_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDSHOOT);
                    break;
                }

            case REDMIDDLESTART_REDSHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(redMiddleStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDTOPSTART);
                    break;
                }

            case REDSHOOT_REDTOPSTART:
                if(!follower.isBusy()) {
                    follower.followPath(redShoot_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDTOPEND);
                    break;
                }

            case REDTOPSTART_REDTOPEND:
                if(!follower.isBusy()) {
                    follower.followPath(redTopStart_redTopEnd, true);
                    setPathState(PathState.REDTOPEND_REDTOPSTART);
                    break;
                }

            case REDTOPEND_REDTOPSTART:
                if(!follower.isBusy()) {
                    follower.followPath(redTopEnd_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDSHOOT);
                    break;
                }

            case REDTOPSTART_REDSHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(redTopStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDEND);
                    break;
                }

            case REDSHOOT_REDEND:
                if(!follower.isBusy()) {
                    follower.followPath(redShoot_redEnd, true);
                    break;
                }
        }

    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();

        shotsTriggered = false;

    }

    @Override
    public void init() {
        pathState = PathState.SMALLREDSTART_SMALLREDPRELOAD;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        outtake.init(hardwareMap);
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
        pathStateUpdate();
        outtake.update();

        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);

    }
}