/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
/*import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
*/


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot{
  /*private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  */
  //makes the variables 
   Joystick stick;
   Joystick Controller2; 
   drivetrain DriveTrain;
   Intake arm; 
   Climber Climb; 
   ButterFlyLift lift; 
   //Tilt tilt;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    //m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);

    //sets up the ports 
    stick = new Joystick(0); 
    Controller2= new Joystick(1);
     DriveTrain= new drivetrain(0,1,2,3 ,stick);
    arm = new Intake(8, 9, Controller2);
    Climb = new Climber (5, stick);
    lift = new ButterFlyLift(6, Controller2);
    
    //arm = new Intake(8, Controller2);
    //tilt = new Tilt(9, Controller2);
  //  shooter = new Shooter(8, 9, Controller2);
    //Old/Not Complicated Camera Setup
// CameraServer.getInstance().startAutomaticCapture(0);
 //CameraServer.getInstance().startAutomaticCapture(1);
 

   //only have the thread or the above, not both

  
   new Thread(() -> {
        boolean allowCam1=false;
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("Cam1",0);
        camera.setResolution(640, 480);
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Cam2",1);
        camera1.setResolution(640, 480);

        CvSink cvSink1 = CameraServer.getInstance().getVideo(camera);
        CvSink cvSink2 = CameraServer.getInstance().getVideo(camera1);
        //CvSource outputStream = CameraServer.getInstance().putVideo("Both", 640, 480);

       //m Mat image = new Mat();

     /*   while(!Thread.interrupted()) {
          if(stick.getRawButton(1)) {
            allowCam1 = !allowCam1;
          }

            if(allowCam1){
              cvSink2.setEnabled(false);
              cvSink1.setEnabled(true);
              cvSink1.grabFrame(image,2);
            } else{
              cvSink1.setEnabled(false);
              cvSink2.setEnabled(true);
              cvSink2.grabFrame(image, 2);     
            }
            outputStream.putFrame(image);
        } */
    }).start(); 
    
   
    
  }
    /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }


  @Override
  public void autonomousPeriodic() {
  
       //this space calls it to function 
  
   //this one is for regular drive
   //DriveTrain.tankdrive();
   //this boolean is for switching between one stick and two stick drive 
   boolean DriveSwitch = stick.getRawButton(5);
   //moving the arm with a stick
   lift.analogMove();
   //moves the tilt on a stick 
   //tilt.stickMove();
  // shooter.intake();
   //Moves the the latch based on left and right bumpers 
   arm.ControllerAxis(); 
   //the if to switcing to driving and climbing 
  if (DriveSwitch == true)
    {
      //tankDrive2 is the one stick drive (only forward and back)
     DriveTrain.tankDrive2();
     Climb.climbCon();
    }
   else
    {
     DriveTrain.tankdrive();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    //this space calls it to function 
  
   //this one is for regular drive
   //DriveTrain.tankdrive();
   //this boolean is for switching between one stick and two stick drive 
   boolean DriveSwitch = stick.getRawButton(5);
    
    lift.analogMove();
    //moves the tilt on a stick 
    //tilt.stickMove();
   // shooter.intake();
    //Moves the the latch based on left and right bumpers 
    arm.ControllerAxis(); 
    //the if to switcing to driving and climbing 
   if (DriveSwitch == true)
     {
       //tankDrive2 is the one stick drive (only forward and back)
      DriveTrain.tankDrive2();
      Climb.climbCon();
     }
    else 
     {
      DriveTrain.tankdrive();
     }
    
   
  }
    /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
