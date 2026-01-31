package org.firstinspires.ftc.teamcode.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.util.baseAuto;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.altEnum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.enums.bigAndSmall2Enum;
import org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red.smallRedAltPaths;

@Autonomous(name = "smallRedAlt")
@Configurable
public class smallRedAlt extends baseAuto {
    private smallRedAltPaths paths = new smallRedAltPaths();

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
                    follower.followPath(paths.smallRedStart_smallRedPreload, true);
                    setPathState(altEnum.OUTTAKE_PRELOAD);
                }
                break;

            case OUTTAKE_PRELOAD:
                if (!shotsTriggered) {
                    manualOuttake.start();
                    shotsTriggered = true;
                }

                if (!manualOuttake.isBusy()) {
                    setPathState(altEnum.SHOOT_END);
                }
                break;

            case SHOOT_END:
                if (!follower.isBusy()) {
                    follower.followPath(paths.smallRedPreload_smallRedAltEnd, true);
                }
                break;
        }
    }
}