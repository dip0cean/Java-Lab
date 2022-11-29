package me.osean.effective_java.chapter_01.item_03.singleton_pattern_03;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Lesserafim;

import java.io.*;

public class LesserafimSerialization {

    public static void main(String[] args) {
        LesserafimSerialization.outputStream();
        LesserafimSerialization.inputStream();
    }

    public static void outputStream() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lesserafim.obj"))) {
            out.writeObject(Lesserafim.INSTANCE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputStream() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("lesserafim.obj"))) {
            Lesserafim lesserafim = (Lesserafim) in.readObject();
            System.out.println(lesserafim == Lesserafim.INSTANCE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
