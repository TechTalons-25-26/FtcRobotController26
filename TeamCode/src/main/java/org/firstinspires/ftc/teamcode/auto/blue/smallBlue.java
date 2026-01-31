package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.smallBluePaths;

@Autonomous(name = "smallBlue")
@Configurable
public class smallBlue extends baseAuto {

private smallBluePaths paths = new smallBluePaths();

    @Override
    protected Enum<?> getInitialState() {
        return smallEnum.START_PRELOAD;
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
        switch ((smallEnum) pathState) {

            case START_PRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBlueStart_smallBluePreload, true);
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
                    follower.followPath(paths.smallBluePreload_blueBottomStart, true);
                    setPathState(smallEnum.BOTTOMSTART_BOTTOMEND);
                }
                break;

            case BOTTOMSTART_BOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueBottomEnd, true);
                    setPathState(smallEnum.INTAKESTART_BOTTOM);
                }
                break;

            case INTAKESTART_BOTTOM:
                intake.runIntake(false, 1);
                setPathState(smallEnum.BOTTOMEND_BOTTOMSTART);
                break;

            case BOTTOMEND_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomEnd_blueBottomStart, true);
                    setPathState(smallEnum.INTAKEEND_BOTTOM);
                }
                break;

            case INTAKEEND_BOTTOM:
                intake.runIntake(false, 0);
                setPathState(smallEnum.BOTTOMSTART_SHOOT);
                break;

            case BOTTOMSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueShoot, true);
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
                    follower.followPath(paths.blueShoot_blueMiddleStart, true);
                    setPathState(smallEnum.MIDDLESTART_MIDDLEEND);
                }
                break;

            case MIDDLESTART_MIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueMiddleEnd, true);
                    setPathState(smallEnum.INTAKESTART_MIDDLE);
                }
                break;

            case INTAKESTART_MIDDLE:
                intake.runIntake(false, 1);
                setPathState(smallEnum.MIDDLEEND_MIDDLESTART);
                break;

            case MIDDLEEND_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleEnd_blueMiddleStart, true);
                    setPathState(smallEnum.INTAKEEND_MIDDLE);
                }
                break;

            case INTAKEEND_MIDDLE:
                intake.runIntake(false, 0);
                setPathState(smallEnum.MIDDLESTART_SHOOT);
                break;

            case MIDDLESTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueShoot, true);
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
                    follower.followPath(paths.blueShoot_blueTopStart, true);
                    setPathState(smallEnum.TOPSTART_TOPEND);
                }
                break;

            case TOPSTART_TOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueTopEnd, true);
                    setPathState(smallEnum.INTAKESTART_TOP);
                }
                break;

            case INTAKESTART_TOP:
                intake.runIntake(false, 1);
                setPathState(smallEnum.TOPEND_TOPSTART);
                break;

            case TOPEND_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopEnd_blueTopStart, true);
                    setPathState(smallEnum.INTAKEEND_TOP);
                }
                break;
                
            case INTAKEEND_TOP:
                intake.runIntake(false, 0);
                setPathState(smallEnum.TOPSTART_SHOOT);
                break;

            case TOPSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueShoot, true);
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
                    follower.followPath(paths.blueShoot_blueEnd, true);
                }
                break;
        }
    }
}