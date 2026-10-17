public class ItemStack {
    private Item item;
//    物品的类型。
    private int quantity;
//    当前堆叠的数量。

    public void setItem(Item item) {
        this.item = item;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //setter

    public Item getItem() {
        return item;
    }
    public int getQuantity() {
        return quantity;
    }

    //getter


    //构造方法
    public ItemStack(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemStack(){
        this.item = null;
        this.quantity = 0;
    }



    //当前背包是否已满
    public boolean isFull() {
        return quantity >= item.getMaxStackSiz();
    }

    //当前背包是否为空
    public boolean isEmpty() {
        return quantity == 0;
    }

    //背包还剩几个空格子
    public int remainingSpace() {
        return item.getMaxStackSiz() - quantity;
    }
}
