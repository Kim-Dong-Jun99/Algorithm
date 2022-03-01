import sys

n = int(sys.stdin.readline())
classes = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
days = [0 for _ in range(10**4+1)]
classes.sort(key = lambda a: a[0], reverse=True)
result = 0
# print(classes)
for i in classes:

    j = i[1]
    while j > 0 and days[j] == 1:
        j -= 1
    if j != 0:
        days[j] = 1
        result += i[0]

print(result)



