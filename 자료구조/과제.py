import sys
n = int(input())
homeworks = []
for _ in range(n):
    deadline, score = map(int, sys.stdin.readline().split())
    homeworks.append((deadline, score))
homeworks.sort()
can    = []
date   = homeworks[-1][0]
answer = 0
while True:
    if date == 0:
        break
    while homeworks and homeworks[-1][0] >= date:
        can.append(homeworks.pop()[1])
    date -= 1
    if not can:
        continue
    can.sort()
    answer += can.pop()
print(answer)