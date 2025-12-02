package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Intake/Outtake Functions", group = "test drive")
public class IntakeAndOuttakeFunctions extends LinearOpMode {

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
    double axonPosition = 0.8;  // start centered
    double step = 0.01; // how much to move each press

    boolean lastA = false;
    boolean lastY = false;

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
           intakeIn();
           intakeOut();
           outtakeOut();

        }
    }
    public void intakeIn() {
        while(gamepad2.right_trigger > 0) {
            double maxIntakePower = 0.5;
            double intakePower = -gamepad2.right_trigger;
            intakeMotor.setPower(intakePower * maxIntakePower);
            conveyor(intakePower);

            telemetry.addData("Intake & Conveyor power: ", intakePower);
            telemetry.update();
        }
    }
    public void intakeOut() {
        while(gamepad2.right_bumper) {
            double maxIntakePower = 0.5;
            double intakePower = 0.5;
            intakeMotor.setPower(intakePower * maxIntakePower);
            conveyor(intakePower);

            telemetry.addData("Intake & Conveyor power: ", intakePower);
            telemetry.update();
        }
    }
    public void outtakeOut() {
        while(gamepad2.left_trigger > 0) {
            double leftWheelPower = -gamepad2.left_stick_y;
            leftWheel.setPower(leftWheelPower * wheelSpeed);
            leftWheel.setPower(leftWheelPower * wheelSpeed);
            conveyor(leftWheelPower);

            telemetry.addData("Outtake & Conveyor power: ", leftWheelPower);
            telemetry.update();
        }
    }
    public void conveyor(double power) {
        double maxConveyorPower = 0.5; // <-- set your desired max power
        //conveyorMotor.setPower(intakePower * maxConveyorPower);
        conveyor.setPower(power * maxConveyorPower);
    }

}
