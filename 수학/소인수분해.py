n = int(input())
i = 2
while i <= int(n**(0.5)):
    while n % i == 0 and n != 1:
        print(i)
        n = int(n / i)
    i += 1
if n != 1:
    print(int(n))