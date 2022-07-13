

    // Complete the hasCycle function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static boolean hasCycle(SinglyLinkedListNode head) {
        SinglyLinkedListNode currentHead = head;
        ArrayList<SinglyLinkedListNode> visitedNodes = new ArrayList<SinglyLinkedListNode>();
        
        while (currentHead != null) {
            if (visitedNodes.contains(currentHead)) {
                 return true;
            }
            visitedNodes.add(currentHead);
            currentHead = currentHead.next;
        }
    return false;
    }

