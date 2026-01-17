package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class smallBlueAltPaths {
    public PathChain smallBlueStart_smallBluePreload;
    public PathChain smallBluePreload_smallBlueAltEnd;

    public void buildPaths(Follower follower) {
        smallBlueStart_smallBluePreload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 8.000),

                                new Pose(56.000, 16.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(110))

                .build();

        smallBluePreload_smallBlueAltEnd = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 16.000),

                                new Pose(36.000, 12.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(110), Math.toRadians(-169))

                .build();
    }
}