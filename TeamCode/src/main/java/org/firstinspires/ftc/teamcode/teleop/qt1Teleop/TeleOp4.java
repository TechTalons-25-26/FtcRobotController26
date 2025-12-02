package org.firstinspires.ftc.teamcode.teleop.qt1Teleop;

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

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp(name = "TeleOp4", group = "test drive")
public class TeleOp4 extends LinearOpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor leftWheel, rightWheel;
    private DcMotor intakeMotor;
    private CRServo conveyor;
    private Servo outtakeAngle;

    private Follower follower;




    double wheelSpeed = 0.38;
    double axonPosition = 0; // start centered
    double step = 0.01; // servo step
    private boolean movingToTarget = false;
    boolean lastA = false;
    boolean lastY = false;
    boolean lastB = false; // track B button

    // target pose for pressing B
    private final Pose targetPose = new Pose(43.570, 99.198, Math.toRadians(143)); // example target

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
        follower.setStartingPose(new Pose(0, 0, 0)); // initial pose

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            Pose currentPose = follower.getPose();
            telemetry.addData("Current Pose", currentPose);
            telemetry.update();
            mecanumWheels();
            intakeIn();
            intakeOut();
            outtakeOut();
            outtakeAngleControl();
            moveToTargetB();
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

        double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        if (maxPower > 1.0) {
            frontLeftPower /= maxPower * 2;
            frontRightPower /= maxPower * 2;
            backLeftPower /= maxPower * 2;
            backRightPower /= maxPower * 2;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    // Intake In
    public void intakeIn() {
        if (gamepad2.right_trigger > 0) {
            double intakePower = 0.7 * gamepad2.right_trigger;
            intakeMotor.setPower(intakePower);
            conveyorMove(-intakePower);
        } else {
            intakeMotor.setPower(0);
            conveyor.setPower(0);
        }
    }

    // Intake Out
    public void intakeOut() {
        if (gamepad2.right_bumper) {
            double intakePower = -0.7;
            intakeMotor.setPower(intakePower);
            conveyorMove(-intakePower);
        } else {
            intakeMotor.setPower(0);
            conveyor.setPower(0);
        }
    }

    // Outtake
    public void outtakeOut() {
        if (gamepad2.left_trigger > 0) {
            double power = -gamepad2.left_trigger;
            leftWheel.setPower(power * wheelSpeed);
            rightWheel.setPower(-power * wheelSpeed);
            conveyorMove(power);
        } else {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
            conveyor.setPower(0);
        }
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

    // NEW: Move robot to target pose when pressing B
    // NEW: Move robot to target pose when pressing B
    public void moveToTargetB() {
        // Start the path when B is first pressed
        if (gamepad2.b && !lastB && !movingToTarget) {
            Pose currentPose = follower.getPose();
            follower.setStartingPose(currentPose);

            BezierLine curve = new BezierLine(currentPose, targetPose);
            Path movePath = new Path(curve);
            movePath.setLinearHeadingInterpolation(currentPose.getHeading(), targetPose.getHeading());

            follower.followPath(movePath);

            outtakeAngle.setPosition(0.14);
            movingToTarget = true;

            telemetry.addData("Started moving to target", targetPose);
            telemetry.update();
        }

        // Update the follower if we are moving
        if (movingToTarget) {
            follower.update();
            if (!follower.isBusy()) {
                movingToTarget = false;
                telemetry.addData("Arrived at target", follower.getPose());
                telemetry.update();
            }
        }

        lastB = gamepad2.b;
    }


}
