def solution(n):
    answer = 0
    a = n ** 0.5
    if a == int(a):
        return (a+1) ** 2
    return -1