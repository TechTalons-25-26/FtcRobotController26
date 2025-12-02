// Package declaration
package org.firstinspires.ftc.teamcode.subsystems;

// Import necessary classes from the FTC SDK
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Mecanum Drive Test", group="Iterative Opmode")

public class Mecanum_Drive extends OpMode {
    // initialize motors
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;


    // Override the init() method from the OpMode class
    @Override
    public void init() {
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
    }

    // Override the loop() method from the OpMode class
    @Override
    public void loop() {
        // By inverting the Y-axis, the robot behaves in the same way that the driver expects based
        // on their physical interaction with the gamepad.
        double y = -gamepad1.left_stick_y; // Forward/backward movement (Y is reversed)
        double x = gamepad1.left_stick_x * 1.1; // Left/right movement (scaled to counteract imperfect strafing)
        double rx = gamepad1.right_stick_x; // Rotation

        // Calculate the power for each motor
        double frontLeftPower = (y + x + rx)  ;
        double frontRightPower = (y - x - rx) ;
        double backLeftPower = (y - x + rx) ;
        double backRightPower = (y + x - rx) ;

        double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        if (maxPower > 0.85) {
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

        // Optionally, add telemetry data to provide feedback to the driver station
        telemetry.addData("Front Left Power:", frontLeftPower);
        telemetry.addData("Front Right Power:", frontRightPower);
        telemetry.addData("Back Left Power:", backLeftPower);
        telemetry.addData("Back Right Power:", backRightPower);

        telemetry.update();
    }
}


