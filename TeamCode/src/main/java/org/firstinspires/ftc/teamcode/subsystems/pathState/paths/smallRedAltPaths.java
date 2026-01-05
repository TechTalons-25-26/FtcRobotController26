package org.firstinspires.ftc.teamcode.subsystems.pathState.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
public class smallRedAltPaths {
    public PathChain smallRedStart_redShoot;
    public PathChain redShoot_redTopStart;
    public PathChain redTopStart_redTopEnd;
    public PathChain redTopEnd_redTopStart;
    public PathChain redTopStart_redShoot;
    public PathChain redShoot_redMiddleStart;
    public PathChain redMiddleStart_redMiddleEnd;
    public PathChain redMiddleEnd_redMiddleStart;
    public PathChain redMiddleStart_redShoot;
    public PathChain redShoot_redBottomStart;
    public PathChain redBottomStart_redBottomEnd;
    public PathChain redBottomEnd_redBottomStart;
    public PathChain redBottomStart_redShoot;
    public PathChain redShoot_redEnd;

    public void buildPaths(Follower follower) {
        smallRedStart_redShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(88.000, 8.000),
                        new Pose(88.200, 44.500),
                        new Pose(62.200, 58.000),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redTopStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(91.700, 93.200),
                        new Pose(92.500, 84.100),
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
                        new Pose(92.500, 84.100),
                        new Pose(91.700, 93.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redMiddleStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(91.700, 93.200),
                        new Pose(91.000, 60.400),
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
                        new Pose(91.000, 60.400),
                        new Pose(91.700, 93.200),
                        new Pose(84.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        redShoot_redBottomStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(84.000, 84.000),
                        new Pose(94.000, 96.200),
                        new Pose(89.700, 35.600),
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
                        new Pose(89.700, 35.600),
                        new Pose(94.000, 96.200),
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