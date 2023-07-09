answer = -1
def solution(k, dungeons):
  
    n = len(dungeons)
    visited = [0] * n
    def backtrack(index, n, k):
        global answer
        if index > answer:
            answer = index
        for i in range(n):
            if k >= dungeons[i][0] and visited[i] == 0:
                visited[i] = 1
                k -= dungeons[i][1]
                backtrack(index + 1,  n, k)
                visited[i] = 0
                k += dungeons[i][1]
                
        
    backtrack(0, n, k)
    return answer