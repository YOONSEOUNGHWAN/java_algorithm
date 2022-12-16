import java.util.*;

public class kakao_1905 {
    /**
     Node 생성
     */
    class Node {
        Node(int id, int x, int y){
            this.id = id;
            this.x = x;
            this.y = y;
        }
        int id;
        int x, y;
        Node left;
        Node right;
    }

    List<Node> Nodes = new ArrayList<Node>();

    Comparator<Node> Compare = new Comparator<Node>(){
        public int compare(Node a, Node b){
            if(a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        }
    };

    void addNode(Node parent, Node child){
        if(child.x < parent.x){
            if(parent.left == null) parent.left = child;
            else addNode(parent.left, child);
        }
        else{
            if(parent.right == null) parent.right = child;
            else addNode(parent.right, child);
        }
    };

    int idx;

    void preorder(Node root, int[][] result){
        if(root == null) return;
        result[0][idx++] = root.id;
        preorder(root.left, result);
        preorder(root.right, result);
    }

    void postorder(Node root, int[][] result){
        if(root == null) return;
        postorder(root.left, result);
        postorder(root.right, result);
        result[1][idx++] = root.id;
    }
    public int[][] solution(int[][] nodeinfo) {

        for(int i=0; i<nodeinfo.length; i++){
            Nodes.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        //정렬 기준 > y좌표 > x좌표

        Nodes.sort(Compare);

        Node root = Nodes.get(0);

        for(int i=1; i<nodeinfo.length; i++){
            addNode(root, Nodes.get(i));
        }

        int[][] answer = new int[2][nodeinfo.length];

        preorder(root, answer);
        idx = 0;
        postorder(root, answer);

        return answer;
    }
}