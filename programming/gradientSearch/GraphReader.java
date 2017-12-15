import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
* Class that is capable of reading in a graph file from disk.
* Graph files are line based. Node names have type String and edge weights have
* type Double. Fields on the line are separated by ':' and there is no extra white space.
*/
public class GraphReader{

  // Fields needed for the Graph Reader should be added here

  /**
  * Creates a new graph reader instance
  */
  public GraphReader() {
    // Configure the graph reader here
  }

  /**
  * Reads in a file and instantiates the graph
  * @param filename the file to read
  * @return the instantiated graph
  */
  public Graph<Integer,Integer> read(String filename){
    try
    {
      Graph<Integer, Integer> graph = new Graph<Integer,Integer>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    // Open the file

    String line;
    int nodeNameCount=0;

    while ((line = reader.readLine()) != null) {
      graph.width=line.length();
      graph.height++;
      for(int i=1; i<line.length()-1; i++)
      {
        if(line.charAt(i)!='#') //if it's not a wall
        {
          if(line.charAt(i)=='g')//if it's the goal
          {
            graph.goalNodeID=nodeNameCount;
          }
          graph.addNode(nodeNameCount);
          nodeNameCount++; //adds 1 so next time the above would add 1 then 2
        }
        
      }
    }
    graph.width-=2;
    graph.height-=2;
    // Return the graph instance
    return graph;
    }
    
    catch (Exception e)
    {
      return null;
    }
  }

  /**
  * Simple main method to open and process a file
  */

}
