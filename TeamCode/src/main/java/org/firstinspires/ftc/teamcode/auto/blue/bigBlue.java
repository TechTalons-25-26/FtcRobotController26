package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.bigAndSmall2Enum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.bigBluePaths;

@Autonomous(name = "bigBlue")
@Configurable
public class bigBlue extends baseAuto {
    private bigBluePaths paths = new bigBluePaths();

    @Override
    protected Enum<?> getInitialState() {
        return bigAndSmall2Enum.START_SHOOT;
    }

    // ---------------- REQUIRED OVERRIDES ----------------

    @Override
    protected Pose getStartingPose() {
        return new Pose(20.800, 123.100, Math.toRadians(144));
    }

    @Override
    protected void buildPaths() {
        paths.buildPaths(follower);
    }

    @Override
    protected void pathStateUpdate() {
        switch ((bigAndSmall2Enum) pathState) {

            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bigBlueStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!shotsTriggered) {
                    manualOuttake.start();
                    shotsTriggered = true;
                }

                if (!manualOuttake.isBusy()) {
                    setPathState(bigAndSmall2Enum.SHOOT_TOPSTART);
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
                    setPathState(bigAndSmall2Enum.INTAKESTART_TOP);
                }
                break;

            case INTAKESTART_TOP:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.TOPEND_TOPSTART);
                break;

            case TOPEND_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopEnd_blueTopStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_TOP);
                }
                break;

            case INTAKEEND_TOP:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.TOPSTART_SHOOT);
                break;

            case TOPSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueTopStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.OUTTAKE_TOP);
                }
                break;

            case OUTTAKE_TOP:
                if (!shotsTriggered) {
                    manualOuttake.start();
                    shotsTriggered = true;
                }

                if (!manualOuttake.isBusy()) {
                    setPathState(bigAndSmall2Enum.SHOOT_MIDDLESTART);
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
                    setPathState(bigAndSmall2Enum.INTAKESTART_MIDDLE);
                }
                break;

            case INTAKESTART_MIDDLE:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.MIDDLEEND_MIDDLESTART);
                break;

            case MIDDLEEND_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleEnd_blueMiddleStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_MIDDLE);
                }
                break;

            case INTAKEEND_MIDDLE:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.MIDDLESTART_SHOOT);
                break;

            case MIDDLESTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueMiddleStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.OUTTAKE_MIDDLE);
                }
                break;

            case OUTTAKE_MIDDLE:
                if (!shotsTriggered) {
                    manualOuttake.start();
                    shotsTriggered = true;
                }

                if (!manualOuttake.isBusy()) {
                    setPathState(bigAndSmall2Enum.SHOOT_BOTTOMSTART);
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
                    setPathState(bigAndSmall2Enum.INTAKESTART_BOTTOM);
                }
                break;

            case INTAKESTART_BOTTOM:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.BOTTOMEND_BOTTOMSTART);
                break;

            case BOTTOMEND_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomEnd_blueBottomStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_BOTTOM);
                }
                break;

            case INTAKEEND_BOTTOM:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.BOTTOMSTART_SHOOT);
                break;

            case BOTTOMSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueBottomStart_blueShoot, true);
                    setPathState(bigAndSmall2Enum.OUTTAKE_BOTTOM);
                }
                break;

            case OUTTAKE_BOTTOM:
                if (!shotsTriggered) {
                    manualOuttake.start();
                    shotsTriggered = true;
                }

                if (!manualOuttake.isBusy()) {
                    setPathState(bigAndSmall2Enum.SHOOT_END);
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