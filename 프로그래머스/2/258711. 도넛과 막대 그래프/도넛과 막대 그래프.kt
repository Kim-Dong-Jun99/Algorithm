import java.util.*

class Solution {
    val answer = IntArray(4) {0}
    val vertexs = Array<Vertex>(1_000_001) { Vertex(it, mutableListOf(), 0, 0)}
    
    fun solution(edges: Array<IntArray>): IntArray {
        initialize(edges)
        solve()
        return answer
    }
    
    fun initialize(edges: Array<IntArray>) {
        for (edge in edges) {
            vertexs[edge[0]].outEdge += 1
            vertexs[edge[0]].next.add(edge[1])
            vertexs[edge[1]].inEdge += 1
        }
    }
    
    fun solve() {
        var centerId: Int = 0
        for (vertex in vertexs) {
            if (vertex.inEdge == 0 && vertex.outEdge >= 2) {
                centerId = vertex.id
                break
            }
        }
        answer[0] = centerId
        val center = vertexs[centerId]
        for (next in center.next) {
            val nextV = vertexs[next]
            determineGraph(nextV)
        }
        
    }
    
    fun determineGraph(v: Vertex) {
        val startId = v.id
        var cur = v
        while (true) {
            
            if (cur.outEdge > 1) {
                answer[3] += 1
                return
            }
            if (cur.outEdge == 0) {
                answer[2] += 1
                return
            }
            cur = vertexs[cur.next.get(0)]
            if (cur.id == startId) {
                answer[1] += 1
                return
            }
        }
    }
    
    
    class Vertex (val id: Int, val next: MutableList<Int>, var inEdge: Int, var outEdge: Int) {}
}