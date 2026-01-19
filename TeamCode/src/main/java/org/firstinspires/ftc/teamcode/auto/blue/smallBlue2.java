package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;

import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.bigAndSmall2Enum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.smallBlue2Paths;

@Autonomous(name = "smallBlue2")
@Configurable
public class smallBlue2 extends baseAuto {
    private smallBlue2Paths paths = new smallBlue2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return bigAndSmall2Enum.INTAKE_START;
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
        switch ((bigAndSmall2Enum) pathState) {
            case INTAKE_START:
                if (!follower.isBusy()) {
                    intake.setIntake(false, 1, Double.POSITIVE_INFINITY);
                    setPathState(bigAndSmall2Enum.START_SHOOT);
                }
            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBlueStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(bigAndSmall2Enum.SHOOT_TOPSTART);
                    }
                }
                break;

            case SHOOT_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueTopStart, true);
                    setPathState(bigAndSmall2Enum.TOPSTART_TOPEND);
                }
                break;

            case TOPSTART_TOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueTopEnd, true);
                    setPathState(bigAndSmall2Enum.TOPEND_TOPSTART);
                }
                break;

            case TOPEND_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopEnd_blueTopStart, true);
                    setPathState(bigAndSmall2Enum.TOPSTART_SHOOT);
                }
                break;

            case TOPSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.SHOOT_MIDDLESTART);
                }
                break;

            case OUTTAKE_TOP:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(bigAndSmall2Enum.SHOOT_MIDDLESTART);
                    }
                }
                break;

            case SHOOT_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueMiddleStart, true);
                    setPathState(bigAndSmall2Enum.MIDDLESTART_MIDDLEEND);
                }
                break;

            case MIDDLESTART_MIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueMiddleEnd, true);
                    setPathState(bigAndSmall2Enum.MIDDLEEND_MIDDLESTART);
                }
                break;

            case MIDDLEEND_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleEnd_blueMiddleStart, true);
                    setPathState(bigAndSmall2Enum.MIDDLESTART_SHOOT);
                }
                break;

            case MIDDLESTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.SHOOT_BOTTOMSTART);
                }
                break;

            case OUTTAKE_MIDDLE:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(bigAndSmall2Enum.SHOOT_BOTTOMSTART);
                    }
                }
                break;

            case SHOOT_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueBottomStart, true);
                    setPathState(bigAndSmall2Enum.BOTTOMSTART_BOTTOMEND);
                }
                break;

            case BOTTOMSTART_BOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottom_blueBottomEnd, true);
                    setPathState(bigAndSmall2Enum.BOTTOMEND_BOTTOMSTART);
                }
                break;

            case BOTTOMEND_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomEnd_blueBottomStart, true);
                    setPathState(bigAndSmall2Enum.BOTTOMSTART_SHOOT);
                }
                break;

            case BOTTOMSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.SHOOT_END);
                }
                break;

            case OUTTAKE_BOTTOM:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(bigAndSmall2Enum.SHOOT_END);
                    }
                }
                break;

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueEnd, true);
                }
                break;
        }
    }
}