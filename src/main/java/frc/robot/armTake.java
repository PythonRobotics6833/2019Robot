/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class armTake {
    double Window;
    PWMVictorSPX Enclose; 
    boolean CloseInvert;
    public armTake(int Close, boolean CloseInvert){
        Enclose = new PWMVictorSPX(Close);
        Window = 0.1;       
        this.CloseInvert = CloseInvert;  

    }
    public void ArmTake(boolean In, boolean Out) {
        if (In)
        {
          if (CloseInvert)
          {
            Enclose.set(Window);
          }
          else
            {
            Enclose.set(-Window);
            }
    
        }
        else if (Out)
        {
          if (CloseInvert)
          {
            Enclose.set(-Window);
          }
          else
            {
            Enclose.set(Window);
            } 
        }
        else
          {
          Enclose.set(0.0);
          }
      }
    public void ArmTake(double speedMotor, double ran){

    Enclose.set(Window);
    

    }
    public void ArmTake(double Controller)
  {
    double inputSqd=(Controller*Controller);
    if(Controller<0)
    {
      Enclose.set(Window*inputSqd);
    }
    else if (Controller>0)
    {
      Enclose.set(Window*inputSqd);
      
    }
    else
    {
      Enclose.set(0.0);
     
    }
  }

}

