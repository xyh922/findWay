package Main;

import findWay.FindWay;
import findWay.Radiation;
import findWay.Try;
import map.Map_;


public class main {

    public static void main(String[] args) {
        //获得地图
        Map_ map = new Map_(10, 5);
        map.RandomMap();
//         map.ExampleMap();
        map.showMap();
        System.out.println("--------------------------------");
        FindWay findWay1 = new Radiation();
        findWay1.find(map);
        System.out.println("--------------------------------");
        //如果可以达到终点，进行探索终点
        if (((Radiation) findWay1).isFindFinal()) {
            FindWay findWay = new Try();

            findWay.find(map);
        }
    }
}
