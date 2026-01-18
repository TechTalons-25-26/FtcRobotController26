package org.firstinspires.ftc.teamcode.subsystems.path;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class pathFollower {

    private final Follower follower;
    private boolean movingToTarget = false;
    private boolean lastB = false;
    public static Pose currentPose = new Pose(0, 0, 0);

    private Pose targetPose = null; // TeleOp will set this

    public pathFollower(HardwareMap hardwareMap) {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(currentPose);
    }

    public void update() {
        if (!movingToTarget) return;

        follower.update();
        Pose currentPose = follower.getPose();

        double dx = currentPose.getX() - targetPose.getX();
        double dy = currentPose.getY() - targetPose.getY();
        double dist = Math.hypot(dx, dy);

        if (!follower.isBusy() || dist < 2.0) {
            movingToTarget = false;
        }
    }

    public void handleStartPath(boolean bPressed) {
        if (targetPose == null) return; // no target set yet

        boolean justPressedB = bPressed && !lastB;

        if (justPressedB && !movingToTarget) {
            Pose currentPose = follower.getPose();

            BezierLine curve = new BezierLine(currentPose, targetPose);
            Path movePath = new Path(curve);
            movePath.setLinearHeadingInterpolation(
                    currentPose.getHeading(),
                    targetPose.getHeading()
            );

            follower.followPath(movePath);
            movingToTarget = true;
        }

        lastB = bPressed;
    }

    public boolean isBusy() {
        return movingToTarget;
    }

    public void setTargetPose(Pose newTarget) {
        this.targetPose = newTarget;
    }

    public Pose getPose() {
        return follower.getPose();
    }
}