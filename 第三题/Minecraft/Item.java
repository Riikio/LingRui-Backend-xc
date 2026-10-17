public class Item {
    private final String name;
//    物品名称，分别有三类物品，石头、木板、钻石剑。
    private final int maxStackSiz;
//    物品的最大堆叠数量，石头、木板、钻石剑的最大堆叠数量分别为 64、64、1。

    public String getName(){
        return this.name;
    }
    public int getMaxStackSiz(){
        return this.maxStackSiz;
    }

    public Item(){
        this.name = "";
        this.maxStackSiz = 0;
    }
    public Item(String name,int maxStackSiz){
        this.name = name;
        this.maxStackSiz = maxStackSiz;
    }
}
