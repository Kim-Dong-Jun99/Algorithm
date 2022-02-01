n, m = map(int, input().split())
ns = list(map(int, input().split()))

l, r = 0, 1
count = 0
while l <= r and r <= n:
    t = ns[l:r]
    ts = sum(t)

    if ts == m:
        count += 1
        r += 1
    elif ts < m:
        r += 1
    else:
        l += 1
print(count)