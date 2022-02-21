import sys

n, m = map(int, sys.stdin.readline().split())

st = []
for _ in range(n):
    q = sys.stdin.readline().strip()
    temp = 0b0
    for i in q:
        temp |= (1 << (ord(i) - 97))
    st.append(temp)

forget = 0b0
for _ in range(m):
    count = 0
    q = list(sys.stdin.readline().split())
    if q[0] == '1':
        forget |= (1 << (ord(q[1]) - 97))
        for i in st:
            if i & forget == 0:
                count += 1
        print(count)
    else:
        forget &= ~(1 << (ord(q[1]) - 97))

        for i in st:
            if i & forget == 0:
                count += 1
        print(count)

