import sys
from collections import defaultdict

N, C = map(int, sys.stdin.readline().split())
M = int(sys.stdin.readline())

delivery = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
# 목적지 순으로 정렬
delivery.sort(key=lambda x: x[1]*10 + x[0])
weight_diff = defaultdict(int)
result = 0



print(result)
