/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class ButterFlyLift
 {
     TalonSRX Bottom;  
     TalonSRX Middle;
     double speedBot;
     double speedMid;
     ControlMode Up;
     Joystick Player2; 
     VictorSP pivotPoint; 

    boolean CargoHeight;
    public ButterFlyLift(int Talon1, int Talon2){
        Bottom = new TalonSRX(Talon1);
        Middle = new TalonSRX(Talon2);
        
        speedBot = 0.1;
        speedMid = 0.1; 
                                                }
    public ButterFlyLift(int Victor, Joystick Player2)
    {
        pivotPoint= new VictorSP(Victor);
        this.Player2=Player2;
        CargoHeight=false;
    }

    public void speed (double speedBot, double speedMid){
        Bottom.set(Up, speedBot);
        Middle.set(Up, speedMid);

    }
    //Call this method in order to manually move th arm based on the joystick
    public void analogMove()
    {
        pivotPoint.setSpeed(Player2.getRawAxis(1)/4);
    }
    public void digitalMove()
    {   
        double fineHeight= Player2.getRawAxis(2);
        //
        int heightLevel;
        if(Player2.getRawAxis(2)>=0.5)
        {
            CargoHeight=true;
        }
        else if(Player2.get)
    }

}

