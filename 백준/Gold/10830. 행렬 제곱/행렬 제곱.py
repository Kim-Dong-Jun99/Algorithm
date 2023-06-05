import sys
from collections import defaultdict
N,B=map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
mult_dict = defaultdict(lambda : [])
def mult(a,b):
    global N
    result = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            for k in range(N):
                result[i][j] += (a[i][k]*b[k][j]) % 1000
            result[i][j] %= 1000
    return result
to_mult = []
while B > 0:
    index = 0
    actual_num=1
    while actual_num * 2 <= B:
        index += 1
        actual_num *= 2
    B -= actual_num
    to_mult.append(index)
mult_dict[0] = matrix
for i in range(1,to_mult[0]+1):
    mult_dict[i] = mult(mult_dict[i-1], mult_dict[i-1])
result = mult_dict[to_mult[0]]
for i in range(1,len(to_mult)):
    result = mult(result, mult_dict[to_mult[i]])
for i in result:
    for j in i:
        sys.stdout.write("%d "%(j%1000))
    sys.stdout.write("\n")

