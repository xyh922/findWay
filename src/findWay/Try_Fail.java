package findWay;

import map.Map_;

import java.util.ArrayList;
import java.util.List;

/*
 * 记录失败路线的试验品！
 * */
public class Try_Fail extends FindWay {
    /*
     * 0为上
     * 1为下
     * 2为左
     * 3为右
     * */

    boolean mapInfo[][];
    //当前位置
    int now_x;
    int now_y;
    int[] end;
    //上一步操作  0为上 1为下 2为左 3为右  -1位无操作

    //失败路线集合
    List<Step> faillist = new ArrayList();
    //成功点集合
    List<int[]> suclist = new ArrayList();


    @Override
    public void find(Map_ map) {
        //当前点为起点
        now_x = map.getStart()[0];
        now_y = map.getStart()[1];


        end = map.getEnd();
        System.out.println("起点为"+now_x+","+now_y);
        mapInfo = map.getMapInfo();
        //尝试找终点
        while (true) {
            if (end[0] == now_x && end[1] == now_y) {
                System.out.println("找到终点结束");
                break;
            }
            if (up(now_x, now_y) && !hasStep(new Step(now_x, now_y, 0))) {
                continue;
            }
            if (down(now_x, now_y) && !hasStep(new Step(now_x, now_y, 1))) {
                continue;
            }
            if (left(now_x, now_y) && !hasStep(new Step(now_x, now_y, 2))) {
                continue;
            }
            if (right(now_x, now_y) && !hasStep(new Step(now_x, now_y, 3))) {
                continue;
            }
            for (Step s : faillist) {
                System.out.println(s.toString());
            }

        }
    }

    @Override
    public void shouLine(Map_ map_) {

    }

    private boolean up(int x, int y) {

        //判断是否能走
        if (mapInfo[x][y - 1]) {
            now_y = y - 1;

            System.out.println("x:" + x + "y:" + y + "  向上走成功");
            return true;
        } else {
            addlist(new Step(x, y, 0));
            System.out.println("x:" + x + "y:" + y + "  向上走失败");
            return false;
        }

    }

    private boolean down(int x, int y) {
        if (mapInfo[x][y + 1]) {
            now_y = y + 1;
            System.out.println("x:" + x + "y:" + y + "  向下走成功");
            return true;
        } else {
            addlist(new Step(x, y, 1));
            System.out.println("x:" + x + "y:" + y + "  向下走失败");
            return false;
        }
    }

    private boolean left(int x, int y) {
        if (mapInfo[x - 1][y]) {
            now_x = x - 1;
            System.out.println("x:" + x + "y:" + y + "  向左走成功");
            return true;
        } else {
            addlist(new Step(x, y, 2));
            System.out.println("x:" + x + "y:" + y + "  向左走失败");
            return false;
        }
    }

    private boolean right(int x, int y) {
        if (mapInfo[x + 1][y]) {
            now_x = x + 1;
            System.out.println("x:" + x + "y:" + y + "  向右走成功");
            return true;

        } else {
            addlist(new Step(x, y, 3));
            System.out.println("x:" + x + "y:" + y + "  向右走失败");
            return false;
        }
    }

    private void addlist(Step s) {
        boolean flag = false;
        for (Step ls : faillist) {
            //如果没有相同元素则添加
            if (ls.equals(s)) {
                flag = true;
            }
        }
        if (!flag) {
            faillist.add(s);
        }
    }

    //真为存在
    //假为不存在
    private boolean hasStep(Step s) {
        for (Step ls : faillist) {
            //如果没有相同元素则添加
            if (ls.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
