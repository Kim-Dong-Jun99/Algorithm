import sys

tc = int(sys.stdin.readline())
for _ in range(tc):
    start, end = map(int, sys.stdin.readline().split())
    dif = end - start
    turn = 1
    compare = 1
    l = 1
    while True:
        if compare <= dif and dif < compare + l:
            print(turn)
            break
        if turn % 2 == 1:
            compare += l
        else:
            compare += l
            l += 1
        turn += 1
