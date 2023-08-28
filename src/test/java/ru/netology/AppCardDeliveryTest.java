package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + currentDate));
    }

    @Test
    public void enterLatinCityTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Belgorod");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void enterEmptyCityTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void enterMissingFromTheListCityTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Киев");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void enterLatinNameTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Stepanova Anna");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void enterEmptyNameTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void enterNumbersNameTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("555");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void enterShortPhoneTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+7977555");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void enterPhoneWithoutPlusTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void enterEmptyPhoneTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void unmarkedCheckboxTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").should(Condition.appear);
    }

    @Test
    public void enterEmptyDateTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    public void enterDateEarlierThanThreeDaysFromCurrentDateTest() {
        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Белгород");
        String currentDate = generateDate(1, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Степанова Анна");
        $("[data-test-id='phone'] input").setValue("+79775557755");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldOrderCardDeliverySuccessful() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(7).format(formatter);

        open("http://localhost:7777");
        $("[data-test-id= 'city'] input").setValue("Бе");
        $(".input__menu").find(withText("Белгород")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(meetingDate);
        $("[data-test-id=name] input").setValue("Степанова Анна");
        $("[data-test-id=phone] input").setValue("+79775557755");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!"));
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + meetingDate));
    }
}
