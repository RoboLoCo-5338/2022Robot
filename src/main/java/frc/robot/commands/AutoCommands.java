// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.ejml.equation.Function;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class AutoCommands {
    // Main auto commands to be built from
    public static Command angleTurnCommand(double angle, String direction) {
		return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetAngle(),
			() -> RobotContainer.driveSystem.angleTurn(direction),
			(interrupt) -> RobotContainer.driveSystem.tankDriveVelocity(0, 0),
			() -> RobotContainer.driveSystem.getAngle() >= angle,
			RobotContainer.driveSystem
		);
	}

    public static Command driveDistanceCommand(double distance) {
        return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetPosition(),
			() -> RobotContainer.driveSystem.driveDistance(distance),
			(interrupt) -> RobotContainer.driveSystem.tankDriveVelocity(0, 0),
			() -> RobotContainer.driveSystem.getPosition() >= distance,
			RobotContainer.driveSystem
		);
	}

	public static Command defaultAutoCommand() {
		return new SequentialCommandGroup(
			driveDistanceCommand(5),
			angleTurnCommand(90, "right")
		);
	}
}
