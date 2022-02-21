import sys

n, m = map(int, sys.stdin.readline().split())

v = 'aeiou'
c = 'bcdfghjklmnpqrstvwxyz'

weight = {}
strings = {}

for i in range(len(c)):
    weight[c[i]] = i
    strings[c[i]] = []



# st = []
for _ in range(n):
    q = sys.stdin.readline().strip()
    # temp = 0b0
    # for i in q:
    #     temp |= (1 << (ord(i) - 97))
    # st.append(temp)
    temp = set()
    for i in q:
        temp.add(i)

    num = 0b0
    for i in temp:
        if i in v:
            continue
        num += 1 << weight.get(i)
    for i in temp:
        if i in v:
            continue
        strings.get(i).append(num)


# forget = 0b0
# # f = []
# # nf = []
# for _ in range(m):
#     count = 0
#     q = list(sys.stdin.readline().split())
#     if q[0] == '1':
#         forget |= (1 << (ord(q[1]) - 97))
#         for i in st:
#             if i & forget == 0:
#                 count += 1
#
#         print(count)
#     else:
#         forget &= ~(1 << (ord(q[1]) - 97))
#
#         for i in st:
#             if i & forget == 0:
#                 count += 1
#         print(count)
#
cur = (1 << 21) - 1
for _ in range(m):
    o, x = sys.stdin.readline().split()
    if o == '1':
        for i in strings.get(x):
            if cur & i == i:
                n -= 1
        cur -= 1 << weight.get(x)
    else:
        cur += 1 << weight.get(x)
        for i in strings.get(x):
            if cur & i == i:
                n += 1
    print(n)