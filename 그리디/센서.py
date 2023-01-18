import sys
n = int(sys.stdin.readline())
k = int(sys.stdin.readline())

sensors = list(map(int, sys.stdin.readline().split()))

sensors.sort()

dis_diff = []
for i in range(n-1):
    dis_diff.append(sensors[i+1] - sensors[i])

dis_diff.sort()

print(sum(dis_diff[:n-k]))