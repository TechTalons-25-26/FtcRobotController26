package org.firstinspires.ftc.teamcode.subsystems.path.state.paths.red;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
public class smallRedPaths {
    public PathChain smallRedStart_smallRedPreload;
    public PathChain smallRedPreload_redBottomStart;
    public PathChain redBottomStart_redBottomEnd;
    public PathChain redBottomEnd_redBottomStart;
    public PathChain redBottomStart_redShoot;
    public PathChain redShoot_redMiddleStart;
    public PathChain redMiddleStart_redMiddleEnd;
    public PathChain redMiddleEnd_redMiddleStart;
    public PathChain redMiddleStart_redShoot;
    public PathChain redShoot_redTopStart;
    public PathChain redTopStart_redTopEnd;
    public PathChain redTopEnd_redTopStart;
    public PathChain redTopStart_redShoot;
    public PathChain redShoot_redEnd;

    public void buildPaths(Follower follower) {
        smallRedStart_smallRedPreload = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(88.000, 8.000),
                        new Pose(88.000, 16.000)
                ))
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(70))
                .build();

        smallRedPreload_redBottomStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(88.000, 16.000),
                        new Pose(96.000, 38.000),
                        new Pose(94.900, 36.000),
                        new Pose(102.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redBottomStart_redBottomEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 36.000),
                        new Pose(136.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redBottomEnd_redBottomStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(136.000, 36.000),
                        new Pose(102.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redBottomStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 36.000),
                        new Pose(89.700, 36.550),
                        new Pose(94.000, 96.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redMiddleStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(92.200, 93.100),
                        new Pose(88.500, 60.400),
                        new Pose(102.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redMiddleStart_redMiddleEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 60.000),
                        new Pose(136.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redMiddleEnd_redMiddleStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(136.000, 60.000),
                        new Pose(102.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redMiddleStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 60.000),
                        new Pose(88.500, 60.400),
                        new Pose(92.200, 93.100),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redTopStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(91.700, 93.200),
                        new Pose(92.500, 84.000),
                        new Pose(102.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redTopStart_redTopEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(102.000, 84.000),
                        new Pose(130.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        redTopEnd_redTopStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(130.000, 84.000),
                        new Pose(102.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redTopStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(102.000, 84.000),
                        new Pose(92.500, 84.000),
                        new Pose(91.700, 93.300),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redEnd = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(94.000, 96.200),
                        new Pose(98.000, 72.100),
                        new Pose(107.300, 71.900)
                ))
                .setTangentHeadingInterpolation()
                .build();
    }
}