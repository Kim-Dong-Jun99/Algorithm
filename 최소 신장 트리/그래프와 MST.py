import sys
from collections import deque, defaultdict

LOG = 21


def bigger(i, j):
    if i < j:
        return j
    return i


def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    rx = find(x)
    ry = find(y)

    if rx != ry:
        parent[rx] = ry


# 크루스칼로 MST만들기
def create_mst():
    kruskal.sort(key=lambda x: x[2])
    global k_value
    for n, m, w_ in kruskal:
        if find(n) != find(m):
            k_value += w_
            union(n, m)
            mst[(n, m)] = 1

            graph_k[n][m] = w_
            graph_k[m][n] = w_


# 최소 공통 조상을 구하기 위한 세팅
def init_lca():
    # 1이 제일 부모임
    q = deque([(1, 0)])
    visited[1] = 1
    height[1] = 0
    while q:
        n, d = q.popleft()
        for child in graph_k[n]:
            if visited[child] == 0:
                height[child] = d + 1
                visited[child] = 1
                q.append((child, d + 1))
                parent_lca[child][0][0] = n
                parent_lca[child][0][1] = graph_k[n][child]

    for i in range(1, LOG):
        for j in range(1, N + 1):
            parent_lca[j][i][0] = parent_lca[parent_lca[j][i - 1][0]][i - 1][0]
            parent_lca[j][i][1] = bigger(parent_lca[j][i - 1][1], parent_lca[parent_lca[j][i - 1][0]][i - 1][1])


# 두 노드를 LCA해서 최소공통 조상까지의 최대 값 구함
def lca(d, e):
    result = 0
    if height[d] != height[e]:
        if height[d] > height[e]:
            d, e = e, d
        for i in range(LOG - 1, -1, -1):
            if height[e] - height[d] >= (1 << i):
                result = bigger(result, parent_lca[e][i][1])
                e = parent_lca[e][i][0]
    if d == e:
        return result
    for i in range(LOG - 1, -1, -1):
        if parent_lca[e][i][0] != parent_lca[d][i][0]:
            result = bigger(result, parent_lca[d][i][1])
            result = bigger(result, parent_lca[e][i][1])
            d = parent_lca[d][i][0]
            e = parent_lca[e][i][0]
    result = bigger(result, parent_lca[d][i][1])
    result = bigger(result, parent_lca[e][i][1])
    return result


N, M = map(int, sys.stdin.readline().split())
# 크루스칼을 위한 리스트
parent = [i for i in range(N + 1)]
# LCA를 위한 딕셔너리, graph[i][j]에 해당 간선의 가중치 저장된다,
graph = defaultdict(dict)
# 크루스칼 한 것을 LCA하는데 쓰는 딕셔너리
graph_k = defaultdict(dict)
# 크루스칼을 위해 쓰는 리스트
kruskal = []
# 원본 순서 저장을 위한 리스트
to_print = []
# MST에 속한 간선들을 저장하는 딕셔너리
mst = {}
# 노드의 높이를 저장하는 리스트
height = [0] * (N + 1)
# i의 2^j 번째 부모와 최소 공통 조상으로 향하는 값중 최대 값을 저장하는 리스트
parent_lca = [[[0, 0] for _ in range(LOG)] for _ in range(N + 1)]
# lca할때, 방문했는지 확인하는 리스트
visited = [0] * (N + 1)

for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    to_print.append([u, v])
    kruskal.append([u, v, w])
    graph[u][v] = w
    graph[v][u] = w
k_value = 0
# 크루스칼로 MST 만들기
create_mst()
init_lca()

for i, j in to_print:
    if (i, j) in mst:
        sys.stdout.write(str(k_value) + "\n")
    else:
        sys.stdout.write(str(k_value - lca(i, j) + graph[i][j]) + "\n")
