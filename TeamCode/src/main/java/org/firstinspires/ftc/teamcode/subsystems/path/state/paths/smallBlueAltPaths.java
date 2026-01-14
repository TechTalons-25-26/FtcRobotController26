package org.firstinspires.ftc.teamcode.subsystems.path.state.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
public class smallBlueAltPaths {
    public PathChain smallBlueStart_blueShoot;
    public PathChain blueShoot_blueTopStart;
    public PathChain blueTopStart_blueTopEnd;
    public PathChain blueTopEnd_blueTopStart;
    public PathChain blueTopStart_blueShoot;
    public PathChain blueShoot_blueMiddleStart;
    public PathChain blueMiddleStart_blueMiddleEnd;
    public PathChain blueMiddleEnd_blueMiddleStart;
    public PathChain blueMiddleStart_blueShoot;
    public PathChain blueShoot_blueBottomStart;
    public PathChain blueBottom_blueBottomEnd;
    public PathChain blueBottomEnd_blueBottomStart;
    public PathChain blueBottomStart_blueShoot;
    public PathChain blueShoot_blueEnd;

    public void buildPaths(Follower follower) {
        smallBlueStart_blueShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(56.000, 8.000),
                        new Pose(55.800, 44.500),
                        new Pose(81.800, 58.000),
                        new Pose(60.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueTopStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(60.000, 84.000),
                        new Pose(52.300, 93.200),
                        new Pose(51.500, 84.100),
                        new Pose(42.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueTopStart_blueTopEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(42.000, 84.000),
                        new Pose(14.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueTopEnd_blueTopStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(14.000, 84.000),
                        new Pose(42.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueTopStart_blueShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(42.000, 84.000),
                        new Pose(51.500, 84.100),
                        new Pose(52.300, 93.200),
                        new Pose(60.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueMiddleStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(60.000, 84.000),
                        new Pose(52.300, 93.200),
                        new Pose(53.000, 60.400),
                        new Pose(42.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueMiddleStart_blueMiddleEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(42.000, 60.000),
                        new Pose(8.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueMiddleEnd_blueMiddleStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(8.000, 60.000),
                        new Pose(42.000, 60.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueMiddleStart_blueShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(42.000, 60.000),
                        new Pose(53.000, 60.400),
                        new Pose(52.300, 93.200),
                        new Pose(60.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueBottomStart = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(60.000, 84.000),
                        new Pose(50.000, 96.200),
                        new Pose(54.300, 35.600),
                        new Pose(42.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueBottom_blueBottomEnd = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(42.000, 36.000),
                        new Pose(8.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .build();

        blueBottomEnd_blueBottomStart = follower.pathBuilder()
                .addPath(new BezierLine(
                        new Pose(8.000, 36.000),
                        new Pose(42.000, 36.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueBottomStart_blueShoot = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(42.000, 36.000),
                        new Pose(54.300, 35.600),
                        new Pose(50.000, 96.200),
                        new Pose(60.000, 84.000)
                ))
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        blueShoot_blueEnd = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Pose(60.000, 84.000),
                        new Pose(50.000, 96.200),
                        new Pose(46.000, 72.100),
                        new Pose(36.700, 71.900)
                ))
                .setTangentHeadingInterpolation()
                .build();
    }
}