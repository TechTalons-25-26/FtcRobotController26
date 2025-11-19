package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;

import java.util.List;


@Autonomous(name = "Detect Tag 21-22-23 (OpMode)")
public class limelightSetup extends OpMode {

    private Limelight3A limelight;

    private LLResult result;
    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.start();
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        if (result != null && result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fiducial : fiducials) {
                int id = fiducial.getFiducialId(); // The ID number of the fiducial
                telemetry.addData("AprilTag", id);
            }
        }
    }
}