package org.firstinspires.ftc.teamcode.drivecode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Drive Code Testing", group="Linear Opmode")

public class DriveCodeTester extends LinearOpMode{
    private ZeroPowerBehavior brake = ZeroPowerBehavior.BRAKE;
    private ZeroPowerBehavior floatt = ZeroPowerBehavior.FLOAT;
    private DcMotor frontLeft, frontRight, backLeft, backRight, craneArm;
    private Servo rightClawRotator, leftClawRotator, rightClawServo, leftClawServo;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        frontLeft = hardwareMap.get(DcMotor.class, "fruntLeft");
        frontRight = hardwareMap.get(DcMotor.class, "fruntRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "jarmy");
        backRight = hardwareMap.get(DcMotor.class, "jarmy");

        
        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setTargetPosition(0);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setTargetPosition(0);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setTargetPosition(0);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);

        craneArm = hardwareMap.get(DcMotor.class, "craneArm");
        craneArm.setDirection(DcMotor.Direction.REVERSE);
        craneArm.setTargetPosition(0);
        craneArm.setPower(0.2);
        craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        craneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");

        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");

        waitForStart();
        craneArm.setTargetPosition(200);
        leftClawRotator.setPosition(0.58);
        rightClawRotator.setPosition(0.37);

        sleep(10000);

//        while(opModeIsActive()){
//
//        }







    }

}