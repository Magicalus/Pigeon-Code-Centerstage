package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad.*;

import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import java.util.Map;
import java.lang.Math;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.util.ElapsedTime.Resolution;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name="value testing", group="Linear Opmode")

public class TESTER extends LinearOpMode{
    private DcMotor.ZeroPowerBehavior brake = DcMotor.ZeroPowerBehavior.BRAKE;
    private DcMotor.ZeroPowerBehavior floatt =DcMotor.ZeroPowerBehavior.FLOAT;
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo rightClawRotator, leftClawRotator, rightClawServo, leftClawServo;

    private DcMotor leftRobotLifter, rightRobotLifter;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        frontLeft = hardwareMap.get(DcMotor.class, "fruntLeft");
        frontRight = hardwareMap.get(DcMotor.class, "fruntRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "jarmy");

        leftRobotLifter = hardwareMap.get(DcMotor.class, "leftRobotLifter");
        rightRobotLifter = hardwareMap.get(DcMotor.class, "rightRobotLifter");



        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");
        
        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");
        

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setTargetPosition(0);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setTargetPosition(0);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setTargetPosition(0);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        leftRobotLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRobotLifter.setTargetPosition(0);
        leftRobotLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRobotLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightRobotLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRobotLifter.setTargetPosition(0);
        rightRobotLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRobotLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
        // craneArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // craneArm.setTargetPosition(0);
        // craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // craneArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        
//        telemetry.addData("right position:", rightClawRotator.getPosition());
//        telemetry.addData("left position:", leftClawRotator.getPosition());
         telemetry.addData("right position:", rightRobotLifter.getCurrentPosition());
        telemetry.addData("left position:", leftRobotLifter.getCurrentPosition());
//        telemetry.addData("backLeft position:", frontLeft.getCurrentPosition());
//
//        telemetry.addData("backRight position:", frontRight.getCurrentPosition());
        telemetry.update();
        waitForStart();
        telemetry.addData("right position:", rightRobotLifter.getCurrentPosition());
        telemetry.addData("left position:", leftRobotLifter.getCurrentPosition());
//        telemetry.addData("backLeft position:", frontLeft.getCurrentPosition());
//
//        telemetry.addData("backRight position:", frontRight.getCurrentPosition());
        telemetry.update();
        sleep(100000);







    }

}