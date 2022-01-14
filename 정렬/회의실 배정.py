import sys

n = int(sys.stdin.readline())
times = [list(map(int, sys.stdin.readline().split())) for i in range(n)]


def mergesort(times):
    if len(times) == 1:
        return times
    else:
        mid = int(len(times) / 2)
        return merge(mergesort(times[:mid]), mergesort(times[mid:]))


def merge(list1, list2):
    index1 = 0
    index2 = 0
    result = []
    while True:
        if list1[index1][0] < list2[index2][0]:
            result.append(list1[index1][:])
            index1 += 1
        elif list1[index1][0] > list2[index2][0]:
            result.append(list2[index2][:])
            index2 += 1
        else:
            if list1[index1][1] < list2[index2][1]:
                result.append(list1[index1][:])
                index1 += 1
            else:
                result.append(list2[index2][:])
                index2 += 1
        if index1 == len(list1):
            while index2 < len(list2):
                result.append(list2[index2][:])
                index2 += 1
            break
        elif index2 == len(list2):
            while index1 < len(list1):
                result.append(list1[index1][:])
                index1 += 1
            break
    return result


times = mergesort(times)
result = 1
cur = None
i = 0
while i < len(times):
    if i == 0:
        cur = times[i][:]
    else:
        if cur[1] > times[i][1]:
            cur = times[i][:]
        elif times[i][0] >= cur[1]:
            result += 1
            cur = times[i][:]
    i += 1

print(result)
