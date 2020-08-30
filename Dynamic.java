package package1;
// This Floyd Warshall Algorithm follows the one laid out in the lecture
public class Dynamic {
	int[][][] A; // this 3D array saves all the intermediate results and final results
	Graph g;
	public Dynamic(Graph x) {
		g = x;
		initializeArray();
	}
	
	private void initializeArray() {
		A = new int[g.nOfVertices + 1][g.nOfVertices + 1][g.nOfVertices + 1]; // A --> (n+1)*(n+1)*(n+1)
		
		for(int i = 0; i <= g.nOfVertices; i++) {
			for(int j = 0; j <= g.nOfVertices; j++) {
				A[i][j][0] = 99999; // plus infinity
				A[i][0][j] = 99999; // plus infinity
				A[0][i][j] = 99999; // plus infinity
			}
		}
		
		for(int i = 1; i <= g.nOfVertices; i++) {
			A[i][i][0] = 0; // length from vertex to itself is zero
		}
		
		for(Edge e : g.edges) { // loop through all edges in a graph
			A[e.vertexHead][e.vertexTail][0] = e.length; // direct connected vertices
		}
	}
	
	public void mainLoop() {
		// triple loop for dynamic programming
		for(int k = 1; k <= g.nOfVertices; k++) { // vertex k
			for(int i = 1; i <= g.nOfVertices; i++) { // vertex i
				for(int j = 1; j <= g.nOfVertices; j++) { // vertex j
					A[i][j][k] = Math.min(A[i][j][k - 1], A[i][k][k - 1] + A[k][j][k - 1]);
				} 
			} 
		}
	}
	
	public boolean checkNegativeCycle() {
		// if there is exists a negative cycle, the path to itself will be less than zero
		for(int i = 1; i <= g.nOfVertices; i++) {
			if(A[i][i][g.nOfVertices] < 0) {
				return true;
			}
		}
		return false;
	}
	
	public int shortestPath() {
		int shortest = Integer.MAX_VALUE;
		for(int i = 1; i <= g.nOfVertices; i++) {
			for(int j = 1; j <= g.nOfVertices; j++) {
				if(A[i][j][g.nOfVertices] < shortest) {
					shortest = A[i][j][g.nOfVertices];
				}
			}
		}
		return shortest;
	}
	
	public void print() {
		for(int i = 0; i <= g.nOfVertices; i++) {
			for(int j = 0; j <= g.nOfVertices; j++) {
				System.out.print(A[i][j][g.nOfVertices] + " ");
			}
			System.out.println(" ");
		}
	}
}
