import java.util.Date;

import org.ejml.Utility;

import Jama.Matrix;
import Jama.SingularValueDecomposition;


public class SVDPseudoInverseMGN extends Utility{
	  public static double[][] inverse(double[][] A)
	  {
	    return (new Matrix(A,true)).inverse().getArray();
	  }
	  public static double[][] pseudoInverse(double[][] A)
	  {
	  	double[][] At = transpose(A);
	  	double[][] AtA = mtimes(At, A);
	  	double[][] AtAi = inverse(AtA);
	  	to1D(A);
	  	return mtimes(AtAi, At);
	  }
	  public static double[] ldivide(double[][] A, double[] b)
	  {
	  	double[][] B = transpose(new double[][]{b});
	    double[][] X = mtimes(pseudoInverse(A), B);
	    
	    return to1D(X);
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
	  	System.out.println("SVDPseudoInverse#Calculating U S V^T Using Matrix A");
	  	System.out.println("SVDPseudoInverse#Calculating U");
	    println(svd.getU().getArray());
	    System.out.println("SVDPseudoInverse#Calculating S");
	    println(svd.getS().getArray());
	    System.out.println("SVDPseudoInverse#Calculating V");
	    println(svd.getV().getArray());
	    System.out.println("SVDPseudoInverse#Calculating V^T");
	    println(transpose(svd.getV().getArray()));
	    System.out.println("SVDPseudoInverse#U S V^T");
	    println(inverse(multiply(multiply(svd.getU().getArray(),svd.getS().getArray()),transpose(svd.getV().getArray()))));
	    System.out.println("SVDPseudoInverse#Singular Values");
	    Matrix singularValues = new Matrix(svd.getSingularValues(), 1);
	    println(singularValues.getArray());
	  }
	  public static double[][] multiply(double[][] A, double[][] B) {
	      int mA = A.length;
	      int nA = A[0].length;
	      int mB = B.length;
	      int nB = A[0].length;
	      //if (nA != mB) throw new RuntimeException("SVDPseudoInverse#Illegal matrix dimensions.");
	      double[][] C = new double[mA][nB];
	      for (int i = 0; i < mA; i++)
	          for (int j = 0; j < nB; j++)
	              for (int k = 0; k < nA; k++)
	                  C[i][j] += (A[i][k] * B[k][j]);
	      return C;
	  }

	  public static void main(String args[])
	  {
		System.out.println("SVDPseudoInverse#Starting Time="+new Date());
		long startTime=System.currentTimeMillis();
	  	double[][] A = new double[][]{
	  			{1,3,2},
	  			{1,1,1}
	  	};
		long totalmemoryStart=pseudoMemoryStart(A);
	  	System.out.println("SVDPseudoInverse#Original Matrix A");
	  	println(A);
	  	System.out.println("SVDPseudoInverse#Apply SVD PseudoInverse On Original Matrix A");
	  	printSVD(A);
	  	System.out.println("SVDPseudoInverse#Calculating Inverse Using PseusoInverse Technique");
	  	println(pseudoInverse(A));
	  	long endTime=System.currentTimeMillis();
	  	System.out.println("SVDPseudoInverse#End Time="+new Date());
	  	System.out.println("SVDPseudoInverse#Total Time To Calculate Inverse Using Pseuso Inverse="+(endTime-startTime)+" miliseconds");
	  	long totalmemoryLast=pseudoMemoryEnd(A);
	    System.out.println("Used memory is bytes: " + (totalmemoryStart-totalmemoryLast));
	  }

}
