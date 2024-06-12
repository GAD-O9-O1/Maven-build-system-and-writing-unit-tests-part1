import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BonusServiceTest {

    @Test
    void shouldCalculateForRegisteredAndUnderLimit() {
        BonusService service = new BonusService();
        long amount = 1000;
        boolean registered = true;
        long expected = 30;

        long actual = service.calculate(amount, registered);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverLimit() {
        BonusService service = new BonusService();
        long amount = 1_000_000;
        boolean registered = true;
        long expected = 500;

        long actual = service.calculate(amount, registered);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForNonRegisteredAndUnderLimit() {
        BonusService service = new BonusService();
        long amount = 1000;
        boolean registered = false;
        long expected = 10;

        long actual = service.calculate(amount, registered);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForNonRegisteredAndOverLimit() {
        BonusService service = new BonusService();
        long amount = 100_000_000;
        boolean registered = false;
        long expected = 500;

        long actual = service.calculate(amount, registered);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnNegativeBonusForNegativeAmount() {
        BonusService service = new BonusService();
        long amount = -100;
        boolean registered = true;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(-3, actual);
    }

    @Test
    void shouldReturnBonusForRegisteredAndDifferentAmounts() {
        BonusService service = new BonusService();
        boolean registered = true;
        long[] amounts = {0, 1, 747, 7649, 12347, 16666, 16667, 21536, 38585};
        long[] expected = {0, 0, 22, 229, 370, 499, 500, 500, 500};

        for (int i = 0; i < amounts.length; i++) {
            long actual = service.calculate(amounts[i], registered);
            Assertions.assertEquals(expected[i], actual);
        }
    }
}
