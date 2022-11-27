package me.osean.effective_java.chapter_01.item_02.builder_pattern.example;

import java.time.LocalDate;

public interface TourPlanBuilder {

    TourPlanBuilder nightsAndDays(int nights, int days);

    TourPlanBuilder title(String title);

    TourPlanBuilder startDate(LocalDate tourDate);

    TourPlanBuilder whereToSay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);

    TourPlan getPlan();
}
