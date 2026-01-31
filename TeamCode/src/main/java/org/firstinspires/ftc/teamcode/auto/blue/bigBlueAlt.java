package org.firstinspires.ftc.teamcode.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.altEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue.bigBlueAltPaths;

@Autonomous(name = "bigBlueAlt")
@Configurable
public class bigBlueAlt extends baseAuto {
    private bigBlueAltPaths paths = new bigBlueAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return altEnum.START_SHOOT;
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
        switch ((altEnum) pathState) {
            case START_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(paths.bigBlueStart_blueShoot, true);
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
                    follower.followPath(paths.blueShoot_blueAltEnd, true);
                }
                break;
        }
    }
}