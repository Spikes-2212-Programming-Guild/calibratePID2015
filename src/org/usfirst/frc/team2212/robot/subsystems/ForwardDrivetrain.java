package org.usfirst.frc.team2212.robot.subsystems;

import static org.usfirst.frc.team2212.robot.Robot.eLeft;
import static org.usfirst.frc.team2212.robot.Robot.eRight;
import static org.usfirst.frc.team2212.robot.Robot.left;
import static org.usfirst.frc.team2212.robot.Robot.right;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardDrivetrain extends PIDSubsystem {

	public static final double RANGE = 0.5;

	private double maximum;

	public ForwardDrivetrain(double p, double i, double d) {
		super(p, i, d);
		getPIDController().setOutputRange(-RANGE, RANGE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	@Override
	protected double returnPIDInput() {
		double pid = (-eLeft.getDistance() + eRight.getDistance()) / 2;
		SmartDashboard.putNumber("In", pid);
		return pid;
	}

	@Override
	public void usePIDOutput(double output) {
		SmartDashboard.putNumber("Out", output / maximum);
		maximum = Math.max(Math.abs(output), maximum);
		SmartDashboard.putNumber("maximum", maximum);
		SmartDashboard.putNumber("Out", output / maximum);
		left.set(RANGE * output / maximum);
		right.set(-RANGE * output / maximum);
	}

	public void updatePID() {
		maximum = 0;
		getPIDController().setPID(SmartDashboard.getNumber("kp"),
				SmartDashboard.getNumber("ki"), SmartDashboard.getNumber("kd"));
		getPIDController().setAbsoluteTolerance(
				SmartDashboard.getNumber("tolerance", 0.1));

	}

	public void reset() {
		eLeft.reset();
		eRight.reset();
	}

	public void putData() {

		SmartDashboard.putNumber("eLeft", -eLeft.getDistance());
		SmartDashboard.putNumber("eRight", eRight.getDistance());
	}
}
