time = list(map(int,input().split()))
hour = [i for i in range(24)]
minute = [i for i in range(60)]
if time[1] - 45 < 0:
    print(hour[time[0]-1],end = ' ')
    print(minute[time[1]-45])
else:
    print(hour[time[0]],end = ' ')
    print(minute[time[1]-45])