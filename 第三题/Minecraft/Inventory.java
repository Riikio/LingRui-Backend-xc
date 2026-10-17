import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
public class Inventory {
    private List<ItemStack> items = new ArrayList<>();
//    用于存放背包中所有的物品堆
    private final int capacity;
//    背包容量（格子总数），同上不可以再次改变

    public Inventory(){
        this.capacity = 36;
        this.items = new ArrayList<>(36);
    }
    //默认背包有36格

    /*功能：addItem(ItemStack itemStackToAdd)
    实现向背包添加物品的完整逻辑（堆叠、找空格）。
    返回值：int，表示背包剩余容量，负数表示添加失败的物品数量。*/

    /*逻辑分析：
    * 1、先遍历背包中的所有物品堆。
    * 2、如果当前物品堆有相同物品，且未满最大堆叠数量，就直接放入当前物品堆。
    * 3、如果当前物品堆没有相同物品，或者已满最大堆叠数量，就创建一个新的物品堆。
    * 4、返回值：int-表示背包剩余容量，负数表示添加失败的物品数量。
    * */
    public int addItem(ItemStack itemStackToAdd){
        Item item = itemStackToAdd.getItem();//物品类型
        int remaining = itemStackToAdd.getQuantity();//剩余放入数量
        for (int i = 0; i < items.size(); i++) {
            if (remaining <= 0) break;
            ItemStack stack = items.get(i);
            if (stack.getItem().getName().equals(item.getName()) && !stack.isFull()) {
                int canAdd = Math.min(stack.remainingSpace(), remaining);
                items.get(i).setQuantity(items.get(i).getQuantity() + canAdd);
                remaining -= canAdd;
            }//当前堆有相同物品，且未满最大堆叠数量
        }
        while (remaining > 0 && items.size() < capacity) {
            int canAdd = Math.min(item.getMaxStackSiz(), remaining);
            items.add(new ItemStack(item, canAdd));
            remaining -= canAdd;
        }//上一堆已满或者没有相同物品堆，还有物品未放入，且背包还有空格，开一个新堆继续放入
        if(remaining > 0 && items.size() >= capacity){
            return -remaining;
        }
        else {
            int remainingCapacity = capacity - items.size();
            return remainingCapacity;
        }
    }

     /*
     从背包中移除指定数量的某种物品。
     基本逻辑与add类似
     */

    public void removeItem(String itemName, int amountToRemove){
        int count = 0;//用于记录是否存在该物品
        for (int i = 0; i < items.size() && amountToRemove > 0;) {
            ItemStack stack = items.get(i);
            if (!stack.getItem().getName().equals(itemName)) continue;
//            当前格子不是相同物品，直接跳过，不运行下一行的代码
            count++;
            int toRemove = Math.min(stack.getQuantity(), amountToRemove);
            stack.setQuantity(stack.getQuantity() - toRemove);
            amountToRemove -= toRemove;     //减少移除数量，直到移除完

            // 清空后就释放格子
            if (stack.getQuantity() == 0) {
                items.remove(i);
                i--;   //去除一个物品堆后索引回退，防止跳过下一个物品堆
            }
            else{
                i++;
            }
        }
        if(count == 0){
            System.out.println("背包中没有该物品");
        }
    }


    //简单的遍历+累加，统计背包中某种物品的数量
    public int getItemCount(String itemName){
       int count = 0;
       for (ItemStack stack : items){
           if (stack.getItem().getName().equals(itemName)){
               count += stack.getQuantity();
           }
       }
       return count;
    }

    public void display() {
        System.out.println("背包物品列表：");
        if (items.isEmpty()) System.out.println("背包为空");
        else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("-----------------");
                System.out.println("|" + items.get(i).getItem().getName() + " " + items.get(i).getQuantity() + "|");
                System.out.println("-----------------");
                System.out.println();
            }

        }
    }
}


