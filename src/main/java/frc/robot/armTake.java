/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Joystick;
/**
 * Add your docs here.
 */
public class armTake {
    Joystick P2;
    double Window;
    PWMVictorSPX Enclose;
    ControlMode Arm; 
    boolean CloseInvert;
    public armTake(int Close, boolean CloseInvert, int Joystick){
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
    public void ArmTake(double speedMotor){

    Enclose.set(Window);
    

    }

}

