package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.smallRedPaths;

@Autonomous(name = "smallRed")
@Configurable
public class smallRed extends baseAuto {

    private smallRedPaths paths = new smallRedPaths();
    
    @Override
    protected Enum<?> getInitialState() {
        return smallEnum.START_PRELOAD;
    }

    // ---------------- REQUI OVERRIDES ----------------

    @Override
    protected Pose getStartingPose() {
        return new Pose(88.000, 8.000, Math.toRadians(90));
    }

    @Override
    protected void buildPaths() {
        paths.buildPaths(follower);
    }

    @Override
    protected void pathStateUpdate() {
        switch ((smallEnum) pathState) {

            case START_PRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedStart_smallRedPreload, true);
                    setPathState(smallEnum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!follower.isBusy()) {
                    manualOuttake.run();
                    while (manualOuttake.outtakeRunning) {}
                    setPathState(smallEnum.PRELOAD_BOTTOMSTART);
                    break;
                }

            case PRELOAD_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedPreload_redBottomStart, true);
                    setPathState(smallEnum.BOTTOMSTART_BOTTOMEND);
                }
                break;

            case BOTTOMSTART_BOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redBottomEnd, true);
                    setPathState(smallEnum.INTAKESTART_BOTTOM);
                }
                break;

            case INTAKESTART_BOTTOM:
                intake.runIntake(false, 1);
                setPathState(smallEnum.BOTTOMEND_BOTTOMSTART);
                break;

            case BOTTOMEND_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomEnd_redBottomStart, true);
                    setPathState(smallEnum.INTAKEEND_BOTTOM);
                }
                break;

            case INTAKEEND_BOTTOM:
                intake.runIntake(false, 0);
                setPathState(smallEnum.BOTTOMSTART_SHOOT);
                break;

            case BOTTOMSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redShoot, true);
                    setPathState(smallEnum.OUTTAKE_BOTTOM);
                }
                break;

            case OUTTAKE_BOTTOM:
                if (!follower.isBusy()) {
                    manualOuttake.run();
                    while (manualOuttake.outtakeRunning) {}
                    setPathState(smallEnum.SHOOT_MIDDLESTART);
                    break;
                }

            case SHOOT_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redShoot_redMiddleStart, true);
                    setPathState(smallEnum.MIDDLESTART_MIDDLEEND);
                }
                break;

            case MIDDLESTART_MIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redMiddleEnd, true);
                    setPathState(smallEnum.INTAKESTART_MIDDLE);
                }
                break;

            case INTAKESTART_MIDDLE:
                intake.runIntake(false, 1);
                setPathState(smallEnum.MIDDLEEND_MIDDLESTART);
                break;

            case MIDDLEEND_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleEnd_redMiddleStart, true);
                    setPathState(smallEnum.INTAKEEND_MIDDLE);
                }
                break;

            case INTAKEEND_MIDDLE:
                intake.runIntake(false, 0);
                setPathState(smallEnum.MIDDLESTART_SHOOT);
                break;

            case MIDDLESTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redShoot, true);
                    setPathState(smallEnum.OUTTAKE_MIDDLE);
                }
                break;

            case OUTTAKE_MIDDLE:
                if (!follower.isBusy()) {
                    manualOuttake.run();
                    while (manualOuttake.outtakeRunning) {}
                    setPathState(smallEnum.SHOOT_TOPSTART);
                    break;
                }

            case SHOOT_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redShoot_redTopStart, true);
                    setPathState(smallEnum.TOPSTART_TOPEND);
                }
                break;

            case TOPSTART_TOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redTopEnd, true);
                    setPathState(smallEnum.INTAKESTART_TOP);
                }
                break;

            case INTAKESTART_TOP:
                intake.runIntake(false, 1);
                setPathState(smallEnum.TOPEND_TOPSTART);
                break;

            case TOPEND_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopEnd_redTopStart, true);
                    setPathState(smallEnum.INTAKEEND_TOP);
                }
                break;

            case INTAKEEND_TOP:
                intake.runIntake(false, 0);
                setPathState(smallEnum.TOPSTART_SHOOT);
                break;

            case TOPSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redShoot, true);
                    setPathState(smallEnum.OUTTAKE_TOP);
                }
                break;

            case OUTTAKE_TOP:
                if (!follower.isBusy()) {
                    manualOuttake.run();
                    while (manualOuttake.outtakeRunning) {}
                    setPathState(smallEnum.SHOOT_END);
                    break;
                }

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redShoot_redEnd, true);
                }
                break;
        }
    }
}