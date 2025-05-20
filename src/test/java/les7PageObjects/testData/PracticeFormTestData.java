package les7PageObjects.testData;

import les7PageObjects.utils.RandomUtils;

public class PracticeFormTestData {

    public String
            firstName = RandomUtils.getRandomFirstName(),
            lastName = RandomUtils.getRandomLastName(),
            userGender = RandomUtils.getRandomGender(),
            userNumber = RandomUtils.getRandomPhone10Digits();
}