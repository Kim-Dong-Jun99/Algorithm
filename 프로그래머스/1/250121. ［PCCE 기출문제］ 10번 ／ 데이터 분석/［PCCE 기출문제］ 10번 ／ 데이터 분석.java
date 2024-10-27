import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<Data> filtered = filter(data, ext, val_ext);
        if (sort_by.equals("code")) {
            filtered.sort(Data::sortByCode);
        }
        if (sort_by.equals("date")) {
            filtered.sort(Data::sortByDate);
        }
        if (sort_by.equals("maximum")) {
            filtered.sort(Data::sortByMax);
        }
        if (sort_by.equals("remain")) {
            filtered.sort(Data::sortByRemain);
        }
        return listToArray(filtered);
    }
    
    int[][] listToArray(List<Data> datas) {
        int[][] result = new int[datas.size()][4];
        for (int i = 0; i < datas.size(); i++) {
            Data d = datas.get(i);
            result[i][0] = d.code;
            result[i][1] = d.date;
            result[i][2] = d.max;
            result[i][3] = d.remain;
        }
        return result;
    }
    
    List<Data> filter(int[][] data, String ext, int val_ext) {
        List<Data> result = new ArrayList<>();
        for (int[] d : data) {
            if (ext.equals("code")) {
                if (d[0] < val_ext) {
                    result.add(new Data(d));
                }
            }
            if (ext.equals("date")) {
                if (d[1] < val_ext) {
                    result.add(new Data(d));
                }
            }
            if (ext.equals("maximum")) {
                if (d[2] < val_ext) {
                    result.add(new Data(d));
                }
            }
            if (ext.equals("remain")) {
                if (d[3] < val_ext) {
                    result.add(new Data(d));
                }
            }
        }
        return result;
    }
    
    static class Data {
        int code;
        int date;
        int max;
        int remain;
        
        Data(int[] data) {
            this.code = data[0];
            this.date = data[1];
            this.max = data[2];
            this.remain = data[3];
        }
        
        int sortByCode(Data compare) {
            return Integer.compare(this.code, compare.code);
        }
        
        int sortByDate(Data compare) {
            return Integer.compare(this.date, compare.date);
        }
        
        int sortByMax(Data compare) {
            return Integer.compare(this.max, compare.max);
        }
        
        int sortByRemain(Data compare) {
            return Integer.compare(this.remain, compare.remain);
        }
    }
}