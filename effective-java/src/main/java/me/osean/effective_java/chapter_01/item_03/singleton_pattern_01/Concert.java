package me.osean.effective_java.chapter_01.item_03.singleton_pattern_01;

public class Concert {
    private boolean lightOn;
    private boolean mainStateOpen;

    private final Idol idol;

    public Concert(Idol idol) {
        this.idol = idol;
    }

    public static void main(String[] args) {
        // 장점 1. 간결하고 싱글턴임을 API 에 들어낼 수 있다.
        // 단점 1. 싱글톤을 사용하는 클라이언트 테스트하기 어려워진다.
        // 만약 외부 API 를 호출하거나 리소스가 많이 소요되는 오퍼레이션인 경우, 매번 새로운 인스턴스를 생성하는 것은 비효울적이다.
        // 때문에 이를 효율적으로 처리하기 위해서 Mock 객체를 이용해서 주입하도록 해야 한다.
        // 리허설인 경우
        Concert dryRun = new Concert(new Idol() {
            @Override
            public void greetings() {
                System.out.println("무대인사 리허설 진행 중 입니다.");
            }

            @Override
            public void sing() {
                System.out.println("테크 리허설 진행 중 입니다.");
            }
        });
        dryRun.perform();

        // 그랜드 오픈인 경우
        Concert grandOpen = new Concert(Lesserafim.INSTANCE);
        grandOpen.perform();
    }

    public void perform() {
        this.mainStateOpen = true;
        this.lightOn = true;
        this.idol.greetings();
        this.idol.sing();
    }

    public boolean isLightOn() {
        return this.lightOn;
    }

    public boolean isMainStateOpen() {
        return this.mainStateOpen;
    }
}
