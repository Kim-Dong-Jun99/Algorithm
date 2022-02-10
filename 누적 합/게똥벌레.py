import sys

n, h = map(int, sys.stdin.readline().split())
top = [0 for _ in range(h + 1)]
bottom = [0 for _ in range(h + 1)]

for i in range(n):
    q = int(sys.stdin.readline())
    if i % 2 == 0:
        bottom[h - q + 1] += 1
    else:
        top[q] += 1

for i in range(h - 1, 0, -1):
    top[i] += top[i + 1]

for i in range(1, h + 1):
    bottom[i] += bottom[i - 1]

total = [0 for _ in range(h + 1)]

for i in range(1, h + 1):
    total[i] = top[i] + bottom[i]

total = total[1:]
result = min(total)
print(result, total.count(result))



