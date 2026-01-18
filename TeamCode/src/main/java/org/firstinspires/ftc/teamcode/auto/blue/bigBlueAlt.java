package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.blue.bigBlueAltEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.bigBlueAltPaths;

@Autonomous(name = "bigBlueAlt")
@Configurable
public class bigBlueAlt extends baseAuto {
    private bigBlueAltPaths paths = new bigBlueAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.BIGBLUESTART_BLUESHOOT;
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
        switch ((PathState) pathState) {
            case BIGBLUESTART_BLUESHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bigBlueStart_blueShoot, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.BLUESHOOT_BLUEALTEND);
                    }
                }
                break;

            case BLUESHOOT_BLUEALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.blueShoot_blueAltEnd, true);
                }
                break;
        }
    }
}