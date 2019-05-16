package findWay;

import map.Map_;

import java.util.*;

//有很大的问题！！无法解决！！！！
//起点有多条可达路径时出错
public class Try extends FindWay {

    //起点
    int start[];
    //终点
    int end[];
    boolean mapInfo[][];
    boolean flag = true;
    //当前位置
    int now_x;
    int now_y;
    //上一步操作  0为上 1为下 2为左 3为右  -1位无操作
    int last = -1;
    //移动步数
    int mov_step = 0;
    //删除的点
    int[] del;
    //记录成功记录
    HashMap suc_step = new HashMap();


    @Override
    public void find(Map_ map) {
        //0位为x 1位为y
        start = map.getStart();
        end = map.getEnd();
        System.out.println("起点为"+Arrays.toString(start));
        System.out.println("终点为"+Arrays.toString(end));
        //定义起点为当前位置
        now_x = start[0];
        now_y = start[1];
        //定义上一步位置当前位置
        mapInfo = map.getMapInfo();
        //记录点中加入起点
        suc_step.put(mov_step++,start);
        //自定义顺序：上下左右
        //返回上一步有问题！！
        _while:
        while (flag) {
            //找到终点，退出
            if (now_x == end[0] && now_y == end[1]) {
                System.out.println("找到出口");
                //当前点等于终点，跳出循环
                break _while;
            }

//上一步是向上走↑下一步就一定 *不会是* 向下走↓，避免无限循环
            //进行时，判断这个点是否已经走过，避免画圈

            if (last != 1 && up(now_x, now_y)) {
                continue;
            }
            if (last != 0 && down(now_x, now_y)) {
                continue;
            }
            if (last != 3 && left(now_x, now_y)) {
                continue;
            }
            if (last != 2 && right(now_x, now_y)) {
                continue;
            }


            //上下左右都走不通，返回上一步
            del= (int[]) suc_step.remove(--mov_step);
            System.out.println("删除的点为"+Arrays.toString(del));


            int now[] = (int[]) suc_step.get(mov_step-1);
            if(now!=null){
                now_x = now[0];
                now_y = now[1];
                if(del[0]+1==now_x){
                    last=3;
                }else if (del[0]-1==now_x)
                    last=2;
                else if(del[1]+1==now_y)
                    last=1;
                else if(del[1]-1==now_y)
                    last=0;

            }

            System.out.println("没有路，返回上一步");
            System.out.println("当前点为x:"+now_x+" y:"+now_y);

            if ( mov_step==-1) {
                //退回起点，跳出循环
                System.out.println("找不到出口");
                break _while;
            }


        }
        Iterator iterator = suc_step.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, int[]> entry = (Map.Entry<Integer, int[]>) iterator.next();
            int p[] = entry.getValue();
            System.out.println("第" + entry.getKey() + "步:" +
                    "x=" + p[0] + " y=" + p[1]);

        }
    }

    private boolean up(int x, int y) {

        y = y - 1;
        //判断是否能走
        if (mapInfo[x][y]) {
            //记录本次行动
            //判断之前是否走过
            if (isPassed(x, y)) {
                //走过
                System.out.println("x:" + x + "y:" + y + "记录存在这个点，不走");
                return false;
            } else {
                //更新当前位置
                last = 0;

                now_x = x;
                now_y = y;
                System.out.println("第" + (mov_step) + "步，" + "x:" + now_x + "y:" + now_y + "  向上走成功并记录");
                suc_step.put(mov_step++, new int[]{now_x, now_y});

                return true;
            }

        } else {

            System.out.println("x:" + x + "y:" + y + "  向上走失败");
            return false;
        }

    }

    private boolean down(int x, int y) {
        y = y + 1;
        if (mapInfo[x][y]) {
            //记录本次行动


            //判断之前是否走过
            if (isPassed(x, y)) {
                //走过
                System.out.println("x:" + x + "y:" + y + "记录存在这个点，不走");
                return false;
            } else {
                //更新当前位置
                last = 1;

                now_x = x;
                now_y = y;
                System.out.println("第" + (mov_step) + "步，" + "x:" + now_x + "y:" + now_y + "  向下走成功并记录");
                suc_step.put(mov_step++, new int[]{now_x, now_y});
                return true;
            }
        } else {
            System.out.println("x:" + x + "y:" + y + "  向下走失败");
            return false;
        }
    }

    private boolean left(int x, int y) {
        x = x - 1;
        if (mapInfo[x][y]) {
            //记录本次行动


            //判断之前是否走过
            if (isPassed(x, y)) {
                //走过
                System.out.println("x:" + x + "y:" + y + "记录存在这个点，不走");
                return false;
            } else {
                //更新当前位置
                last = 2;

                now_x = x;
                now_y = y;
                System.out.println("第" + (mov_step) + "步，" + "x:" + now_x + "y:" + now_y + "  向左走成功并记录");
                suc_step.put(mov_step++, new int[]{now_x, now_y});
                return true;
            }
        } else {
            System.out.println("x:" + x + "y:" + y + "  向左走失败");
            return false;
        }
    }

    private boolean right(int x, int y) {
        x = x + 1;
        if (mapInfo[x][y]) {
            //记录本次行动

            //判断之前是否走过
            if (isPassed(x, y)) {
                //走过
                System.out.println("x:" + x + "y:" + y + "记录存在这个点，不走");
                return false;
            } else {
                //更新当前位置
                last = 3;

                now_x = x;
                now_y = y;
                System.out.println("第" + (mov_step) + "步，" + "x:" + now_x + "y:" + now_y + "  向右走成功并记录");
                suc_step.put(mov_step++, new int[]{now_x, now_y});
                return true;
            }
        } else {
            System.out.println("x:" + x + "y:" + y + "  向右走失败");
            return false;
        }
    }

    private boolean isPassed(int x, int y) {
        //从记录中取出所有走过的坐标
        Collection<int[]> postion = suc_step.values();
//判断坐标是否已经走过
        for (int[] v : postion) {
            if (x == v[0] && y == v[1]) {
                //坐标走过，返回真,跳过
                return true;
            }
        }
        return false;

    }
}
