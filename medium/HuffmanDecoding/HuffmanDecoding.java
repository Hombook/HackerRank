

/*  
    class Node
        public  int frequency; // the frequency of this tree
        public  char data;
        public  Node left, right;
    
*/ 

    void decode(String s, Node root) {
        String decodedString = "";
        Node nodePointer = root;
        for (int i=0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '1') {
                nodePointer = nodePointer.right;
            } else {
                nodePointer = nodePointer.left;
            }
            
            // A leaf node
            if ((nodePointer.left == null) && (nodePointer.right == null)) {
                decodedString += nodePointer.data;
                nodePointer = root;
            }
        }

       System.out.print(decodedString);
    }

