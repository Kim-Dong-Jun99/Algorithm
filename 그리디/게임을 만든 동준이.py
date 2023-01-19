import sys

def smaller(i,j):
    if i > j:
        return j
    return i

N = int(sys.stdin.readline())
scores = [int(sys.stdin.readline()) for _ in range(N)]
result = 0
for i in range(N-2, -1, -1):
    if scores[i] >= scores[i+1]:
        result += scores[i] - (scores[i+1]-1)
        scores[i] = scores[i+1] - 1

print(result)