import sys
n = int(sys.stdin.readline())
str = [sys.stdin.readline().strip() for i in range(n)]
for j in range(n):
    stack = []
    check = True
    for i in str[j]:
        if i == '(':
            stack.append('(')
        else:
            if stack != []:
                stack.pop()
            else:
                check = False
                break
    if check:
        if stack == []:
            print('YES')
        else:
            print('NO')
    else:
        print('NO')