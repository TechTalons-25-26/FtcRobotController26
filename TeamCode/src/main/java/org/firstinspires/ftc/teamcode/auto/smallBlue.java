package org.firstinspires.ftc.teamcode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.subsystems.pathState.pathStateEnums.smallBlueEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.pathState.paths.smallBluePaths;

@Autonomous(name = "smallBlue")
@Configurable
public class smallBlue extends baseAuto {

private smallBluePaths paths = new smallBluePaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.INTAKE_START;
    }

    // ---------------- REQUIRED OVERRIDES ----------------

    @Override
    protected Pose getStartingPose() {
        return new Pose(56.000, 8.000, Math.toRadians(90));
    }

    @Override
    protected void buildPaths() {
        paths.buildPaths(follower);
    }

    @Override
    protected void pathStateUpdate() {
        switch ((PathState) pathState) {
            case INTAKE_START:
                if (!follower.isBusy()) {
                    intake.runIntake(false, 1, Double.POSITIVE_INFINITY);
                    setPathState(PathState.SMALLBLUESTART_SMALLBLUEPRELOAD);
                }
            case SMALLBLUESTART_SMALLBLUEPRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBlueStart_smallBluePreload, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.SMALLBLUEPRELOAD_BLUEBOTTOMSTART);
                    }
                }
                break;

            case SMALLBLUEPRELOAD_BLUEBOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBluePreload_blueBottomStart, true);
                    setPathState(PathState.BLUEBOTTOMSTART_BLUEBOTTOMEND);
                }
                break;

            case BLUEBOTTOMSTART_BLUEBOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueBottomEnd, true);
                    setPathState(PathState.BLUEBOTTOMEND_BLUEBOTTOMSTART);
                }
                break;

            case BLUEBOTTOMEND_BLUEBOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomEnd_blueBottomStart, true);
                    setPathState(PathState.BLUEBOTTOMSTART_BLUESHOOT);
                }
                break;

            case BLUEBOTTOMSTART_BLUESHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUEMIDDLESTART);
                }
                break;

            case SHOOT_BOTTOM:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.BLUESHOOT_BLUEMIDDLESTART);
                    }
                }
                break;

            case BLUESHOOT_BLUEMIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueMiddleStart, true);
                    setPathState(PathState.BLUEMIDDLESTART_BLUEMIDDLEEND);
                }
                break;

            case BLUEMIDDLESTART_BLUEMIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueMiddleEnd, true);
                    setPathState(PathState.BLUEMIDDLEEND_BLUEMIDDLESTART);
                }
                break;

            case BLUEMIDDLEEND_BLUEMIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleEnd_blueMiddleStart, true);
                    setPathState(PathState.BLUEMIDDLESTART_BLUESHOOT);
                }
                break;

            case BLUEMIDDLESTART_BLUESHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUETOPSTART);
                }
                break;

            case SHOOT_MIDDLE:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.BLUESHOOT_BLUETOPSTART);
                    }
                }
                break;

            case BLUESHOOT_BLUETOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueTopStart, true);
                    setPathState(PathState.BLUETOPSTART_BLUETOPEND);
                }
                break;

            case BLUETOPSTART_BLUETOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueTopEnd, true);
                    setPathState(PathState.BLUETOPEND_BLUETOPSTART);
                }
                break;

            case BLUETOPEND_BLUETOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopEnd_blueTopStart, true);
                    setPathState(PathState.BLUETOPSTART_BLUESHOOT);
                }
                break;

            case BLUETOPSTART_BLUESHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueShoot, true);
                    setPathState(PathState.BLUESHOOT_BLUEEND);
                }
                break;

            case SHOOT_TOP:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.BLUESHOOT_BLUEEND);
                    }
                }
                break;

            case BLUESHOOT_BLUEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueEnd, true);
                }
                break;
        }
    }
}