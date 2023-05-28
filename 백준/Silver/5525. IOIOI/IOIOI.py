import sys
N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
given = sys.stdin.readline().rstrip()
pattern = 'IO' * N
pattern += 'I'

#  kmp_table
table = [0 for _ in range(len(pattern))]
i = 0
for j in range(1, len(pattern)):
    while i > 0 and pattern[i] != pattern[j]:
        i = table[i-1]

    if pattern[i] == pattern[j]:
        i += 1
        table[j] = i
def kmp(all_string, pattern):
    #  kmp_table
    table = [0 for _ in range(len(pattern))]
    i = 0
    for j in range(1, len(pattern)):
        while i > 0 and pattern[i] != pattern[j]:
            i = table[i-1]

        if pattern[i] == pattern[j]:
            i += 1
            table[j] = i

    #  kmp
    result = []
    i = 0
    for j in range(len(all_string)):
        while i > 0 and pattern[i] != all_string[j]:
            i = table[i-1]

        if pattern[i] == all_string[j]:
            i += 1
            if i == len(pattern):
                result.append(j - i + 1)
                i = table[i-1]

    return result
sys.stdout.write("%d\n"%(len(kmp(given, pattern))))