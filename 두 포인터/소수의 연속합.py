n = int(input())

count = 0
primes = []
for i in range(2, n + 1):
    check = True
    for j in range(2, int(i ** 0.5) + 1):
        if i % j == 0:
            check = False
            break
    if check:
        primes.append(i)
if primes:
    left, right = 0, 1

    while left <= right and right <= len(primes):
        tempsum = sum(primes[left:right])
        if tempsum == n:
            count += 1
            right += 1


        elif tempsum < n:
            right += 1
        else:
            left += 1
    print(count)
else:
    print(0)

