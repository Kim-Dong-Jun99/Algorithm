import sys

laser = sys.stdin.readline().strip()
ironStack = []
result = 0
i = 0
while i < len(laser):
    if laser[i] == '(':
        if laser[i + 1] == ')':
            result += len(ironStack)
            i += 2
        else:
            ironStack.append('(')
            i += 1
    else:
        result += 1
        ironStack.pop()
        i += 1
print(result)
