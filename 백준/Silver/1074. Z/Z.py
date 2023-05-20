import sys

N, r, c = map(int, sys.stdin.readline().split())

left_top = [0,0]
result = 0

def get_division(left_top, N, r, c):
    distance = 2 ** (N-1)
    if abs(left_top[0] - r) < distance and abs(left_top[1] - c) < distance:
        return 1
    if abs(left_top[0] - r) < distance and abs(left_top[1] - c) >= distance:
        return 2
    if abs(left_top[0] - r) >= distance and abs(left_top[1] - c) < distance:
        return 3
    if abs(left_top[0] - r) >= distance and abs(left_top[1] - c) >= distance:
        return 4
    

while N > 1:
    division = get_division(left_top, N, r, c)
    if division != 1:
        result += (4**(N-1)) * (division - 1)
        if division == 2:
            left_top[1] += 2**(N-1)
        elif division == 3:
            left_top[0] += 2**(N-1)
        elif division == 4:
            left_top[1] += 2**(N-1)
            left_top[0] += 2**(N-1)
    N -= 1
        
result += get_division(left_top, N, r, c)-1
print(result)