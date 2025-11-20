package org.firstinspires.ftc.teamcode.pedroPathing;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

// PedroPathing imports
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;

@TeleOp(name = "TeleOp2", group = "test drive")
public class TeleOp1 extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private DcMotor intakeMotor;
    private CRServo conveyor;
    private Servo outtakeAngle;

    // PedroPathing objects
    private Follower follower;
    // target pose to move to when pressing B
    private final Pose targetPose = new Pose(50, 50, Math.toRadians(0)); // example target

    double wheelSpeed = 0.38;
    double axonPosition = 0;  // start centered
    double step = 0.01; // how much to move each press

    boolean lastA = false;
    boolean lastY = false;
    boolean lastB = false;  // track B button

    @Override
    public void runOpMode() {

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

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize PedroPathing follower
        follower = Constants.createFollower(hardwareMap); // make sure you have your Constants class
        follower.setStartingPose(new Pose(0, 0, 0)); // initial dummy starting pose

        telemetry.addData("Version 1", "Uploaded");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            mecanumWheels();
            intakeIn();
            intakeOut();
            outtakeOut();
            outtakeAngleControl();
            moveToTarget();  // our new function
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
            frontLeftPower /= maxPower * 2;  // scaled down
            frontRightPower /= maxPower * 2;
            backLeftPower /= maxPower * 2;
            backRightPower /= maxPower * 2;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        breakWheels();
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

    // NEW: move robot to a fixed position using PedroPathing + set outtake to 0.14
    public void moveToTarget() {
        if (gamepad2.b && !lastB) {
            telemetry.addData("position", follower.getPose());
            Pose currentPose = follower.getPose(); // your dual odometry + pinpoint
            follower.setStartingPose(currentPose);
            follower.setPose(targetPose);
            outtakeAngle.setPosition(0.14);
            telemetry.addData("position", follower.getPose());
        }
        lastB = gamepad2.b;
    }

    public void breakWheels() {
        wheelSpeed = 0;
    }
}
