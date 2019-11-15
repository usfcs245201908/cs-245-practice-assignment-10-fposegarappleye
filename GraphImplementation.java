import java.lang.Exception;
import java.util.List;
import java.util.*;

public class GraphImplementation implements Graph {
	// VARIABLES
	private int matrix[][];
	private int vertices;


	// CONSTRUCTORS
	public GraphImplementation(int verticesIn) {
		vertices = verticesIn;
		matrix = new int[vertices][vertices];
	}


	//FUNCTIONS
	
	/*Adds a directed edge between two vertices from src to tar*/
	public void addEdge(int v1, int v2) throws Exception {
		if(v1 >= vertices ||
			 v2 >= vertices ||
			 v1 < 0 ||
			 v2 < 0) {
				 throw new Exception("Vertex input is out of bounds");
			 }
			 matrix[v1][v2] = 1;
	}


	/*Prints (to console) one ordering of vertices such that each
	directed edge (v1, v2) from vertex v1 to vertex v2, v1 comes
	before v2 in the ordering. If such an ordering is not possible
	(due to cycles, for example), this function must indicate so,
	though it may print a partial solution in so doing.*/
	public List<Integer> topologicalSort() {
		// The list holds the data to be returned
		List<Integer> list  = new ArrayList<Integer>();
		// The sum stores the sum of the matrix items
		int [] sum = new int[vertices];

		// Fill in the sum arr
		for(int i = 0; i < vertices ; i++) {
			for(int j = 0; j < vertices ; j++) {
				sum[i] += matrix[j][i];
			}
		}
		// Producing and printing the output
		System.out.print("Sorted output: ");
		for(int count = 0; count < vertices ; count++) {
			int n = findZero(sum);
			if(n == -1) {
				return list;
			}
			list.add(n);
			System.out.print(n + " ");
			sum[n] = -1;
			for(int i = 0; i < vertices ; i++) {
				sum[i] -= matrix[n][i];
			}
		}
		System.out.print("\n");
		return list;
	}


	/*Returns a List of vertex IDs, with each ID representing a
	vertex which is the destination of the edge originating from
	the source vertex, passed as the argument*/
	public List<Integer> neighbors(int vertex) throws Exception {
		if(vertex >= vertices || vertex < 0) {
			throw new Exception("Vertex input is out of bounds");
		}
		// This list will contain the necessary items
		List<Integer> neighbors = new ArrayList<>();
		for(int i = 0; i < vertices; i++) {
			if(matrix[vertex][i] > 0) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	/* Helper function */
	public int findZero(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0) {
				return i;
			}
		}
		return -1;
	}
}
