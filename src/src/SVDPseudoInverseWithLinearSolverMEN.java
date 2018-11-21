import java.util.Date;

import org.ejml.LinearSolverInverse;
import org.ejml.Utility;

import Jama.Matrix;
import Jama.SingularValueDecomposition;


public class SVDPseudoInverseWithLinearSolverMEN extends Utility{
	  static double t[];
	  static int n;
	  public static double[][] inverse(double[][] A)
	  {
	    return (new Matrix(A)).inverse().getArray();
	  }
	  public static double[][] inveseUsingLinearSolver(double[][] A) {
		  LinearSolverInverse ls=new LinearSolverInverse();
		  return ls.inveseUsingLinearSolver(A);
	    }
	  public static double[] ldivide(double[][] A)
	  {
	  	SingularValueDecomposition svd = new SingularValueDecomposition(new Matrix(A));
	  	double[][] S = svd.getS().getArray();
	  	int m = A.length;
	  	int n = A[0].length; 
	    double minw = Double.MAX_VALUE;
	    int mini = 0;
	    
	    for(int i=0; i<((m<n)?m:n); i++){
	    	if(S[i][i] <= minw){
	    		minw = S[i][i];
	    		mini = i;
	    	}
	    }
	    
	    return svd.getV().transpose().getArray()[mini];
	  }
	  public static void printSVD(double[][] A)
	  {
	  	SingularValueDecomposition svd = new SingularValueDecomposition(new Matrix(A));
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Calculating U S V^T Using Matrix A");
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Calculating U");
	    println(svd.getU().getArray());
	    System.out.println("SVDPseudoInverseWithLinearSolver#Calculating S");
	    println(svd.getS().getArray());
	    System.out.println("SVDPseudoInverseWithLinearSolver#Calculating V");
	    println(svd.getV().getArray());
	    System.out.println("SVDPseudoInverseWithLinearSolver#Calculating V^T");
	    println(transpose(svd.getV().getArray()));
	    System.out.println("SVDPseudoInverseWithLinearSolver#U S V^T");
	    println(inverse(multiply(multiply(svd.getU().getArray(),svd.getS().getArray()),transpose(svd.getV().getArray()))));
	    System.out.println("SVDPseudoInverseWithLinearSolver#Singular Values");
	    Matrix singularValues = new Matrix(svd.getSingularValues(), 1);
	    println(singularValues.getArray());
	  }
	  public static double[][] multiply(double[][] A, double[][] B) {
	      int mA = A.length;
	      int nA = A[0].length;
	      int mB = B.length;
	      int nB = A[0].length;
	      //if (nA != mB) throw new RuntimeException("SVDPseudoInverseWithLinearSolver#Illegal matrix dimensions.");
	      double[][] C = new double[mA][nB];
	      for (int i = 0; i < mA; i++)
	          for (int j = 0; j < nB; j++)
	              for (int k = 0; k < nA; k++)
	                  C[i][j] += (A[i][k] * B[k][j]);
	      return C;
	  }

	  public static void main(String args[])
	  {
		
		System.out.println("SVDPseudoInverseWithLinearSolver#Starting Time="+new Date());
		long startTime=System.currentTimeMillis();
		double[][] A = new double[][]{
				{1,0,0},
				{-2,0,0,},
				{4,6,1}
	  	};
	  	long totalmemoryStart=lsMemoryStart(A);
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Original Matrix A");
	  	println(A);
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Apply SVD PseudoInverse On Original Matrix A");
	  	printSVD(A);
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Calculating Inverse Using LinearSolver Technique");
	  	println(inveseUsingLinearSolver(A));
	  	long endTime=System.currentTimeMillis();
	  	System.out.println("SVDPseudoInverseWithLinearSolver#End Time="+new Date());
	  	System.out.println("SVDPseudoInverseWithLinearSolver#Total Time To Calculate Inverse Using Linear Solver="+(endTime-startTime)+" miliseconds");
	  	long totalmemoryLast=lsMemoryEnd(A);
	    System.out.println("Used memory is bytes: " + (totalmemoryStart-totalmemoryLast));
	  }

}
