changes = [500,100,50,10,5,1]
value = 1000 - int(input())
result = 0
for i in changes:
    result += value // i
    value = value % i
print(result)