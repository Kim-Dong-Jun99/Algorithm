n = int(input())

two_count = 0
five_count = 0
for i in range(2, n+1):
    if i % 2 == 0:
        for_two = i
        while for_two % 2 == 0:
            for_two /= 2
            two_count += 1
    if i % 5 == 0:
        for_five = i
        while for_five % 5 == 0:
            for_five /= 5
            five_count += 1
print(min([two_count, five_count]))