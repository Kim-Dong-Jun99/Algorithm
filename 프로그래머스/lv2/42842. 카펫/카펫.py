def solution(brown, yellow):
    
    for i in range(1, int(brown / 2) +2):
        a = i
        b = int(brown / 2) + 2 - a
        # print(a,b, a*b, a-2)
        if a * b == brown+yellow and (a - 2) * (b - 2) == yellow:
            return [max(a,b), min(a,b)]