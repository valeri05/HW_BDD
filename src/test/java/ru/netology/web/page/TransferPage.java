package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("button.button");
    private final SelenideElement headerTransfer = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-text-id=error-message]");

    public TransferPage() {
        headerTransfer.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amount, DataHelper.CardInfo cardInfo) {
        transfer(amount, cardInfo);
        return new DashboardPage();
    }

    public void transfer(String amount, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amount);
        from.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
