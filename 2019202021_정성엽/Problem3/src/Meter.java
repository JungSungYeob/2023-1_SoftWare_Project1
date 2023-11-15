public interface Meter {
    default void distance(int distance){
        System.out.printf("이동거리는 %d 입니다.\n",distance);
    }

    public abstract int fare(int distance);
    public int getTotalFare();
}
