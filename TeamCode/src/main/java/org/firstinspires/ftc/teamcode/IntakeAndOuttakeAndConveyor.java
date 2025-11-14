package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake/Outtake with Conveyor", group = "test drive")
public class IntakeAndOuttakeAndConveyor extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private DcMotor intakeMotor;
    private Servo axon;

    private DcMotor conveyorMotor;


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
        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
      //  axon = hardwareMap.get(Servo.class, "axon");
    
        rightWheel.setDirection(DcMotor.Direction.REVERSE);
        /*

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
*/

        // Set the direction that the motors will be moving
        /*
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
*/

        // Set the zero power behavior to BRAKE for all motors
        /*
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        */

        // Add telemetry data to indicate that the robot is initialized
        /*
        telemetry.addData("Version 1", "Uploaded");
        telemetry.addData("Status", "Initialized");
        */

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        conveyorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();

        while (opModeIsActive()) {
            /*
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
 */


            // Wheels spin using left stick
            while(gamepad2.left_stick_y > 0) {
                double leftWheelPower = -gamepad2.left_stick_y;
                leftWheel.setPower(leftWheelPower * wheelSpeed);
                leftWheel.setPower(leftWheelPower * wheelSpeed);
                double maxConveyorPower = 0.5; // <-- set your desired max power
                conveyorMotor.setPower(leftWheelPower * maxConveyorPower);

                telemetry.addData("Outtake & Conveyor power: ", leftWheelPower);
                telemetry.update();
            }
            
            double drive = -gamepad2.left_stick_y;
            leftWheel.setPower(drive * wheelSpeed);
            rightWheel.setPower(drive * wheelSpeed);

             
            //Intake spins with right stick
            while(gamepad2.right_stick_y > 0 || gamepad2.right_stick_y <= 0) {
                double maxIntakePower = 0.5;
                double intakePower = -gamepad2.right_stick_y;
                intakeMotor.setPower(intakePower * maxIntakePower);
                double maxConveyorPower = 0.5; // <-- set your desired max power
                conveyorMotor.setPower(intakePower * maxConveyorPower);

                telemetry.addData("Intake & Conveyor power: ", intakePower);
                telemetry.update();
            }

          /*  double intakePower = -gamepad2.right_stick_y;
            double maxIntakePower = 0.5; // <-- set your desired max power
            intakeMotor.setPower(intakePower * maxIntakePower);

            double conveyorPower = -gamepad2.right_stick_y;
            double maxConveyorPower = 0.5; // <-- set your desired max power
            conveyorMotor.setPower(conveyorPower * maxConveyorPower);
          */

           /* telemetry.addData("Intake Power", intakePower);
            telemetry.update();
            */

            // Increment servo position on button press
            /*
            if (gamepad2.a && !lastA) {
                axonPosition += step;
            } else if (gamepad2.y && !lastY) {
                axonPosition -= step;
            }

            // Clamp servo position between 0 and 1
            axonPosition = Math.max(0, Math.min(1, axonPosition));

            // Update servo
            axon.setPosition(axonPosition);

            // Save button states for next loop
            lastA = gamepad2.a;
            lastY = gamepad2.y;

            telemetry.addData("Axon Position", axonPosition);
            //telemetry.addData("Drive Power", drive);
            telemetry.update();
*/

        }
    }

}
