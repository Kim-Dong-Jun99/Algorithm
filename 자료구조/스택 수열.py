import sys

n = int(sys.stdin.readline())
query = [int(sys.stdin.readline()) for i in range(n)]
target = [i for i in range(1, n + 1)]
me = []
stack = []
index = 0
fail = False
while index < n:
    while target and query[index] != target[0]:
        stack.append(target.pop(0))
        me.append('+')

    if target:
        stack.append(target.pop(0))
        me.append('+')
        while stack and index < n and query[index] == stack[-1]:
            index += 1
            me.append('-')
            stack.pop()
    else:
        if stack[-1] == query[index]:
            while stack and index < n and query[index] == stack[-1]:
                index += 1
                me.append('-')
                stack.pop()
            if stack or index != n:
                fail = True
                break
        else:
            fail = True
            break
if fail:
    print('NO')
else:
    for i in me:
        print(i)


