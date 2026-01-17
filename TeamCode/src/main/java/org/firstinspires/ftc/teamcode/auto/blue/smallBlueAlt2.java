package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.smallBlueAlt2Enum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.smallBlueAlt2Paths;

@Autonomous(name = "smallBlueAlt2")
@Configurable
public class smallBlueAlt2 extends baseAuto {
    private smallBlueAlt2Paths paths = new smallBlueAlt2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.SMALLBLUESTART_BLUEPRELOAD;
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
            case SMALLBLUESTART_BLUEPRELOAD:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBlueStart_bluePreload, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.BLUEPRELOAD_BLUEALTEND);
                    }
                }
                break;

            case BLUEPRELOAD_BLUEALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bluePreload_blueAltEnd, true);
                }
                break;
        }
    }
}