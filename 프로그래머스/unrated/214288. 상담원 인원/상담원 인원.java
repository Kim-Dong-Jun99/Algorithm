import java.util.*;

class Solution {
    
    int answer, k, n;
    int[] counselors;
    int[] waitingTime;
    HashMap<Integer, List<Meeting>> meetingSchedule;
    public int solution(int k, int n, int[][] reqs) {
        init(k, n, reqs);
        solve();
        return answer;
    }

    void init(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        answer = 0;
        counselors = new int[k + 1];
        waitingTime = new int[k + 1];
        Arrays.fill(counselors, 1);
        meetingSchedule = new HashMap<>();
        for (int[] req : reqs) {
            List<Meeting> meetings = meetingSchedule.getOrDefault(req[2], new ArrayList<>());
            meetings.add(new Meeting(req[0], req[1]));
            meetingSchedule.put(req[2], meetings);
        }
    }

    void solve() {
        updateWaitingTime();
        for (int i = 0; i < n - k; i++) {
            updateCounselors();
        }
        updateAnswer();
    }

    void updateWaitingTime() {
        for (int i = 1; i <= k; i++) {
            int heapSize = counselors[i];
            PriorityQueue<Integer> meetingEndTime = new PriorityQueue<>();
            int tempTime = 0;
            for (Meeting meeting : meetingSchedule.getOrDefault(i, new ArrayList<>())) {
                if (meetingEndTime.size() < heapSize) {
                    meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                } else {
                    if (meetingEndTime.peek() <= meeting.startTime) {
                        meetingEndTime.remove();
                        meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                    } else {
                        tempTime += meetingEndTime.peek() - meeting.startTime;
                        meetingEndTime.add(meetingEndTime.peek() + meeting.requestMeetingTime);
                        meetingEndTime.remove();
                    }
                }
            }
            waitingTime[i] = tempTime;
        }
    }

    void updateCounselors() {
        PriorityQueue<DecreaseTime> decreaseTimes = new PriorityQueue<>(DecreaseTime::compareWithDecreasedTime);
        for (int i = 1; i <= k; i++) {
            int heapSize = counselors[i] + 1;
            PriorityQueue<Integer> meetingEndTime = new PriorityQueue<>();
            int tempTime = 0;
            for (Meeting meeting : meetingSchedule.getOrDefault(i, new ArrayList<>())) {
                if (meetingEndTime.size() < heapSize) {
                    meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                } else {
                    if (meetingEndTime.peek() <= meeting.startTime) {
                        meetingEndTime.remove();
                        meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                    } else {
                        tempTime += meetingEndTime.peek() - meeting.startTime;
                        meetingEndTime.add(meetingEndTime.peek() + meeting.requestMeetingTime);
                        meetingEndTime.remove();

                    }
                }
            }
            decreaseTimes.add(new DecreaseTime(i, waitingTime[i] - tempTime));
        }


        counselors[decreaseTimes.peek().type] += 1;
        waitingTime[decreaseTimes.peek().type] -= decreaseTimes.peek().decreasedTime;
    }

    void updateAnswer() {
        for (int i = 1; i <= k; i++) {
            int heapSize = counselors[i];
            PriorityQueue<Integer> meetingEndTime = new PriorityQueue<>();
            for (Meeting meeting : meetingSchedule.getOrDefault(i, new ArrayList<>())) {
                if (meetingEndTime.size() < heapSize) {
                    meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                } else {
                    if (meetingEndTime.peek() <= meeting.startTime) {
                        meetingEndTime.remove();
                        meetingEndTime.add(meeting.startTime + meeting.requestMeetingTime);
                    } else {
                        answer += meetingEndTime.peek() - meeting.startTime;
                        meetingEndTime.add(meetingEndTime.peek() + meeting.requestMeetingTime);
                        meetingEndTime.remove();
                    }
                }
            }
        }
    }

    static class Meeting {
        int startTime;
        int requestMeetingTime;

        public Meeting(int startTime, int requestMeetingTime) {
            this.startTime = startTime;
            this.requestMeetingTime = requestMeetingTime;
        }
    }

    static class DecreaseTime {
        int type;
        int decreasedTime;

        public DecreaseTime(int type, int decreasedTime) {
            this.type = type;
            this.decreasedTime = decreasedTime;
        }

        int compareWithDecreasedTime(DecreaseTime compare) {
            return Integer.compare(compare.decreasedTime, this.decreasedTime);
        }
    }

}