package tests;

import io.qameta.allure.Step;
import les7PageObjects.pages.RegistrationPage;
import les7PageObjects.pages.components.TableWindowComponent;

public class WebSteps extends TestBase{
    @Step("Открываем форму - Student Registration Form")
    public void openPageStudentRegistrationForm() {
    new RegistrationPage().openPage()
            .clearBanners();
    }

    @Step("Заполняем обязательные поля формы - Student Registration Form")
    public void fillRequirementFieldsInForm (String firstName, String lastName, String userGender, String userNumber) {
        new RegistrationPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .setUserNumber(userNumber);
    }

    @Step("Нажимаем кнопку - Submit")
    public void clickSmtButton() {
        new RegistrationPage()
                .clickSubmitButton();
    }

    @Step("Проверяем заполнение обязательных полей на форме - Student Registration Form")
    public void checkFormWindow(String firstName, String lastName, String userGender, String userNumber) {
        new TableWindowComponent()
                .checkModalHeader("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber)
                .checkResultEmpty("Student Email")
                .checkResultEmpty("Subjects")
                .checkResultEmpty("Hobbies")
                .checkResultEmpty("Picture")
                .checkResultEmpty("Address")
                .checkResultEmpty("State and City");
    }

    @Step("Проверяем, что не отображается таблица заполненных полей на форме - Student Registration Form")
    public void checkTableWindow_isNotVisible_() {
        new TableWindowComponent()
                .checkIsNotVisible_tableWindow();
    }

    @Step("Заполняем обязательные поля (кроме Gender) формы - Student Registration Form")
    public void fillRequirementFieldsWithoutGenderInForm (String firstName, String lastName, String userNumber) {
        new RegistrationPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserNumber(userNumber);
    }
}