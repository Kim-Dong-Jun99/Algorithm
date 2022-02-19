import sys

tc = int(sys.stdin.readline())
for _ in range(tc):
    n = int(sys.stdin.readline())
    table = {}
    for __ in range(n):
        c, t = sys.stdin.readline().split()
        if table.get(t, - 1) == -1:
            table[t] = [c]
        else:
            temp = table[t]
            temp.append(c)
            table[t] = temp
    result = 1
    for i in table.keys():
        result *= len(table[i]) + 1
    # print(table)
    print(result - 1)

