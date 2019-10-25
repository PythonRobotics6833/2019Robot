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
    PWMVictorSPX WindowMotor2;
    Joystick stick;
    /*since joysticks aren't entirely accurate, 
    they could be like .01 and still be runninng in the "if"
    so we set a deadzone, so unless its greater than ".15"
    it won't run */
    double deadZone=0.15; 
    private float voltage_d; 
    
  public Intake(int Close, int Close2, Joystick stick1){
        //the motors are the left and right side
        WindowMotor = new PWMVictorSPX(Close);
        WindowMotor2 = new PWMVictorSPX(Close2);
        WindowSpeed = .7;       
         this.stick=stick1;
    
    }

    //moves the window motor using the bumpers on the controllers
    public void ControllerAxis(){

      if(stick.getRawAxis(3) > deadZone){
            WindowMotor.set(-stick.getRawAxis(3)/2-voltage_d);
            WindowMotor2.set(stick.getRawAxis(3)/2-voltage_d);
      }
      else if(stick.getRawAxis(2) > deadZone){
         WindowMotor.set(stick.getRawAxis(2)/2-voltage_d);
         WindowMotor2.set(-stick.getRawAxis(2)/2-voltage_d);
      }
      else
      {
      
        WindowMotor.set(0.0);
        WindowMotor2.set(0.0);
      }

    }
  }



