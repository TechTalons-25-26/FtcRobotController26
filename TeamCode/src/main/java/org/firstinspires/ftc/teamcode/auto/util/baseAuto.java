package org.firstinspires.ftc.teamcode.auto.util;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.intake.intakeLogic;
import org.firstinspires.ftc.teamcode.subsystems.outtake.manualOuttake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtakeLogic;
import org.firstinspires.ftc.teamcode.subsystems.path.poseStorage;

public abstract class baseAuto extends OpMode {

    protected TelemetryManager panelsTelemetry;
    protected Follower follower;
    protected Timer pathTimer, opModeTimer;
    protected outtakeLogic outtake = new outtakeLogic();

    protected manualOuttake manualOuttake = new manualOuttake();
    protected intakeLogic intake = new intakeLogic();
    protected boolean shotsTriggered = false;

    // Each auto will define its own PathState enum
    protected Enum<?> pathState;

    // ---------------- ABSTRACT METHODS ----------------
    protected abstract void buildPaths();
    protected abstract void pathStateUpdate();
    protected abstract Enum<?> getInitialState();
    protected abstract Pose getStartingPose();

    // ---------------- SHARED LOGIC ----------------
    protected void setPathState(Enum<?> newState) {
        pathState = newState;
        pathTimer.resetTimer();
        shotsTriggered = false;
    }

    @Override
    public void init() {
        pathState = getInitialState();
        pathTimer = new Timer();
        opModeTimer = new Timer();

        follower = Constants.createFollower(hardwareMap);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        intake.init(hardwareMap);
        outtake.init(hardwareMap);

        buildPaths();
        follower.setStartingPose(getStartingPose());
    }

    @Override
    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        pathStateUpdate();

        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void stop() {
        poseStorage.currentPose = follower.getPose();
    }
}