// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.RobotContainer;

public class DriveSystem extends PIDSubsystem {
	private static final double MAX_VELOCITY = 475;
	private static final double SLOW_VELOCITY = 400;
	private static final double PEAK_OUTPUT = 1.0;
  private static boolean slow = false;

  // set PID values for teleop
  public static final double VELOCITY_P = 0.000213;
	public static final double VELOCITY_I = 0.0;
	public static final double VELOCITY_D = 0.0;
	public static final double VELOCITY_FEED_FORWARD = 0.243;

  // set PID values for autonomous
	public static final double POSITION_P = 0.06;
	public static final double POSITION_I = 0.0;
	public static final double POSITION_D = 0.0;
	public static final double POSITION_FEED_FORWARD = 0.0;

  // encoder math
  private static final double TICKS_PER_REVOLUTION = 2048;
  private static final double WHEEL_DIAMETER = 6.0;
  private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
  private static final double TICKS_PER_INCH = TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;
  

  /** Creates a new DriveSystem. */
  private static WPI_TalonSRX rightFront;
  private static WPI_TalonSRX rightRear;
  private static WPI_TalonSRX leftFront;
  private static WPI_TalonSRX leftRear;

  public DriveSystem() {
    // set PID values here
    super(new PIDController(VELOCITY_P, VELOCITY_I, VELOCITY_D));

    rightFront = new WPI_TalonSRX(2);
    rightRear = new WPI_TalonSRX(3);
    leftFront = new WPI_TalonSRX(0);
    leftRear = new WPI_TalonSRX(1);

    configureTalon();
  }

  
  // configure talon properties
  private static void configureTalon() {
    // rightFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    // rightFront.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 30);

    // leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    // leftFront.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 30);
    leftFront.setInverted(true);

    // rightRear.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    rightRear.setNeutralMode(NeutralMode.Brake);
    rightRear.follow(rightFront);
    // rightRear.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 30);

    // leftRear.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    leftRear.setNeutralMode(NeutralMode.Brake);
    leftRear.follow(leftFront);
    // leftRear.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 30);
    leftRear.setInverted(true);
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    rightFront.config_kP(0, kP, 100);
    rightFront.config_kI(0, kI, 100);
    rightFront.config_kD(0, kD, 100);
    rightFront.config_kF(0, kF, 100);

    leftFront.config_kP(0, kP, 100);
    leftFront.config_kI(0, kI, 100);
    leftFront.config_kD(0, kD, 100);
    leftFront.config_kF(0, kF, 100);
    
    rightRear.config_kP(0, kP, 100);
    rightRear.config_kI(0, kI, 100);
    rightRear.config_kD(0, kD, 100);
    rightRear.config_kF(0, kF, 100);

    leftRear.config_kP(0, kP, 100);
    leftRear.config_kI(0, kI, 100);
    leftRear.config_kD(0, kD, 100);
    leftRear.config_kF(0, kF, 100);
  }

   // creates a PID velocity robot. Uses PID settings to determine speeds
   public void tankDriveVelocity(double left, double right) {
    double targetLeft;
    double targetRight;

    // max rpm of wheels desired
    double targetVelocity = MAX_VELOCITY;
    
    if (this.slow) {
      targetVelocity = SLOW_VELOCITY;
    }

    // target speed in encoder units based on joystick position
    targetLeft = left * targetVelocity * 4096 / 600.0;
    targetRight = right * targetVelocity * 4096 / 600.0;

    // set target speeds to motors
    this.leftFront.set(ControlMode.Velocity, targetLeft);
    this.rightFront.set(ControlMode.Velocity, targetRight);
  }

  public void tankPercent(double left, double right) {
    this.leftFront.set(ControlMode.PercentOutput, left);
    this.rightFront.set(ControlMode.PercentOutput, right);
    this.leftRear.set(ControlMode.PercentOutput, left);
    this.rightRear.set(ControlMode.PercentOutput, right);
  }

  public void driveDistance(double inches) {
    double targetPosition = inches * TICKS_PER_INCH;
    
    this.rightFront.set(ControlMode.Position, targetPosition);
		this.leftFront.set(ControlMode.Position, targetPosition);
		this.rightRear.set(ControlMode.Position, targetPosition);
		this.leftRear.set(ControlMode.Position, targetPosition);
	}

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
