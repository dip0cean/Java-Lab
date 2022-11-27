package me.osean.effective_java.chapter_01.item_02.builder_pattern.example;

public class App {
    public static void main(String[] args) {
        TourDirector director = new TourDirector(new DefaultTourBuilder());
        TourPlan canconTrip = director.cancunTrip();
        TourPlan longBeachTrip = director.longBeachTrip();
    }
}
