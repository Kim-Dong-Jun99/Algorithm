S = input()
T = input()
flipped = False
left_tail = 0
right_tail = len(T) - 1
for i in range(len(T), len(S), -1):
    if flipped:
        if T[right_tail] == 'A':
            right_tail += 1
        else:
            flipped = False
            right_tail += 1
            left_tail, right_tail = right_tail, left_tail
    else:
        if T[right_tail] == 'A':
            right_tail -= 1
        else:
            flipped = True
            right_tail -= 1
            left_tail, right_tail = right_tail, left_tail

result = ""
if flipped:
    for i in range(left_tail, right_tail-1, -1):
        result += T[i]
else:
    for i in range(left_tail, right_tail+1):
        result += T[i]


# print(result)
if result == S:
    print(1)
else:
    print(0)