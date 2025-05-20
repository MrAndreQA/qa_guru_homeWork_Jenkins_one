package les7PageObjects.pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TableWindowComponent {

    private final SelenideElement
            tableWindow = $(".table-responsive"),
            modalHeader = $(".modal-title");

    public TableWindowComponent checkModalHeader (String value) {
        modalHeader.shouldHave(text(value));
        return this;
    }

    public TableWindowComponent checkResult(String key, String value) {
        tableWindow.$(byText(key)).parent()
                .shouldHave(text(value));
        return this;
    }
    public TableWindowComponent checkResultEmpty(String key) {
        tableWindow.$(byText(key)).sibling(0)
                //.shouldHave(exactText(""));
                .shouldBe(empty);
        return this;
    }

    public TableWindowComponent checkIsNotVisible_tableWindow() {
        tableWindow.shouldNotBe(visible);
        return this;
    }
}