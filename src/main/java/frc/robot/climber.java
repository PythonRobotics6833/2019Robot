/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.buttons.Button; 

/**
 * Add your docs here.
 */
public class climber {

    PWMVictorSPX ClimbMotor; 
    Joystick joystick;
    double MotorSpeed;
    Button On; 


    
    public climber(int VictorMotorLeft1, Joystick ClimbStick)
    {
        
        joystick=ClimbStick;
        ClimbMotor=new PWMVictorSPX(VictorMotorLeft1);
        MotorSpeed = .01; 
       
       
       
    }
    public void ClimbCon(){ 
          {
            
          }
    }

}



