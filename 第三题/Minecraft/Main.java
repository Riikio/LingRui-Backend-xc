public class Main {
    public static void main(String[] args) {
        // 定义三种物品
        Item stone = new Item("石头",   64);
        Item plank = new Item("木板",   64);
        Item diamondSword = new Item("钻石剑", 1);
        Inventory inventory = new Inventory(); //构造默认背包为36的无参构造

//        添加物品：向背包中添加 30 个"石头"。
        inventory.addItem(new ItemStack(stone, 30));
//        inventory.display();
//        测试堆叠：再次向背包中添加 40 个"石头"。（预期：背包里应有一个 64 个的石头堆和一个 6 个的石头堆）。
        inventory.addItem(new ItemStack(stone, 40));
//        inventory.display();
//        添加不可堆叠物品：向背包中添加 1 把"钻石剑"。
        inventory.addItem(new ItemStack(diamondSword, 1));
//        inventory.display();
//        测试溢出：尝试向背包中添加 35 个"木板"堆，每个堆包含 64 个木板。（预期：填满剩余格子后，会有物品添加失败）。
        for (int i = 0; i < 35; i++) {
            System.out.println("剩余格子/溢出个数： "+inventory.addItem(new ItemStack(plank, 64)));
        }
        System.out.println("--------------------------------");
//        inventory.display();
//        移除物品：从背包中移除 10 个"石头"。（预期：64 的石头堆变为 54）
        inventory.removeItem(stone.getName(),10);
        System.out.println("--------------------------------");

        inventory.display();
        System.out.println("剩余石头总数： "+inventory.getItemCount(stone.getName()));
    }
}
