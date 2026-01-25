package org.firstinspires.ftc.teamcode.subsystems.path.state.paths.blue;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class bigBlueAltPaths {
    public PathChain bigBlueStart_blueShoot;
    public PathChain blueShoot_blueAltEnd;

    public void buildPaths(Follower follower) {
        bigBlueStart_blueShoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(20.800, 123.100),
                                new Pose(39.600, 109.300),
                                new Pose(44.000, 103.200),
                                new Pose(60.000, 84.000)
                        )
                ).setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueAltEnd = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(50.000, 96.200),
                                new Pose(46.000, 72.100),
                                new Pose(20.000, 104.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}