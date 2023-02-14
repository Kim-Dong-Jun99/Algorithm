import sys

sys.setrecursionlimit(1000000)


def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])

    return parent[x]


def update(r, w):
    for i in range(1, N + 1):
        if find(i) == r:
            position[i] += w


# 여기서 부모를 정하니까, 무슨 부모를 정할지에 대한 로직이 들어가야함
def union(x, y, w):
    # x의 최종 부모
    rx = find(x)
    # y의 최종 부모
    ry = find(y)
    # x의 현재 위치 값을 구함
    px = position[x]
    # x의 최종 부모의 위치
    prx = position[rx]
    # y의 현재 위치 값
    py = position[y]
    # y의 최종 부모의 위치 값
    pry = position[ry]

    if rx != ry:
        # 그룹이 이제 처음 시작할때임, y의 최종 부모 위치도 0, x의 최종 부모 위치도 0, 최종 부모는 pry임
        if visited[x] == 0 and visited[y] == 0:
            visited[x] = 1
            visited[y] = 1
            parent[rx] = ry
            position[ry] = px + w
        # 1 -> 2, 2-> 3, 4 -> 2 같은 상황임, 이럴때는 최종 부모를 pry로 하면된다
        elif visited[y] != 0 and visited[x] == 0:
            visited[x] = 1
            parent[rx] = ry
            position[rx] = py - w
        # 부모를 업데이트해야될 수도 있는 상황임, 1 -> 2, 1 -> 3 같은 상황, 3이 더크면 업데이트해야겟지?
        elif visited[x] != 0 and visited[y] == 0:
            visited[y] = 1
            position[ry] = px + w
            if px + w > prx:
                parent[rx] = ry
            else:
                parent[ry] = rx
        # ㅈ같은 상황, 1 -> 2, 2 -> 3, 2 -> 3 이렇게는 안 나올거니까, 두 그룹을 합치는 일이 생길 거임 ㅇㅇ
        # 1 2 200, 4 5 150, 1 4 100 이라면, 최종 부모는 5가 되야함, 1 4 10 이라면 최종 부모는 2임
        else:
            diff = (w + px) - py
            update(ry, diff)
            # 1 4 10 같은 상황
            if prx > pry + diff:
                parent[ry] = rx
            # 1 4 100 같은 상황
            else:
                parent[rx] = ry


while True:
    N, M = map(int, sys.stdin.readline().split())
    if N == 0 and M == 0:
        break
    parent = [i for i in range(N + 1)]
    visited = [0] * (N + 1)
    # 상대적인 위치 차이를 나타내는 값
    position = [0] * (N + 1)
    for _ in range(M):
        query = list(sys.stdin.readline().split())
        q1 = int(query[1])
        q2 = int(query[2])
        if query[0] == '!':
            q3 = int(query[3])
            union(q1, q2, q3)
        else:
            if find(q1) != find(q2):
                sys.stdout.write("UNKNOWN\n")
            else:
                sys.stdout.write(str(position[q2] - position[q1]) + "\n")
        # print(position)
        # print(parent)
