public class point2 {
    private int x;
    private int y;
    private int z;
    public String toString() {
        return x+":"+y;
    }
    public point2() {
        x = 0;
        y = 0;
    }
    public point2(int xx, int yy) {
        x = xx;
        y = yy;
    }
    public double distO()
    {
        double d=Math.sqrt(x*x+y*y);
        return d;
    }
    public void SetX(int xx) {
        this.x = xx;
    }
    public int getX() {
        return x;
    }
    public void SetY(int yy) {
        y = yy;
    }
    public int getY() {
        return y;
    }
    public static void main(String args[]) {
        point2 p1 = new point2(2,4);
        point2 p2 = new point2(3,7);
        System.out.println(p1);
        System.out.println(p2);
        p2 = p1;
        // now the p2 object is destroyed as the p2 reference is now given to another object.
        System.out.println(p2);
        p1.SetX(10);
        System.out.println(p1);
        System.out.println(p2);
    }
}

