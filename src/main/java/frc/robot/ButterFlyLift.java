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
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class ButterFlyLift
 {
     TalonSRX Bottom;  
     TalonSRX Middle;
     double armSpeed=0.5;
     ControlMode Up;
     Joystick Player2; 
     VictorSP armMotor; 

    boolean CargoHeight;

    boolean lowLevelButton;
    boolean midLevelButton;
    boolean highLevelButton;
    //This variable controls the height levels for the butterfly lift
    //when it is set to 0 it is at the lowest level
    //When it is set at 1 it is at the mid level of the rocket
    //When it is set to 2 it is at the heightest level of the robot
    //When it is set to -1 is a general Error
    int heightLevel;
    //this is a placeholder until we install and test the encoder
    double encoder;
    //this is added and subtracted to the encoder position values to give it a range in which it will stop instead of an exact value
    double deadZone;
    //Digital input for a switch at the bottom of the arm
    DigitalInput LowPoint;
    //These two varaibles will be filled with the ecoder posision that is required to be at a certain height.
    Double midLevelEncoderV;
    Double highLevelEncoderV;
    //This is the value added to the encoder position so the arm can find its position
    Double cargoHeightEncoderV;

    //Constructor for Talons
    public ButterFlyLift(int Talon1, int Talon2)
    {
        Bottom = new TalonSRX(Talon1);
        Middle = new TalonSRX(Talon2);
        
    }
    public ButterFlyLift(int Victor, Joystick Player2)
    {
        armMotor= new VictorSP(Victor);
        this.Player2=Player2;
        CargoHeight=false;

        LowPoint= new DigitalInput(0);
    }

    public void speed (double speedBot, double speedMid){
        Bottom.set(Up, speedBot);
        Middle.set(Up, speedMid);

    }
    //Call this method in order to manually move th arm based on the joystick
    public void analogMove()
    {
        armMotor.setSpeed(Player2.getRawAxis(1)/4);
    }
    //This uses the controller to set the arm to direct positions on the robot
    public void digitalMove()
    {   
        double fineHeight= Player2.getRawAxis(2);
        lowLevelButton=Player2.getRawButtonPressed(1);
        midLevelButton=Player2.getRawButtonPressed(2);
        highLevelButton=Player2.getRawButtonPressed(3);
        if(fineHeight>=0.5)
        {
            CargoHeight=true;
        }
        else if(fineHeight<=-0.5)
        {
            CargoHeight=false;
        }

        if(lowLevelButton)
        {
            heightLevel=0;
        }
        else if(midLevelButton)
        {
            heightLevel=1;
        }
        else if(highLevelButton)
        {
            heightLevel=2;
        }        

        switch(heightLevel)
        {
            case 0: setLevel(0, CargoHeight);
            break;

            case 1: setLevel(1, CargoHeight);
            break;

            case 2: setLevel(2, CargoHeight);
            break;

            default: setLevel(0, CargoHeight);
            break;
        }
        
    }
    //This method is used to actuate the arm to its position by using the encoder as its guide
    private void setLevel(int level, boolean heightlevel)
    {
        if(level ==0)
        {
            if(LowPoint.get())
            {
                return;
            }
            else{
                armMotor.setSpeed(-armSpeed);;
            }
        }
        else if(level == 1)
        {
            if(encoder<midLevelEncoderV-deadZone)
            {
                armMotor.setSpeed(armSpeed);
            }
            else if(encoder>midLevelEncoderV+deadZone)
            {
                armMotor.setSpeed(-armSpeed);
            }
            else
            {
                armMotor.setSpeed(0);
            }
        }
        else if(level == 2)
        {
            if(encoder<highLevelEncoderV-deadZone)
            {
                armMotor.setSpeed(armSpeed);
            }
            //This should never happen but if something goes wrong with the gears this will force it to back off.
            else if(encoder>highLevelEncoderV+deadZone)
            {
                armMotor.setSpeed(-armSpeed);
            }
            else
            {
                armMotor.setSpeed(0);
            }
        }
        else
        {
            level =0;
            System.out.print("Error:");
        }
    }

}

