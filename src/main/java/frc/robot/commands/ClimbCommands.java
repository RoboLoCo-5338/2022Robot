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
public class ClimbCommands {
    public static Command toggleLongArms = new RunCommand(
      () -> RobotContainer.climb.toggleLongArms(),
      RobotContainer.climb
    );

    public static Command toggleShortArms = new RunCommand(
      () -> RobotContainer.climb.toggleShortArms(),
      RobotContainer.climb
    );

    public static Command toggleClimbMotor = new RunCommand(
      () -> RobotContainer.climb.toggleClimbMotor(),
      RobotContainer.climb
    );

    public static Command toggleClimbWinch = new RunCommand(
      () -> RobotContainer.climb.toggleClimbWinch(),
      RobotContainer.climb
    );
}
