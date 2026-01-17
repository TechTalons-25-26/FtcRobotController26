package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class smallRedAltPaths {
    public PathChain smallRedStart_smallRedPreload;
    public PathChain smallRedPreload_smallRedAltEnd;

    public void buildPaths(Follower follower) {
        smallRedStart_smallRedPreload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(88.000, 8.000),

                                new Pose(88.000, 16.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(70))

                .build();

        smallRedPreload_smallRedAltEnd = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(88.000, 16.000),

                                new Pose(108.000, 12.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(70), Math.toRadians(-11))

                .build();
    }
}