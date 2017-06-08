import java.io.*;
import java.util.*;

interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

class ListIterator implements Iterator<Integer> {

  Stack<NestedInteger> stack = new Stack<NestedInteger>();
  ListIterator(List<NestedInteger> list) {
    if (list == null || list.size() == 0) return;
    for(int i = list.size()-1; i >= 0; i--) {
      stack.push(list.get(i));
    }
  }

  @Override
  public Integer next() {
    return stack.pop().getInteger();
  }

  @Override
  public boolean hasNext() {
    while(!stack.isEmpty()) {
      NestedInteger top = stack.peek();
      if(top.isInteger()) { //one integer
        return true;
      } else {
        NestedInteger li = stack.pop();
        List<NestedInteger> nestedLi = li.getList();
        for(int i = nestedLi.size()-1; i >= 0 ; i--) {
          stack.push(nestedLi.get(i));
        }
      }
    }
    return false;
  }

}

class Solution {

  private static List<Object> flattenLinkedList(List<?> list) {
    List<Object> flattened = new ArrayList<Object>();
    if (list == null || list.size() == 0) return flattened;
    flatten(list, flattened);
    return flattened;
  }

  private static void flatten(List<?> list, List<Object> flattened) {
    for(Object item: list) {
      if (item instanceof List<?>) {
        flatten((List<?>) item, flattened);
      } else { //just one item
        flattened.add(item);
      }
    }
  }

  public static void main(String[] args) {
    List<Object> treeList = Arrays.asList("Hello", "World!", "How", "Are", "You", Arrays.asList("hi", "okay"));
    List<Object> flat = flattenLinkedList(treeList);

    for(Object item: treeList) {
      System.out.println(item);
    }

    for(Object item: flat) {
      System.out.println(item);
    }
  }
}

