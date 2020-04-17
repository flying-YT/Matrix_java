
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
	
	public static double Det( double[][] x ) {
		double returnValue = 0;
		if(CheckSquareMatrix( x )) {
			double[][] l = new double[x.length][x[0].length];
			double[][] u = new double[x.length][x[0].length];
			for(int i=0;i<x.length;i++) {
				l[i][i] = 1;
				u[0][i] = x[0][i];
			}
			int count = 1;
			int line = 1;
			int column = 1;
			while(count <= (x.length-1)*2) {
				if(count%2 != 0) {
					for(int i=line;i<x.length;i++) {
						double n = 0;
						for(int w=0;w<x[0].length;w++) {
							if(w != (line-1)) {
								n += l[i][w] * u[w][line-1];
							}
						}
						l[i][column-1] = (x[i][column-1]-n) / u[line-1][column-1];
					}
					column++;
				} else {
					for(int i=(column-1);i<x[0].length;i++) {
						double n = 0;
						for(int w=0;w<x.length;w++) {
							if(w != (column-1)) {
								n += l[column-1][w] * u[w][i];
							}
						}
						u[line][i] = (x[line][i]-n) / l[line][column-1]; 
					}
					line++;
				}
				count++;
			}
//			MatrixPrint( l );
//			MatrixPrint( u );
			double detU = 1;
			for(int i=0;i<x.length;i++) {
				detU = detU * u[i][i];
			}
			double detl = 1;
			returnValue = detl * detU;
		} else {
			System.out.println( "It's not SquareMatrix." );
		}
		return returnValue;
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
	
	
	public static double[][] MatrixInverse( double[][] x ) {
		if(CheckSquareMatrix( x )) {
			if(Det(x) != 0) {
				double[][] returnMatrix = new double[x.length][x[0].length];
				double[][] xi = new double[x.length][x[0].length*2];
				double[][] i = IdentityMatrix(x.length);
				for(int v=0;v<xi.length;v++) {
					for(int w=0;w<xi[v].length;w++) {
						if(w < x[v].length) {
							xi[v][w] = x[v][w];
						} else {
							xi[v][w] = i[v][w-x[v].length];
						}
					}
				}
				// start process
				int column = 0;
				for(int v=0;v<xi.length;v++) {
					if(xi[v][column] != 0) {
						double y = xi[v][column];
						for(int w=0;w<xi[v].length;w++) {
							xi[v][w] = xi[v][w] / y;
						}
						for(int w=0;w<xi.length;w++) {
							y = xi[w][column];
							for(int n =0;n<xi[w].length;n++) {
								if(v != w) {
									xi[w][n] -= (xi[v][n] * y);
								}
							}
						}
						column++;
					} else {
						for(int w=v+1;w<xi.length;w++) {
							if(xi[w][column] != 0) {
								double z = 0;
								for(int n=0;n<xi[w].length;n++) {
									z = xi[v][n];
									xi[v][n] = xi[w][n];
									xi[w][n] = z;
								}
							}
						}
						v--;
					}
				}
				// finish process and make return value
				for(int v=0;v<x.length;v++) {
					for(int w=0;w<x[v].length;w++) {
						returnMatrix[v][w] = xi[v][w+x[v].length];
					}
				}
				return returnMatrix;
			} else {
				System.out.println( "Matrix type mismatch." );
				return null;
			}
		} else {
			System.out.println( "It's not SquareMatrix." );
			return null;
		}
	}
	
	
	public static void MatrixPrint( double[][] x ) {
		if(x != null){
			int n = MaxDigit( x );
			System.out.println("[");
			for(int i=0;i<x.length;i++) {
				for(int w=0;w<x[i].length;w++) {
					System.out.print(" ");
					System.out.print(String.format("%" + n + "s", x[i][w]));
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

