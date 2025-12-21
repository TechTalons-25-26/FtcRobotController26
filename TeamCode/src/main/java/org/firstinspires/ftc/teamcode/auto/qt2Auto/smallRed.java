package org.firstinspires.ftc.teamcode.auto.qt2Auto;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.PoseStorage;

@Autonomous(name = "smallRedOldV1", group = "Autonomous")
@Configurable // Panels
public class smallRed extends OpMode {
    // ----- Drive Motors -----
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor leftWheel, rightWheel;
    // ----- Mechanisms -----
    private DcMotor intakeMotor;
    private CRServo conveyor;
    private Servo outtakeAngle;
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private PathsR paths; // Paths defined in the Paths class
    private Timer pathTimer, actionTimer, opmodeTimer;
    // ----- Timed Mechanisms -----
    private long intakeEndTime = 0;
    private long conveyorEndTime = 0;
    private long outtakeEndTime = 0;
    @Override
    public void init() {
        robotSetup();
        //updateMechanisms();

        outtakeAngle.setPosition(0.14);

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(88, 8, Math.toRadians(270)));

        paths = new PathsR(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);

        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine
        updateMechanisms();

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
        telemetry.addData("outtake Angle:", outtakeAngle);
        telemetry.update();
    }

    public static class PathsR {

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
        public PathChain Path13;

        public PathsR(Follower follower) {
            Path1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(88.000, 8.000), new Pose(88.000, 12.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(288))
                    .build();

            Path2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(88.000, 12.000),
                                    new Pose(93.600, 29.200),
                                    new Pose(85.200, 36.120),
                                    new Pose(72.000, 36.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(72.000, 36.000), new Pose(96.000, 36.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(96.000, 36.000), new Pose(136.400, 36.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path5 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(136.400, 36.000),
                                    new Pose(91.400, 36.460),
                                    new Pose(77.000, 76.100),
                                    new Pose(84.000, 84.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path6 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(84.000, 84.000),
                                    new Pose(74.270, 72.590),
                                    new Pose(79.480, 60.150),
                                    new Pose(96.000, 60.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path7 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(96.000, 60.000), new Pose(136.400, 60.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path8 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(136.400, 60.000), new Pose(96.000, 60.000))
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();

            Path9 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(96.000, 60.000),
                                    new Pose(79.480, 60.150),
                                    new Pose(74.270, 72.590),
                                    new Pose(84.000, 84.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();
            /*
            Path10 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(84.000, 84.000), new Pose(84.000, 84.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(230), Math.toRadians(180))
                    .build();

            Path11 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(84.000, 84.000), new Pose(120.000, 84.000))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            Path12 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(120.000, 84.000), new Pose(84.000, 84.000))
                    )
                    .setTangentHeadingInterpolation()
                    .setReversed()
                    .build();



            Path13 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(84.000, 84.000), new Pose(84.000, 84.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(230))
                    .build();

             */
        }
    }

    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(paths.Path1);
                setPathState(1);
                break;
            case 1:

            /* You could check for
            - Follower State: "if(!follower.isBusy()) {}"
            - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
            - Robot Position: "if(follower.getPose().getX() > 36) {}"
            */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(paths.Path2);
                    setPathState(2);
                }
                break;
            case 2:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.Path3);
                    setPathState(3);
                }
                break;
            case 3:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    rollIntake(0.9,5000);
                    rollConveyor(1,5000);

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(paths.Path4);
                    setPathState(4);
                }
                break;
            case 4:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.Path5);
                    setPathState(5);
                }
                break;
            case 5:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if (!follower.isBusy()) {
                    pathTimer.resetTimer();  // start pause timer
                    setPathState(50);        // go to pause
                    //rollOuttake(0.38, 50);
                }
                break;
            case 50:
                rollIntake(-0.5, 50);
                rollConveyor(-1, 50);
                rollOuttake(0.38, 4999);
                rollConveyor(-1, 4999);
                if (pathTimer.getElapsedTimeSeconds() > 5) {

                    follower.followPath(paths.Path6);
                    setPathState(6);   // go to the normal next state
                }
                break;
            case 6:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */
                    rollIntake(0.9,5000);
                    rollConveyor(1,5000);

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.Path7);
                    setPathState(7);
                }
                break;
            case 7:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */
                    rollConveyor(1,500);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.Path8);
                    setPathState(8);
                }
                break;
            case 8:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */
                    rollOuttake(0.38, 4999);
                    rollConveyor(-1, 4999);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(paths.Path9);
                    setPathState(9);
                }
                break;
            case 9:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    Pose finalPose = follower.getPose();

                    PoseStorage.currentPose = finalPose;
                    panelsTelemetry.debug("Final X", finalPose.getX());
                    panelsTelemetry.debug("Final Y", finalPose.getY());
                    panelsTelemetry.debug("Final Heading", finalPose.getHeading());
                    panelsTelemetry.update(telemetry);
                    setPathState(-1);
                }
                break;
        }
        return pathState;
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    private void robotSetup() {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        conveyor = hardwareMap.get(CRServo.class, "conveyor");
        outtakeAngle = hardwareMap.get(Servo.class, "outtakeAngle");

        rightWheel.setDirection(DcMotor.Direction.REVERSE);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        stopWheelMotors();

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    private void stopWheelMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    // ----- Timed Mechanism Methods -----
    public void rollIntake(double speed, long durationMS) {
        intakeMotor.setPower(speed);
        intakeEndTime = System.currentTimeMillis() + durationMS;
    }

    public void rollConveyor(double speed, long durationMS) {
        conveyor.setPower(-speed);
        conveyorEndTime = System.currentTimeMillis() + durationMS;
    }

    public void rollOuttake(double speed, long durationMS) {
        leftWheel.setPower(speed);
        rightWheel.setPower(speed);
        outtakeEndTime = System.currentTimeMillis() + durationMS;
    }

    private void updateMechanisms() {
        if (System.currentTimeMillis() > intakeEndTime) {
            intakeMotor.setPower(0);
            intakeEndTime = 0;
        }

        if (System.currentTimeMillis() > conveyorEndTime) {
            conveyor.setPower(0);
            conveyorEndTime = 0;
        }

        if (System.currentTimeMillis() > outtakeEndTime) {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
            outtakeEndTime = 0;
        }
    }
}
