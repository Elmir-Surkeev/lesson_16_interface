//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Action action = new Action();
        AccesData data = new AccesData("key_1", "aasdfakdf");
        data.setValue("murashkami");
        data.getKey();
        System.out.println(data.getKey());
        System.out.println(data.getValue());
    }
}