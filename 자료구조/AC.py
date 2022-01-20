import sys
from collections import deque

tc = int(sys.stdin.readline())
for _ in range(tc):
    func = sys.stdin.readline().rstrip()
    n = int(sys.stdin.readline())
    ns = sys.stdin.readline().rstrip()[1:-1].split(',')
    if n == 0:
        ns = []
    else:
        ns = deque(ns)

    r = 0
    error = False
    for i in func:
        if i == 'R':
            r += 1
        else:
            if len(ns) < 1:
                error = True
                break
            else:
                if r % 2 == 1:
                    ns.pop()
                else:
                    ns.popleft()

    if error:
        print('error')
    else:
        if r % 2 == 1:
            ns.reverse()
            print("[" + ",".join(ns) + "]")
        else:
            print("[" + ",".join(ns) + "]")
