import sys
n = int(sys.stdin.readline())
query = [int(sys.stdin.readline()) for i in range(n)]
query.sort()
for i in query:
    print(i)