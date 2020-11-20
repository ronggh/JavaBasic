package cn.alan.clone;

public class CloneTest {
    public static void main(String[] args) {
        try {
            // 修改克隆的Person对象p2关联的汽车对象的品牌属性
            // 原来的Person对象p1关联的汽车不会受到任何影响
            // 因为在克隆Person对象时其关联的汽车对象也被克隆了

            Person p1 = new Person("郭靖", 33, new Car("Benz", 300));
            // 深度克隆
            Person p2 = CloneUtils.clone(p1);
            p2.getCar().setBrand("BYD");
            System.out.println("p1 ===> " + p1);
            System.out.println("p2 ===> " + p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
