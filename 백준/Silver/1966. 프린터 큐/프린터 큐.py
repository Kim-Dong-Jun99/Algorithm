import sys
testcase = int(sys.stdin.readline())
for tc in range(testcase):
    n, index = map(int,sys.stdin.readline().split())
    values = list(map(int,sys.stdin.readline().split()))
    queue = [i for i in range(n)]
    turn = 0
    while True:
        tempMax = max(values)
        while values[0] != tempMax:
            values.append(values.pop(0))
            queue.append(queue.pop(0))
        turn += 1
        if queue[0] == index:
            break
        else:
            values.pop(0)
            queue.pop(0)
    print(turn)