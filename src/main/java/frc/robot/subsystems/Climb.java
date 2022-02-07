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

public class Climb extends PIDSubsystem {
	public static Constants constants = new Constants();  // TODO: You don't need to create a constants object. If you create static variables, you can refer to them as Constants.variableName
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
    /** TODO: Do NOT capitalize variable names unless they are final */
    private static WPI_TalonFX Motor;
    private static WPI_TalonFX Winch1;
    private static WPI_TalonFX Winch2;

    //initialize solenoids
    DoubleSolenoid longArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);
    DoubleSolenoid shortArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);

    public Climb() {
        super(new PIDController(ANGLE_P, ANGLE_I, ANGLE_D));

        // Do we want to move these ID's to the Constants.java file?
        Motor = new WPI_TalonFX(0);
        Winch1 = new WPI_TalonFX(1);
        Winch2 = new WPI_TalonFX(2);

        configureTalon();

    }

    private static void configureTalon() {
        // JDE: Are current limits set - should they be set here or elsewhere?
        // https://docs.ctre-phoenix.com/en/latest/ch13_MC.html#new-api-in-2020
        Motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        Motor.setNeutralMode(NeutralMode.Brake);
        Motor.configNeutralDeadband(0.001, 0);
        Motor.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        Motor.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        Motor.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    
        Winch1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        Winch1.setNeutralMode(NeutralMode.Brake);
        Winch1.configNeutralDeadband(0.001, 0);
        Winch1.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        Winch1.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        Winch1.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    
        Winch2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        Winch2.setNeutralMode(NeutralMode.Brake);
        Winch2.follow(Winch1);
        Winch2.configNeutralDeadband(0.001, 0);
        Winch2.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        Winch2.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        Winch2.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    }
    public void setPIDF(double kP, double kI, double kD, double kF) {
        Motor.config_kP(0, kP, 100);
        Motor.config_kI(0, kI, 100);
        Motor.config_kD(0, kD, 100);

        Winch1.config_kP(0, kP, 100);
        Winch1.config_kI(0, kI, 100);
        Winch1.config_kD(0, kD, 100);
        
        Winch2.config_kP(0, kP, 100);
        Winch2.config_kI(0, kI, 100);
        Winch2.config_kD(0, kD, 100);
    }
	
    // TODO: Name the below methods differently or comment specifically what they do
    
    public void setMotor(double val){
        Motor.set(ControlMode.PercentOutput, val);
    }
    public void setWinch(double val){
        Winch1.set(ControlMode.PercentOutput, val);
        Winch2.follow(Winch1);
    }

    public void shortArm(){
        longArm.toggle();
    }

    public void longArm(){
        longArm.toggle();
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
