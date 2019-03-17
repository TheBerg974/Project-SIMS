/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesCanvas;

import java.util.ArrayList;
import FourierSeriesEpicycles.Point;

/**
 *
 * @author berge
 */
public class ImaginaryNumber {
    
    private double real;
    private double imaginary;

    public ImaginaryNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    public void add(ImaginaryNumber num) {
        this.real += num.getReal();
        this.imaginary += num.getImaginary();
    }
    
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
    
    public static ArrayList<ImaginaryNumber> toImaginaryNumbers(ArrayList<Point> points) {
        ArrayList<ImaginaryNumber> imaginaryNums = new ArrayList<>();
        for(int i = 0; i < points.size(); i+=5) {
            double x = points.get(i).getX();
            double y = points.get(i).getY();
            ImaginaryNumber tempNum = new ImaginaryNumber(x, y);
            imaginaryNums.add(tempNum);
        }
        return imaginaryNums;
    }
    
    public double getRadius() {
        return Math.sqrt(this.real*this.real + this.imaginary* this.imaginary);
    }
    
    public double getPhase() {
        return Math.atan2(this.imaginary, this.real);
    }
    
    
    
}
