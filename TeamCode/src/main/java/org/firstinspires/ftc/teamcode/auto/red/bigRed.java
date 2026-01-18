package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.red.bigRedEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.bigRedPaths;

@Autonomous(name = "bigRed")
@Configurable
public class bigRed extends baseAuto {

    public bigRedPaths paths = new bigRedPaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.INTAKE_START;
    }

    // ---------------- REQUIRED OVERRIDES ----------------

    @Override
    protected Pose getStartingPose() {
        return new Pose(123.200, 123.100, Math.toRadians(36));
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
                    intake.runIntake(false, 1);
                    setPathState(PathState.BIGREDSTART_REDSHOOT);
                }
                break;

            case BIGREDSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bigRedStart_redShoot, true);
                    setPathState(PathState.SHOOT_PRELOAD);
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
                    follower.followPath(paths.redShoot_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDTOPEND);
                }
                break;

            case REDTOPSTART_REDTOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redTopEnd, true);
                    setPathState(PathState.REDTOPEND_REDTOPSTART);
                }
                break;

            case REDTOPEND_REDTOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopEnd_redTopStart, true);
                    setPathState(PathState.REDTOPSTART_REDSHOOT);
                }
                break;

            case REDTOPSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redShoot, true);
                    setPathState(PathState.SHOOT_TOP);
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
                    follower.followPath(paths.redShoot_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDMIDDLEEND);
                }
                break;

            case REDMIDDLESTART_REDMIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redMiddleEnd, true);
                    setPathState(PathState.REDMIDDLEEND_REDMIDDLESTART);
                }
                break;

            case REDMIDDLEEND_REDMIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleEnd_redMiddleStart, true);
                    setPathState(PathState.REDMIDDLESTART_REDSHOOT);
                }
                break;

            case REDMIDDLESTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDBOTTOMEND);
                }
                break;

            case REDBOTTOMSTART_REDBOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redBottomEnd, true);
                    setPathState(PathState.REDBOTTOMEND_REDBOTTOMSTART);
                }
                break;

            case REDBOTTOMEND_REDBOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomEnd_redBottomStart, true);
                    setPathState(PathState.REDBOTTOMSTART_REDSHOOT);
                }
                break;

            case REDBOTTOMSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redEnd, true);
                }
                break;
        }
    }
}