/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Tests
*
* @name    : Java Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 30, 2020
****************************************************************************/ 

import static classes.PhysicalConstants.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class TestWorker {

	final String name = "Sdsdsd";
	
	private List<String> createList() {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("One", "Two", "Three"));
		System.out.println(System.identityHashCode(list));
		return list;
	}
	
	public void GetListFromMethod() {
		 List<String> list = createList();
		 System.out.println(System.identityHashCode(list));
		 System.out.println(list.size());
	}
}


class TestObject {
	public void test() {
		System.out.println("test()");
	}

	public static void test1() {
		System.out.println("static method test1() called");
	}

	public TestObject() {
		System.out.println(this.getClass().getName() + " created");
	}
}

class Chooser<T> {
	private final List<T> choiceList;

	public Chooser(Collection<T> choices) {
		choiceList = new ArrayList<>(choices);
	}

	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}

///--------------------------------------------------------------------------------

class Result {

	/*
	 * Complete the 'mostBalancedPartition' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts following parameters:
	 *  1. INTEGER_ARRAY parent
	 *  2. INTEGER_ARRAY files_size
	 */

	public static int mostBalancedPartition(List<Integer> parent, List<Integer> fileSizes) {
		DirectedGraph graph = new DirectedGraph();
		for (int index = 0; index < parent.size(); index++) {
			graph.addVertex(index, fileSizes.get(index));
			if (index != 0) {
				graph.addEdge(parent.get(index), index);
			}
		}

		return graph.mostBalancedPartition();
	}

	private static class Vertex {
		private final int fileSize;
		private long totalMemoryRequired;
		List<Vertex> edges = new ArrayList<>();

		Vertex(int fileSize) {
			this.fileSize = fileSize;
		}

		void addEdge(Vertex to) {
			this.edges.add(to);
		}
	}


	private static class DirectedGraph {
		Map<Integer, Vertex> vertices = new HashMap<>();
		private long diskSize;

		public void addVertex(int data, int fileSize) {
			vertices.put(data, new Vertex(fileSize));
		}

		public void addEdge(int from, int to) {
			Vertex fromVertex = vertices.get(from);
			fromVertex.addEdge(vertices.get(to));
		}

		int mostBalancedPartition() {
			diskSize = computeFileDataDistribution(vertices.get(0));
			return mostBalancedPartition(vertices.get(0));
		}

		private long computeFileDataDistribution(Vertex current) {
			long totalMemoryRequired = current.fileSize;
			for (Vertex child : current.edges) {
				totalMemoryRequired += computeFileDataDistribution(child);
			}
			current.totalMemoryRequired = totalMemoryRequired;
			return current.totalMemoryRequired;
		}

		private int mostBalancedPartition(Vertex current) {
			int result = Integer.MAX_VALUE;
			for (Vertex child : current.edges) {
				// System.out.println(child.fileSize + " " + child.totalMemoryRequired);
				result = Math.min(result, mostBalancedPartition(child));
				result = (int) Math.min(result, Math.abs(diskSize - 2 * child.totalMemoryRequired));
			}
			return result;
		}
	}

	/*
	public static void Test1()  {
		Set<Vertex> edges = new HashSet<>();
		edges.add(new Vertex(1));
		edges.add(1);
		edges.add(1);
	}*/
}


public class TESTS
{
	public static void main(String[] args)  throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		int j = 1;
		for(int i = 1; i < 10; i++){
			if(i>j){
				System.out.println(i);
			}
			else{
				System.out.println("Code");
			}
		}
	}
}
