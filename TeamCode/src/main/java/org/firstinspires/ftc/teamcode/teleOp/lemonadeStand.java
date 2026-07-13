package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Lemonade Stand Code (Wheels & Intake)")
public class lemonadeStand extends LinearOpMode{

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor intake;

    //INTAKE MOTOR IS AT PORT TWO FOR CONFIG

    @Override
    public void runOpMode() {


        // Hardware mapping
        intake = hardwareMap.get(DcMotor.class, "intake");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        IMU imu = hardwareMap.get(IMU.class, "imu");

        // Motor directions
        //rightWheel.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);


        // Zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // IMU stuff for field centric

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);



            handleIntake();

            telemetry.update();
        }
    }

    // Intake
    public void handleIntake() {
        double maxIntakePower1 = 1.0;


        double rt = gamepad2.right_trigger;   // intake in
        boolean rb = gamepad2.right_bumper;   // intake out

        double intakePower = 0.0;

        if (rt > 0.05) {
            // INTAKE IN: intake motor + conveyor reverse
            maxIntakePower1 = rt * maxIntakePower1; // full speed from trigger

            telemetry.addData("Mode", "INTAKE IN");
            telemetry.addData("Intake trigger", rt);

        }  else if (rb) {
            // INTAKE OUT (reverse)
            // Why not left trigger? To control speed? And make it uniform?
            maxIntakePower1 = -maxIntakePower1; // constant speed out

            telemetry.addData("Mode", "INTAKE OUT (RB)");
        }
        else {
            maxIntakePower1 = 0.0;
            telemetry.addData("Mode", "OFF");
        }


        // Actually apply powers
        intake.setPower(maxIntakePower1);

        telemetry.addData("Intake power 1", maxIntakePower1);
    }

}