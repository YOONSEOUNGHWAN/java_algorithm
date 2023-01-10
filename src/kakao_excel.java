import java.util.*;

class kakao_excel {

    class Node{
        boolean removed;
        Node prev;

        Node next;

    }

    Node[] NodeArr = new Node[1000000];

    public String solution(int n, int k, String[] cmd) {
        for(int i=0; i<n; i++){
            NodeArr[i] = new Node();
        }

        for(int i=1; i<n; i++){
            NodeArr[i-1].next = NodeArr[i];
            NodeArr[i].prev = NodeArr[i-1];
        }

        Node curr = NodeArr[k];
        Stack<Node> mystack = new Stack<>();

        for(String str : cmd){
            if(str.charAt(0) == 'U'){
                int x = Integer.parseInt(str.substring(2));
                for(int i=0; i<x; i++){
                    curr = curr.prev;
                }
            }else if(str.charAt(0) == 'D'){
                int x = Integer.parseInt(str.substring(2));
                for(int i=0; i<x; i++){
                    curr = curr.next;
                }
            }else if(str.charAt(0) == 'C'){
                mystack.push(curr);
                curr.removed = true;
                Node up = curr.prev;
                Node down = curr.next;

                if(up != null) up.next = down;
                if(down!= null) {
                    down.prev = up;
                    curr = down;
                }else{
                    curr = up;
                }
            }else{
                Node node = mystack.pop();
                node.removed = false;
                Node up = node.prev;
                Node down = node.next;
                if(up != null) up.next = node;
                if(down != null)down.prev = node;
            }
        }

        StringBuilder answer = new StringBuilder();

        for(int i=0; i<n; i++){
            if(NodeArr[i].removed) answer.append('X');
            else answer.append('O');
        }

        return answer.toString();
    }
}