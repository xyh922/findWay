package map;

import java.util.Random;

//地图坐标从1开始到长度-1结束，因为有一层外包围（围墙）
//按照习惯x为横轴，y为纵轴
//Example有例子
public class Map_ {
    private int height;
    private int width;
    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    private boolean[][] mapInfo;
    public Map_(int width, int height){
        this.width=width+2;
        this.height=height+2;


    }
    //起点终点
    public int[] getStart(){
        return new int[]{start_x,start_y};
    }
    public int[] getEnd(){
        return new int[]{end_x,end_y};
    }

    //
    public void setStart(int x,int y){
        this.start_x=x;
        this.start_y=y;
    }
    public void setEnd(int x,int y){
        this.end_x=x;
        this.end_y=y;
    }
    public int getHeight() {
        return height;
    }



    public int getWidth() {
        return width;
    }


    public  boolean[][] getMapInfo() {
        return mapInfo;
    }

    public  void setMapInfo(boolean[][] mapInfo) {
        this.mapInfo = mapInfo;
    }
//随机生成地图
    public void RandomMap(){
        //定义地图长宽,有一层不可通行的外包围
        mapInfo=new boolean[width][height];
        //随机地图，true为可以通行，false为不可以通行

        //设置外包围  弃用
       // setBorder();
        //随机设置除外包围部分

        Random random=new Random();
        for(int y=1;y<height-1;y++){
        for(int x=1;x<width-1;x++){
//            可达点多于不可达
                if(random.nextInt(10)<7){
                    mapInfo[x][y]=true;
                }
                //完全随机
//                mapInfo[x][y]=random.nextBoolean();
            }
        }
        //使起点终点可以通行
        mapInfo[width-2][height-2]=true;
        mapInfo[1][1]=true;
        setStart(1,1);
        setEnd(width-2,height-2);



    }
    //展示地图
    public void showMap(){
        //不可达为实心，可达为空心
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){

                if(mapInfo[x][y]){
                    System.out.print("□");
                }else {
                    System.out.print("■");
                }

            }
            System.out.println("");
        }

    }

    //生成示例地图
    public void ExampleMap(){
        mapInfo=new boolean[width][height];
        for(int y=1;y<height-1;y++){

            for(int x=1;x<width-1;x++){
                mapInfo[x][y]=false;
            }
        }
        for(int y=1;y<height-1;y++){
            mapInfo[1][y]=true;
        }
        for(int x=1;x<width-1;x++){
            mapInfo[x][height-2]=true;
        }
        //设置起点终点为左上角和右下角
        setStart(1,1);
        setEnd(width-2,height-2);





    }
    private void setBorder(){
        for(int x=0;x<width;x++){
            mapInfo[x][0]=false;
            mapInfo[x][height-1]=false;
        }
        for(int y=0;y<height;y++){
            mapInfo[0][y]=false;
            mapInfo[width-1][y]=false;
        }
    }
}
