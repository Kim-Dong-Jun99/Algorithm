import sys

while True:
    query = sys.stdin.readline().rstrip()
    stack = []
    if len(query) == 1 and query[0] == '.':
        break
    else:
        fail = False
        for i in query:
            if i == '(' or i == '[':
                stack.append(i)
            elif i == ']' or i == ')':
                if stack:
                    if i == ']':
                        if stack[-1] == '[':
                            stack.pop()
                        else:
                            fail = True
                            break
                    else:
                        if stack[-1] == '(':
                            stack.pop()
                        else:
                            fail = True
                            break
                else:
                    fail = True
                    break
        if fail:
            print('no')
        else:
            if stack == []:
                print('yes')
            else:
                print('no')
