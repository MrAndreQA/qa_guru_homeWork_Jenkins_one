package tests;

import io.qameta.allure.*;
import les7PageObjects.testData.PracticeFormTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class HomeWorkJenkinsWithParametersTest extends RemoteTestBase {

    private PracticeFormTestData data;
    @BeforeEach
    public void beforeEach() {
        setupRemote(); // для удаленного запуска тестов
        data = new PracticeFormTestData(); // для генерации тестовых данных
    }


    @Tag("basic_test")
    @DisplayName("Practice Form: отправка формы при заполнении только обязательных полей")
    @Feature("Раздел Practice Form ")
    @Story("Заполнение  и отправка - Student Registration Form")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Student Registration Form", url = "https://demoqa.com/automation-practice-form")
    @Owner("Volodin_AS")
    @Test
    void fillOnlyRequiredFieldsInPracticeFormTest() {
        WebSteps steps = new WebSteps();
        steps.openPageStudentRegistrationForm();
        steps.fillRequirementFieldsInForm(data.firstName, data.lastName, data.userGender, data.userNumber);
        steps.clickSmtButton();
        steps.checkFormWindow(data.firstName, data.lastName, data.userGender, data.userNumber);
    }

    @Tag("minimal_test")
    @DisplayName("Practice Form: негативный тест - попытка отправить форму при незаполненном чек-боксе Sex")
    @Feature("Раздел Practice Form ")
    @Story("Заполнение  и отправка - Student Registration Form")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Student Registration Form", url = "https://demoqa.com/automation-practice-form")
    @Owner("Volodin_AS")
    @Test
    void fillPracticeFormWithoutGenderTest() {
        WebSteps steps = new WebSteps();
        steps.openPageStudentRegistrationForm();
        steps.fillRequirementFieldsWithoutGenderInForm(data.firstName, data.lastName, data.userNumber);
        steps.clickSmtButton();
        steps.checkTableWindow_isNotVisible_();
    }
}