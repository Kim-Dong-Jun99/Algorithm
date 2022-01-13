import sys

n = int(sys.stdin.readline())
ns = [i for i in range(1, n + 1)]

while True:
    temp = []
    i = 1

    while i < len(ns):
        temp.append(ns[i])
        i += 2
    if len(ns) % 2 == 1:
        temp.insert(0, ns[-1])
    ns = temp
    if len(ns) == 1:
        print(ns[0])
        break

