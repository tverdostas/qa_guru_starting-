import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome"; // Укажите ваш браузер
        Configuration.pageLoadStrategy = "eager"; // Установите стратегию загрузки страницы
    }

    @AfterEach
    public void down(){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    void antibotSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("html").shouldHave(text("Об этой странице"));
    }

    @Test
    void searchTestYahoo() {
        open("https://www.yahoo.com/");
        $("[id=ybar-sbq]").shouldBe(visible);
        $("[id=ybar-sbq]").setValue("selenide");
        $("[id=ybar-search]").click();
        switchTo().window(1); // переключение на новое окно
        $("[class='main-res']").shouldHave(text("selenide.org"));
    }

    @Test
    void searchTestDuckduckgo() {
        open("https://duckduckgo.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=web_content_wrapper]").shouldHave(text("selenide.org"));
    }

    @Test
    void successfulLoginTest(){
        open("https://auth.niffler.qa.guru/login");
        $("[name=username]").setValue("qaguru31");
        $("[name=password]").setValue("1234");
        $("[id=login-button]").click();
        sleep(1000);
        $("[id=stat]").shouldHave(text("Statistics"));
    }

    @Test
    void searchYandexTest() {
        open("https://ya.ru/");
        $("[name=text]").setValue("selenide").pressEnter();
        $("button[class*='Distribution-ButtonClose']").click();
        $("[id='search-result']").shouldHave(text("ru.selenide.org"));
    }

/*    @Test
    void successfulLogoutTest(){
        open("https://auth.niffler.qa.guru/login");
        $("[name=username]").setValue("qaguru31");
        $("[name=password]").setValue("1234");
        $("[id=login-button]").click();
        $("[id=stat]").shouldHave(text("Statistics"));

        $("[aria-label=Menu]").click();
        $("[role=menuitem]:nth-child(4)").click();
        //$("[class*=MuiTouchRipple]:nth-child(8)").click();
        $(byXpath)
    }*/
}
