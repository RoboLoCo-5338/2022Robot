// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class AutoCommands {
    // Main auto commands to be built from
    public static Command angleTurnCommand(double angle, String direction) {
		System.out.println("given: " + angle);
		return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetAngle(),
			() -> RobotContainer.driveSystem.angleTurn(direction),
			(interrupt) -> RobotContainer.driveSystem.tankDriveVelocity(0, 0),
			() -> Math.abs(RobotContainer.driveSystem.getAngle()) >= angle,
			RobotContainer.driveSystem
		);
	}

    public static Command driveDistanceCommand(double distance) {
        return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetPosition(),
			() -> RobotContainer.driveSystem.driveDistance(distance),
			(interrupt) -> RobotContainer.driveSystem.tankDriveVelocity(0, 0),
			() -> Math.abs(RobotContainer.driveSystem.getPosition()) >= Math.abs(distance),
			RobotContainer.driveSystem
		);
	}

	public static Command stopCommand() {
		return new RunCommand(() -> RobotContainer.driveSystem.tankDriveVelocity(0, 0), RobotContainer.driveSystem);
	}

	public static Command defaultAutoCommand() {
		return new SequentialCommandGroup(
			driveDistanceCommand(24),
			angleTurnCommand(90, "right").andThen(stopCommand()),
			driveDistanceCommand(12)
		);
	}

	public static Command driveDistanceIntake(double distance) {
		return new ParallelCommandGroup(
			//start intake,
			driveDistanceCommand(distance)
		);
	}

	public static Command bottomMid1() {
		return new SequentialCommandGroup(
			driveDistanceIntake(40.44),
			angleTurnCommand(180, "right"),
			//stop intake,
			driveDistanceCommand(116.17),
			angleTurnCommand(22.5, "right"),
			//fire 2 balls,
			angleTurnCommand(22.5, "left"),
			driveDistanceCommand(-116.17)
		);
	}
	public static Command bottomMid2() {
		return new SequentialCommandGroup(
			driveDistanceIntake(40.44),
			angleTurnCommand(180, "right"),
			//stop intake,
			driveDistanceCommand(116.17),
			angleTurnCommand(22.5, "left"),
			//fire 2 balls,
			driveDistanceCommand(-116.17)
		);
	}
	public static Command bottomLeft() {
		return new SequentialCommandGroup(
			driveDistanceIntake(40.44),
			angleTurnCommand(180, "right"),
			//stop intake,
			driveDistanceCommand(40.44),
			angleTurnCommand(65, "right"),
			driveDistanceCommand(75.07),
			//fire 2 balls,
			driveDistanceCommand(-75.07)
		);
	}
	public static Command bottomRightLeft() {
		return new SequentialCommandGroup(
			driveDistanceCommand(153),
			angleTurnCommand(67.5, "left"),
			driveDistanceIntake(40.44),
			angleTurnCommand(180, "right"),
			//stop intake,
			driveDistanceCommand(40.44),
			angleTurnCommand(65, "right"),
			driveDistanceCommand(75.07),
			//fire 2 balls,
			driveDistanceCommand(-75.07)
		);
	}
}
