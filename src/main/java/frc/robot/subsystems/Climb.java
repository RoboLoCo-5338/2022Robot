package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;


public class Climb extends PIDSubsystem {
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
    // private static final double GEAR_RATIO = 10.7 / 1; //TODO: change to actual value on climb
    // private static final double TICKS_PER_INCH = (TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
    

    /** Initialize Talons */
    // private static WPI_TalonFX armMotor;
    private static WPI_TalonFX winch1;

    //initialize solenoids
    //placeholder CAN IDs rn
    DoubleSolenoid longArms;
    DoubleSolenoid shortArms;


    public Climb() {
        super(new PIDController(ANGLE_P, ANGLE_I, ANGLE_D));

        // armMotor = new WPI_TalonFX(Constants.CLIMB_MOTOR_ID);
        winch1 = new WPI_TalonFX(Constants.WINCH_1_ID);

        longArms = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.LONG_ARM_ID[0],Constants.LONG_ARM_ID[1]);
        longArms.set(Value.kReverse);

        shortArms = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.SHORT_ARM_ID[0],Constants.SHORT_ARM_ID[1]);
        shortArms.set(Value.kReverse);

        configureTalon();
    }

    private static void configureTalon() {
        // JDE: Are current limits set - should they be set here or elsewhere?
        // https://docs.ctre-phoenix.com/en/latest/ch13_MC.html#new-api-in-2020
        // armMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        // armMotor.setNeutralMode(NeutralMode.Brake);
        // armMotor.configNeutralDeadband(0.001, 0);
        // armMotor.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        // armMotor.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        // armMotor.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    
        winch1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 100);
        winch1.setNeutralMode(NeutralMode.Brake);
        winch1.configNeutralDeadband(0.001, 0);
        winch1.setStatusFramePeriod(StatusFrame.Status_1_General, 5, 0);
        winch1.setControlFramePeriod(ControlFrame.Control_3_General, 5);
        winch1.configClosedLoopPeakOutput(0, PEAK_OUTPUT, 100);
    }
    public void setPIDF(double kP, double kI, double kD, double kF) {
        // armMotor.config_kP(0, kP, 100);
        // armMotor.config_kI(0, kI, 100);
        // armMotor.config_kD(0, kD, 100);

        winch1.config_kP(0, kP, 100);
        winch1.config_kI(0, kI, 100);
        winch1.config_kD(0, kD, 100);
    }
    
    // public void climbToPos(double pos){
    //     armMotor.set(ControlMode.Position, pos); 
    // }

    // public void climbPercent(double speed) {
    //     armMotor.set(ControlMode.PercentOutput, speed);
    // }

    // public double getMotorPosition() {
    //     return armMotor.getSelectedSensorPosition();
    // }

    public void winchToPos(double pos){
        winch1.set(ControlMode.Position, pos);
    }

    public void winchPercent(double speed) {
        winch1.set(ControlMode.PercentOutput, speed);
    }

    public double getWinchPosition() {
        return winch1.getSelectedSensorPosition();
    }

    public void longToggle() {
        longArms.toggle();
    }

    public void longReverse() {
        longArms.set(Value.kForward);
    }

    public void shortToggle() {
        shortArms.toggle();
    }

    public void keepEncoderValues() {}

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
