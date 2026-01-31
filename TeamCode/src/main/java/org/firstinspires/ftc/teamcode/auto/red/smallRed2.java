package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.bigAndSmall2Enum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.smallRed2Paths;

@Autonomous(name = "smallRed2")
@Configurable
public class smallRed2 extends baseAuto {
    private smallRed2Paths paths = new smallRed2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return bigAndSmall2Enum.START_SHOOT;
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
        switch ((bigAndSmall2Enum) pathState) {

            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redTopStart, true);
                    setPathState(bigAndSmall2Enum.TOPSTART_TOPEND);
                }
                break;

            case TOPSTART_TOPEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redTopEnd, true);
                    setPathState(bigAndSmall2Enum.INTAKESTART_TOP);
                }
                break;

            case INTAKESTART_TOP:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.TOPEND_TOPSTART);
                break;

            case TOPEND_TOPSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopEnd_redTopStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_TOP);
                }
                break;

            case INTAKEEND_TOP:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.TOPSTART_SHOOT);
                break;

            case TOPSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redTopStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redMiddleStart, true);
                    setPathState(bigAndSmall2Enum.MIDDLESTART_MIDDLEEND);
                }
                break;

            case MIDDLESTART_MIDDLEEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redMiddleEnd, true);
                    setPathState(bigAndSmall2Enum.INTAKESTART_MIDDLE);
                }
                break;

            case INTAKESTART_MIDDLE:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.MIDDLEEND_MIDDLESTART);
                break;

            case MIDDLEEND_MIDDLESTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleEnd_redMiddleStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_MIDDLE);
                }
                break;

            case INTAKEEND_MIDDLE:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.MIDDLESTART_SHOOT);
                break;

            case MIDDLESTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redMiddleStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redBottomStart, true);
                    setPathState(bigAndSmall2Enum.BOTTOMSTART_BOTTOMEND);
                }
                break;

            case BOTTOMSTART_BOTTOMEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redBottomEnd, true);
                    setPathState(bigAndSmall2Enum.INTAKESTART_BOTTOM);
                }
                break;

            case INTAKESTART_BOTTOM:
                intake.runIntake(false, 1);
                setPathState(bigAndSmall2Enum.BOTTOMEND_BOTTOMSTART);
                break;

            case BOTTOMEND_BOTTOMSTART:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomEnd_redBottomStart, true);
                    setPathState(bigAndSmall2Enum.INTAKEEND_BOTTOM);
                }
                break;

            case INTAKEEND_BOTTOM:
                intake.runIntake(false, 0);
                setPathState(bigAndSmall2Enum.BOTTOMSTART_SHOOT);
                break;

            case BOTTOMSTART_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redBottomStart_redShoot, true);
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
                    follower.followPath(paths.redShoot_redEnd, true);
                }
                break;
        }
    }
}