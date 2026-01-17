package org.firstinspires.ftc.teamcode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallBlueAltEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.smallBlueAltPaths;

@Autonomous(name = "smallBlueAlt")
@Configurable
public class smallBlueAlt extends baseAuto {
    private smallBlueAltPaths paths = new smallBlueAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.SMALLBLUESTART_SMALLBLUEPRELOAD;
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
                        setPathState(PathState.SMALLBLUEPRELOAD_SMALLBLUEALTEND);
                    }
                }
                break;

            case SMALLBLUEPRELOAD_SMALLBLUEALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBluePreload_smallBlueAltEnd, true);
                }
                break;
        }
    }
}