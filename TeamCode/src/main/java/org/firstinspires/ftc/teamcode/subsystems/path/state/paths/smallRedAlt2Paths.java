package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class smallRedAlt2Paths {
    public PathChain smallRedStart_redPreload;
    public PathChain redPreload_redAltEnd;

    public void buildPaths(Follower follower) {
        smallRedStart_redPreload = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(88.000, 8.000),
                                new Pose(88.100, 23.300),
                                new Pose(69.100, 65.600),
                                new Pose(84.000, 84.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        redPreload_redAltEnd = follower.pathBuilder().addPath(
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
