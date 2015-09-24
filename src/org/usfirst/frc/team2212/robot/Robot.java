package org.usfirst.frc.team2212.robot;

import org.usfirst.frc.team2212.robot.subsystems.ForwardDrivetrain;
import org.usfirst.frc.team2212.robot.subsystems.GearBox;
import org.usfirst.frc.team2212.robot.subsystems.SidewaysDrivetrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public final static Encoder eLeft = new Encoder(1, 19),
			eRight = new Encoder(0, 20), eFront = new Encoder(2, 18),
			eRear = new Encoder(4, 5);

	public final static GearBox left = new GearBox(0, 1), right = new GearBox(
			8, 9);

	public final static VictorSP front = new VictorSP(4),
			rear = new VictorSP(5);

	public final static Gyro gyro = new Gyro(0);

	Joystick driver = new Joystick(0);

	public static ForwardDrivetrain forward;
	public static SidewaysDrivetrain sideways;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		// instantiate the command used for the autonomous period
		forward = new ForwardDrivetrain(0, 0, 0);
		sideways = new SidewaysDrivetrain(0, 0, 0);
		SmartDashboard.putNumber("kp", 0);
		SmartDashboard.putNumber("ki", 0);
		SmartDashboard.putNumber("kd", 0);
		SmartDashboard.putNumber("destination", 0);
		SmartDashboard.putNumber("tolerance", 0.1);
		SmartDashboard.putNumber("side-kp", 0);
		SmartDashboard.putNumber("side-ki", 0);
		SmartDashboard.putNumber("side-kd", 0);
		SmartDashboard.putNumber("side-destination", 0);
		SmartDashboard.putNumber("side-tolerance", 0.1);
		eLeft.setDistancePerPulse(6 * 2.54 * Math.PI / 360);
		eRight.setDistancePerPulse(6 * 2.54 * Math.PI / 360);
		eFront.setDistancePerPulse(6 * 2.54 * Math.PI / 360);
		eRear.setDistancePerPulse(6 * 2.54 * Math.PI / 360);

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void autonomousInit() {
		/*
		 * eRight.reset(); eLeft.reset(); forward.updatePID();
		 * forward.setSetpoint(SmartDashboard.getNumber("destination"));
		 * forward.enable();
		 */
		eFront.reset();
		eRear.reset();
		forward.updatePID();
		forward.setSetpoint(SmartDashboard.getNumber("side-destination"));
		forward.enable();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		forward.putData();
	}

	public static int random() {
		// Chosen randomly by a fair dice
		return 3;
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
		forward.disable();
		sideways.disable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		double y = driver.getY();
		double x = driver.getX();
		front.set(x);
		rear.set(-x);
		left.set(y);
		right.set(-y);
		forward.putData();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
