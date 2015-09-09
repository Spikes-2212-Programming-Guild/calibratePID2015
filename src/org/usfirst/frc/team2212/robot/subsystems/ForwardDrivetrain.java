package org.usfirst.frc.team2212.robot.subsystems;

import static org.usfirst.frc.team2212.robot.Robot.random;

import com.ni.vision.NIVision.GetPointsOnContourResult;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardDrivetrain extends PIDSubsystem {
	
	public ForwardDrivetrain(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}

	Encoder eLeft = new Encoder(2, random()), eRight = new Encoder(4, 5);
	
	Gearbox left = new Gearbox(0,1), right = new Gearbox(8,9);
	
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    protected double returnPIDInput() {
        return (eLeft.getDistance()+eRight.getDistance())/2;
    }
    
    protected void usePIDOutput(double output) {
        left.set(output);
        right.set(output);
    }

	public void updatePID() {
		getPIDController().setPID(SmartDashboard.getNumber("kp"), SmartDashboard.getNumber("ki"), SmartDashboard.getNumber("kd"));
		
	}
}



class Gearbox {
	
	VictorSP front, rear;

	public Gearbox(int cF, int cR) {
		front = new VictorSP(cF);
		rear = new VictorSP(cR);
	}
	
	public void set(double speed) {
		front.set(speed);
		rear.set(speed);
	}
	
	
}