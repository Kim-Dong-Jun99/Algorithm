import sys
n = int(sys.stdin.readline())
ns = [int(sys.stdin.readline()) for _ in range(n)]
ns.sort()
result = 0
if ns[0] < 0:
    if ns[-1] > 0:
        index = 0
        while index < len(ns)-1:
            if ns[index] <= 0 and ns[index+1] <= 0:
                result += ns[index] * ns[index+1]
                index += 2
            else:
                if ns[index+1] > 0 and ns[index] <= 0:
                    result += ns[index]
                    index += 1
                break
        rindex = len(ns)-1
        while rindex >= index:
            if rindex-1 >= index:
                result += ns[rindex]*ns[rindex-1]
                rindex -= 2
            else:
                result += ns[rindex]
                break
        print(result)
    else:
        index = 0
        while index < len(ns)-1:
            result += ns[index] * ns[index+1]
            index += 2
        if index == len(ns)-1:
            result += ns[index]
        print(result)
else:
    index = len(ns)-1
    while index > 0:
        if (ns[index] != 0 and ns[index-1] != 0) and (ns[index] != 1 and ns[index-1] != 1):
            result += ns[index] * ns[index-1]
        else:
            result += ns[index] + ns[index-1]
        index -= 2
    if index == 0:
        result += ns[index]
    print(result)



