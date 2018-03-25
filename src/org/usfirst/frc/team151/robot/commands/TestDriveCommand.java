package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestDriveCommand extends CommandGroup {

    public TestDriveCommand() {
//    	addSequential(new AutoTurnPIDCommand(90, 0.035, 0, 0.0));
    	addSequential(new DriveStraightPIDCommand(36, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(48, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(-90, Robot.kPt, Robot.kIt, Robot.kDt));
    //	addSequential(new FinishAutoCommand());
    }
}
