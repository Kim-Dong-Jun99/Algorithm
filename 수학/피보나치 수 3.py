n = int(input())
fib = [0,1]
m = 10**6
p = m // 10*15
for i in range(2,p):
    fib.append(fib[i-1]+fib[i-2])
    fib[i] %= m
print(fib[n%p])