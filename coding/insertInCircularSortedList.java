Node sortedInsert(Node* head , int val)
{
    Node current = head;
    Node ins = new Node(val);
    
    if(!head){ // if it is empty
        head = ins;
        ins->next = head;
        return head;
    }
    /* 
    
        5        11    
     3 4 7 9 10 wraps 
              p  h 
   2 
     3 4 7 9 10 wraps 
     p h
   

    If value is smaller than head's value then
             we need to change next of last node */
     else if(current.data >= ins.data) {
        while (current.next != head) {
            current = current.next; //end of list
        }
        current.next = ins;
        ins.next = head;
        head = ins;
     } else {
        while (current.next != head && current.next.data < ins.data) {
            current = current.next;
        }
        ins.next = current.next;
        current.next = ins;
     }
}