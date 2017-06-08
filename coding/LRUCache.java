/*
a hashtable:
1. fast look up O(1)
2. no quick way to remove recently used item, 
We also need a data structure to maintain page numbers in cache in the order of their access time. 
One way to do that is to keep a timestamp field for each record, but we still need to sort them which 
cannot be done in O(1) time.

linked list:

1. Alternatively, we can use a linked list to keep all records, and move the 
newly visited one to the head of the list. To get O(1) time complexity for updating such a linked list, 
we need a doubly linked list.
2. look up is O(n), so we

use a hashtable that keeps track of the keys and its values in the double linked list. 
One interesting property about double linked list is that the node 
can remove itself without other reference. In addition, it takes constant time 
to add and remove nodes from the head or tail. O(1)

Each time when a new page number comes in,
If it is already in the cache, move the node to the head of the linked list;
If it is not in the cache, insert it to the head of the linked list and update the current capacity of 
the cache. If the cache is full, remove the last node of the linked list.

1. getFuction:
moveToHead

2. setFunction:
if doesn't exist:
  update cache,
  addNode (already head)
  if exceeds capacity, remove tail
else :
  update cache
  move to head
*/

class DLinkedNode {
  int key;
  int value;
  DLinkedNode pre;
  DLinkedNode post;
}

/**
 * Always add the new node right after head;
 */
private void addNode(DLinkedNode node){
  node.pre = head;
  node.post = head.post;

  head.post.pre = node;
  head.post = node;
}

/**
 * Remove an existing node from the linked list.
 */
private void removeNode(DLinkedNode node){
  DLinkedNode pre = node.pre;
  DLinkedNode post = node.post;

  pre.post = post;
  post.pre = pre;
}

/**
 * Move certain node in between to the head.
 */
private void moveToHead(DLinkedNode node){
  this.removeNode(node);
  this.addNode(node);
}

// pop the current tail. 
private DLinkedNode popTail(){
  DLinkedNode res = tail.pre;
  this.removeNode(res);
  return res;
}

class LRUCache {
  private Hashtable<Integer, DLinkedNode> 
    cache = new Hashtable<Integer, DLinkedNode>();
  private int count;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.count = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    head.pre = null;

    tail = new DLinkedNode();
    tail.post = null;

    head.post = tail;
    tail.pre = head;
  }

  public int get(int key) {

    DLinkedNode node = cache.get(key);
    if(node == null){
      return -1; // should raise exception here.
    }

    // move the accessed node to the head;
    this.moveToHead(node);

    return node.value;
  }


  public void set(int key, int value) {
    DLinkedNode node = cache.get(key);

    if(node == null){

      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;

      this.cache.put(key, newNode);
      this.addNode(newNode);

      ++count;

      if(count > capacity){
        // pop the tail
        DLinkedNode tail = this.popTail();
        this.cache.remove(tail.key);
        --count;
      }
    }else{
      // update the value.
      node.value = value;
      this.moveToHead(node);
    }  
  }

  /**
 * Always add the new node right after head;
 */
  private void addNode(DLinkedNode node){
    node.pre = head;
    node.post = head.post;

    head.post.pre = node;
    head.post = node;
  }

  /**
 * Remove an existing node from the linked list.
 */
  private void removeNode(DLinkedNode node){
    DLinkedNode pre = node.pre;
    DLinkedNode post = node.post;

    pre.post = post;
    post.pre = pre;
  }

  /**
 * Move certain node in between to the head.
 */
  private void moveToHead(DLinkedNode node){
    this.removeNode(node);
    this.addNode(node);
  }

  // pop the current tail. 
  private DLinkedNode popTail(){
    DLinkedNode res = tail.pre;
    this.removeNode(res);
    return res;
  }
}