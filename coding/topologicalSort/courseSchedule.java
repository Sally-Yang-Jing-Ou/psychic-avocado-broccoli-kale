import java.io.*;
import java.util.*;

class Solution {

  private static ArrayList<Integer> findOrder(int numCourses, int[][] prerequisites) {
    if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) return null;

    Map<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
    Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

    for(int[] pair : prerequisites) {
      indegree.put(pair[0], 0);
      indegree.put(pair[1], 0);
    }

    //make graph and indegree map
    computeGraphAndIndegree(map, indegree, prerequisites);

    Queue<Integer> queue = new LinkedList<Integer>();
    for(Integer course: indegree.keySet()) {
      if(indegree.get(course) == 0) {
        queue.add(course); //no incoming edges
      }
    }

    ArrayList<Integer> result = new ArrayList<Integer>();

    while(!queue.isEmpty()) {
      Integer node = queue.poll();
      result.add(node);
      if(map.containsKey(node)) {
        for(Integer d: map.get(node)) {
          indegree.put(d, indegree.get(d) - 1);
          if (indegree.get(d) == 0) {
            queue.add(d);
          }
        }
      }
    }

    if (result.size() != numCourses) {
      System.out.println("invalid courses prerequisites: cycle exists");
      return null;
    }

    return result;

  }

  private static void computeGraphAndIndegree(Map<Integer, HashSet<Integer>> map, Map<Integer, Integer> indegree, int[][] prerequisites) {
    for(int[] pair: prerequisites) {
      if(!map.containsKey(pair[1])) {
        map.put(pair[1], new HashSet<Integer>());
      }
      if(!map.get(pair[1]).contains(pair[0])) {
        indegree.put(pair[0], indegree.getOrDefault(pair[0], 0) + 1);
        map.get(pair[1]).add(pair[0]);
      }      
    }
  }

  private static ArrayList<Integer> findOrder2(int numCourses, int[][] prerequisites) {
    if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) return null;

    Map<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
    for(int[] pair: prerequisites) {
      if(!map.containsKey(pair[1])) {
        map.put(pair[1], new HashSet<Integer>());
      }
      map.get(pair[1]).add(pair[0]);      
    }
    return topologicalSort(numCourses, map);
  }

  private static ArrayList<Integer> topologicalSort(int numCourses, Map<Integer, HashSet<Integer>> map) {
    boolean[] visited = new boolean[numCourses];
    boolean[] visiting = new boolean[numCourses];
    ArrayList<Integer> results = new ArrayList<Integer>();
    for(Integer course: map.keySet()) {
      if(!visited[course] && map.getOrDefault(course, null) != null) {
        if (!DFS(visited, visiting, map, results, course)) {
          System.out.println("invalid courses: cycle exists");
          return null;
        }
      }
    }
    //System.out.println(results.toString());
    Collections.reverse(results);
    return results;
  }

  private static boolean DFS(boolean[] visited, boolean[] visiting, Map<Integer, HashSet<Integer>> map, ArrayList<Integer> results, int node) {
    if(visiting[node]) return false; //cycle
    if(visited[node]) return true; //already visited, skip
    visiting[node] = true;
    if(map.containsKey(node)) {
      for(Integer dependent: map.get(node)) {
        if(!visited[dependent]) {
          if (!DFS(visited, visiting, map, results, dependent)) {
            return false;
          }
        }
      }
    }
    visiting[node] = false;
    visited[node] = true;
    results.add(node);
    return true;

  }

  public static void main(String[] args) {
    //4, [[1,0],[2,0],[3,1],[3,2]]
    ArrayList<Integer> res = findOrder(4, new int[][] {{1,0},{2,0},{3,1},{3,2},{0,1}});
    //ArrayList<Integer> res = findOrder2(2, new int[][] {{1,0}});
    if(res!=null) {
      System.out.println(res.toString());
    }
  }
}