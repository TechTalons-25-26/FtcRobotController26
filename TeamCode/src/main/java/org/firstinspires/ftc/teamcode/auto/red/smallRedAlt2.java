package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallRedAlt2Enum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.smallRedAlt2Paths;

@Autonomous(name = "smallRedAlt2")
@Configurable
public class smallRedAlt2 extends baseAuto {
    private smallRedAlt2Paths paths = new smallRedAlt2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.SMALLREDSTART_REDPRELOAD;
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
            case SMALLREDSTART_REDPRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedStart_redPreload, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDPRELOAD_REDALTEND);
                    }
                }
                break;

            case REDPRELOAD_REDALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redPreload_redAltEnd, true);
                }
                break;
        }
    }
}