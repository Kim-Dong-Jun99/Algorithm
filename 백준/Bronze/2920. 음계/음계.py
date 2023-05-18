ns = list(map(int, input().split()))

asc = ns.copy()
desc = ns.copy()
asc.sort()
desc.sort(reverse = True)

if ns == asc:
    print('ascending')
elif ns == desc:
    print('descending')
else:
    print('mixed')