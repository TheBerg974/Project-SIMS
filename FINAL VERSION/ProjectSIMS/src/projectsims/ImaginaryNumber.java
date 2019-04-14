/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berge
 */
public class ImaginaryNumber {
    
    private double real;
    private double imaginary;

    /**
     * Creates an imaginary number with a specified real and imaginary component
     * @param real
     * @param imaginary 
     */
    public ImaginaryNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    /**
     * Adding two Imaginary number together
     * @param num the number added to the current number
     */
    public void add(ImaginaryNumber num) {
        this.real += num.getReal();
        this.imaginary += num.getImaginary();
    }
    
    /**
     * Multiplies two Imaginary Numbers
     * @param num the imaginary number multiplied to the current imaginary number
     * @return the multiplied imaginary number
     */
    public ImaginaryNumber mult(ImaginaryNumber num) {
        double real = (this.real*num.getReal()) - (this.imaginary*num.getImaginary());
        double imaginary = (this.real*num.getImaginary()) +  (num.getReal()*this.imaginary);
        return new ImaginaryNumber(real, imaginary);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }
    
    /**
     * This method transforms the List of points into a List of Imaginary Numbers
     * @param points The List of points in the CanvasPane
     * @return The List of Imaginary numbers that represent the points
     */
    public static ArrayList<ImaginaryNumber> toImaginaryNumbers(List<Point> points) {
        ArrayList<ImaginaryNumber> imaginaryNums = new ArrayList<>();
        for(int i = 0; i < points.size(); i+=5) {
            double x = points.get(i).getX();
            double y = points.get(i).getY();
            ImaginaryNumber tempNum = new ImaginaryNumber(x, y);
            imaginaryNums.add(tempNum);
        }
        return imaginaryNums;
    }
    
    /**
     * This method gets the radius of the imaginary and real component of a Imaginary Number
     * @return The radius of an imaginary number
     */
    public double getRadius() {
        return Math.sqrt(this.real*this.real + this.imaginary* this.imaginary);
    }
    
    /**
     * This method gets the Phase of an Imaginary Number
     * @return The phase of an imaginary Number
     */
    public double getPhase() {
        return Math.atan2(this.imaginary, this.real);
    }
    
    
    
}
