package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drive.mecanum;
import org.firstinspires.ftc.teamcode.subsystems.intake.intakeLogic;
import org.firstinspires.ftc.teamcode.subsystems.outtake.manualOuttake;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtakeLogic;

public class robot {

    // -------------------------
    // Subsystems
    // -------------------------
    public mecanum drive;
    public intakeLogic intake;
    public outtakeLogic outtake;
    public manualOuttake manualOuttake;

    private final HardwareMap hardwareMap;

    public robot(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // -------------------------
    // Initialize all subsystems
    // -------------------------
    public void init() {
        drive = new mecanum(hardwareMap);

        intake = new intakeLogic();
        intake.init(hardwareMap);

        outtake = new outtakeLogic();
        outtake.init(hardwareMap);

        manualOuttake = new manualOuttake();
        manualOuttake.init(hardwareMap);

    }

    public void start() {
        outtake.start();
    }

    // -------------------------
    // Update all subsystems
    // -------------------------
    public void update() {
        outtake.update();
    }
}