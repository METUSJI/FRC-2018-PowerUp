package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeSwitchCommand extends CommandGroup {

    public AutoEdgeSwitchCommand(int left) {
    	addSequential(new CloseClawCommand());
    	addParallel(new ChangeElevatorSetpointCommand(16));
    	addSequential(new DriveStraightPIDCommand(130, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(12, Robot.kPd, Robot.kId, Robot.kDd));
    	addParallel(new TimedSpinCubeWheelsCommand(1.5));
    	addSequential(new OpenClawCommand());
    }
}
