package search;

import dataStructure.linear.LinkedList;
import dataStructure.linear.LinkedListQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("탐색 알고리즘 테스트")
public class DfsAndBfs {

    private final boolean[] visited = new boolean[10];

    private final Object[] graphs = new Object[8];

    @Test
    @DisplayName("깊이 우선 탐색")
    public void dfs() {
        dfs(1);
    }

    public void dfs(Integer n) {
        if (visited[n]) return;
        visited[n] = true;
        System.out.println("[DFS] 노드 => " + n + " 방문");
        LinkedList<Integer> nodes = (LinkedList<Integer>) graphs[n];
        while (!nodes.isEmpty()) {
            Integer node = nodes.removeFirst();
            dfs(node);
        }
    }
    // =========================================================================================================

    @Test
    @DisplayName("너비 우선 탐색")
    public void bfs() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            Integer n = queue.dequeue();
            System.out.println("[BFS] 노드 => " + n + " 방문");
            LinkedList<Integer> nodes = (LinkedList<Integer>) graphs[n];
            while (!nodes.isEmpty()) {
                Integer node = nodes.removeFirst();
                if (!visited[node]) {
                    visited[node] = true;
                    queue.enqueue(node);
                }
            }
        }
    }

    // =========================================================================================================
    @BeforeEach
    public void graphInit() {
        LinkedList<Integer> edge = new LinkedList<>();
        edge.add(2);
        edge.add(3);

        LinkedList<Integer> edge2 = new LinkedList<>();
        edge2.add(1);
        edge2.add(3);
        edge2.add(4);
        edge2.add(5);

        LinkedList<Integer> edge3 = new LinkedList<>();
        edge3.add(1);
        edge3.add(2);
        edge3.add(6);
        edge3.add(7);

        LinkedList<Integer> edge4 = new LinkedList<>();
        edge4.add(2);
        edge4.add(5);

        LinkedList<Integer> edge5 = new LinkedList<>();
        edge5.add(2);
        edge5.add(4);

        LinkedList<Integer> edge6 = new LinkedList<>();
        edge6.add(3);
        edge6.add(7);

        LinkedList<Integer> edge7 = new LinkedList<>();
        edge7.add(3);
        edge7.add(6);

        graphs[1] = edge;
        graphs[2] = edge2;
        graphs[3] = edge3;
        graphs[4] = edge4;
        graphs[5] = edge5;
        graphs[6] = edge6;
        graphs[7] = edge7;
        System.out.println("######################################");
    }

}
