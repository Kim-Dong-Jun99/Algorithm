import sys
n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline())for i in range(n)]
ns.sort()
for i in ns:
    print(i)