package findWay;

public class Step {
    int no;
    int[] postion;

    public int getNo() {
        return no;
    }

    public Step(int no, int[] postion) {
        this.no = no;
        this.postion = postion;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int[] getPostion() {
        return postion;
    }

    public void setPostion(int[] postion) {
        this.postion = postion;
    }
}
