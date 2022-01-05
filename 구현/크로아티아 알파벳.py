n = input()
calpha = ['c=','c-','dz=','d-','lj','nj','s=','z=']
i = 0
result = 0
while i <len(n):
    if (n[i:i+2] in calpha):
        i += 2
    elif (n[i:i+3] in calpha):
        i += 3
    else:
        i += 1
    result += 1
print(result)