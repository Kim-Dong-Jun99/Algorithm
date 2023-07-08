def solution(n):
    answer = n-1
    for i in range(n-2,1,-1):
        if n % i == 1:
            answer = i
    
    return answer