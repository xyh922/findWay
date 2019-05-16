package Main;

import findWay.FindWay;
import findWay.Radiation;
import findWay.RandomTry;
import findWay.Try;
import map.Map_;

import java.util.Random;

public class main {

     public static void main(String[] args)
     {
         //获得地图
         Map_ map=new Map_(35,5);
         map.RandomMap();
//         map.ExampleMap();
         map.showMap();
//
         FindWay findWay=new Radiation();
         findWay.find(map);



     }
}
