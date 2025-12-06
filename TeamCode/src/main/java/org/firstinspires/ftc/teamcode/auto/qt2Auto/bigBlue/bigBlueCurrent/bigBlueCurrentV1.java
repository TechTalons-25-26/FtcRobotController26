package org.firstinspires.ftc.teamcode.auto.qt2Auto.bigBlue.bigBlueCurrent;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable // Panels
public class bigBlueCurrentV1 extends OpMode {

  private TelemetryManager panelsTelemetry; // Panels Telemetry instance
  public Follower follower; // Pedro Pathing follower instance
  private int pathState; // Current autonomous path state (state machine)
  private Paths paths; // Paths defined in the Paths class

  @Override
  public void init() {
    panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    follower = Constants.createFollower(hardwareMap);
    follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

    paths = new Paths(follower); // Build paths

    panelsTelemetry.debug("Status", "Initialized");
    panelsTelemetry.update(telemetry);
  }

  @Override
  public void loop() {
    follower.update(); // Update Pedro Pathing
    pathState = autonomousPathUpdate(); // Update autonomous state machine

    // Log values to Panels and Driver Station
    panelsTelemetry.debug("Path State", pathState);
    panelsTelemetry.debug("X", follower.getPose().getX());
    panelsTelemetry.debug("Y", follower.getPose().getY());
    panelsTelemetry.debug("Heading", follower.getPose().getHeading());
    panelsTelemetry.update(telemetry);
  }

  public static class Paths {

    public PathChain Path1;
    public PathChain Path2;
    public PathChain Path3;
    public PathChain Path4;
    public PathChain Path5;
    public PathChain Path6;
    public PathChain Path7;
    public PathChain Path8;
    public PathChain Path9;
    public PathChain Path10;
    public PathChain Path11;
    public PathChain Path12;

    public Paths(Follower follower) {
      Path1 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(21.000, 123.000), new Pose(60.000, 84.000))
        )
        .setLinearHeadingInterpolation(Math.toRadians(324), Math.toRadians(360))
        .build();

      Path2 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(60.000, 84.000), new Pose(16.000, 84.000))
        )
        .setTangentHeadingInterpolation()
        .setReversed()
        .build();

      Path3 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(16.000, 84.000), new Pose(41.000, 84.000))
        )
        .setTangentHeadingInterpolation()
        .build();

      Path4 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(41.000, 84.000),
            new Pose(59.000, 84.000),
            new Pose(49.000, 97.000)
          )
        )
        .setTangentHeadingInterpolation()
        .build();

      Path5 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(49.000, 97.000),
            new Pose(60.000, 83.000),
            new Pose(79.000, 59.000),
            new Pose(41.000, 60.000)
          )
        )
        .setTangentHeadingInterpolation()
        .setReversed()
        .build();

      Path6 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(41.000, 60.000), new Pose(15.000, 60.000))
        )
        .setTangentHeadingInterpolation()
        .setReversed()
        .build();

      Path7 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(15.000, 60.000), new Pose(41.000, 60.000))
        )
        .setTangentHeadingInterpolation()
        .build();

      Path8 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(41.000, 60.000),
            new Pose(91.000, 60.000),
            new Pose(60.000, 82.000),
            new Pose(48.000, 97.000)
          )
        )
        .setTangentHeadingInterpolation()
        .build();

      Path9 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(48.000, 97.000),
            new Pose(96.000, 36.600),
            new Pose(43.000, 37.100)
          )
        )
        .setTangentHeadingInterpolation()
        .setReversed()
        .build();

      Path10 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(43.000, 37.100), new Pose(16.000, 36.400))
        )
        .setTangentHeadingInterpolation()
        .setReversed()
        .build();

      Path11 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(16.000, 36.400), new Pose(43.000, 36.000))
        )
        .setTangentHeadingInterpolation()
        .build();

      Path12 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(43.000, 36.000),
            new Pose(96.600, 36.000),
            new Pose(48.000, 97.000)
          )
        )
        .setTangentHeadingInterpolation()
        .build();
    }
  }

  public int autonomousPathUpdate() {
    // Add your state machine Here
    // Access paths with paths.pathName
    // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
    return pathState;
  }
}
