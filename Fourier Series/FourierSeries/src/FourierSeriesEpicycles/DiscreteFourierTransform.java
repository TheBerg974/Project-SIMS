/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesEpicycles;

import FourierSeriesEpicycles.Epicycle;
import FourierSeriesEpicycles.Point;
import java.util.ArrayList;

/**
 *
 * @author berge
 */
public class DiscreteFourierTransform {
    
    private ArrayList<Point> points;

    public DiscreteFourierTransform(ArrayList<Point> points) {
        this.points = points;
    }
    
    
    public ArrayList<Epicycle> discreteFourierTransform() {
        ArrayList<Epicycle> array = new ArrayList<>();
        ArrayList<ImaginaryNumber> Xn = ImaginaryNumber.toImaginaryNumbers(this.points);
        int N = Xn.size();
        double x = 200;
        double y = 200;
        for(int k = 0; k < N; k++) {
            ImaginaryNumber sum = new ImaginaryNumber(0, 0);
            for(int n = 0; n < N; n++) {
                double phi = ((2*Math.PI)*k*n)/N;
                double real = Math.cos(phi);
                double imaginary = Math.sin(phi);
                ImaginaryNumber c = new ImaginaryNumber(real, -imaginary);
                sum.add(Xn.get(n).mult(c));
            }
            sum.setReal(sum.getReal()/N);
            sum.setImaginary(-1*sum.getImaginary()/N);

            double frequency = k;
            double radius = sum.getRadius();
            double phase = sum.getPhase();
            Epicycle epi = new Epicycle(x, y, radius, phase, frequency);
            x = epi.computePhasorX(phase);
            y = epi.computePhasorY(phase);
            
            array.add(epi);
        }
        return array;
    }
    
}
