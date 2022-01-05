testcase = int(input())
for i in range(testcase):
    query = list(map(int,input().split()))
    query = query[1:]
    mean = sum(query)/len(query)
    aboveAV = 0
    for j in query:
        if j > mean:
            aboveAV += 1
    value = (aboveAV/len(query))*100
    print('%0.3f%%'%value)