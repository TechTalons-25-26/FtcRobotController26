package org.firstinspires.ftc.teamcode.teleOp.blue;


//import org.firstinspires.ftc.teamcode.pedroPathing;


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
import com.sun.tools.javac.tree.DCTree;


import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.subsystems.path.poseStorage;


@TeleOp(name = "blueTeleOp2")
public class blueTeleOp2 extends LinearOpMode {


    private DcMotor frontLeft, frontRight, backLeft, backRight;
    //private DcMotor leftWheel, rightWheel;
    private DcMotor outtake;
    //INTAKE MOTOR 1 IS FOR THE FIRST STAGE OKAY
    private DcMotor intake;
    //INTAKE MOTOR 2 IS FOR THE FIRST STAGE OKAY
    private DcMotor stage;


    private CRServo parkingPlate;


    //private CRServo conveyor;
    //private Servo outtakeAngle;


    //private Servo lid;
    // private CRServo parkingPlate;




    private Follower follower;

    double outtakePower = 0.4;
    double wheelSpeed = 0.4;
    double axonPosition = 0.14; // start centered
    double step = 0.01; // servo step


    private boolean movingToTarget = false;
    boolean lastX = false;
    boolean lastY = false;
    boolean lastB = false; // track B button


    // target pose for pressing B (make sure units match your field config)
    private final Pose targetPose = new Pose(83.959, 86.004, Math.toRadians(-2.226)); // example target


    @Override
    public void runOpMode() {


        // Hardware mapping
        outtake = hardwareMap.get(DcMotor.class, "outtake");
        //leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        //rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        intake = hardwareMap.get(DcMotor.class, "intake");
        stage = hardwareMap.get(DcMotor.class, "stage");
        //conveyor = hardwareMap.get(CRServo.class, "conveyor");
        parkingPlate = hardwareMap.get(CRServo.class, "parkingPlate");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");


        // Motor directions
        //rightWheel.setDirection(DcMotor.Direction.REVERSE);
        outtake.setDirection(DcMotor.Direction.REVERSE);
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
        follower.setStartingPose(poseStorage.currentPose);
        telemetry.addData("Starting X", poseStorage.currentPose.getX());
        telemetry.addData("Starting Y", poseStorage.currentPose.getY());
        telemetry.addData("Starting Heading", poseStorage.currentPose.getHeading());
        telemetry.update();// initial pose (only set ONCE)





        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stage.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //parkingPlate.setPosition(axonPosition);


        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {
            //follower.update();
            //outtakeWheel.setPower(outtakePower);


            // ALWAYS keep follower/localizer updated
            //follower.update();


            //Pose currentPose = follower.getPose();
            //telemetry.addData("Current Pose", currentPose);


            if (movingToTarget) {
                // Pedro is driving – check if we arrived
                follower.update();
                Pose currentPose = follower.getPose();
                telemetry.addData("Current Pose", currentPose);


                double dx = currentPose.getX() - targetPose.getX();
                double dy = currentPose.getY() - targetPose.getY();
                double distToTarget = Math.hypot(dx, dy);


                telemetry.addData("Distance to target", distToTarget);
                telemetry.addData("Current X", currentPose.getX());
                telemetry.addData("Current Y", currentPose.getY());
                //telemetry.addData("Current Heading", PoseStorage.currentPose.getHeading());


                // Stop when Pedro says path is done OR we're close enough
                if (!follower.isBusy() || distToTarget < 2.0) { // 2 units tolerance
                    movingToTarget = false;


                    // Make sure drive motors are stopped
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backLeft.setPower(0);
                    backRight.setPower(0);


                    telemetry.addData("Arrived at target", currentPose);
                }
                follower.update();


            }
           /*else {
               // Normal driver control when not following a path
               mecanumWheels();
           }
           */
            if(!movingToTarget) {
                mecanumWheels();
            }


            // Mechanisms can run in both modes
            handleIntakeAndOuttake();
            //outtakeAngleControl();
            outtakeTest();
            //openCloseLid();
            checkStartPathWithB();


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




    // Intake / outtake
    public void handleIntakeAndOuttake() {
        double maxIntakePower1 = 1.0;
        double maxIntakePower2 = 1.0;


        double rt = gamepad2.right_trigger;   // intake in
        boolean rb = gamepad2.right_bumper;   // intake out
        double lt = gamepad2.left_trigger;    // outtake
        boolean lb = gamepad2.left_bumper;    // outtake ramp
        //double parking = gamepad1.right_trigger;

        double intakePower = 0.0;
        double parkingPlatePower = gamepad1.right_trigger;
        double outtakeWheelPower = 0.0;

        if (rt > 0.05) {
            // INTAKE IN: intake motor + conveyor reverse
            maxIntakePower1 = rt * maxIntakePower1; // full speed from trigger
            maxIntakePower2 = rt * (-maxIntakePower2);
            //conveyorPower = -rt;


            //leftWheel.setPower(0);
            //rightWheel.setPower(0);
            //outtakeWheel.setPower(0);


            telemetry.addData("Mode", "INTAKE IN");
            telemetry.addData("Intake trigger", rt);


        } else if (lt > 0.05) {
            maxIntakePower1 = lt * maxIntakePower1;

            maxIntakePower2 = lt * maxIntakePower2;


        }  else if (rb) {
            // INTAKE OUT (reverse)
            maxIntakePower1 = -maxIntakePower1; // constant speed out
            //conveyorPower = 1.0;           // spit pieces out
            maxIntakePower2 = -maxIntakePower2;


            //leftWheel.setPower(0);
            //rightWheel.setPower(0);
            //outtakeWheel.setPower(0);


            telemetry.addData("Mode", "INTAKE OUT (RB)");
        }
        else {
            // NOTHING PRESSED: stop everything
            maxIntakePower1 = 0.0;
            maxIntakePower2 = 0.0;
            //conveyorPower = 0.0;
            //leftWheel.setPower(0);
            //rightWheel.setPower(0);
            //outtakeWheel.setPower(0);


            telemetry.addData("Mode", "OFF");
        }


        // Actually apply powers
        intake.setPower(maxIntakePower1);
        stage.setPower(maxIntakePower2);
        //  moveParkingPlate(parkingPlatePower);
        //conveyorMove(conveyorPower);


        telemetry.addData("Intake power 1", maxIntakePower1);
        telemetry.addData("Intake power 2", maxIntakePower2);

        //telemetry.addData("Conveyor power", conveyorPower);
    }

    public void outtakeTest (){
        if (gamepad2.left_trigger > 0.05){
            outtake.setPower(1.0);
        }
        else if (gamepad2.left_bumper){
            outtake.setPower(1.0);
        }
        else{
            outtake.setPower(0);
        }
    }


   public void moveParkingPlate(double power) {
        double maxConveyorPower = 0.7;
        parkingPlate.setPower(power * maxConveyorPower);


    }

    // Start the path when B is first pressed
    public void checkStartPathWithB() {
        boolean justPressedB = gamepad1.b && !lastB;
        if (justPressedB && !movingToTarget) {
            //removed && !lastB
            Pose currentPose = follower.getPose();


            // Build a path from current pose to target
            BezierLine curve = new BezierLine(currentPose, targetPose);
            Path movePath = new Path(curve);
            movePath.setLinearHeadingInterpolation(currentPose.getHeading(), targetPose.getHeading());


            follower.followPath(movePath);


            //outtakeAngle.setPosition(0.14);
            movingToTarget = true;


            telemetry.addData("Started moving to target", targetPose);
        }


        lastB = gamepad1.b;
    }
}

