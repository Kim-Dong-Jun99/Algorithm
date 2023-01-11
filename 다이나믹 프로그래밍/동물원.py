import sys

n = int(sys.stdin.readline())

cur = [1,1,1]

# 0 - 둘다 빔, 1 - 왼쪽만 참, 2 - 오른쪽만 참

for i in range(1, n):
    temp = [0,0,0]
    temp[0] = cur[0] + cur[1] + cur[2]
    temp[1] = cur[0] + cur[2]
    temp[2] = cur[0] + cur[1]
    cur = temp

print(sum(cur) % 9901)
