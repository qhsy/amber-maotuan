package zz.mk.utilslibrary.algo.dijkstra;

import java.util.HashMap;

/**
 * author: zhu on 2017/5/2 11:49
 * email: mackkill@gmail.com
 */

public interface Distance {
    public static final MinStep UNREACHABLE = new MinStep(false, -1);
    /**
     * @param start
     * @param end
     * @param stepLength
     * @return
     * @Author:lulei
     * @Description: 起点到终点的最短路径
     */
    public MinStep getMinStep(int start, int end, final HashMap<Integer, HashMap<Integer, Integer>> stepLength);
}
