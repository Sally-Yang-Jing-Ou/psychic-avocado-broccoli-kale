 public  void dfsUsingStack(int adjacency_matrix[][], Node node)
 {
  Stack stack= new  Stack();
  ArrayList setA;
  ArrayList setB;
  stack.add(node);
  setA.add(node);
  justAddedToSetA = true;
  node.visited=true;
  while (!stack.isEmpty())
  {
   Node element=stack.pop();
   System.out.print(element.data + "t");
   ArrayList neighbours=findNeighbours(adjacency_matrix,element);
   for (int i = 0; i < neighbours.size(); i++) {
    Node n=neighbours.get(i);
    if(n!=null && !n.visited)
    {
     if(justAddedToSetA) {
      setB.add(n);
     } else {
      setA.add(n);
     }
     stack.add(n);
     n.visited=true;
 
    }
   }
   justAddedToSetA = !justAddedToSetA;
  }
 }

 import java.util.ArrayList;
import java.util.Stack;
 
public class DepthFirstSearchExample
{ 
 
 static ArrayList nodes=new ArrayList();
 static class Node
 {
  int data;
  boolean visited;
 
  Node(int data)
  {
   this.data=data;
 
  }
 }
 
 
 
 // find neighbors of node using adjacency matrix
 // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
 public ArrayList findNeighbours(int adjacency_matrix[][],Node x)
 {
  int nodeIndex=-1;
 
  ArrayList neighbours=new ArrayList();
  for (int i = 0; i < nodes.size(); i++) {
   if(nodes.get(i).equals(x))
   {
    nodeIndex=i;
    break;
   }
  }
 
  if(nodeIndex!=-1)
  {
   for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
    if(adjacency_matrix[nodeIndex][j]==1)
    {
     neighbours.add(nodes.get(j));
    }
   }
  }
  return neighbours;
 }
 
 
    // Recursive DFS
 public  void dfs(int adjacency_matrix[][], Node node)
 {
 
  System.out.print(node.data + "t");
  ArrayList neighbours=findNeighbours(adjacency_matrix,node);
  for (int i = 0; i < neighbours.size(); i++) {
   Node n=neighbours.get(i);
   if(n!=null && !n.visited)
   {
    dfs(adjacency_matrix,n);
    n.visited=true;
 
   }
  }
 }