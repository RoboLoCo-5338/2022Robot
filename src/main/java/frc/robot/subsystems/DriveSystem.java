// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class DriveSystem extends PIDSubsystem {
	private static final double MAX_VELOCITY = 200;
	private static final double SLOW_VELOCITY = 150;
	private static final double PEAK_OUTPUT = 1.0;
  public static boolean slow = false;

  // set PID values for teleop
  public static final double VELOCITY_P = 0.000056282;
	public static final double VELOCITY_I = 0.0;
	public static final double VELOCITY_D = 0.0;
	public static final double VELOCITY_FEED_FORWARD = 0.0;

  // set PID values for autonomous
	public static final double POSITION_P = 0.017047;
	public static final double POSITION_I = 0.0;
	public static final double POSITION_D = 0.000094614;
	public static final double POSITION_FEED_FORWARD = 0.0;

  // encoder math
  private static final double TICKS_PER_REVOLUTION = 2048;
  private static final double WHEEL_DIAMETER = 6.0;
  private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
  private static final double GEAR_RATIO = 10.7 / 1;
  private static final double TICKS_PER_INCH = (TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
  

  /** Creates a new DriveSystem. */
  private static WPI_TalonFX rightFront;
  private static WPI_TalonFX rightRear;
  private static WPI_TalonFX leftFront;
  private static WPI_TalonFX leftRear;

  // create Gyro object
  public AHRS navX;

  public DriveSystem() {
    // set PID values here
    super(new PIDController(VELOCITY_P, VELOCITY_I, VELOCITY_D));

    // Do we want to move these ID's to the Constants.java file?
    rightFront = new WPI_TalonFX(2);
    rightRear = new WPI_TalonFX(3);
    leftFront = new WPI_TalonFX(0);
    leftRear = new WPI_TalonFX(1);

    navX = new AHRS(SPI.Port.kMXP);

    configureTalon();
  }

  
  // configure talon properties
  private static void configureTalon() {
    // JDE: Are current limits set - should they be set here or elsewhere?
    // https://docs.ctre-phoenix.com/en/latest/ch13_MC.html#new-api-in-2020
    rightFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
		rightFront.configNeutralDeadband(0.001, 0);
		rightFront.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
		rightFront.setControlFramePeriod(ControlFrame.Control_3_General, 5);
    rightFront.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);

    leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
		leftFront.configNeutralDeadband(0.001, 0);
		leftFront.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
		leftFront.setControlFramePeriod(ControlFrame.Control_3_General, 5);
    leftFront.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    leftFront.setInverted(true);

    rightRear.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    rightRear.setNeutralMode(NeutralMode.Brake);
    rightRear.follow(rightFront);
		rightRear.configNeutralDeadband(0.001, 0);
		rightRear.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
		rightRear.setControlFramePeriod(ControlFrame.Control_3_General, 5);
    rightRear.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);

    leftRear.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    leftRear.setNeutralMode(NeutralMode.Brake);
    leftRear.follow(leftFront);
		leftRear.configNeutralDeadband(0.001, 0);
		leftRear.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
		leftRear.setControlFramePeriod(ControlFrame.Control_3_General, 5);
    leftRear.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    leftRear.setInverted(true);
  }

  public void setPIDF(double kP, double kI, double kD, double kF) {
    rightFront.config_kP(0, kP, 100);
    rightFront.config_kI(0, kI, 100);
    rightFront.config_kD(0, kD, 100);

    leftFront.config_kP(0, kP, 100);
    leftFront.config_kI(0, kI, 100);
    leftFront.config_kD(0, kD, 100);
    
    rightRear.config_kP(0, kP, 100);
    rightRear.config_kI(0, kI, 100);
    rightRear.config_kD(0, kD, 100);

    leftRear.config_kP(0, kP, 100);
    leftRear.config_kI(0, kI, 100);
    leftRear.config_kD(0, kD, 100);
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
    targetLeft = left * targetVelocity * 2048 / 600.0;
    targetRight = right * targetVelocity * 2048 / 600.0;

    // set target speeds to motors
    leftFront.set(ControlMode.Velocity, targetLeft);
    rightFront.set(ControlMode.Velocity, targetRight);
  }

  public void tankPercent(double left, double right) {
    leftFront.set(ControlMode.PercentOutput, left);
    rightFront.set(ControlMode.PercentOutput, right);
    leftRear.set(ControlMode.PercentOutput, left);
    rightRear.set(ControlMode.PercentOutput, right);
  }

  public void driveDistance(double inches) {
    double targetPosition = inches * TICKS_PER_INCH; // 10.7:1 is gear ratio
    
    this.rightFront.set(ControlMode.Position, targetPosition);
		this.leftFront.set(ControlMode.Position, targetPosition);
		this.rightRear.follow(rightFront);
		this.leftRear.follow(leftFront);
  }

  public void angleTurn(String direction) {
    if (direction.equalsIgnoreCase("left")) {
      this.tankPercent(-0.5, 0.5);
    } else if (direction.equalsIgnoreCase("right")) {
      this.tankPercent(0.5, -0.5);
    } else {
      this.tankPercent(0, 0);
    }
  }

  public double getAngle() {
		return navX.getAngle();
	}

	public void resetAngle() {
		navX.reset();
	}

  public double getPosition() {
    double front = leftFront.getSelectedSensorPosition() + rightFront.getSelectedSensorPosition();
    double rear = leftRear.getSelectedSensorPosition() + rightRear.getSelectedSensorPosition();
    double avg = (front + rear) / 4.0;
    return avg;
  }

  public void resetPosition() {
    leftFront.setSelectedSensorPosition(0);
    rightFront.setSelectedSensorPosition(0);
    leftRear.setSelectedSensorPosition(0);
    rightRear.setSelectedSensorPosition(0);
  }

  public void toggleSlow() {
    slow = !slow;
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
