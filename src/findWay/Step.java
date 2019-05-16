package findWay;

//暂时无用
public class Step {
    int x;

    public Step(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    int y;
    int dir;//方向

    @Override
    public boolean equals(Object o) {
        Step p=(Step) o;
        if(p.x==this.x&&p.y==this.y&&p.dir==this.dir)
        return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Step{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }

    public int getX() {
        return x;

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
