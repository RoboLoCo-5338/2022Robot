package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import javax.swing.text.Position;

public class Climb extends PIDSubsystem {
    private static final double MAX_VELOCITY = 300;
	private static final double PEAK_OUTPUT = 1.0;

  // set PID values
    public static final double ANGLE_P = 0.0;
	public static final double ANGLE_I = 0.0;
	public static final double ANGLE_D = 0.0;
	public static final double ANGLE_FEED_FORWARD = 0.0;

    // encoder math ??? I don't know if this is needed for climb
    private static final double TICKS_PER_REVOLUTION = 2048;
    private static final double WHEEL_DIAMETER = 6.0;
    private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    private static final double GEAR_RATIO = 10.7 / 1;
    private static final double TICKS_PER_INCH = (TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
    

    /** Initialize Talons */
    private static WPI_TalonFX motor;
    private static WPI_TalonFX winch1;
    private static WPI_TalonFX winch2;

    //initialize solenoids
    DoubleSolenoid longArm1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,0,1);
    DoubleSolenoid longArm2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,2,3);
    DoubleSolenoid shortArm1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,4,5);
    DoubleSolenoid shortArm2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,6,7);

    public Climb() {
        super(new PIDController(ANGLE_P, ANGLE_I, ANGLE_D));

        // Do we want to move these ID's to the Constants.java file?
        motor = new WPI_TalonFX(0);
        winch1 = new WPI_TalonFX(1);
        winch2 = new WPI_TalonFX(2);

        configureTalon();

    }

    private static void configureTalon() {
        // JDE: Are current limits set - should they be set here or elsewhere?
        // https://docs.ctre-phoenix.com/en/latest/ch13_MC.html#new-api-in-2020
        motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        motor.setNeutralMode(NeutralMode.Brake);
        motor.configNeutralDeadband(0.001, 0);
        motor.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        motor.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        motor.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    
        winch1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        winch1.setNeutralMode(NeutralMode.Brake);
        winch1.configNeutralDeadband(0.001, 0);
        winch1.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        winch1.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        winch1.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    
        winch2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        winch2.setNeutralMode(NeutralMode.Brake);
        winch2.follow(winch1);
        winch2.configNeutralDeadband(0.001, 0);
        winch2.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        winch2.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        winch2.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    }
    public void setPIDF(double kP, double kI, double kD, double kF) {
        motor.config_kP(0, kP, 100);
        motor.config_kI(0, kI, 100);
        motor.config_kD(0, kD, 100);

        winch1.config_kP(0, kP, 100);
        winch1.config_kI(0, kI, 100);
        winch1.config_kD(0, kD, 100);
        
        winch2.config_kP(0, kP, 100);
        winch2.config_kI(0, kI, 100);
        winch2.config_kD(0, kD, 100);
    }
    
    public void toggleClimbMotor(){
        if(motor.getSelectedSensorPosition() != 0.0){
            motor.set(ControlMode.Position, 0.0);
        }
        else{
            motor.set(ControlMode.Position, 90.0); //TODO: 90 is a placeholder value, replace with correct number later
        }
    }
    public void toggleClimbWinch(){
        if(winch1.getSelectedSensorPosition() != 0.0){
            winch1.set(ControlMode.Position, 0.0);
            winch2.follow(winch1);
        }
        else{
            winch1.set(ControlMode.Position, 90.0); //90 is a placeholder value
            winch2.follow(winch1);

        }
    }

    public void toggleLongArms(){
        longArm1.toggle();
        longArm2.toggle();
    }

    public void toggleShortArms(){
        shortArm1.toggle();
        shortArm2.toggle();
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
