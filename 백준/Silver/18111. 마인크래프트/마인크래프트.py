import sys
from collections import defaultdict
N, M, B = map(int, sys.stdin.readline().split())

ground = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

heights = defaultdict(lambda : 0)

for i in range(N):
    for j in range(M):
        heights[ground[i][j]] += 1
hs = list(heights.keys())
hs.sort()
result_time = sys.maxsize
result_height = 0
for i in range(hs[0],hs[-1]+1):
    temp_time = 0
    spare = B
    done = True
    for j in range(len(hs)-1,-1,-1):
        if hs[j] > i:
            diff = hs[j] - i
            temp_time += diff * (heights[hs[j]]) * 2
            spare += diff * (heights[hs[j]])
        elif hs[j] < i:
            diff = i - hs[j]
            spare -= diff * (heights[hs[j]])
            if spare < 0:
                done = False
                break
            temp_time += diff * (heights[hs[j]])
    if done:
        if temp_time < result_time:
            result_time = temp_time
            result_height = i
        elif temp_time == result_time and i > result_height:
            result_height = i
            
sys.stdout.write("%d %d\n"%(result_time, result_height))
        