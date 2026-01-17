package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class bigRedAltPaths {
    public PathChain bigRedStart_redShoot;
    public PathChain redShoot_redEnd;

    public void buildPaths(Follower follower) {
        bigRedStart_redShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(123.200, 123.100),
                                new Pose(104.400, 109.300),
                                new Pose(100.000, 103.200),
                                new Pose(84.000, 84.000)
                        )
                ).setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redEnd = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(84.000, 84.000),
                                new Pose(94.000, 96.200),
                                new Pose(98.000, 72.100),
                                new Pose(124.000, 104.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}
