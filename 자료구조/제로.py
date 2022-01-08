import sys

n = int(sys.stdin.readline())
query = [sys.stdin.readline().strip() for i in range(n)]
stack = []
for i in query:
    if i != '0':
        stack.append(int(i))
    else:
        stack.pop()
print(sum(stack))
