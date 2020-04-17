
public class MatrixCalculation {
	
	private static boolean CheckSquareMatrix( double[][] x ) {
		boolean b = true;
		for(int i=0;i<x.length;i++) {
			if( x.length != x[i].length ) {
				b = false;
			}
		}
		return b;
	}
	
	public static double[][] IdentityMatrix( int x ) {
		double[][] e = new double[x][x];
		for(int i=0;i<x;i++) {
			for(int w=0;w<x;w++) {
				if( i == w ) {
					e[i][w] = 1;
				} else {
					e[i][w] = 0;
				}
			}
		}
		return e;
	}
	
	public static void MatrixPrint( double[][] x ) {
		if(x != null){
			int n = MaxDigit( x );
			System.out.println("[");
			for(int i=0;i<x.length;i++) {
				for(int w=0;w<x[i].length;w++) {
					System.out.print(" ");
					System.out.printf("%"+ n + "f",x[i][w]);
				}
				System.out.print("\n");
			}
			System.out.println("]");
		} else {
			System.out.println("Matrix is null.");
		}
	}
	
	public static double[][] MatrixProduct( double[][] x, double[][] y ) {
		if(CheckSquareMatrix( x ) && CheckSquareMatrix( y )) {
			if(x[0].length == y.length) {
				double[][] product = new double[x.length][y[0].length];
				for(int i=0;i<x.length;i++) {
					for(int w=0;w<y[i].length;w++) {
						for(int v=0;v<x[i].length;v++) {
							product[i][w] += x[i][v] * y[v][w];
						}
					}
				}
				return product;
			} else {
				System.out.println( "Matrix type mismatch." );
				return null;
			}
		} else {
			System.out.println( "It's not SquareMatrix." );
			return null;
		}
	}
	
	public static double[][] MatrixSum( double[][] x, double[][] y ){
		if(CheckSquareMatrix( x ) && CheckSquareMatrix( y )) {
			if(x.length == y.length && x[0].length == y[0].length) {
				for(int i=0;i<x.length;i++) {
					for(int w=0;w<x[i].length;w++) {
						x[i][w] = x[i][w] + y[i][w];
					}
				}
				return x;
			} else {
				System.out.println( "Matrix type mismatch" );
				return null;
			}
		} else {
			System.out.println( "It's not SquareMatrix." );
			return null;
		}
	}
	
	private static int MaxDigit( double[][] x ) {
		int max = 0;
		for(int i=0;i<x.length;i++) {
			for(int w=0;w<x[i].length;w++) {
				if(max < Double.toString(x[i][w]).length()) {
					max = Double.toString(x[i][w]).length();
				}
			}
		}
		return max;
	}
}

