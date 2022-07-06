package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Disabled("Отключено до релиза 10.07.2022")
    @DisplayName("Проверка операции равенства")
    @Test
    void onlyTest(){
        Assertions.assertEquals(10,10);

    }

    @Test
    @DisplayName("Проверка операции НЕравенства")
    void secondTest(){
        Assertions.assertNotEquals(20,10);

    }
}
