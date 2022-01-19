import sys

lstack = list(sys.stdin.readline().rstrip())
rstack = []
n = int(sys.stdin.readline())

for i in range(n):
    query = list(sys.stdin.readline().split())
    if query[0] == 'L':
        if lstack:
            rstack.append(lstack.pop())
    elif query[0] == 'D':
        if rstack:
            lstack.append(rstack.pop())
    elif query[0] == 'B':
        if lstack:
            lstack.pop()
    elif query[0] == 'P':
        lstack.append(query[1])
print(''.join(lstack + list(reversed(rstack))))


