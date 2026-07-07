package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Lemonade Stand Code (Wheels & Intake)")
public class lemonadeStand extends LinearOpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    //private DcMotor leftWheel, rightWheel;
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
        //stage.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {
            mecanumWheels();
            handleIntake();





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
