package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Intake {
   //pneumatics on the right
   //pneumatics on the left
   //both need to go at the same time
   //motor, starts when the pneumatics start
   private DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);// right solenoid object, ports are placeholders
   private DoubleSolenoid leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);//left solenoid object, ports are placeholders
   private WPI_TalonFX intakeMotor = new WPI_TalonFX(2);
    
   rightSolenoid.set(kForward);
   leftSolenoid.set(kForward);


   public void extendIntake(){
      if (m_controller.getYButtonPressed()) {//the controller name and button are placeholders
         rightSolenoid.toggle();
         leftSolenoid.toggle();
      } 
   }
   


}
