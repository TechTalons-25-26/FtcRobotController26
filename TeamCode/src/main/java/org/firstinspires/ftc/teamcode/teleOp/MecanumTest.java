package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@TeleOp(name = "hahHAHHSHHAHHAHSAHGHAH", group = "test drive")
public class MecanumTest extends LinearOpMode {
    // All DcMotors
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private DcMotor BackLeft;
    private DcMotor BackRight;
    
    // global variables
    double y, x, rx;
    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;


    public void runOpMode() {
        // Initialize robot motors
        robotSetup();


        waitForStart();

        




        while (opModeIsActive()) {
            mecanumDrive();
        }
    }
    public void robotSetup() {
        // Initialize motors for driving
        FrontLeft = hardwareMap.get(DcMotor.class, "front_left");
        FrontRight = hardwareMap.get(DcMotor.class, "front_right");
        BackLeft = hardwareMap.get(DcMotor.class, "back_left");
        BackRight = hardwareMap.get(DcMotor.class, "back_right");


        // Set direction for motors
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.REVERSE);


        // Set initial power to all motors to zero
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);


        // Allows them to stop immediately
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        




    }
    public void mecanumDrive(){
        y = -gamepad1.left_stick_y; // Forward/backward movement (Y is reversed)
        x = gamepad1.left_stick_x * 1.1; // Left/right movement (scaled to counteract imperfect strafing)
        rx = gamepad1.right_stick_x; // Rotation
        // Calculate the power for each motor
        frontLeftPower = (y + x + rx)  ;
        frontRightPower = (y - x - rx) ;
        backLeftPower = (y - x + rx) ;
        backRightPower = (y + x - rx) ;
        double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));
        double factor = 1.0;
        if (maxPower > 1.0) {
            frontLeftPower = frontLeftPower / maxPower * factor;
            frontRightPower = frontRightPower / maxPower * factor;
            backLeftPower = backLeftPower / maxPower * factor;
            backRightPower = backRightPower / maxPower * factor;
        }


        // Set the power to the motors
        FrontLeft.setPower(frontLeftPower);
        FrontRight.setPower(frontRightPower);
        BackLeft.setPower(backLeftPower);
        BackRight.setPower(backRightPower);


    }
    
}

