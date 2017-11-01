import java.math.BigDecimal;

public class TestBigDecimal {
  public static void main(String[] args) {
    BigDecimal bn1 = new BigDecimal(
         "98765423462576235623562346234623462.35632456234567890");
    BigDecimal bn2 = new BigDecimal( 
         "9898234523235624664376437634674373436547.34586558");
    BigDecimal bn3 = bn1.multiply(bn2);
    System.out.println(bn3);
  }
}
