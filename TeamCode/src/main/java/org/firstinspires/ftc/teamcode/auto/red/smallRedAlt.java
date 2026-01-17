package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallRedAltEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.smallRedAltPaths;

@Autonomous(name = "smallRedAlt")
@Configurable
public class smallRedAlt extends baseAuto {
    private smallRedAltPaths paths = new smallRedAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.SMALLREDSTART_SMALLREDPRELOAD;
    }

    // ---------------- REQUIRED OVERRIDES ----------------

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
        switch ((PathState) pathState) {
            case SMALLREDSTART_SMALLREDPRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedStart_smallRedPreload, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.SMALLREDPRELOAD_SMALLREDALTEND);
                    }
                }
                break;

            case SMALLREDPRELOAD_SMALLREDALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedPreload_smallRedAltEnd, true);
                }
                break;
        }
    }
}