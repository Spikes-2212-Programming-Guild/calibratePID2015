
package org.usfirst.frc.team2212.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team2212.robot.subsystems.ForwardDrivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	public static  ForwardDrivetrain forward;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
		forward = new ForwardDrivetrain(0, 0, 0);
		SmartDashboard.putNumber("KP", 0);
		SmartDashboard.putNumber("KI", 0);
		SmartDashboard.putNumber("KD", 0);
		SmartDashboard.putNumber("Destination", 0);
		
    }
    
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	
	}

    public void autonomousInit() {
    	forward.updatePID();
    	forward.setSetpoint(SmartDashboard.getNumber("Destination"));
        forward.enable();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public static int random(){
    	// Chosen randomly by a fair dice
    	return 3;
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	forward.disable();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
