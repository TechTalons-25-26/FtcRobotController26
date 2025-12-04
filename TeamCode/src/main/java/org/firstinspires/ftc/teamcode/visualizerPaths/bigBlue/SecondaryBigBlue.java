package org.firstinspires.ftc.teamcode;

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
public class PedroAutonomous extends OpMode {

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

    public PathChain Path2;
    public PathChain Path2;
    public PathChain Path3;
    public PathChain Path5;
    public PathChain Path6;
    public PathChain Path7;
    public PathChain Path8;
    public PathChain Path9;
    public PathChain Path10;
    public PathChain Path11;

    public Paths(Follower follower) {
      Path2 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(21.178, 123.333), new Pose(53.356, 95.588))
        )
        .setLinearHeadingInterpolation(Math.toRadians(324), Math.toRadians(360))
        .build();

      Path2 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(53.356, 95.588), new Pose(13.391, 95.588))
        )
        .setTangentHeadingInterpolation()
        .setReversed(true)
        .build();

      Path3 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(13.391, 95.588), new Pose(53.356, 95.588))
        )
        .setTangentHeadingInterpolation()
        .build();

      Path5 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(53.356, 95.588),
            new Pose(29.459, 104.446),
            new Pose(27.399, 74.987),
            new Pose(49.030, 75.811)
          )
        )
        .setTangentHeadingInterpolation()
        .build();

      Path6 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(49.030, 75.811), new Pose(14.215, 75.811))
        )
        .setTangentHeadingInterpolation()
        .setReversed(true)
        .build();

      Path7 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(14.215, 75.811), new Pose(49.030, 75.811))
        )
        .setTangentHeadingInterpolation()
        .build();

      Path8 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(49.030, 75.811),
            new Pose(67.777, 80.755),
            new Pose(49.236, 98.266)
          )
        )
        .setTangentHeadingInterpolation()
        .build();

      Path9 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(49.236, 98.266),
            new Pose(35.845, 127.931),
            new Pose(19.571, 49.442),
            new Pose(48.824, 55.416)
          )
        )
        .setTangentHeadingInterpolation()
        .build();

      Path10 = follower
        .pathBuilder()
        .addPath(
          new BezierLine(new Pose(48.824, 55.416), new Pose(13.391, 55.416))
        )
        .setTangentHeadingInterpolation()
        .setReversed(true)
        .build();

      Path11 = follower
        .pathBuilder()
        .addPath(
          new BezierCurve(
            new Pose(13.391, 55.416),
            new Pose(86.000, 69.000),
            new Pose(49.236, 98.266)
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
