def solution(n, computers):
    answer = 0
    visited = [0] * n
    for i in range(n):
        if visited[i] == 0:
            visited[i] = 1
            answer += 1
            cur = [i]
            while cur:
                temp = []
                for j in cur:
                    for k in range(n):
                        if k != j and computers[j][k] == 1 and visited[k] == 0:
                            visited[k] = 1
                            temp.append(k)
                cur = temp       
    return answer