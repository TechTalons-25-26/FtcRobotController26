package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class smallBlueAlt2Paths {
    public PathChain smallBlueStart_bluePreload;
    public PathChain bluePreload_blueAltEnd;

    public void buildPaths(Follower follower) {
        smallBlueStart_bluePreload = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(56.000, 8.000),
                                new Pose(55.900, 23.300),
                                new Pose(74.900, 65.600),
                                new Pose(60.000, 84.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        bluePreload_blueAltEnd = follower.pathBuilder().addPath(
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
