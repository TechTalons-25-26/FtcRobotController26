// code for the intake wheels as well
package org.firstinspires.ftc.teamcode.oldCode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Backup Teleop (Don't Run)", group = "A")
public class intoTheDeepBackupTeleOp extends LinearOpMode {
    /*All DcMotors*/
    private DcMotor intakeSlide;
    private DcMotor bucketSlide;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor leftActuator; 
    private DcMotor rightActuator;
    
    // All servos
    private Servo specimenServo;
    private Servo wristServo;
    private Servo redServo;
    private Servo blueServo;
    private Servo bucketServo;


    // global variables
    int c = 0;
    int currentPosition = 0;
    double wristPosition = 0.0;
    double redPosition = 0.5;
    double bluePosition = 0.5;

    private boolean previousXState = false;
    boolean actuatorExtended = false;

    private boolean bucketDump = false;


    public void runOpMode() {
        // Initialize robot motors
        robotSetup();
        waitForStart();


        // reset encoders
        intakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bucketSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucketSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive()) {
            moveBucketSlide(11600, 15);
            moveIntakeSlide(2050, 15);
            clip();
            wristMovement(0.005);
            //intakeWheels(0.38, 0.26);
            moveLinearSlideAuto(10000, 1.0);
            mecanumDrive();
            incrementalWrist(0.02);
            intake();
            actuatorHang();
            actuatorDown();


        }
    }
    public void robotSetup() {
        // Initialize motors for driving
        intakeSlide = hardwareMap.get(DcMotor.class, "intakeSlide");
        bucketSlide = hardwareMap.get(DcMotor.class, "bucketSlide");


        bucketSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // initialize servos
        specimenServo = hardwareMap.get(Servo.class, "specimenServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        redServo = hardwareMap.get(Servo.class, "redServo");
        blueServo = hardwareMap.get(Servo.class, "blueServo");
        bucketServo = hardwareMap.get(Servo.class, "bucketServo");

        // initalize wheel motors 
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        leftActuator = hardwareMap.get(DcMotor.class, "leftActuator");
        rightActuator = hardwareMap.get(DcMotor.class, "rightActuator");


        // initialize claw & wrist position
        initpos();


    }

    public void initpos() {
        //telemetry.addLine("Initialization has begun.");
        // initialize the specimen position
        specimenServo.setPosition(1.0);
        // initialize rolling intake positions
        wristServo.setPosition(0.3);
        redServo.setPosition(redPosition);
        blueServo.setPosition(bluePosition);
        bucketServo.setPosition(0.0);

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
    }
    public void mecanumDrive() {
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

        // Optionally, add telemetry data to provide feedback to the driver station
        //telemetry.addData("Front Left Power", frontLeftPower);
        //telemetry.addData("Front Right Power", frontRightPower);
        //telemetry.addData("Back Left Power", backLeftPower);
        //telemetry.addData("Back Right Power", backRightPower);
        //telemetry.update();
    }

    public void incrementalWrist(double wristSpeed){
        if (gamepad2.y) {
            wristPosition -= wristSpeed;
            wristPosition = Math.min(wristPosition, 1.0);
            wristServo.setPosition(wristPosition);
        }
        if (gamepad2.a) {
            wristPosition += wristSpeed;
            wristPosition = Math.max(wristPosition, 0.0);
            wristServo.setPosition(wristPosition);
        }
        if (gamepad2.x){
            bucketServo.setPosition(0.4);
        }
        if (gamepad2.b){
            bucketServo.setPosition(0.0);
        }
    }

    public void moveBucketSlide(final int maximum, final int tolerance) {   // Get the current encoder position of the slide
        c = bucketSlide.getCurrentPosition();
        //telemetry.addData("Current Position", c);
        //telemetry.update();
        if ((c >= maximum - tolerance) && (gamepad2.right_stick_y < 0)) {
            // Allow small adjustments to bring the slide back within the range
            bucketSlide.setPower(0);
        } else if (c <= 50 && gamepad2.right_stick_y > 0) {
            // Prevent the slide from moving below its minimum position
            bucketSlide.setPower(0);
        } else {
            // Move the slide based on joystick input
            bucketSlide.setPower(-gamepad2.right_stick_y);
        }
    }

    public void moveIntakeSlide(final int Max, final int Tol) {   // Get the current encoder position of the slide
        currentPosition = intakeSlide.getCurrentPosition();
        telemetry.addData("Rolling Intake Slide Position: ", currentPosition);
        telemetry.update();
        if (currentPosition >= Max - Tol && gamepad2.left_stick_y < 0) {
            // Allow small adjustments to bring the slide back within the range
            intakeSlide.setPower(0);
        } else if (currentPosition <= 50 && gamepad2.left_stick_y > 0) {
            // Prevent the slide from moving below its minimum position
            intakeSlide.setPower(0);
        } else {
            // Move the slide based on joystick input
            intakeSlide.setPower(-gamepad2.left_stick_y);
        }
        // July 16th night change
        /*if (gamepad2.dpad_down){
            // initialize wrist
            wristServo.setPosition(0.01);// change this to lower position
            intakeSlide.setTargetPosition(300); // go back to base
            intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeSlide.setPower(0.6);
            while (opModeIsActive() && intakeSlide.isBusy()) {
                //telemetry.addData("Intake Slide goes back!", intakeSlide.getCurrentPosition());
                //telemetry.update();
            }
            intakeSlide.setPower(0);
            intakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }*/
    }

    public void clip() {
        int r = intakeSlide.getCurrentPosition();
        if (gamepad2.left_bumper) {
            //telemetry.addLine("Bumper pressed.");
            //telemetry.update();
            specimenServo.setPosition(0.4);
        } else {
            specimenServo.setPosition(1.0);
        }
    }
    public void actuatorHang() {
        int t = rightActuator.getCurrentPosition();
        int s = leftActuator.getCurrentPosition();
        //telemetry.addData("Right Position", t);
        //telemetry.addData("Left Position", s);
        //telemetry.update();

        if (gamepad1.right_trigger > 0.5 && !actuatorExtended) {
            rightActuator.setTargetPosition(-10950); // Or 'maximum' if you want dynamic range
            rightActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightActuator.setPower(1.0);
            
            leftActuator.setTargetPosition(-11000); // Or 'maximum' if you want dynamic range
            leftActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftActuator.setPower(1.0);

            while (opModeIsActive() && rightActuator.isBusy()) {
                //telemetry.addData("Extending Actuator", rightActuator.getCurrentPosition());
                //telemetry.update();
            }

            rightActuator.setPower(0); // Stop motor after reaching target
            rightActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            
            leftActuator.setPower(0); // Stop motor after reaching target
            leftActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            actuatorExtended = true;
        }

        // Reset flag if trigger is released
        if (gamepad1.right_trigger < 0.1) {
            actuatorExtended = false;
        }
        
        
    }
    
    public void actuatorDown() {
        int t = rightActuator.getCurrentPosition();
        int s = leftActuator.getCurrentPosition();

        if (gamepad1.left_trigger > 0.5) {
            rightActuator.setTargetPosition(11050); // Or 'maximum' if you want dynamic range
            rightActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightActuator.setPower(1.0);
            
            leftActuator.setTargetPosition(11000); // Or 'maximum' if you want dynamic range
            leftActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftActuator.setPower(1.0);

            while (opModeIsActive() && rightActuator.isBusy()) {
                //telemetry.addData("Extending Actuator", rightActuator.getCurrentPosition());
                //telemetry.update();
            }

            rightActuator.setPower(0); // Stop motor after reaching target
            rightActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            
            leftActuator.setPower(0); // Stop motor after reaching target
            leftActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            actuatorExtended = true;
        }

        // Reset flag if trigger is released
        if (gamepad1.right_trigger < 0.1) {
            actuatorExtended = false;
        }
        
        
    }
    
    public void wristMovement(double wristSpeed) {
        if (gamepad2.left_stick_button) {
            wristPosition = 0.094;//changed it fro .0978 
            //wristPosition = wristPosition + wristSpeed;
            // Ensure servo positions are within valid range
            wristPosition = Math.min(Math.max(wristPosition, 0), 1);
            // adjust the servo accordingly
            wristServo.setPosition(wristPosition);
        }
        if (gamepad2.right_stick_button) {
            wristPosition = 0.0;
            //wristPosition = wristPosition - wristSpeed;
            // Ensure servo positions are within valid range
            wristPosition = Math.min(Math.max(wristPosition, 0), 1);


            // adjust the servo accordingly
            wristServo.setPosition(wristPosition);


        }


        //telemetry.addData("Wrist Position", wristPosition);
        //telemetry.update();
    }
    public void intake() {
        if (gamepad2.left_trigger > 0.0) {
            //telemetry.addLine("Bumper pressed.");
            //telemetry.update();
            blueServo.setPosition(0.2); // could be zero
        } else {
            blueServo.setPosition(0.8);
        }
    }

    /*public void intakeWheels(double redSpeed, double blueSpeed) {
        if (gamepad2.left_trigger > 0.0) {
            redPosition += redSpeed;
            bluePosition -= blueSpeed;
        } else if (gamepad2.right_trigger > 0.0) {
            redPosition -= redSpeed;
            bluePosition += blueSpeed;
        } else {
            redPosition = 0.5;
            bluePosition = 0.5;
        } //0.5 is stop position for Continuous servo
        blueServo.setPosition(bluePosition);
        redServo.setPosition(redPosition);
        //telemetry.addData("Position of blue: ", bluePosition);
        //telemetry.addData("Position of red: ", redPosition);
        //telemetry.update();
    }*/

    // REMEMBER ITS THE RIGHT STICK I FIXED JUST PUT BACK
    public void moveLinearSlideAuto(int ticks, double power) {
        if ((gamepad2.dpad_up) && (previousXState == false)) {
            wristServo.setPosition(0.015);
            previousXState = true; // first X press
            bucketSlide.setTargetPosition(ticks);
            bucketSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bucketSlide.setPower(power);
            while (opModeIsActive() && bucketSlide.isBusy()) {
                //telemetry.addData("Bucket Slide goes up!", bucketSlide.getCurrentPosition());
                //telemetry.update();
            }
            bucketSlide.setPower(1.0);// change
            //bucketSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bucketServo.setPosition(0.8); // dump
            bucketSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if ((gamepad2.dpad_up) && (previousXState == true)) { // second X press
            int threshold = 100;
            // telemetry.addData("Button pressed again!", bucketSlide.getCurrentPosition());
            //telemetry.update();

            bucketServo.setPosition(0.0);
            bucketSlide.setTargetPosition(0); // go back to base
            bucketSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bucketSlide.setPower(power);
            while (opModeIsActive() && bucketSlide.isBusy()) {
                //telemetry.addData("Bucket Slide goes down!", bucketSlide.getCurrentPosition());
                //telemetry.update();
            }
            bucketSlide.setPower(0);
            bucketSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bucketSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            previousXState = false;

        }
    }


}
