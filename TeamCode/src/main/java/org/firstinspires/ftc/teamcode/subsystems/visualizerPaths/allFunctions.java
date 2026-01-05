package org.firstinspires.ftc.teamcode.subsystems.visualizerPaths;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class allFunctions extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private DcMotor intakeMotor;
    private CRServo conveyor;
    private Servo outtakeAngle;


    //private DcMotor conveyorMotor;


    double wheelSpeed = 0.38;
    double axonPosition = 0.15;  // start centered
    double step = 0.01; // how much to move each press

    boolean lastA = false;
    boolean lastY = false;

    boolean rightBumper = false;




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


        // Set the direction that the motors will be moving

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);


        // Set the zero power behavior to BRAKE for all motors

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Add telemetry data to indicate that the robot is initialized

        telemetry.addData("Version 1", "Uploaded");
        telemetry.addData("Status", "Initialized");


        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            handleIntakeAndOuttake();
            //intakeIn();
            //intakeOut();
            //outtakeOut();
            mecanumWheels();
            outtakeAngle();

        }
    }
    public void mecanumWheels() {
        double y = -gamepad1.left_stick_y; // Forward/backward movement (Y is reversed)
        double x = gamepad1.left_stick_x * 1.1; // Left/right movement (scaled to counteract imperfect strafing)
        double rx = gamepad1.right_stick_x; // Rotation

        // Calculate the power for each motor
        double frontLeftPower = (y + x + rx)  ;
        double frontRightPower = (y - x - rx) ;
        double backLeftPower = (y - x + rx) ;
        double backRightPower = (y + x - rx) ;

        double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        if (maxPower > 1.0) {
            frontLeftPower = frontLeftPower / maxPower * 0.5;
            frontRightPower = frontRightPower / maxPower * 0.5;
            backLeftPower = backLeftPower / maxPower * 0.5;
            backRightPower = backRightPower / maxPower * 0.5;
        }

        // Set the power to the motors
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

    }
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
            outtakeWheelPower = 1;  // set wheel speed
            leftWheel.setPower(outtakeWheelPower);
            rightWheel.setPower(outtakeWheelPower);

            intakeMotor.setPower(0);
            conveyor.setPower(0);

            telemetry.addData("Mode", "OUTTAKE WHEELS ONLY");
        }
        if (lt > 0.05) {
            // OUTTAKE: use leftWheel/rightWheel + conveyor
            outtakeWheelPower = -lt * wheelSpeed;
            leftWheel.setPower(-outtakeWheelPower);
            rightWheel.setPower(-outtakeWheelPower);

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
            intakePower = -0.5; // constant speed out
            conveyorPower = 0.7;           // spit pieces out

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

    /*public void intakeIn() {
        while(gamepad2.right_trigger > 0) {
            double maxIntakePower = 0.7;
            double intakePower = gamepad2.right_trigger;
            intakeMotor.setPower(intakePower * maxIntakePower);
            conveyorMove(-intakePower);

            telemetry.addData("Intake & Conveyor power: ", intakePower);
            telemetry.update();
        }
        intakeMotor.setPower(0);
        conveyor.setPower(0);
    }
    public void intakeOut() {
        while (gamepad2.right_bumper && !rightBumper) {
            double maxIntakePower = 0.7;
            double intakePower = -0.7;
            intakeMotor.setPower(intakePower * maxIntakePower);
            conveyorMove(-intakePower);

            telemetry.addData("Intake & Conveyor power: ", intakePower);
            telemetry.update();
        }
        intakeMotor.setPower(0);
        conveyor.setPower(0);

        rightBumper = gamepad2.right_bumper;
    }
    public void outtakeOut() {
        while(gamepad2.left_trigger > 0) {
            double leftWheelPower = -gamepad2.left_trigger;
            leftWheel.setPower(-leftWheelPower * wheelSpeed);
            rightWheel.setPower(-leftWheelPower * wheelSpeed);
            conveyorMove(leftWheelPower);

            telemetry.addData("Outtake & Conveyor power: ", leftWheelPower);
            telemetry.update();
        }
        leftWheel.setPower(0);
        rightWheel.setPower(0);
        conveyor.setPower(0);
    }
    */

    public void conveyorMove(double power) {
        double maxConveyorPower = 0.7; // <-- set your desired max power
        //conveyorMotor.setPower(intakePower * maxConveyorPower);
        conveyor.setPower(power * maxConveyorPower);
    }

    public void outtakeAngle (){
        if (gamepad2.a && !lastA) {
            axonPosition += step;
        } else if (gamepad2.y && !lastY) {
            axonPosition -= step;
        }

        // Clamp servo position between 0 and 1
        axonPosition = Math.max(0, Math.min(1, axonPosition));

        // Update servo
        outtakeAngle.setPosition(axonPosition);

        // Save button states for next loop
        lastA = gamepad2.a;
        lastY = gamepad2.y;

        telemetry.addData("Axon Position", axonPosition);
        telemetry.update();
    }

}
