package me.osean.effective_java.chapter_01.item_02.builder_pattern.example;

import java.time.LocalDate;

public class TourDirector {
    private TourPlanBuilder builder;

    public TourDirector(TourPlanBuilder builder) {
        this.builder = builder;
    }

    public TourPlan cancunTrip() {
        return builder
                .title("칸쿤 여행")
                .startDate(LocalDate.of(2022, 12, 25))
                .nightsAndDays(2, 3)
                .whereToSay("칸쿤 라마다 호텔")
                .addPlan(0, "칸쿤 공항 도착")
                .addPlan(0, "호텔 체크인")
                .addPlan(0, "에메랄드 해변 산책")
                .getPlan();
    }

    public TourPlan longBeachTrip() {
        return builder
                .title("롱비치 투어")
                .startDate(LocalDate.of(2022, 12, 25))
                .getPlan();
    }
}
