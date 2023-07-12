def solution(n):
    answer = 1
    subsums = [0] * (n+1)
    for i in range(1, n+1):
        subsums[i] = i
        subsums[i] += subsums[i-1]
    
    for i in range(1, n):
        for j in range(1, n+1):
            if j + i > n:
                break
            else:
                if subsums[j+i] - subsums[j-1] == n:
                    answer += 1
                elif subsums[j+i] - subsums[j-1] > n:
                    break
                    
            
    
    
    return answer