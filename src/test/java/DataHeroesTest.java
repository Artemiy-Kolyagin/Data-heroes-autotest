import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class DataHeroesTest {
    @BeforeEach
    void autorization() {
        open("https://app.dataheroes.pro/landing");
        $(".v-toolbar__content .v-btn__content").click();
        switchTo().window("Вход в сервис Data heroes");
        $("[name=\"username\"]").setValue("kolaguin@gmail.com");
        $("[name=\"password\"]").setValue("zyzdyd-safdo0-Huxbyp");
        $("[name=\"action\"]").click();
        switchTo().window("Data heroes");
        $(byText("ПРОПУСТИТЬ")).shouldBe(Condition.visible).click();
    }

    @AfterEach
    void closeWindow() {
        Selenide.closeWindow();
    }

    @Test
//проверка работы справки на главном экране
    void shouldHelper() {
        $(".mdi-help-circle").click();
        $(withText("Проект — это ваш бизнес")).shouldBe(Condition.visible);
    }

    @Test
//изменение прав доступа
    void shouldChangingAccess() {
        $(byText("Тестовый проект 1")).click();
        $(".v-data-table__checkbox").click();
        $(byText("Назначить права")).click();
        $(byText("Редактор")).click();
        $(byText("Редактор (Пользователь не зарегестрирован)")).shouldBe(Condition.visible);
        $(".v-data-table__checkbox").click();
        $(byText("Назначить права")).click();
        $(byText("Наблюдатель")).click();
    }
    // TODO: 18.08.2021  shouldAddingNewPartisipantWithInvalidEmail нуждается в доработке
//    @Test//попытка добавления участника с невалидной email
//    void shouldAddingNewPartisipantWithInvalidEmail(){
//        $(byText("Тестовый проект 1")).click();
//        $(byText("Добавить участника")).click();
//        $("[class=\"v-input v-input--has-state theme--light v-text-field v-text-field--filled v-text-field--is-booted v-text-field--enclosed error--text\"]").setValue("khkhlkjhl");
//        $(byText("email должен быть валидным")).shouldBe(Condition.visible);
//    }

    @Test
//выход из аккаунта
    void shouldExit() {
        $("[class=\"v-avatar mx-4\"]").click();
        $(byText("Выйти")).click();
        $(byText("Войти")).shouldBe(Condition.visible);
    }
}
