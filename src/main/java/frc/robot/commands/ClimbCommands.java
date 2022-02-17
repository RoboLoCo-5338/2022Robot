// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class ClimbCommands {
    public static Command longForward = new RunCommand(
      () -> RobotContainer.climb.longForward(),
      RobotContainer.climb
    );

    public static Command longReverse = new RunCommand(
      () -> RobotContainer.climb.longReverse(),
      RobotContainer.climb
    );

    public static Command shortForward = new RunCommand(
      () -> RobotContainer.climb.shortForward(),
      RobotContainer.climb
    );

    public static Command shortReverse = new RunCommand(
      () -> RobotContainer.climb.shortReverse(),
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
