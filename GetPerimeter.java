public class calculatePerimeter {
//calculate perimeter of shape which contains given number of points in a file
  
  public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
       double totalPerim = 0.0;
       // Start wth prevPt = the last point 
       Point prevPt = s.getLastPoint();
       // For each point currPt in the shape,
       for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
       }
       return totalPerim;
    }

  public void testPerimeter () {
    //select file which contains points
       FileResource fr = new FileResource();
    //
       Shape s = new Shape(fr);
      
       double Perimeter = getPerimeter(s);
       System.out.println("perimeter of the shape = " + Perimeter);  
    }

  public static void main (String[] args) {
       calculatePerimeter pr = new calculatePerimeter();
       pr.testPerimeter();
    }
  
}