import sys

g = int(sys.stdin.readline())
p = int(sys.stdin.readline())

gates = [0 for _ in range(g + 1)]
count = 0
for _ in range(p):
    plane = int(sys.stdin.readline())
    check = True
    for i in range(plane, 0, -1):
        if gates[i] == 0:
            gates[i] = 1
            check = False
            break

    if check:
        break
    count += 1
print(count)