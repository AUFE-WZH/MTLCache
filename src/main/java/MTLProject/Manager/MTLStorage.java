package MTLProject.Manager;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class MTLStorage {
    private String storageFilePath;

    public MTLStorage(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    public String getStorageFilePath() {
        return storageFilePath;
    }

    public void setStorageFilePath(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    protected void writeObjectToFile(Object obj) throws IOException {
        writeObjectToFile(obj, storageFilePath);
    }

    protected Object readObjectFromFile(Class<?> cls) throws IOException, ClassNotFoundException {
        return readObjectFromFile(storageFilePath, cls);
    }

    public static void writeObjectToFile(Object obj, String filePath) throws IOException {
        String jsonString = JSONObject.toJSONString(obj, true);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(jsonString);
        oos.close();
    }

    public static Object readObjectFromFile(String filePath, Class<?> cls) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        String jsonString = (String) ois.readObject();
        ois.close();
        return JSONObject.parseObject(jsonString, cls);
    }
}
