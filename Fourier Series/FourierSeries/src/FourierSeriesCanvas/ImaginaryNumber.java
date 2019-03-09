/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesCanvas;

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
    
    public void mult(ImaginaryNumber num) {
        double real = (this.real*num.getReal()) - (this.imaginary*num.getImaginary());
        double imaginary = (this.real*num.getImaginary()) +  (num.getReal()*this.imaginary);
        this.real = real;
        this.imaginary = imaginary;
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
    
    
    
}
