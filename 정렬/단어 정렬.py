import sys
n = int(sys.stdin.readline())
ns = [sys.stdin.readline().strip() for i in range(n)]
num = [[]for i in range(51)]
for i in ns:
    if (i not in num[len(i)]):
        num[len(i)].append(i[:])
for i in range(1,51):
    if num[i] != []:
        temp = num[i][:]
        temp.sort()
        for j in temp:
            print(j)