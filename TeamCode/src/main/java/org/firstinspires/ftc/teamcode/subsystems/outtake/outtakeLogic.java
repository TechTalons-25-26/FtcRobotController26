package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class outtakeLogic {
    private DcMotorEx outtakeMotor;
    private Servo gateServo;
    private ElapsedTime outtakeTimer = new ElapsedTime();
    OuttakeState outtakeState;

    public enum OuttakeState {
        IDLE,
        SPIN_UP,
        LAUNCH,
        RESET_GATE
    }

    //TODO: TUNE THESE
    //----------- Gate Constants ----------
    private double gateCloseAngle = 0;
    private double gateOpenAngle = 90;
    private double gateOpenTime = 0.5;
    private double gateCloseTime = 0.5;

    //TODO: TUNE THESE
    //----------- Outtake Constants--------
    private int shotsRemaining = 0;
    private double outtakeVelocity = 0;
    private double minOuttakeRPM = 800;
    private double targetOuttakeRPM = 1100;
    private double maxOuttakeSpinupTime = 2;

    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        gateServo = hardwareMap.get(Servo.class, "gate");

        setOuttakeState(OuttakeState.IDLE);

        outtakeMotor.setPower(0);
        gateServo.setPosition(gateCloseAngle);

    }

    public void update() {
        switch (outtakeState) {
            case IDLE:
                if (shotsRemaining > 0) {
                    gateServo.setPosition(gateCloseAngle);
                    outtakeTimer.reset();
                    //set velocity
                    outtakeMotor.setPower(targetOuttakeRPM);
                    setOuttakeState(OuttakeState.SPIN_UP);
                }
                break;

            case SPIN_UP:
                //set velocity
                outtakeTimer.reset();
                if (outtakeVelocity > minOuttakeRPM || outtakeTimer.seconds() > maxOuttakeSpinupTime) {
                    gateServo.setPosition(gateOpenAngle);
                    outtakeTimer.reset();

                    setOuttakeState(OuttakeState.LAUNCH);
                }
                break;

            case LAUNCH:
                if (outtakeTimer.seconds() > gateOpenTime) {
                    shotsRemaining--; //increment by 1
                    gateServo.setPosition(gateCloseAngle);
                    outtakeTimer.reset();

                    setOuttakeState(OuttakeState.RESET_GATE);
                }
                break;

            case RESET_GATE:
                if (outtakeTimer.seconds() > gateCloseTime) {
                    if (shotsRemaining > 0) {
                        outtakeTimer.reset();
                        setOuttakeState(OuttakeState.SPIN_UP);
                    } else {
                        outtakeMotor.setPower(0);
                        setOuttakeState(OuttakeState.IDLE);
                    }
                }
                break;
        }
    }

    public void fireShots(int numShots) {
        if (outtakeState.equals(OuttakeState.IDLE)) {
            shotsRemaining = numShots;
        }
    }

    public boolean isBusy() {
        return outtakeState != OuttakeState.IDLE;
    }

    public void setOuttakeState(OuttakeState newState) {
        outtakeState = newState;
    }


}