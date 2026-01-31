package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.altEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.smallRedAlt2Paths;

@Autonomous(name = "smallRedAlt2")
@Configurable
public class smallRedAlt2 extends baseAuto {
    private smallRedAlt2Paths paths = new smallRedAlt2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return altEnum.START_SHOOT;
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
        switch ((altEnum) pathState) {
            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedStart_redPreload, true);
                    setPathState(altEnum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!follower.isBusy()) {
                    if (!shotsTriggered) {
                        outtake.fireShots(2);
                        shotsTriggered = true;
                    } else if (shotsTriggered && !outtake.isBusy()) {
                        setPathState(altEnum.SHOOT_END);
                    }
                }
                break;

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redPreload_redAltEnd, true);
                }
                break;
        }
    }
}