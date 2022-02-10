import sys

n = int(sys.stdin.readline())
ns = list(map(int, sys.stdin.readline().split()))

freq = [0] * (max(ns) + 1)
for i in ns:
    freq[i] += 1

ngf = [0] * (n)
stack = []

# print(freq)
for i in range(n):
    if stack == []:
        stack.append(i)
    else:
        while stack and freq[ns[stack[-1]]] < freq[ns[i]]:
            ngf[stack[-1]] = ns[i]
            stack.pop()
        stack.append(i)
for i in ngf:
    if i != 0:
        print(i, end=' ')
    else:
        print(-1, end=' ')