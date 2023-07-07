from collections import defaultdict
import math
def solution(fees, records):
    answer = []
    in_out = defaultdict(lambda : [])
    for r in records:
        time, car_num, action = r.split()
        in_out[car_num].append(time)
    ordered = sorted(in_out.keys())
    
    def time_diff(a,b):
        hour_a, min_a = a.split(":")
        hour_b, min_b = b.split(":")
        return abs(60*int(hour_a)+int(min_a) - 60*int(hour_b) - int(min_b))
    for car in ordered:
        stay_time = 0
        if len(in_out[car]) % 2 == 1:
            in_out[car].append("23:59")
        for i in range(0, len(in_out[car]), 2):
            stay_time += time_diff(in_out[car][i], in_out[car][i+1])
        # print(car, stay_time)
        if stay_time <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((stay_time - fees[0])/fees[2]) * fees[3] )
        
    return answer