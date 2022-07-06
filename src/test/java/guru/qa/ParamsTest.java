package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParamsTest {

    @ValueSource(strings = {"Димедрол табл", "Аспирин"})
    @ParameterizedTest(name = "При поиске на сайте Аптека Апрель по запросу {0} в результатах отображается текст {0}")
    void aptekaTestFirst(String testData) {
        Selenide.open("https://apteka-april.ru/");
        $("#search-field").setValue(testData);
        $("button[type='submit']").click();
        $$(".c-product-card").find(text(testData)).shouldBe(visible);

    }

    @CsvSource(value = {
            "Верапамил, Верапамил таблетки 40мг",
            "Нифедипин, Нифедипин таблетки 10мг",
            "Каптоприл, Каптоприл таблетки 25мг",
            "Амлодипин, Амлодипин таблетки 5мг"
    })
    @ParameterizedTest(name = "При поиске на сайте Аптека Апрель по запросу {0} в результатах отображается текст {1}")
    void aptekaTestCsvSource(String searchData, String expectedResult) {
        Selenide.open("https://apteka-april.ru/");
        $("#search-field").setValue(searchData);
        $("button[type='submit']").click();
        $$(".c-product-card").find(text(expectedResult)).shouldBe(visible);

    }


    static Stream<Arguments> sberTestComplexProvider() {
        return Stream.of(
                Arguments.of("еда", List.of("Продукты питания")),
                Arguments.of("продукты", List.of("Продукты питания"))
        );
    }

    @MethodSource(value = "sberTestComplexProvider")
    @ParameterizedTest(name = "При поиске {0} в результатах есть {1}")
    void sberTestComplex(String testData, List<String> expectedResult) {
        Selenide.open("https://sbermegamarket.ru/");
        $("input[placeholder='Искать товары']").click();
        $("input[placeholder='Искать товары']").setValue(testData).pressEnter();
        $$(".catalog-department-header__title").shouldHave(CollectionCondition.texts(expectedResult));
    }


}
