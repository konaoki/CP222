import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
* Class that is capable of reading in a graph file from disk.
* Graph files are line based. Node names have type String and edge weights have
* type Double. Fields on the line are separated by ':' and there is no extra white space.
*/
public class DiGraphReader implements IGraphReader {
  String tokens[];

  // Fields needed for the Graph Reader should be added here

  /**
  * Creates a new graph reader instance
  */
  public DiGraphReader() {
    // Configure the graph reader here
  }

  /**
  * Reads in a file and instantiates the graph
  * @param filename the file to read
  * @return the instantiated graph
  */
  public IGraph<String,Double> read(String filename) throws FileNotFoundException, IOException {
    IGraph<String, Double> graph = new Graph<String,Double>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    // Open the file

    // Parse the lines. If a line does not have exactly 3 fields, ignore the line
    // For each line, add the nodes and edge
    String line;
    while ((line = reader.readLine()) != null) {
      if(line.split(":").length==3)
      {
        graph.addEdge(graph.addNode(line.split(":")[0]),graph.addNode(line.split(":")[1]),Double.parseDouble(line.split(":")[2]));
      }
    }
    // Return the graph instance
    return graph;
  }

  /**
  * Simple main method to open and process a file
  */
  public static void main(String[] argv) throws Exception {
    // This code should work without modification once your reader code is working
    IGraphReader r = new DiGraphReader();
    IGraph<String,Double> g = r.read("graphfile.cs2");
    IEdge<String,Double>[] edges = g.getEdgeSet();
    for(int i=0; i<edges.length; i++) {
      //System.out.println(edges[i].getSource().getValue()+" -> "+edges[i].getDestination().getValue()+"  w: "+edges[i].getWeight());
    }
    Graph<String,Double> gg = (Graph)g;
    //gg.printNodes();
    DepthFirstSearcher d = new DepthFirstSearcher();
    System.out.println(" depth first print path");
    d.printPath(g, g.getNodeSet()[0], g.getNodeSet()[g.getNodeSet().length-2]);
    BreadthFirstSearcher b = new BreadthFirstSearcher();
    System.out.println(" breadth first print path");
    b.printPath(g, g.getNodeSet()[0], g.getNodeSet()[g.getNodeSet().length-2]);
  }
}
