package org.firstinspires.ftc.teamcode.auto.qt2Auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.subsystems.pathState.pathStateEnums.smallRedAltEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.auto.baseAuto;

@Autonomous(name = "smallRedAlt")
@Configurable
public class smallRedAlt extends baseAuto {

    private PathChain smallRedStart_redShoot;
    private PathChain redShoot_redTopStart;
    private PathChain redTopStart_redTopEnd;
    private PathChain redTopEnd_redTopStart;
    private PathChain redTopStart_redShoot;
    private PathChain redShoot_redMiddleStart;
    private PathChain redMiddleStart_redMiddleEnd;
    private PathChain redMiddleEnd_redMiddleStart;
    private PathChain redMiddleStart_redShoot;
    private PathChain redShoot_redBottomStart;
    private PathChain redBottomStart_redBottomEnd;
    private PathChain redBottomEnd_redBottomStart;
    private PathChain redBottomStart_redShoot;
    private PathChain redShoot_redEnd;

    @Override
    protected Enum<?> getInitialState() {
        return PathState.INTAKE_START;
    }

    // ---------------- REQUIRED OVERRIDES ----------------

    @Override
    protected Pose getStartingPose() {
        return new Pose(88.000, 8.000, Math.toRadians(90));
    }

    @Override
    protected void buildPaths() {

        smallRedStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(88.000, 8.000),
                        new Pose(88.200, 44.500),
                        new Pose(62.200, 58.000),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redTopStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(91.700, 93.200),
                        new Pose(92.500, 84.100),
                        new Pose(102.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redTopStart_redTopEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 84.000),
                        new Pose(130.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redTopEnd_redTopStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(130.000, 84.000),
                        new Pose(102.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redTopStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 84.000),
                        new Pose(92.500, 84.100),
                        new Pose(91.700, 93.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redMiddleStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(91.700, 93.200),
                        new Pose(91.000, 60.400),
                        new Pose(102.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redMiddleStart_redMiddleEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 60.000),
                        new Pose(136.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redMiddleEnd_redMiddleStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(136.000, 60.000),
                        new Pose(102.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redMiddleStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 60.000),
                        new Pose(91.000, 60.400),
                        new Pose(91.700, 93.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redBottomStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(94.000, 96.200),
                        new Pose(89.700, 35.600),
                        new Pose(102.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redBottomStart_redBottomEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 36.000),
                        new Pose(136.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redBottomEnd_redBottomStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(136.000, 36.000),
                        new Pose(102.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redBottomStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 36.000),
                        new Pose(89.700, 35.600),
                        new Pose(94.000, 96.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redEnd = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(94.000, 96.200),
                        new Pose(98.000, 72.100),
                        new Pose(107.300, 71.900)
                ))
                .setTangentHeadingInterpolation()
                .build();
    }

    @Override
    protected void pathStateUpdate() {
        switch ((PathState) pathState) {
            case INTAKE_START:
                if (!follower.isBusy()) {
                    intake.runIntake(false, 1, Double.POSITIVE_INFINITY);
                    setPathState(PathState.SMALLREDSTART_REDSHOOT);
                }
            case SMALLREDSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(smallRedStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDTOPSTART);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDSHOOT_REDTOPSTART);
                    }
                }
                break;

            case REDSHOOT_REDTOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(redShoot_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDTOPEND);
                }
                break;

            case REDTOPSTART_REDTOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(redTopStart_redTopEnd, true);
                    setPathState(PathState.REDTOPEND_REDTOPSTART);
                }
                break;

            case REDTOPEND_REDTOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(redTopEnd_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDSHOOT);
                }
                break;

            case REDTOPSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(redTopStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDMIDDLESTART);
                }
                break;

            case SHOOT_TOP:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDSHOOT_REDMIDDLESTART);
                    }
                }
                break;

            case REDSHOOT_REDMIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(redShoot_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDMIDDLEEND);
                }
                break;

            case REDMIDDLESTART_REDMIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(redMiddleStart_redMiddleEnd, true);
                    setPathState(PathState.REDMIDDLEEND_REDMIDDLESTART);
                }
                break;

            case REDMIDDLEEND_REDMIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(redMiddleEnd_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDSHOOT);
                }
                break;

            case REDMIDDLESTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(redMiddleStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDBOTTOMSTART);
                }
                break;

            case SHOOT_MIDDLE:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDSHOOT_REDBOTTOMSTART);
                    }
                }
                break;

            case REDSHOOT_REDBOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(redShoot_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDBOTTOMEND);
                }
                break;

            case REDBOTTOMSTART_REDBOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(redBottomStart_redBottomEnd, true);
                    setPathState(PathState.REDBOTTOMEND_REDBOTTOMSTART);
                }
                break;

            case REDBOTTOMEND_REDBOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(redBottomEnd_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDSHOOT);
                }
                break;

            case REDBOTTOMSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(redBottomStart_redShoot, true);
                    setPathState(PathState.REDSHOOT_REDEND);
                }
                break;

            case SHOOT_BOTTOM:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDSHOOT_REDEND);
                    }
                }
                break;

            case REDSHOOT_REDEND:
                if (!follower.isBusy()) {
                    follower.followPath(redShoot_redEnd, true);
                }
                break;
        }
    }
}