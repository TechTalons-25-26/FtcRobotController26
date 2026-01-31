package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.altEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.smallBlueAlt2Paths;

@Autonomous(name = "smallBlueAlt2")
@Configurable
public class smallBlueAlt2 extends baseAuto {
    private smallBlueAlt2Paths paths = new smallBlueAlt2Paths();

    @Override
    protected Enum<?> getInitialState() {
        return altEnum.START_SHOOT;
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
        switch ((altEnum) pathState) {
            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallBlueStart_bluePreload, true);
                    setPathState(altEnum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!follower.isBusy()) {
                    outtake.run();
                    break;
                }

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bluePreload_blueAltEnd, true);
                }
                break;
        }
    }
}