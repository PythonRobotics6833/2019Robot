/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class Intake {
    double WindowSpeed;
    PWMVictorSPX WindowMotor;
    Joystick stick; 
    
  public Intake(int Close, Joystick stick1){
        WindowMotor = new PWMVictorSPX(Close);
        WindowSpeed = 1;       
         this.stick=stick1;
    
    }

    //moves the window motor using the bumpers on the controllers
    public void ControllerAxis(){

      if(stick.getRawAxis(3) > 0){
            WindowMotor.set(WindowSpeed);
      }
  
      if(stick.getRawAxis(2) > 0){
         WindowMotor.set(-WindowSpeed);
      }
      else if(stick.getRawAxis(2) == 0 && stick.getRawAxis(3) == 0)
      {
      
        WindowMotor.set(0.0);
      }

    }
  }



