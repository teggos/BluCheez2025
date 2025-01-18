package frc.robot.subsystems.Subsystems.Elevator;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.util.Units;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.subsystems.Subsystems.Elevator.ElevatorConstants;

public class Elevator extends SubsystemBase{

    SparkMax rightMotor; 
    SparkMax leftMotor; 
    SparkMaxConfig rightconfig;
    SparkMaxConfig leftconfig;

    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;

    public double currentHeight;

    public Elevator(){
        rightMotor = new SparkMax(ElevatorConstants.rightMotorID, MotorType.kBrushless);
        leftMotor = new SparkMax(ElevatorConstants.leftMotorID, MotorType.kBrushless);
        rightconfig = new SparkMaxConfig();
        leftconfig = new SparkMaxConfig();
        rightconfig
            .smartCurrentLimit(40)
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        leftconfig
            .smartCurrentLimit(40)
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        leftconfig
            .follow(rightMotor, true);
        rightconfig.encoder
            .positionConversionFactor(6.75 * 2.0 * Math.PI * Units.inchesToMeters(0.918));
    
        rightMotor.configure(rightconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftMotor.configure(rightconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();
    }
    //yay
    public void setspeed(double speed){

        currentHeight = rightEncoder.getPosition();

        if (speed > 0.0 && currentHeight <= ElevatorConstants.MaxHeight){
            rightMotor.set(speed);
        }
        else{
            rightMotor.set(0.0);
        }
        
    }

    public double getPosition(){
        return rightEncoder.getPosition();
    }

    public double getVelocity(){
        return rightEncoder.getVelocity();
    }

    public void resetEncoders(){
       rightEncoder.setPosition(0);
       leftEncoder.setPosition(0);
    }


}
