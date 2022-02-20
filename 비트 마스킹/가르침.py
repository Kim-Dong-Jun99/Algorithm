import sys
from itertools import combinations
n, k = map(int, sys.stdin.readline().split())
bintable = {'b': 20, 'd': 19, 'e': 18, 'f': 17, 'g': 16, 'h': 15, 'j': 14,
            'k': 13, 'l': 12, 'm': 11, 'o': 10, 'p': 9, 'q': 8, 'r': 7,
            's': 6, 'u': 5, 'v': 4, 'w': 3, 'x': 2, 'y': 1, 'z': 0}


def wordtobin(word):
    answer = 0b0
    for i in word:
        answer = answer | (1 << bintable[i])
    return answer


if k < 5:
    print(0)
else:
    default = {'a', 'n', 't', 'i', 'c'}
    words = []
    for _ in range(n):
        q = sys.stdin.readline().strip()
        temp = set()
        for i in range(4, len(q)-4):
            if q[i] not in default:
                temp.add(q[i])
        words.append(temp)
    binlist = [wordtobin(i) for i in words]
    result = 0

    power2 = [2**i for i in range(21)]

    for i in combinations(power2, k-5):
        cur = sum(i)
        count = 0
        for j in binlist:
            if j & cur == j:
                count += 1
        result = max(count, result)
    print(result)