import sys

N, M = map(int, sys.stdin.readline().split())

locations = list(map(int, sys.stdin.readline().split()))
locations.append(0)
locations.sort()
zero_index = locations.index(0)
result = 0
negative = zero_index
positive = N - zero_index
if locations[N] == 0:
    repeat_time = negative // M
    remainder = negative % M
    if repeat_time != 0:
        for i in range(repeat_time):
            if i == 0:
                result += abs(locations[i * M])
            else:
                result += abs(locations[i * M] * 2)
        result += abs(locations[zero_index - remainder] * 2)
    else:
        result += abs(locations[0])
# 	print(result)
elif locations[0] == 0:
    repeat_time = positive // M
    remainder = positive % M
    if repeat_time != 0:
        for i in range(repeat_time):
            if i == 0:
                result += locations[N - i * M]
            else:
                result += locations[N - i * M] * 2
        result += locations[zero_index + remainder] * 2
    else:
        result += locations[N]
# 	print(result)
else:
    if abs(locations[0]) > abs(locations[N]):
        # 음수가 더 큼
        repeat_time = positive // M
        remainder = positive % M
        if repeat_time != 0:
            for i in range(repeat_time):
                result += locations[N - i * M] * 2
            result += locations[zero_index + remainder] * 2
        else:
            result += locations[N] * 2
        repeat_time = negative // M
        remainder = negative % M
        if repeat_time != 0:
            for i in range(repeat_time):
                if i == 0:
                    result += abs(locations[i * M])
                else:
                    result += abs(locations[i * M] * 2)
            result += abs(locations[zero_index - remainder] * 2)
        else:
            result += abs(locations[0])


    else:
        # 양수가 더 큼
        repeat_time = negative // M
        remainder = negative % M
        if repeat_time != 0:
            for i in range(repeat_time):
                result += abs(locations[i * M] * 2)
            result += abs(locations[zero_index - remainder] * 2)
        else:
            result += abs(locations[0]) * 2


        repeat_time = positive // M
        remainder = positive % M
        if repeat_time != 0:
            for i in range(repeat_time):
                if i == 0:
                    result += locations[N - i * M]
                else:
                    result += locations[N - i * M] * 2
            result += locations[zero_index + remainder] * 2
        else:
            result += locations[N]


print(result)
