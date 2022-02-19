n = input()
result = ''
stack = []
reverse = True
for i in n:
    if i == '<':
        reverse = False
        while stack:
            result += stack.pop()
        result += i
    elif i == '>':
        reverse = True
        result += i
    elif i == ' ' and reverse:

        while stack:
            result += stack.pop()
        result += ' '
    else:
        if reverse:
            stack.append(i)
        else:
            result += i
while stack:
    result += stack.pop()
print(result)
