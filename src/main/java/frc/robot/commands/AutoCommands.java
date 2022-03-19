// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class AutoCommands {
    // Main auto commands to be built from
    public static Command angleTurnCommand(double angle, String direction) {
		return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetAngle(),
			() -> RobotContainer.driveSystem.angleTurn(direction),
			(interrupt) -> RobotContainer.driveSystem.tankDriveVelocity(0, 0),
			() -> Math.abs(RobotContainer.driveSystem.getAngle()) >= angle,
			RobotContainer.driveSystem
		);
	}

	//pid turn command maybe??
	public static Command pidTurnCommand(double angle) {
		return new PIDCommand(
			new PIDController(
				0.0,
				0.0,
				0.0),
			// Use navX sensors input 
			() -> RobotContainer.driveSystem.getAngle(),
			// Use angle as setpoint
			() -> angle,
			(output) -> RobotContainer.driveSystem.tankDriveVelocity(output, -output),
			RobotContainer.driveSystem
		);
	}

    public static Command driveDistanceCommand(double distance, Direction direction) {
        return new FunctionalCommand(
			() -> RobotContainer.driveSystem.resetPosition(),
			() -> RobotContainer.driveSystem.driveDistance(distance, direction),
			(interrupt) -> RobotContainer.driveSystem.tankPercent(0, 0),
			() -> RobotContainer.driveSystem.getPosition() >= distance,
			RobotContainer.driveSystem
		);
	}

	public static Command sidelineAuto() {
		return new SequentialCommandGroup(
			driveDistanceIntake(38, Direction.BACKWARD),
			angleTurnCommand(157.5, "left"),
			driveDistanceCommand(114, Direction.FORWARD),
			doubleShootCommand()
		);
	}

	public static Command middleAuto() {
		return new SequentialCommandGroup(
			driveDistanceIntake(54, Direction.BACKWARD),
			angleTurnCommand(180, "left"),
			driveDistanceCommand(93, Direction.FORWARD),
			angleTurnCommand(22.5, "left"),
			driveDistanceCommand(30, Direction.FORWARD),
			doubleShootCommand()
		);
	}

	public static Command hangerSideAuto() {
		return new SequentialCommandGroup(
			driveDistanceIntake(38, Direction.BACKWARD),
			angleTurnCommand(157.5, "right"),
			driveDistanceCommand(117, Direction.BACKWARD),
			doubleShootCommand()	
		);
	}

	public static Command stopCommand() {
		return new RunCommand(() -> RobotContainer.driveSystem.tankDriveVelocity(0, 0), RobotContainer.driveSystem);
	}

	// autonomous default command group
	public static Command defaultAutoCommand() {
		return new SequentialCommandGroup(
			driveDistanceCommand(80, Direction.FORWARD)
		);
	}

	public static Command driveDistanceIntake(double distance, Direction direction) {
		return new SequentialCommandGroup(
			IntakeCommands.toggleIntakePneumatics(),
			new ParallelCommandGroup(
				IntakeCommands.indexForwardTime(),
				driveDistanceCommand(distance, direction)
			),
			IntakeCommands.toggleIntakePneumatics()
		);
	}

	public static Command doubleShootCommand() {
		return new ParallelDeadlineGroup(
			IntakeCommands.indexForward(),
			ShooterCommands.shootCommand()
		);
	}

	// start on bottom line (parallel to long side of field)
	// public static Command bottomMid1() {
	// 	return new SequentialCommandGroup(
	// 		driveDistanceIntake(40.44),
	// 		angleTurnCommand(180, "right"),
	// 		// IntakeCommands.stopIntake(),
	// 		driveDistanceCommand(116.17),
	// 		angleTurnCommand(22.5, "right")
	// 		// ShooterCommands.shootCommand(),
	// 		// angleTurnCommand(22.5, "left"),
	// 		// driveDistanceCommand(-116.17)
	// 	);
	// }
	// public static Command bottomMid2() {
	// 	return new SequentialCommandGroup(
	// 		driveDistanceIntake(40.44),
	// 		angleTurnCommand(180, "right"),
	// 		// IntakeCommands.stopIntake(),
	// 		driveDistanceCommand(116.17),
	// 		angleTurnCommand(22.5, "left")
	// 		//fire 2 balls,
	// 		// driveDistanceCommand(-116.17)
	// 	);
	// }

	// // start at far left corner (get left ball)
	// public static Command bottomLeft() {
	// 	return new SequentialCommandGroup(
	// 		driveDistanceIntake(40.44),
	// 		angleTurnCommand(180, "right"),
	// 		// IntakeCommands.stopIntake(),
	// 		driveDistanceCommand(40.44),
	// 		angleTurnCommand(65, "right"),
	// 		driveDistanceCommand(75.07)
	// 		//fire 2 balls,
	// 		// driveDistanceCommand(-75.07)
	// 	);
	// }

	// // start at far right corner (get left ball)
	// public static Command bottomRightLeft() {
	// 	return new SequentialCommandGroup(
	// 		driveDistanceCommand(153),
	// 		angleTurnCommand(67.5, "left"),
	// 		driveDistanceIntake(40.44),
	// 		angleTurnCommand(180, "right"),
	// 		// IntakeCommands.stopIntake(),
	// 		driveDistanceCommand(40.44),
	// 		angleTurnCommand(65, "right"),
	// 		driveDistanceCommand(75.07)
	// 		//fire 2 balls,
	// 		// driveDistanceCommand(-75.07)
	// 	);
	// }
}
