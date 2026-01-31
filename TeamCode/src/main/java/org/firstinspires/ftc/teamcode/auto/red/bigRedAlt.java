package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.altEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.bigAndSmall2Enum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.bigRedAltPaths;

@Autonomous(name = "bigRedAlt")
@Configurable
public class bigRedAlt extends baseAuto {
    private bigRedAltPaths paths = new bigRedAltPaths();

    @Override
    protected Enum<?> getInitialState() {
        return altEnum.START_SHOOT;
    }

    // ---------------- REQUI OVERRIDES ----------------

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
                    follower.followPath(paths.bigRedStart_redShoot, true);
                    setPathState(altEnum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!follower.isBusy()) {
                    outtake.run();
                    while (outtake.outtakeRunning) {}
                    setPathState(altEnum.SHOOT_END);
                    break;
                }

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.redShoot_redAltEnd, true);
                }
                break;
        }
    }
}