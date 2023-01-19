import sys

given = input()
n = len(given)
result = ""
continuous = 0
for i in range(n):
    if given[i] == 'X':
        continuous += 1
        if i == n - 1:
            b_val = continuous // 4
            result += "A" * 4 * b_val
            remaining = continuous % 4
            if remaining % 2 == 1:
                print(-1)
                sys.exit()
            result += "B" * 2 * (remaining // 2)
            continuous = 0
        elif given[i + 1] == '.':
            b_val = continuous // 4
            result += "A" * 4 * b_val
            remaining = continuous % 4
            if remaining % 2 == 1:
                print(-1)
                sys.exit()
            result += "B" * 2 * (remaining // 2)
            continuous = 0
    else:
        result += "."

print(result)