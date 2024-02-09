package org.firstinspires.ftc.teamcode.newOpenCVAuton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.supermanTESTER.Globals;
import org.firstinspires.ftc.teamcode.supermanTESTER.Location;
import org.firstinspires.ftc.teamcode.supermanTESTER.PropPipeline;
import org.firstinspires.ftc.teamcode.universalCode.craneMotors;
import org.firstinspires.ftc.teamcode.universalCode.driveTrain;
import org.firstinspires.ftc.teamcode.universalCode.values;
import org.firstinspires.ftc.vision.VisionPortal;


//@Disabled
@Autonomous(name="red Back\uD83D\uDD34")
public class redBack extends LinearOpMode {
    private VisionPortal portal;
    private DcMotor.ZeroPowerBehavior brake = DcMotor.ZeroPowerBehavior.BRAKE;
    private DcMotor.ZeroPowerBehavior floatt =DcMotor.ZeroPowerBehavior.FLOAT;
    private Servo leftClawRotator;
    private Servo rightClawRotator;
    private Servo airplaneLauncher;

    private Servo leftClawServo;
    private Servo rightClawServo;

    private driveTrain wheels;
    private craneMotors crane;
    // private Servo goodServo;
    //private Servo badServo;

    private int mid = 80;
    private int turn = 65;

    private long startTime;

    private PropPipeline propPipeline;
    private Location randomization;

    @Override
    public void runOpMode() throws InterruptedException {


        Globals.IS_AUTO = true;
        Globals.ALLIANCE = Location.RED;
        Globals.SIDE = Location.FAR;

        propPipeline = new PropPipeline();
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1920, 1080))
                .addProcessor(propPipeline)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .setAutoStopLiveView(true)
                .build();

        rightClawRotator = hardwareMap.get(Servo.class, "rightClawRotator");
        leftClawRotator = hardwareMap.get(Servo.class, "leftClawRotator");

        rightClawServo = hardwareMap.get(Servo.class, "rightClawServo");
        leftClawServo = hardwareMap.get(Servo.class, "leftClawServo");

        airplaneLauncher = hardwareMap.get(Servo.class, "airplaneLauncher");

        wheels = new driveTrain(hardwareMap, this);
        wheels.isAuton();

        crane = new craneMotors(hardwareMap);

        closeClaw();
        airplaneLauncher.setPosition(0);



        while (opModeInInit()) {
            telemetry.addLine("ready");
            telemetry.addData("position", propPipeline.getLocation());
            telemetry.update();
        }

        randomization = propPipeline.getLocation();
        portal.close();

        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            startTime = System.currentTimeMillis();
            wheels.resetEncoders();

            switch (randomization) {
                case LEFT:
                    telemetry.addData("left","left");
                    telemetry.update();

                    foward(-1300);

                    rotate(values.turn90DegreesClockwise);
                    pickupPixel();

                    foward(100);
                    rightClawServo.setPosition(0.5);
                    sleep(500);

                    neutral();
                    sleep(2500);

                    foward(-50);
                    sleep(2500);

                    side(1050);

                    while(System.currentTimeMillis() - startTime < 25000.0);

                    foward(-4500);

                    pickupPixel();

                    leftClawServo.setPosition(values.leftClawOpen);

                    neutral();

                    break;
                case CENTER:
                    telemetry.addData("Center","center");
                    telemetry.update();

                    foward(-2160);

                    pickupPixel();
                    sleep(500);

                    rightClawServo.setPosition(0.5);
                    sleep(1000);

                    neutral();

                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 25000.0);
//                side(-2700);
//                waitforwheels();

                    foward(-4500);

                    pickupPixel();

                    leftClawServo.setPosition(values.leftClawOpen);

                    neutral();

                    break;
                case RIGHT:
                    telemetry.addData("right","right");
                    telemetry.update();

                    neutral();
                    foward(-1100);

                    pickupPixel();
                    rotate(values.turn90DegreesCounterClockwise);

                    foward(100);
                    rightClawServo.setPosition(0.5);
                    sleep(500);

                    neutral();
                    foward(-100);
                    sleep(1000);

                    rotate(values.turn90DegreesClockwise);
                    wheels.setFowardSpeed(0.4);
                    foward(-1200);
                    rotate(values.turn90DegreesClockwise);

                    while(System.currentTimeMillis() - startTime < 25000.0);

                    foward(-4500);

                    pickupPixel();

                    leftClawServo.setPosition(values.leftClawOpen);

                    neutral();

                    break;
            }
            break;
        }


    }

    public void foward(int distance){
        wheels.foward(distance);
    }

    public void side(int distance){
        wheels.side(distance);
    }

    public void rotate(int distance){
        wheels.rotate(distance);
    }

    public void placePixelLow(){
        crane.setTargetPosition(values.cranePlaceHighAuton);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void neutral(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(0.1);
        rightClawRotator.setPosition(0.85);
    }
    public void pickupPixel(){
        crane.setTargetPosition(values.craneResting);
        leftClawRotator.setPosition(0.53);
        rightClawRotator.setPosition(0.42);
    }
    public void closeClaw() {
        leftClawServo.setPosition(0);
        rightClawServo.setPosition(1);
    }
}