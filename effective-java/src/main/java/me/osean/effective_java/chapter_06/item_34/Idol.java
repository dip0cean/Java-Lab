package me.osean.effective_java.chapter_06.item_34;

public interface Idol {

    static <E extends Idol> boolean checkSex(Idol idol, Class<E> clazz) {
        return idol.getClass().isAssignableFrom(clazz);
    }

    int getRank();

    enum MaleIdol implements Idol {
        BTS(1), EXO(2), NCT_127(3), SEVENTEEN(4), TREASURE(5);

        private final int rank;

        MaleIdol(int rank) {
            this.rank = rank;
        }

        @Override
        public int getRank() {
            return this.rank;
        }
    }

    enum FemaleIdol implements Idol {
        LE_SSERAFIM(1), G_IDLE(2), AESPA(3), STACY(4), IVE(5);

        private final int rank;

        FemaleIdol(int rank) {
            this.rank = rank;
        }

        @Override
        public int getRank() {
            return this.rank;
        }
    }
}
