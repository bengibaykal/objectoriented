import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

import static java.lang.System.*;

import java.util.Scanner;


public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {  
       
        int numberOfPoints = 0;
        for ( Point p : s.getPoints() ) {
        numberOfPoints++;
       }
        return numberOfPoints;
    }

     public int getNumberOfSides(Shape s) {
        int numberOfSides = getActualPoints(s).size();    
        return numberOfSides;
    }
    
    public ArrayList<Point> getActualPoints(Shape s) {
        Point current = new Point(0,0);
        Point next = new Point(0,0);
        Point nextnext = new Point(0,0);
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        int numberOfPoints = 0;
        int numberOfSides = 0;
        for ( Point p : s.getPoints() ) {
            pointArrayList.add(p);
            numberOfPoints++;
        }
    
        
        //calculate number of sides by excluding linear points

        numberOfSides = numberOfPoints;
        for ( int i=0 ; i<(pointArrayList.size() - 2) ; i++){
            current = pointArrayList.get(i);
            next = pointArrayList.get(i+1);
            nextnext = pointArrayList.get(i+2);
        
            double egim1 = getGradient((next.getY()-current.getY()), (next.getX() - current.getX()));
            double egim2 = getGradient((nextnext.getY()-current.getY()), (nextnext.getX() - current.getX()));
            if ( egim1 == egim2){
                numberOfSides--;
                pointArrayList.remove(next);
            }
        }
        System.out.println(pointArrayList);
        return pointArrayList;
    }
    
    public double getGradient(int n, int d) {
       double fraction = 0;
       if (d == 0){
         fraction = 0;
       } else {
         fraction = n/d;
       }
       return fraction;      
    }
        

    public double getLargestSide(Shape s) {
        ArrayList<Point> hede = getActualPoints(s);
        double distPrev = 0;
        
        for(int i = 0; i<(hede.size() - 1); i++) {
            Point curr = hede.get(i);
            Point next = hede.get(i+1);
            double distCurr = curr.distance(next);
            if(distCurr > distPrev){
                distPrev = distCurr;
            }
            double update = hede.get(hede.size() - 1).distance(hede.get(0));
            if(update > distPrev) {
               distPrev = update;
            }
        }
        return distPrev;
    }

    
    public void testPerimeter () {
       FileResource fr = new FileResource();
       Shape s = new Shape(fr);
        
        
       int numPoints = getNumPoints(s);
       System.out.println("Points number = " + numPoints);
       
        //calculate actual number of sides by excluding linear points
       int numSides = getNumberOfSides(s);
       System.out.println("Sides number = " + numSides); 
    }

    public static void main (String[] args) {
       PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
       pr.testPerimeter();
    }
}
