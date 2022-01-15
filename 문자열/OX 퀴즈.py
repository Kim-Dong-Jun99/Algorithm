import sys
n = int(sys.stdin.readline())
query = [sys.stdin.readline().rstrip() for i in range(n)]
for i in query:
    result = 0
    temp = 0
    for j in i:
        if j == 'O':
            temp += 1
            result += temp
        else:
            temp = 0
    print(result)