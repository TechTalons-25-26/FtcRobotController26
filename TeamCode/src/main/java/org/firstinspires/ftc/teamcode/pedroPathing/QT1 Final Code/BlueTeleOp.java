package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

// PedroPathing imports
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.Path;

@TeleOp(name = "A BLUE", group = "test drive")
public class BlueTeleOp extends LinearOpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor leftWheel, rightWheel;
    private DcMotor intakeMotor;
    private CRServo conveyor;
    private Servo outtakeAngle;

    private Follower follower;

    double wheelSpeed = 0.4;
    double axonPosition = 0.14; // start centered
    double step = 0.01; // servo step

    private boolean movingToTarget = false;
    boolean lastA = false;
    boolean lastY = false;
    boolean lastB = false; // track B button

    // target pose for pressing B (make sure units match your field config)
    private final Pose targetPose = new Pose(60.055, 85.424, Math.toRadians(-43.052)); // example target

    @Override
    public void runOpMode() {

        // Hardware mapping
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        conveyor = hardwareMap.get(CRServo.class, "conveyor");
        outtakeAngle = hardwareMap.get(Servo.class, "outtakeAngle");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Motor directions
        rightWheel.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize PedroPathing follower
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(PoseStorage.currentPose);
        telemetry.addData("Starting X", PoseStorage.currentPose.getX());
        telemetry.addData("Starting Y", PoseStorage.currentPose.getY());
        telemetry.addData("Starting Heading", PoseStorage.currentPose.getHeading());
        telemetry.update();// initial pose (only set ONCE)

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Axon position", axonPosition);
            telemetry.update();
            // ALWAYS keep follower/localizer updated
            //follower.update();

            //Pose currentPose = follower.getPose();
            //telemetry.addData("Current Pose", currentPose);

            if (movingToTarget) {
                // Pedro is driving – check if we arrived
                follower.update();
                Pose currentPose = follower.getPose();
                telemetry.addData("Current Pose", currentPose);

                double dx = currentPose.getX() - targetPose.getX();
                double dy = currentPose.getY() - targetPose.getY();
                double distToTarget = Math.hypot(dx, dy);

                telemetry.addData("Distance to target", distToTarget);

                // Stop when Pedro says path is done OR we're close enough
                if (!follower.isBusy() || distToTarget < 2.0) { // 2 units tolerance
                    movingToTarget = false;

                    // Make sure drive motors are stopped
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backLeft.setPower(0);
                    backRight.setPower(0);

                    telemetry.addData("Arrived at target", currentPose);
                }
                follower.update();

            }
            /*else {
                // Normal driver control when not following a path
                mecanumWheels();
            }
            */
            if(!movingToTarget) {
                mecanumWheels();
            }

            // Mechanisms can run in both modes
            handleIntakeAndOuttake();
            outtakeAngleControl();
            checkStartPathWithB();

            telemetry.update();
        }
    }

    // Mecanum drive
    public void mecanumWheels() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

        double frontLeftPower = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower = y - x + rx;
        double backRightPower = y + x - rx;

        double maxPower = Math.max(Math.abs(frontLeftPower),
                Math.max(Math.abs(frontRightPower),
                        Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        if (maxPower > 1.0) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }

        // Clip to [-1, 1]
        frontLeftPower = Math.max(-1, Math.min(1, frontLeftPower));
        frontRightPower = Math.max(-1, Math.min(1, frontRightPower));
        backLeftPower = Math.max(-1, Math.min(1, backLeftPower));
        backRightPower = Math.max(-1, Math.min(1, backRightPower));

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }


    // Intake / outtake
    public void handleIntakeAndOuttake() {
        double maxIntakePower = 0.7;

        double rt = gamepad2.right_trigger;   // intake in
        boolean rb = gamepad2.right_bumper;   // intake out
        double lt = gamepad2.left_trigger;    // outtake
        boolean lb = gamepad2.left_bumper;    // outtake

        double intakePower = 0.0;
        double conveyorPower = 0.0;
        double outtakeWheelPower = 0.0;

        // PRIORITY: outtake (lt) > intake in (rt) > intake out (rb)
        if (lb) {
            outtakeWheelPower = 0.8;  // set wheel speed
            leftWheel.setPower(outtakeWheelPower);
            rightWheel.setPower(outtakeWheelPower);

            intakeMotor.setPower(0);
            conveyor.setPower(0);

            telemetry.addData("Mode", "OUTTAKE WHEELS ONLY");
        }
        if (lt > 0.05) {
            // OUTTAKE: use leftWheel/rightWheel + conveyor
            outtakeWheelPower = lt * wheelSpeed;
            leftWheel.setPower(outtakeWheelPower);
            rightWheel.setPower(outtakeWheelPower);

            intakePower = 0.0;
            conveyorPower = -lt;   // feed game pieces out

            telemetry.addData("Mode", "OUTTAKE");
            telemetry.addData("Outtake trigger", lt);

        } else if (rt > 0.05) {
            // INTAKE IN: intake motor + conveyor reverse
            intakePower = rt * maxIntakePower; // full speed from trigger
            conveyorPower = -rt;

            leftWheel.setPower(0);
            rightWheel.setPower(0);

            telemetry.addData("Mode", "INTAKE IN");
            telemetry.addData("Intake trigger", rt);

        } else if (rb) {
            // INTAKE OUT (reverse)
            intakePower = -maxIntakePower; // constant speed out
            conveyorPower = 1.0;           // spit pieces out

            leftWheel.setPower(0);
            rightWheel.setPower(0);

            telemetry.addData("Mode", "INTAKE OUT (RB)");
        } else {
            // NOTHING PRESSED: stop everything
            intakePower = 0.0;
            conveyorPower = 0.0;
            leftWheel.setPower(0);
            rightWheel.setPower(0);

            telemetry.addData("Mode", "OFF");
        }

        // Actually apply powers
        intakeMotor.setPower(intakePower);
        conveyorMove(conveyorPower);

        telemetry.addData("Intake power", intakePower);
        telemetry.addData("Conveyor power", conveyorPower);
    }

    // Conveyor helper
    public void conveyorMove(double power) {
        double maxConveyorPower = 0.7;
        conveyor.setPower(power * maxConveyorPower);


    }

    // Servo control
    public void outtakeAngleControl() {
        if (gamepad2.a && !lastA) {
            axonPosition += step;
        } else if (gamepad2.y && !lastY) {
            axonPosition -= step;
        }

        axonPosition = Math.max(0, Math.min(1, axonPosition));
        outtakeAngle.setPosition(axonPosition);

        lastA = gamepad2.a;
        lastY = gamepad2.y;
    }

    // Start the path when B is first pressed
    public void checkStartPathWithB() {
        boolean justPressedB = gamepad1.b && !lastB;
        if (justPressedB && !movingToTarget) {
            //removed && !lastB
            Pose currentPose = follower.getPose();

            // Build a path from current pose to target
            BezierLine curve = new BezierLine(currentPose, targetPose);
            Path movePath = new Path(curve);
            movePath.setLinearHeadingInterpolation(currentPose.getHeading(), targetPose.getHeading());

            follower.followPath(movePath);

            outtakeAngle.setPosition(0.14);
            movingToTarget = true;

            telemetry.addData("Started moving to target", targetPose);
        }

        lastB = gamepad1.b;
    }
}
