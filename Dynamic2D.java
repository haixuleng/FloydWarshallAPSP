package package1;

public class Dynamic2D {
	int[][] A; // Store the result of A[i, j, k]
	int[][] B; // Store the result of A[i, j, k - 1]
	Graph g;
	public Dynamic2D(Graph x) {
		g = x;
		initializeArray();
	}
	
	private void initializeArray() {
		A = new int[g.nOfVertices + 1][g.nOfVertices + 1]; // A --> (n+1)*(n+1)
		B = new int[g.nOfVertices + 1][g.nOfVertices + 1];
		for(int i = 0; i <= g.nOfVertices; i++) {
			for(int j = 0; j <= g.nOfVertices; j++) {
				B[i][j] = 99999; // plus infinity
			}
		}
		
		for(int i = 1; i <= g.nOfVertices; i++) {
			B[i][i] = 0; // length from vertex to itself is zero
		}
		
		for(Edge e : g.edges) { // loop through all edges in a graph
			B[e.vertexHead][e.vertexTail] = e.length; // direct connected vertices
		}
	}
	
	public void mainLoop() {
		// triple loop for dynamic programming
		for(int k = 1; k <= g.nOfVertices; k++) { // vertex k
			for(int i = 1; i <= g.nOfVertices; i++) { // vertex i
				for(int j = 1; j <= g.nOfVertices; j++) { // vertex j
					A[i][j] = Math.min(B[i][j], B[i][k] + B[k][j]);
				} 
			} 
			B = A.clone();
		}
	}
	
	public boolean checkNegativeCycle() {
		// if there is exists a negative cycle, the path to itself will be less than zero
		for(int i = 1; i <= g.nOfVertices; i++) {
			if(A[i][i] < 0) {
				return true;
			}
		}
		return false;
	}
	
	public int shortestPath() {
		int shortest = Integer.MAX_VALUE;
		for(int i = 1; i <= g.nOfVertices; i++) {
			for(int j = 1; j <= g.nOfVertices; j++) {
				if(A[i][j] < shortest) {
					shortest = A[i][j];
				}
			}
		}
		return shortest;
	}
	
	public void print() {
		for(int i = 0; i <= g.nOfVertices; i++) {
			for(int j = 0; j <= g.nOfVertices; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
}
