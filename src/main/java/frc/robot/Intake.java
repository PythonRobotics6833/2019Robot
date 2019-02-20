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
//make a button boolean 
//
public class Intake {
    double Window;
    PWMVictorSPX Enclose; 
    boolean CloseInvert;
    Joystick stick; 
    
  public Intake(int Close, Joystick stick1){
        Enclose = new PWMVictorSPX(Close);
        Window = 0.1;       
         this.stick=stick1;

    }
    public void ArmTake() {
        if (stick.getRawButton(5))
        {

            Enclose.set(Window);
          }
          else if(stick.getRawButton(6))
            {
            Enclose.set(-Window);
            }
    else 
    {
      Enclose.set(0.0);
    }
       
         
       }
      
    public void speed(double speedMotor){

    Enclose.set(Window);
    

    }
   /* public void Controlling(double Controller)
  {
    double input=(Controller*Controller);
    if(Controller<0)
    {
      Enclose.set(Window*input);
    }
    else if (Controller>0)
    {
      Enclose.set(Window*input);
      
    }
    else
    {
      Enclose.set(0.0);
     
    }
  } */
    public void holdPossition (boolean hold)
    {
      if(hold) {
        Enclose.set(-.195);
        
      }
      else
        {
          Enclose.set(0);
         } 
    }
    public void Controlling(Joystick stick2)
    {
      if(stick2.getRawAxis(1) < -0.5)
      {
        Enclose.set(-Window);
      }
      else if(stick2.getRawAxis(1)>0.5)
      {
        Enclose.set(Window);
      }
      else
        {
          Enclose.set(0);
        }
  
      }
    public void ConAxis(){
      Enclose.setSpeed(stick.getRawAxis(5)/2);

    }
  }



