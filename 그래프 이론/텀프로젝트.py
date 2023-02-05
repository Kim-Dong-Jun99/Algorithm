import sys

sys.setrecursionlimit(1000000)


def dfs(i):
    visited[i] = 1
    group.add(i)
    if visited[students[i] - 1] == 0:
        dfs1 = dfs(students[i] - 1)
        if dfs1 != 0:
            group.remove(students[i]-1)
            if dfs1[1] in group:
                dfs1[0] += 1
            return dfs1
        else:
            visited[i] = 0
            return 0
    else:
        if (students[i] - 1) in group:
            return [1, students[i]-1]
        else:
            visited[i] = 0
            return 0


T = int(sys.stdin.readline())
for _ in range(T):
    N = int(sys.stdin.readline())
    students = list(map(int, sys.stdin.readline().split()))
    visited = [0] * N
    not_in_group = N
    for i in range(N):
        if visited[i] == 0:
            group = set()
            dfs_ = dfs(i)
            if dfs_ == 0:
                visited[i] = 1
            else:
                not_in_group -= dfs_[0]

    print(not_in_group)
