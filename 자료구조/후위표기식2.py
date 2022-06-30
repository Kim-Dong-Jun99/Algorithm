import sys
n = int(sys.stdin.readline())
expression = sys.stdin.readline()
numbers = [int(sys.stdin.readline()) for _ in range(n)]
dic = {}
index = 0
stack = []
for i in expression:
    if i.isalpha() and (i not in dic):
        dic[i] = numbers[index]
        index += 1
print(dic)
for i in expression:
    # print(stack)
    if i.isalpha():
        stack.append(dic[i])
    else:
        if i == '+':
            temp = stack.pop()
            temp += stack.pop()
            stack.append(temp)
        elif i == '*':
            temp = stack.pop()
            temp *= stack.pop()
            stack.append(temp)
        elif i == '-':
            temp = stack.pop()
            temp = stack.pop() - temp
            stack.append(temp)
        elif i == '/':
            temp = stack.pop()
            temp = stack.pop() / temp
            stack.append(temp)
        else:
            break

print("%0.2f"%(stack[-1]))
