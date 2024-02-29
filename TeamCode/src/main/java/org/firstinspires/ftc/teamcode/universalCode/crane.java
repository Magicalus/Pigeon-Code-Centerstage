package org.firstinspires.ftc.teamcode.universalCode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class crane {
    private DcMotor leftDrawerSlide, rightDrawerSlide, clawSpinnies;
    public int targetPosition;

    public boolean clawIsBack = false;
    public double power = 0;

    public crane(HardwareMap hardwareMap, boolean clawIsBack){ this(hardwareMap, 0.5, clawIsBack, false); }

    public crane(HardwareMap hardwareMap, double power, boolean clawIsBack, boolean craneByPower){
        leftDrawerSlide = hardwareMap.get(DcMotor.class, "leftDrawerSlide");
        leftDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightDrawerSlide = hardwareMap.get(DcMotor.class, "rightDrawerSlide");
        rightDrawerSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrawerSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoders();

        clawSpinnies = hardwareMap.get(DcMotor.class, "clawSpinnies");
        resetClawSpinnies();
        clawSpinnies.setTargetPosition(0);
        clawSpinnies.setPower(0.6);
        clawSpinnies.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawSpinnies.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        clawSpinnies.setDirection(DcMotorSimple.Direction.REVERSE);
        this.clawIsBack = clawIsBack;
        targetPosition = 0;

        setPower(power);
        setTargetPosition(0);
        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void setTargetPosition(int target){
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrawerSlide.setTargetPosition(target);
        rightDrawerSlide.setTargetPosition(target);
        targetPosition = target;
    }

    public void resetEncoders(){
        leftDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrawerSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setTargetPosition(0);
        setPower(power);

        leftDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrawerSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetClawSpinnies(){
        clawSpinnies.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawSpinnies.setTargetPosition(0);
        clawSpinnies.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setSpinniesPosition(int target){
        clawSpinnies.setTargetPosition(target);
    }

    public void craneMaintenance(){
        if(offCheck() && targetPosition == 0){
            resetEncoders();
        }
        if(clawSpinnies.getCurrentPosition() < -2574){
            clawIsBack = false;
            resetClawSpinnies();
        }else if(clawIsBack){
            clawSpinnies.setTargetPosition(-2575);
        }else if(leftDrawerSlide.getCurrentPosition() > 500){
            clawSpinnies.setTargetPosition(500);
        }else{
            clawSpinnies.setTargetPosition(0);
        }
    }

    public void move(double movement){
        if(movement <= 1 &&
                (getCurrentLeftPosition() + getCurrentRightPosition()) / 2 < values.craneMax){

            setPower(movement);
        }else if(movement <= 1){
            setPower(0);
        }else if(movement > values.craneMax){
            setTargetPosition(values.craneMax);
        }else{
            setTargetPosition((int)movement);
        }
    }
    public void setPower(double power){
        this.power = power;
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrawerSlide.setPower(power);
        rightDrawerSlide.setPower(power);
    }

    public int getCurrentLeftPosition() { return leftDrawerSlide.getCurrentPosition(); }

    public int getCurrentRightPosition() { return rightDrawerSlide.getCurrentPosition(); }
    public int getCurrentSpinniesPosition(){return clawSpinnies.getCurrentPosition();}

    public boolean offCheck(){
        return (getCurrentLeftPosition() > 10 && getCurrentRightPosition() < 0) || (getCurrentLeftPosition() < 0 && getCurrentRightPosition() > 10) || (getCurrentLeftPosition() < 0 && getCurrentRightPosition() < 0);
    }

    public void clawAintBack(){
        clawIsBack = false;
    }
    public void setMode(DcMotor.RunMode mode){
        leftDrawerSlide.setMode(mode);
        rightDrawerSlide.setMode(mode);
    }
}