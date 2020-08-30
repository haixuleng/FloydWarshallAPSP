package package1;

import java.io.FileNotFoundException;

public class TestLoadText {
	public static void main(String args[]) throws FileNotFoundException {
		LoadText lt = new LoadText("data/g1.txt");
		Graph g = lt.get();
		for(Edge e : g.edges) {
			System.out.println("H:" + e.vertexHead + ", T:" + e.vertexTail + ", L:" + e.length);
		}
		System.out.println("N of Edges: " + g.nOfEdges);
		System.out.println("N of Vertices: " + g.nOfVertices);
		
		// checked out and it works fine.
	}
}
