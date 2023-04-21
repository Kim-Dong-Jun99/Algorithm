import sys


def rotate(wheel, direction):
    if direction == 1:  # 시계 방향으로 회전
        remain = wheel.pop(len(wheel) - 1)
        wheel.insert(0, remain)
    elif direction == -1:  # 반시계 방향으로 회전
        remain = wheel.pop(0)
        wheel.append(remain)


temp_wheel = [sys.stdin.readline().rstrip() for _ in range(4)]
wheel = [[] for _ in range(4)]
for temp in range(4):
    for i in temp_wheel[temp]:
        wheel[temp].append(i)
test_case = int(sys.stdin.readline())
three, nine = 2, 6
for _ in range(test_case):
    target, direction = map(int, sys.stdin.readline().split())
    target -= 1
    rotation = [0, 0, 0, 0]
    rotation[target] = direction
    left_rotate = target
    right_rotate = target
    # print(target)
    while left_rotate - 1 >= 0:
        if wheel[left_rotate][nine] != wheel[left_rotate - 1][three]:
            rotation[left_rotate - 1] = rotation[left_rotate] * -1
            left_rotate -= 1
        else:
            break
    while right_rotate + 1 <= 3:
        if wheel[right_rotate][three] != wheel[right_rotate + 1][nine]:
            rotation[right_rotate + 1] = rotation[right_rotate] * -1
            right_rotate += 1
        else:
            break
    for i in range(4):
        rotate(wheel[i], rotation[i])
answer = 0
if wheel[0][0] == '1':
    answer += 1
if wheel[1][0] == '1':
    answer += 2
if wheel[2][0] == '1':
    answer += 4
if wheel[3][0] == '1':
    answer += 8

print(answer)
