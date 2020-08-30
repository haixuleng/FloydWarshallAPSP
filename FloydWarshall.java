package package1;

import java.io.FileNotFoundException;

public class FloydWarshall {
	public static void main(String args[]) throws FileNotFoundException {
		//LoadText lt = new LoadText("data/g1.txt");
		LoadText lt = new LoadText("data/input_random_34_512.txt");
		Graph g = lt.get();
		Dynamic2D d = new Dynamic2D(g);
		d.mainLoop();
		if(d.checkNegativeCycle()) {
			System.out.println("NULL, has negative cycle.");
			//System.out.println(d.shortestPath());
			//d.print();
		}
		else {
			System.out.println(d.shortestPath());
		}
	}
}
