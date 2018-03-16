package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTimedDriveStopCommandGroup extends CommandGroup {

    public AutoTimedDriveStopCommandGroup() {
        addSequential(new AutoTimedDriveCommand(5));
//        addSequential(new StopAutonomousCommand(10));
    }
}
