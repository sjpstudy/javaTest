package algorithm;

import java.util.*;

public class GraphTest1 {
    static int[][] zupu;
    static BitSet[] zupubs;
    static char[] companies;
    static List<String> result = new ArrayList<>();

    static {
        // 通过数组建立客户和下标的映射关系，0代表A客户
        companies = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //族谱
        zupu = new int[][]{{0, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0}};

        // 通过位数组存储族谱
        zupubs = new BitSet[companies.length];
        for (int d = 0; d < zupubs.length; d++) {
            zupubs[d] = new BitSet(companies.length);
        }

        for (int i = 0; i < zupu.length; i++) {
            for (int j = 0; j < zupu.length; j++) {
                if (zupu[i][j] == 1) {
                    zupubs[i].set(i);
                }
            }
        }

    }

    /**
     *
     * @param beg 核心企业
     * @param end 需要查询层级的供应商
     * @param ret 存储每一次结果
     * @param cutIndex ret的游标
     * @param map 保存已遍历的节点及次数
     */
    public static void find(int beg, int end, char[] ret, int cutIndex, Map<Integer, Integer> map) {
        // 如果是同一个客户则写结果后返回
        if (beg == end && !map.containsKey(beg)) {
            result.add(companies[end] + "");
            return;

        }

        ret[cutIndex++] = companies[beg];
        for (int i = 0; i < zupubs.length; i++) {
            if (i == beg) {
                continue;
            }

            if (zupubs[beg].get(i)) {
                if (map.get(i) != null
                        && map.get(beg) != null
                        && map.get(i) == 1
                        && map.get(beg) > 1) {
                    continue;
                }
                if (i == end) {
                    ret[cutIndex++] = companies[end];
                    StringBuffer sb = new StringBuffer();
                    for (int k = 0; i < cutIndex; k++) {
                        sb.append(ret[k]);
                    }
                    result.add(sb.toString());
                    cutIndex--;
                    if (map.containsKey(i)) {
                        continue;
                    }
                }

                map.put(i, map.get(i) == null ? 1 : map.get(i) + 1);
                find(i, end, ret, cutIndex, map);
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }
            }
        }
        cutIndex--;
    }

    public static void main(String[] args) {
        find(0, 5, new char[zupubs.length * 2], 0, new HashMap<>());
        for (String str : result) {
            System.out.println(str);
        }
    }
}
