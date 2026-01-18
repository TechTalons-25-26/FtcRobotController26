package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.red.bigRedAltEnum.PathState;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.bigRedAltPaths;

@Autonomous(name = "bigRedAlt")
@Configurable
public class bigRedAlt extends baseAuto {
    private bigRedAltPaths paths = new bigRedAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return PathState.BIGREDSTART_REDSHOOT;
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
            case BIGREDSTART_REDSHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bigRedStart_redShoot, true);
                    setPathState(PathState.SHOOT_PRELOAD);
                }
                break;

            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(3);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(PathState.REDSHOOT_REDALTEND);
                    }
                }
                break;

            case REDSHOOT_REDALTEND:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redShoot_redAltEnd, true);
                }
                break;
        }
    }
}