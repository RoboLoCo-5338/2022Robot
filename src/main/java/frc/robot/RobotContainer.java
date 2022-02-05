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
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveSystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static DriveSystem driveSystem = new DriveSystem();
  public static Climb climb = new Climb();
  private Joystick controller1 = new Joystick(0);
  private Joystick controller2 = new Joystick(1);

  // Initialize the drive command
    public Command defaultDrive = new RunCommand(
      () -> driveSystem.tankDriveVelocity(
        this.controller1.getRawAxis(1),
        this.controller1.getRawAxis(5)
      ),
      driveSystem
    );

    public Command toggleSlow = new RunCommand(
      () -> driveSystem.toggleSlow(),
      driveSystem
    );

    public Command longArmToggle = new RunCommand(
      () -> climb.longArm(),
      climb
    );

    public Command shortArmToggle = new RunCommand(
      () -> climb.shortArm(),
      climb
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
    JoystickButton slowButton = new JoystickButton(controller1, 1);
    slowButton.whenPressed(toggleSlow);
    JoystickButton longPiston = new JoystickButton(controller2, 3);
    longPiston.whenPressed(longArmToggle);
    JoystickButton shortPiston = new JoystickButton(controller2, 4);
    shortPiston.whenPressed(shortArmToggle);
    JoystickButton climbMotor = new JoystickButton(controller2, 5);
    //toggle motor
    JoystickButton climbWinch = new JoystickButton(controller2, 6);
    //toggle winch
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
