package kro.kr.rhya_network.aicharacter_project.core.data;

public class LAHiyoriModelData {
    public enum MotionData {
        HIYORI_DEFAULT(0),
        HIYORI_QUESTION_AND_ANSWER(1),
        HIYORI_EXCITED(2),
        HIYORI_LITTLE_SAD(3),
        HIYORI_PLEASURE(4),
        HIYORI_PLEASURE_IN_HANDS(5),
        HIYORI_WOW(6),
        HIYORI_PLEASURE_OUT_HANDS(7),
        HIYORI_WOW_AND_LITTLE_SAD(8),
        HIYORI_WORTY(9);

        private final int number;

        MotionData(int number) {
            this.number = number;
        }

        public int getMotionNumber() {
            return number;
        }
    }
}
