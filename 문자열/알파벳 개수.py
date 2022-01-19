str = input()
alpha = [0 for i in range(26)]
for i in str:
    alpha[ord(i)-97] += 1
for i in alpha:
    print(i,end = ' ')