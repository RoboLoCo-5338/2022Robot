// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoCommands;
import frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ShooterSystem;
import frc.robot.commands.ShooterCommands;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static DriveSystem driveSystem = new DriveSystem();
  public static ShooterSystem shooterSystem = new ShooterSystem();
  public static Intake intake = new Intake();

  Joystick controller = new Joystick(0);

  // Initialize the drive command
    public Command defaultDrive = new RunCommand(
      () -> driveSystem.tankDriveVelocity(
        this.controller.getRawAxis(1),
        this.controller.getRawAxis(5)
      ),
      driveSystem
    );

    public Command toggleSlow = new RunCommand(
      () -> driveSystem.toggleSlow(),
      driveSystem
    );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton slowButton = new JoystickButton(controller, Constants.ABUTTON);
    slowButton.whenPressed(toggleSlow);
    JoystickButton shooterButton = new JoystickButton(controller, Constants.BBUTTON);
    shooterButton.whenPressed(ShooterCommands.shootCommand());
    shooterButton.whenReleased(ShooterCommands.stopShootCommand());
    
    JoystickButton pneumaticsButton = new JoystickButton(controller, Constants.BBUTTON); //TODO: replace all the buttons+ figure it out
    pneumaticsButton.whenPressed(IntakeCommands.extend());
    pneumaticsButton.whenReleased(IntakeCommands.retract());

    JoystickButton intakeIndexForward = new JoystickButton(controller, Constants.ABUTTON);
    intakeIndexForward.whenPressed(IntakeCommands.intakeIndexForward());
    intakeIndexForward.whenReleased(IntakeCommands.stopIndex());

    JoystickButton intakeIndexReverse = new JoystickButton(controller, Constants.XBUTTON);
    intakeIndexReverse.whenPressed(IntakeCommands.intakeIndexReverse());
    intakeIndexReverse.whenReleased(IntakeCommands.stopIndex());

    JoystickButton outakeIndexForward = new JoystickButton(controller, Constants.YBUTTON);
    outakeIndexForward.whenPressed(IntakeCommands.extend());
    outakeIndexForward.whenReleased(IntakeCommands.stopIndex());

    JoystickButton outakeIndexReverse = new JoystickButton(controller, Constants.LBBUTTON);
    outakeIndexReverse.whenPressed(IntakeCommands.extend());
    outakeIndexReverse.whenReleased(IntakeCommands.stopIndex());
  }

  private void configureDefaultCommands() {
    driveSystem.setDefaultCommand(defaultDrive);
    CommandScheduler scheduler = CommandScheduler.getInstance();
    scheduler.setDefaultCommand(driveSystem, defaultDrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return AutoCommands.defaultAutoCommand();
  }
}
