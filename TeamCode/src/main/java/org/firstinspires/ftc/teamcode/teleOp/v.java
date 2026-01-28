package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Encoder Ticks Per Rev Test")
public class v extends OpMode {

    private DcMotorEx outtake;

    @Override
    public void init() {
        outtake = hardwareMap.get(DcMotorEx.class, "outtake");

        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Rotate shaft exactly 1 revolution by hand");
        telemetry.addLine("Press A to reset encoder");
        telemetry.update();
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        int ticks = outtake.getCurrentPosition();

        telemetry.addData("Encoder Ticks", ticks);
        telemetry.addLine("Turn shaft exactly 1 full rev");
        telemetry.update();
    }
}
