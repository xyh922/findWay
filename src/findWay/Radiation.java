package findWay;

import map.Map_;

import java.util.HashSet;
import java.util.Set;
//辐射的方式进行遍历
public class Radiation extends FindWay {
    boolean[][] info;
    int[] end;
    //存放可达位置的Set
    Set<Point> set = new HashSet();
    int height;
    int width;
    boolean mapInfo[][];
    //是否可以访问到终点
    boolean findFinal;
    @Override
    public void find(Map_ m) {
        //原地图信息
        info = m.getMapInfo();
        end = m.getEnd();
        width=m.getWidth();
        height=m.getHeight();
        //用于展示可达位置的地图
        mapInfo=new boolean[width][height];
        //对起点进行分析
        handle(new Point(m.getStart()[0], m.getStart()[1]));
        for (Point p : set) {
            System.out.println("x:" + p.getX() + " y:" + p.getY());
            mapInfo[p.getX()][p.getY()]=true;
        }

        System.out.println("共遍历了"+set.size()+"个点");
        //展示效果，但是对原map进行了修改，可以删除
        m.setMapInfo(mapInfo);
        m.showMap();

    }

    public boolean isFindFinal() {
        return findFinal;
    }

    public void setFindFinal(boolean findFinal) {
        this.findFinal = findFinal;
    }

    //对点进行分析上下左右
    private void handle(Point p) {

        int x = p.getX();
        int y = p.getY();
        //如果没有相同坐标则继续
        for(Point po:set){
            if(po.getX()==x&&po.getY()==y){
                return;
            }
        }
        //找到终点结束
        if (end[0] == x && end[1] == y) {
            System.out.println("找到终点");
            mapInfo[width-2][height-2]=true;
            findFinal=true;
            return;
        }

        //上
        if (info[x][y - 1]) {
            p.setUp(true);
        }
        //下
        if (info[x][y + 1]) {
            p.setDown(true);
        }
        //左
        if (info[x - 1][y]) {
            p.setLeft(true);
        }
        //右
        if (info[x + 1][y]) {
            p.setRight(true);
        }
        set.add(p);
        if(p.isUp()){
            handle(new Point(x,y-1));
        }
        if(p.isDown()){
            handle(new Point(x,y+1));
        }
        if(p.isLeft()){
            handle(new Point(x-1,y));
        }
        if(p.isRight()){
            handle(new Point(x+1,y));
        }

    }
}
