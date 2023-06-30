def solution(a, b, n):
    answer = 0
    while n >= a:
        temp = n // a
        n -= temp * a
        n += b * temp
        answer += b * temp
        
    return answer