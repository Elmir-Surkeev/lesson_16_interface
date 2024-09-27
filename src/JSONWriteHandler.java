import com.google.gson.Gson;


public class JSONWriteHandler {
    private static final Gson gson = new Gson();
//    public static AccesData getAcces() {
//        try (Reader reader = new FileReader("data.json")) {
//            return gson.fromJson(reader, AccesData.class);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new AccesData;
//    }
//
//    public static void writePlayer(Computer[] players) {
//        try (Writer writer = new FileWriter("data.json")){
//            String json = gson.toJson(players);
//            writer.write(json);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
