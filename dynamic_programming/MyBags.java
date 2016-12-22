/**
 * Exmaple code of Dynamic Programming
 * /
public class MyBags {
    /*物品类*/
    static class Things {
        /*重量*/
        private int weight;
        /*价值*/
        private int value;

        Things(int w, int v) {
            this.weight = w;
            this.value = v;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }

    /*背包总承重*/
    private int totalWeight;
    /*物品数量*/
    private int thingsNumber;
    /*总的物品*/
    private Things[] things;
    /*在放入第i个物体，总重为w时候的背包价值*/
    private int[][] values;
    private int bestValue;

    MyBags(Things[] t, int w) {
        this.things = t;
        this.totalWeight = w;
        this.thingsNumber = t.length;
        if (values == null) {
            values = new int[thingsNumber + 1][totalWeight + 1];
        }

    }

    public int getValue() {
        //对背包承重进行遍历
        for (int j = 0; j <= totalWeight; j++) {
            //对物品进行遍历
            for (int i = 0; i <= thingsNumber; i++) {
                if (i == 0 || j == 0) {
                    values[i][j] = 0;
                } else {
                    /*此时有两种情况，要么放，要么不放
                    * 为什么放?--->因为要取得最大的价值！--->在总重w情况下，前i-1个物品价值
                    *              和总重w-w[i]情况下前i-1个物品加上第i个物品的价值谁大。
                    * 为什么不放?----因为已经放不下了!--->最大价值在前面的i-1个物品中诞生！*/
                    if (j < things[i - 1].getWeight()) //放不下
                    {
                        values[i][j] = values[i - 1][j];
                    } else {
                        values[i][j] = Math.max(values[i - 1][j],
                                values[i - 1][j - things[i - 1].getWeight()] + things[i - 1].getValue());
                    }
                }
            }
        }
        bestValue = values[thingsNumber][totalWeight];
        return bestValue;
    }

    public static void main(String[] args) {
        Things[] things = new Things[] { new Things(4, 5), new Things(3, 6), new Things(7, 10) };
        int totalWeight = 8;
        MyBags mb = new MyBags(things, totalWeight);
        int best = mb.getValue();
        System.out.println(best);
    }
}