import sys
N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
broken = set(list(sys.stdin.readline().split()))
result = abs(N - 100)
for i in range(1000001):
    go_next = False
    for j in str(i):
        if j in broken:
            go_next = True
            break
    if not go_next:
        temp_result = len(str(i)) + abs(N - i)
        result = min(temp_result, result)
sys.stdout.write("%d\n"%result)
        