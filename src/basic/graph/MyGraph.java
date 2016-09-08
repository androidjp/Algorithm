package basic.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用邻接矩阵来表示'边'的图结构
 * Created by androidjp on 16/9/8.
 */
public class MyGraph<V> {
    private ArrayList<V> vertexList;//点集合
    private int[][] edges; //邻接矩阵表示的边集合
    private int numOfEdges; //边数

    public MyGraph(int num){
        vertexList  = new ArrayList(num);
        edges = new int[num][num];
        numOfEdges = 0;
    }

    ///边数
    public int getNumOfEdges(){
        return this.numOfEdges;
    }

    ///结点数
    public int getNumOfVertex(){
        return this.vertexList.size();
    }

    //获取第i个结点
    public V getVertex(int i){
        return this.vertexList.get(i);
    }

    //获取i、j两个结点之间的权值
    public int getWeight(int i, int j){
        return this.edges[i][j];
    }

    //插入结点
    public void insertVertex(V vertex) {
        vertexList.add(vertexList.size(),vertex);
    }

    //插入结点
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        numOfEdges++;
    }

    //删除结点
    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numOfEdges--;
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for(int j=0;j<vertexList.size();j++) {
            if (edges[index][j]>0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来取得下一个邻接结点
    public int getNextNeighbor(int v1,int v2) {
        for (int j=v2+1;j<vertexList.size();j++) {
            if (edges[v1][j]>0) {
                return j;
            }
        }
        return -1;
    }

    //私有函数，深度优先遍历
    private void depthFirstSearch(boolean[] isVisited,int  i) {
        //首先访问该结点，在控制台打印出来
        System.out.print(getVertex(i)+"  ");
        //置该结点为已访问
        isVisited[i]=true;

        int w=getFirstNeighbor(i);
        while (w!=-1) {
            if (!isVisited[w]) {
                depthFirstSearch(isVisited,w);
            }
            w=getNextNeighbor(i, w);
        }
    }

    //对外公开函数，深度优先遍历，与其同名私有函数属于方法重载
    public void depthFirstSearch() {
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for(int i=0;i<isVisited.length;i++)
            isVisited[i] = false;

        for(int i=0;i<getNumOfVertex();i++) {
            //因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点的。
            if (!isVisited[i]) {
                depthFirstSearch(isVisited,i);
            }
        }
    }

    //私有函数，广度优先遍历
    private void broadFirstSearch(boolean[] isVisited,int i) {
        int u,w;
        LinkedList queue=new LinkedList();

        //访问结点i
        System.out.print(getVertex(i)+"  ");
        isVisited[i]=true;
        //结点入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u=((Integer)queue.removeFirst()).intValue();
            w=getFirstNeighbor(u);
            while(w!=-1) {
                if(!isVisited[w]) {
                    //访问该结点
                    System.out.print(getVertex(w)+"  ");
                    //标记已被访问
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //寻找下一个邻接结点
                w=getNextNeighbor(u, w);
            }
        }
    }

    //对外公开函数，广度优先遍历
    public void broadFirstSearch() {
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for(int i=0;i<isVisited.length;i++)
            isVisited[i] = false;
        for(int i=0;i<getNumOfVertex();i++) {
            if(!isVisited[i]) {
                broadFirstSearch(isVisited, i);
            }
        }
    }



    public static void main(String args[]) {
        int n = 8, e = 9;//分别代表结点个数和边的数目
        String labels[] = {"1", "2", "3", "4", "5", "6", "7", "8"};//结点的标识
        MyGraph<String> graph = new MyGraph<String>(n);
        for (String label : labels) {
            graph.insertVertex(label);//插入结点
        }
        //插入九条边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(6, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);

        System.out.println("深度优先搜索序列为：");
        graph.depthFirstSearch();
        System.out.println();
        System.out.println("广度优先搜索序列为：");
        graph.broadFirstSearch();
    }
}
