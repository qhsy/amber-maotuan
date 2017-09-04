package zz.mk.utilslibrary.algo.dijkstra;

import java.util.List;

/**
 * author: zhu on 2017/5/2 14:49
 * email: mackkill@gmail.com
 */

public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
